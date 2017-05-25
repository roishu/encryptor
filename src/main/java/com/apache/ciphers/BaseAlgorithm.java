package com.apache.ciphers;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;

import com.apache.encryptor.FileHolder;

public abstract class BaseAlgorithm extends Algorithm {
	protected Key key;
	protected SecureRandom random;
	
	public BaseAlgorithm(String name){
		super(name);
		random = new SecureRandom();
		key = new Key(randomizeKeyByte());
	}

	protected Byte randomizeKeyByte() {
		//random from -128 to 127
		byte key = (byte)(random.nextInt(2*Byte.MAX_VALUE + 2) + Byte.MIN_VALUE); 
		System.out.println("Key =" + key);
        return key;
	}
	
    public abstract byte encryptByte(byte b, Key key)
            throws IOException;

    public abstract byte decryptByte(byte b, Key key)
            throws IOException;
    
    private void encryption(byte[] fileBytes , FileHolder fileHolder) throws IOException{
    	 for (int i = 0; i < fileBytes.length; i++) 
         	fileBytes[i] = encryptByte(fileBytes[i], key);
         	FileOutputStream fos = new FileOutputStream(fileHolder.getEncryptedResultPath());
         	fos.write(fileBytes);
         	fos.close();
    }
    
    private void decryption(byte[] fileBytes , FileHolder fileHolder) throws IOException{
    	for (int i = 0; i < fileBytes.length; i++) 
        	fileBytes[i] = decryptByte(fileBytes[i], key);
		 	FileOutputStream fos = new FileOutputStream(fileHolder.getDecryptedResultPath());
		 	fos.write(fileBytes);
		 	fos.close();
    }
    
    public void execute(FileHolder fileHolder , String choice) throws IOException{
    	byte[] fileBytes = fileHolder.getData();
    	if (choice.equals("Encryption"))
    		encryption(fileBytes ,fileHolder);
    	else
    		decryption(fileBytes ,fileHolder);
    }
    
}
