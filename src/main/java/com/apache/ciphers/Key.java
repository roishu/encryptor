package com.apache.ciphers;

import lombok.NonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.SecureRandom;

public class Key implements Serializable {
	String filename = "key.bin";
	@NonNull
    public final Byte key;
    public  Byte tempKey;
    /**
     * non-default constructor
     * @param key
     */
    public Key(byte key) {
        this.key = key;
    }
    /**
     * default constructor
     */
	public Key() {
        this.key = (byte)(new SecureRandom().nextInt(2*Byte.MAX_VALUE + 2) + Byte.MIN_VALUE); 
    }
    
    private void serialize(byte key) {
		// TODO Auto-generated method stub
    	// save the object to file
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(filename);
            out = new ObjectOutputStream(fos);
            out.writeObject(key);
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
    
    private void deserialize(){
    	 // read the object from file
        FileInputStream fis = null;
        ObjectInputStream in = null;
        tempKey = null;
        try {
            fis = new FileInputStream(filename);
            in = new ObjectInputStream(fis);
            tempKey = (Byte) in.readObject();
            in.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
