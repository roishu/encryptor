package com.apache.encryptor;

import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JTextArea;
import javax.xml.bind.JAXBException;

public class EncryptorThread implements Runnable {

	private Thread t;
	private FileHolder fileHolder;
	private EncryptorManager encryptor;
	private String algorithm;
	private String cipher1="",cipher2="";
	private JTextArea console;
	//mutex
	private ReentrantLock lock = ThreadPoolEncryptor.lock;

	public EncryptorThread(FileHolder fileHolder, EncryptorManager encryptor, String algorithm) {
		super();
		this.fileHolder = fileHolder;
		this.encryptor = encryptor;
		this.algorithm = algorithm;
	}

	public EncryptorThread(FileHolder fileHolder, EncryptorManager encryptor, String algorithm , String cipher1 ) {
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

	public EncryptorThread(FileHolder fileHolder, EncryptorManager encryptor, String algorithm , String cipher1 , String cipher2 
			, JTextArea console) {
		super();
		this.fileHolder = fileHolder;
		this.encryptor = encryptor;
		this.algorithm = algorithm;
		this.cipher1 = cipher1;
		this.cipher2 = cipher2;
		this.console = console;
	}

	private void threadExecuteBaseAlgorithm() throws IOException, JAXBException {
		encryptor.executeBaseAlgorithm(algorithm, fileHolder);
	}

	private void threadExecuteExtendedAlgorithm() throws IOException, JAXBException {
		// TODO Auto-generated method stub
		if(algorithm.equals("ReverseAlgorithm"))
			encryptor.executeReverseAlgorithm(cipher1, fileHolder);

		else if(algorithm.equals("SplitAlgorithm"))
			encryptor.executeSplitAlgorithm(cipher1,cipher2, fileHolder);

		else if(algorithm.equals("DoubleAlgorithm"))
			encryptor.executeDoubleAlgorithm(cipher1,cipher2, fileHolder);
		else{
			//exception
		}
	}


	@Override
	public void run() {
		//lock.lock();
		print("Encryption\\Decryption File: " +  fileHolder.getFileNameWithoutExtension());
		try {
			if(cipher1.equals(""))
				threadExecuteBaseAlgorithm();
			else
				threadExecuteExtendedAlgorithm();
		} catch (IOException | JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		print("Finish Encryption\\Decryption File: " +  fileHolder.getFileNameWithoutExtension());
		//lock.unlock();
	}

	public void start () {
		if (t == null) {
			print("New Thread Created.");
			t = new Thread(this);
			t.start ();
		}
	}

	public void print(String str){
		if(console!=null){
			console.setText(console.getText()+str+"\n"); //to user
			EncryptorController.logger.info("Thread Info : " + str); //to log
		}
			
		else
			System.out.println(str);
	}


}
