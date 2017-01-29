package com.apache.ciphers;

import java.io.FileOutputStream;
import java.io.IOException;

import com.apache.encryptor.FileHolder;

public class SplitCipher extends ExtendedAlgorithm{

	public SplitCipher(BaseAlgorithm baseAlgorithm, BaseAlgorithm secondaryBaseAlgorithm) {
		super(baseAlgorithm, secondaryBaseAlgorithm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void encrypt(FileHolder fileHolder) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decrypt(FileHolder fileHolder) throws IOException {
		// TODO Auto-generated method stub
		
	}
	
	public void execute(FileHolder fileHolder , String choice) throws IOException{
    	long startTime = System.nanoTime();
    	byte[] fileBytes = fileHolder.getData(); //may be changed in FileHolder - check it !
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
            	FileOutputStream fos = new FileOutputStream(fileHolder.getDirectoryPath()+"\\encrypted-algorithm.txt");
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
    		 	FileOutputStream fos = new FileOutputStream(fileHolder.getDirectoryPath()+"\\decrypted-algorithm.txt");
    		 	fos.write(fileBytes);
    		 	fos.close();
    }
		long endTime = System.nanoTime();
		long duration = (endTime - startTime) / 1000000 ;
		System.out.println("Time: " + duration + "ms.");   	
	}
	

}
