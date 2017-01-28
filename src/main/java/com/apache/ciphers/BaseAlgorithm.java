package com.apache.ciphers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;

import com.apache.encryptor.FileHolder;

public abstract class BaseAlgorithm {
	protected Key key;
	protected SecureRandom random;
	
	public BaseAlgorithm(){
		random = new SecureRandom();
		key = new Key(randomizeKeyByte());
	}

	protected Byte randomizeKeyByte() {
		//random from -128 to 127
		byte key = (byte)(random.nextInt(2*Byte.MAX_VALUE + 2) + Byte.MIN_VALUE); 
		System.out.println("Key =" + key);
        return key;
	}
	
    protected abstract byte encryptByte(byte b, Key key)
            throws IOException;

    protected abstract byte decryptByte(byte b, Key key)
            throws IOException;
    
    public void execute(FileHolder fileHolder , String choice) throws IOException{
    	long startTime = System.nanoTime();
    	byte[] fileBytes = fileHolder.getData();
    	if (choice.equals("enc")){
            for (int i = 0; i < fileBytes.length; i++) 
            	fileBytes[i] = encryptByte(fileBytes[i], key);
            	FileOutputStream fos = new FileOutputStream(fileHolder.getDirectoryPath()+"\\encrypted-algorithm.txt");
            	fos.write(fileBytes);
            	fos.close();
    	}
    	else if (choice.equals("dec")){
    		for (int i = 0; i < fileBytes.length; i++) 
            	fileBytes[i] = decryptByte(fileBytes[i], key);
    		 	FileOutputStream fos = new FileOutputStream(fileHolder.getDirectoryPath()+"\\decrypted-algorithm.txt");
    		 	fos.write(fileBytes);
    		 	fos.close();
    }
		long endTime = System.nanoTime();
		long duration = (endTime - startTime) / 1000000 ;
		System.out.println("Time: " + duration + "ms.");   	
}//execute
    
}
