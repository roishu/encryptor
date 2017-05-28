package com.apache.encryptor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.locks.ReentrantLock;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.apache.ciphers.BaseAlgorithm;
import com.apache.ciphers.CaesarCipher;
import com.apache.ciphers.DoubleAlgorithm;
import com.apache.ciphers.ExtendedAlgorithm;
import com.apache.ciphers.MultiplicativeCipher;
import com.apache.ciphers.ReverseAlgorithm;
import com.apache.ciphers.SplitAlgorithm;
import com.apache.ciphers.XORCipher;
import com.apache.exception.NoSuchFunctionException;
import com.apache.jaxb.DoubleAlgorithmJAXB;

public class EncryptorManager {
	//Algorithms and Ciphers
	private	CaesarCipher caesarCipher = new CaesarCipher();
	private	MultiplicativeCipher multiplicativeCipher = new MultiplicativeCipher();
	private	XORCipher xorCipher = new XORCipher();
	private	DoubleAlgorithm doubleAlgorithm;
	private	ReverseAlgorithm reverseAlgorithm;
	private	SplitAlgorithm splitAlgorithm;
	private ExtendedAlgorithm extendedAlgorithm;
	private	BaseAlgorithm[] baseAlgorithms ={caesarCipher,multiplicativeCipher,xorCipher};
	//JAXB
	private DoubleAlgorithmJAXB doubleAlgorithmJAXB;
	private JAXBContext jc;
	private Marshaller marshaller;
	private File xmlFile;
	//mutex
	private ReentrantLock lock = SingletonLockManager.instance();


	public EncryptorManager() throws JAXBException{
		initJAXB("CaesarCipher","XORCipher");
	}

	private void initJAXB(String choice1 , String choice2) throws JAXBException {
		jc = JAXBContext.newInstance(DoubleAlgorithmJAXB.class);
        marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        xmlFile = new File("DoubleAlgorithmJAXB.xml");
		//marshel into xmlFile
		doubleAlgorithmJAXB = new DoubleAlgorithmJAXB();
        doubleAlgorithmJAXB.setCipher(choice1);
        doubleAlgorithmJAXB.setSecondaryCipher(choice2);
        marshaller.marshal(doubleAlgorithmJAXB, xmlFile);
        doubleAlgorithmJAXB=null;
	}

	public void executeBaseAlgorithm(String choice , FileHolder fileHolder) throws IOException, NoSuchFunctionException{
		int index = getCipherIndex(choice);
		baseAlgorithms[index].execute(fileHolder, "Encryption");
		baseAlgorithms[index].execute(fileHolder, "Decryption");
		renameEncryptedFile(fileHolder);
	}

	public void executeExtendedAlgorithm(ExtendedAlgorithm extendedAlgorithm , FileHolder fileHolder) throws IOException, NoSuchFunctionException{
		extendedAlgorithm.execute(fileHolder, "Encryption");
		extendedAlgorithm.execute(fileHolder, "Decryption");
		if(extendedAlgorithm.getName().equals("ReverseAlgorithm"))
			((ReverseAlgorithm)extendedAlgorithm).swapFiles(fileHolder);
		renameEncryptedFile(fileHolder);
	}

	public void executeDoubleAlgorithm(String choice1 , String choice2, FileHolder fileHolder) throws IOException, JAXBException, NoSuchFunctionException{
		lock.lock();
		initJAXB(choice1,choice2);
		Unmarshaller unmarshaller = jc.createUnmarshaller(); //create here to avoid UnmarshalException
        DoubleAlgorithmJAXB doubleAlgorithmJAXB = (DoubleAlgorithmJAXB) unmarshaller.unmarshal(xmlFile);
        doubleAlgorithmJAXB.setCipher(choice1);
        doubleAlgorithmJAXB.setSecondaryCipher(choice2);
    	doubleAlgorithmJAXB.execute(fileHolder, "Encryption");
    	doubleAlgorithmJAXB.execute(fileHolder, "Decryption");
    	renameEncryptedFile(fileHolder);
    	lock.unlock();
	}

	public void executeReverseAlgorithm(String choice , FileHolder fileHolder) throws IOException, NoSuchFunctionException{
		int index = getCipherIndex(choice);
		reverseAlgorithm = new ReverseAlgorithm(baseAlgorithms[index]);
		executeExtendedAlgorithm(reverseAlgorithm,fileHolder);
	}

	public void executeSplitAlgorithm(String choice1 , String choice2, FileHolder fileHolder) throws IOException, NoSuchFunctionException{
		int index1 = getCipherIndex(choice1);
		int index2 = getCipherIndex(choice2);
		splitAlgorithm = new SplitAlgorithm(baseAlgorithms[index1],baseAlgorithms[index2]);
		executeExtendedAlgorithm(splitAlgorithm,fileHolder);
	}

	private void renameEncryptedFile(FileHolder fileHolder) {
		File decryptedFile = new File (fileHolder.getDecryptedResultPath());
		decryptedFile.renameTo(new File (fileHolder
				.getDirectoryPath()+"\\"+fileHolder.getFileNameWithoutExtension()
				+"-decrypted"+fileHolder.expectedExtension()));
	}

	private int getCipherIndex(String choice) {
		if (choice.equals("CaesarCipher")) return 0;
		else if (choice.equals("MultiplicativeCipher")) return 1;
		return 2;
	}

	public void deleteEncryptorFiles(FileHolder fileHolder){
		//delete Files
		Paths.get(fileHolder.getEncryptedResultPath()).toFile().delete();
		Paths.get(fileHolder.getDecryptedResultPath()).toFile().delete();	
	}


}
