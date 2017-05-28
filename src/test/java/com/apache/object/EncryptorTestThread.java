package com.apache.object;

import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JTextArea;
import javax.xml.bind.JAXBException;

import com.apache.encryptor.EncryptorController;
import com.apache.encryptor.EncryptorManager;
import com.apache.encryptor.FileHolder;
import com.apache.encryptor.SingletonLockManager;
import com.apache.encryptor.EncryptorThreadPoolModel;
import com.apache.exception.NoSuchAlgorithmException;
import com.apache.exception.NoSuchCipherException;

public class EncryptorTestThread implements Runnable {

	private Thread t;
	private FileHolder fileHolder;
	private EncryptorManager encryptor;
	private String algorithm;
	private String cipher1="",cipher2="";
	private JTextArea console;
	private Exception ex=null;
	//mutex
	private ReentrantLock lock = SingletonLockManager.instance();

	public EncryptorTestThread(FileHolder fileHolder, EncryptorManager encryptor, String algorithm) {
		super();
		this.fileHolder = fileHolder;
		this.encryptor = encryptor;
		this.algorithm = algorithm;
	}

	public EncryptorTestThread(FileHolder fileHolder, EncryptorManager encryptor, String algorithm , String cipher1 ) {
		super();
		this.fileHolder = fileHolder;
		this.encryptor = encryptor;
		this.algorithm = algorithm;
		this.cipher1 = cipher1;
	}

	public EncryptorTestThread(FileHolder fileHolder, EncryptorManager encryptor, String algorithm , String cipher1 , String cipher2) {
		super();
		this.fileHolder = fileHolder;
		this.encryptor = encryptor;
		this.algorithm = algorithm;
		this.cipher1 = cipher1;
		this.cipher2 = cipher2;
	}

	public EncryptorTestThread(FileHolder fileHolder, EncryptorManager encryptor, String algorithm , String cipher1 , String cipher2 
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
		/*
		 * unnecessary for test 
		 */
	}

	private void threadExecuteExtendedAlgorithm() throws IOException, JAXBException, NoSuchCipherException {
		if("CaesarCipherXORCipherMultiplicativeCipher".contains(cipher1) 
				&& "CaesarCipherXORCipherMultiplicativeCipher".contains(cipher2))
		{
			/*
			 * unneccesary for test
			 */
		}
		else
			ex = new NoSuchCipherException("one or more {"+cipher1+","+cipher2+"} ciphers not found.");
	}


	/**
	 * @author Roi
	 * to run thread async lock and unlock by the mutex
	 */
	@Override
	public void run() {
		//lock.lock();
		try {
			if(cipher1.equals("") && "CaesarCipherXORCipherMultiplicativeCipher".contains(algorithm))
				threadExecuteBaseAlgorithm();
			else if ("DoubleAlgorithmReverseAlgorithmSplitAlgorithm".contains(algorithm))
				threadExecuteExtendedAlgorithm();
			else{
				ex = new NoSuchAlgorithmException("Algorithm "+algorithm+" not found.");
			}
		} catch (IOException | JAXBException | NoSuchCipherException e) {
			e.printStackTrace();
		}
		//lock.unlock();
	}

	public void start () {
		if (t == null) {
			t = new Thread(this);
			t.start ();
		}
	}
	
    public void checkForExceptionForTest() throws Exception {
        if (ex!= null) {
            throw ex;
        }
    }


}

