package com.apache.encryptor;

import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JTextArea;
import javax.xml.bind.JAXBException;

import com.apache.exception.NoSuchAlgorithmException;
import com.apache.exception.NoSuchCipherException;
import com.apache.exception.NoSuchFunctionException;

public class EncryptorThread implements Runnable {

	private Thread t;
	private FileHolder fileHolder;
	private EncryptorManager encryptor;
	private String algorithm;
	private String cipher1="",cipher2="";
	private JTextArea console;
	//mutex - singleton
	private ReentrantLock lock = SingletonLockManager.instance();

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

	private void threadExecuteBaseAlgorithm() throws IOException, JAXBException, NoSuchFunctionException {
		encryptor.executeBaseAlgorithm(algorithm, fileHolder);
	}

	private void threadExecuteExtendedAlgorithm() throws IOException, JAXBException, NoSuchCipherException, NoSuchFunctionException {
		if("CaesarCipherXORCipherMultiplicativeCipher".contains(cipher1) 
				&& "CaesarCipherXORCipherMultiplicativeCipher".contains(cipher2))
		{
			if(algorithm.equals("ReverseAlgorithm"))
				encryptor.executeReverseAlgorithm(cipher1, fileHolder);

			else if(algorithm.equals("SplitAlgorithm"))
				encryptor.executeSplitAlgorithm(cipher1,cipher2, fileHolder);

			else if(algorithm.equals("DoubleAlgorithm"))
				encryptor.executeDoubleAlgorithm(cipher1,cipher2, fileHolder);
		}
		else
			throw new NoSuchCipherException("one or more {"+cipher1+","+cipher2+"} ciphers not found.");
	}


	/**
	 * @author Roi
	 * to run thread async lock and unlock by the mutex
	 */
	@Override
	public void run() {
		//lock.lock();
		print("Encryption\\Decryption File: " +  fileHolder.getFileNameWithoutExtension());
		try {
			if(cipher1.equals("") && "CaesarCipherXORCipherMultiplicativeCipher".contains(algorithm))
				threadExecuteBaseAlgorithm();
			else if ("DoubleAlgorithmReverseAlgorithmSplitAlgorithm".contains(algorithm))
				threadExecuteExtendedAlgorithm();
			else{
				throw new NoSuchAlgorithmException("Algorithm "+algorithm+" not found.");
			}
		} catch (IOException | JAXBException | NoSuchAlgorithmException | NoSuchCipherException | NoSuchFunctionException e) {
			e.printStackTrace();
			if(console!=null)
				printErrorMVC(e.getMessage());
		}
		print("Finish Encryption\\Decryption File: " +  fileHolder.getFileNameWithoutExtension());
		//lock.unlock();
	}

	public void start () {
		if (t == null) {
			print(algorithm+"'s Thread Created.");
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
	
	public void printErrorMVC(String str){
		EncryptorController.logger.error(str);
		console.setText(console.getText()+str+"\n");
	}


}
