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
import java.util.concurrent.locks.ReentrantLock;

import com.apache.encryptor.SingletonLockManager;
import com.apache.exception.IllegalKeyException;

public class Key implements Serializable {
	String filename = "key.bin";
	@NonNull
	public Byte key;
	/**
	 * non-default constructor
	 * @param key
	 * @author Roi
	 */
	public Key(byte key) {
		this.key = key;
	}
	/**
	 * default constructor
	 * @author Roi
	 */
	public Key() {
		this.key = (byte)(new SecureRandom().nextInt(2*Byte.MAX_VALUE + 2) + Byte.MIN_VALUE); 
	}

	public void serialize() {
		// save the key to file
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(this.key);
			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void deserialize(String filename) throws IllegalKeyException{
		// read the key from file
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			this.key = (Byte) in.readObject();
			in.close();
			fis.close();
		} catch (Exception ex) {
			throw new IllegalKeyException("Illegal Key {deserialize failed}");
		}
	}


}
