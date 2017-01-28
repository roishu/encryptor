package com.apache.ciphers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;

import com.apache.encryptor.FileHolder;

public abstract class Algorithm {
	private Key key;
	private SecureRandom random;
	
	public Algorithm(){
		random = new SecureRandom();
		key = new Key(randomizeKeyByte());
	}

	private Byte randomizeKeyByte() {
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[16]; // 128 bits are converted to 16 bytes;
		random.nextBytes(bytes);
		return bytes[0];
	}
	
    protected abstract byte encryptByte(byte b, int idx, Key key)
            throws IOException;

    protected abstract byte decryptByte(byte b, int idx, Key key)
            throws IOException;
    
    public void execute(FileHolder fileHolder , String choice) throws IOException{
    	byte[] fileBytes = fileHolder.getData();
    	if (choice.equals("enc")){
    		long startTime = System.nanoTime();
            for (int i = 0; i < fileBytes.length; i++) 
            	fileBytes[i] = encryptByte(fileBytes[i], i, key);
            	FileOutputStream fos = new FileOutputStream(fileHolder.getDirectoryPath()+"\\encrypted-algorithm.txt");
            	fos.write(fileBytes);
            	fos.close();
    	}
    	else{
    		for (int i = 0; i < fileBytes.length; i++) 
            	fileBytes[i] = decryptByte(fileBytes[i], i, key);
    		 	FileOutputStream fos = new FileOutputStream(fileHolder.getDirectoryPath()+"\\decrypted-algorithm.txt");
    		 	fos.write(fileBytes);
    		 	fos.close();
    }
}//execute
    
}
