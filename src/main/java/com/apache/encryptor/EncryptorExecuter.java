package com.apache.encryptor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import com.apache.ciphers.BaseAlgorithm;
import com.apache.ciphers.CaesarCipher;
import com.apache.ciphers.DoubleCipher;
import com.apache.ciphers.ExtendedAlgorithm;
import com.apache.ciphers.MultiplicativeCipher;
import com.apache.ciphers.ReverseCipher;
import com.apache.ciphers.SplitCipher;
import com.apache.ciphers.XORCipher;

public class EncryptorExecuter {
	private	CaesarCipher caesarCipher = new CaesarCipher();
	private	MultiplicativeCipher multiplicativeCipher = new MultiplicativeCipher();
	private	XORCipher xorCipher = new XORCipher();
	private	DoubleCipher doubleCipher;
	private	ReverseCipher reverseCipher;
	private	SplitCipher splitCipher;
	private	BaseAlgorithm[] baseAlgorithms ={caesarCipher,multiplicativeCipher,xorCipher};

	/**
	 * time =
	 * 		long endTime = System.nanoTime();
		long duration = (endTime - startTime) / 1000000 ;
		System.out.println("Time: " + duration + "ms."); 
	 */
	public EncryptorExecuter(){
		
	}
	
	public void executeBaseAlgorithm(String choice , FileHolder fileHolder) throws IOException{
		int index = getAlgorithmIndex(choice);
		baseAlgorithms[index].execute(fileHolder, "Encryption");
		baseAlgorithms[index].execute(fileHolder, "Decryption");
	   renameEncryptedFile(fileHolder);
	}
	
	public void executeExtendedAlgorithm(ExtendedAlgorithm extendedAlgorithm , FileHolder fileHolder) throws IOException{
		extendedAlgorithm.encrypt(fileHolder);
		extendedAlgorithm.decrypt(fileHolder);
		if (extendedAlgorithm instanceof ReverseCipher)
			reverseCipher.swapFiles(fileHolder);	
	   renameEncryptedFile(fileHolder);
	}

	public void executeDoubleAlgorithm(String choice1 , String choice2, FileHolder fileHolder) throws IOException{
		int index1 = getAlgorithmIndex(choice1);
		int index2 = getAlgorithmIndex(choice2);
		doubleCipher = new DoubleCipher(baseAlgorithms[index1],baseAlgorithms[index2]);
		executeExtendedAlgorithm(doubleCipher,fileHolder);
	}
	
	public void executeReverseAlgorithm(String choice , FileHolder fileHolder) throws IOException{
		int index = getAlgorithmIndex(choice);
		reverseCipher = new ReverseCipher(baseAlgorithms[index]);
		executeExtendedAlgorithm(reverseCipher,fileHolder);
	}
	
	public void executeSplitAlgorithm(String choice1 , String choice2, FileHolder fileHolder) throws IOException{
		int index1 = getAlgorithmIndex(choice1);
		int index2 = getAlgorithmIndex(choice2);
		splitCipher = new SplitCipher(baseAlgorithms[index1],baseAlgorithms[index2]);
		executeExtendedAlgorithm(splitCipher,fileHolder);
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
