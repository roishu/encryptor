package com.apache.encryptor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

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
	private JAXBContext caesarCipherContext;
	private JAXBContext multiplicativeCipherContext;
	private JAXBContext xorCipherContext;
	private JAXBContext reverseAlgorithmContext;
	private JAXBContext splitAlgorithmContext;
	private JAXBContext doubleAlgorithmContext;
	private Marshaller marshaller;
	private Unmarshaller caesarUnmarshaller , multiplicativeUnmarshaller , xorUnmarshaller;


	public EncryptorManager() throws JAXBException{
		initJAXB();
	}

	private void initJAXB() throws JAXBException {
		caesarCipherContext= JAXBContext.newInstance(CaesarCipher.class);
		multiplicativeCipherContext= JAXBContext.newInstance(MultiplicativeCipher.class);
		xorCipherContext= JAXBContext.newInstance(XORCipher.class);
		//reverseAlgorithmContext= JAXBContext.newInstance(ReverseAlgorithm.class);
		//splitAlgorithmContext= JAXBContext.newInstance(SplitAlgorithm.class);
		doubleAlgorithmContext= JAXBContext.newInstance(DoubleAlgorithm.class);
		//create-XML-Ciphers
		//caesarCipher
		marshaller = caesarCipherContext.createMarshaller();
		marshaller.marshal(new CaesarCipher(), new File("CaesarCipher.xml"));
		//multiplicativeCipher
		marshaller = multiplicativeCipherContext.createMarshaller();
		marshaller.marshal(new MultiplicativeCipher(), new File("MultiplicativeCipher.xml"));
		//xorCipher
		marshaller = xorCipherContext.createMarshaller();
		marshaller.marshal(new XORCipher(), new File("XORCipher.xml"));
	}

	private void executeAlgorithmFromXML(String choice , FileHolder fileHolder){

		//    	Marshaller marshaller2 = this.doubleContext.createMarshaller();
		//    	marshaller2.marshal(new DoubleAlgorithm(), new File("DoubleAlgorithm.xml"));
		//    	
		//    	Unmarshaller unmarshaller2 = this.doubleContext.createUnmarshaller();
		//    	DoubleAlgorithm doubleAlgorithm = (DoubleAlgorithm) unmarshaller2.unmarshal(new File("DoubleAlgorithm.xml"));
		//    	
		//    	assertEquals(doubleAlgorithm.getBaseAlgorithmName(), "CaesarCipher");
		//    	assertEquals(doubleAlgorithm.getSecondaryBaseAlgorithmName(), "MultiplicativeCipher");
	}

	public void executeBaseAlgorithm(String choice , FileHolder fileHolder) throws IOException{
		int index = getCipherIndex(choice);
		baseAlgorithms[index].execute(fileHolder, "Encryption");
		baseAlgorithms[index].execute(fileHolder, "Decryption");
		renameEncryptedFile(fileHolder);
	}

	public void executeExtendedAlgorithm(ExtendedAlgorithm extendedAlgorithm , FileHolder fileHolder) throws IOException{
		extendedAlgorithm.execute(fileHolder, "Encryption");
		extendedAlgorithm.execute(fileHolder, "Decryption");
		if(extendedAlgorithm.getName().equals("ReverseAlgorithm"))
			((ReverseAlgorithm)extendedAlgorithm).swapFiles(fileHolder);
		renameEncryptedFile(fileHolder);
	}

	public void executeDoubleAlgorithm(String choice1 , String choice2, FileHolder fileHolder) throws IOException{
		int index1 = getCipherIndex(choice1);
		int index2 = getCipherIndex(choice2);
		doubleAlgorithm = new DoubleAlgorithm(baseAlgorithms[index1],baseAlgorithms[index2]);
		executeExtendedAlgorithm(doubleAlgorithm,fileHolder);
	}

	public void executeReverseAlgorithm(String choice , FileHolder fileHolder) throws IOException{
		int index = getCipherIndex(choice);
		reverseAlgorithm = new ReverseAlgorithm(baseAlgorithms[index]);
		executeExtendedAlgorithm(reverseAlgorithm,fileHolder);
	}

	public void executeSplitAlgorithm(String choice1 , String choice2, FileHolder fileHolder) throws IOException{
		int index1 = getCipherIndex(choice1);
		int index2 = getCipherIndex(choice2);
		splitAlgorithm = new SplitAlgorithm(baseAlgorithms[index1],baseAlgorithms[index2]);
		executeExtendedAlgorithm(splitAlgorithm,fileHolder);
	}

	public void executeBaseAlgorithmFromXML(String algorithm, FileHolder fileHolder) throws JAXBException, IOException {
		// TODO Auto-generated method stub
		int index = getCipherIndex(algorithm);
		BaseAlgorithm baseAlgorithm = getBaseAlgorithmFromXML(index);
		if(baseAlgorithm!=null){
			baseAlgorithm.execute(fileHolder, "Encryption");
			baseAlgorithm.execute(fileHolder, "Decryption");
			renameEncryptedFile(fileHolder);
		}
		else{
			//throw NullPointerException
		}
		
	}

	private BaseAlgorithm getBaseAlgorithmFromXML(int index) throws JAXBException {
		BaseAlgorithm baseAlgorithm = null;
		switch(index){
		case 0: 
			caesarUnmarshaller = this.caesarCipherContext.createUnmarshaller();
			baseAlgorithm= (CaesarCipher) caesarUnmarshaller.unmarshal(new File("CaesarCipher.xml")); 
			System.out.println(baseAlgorithm.getName()+"XML");
			break;
		case 1: 
			multiplicativeUnmarshaller = this.multiplicativeCipherContext.createUnmarshaller();
			baseAlgorithm= (MultiplicativeCipher) multiplicativeUnmarshaller.unmarshal(new File("MultiplicativeCipher.xml")); 
			System.out.println(baseAlgorithm.getName()+"XML");
			break;
		case 2: 
			xorUnmarshaller = this.xorCipherContext.createUnmarshaller();
			baseAlgorithm= (XORCipher) xorUnmarshaller.unmarshal(new File("XORCipher.xml")); 
			System.out.println(baseAlgorithm.getName()+"XML");
			break;
		}
		return baseAlgorithm;
	}

	public void executeSplitAlgorithmFromXML(String choice1, String choice2, FileHolder fileHolder) throws IOException, JAXBException {
		// TODO Auto-generated method stub
		int index1 = getCipherIndex(choice1);
		int index2 = getCipherIndex(choice2);
		splitAlgorithm = new SplitAlgorithm(getBaseAlgorithmFromXML(index1),getBaseAlgorithmFromXML(index2));
		executeExtendedAlgorithm(splitAlgorithm,fileHolder);
	}

	public void executeReverseAlgorithmFromXML(String choice, FileHolder fileHolder) throws IOException, JAXBException {
		// TODO Auto-generated method stub
		int index = getCipherIndex(choice);
		reverseAlgorithm = new ReverseAlgorithm(getBaseAlgorithmFromXML(index));
		executeExtendedAlgorithm(reverseAlgorithm,fileHolder);
	}

	public void executeDoubleAlgorithmFromXML(String choice1, String choice2, FileHolder fileHolder) throws IOException, JAXBException {
		// TODO Auto-generated method stub
		int index1 = getCipherIndex(choice1);
		int index2 = getCipherIndex(choice2);
		doubleAlgorithm = new DoubleAlgorithm(getBaseAlgorithmFromXML(index1),getBaseAlgorithmFromXML(index2));
		executeExtendedAlgorithm(doubleAlgorithm,fileHolder);
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
