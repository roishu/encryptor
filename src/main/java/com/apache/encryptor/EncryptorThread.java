package com.apache.encryptor;

import java.io.IOException;

public class EncryptorThread implements Runnable {

	private Thread t;
	private FileHolder fileHolder;
	private EncryptorManager encryptor;
	private String algorithm;
	private String cipher1="",cipher2="";
	
	public EncryptorThread(FileHolder fileHolder, EncryptorManager encryptor, String algorithm) {
		super();
		this.fileHolder = fileHolder;
		this.encryptor = encryptor;
		this.algorithm = algorithm;
	}
	
	public EncryptorThread(FileHolder fileHolder, EncryptorManager encryptor, String algorithm , String cipher1) {
		super();
		this.fileHolder = fileHolder;
		this.encryptor = encryptor;
		this.algorithm = algorithm;
		this.cipher1 = cipher1;
	}
	
	public EncryptorThread(FileHolder fileHolder, EncryptorManager encryptor, String algorithm , String cipher1 , String cipher2) {
		super();
		this.fileHolder = fileHolder;
		this.encryptor = encryptor;
		this.algorithm = algorithm;
		this.cipher1 = cipher1;
		this.cipher2 = cipher2;
	}
	
	
	@Override
	public void run() {
	      System.out.println("Encryption\\Decryption File: " +  fileHolder.getFileNameWithoutExtension());
	      try {
	    	  if(cipher1.equals(""))
	    		  encryptor.executeBaseAlgorithm(algorithm, fileHolder);
	    	  else if (algorithm.equals("DoubleCipher")) encryptor.executeDoubleAlgorithm(cipher1,cipher2, fileHolder);
	    		  else if (algorithm.equals("ReverseCipher")) encryptor.executeReverseAlgorithm(cipher1, fileHolder);
	    			  else if (algorithm.equals("SplitCipher")) encryptor.executeSplitAlgorithm(cipher1,cipher2, fileHolder);
	    			  else System.out.println("THROW EXCEPTION");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      System.out.println("Finish Encryption\\Decryption File: " +  fileHolder.getFileNameWithoutExtension());
	}
	
	   public void start () {
		      if (t == null) {
		    	  System.out.println("New Thread Created.");
		         t = new Thread(this);
		         t.start ();
		      }
		   }
	
	
}
