package com.apache.ciphers;

import java.io.FileOutputStream;
import java.io.IOException;

import com.apache.encryptor.FileHolder;

public class SplitAlgorithm extends ExtendedAlgorithm{

	public SplitAlgorithm(BaseAlgorithm baseAlgorithm, BaseAlgorithm secondaryBaseAlgorithm) {
		super("SplitAlgorithm",baseAlgorithm, secondaryBaseAlgorithm);
	}

	@Override
	public void encrypt(FileHolder fileHolder) throws IOException {
		execute(fileHolder, "Encryption");
	}

	@Override
	public void decrypt(FileHolder fileHolder) throws IOException {
		execute(fileHolder, "Decryption");
	}
	
	public void execute(FileHolder fileHolder , String choice) throws IOException{
    	byte[] fileBytes = fileHolder.getData();
    	if (choice.equals("Encryption")){
            for (int i = 0; i < fileBytes.length; i++) 
            {
            	if(i%2 ==0){
            		fileBytes[i] = baseAlgorithm.encryptByte(fileBytes[i], baseAlgorithm.key);
            	}
            	else{
            		fileBytes[i] = secondaryBaseAlgorithm.encryptByte(fileBytes[i], secondaryBaseAlgorithm.key);
            	}
            }
            	FileOutputStream fos = new FileOutputStream(fileHolder.getEncryptedResultPath());
            	fos.write(fileBytes);
            	fos.close();
    	}
    	else if (choice.equals("Decryption")){
            for (int i = 0; i < fileBytes.length; i++) 
            {
            	if(i%2 ==0){
            		fileBytes[i] = baseAlgorithm.decryptByte(fileBytes[i], baseAlgorithm.key);
            	}
            	else{
            		fileBytes[i] = secondaryBaseAlgorithm.decryptByte(fileBytes[i], secondaryBaseAlgorithm.key);
            	}
            }
    		 	FileOutputStream fos = new FileOutputStream(fileHolder.getDecryptedResultPath());
    		 	fos.write(fileBytes);
    		 	fos.close();
    }
	}
	

}
