package com.apache.encryptor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import com.apache.ciphers.BaseAlgorithm;
import com.apache.ciphers.CaesarCipher;
import com.apache.ciphers.MultiplicativeCipher;
import com.apache.ciphers.XORCipher;

public class EncryptorExecuter {
	private static final CaesarCipher caesarCipher = new CaesarCipher();
	private static final MultiplicativeCipher multiplicativeCipher = new MultiplicativeCipher();
	private static final XORCipher xorCipher = new XORCipher();
	private static final BaseAlgorithm[] baseAlgorithms ={caesarCipher,multiplicativeCipher,xorCipher};
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
