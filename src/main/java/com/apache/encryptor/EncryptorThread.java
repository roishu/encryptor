package com.apache.encryptor;

import java.io.IOException;

public class EncryptorThread implements Runnable {

	private Thread t;
	private FileHolder fileHolder;
	private EncryptorExecuter encryptor;
	private String algorithm;
	
	public EncryptorThread(FileHolder fileHolder, EncryptorExecuter encryptor, String algorithm) {
		super();
		this.fileHolder = fileHolder;
		this.encryptor = encryptor;
		this.algorithm = algorithm;
	}
	
	
	public void run() {
	      System.out.println("Encryption\\Decryption File: " +  fileHolder.getFileNameWithoutExtension());
	      try {
			encryptor.executeBaseAlgorithm(algorithm, fileHolder);
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
