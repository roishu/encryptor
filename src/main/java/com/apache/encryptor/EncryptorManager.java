package com.apache.encryptor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import com.apache.ciphers.BaseAlgorithm;
import com.apache.ciphers.CaesarCipher;
import com.apache.ciphers.DoubleAlgorithm;
import com.apache.ciphers.ExtendedAlgorithm;
import com.apache.ciphers.MultiplicativeCipher;
import com.apache.ciphers.ReverseAlgorithm;
import com.apache.ciphers.SplitAlgorithm;
import com.apache.ciphers.XORCipher;

public class EncryptorManager {
	private	CaesarCipher caesarCipher = new CaesarCipher();
	private	MultiplicativeCipher multiplicativeCipher = new MultiplicativeCipher();
	private	XORCipher xorCipher = new XORCipher();
	private	DoubleAlgorithm doubleAlgorithm;
	private	ReverseAlgorithm reverseAlgorithm;
	private	SplitAlgorithm splitAlgorithm;
	private ExtendedAlgorithm extendedAlgorithm;
	private	BaseAlgorithm[] baseAlgorithms ={caesarCipher,multiplicativeCipher,xorCipher};

	public EncryptorManager(){
		
	}
	
	public void executeBaseAlgorithm(String choice , FileHolder fileHolder) throws IOException{
		int index = getAlgorithmIndex(choice);
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
		int index1 = getAlgorithmIndex(choice1);
		int index2 = getAlgorithmIndex(choice2);
		doubleAlgorithm = new DoubleAlgorithm(baseAlgorithms[index1],baseAlgorithms[index2]);
		executeExtendedAlgorithm(doubleAlgorithm,fileHolder);
	}
	
	public void executeReverseAlgorithm(String choice , FileHolder fileHolder) throws IOException{
		int index = getAlgorithmIndex(choice);
		reverseAlgorithm = new ReverseAlgorithm(baseAlgorithms[index]);
		executeExtendedAlgorithm(reverseAlgorithm,fileHolder);
	}
	
	public void executeSplitAlgorithm(String choice1 , String choice2, FileHolder fileHolder) throws IOException{
		int index1 = getAlgorithmIndex(choice1);
		int index2 = getAlgorithmIndex(choice2);
		splitAlgorithm = new SplitAlgorithm(baseAlgorithms[index1],baseAlgorithms[index2]);
		executeExtendedAlgorithm(splitAlgorithm,fileHolder);
	}

	private void renameEncryptedFile(FileHolder fileHolder) {
		File decryptedFile = new File (fileHolder.getDecryptedResultPath());
		decryptedFile.renameTo(new File (fileHolder
				.getDirectoryPath()+"\\"+fileHolder.getFileNameWithoutExtension()
				+"-decrypted"+fileHolder.expectedExtension()));
	}

	private int getAlgorithmIndex(String choice) {
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
