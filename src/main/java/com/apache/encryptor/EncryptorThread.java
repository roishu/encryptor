package com.apache.encryptor;

import java.io.IOException;

import javax.swing.JTextArea;
import javax.xml.bind.JAXBException;

public class EncryptorThread implements Runnable {

	private Thread t;
	private FileHolder fileHolder;
	private EncryptorManager encryptor;
	private String algorithm;
	private String cipher1="",cipher2="";
	private boolean runFromXML = false;
	private JTextArea console;

	public EncryptorThread(FileHolder fileHolder, EncryptorManager encryptor, String algorithm , boolean runFromXml) {
		super();
		this.fileHolder = fileHolder;
		this.encryptor = encryptor;
		this.algorithm = algorithm;
		this.runFromXML = runFromXml;
	}

	public EncryptorThread(FileHolder fileHolder, EncryptorManager encryptor, String algorithm , String cipher1 , boolean runFromXml) {
		super();
		this.fileHolder = fileHolder;
		this.encryptor = encryptor;
		this.algorithm = algorithm;
		this.cipher1 = cipher1;
		this.runFromXML = runFromXml;
	}

	public EncryptorThread(FileHolder fileHolder, EncryptorManager encryptor, String algorithm , String cipher1 , String cipher2 , boolean runFromXml) {
		super();
		this.fileHolder = fileHolder;
		this.encryptor = encryptor;
		this.algorithm = algorithm;
		this.cipher1 = cipher1;
		this.cipher2 = cipher2;
		this.runFromXML = runFromXml;
	}
	
	public EncryptorThread(FileHolder fileHolder, EncryptorManager encryptor, String algorithm , String cipher1 , String cipher2 , 
			boolean runFromXml, JTextArea console) {
		super();
		this.fileHolder = fileHolder;
		this.encryptor = encryptor;
		this.algorithm = algorithm;
		this.cipher1 = cipher1;
		this.cipher2 = cipher2;
		this.runFromXML = runFromXml;
		this.console = console;
	}
	
	private void threadExecuteBaseAlgorithm() throws IOException, JAXBException {
		if(!runFromXML)
			encryptor.executeBaseAlgorithm(algorithm, fileHolder);
		else
			encryptor.executeBaseAlgorithmFromXML(algorithm, fileHolder);
	}

	private void threadExecuteExtendedAlgorithm() throws IOException, JAXBException {
		// TODO Auto-generated method stub
		if(algorithm.equals("ReverseAlgorithm")){
			if(!runFromXML)
				encryptor.executeReverseAlgorithm(cipher1, fileHolder);
			else
				encryptor.executeReverseAlgorithmFromXML(cipher1, fileHolder);
		}
		else if(algorithm.equals("SplitAlgorithm")){
			if(!runFromXML)
				encryptor.executeSplitAlgorithm(cipher1,cipher2, fileHolder);
			else
				encryptor.executeSplitAlgorithmFromXML(cipher1 , cipher2, fileHolder);
		}
		else if(algorithm.equals("DoubleAlgorithm")){
			if(!runFromXML)
				encryptor.executeDoubleAlgorithm(cipher1,cipher2, fileHolder);
			else
				encryptor.executeDoubleAlgorithmFromXML(cipher1,cipher2, fileHolder);
		}
		else{
			//exception
		}
	}
	
	@Override
	public void run() {
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
	}

	public void start () {
		if (t == null) {
			print("New Thread Created.");
			t = new Thread(this);
			t.start ();
		}
	}
	
	public void print(String str){
		if(console!=null)
			console.setText(console.getText()+str+"\n");
		else
			System.out.println(str);
	}


}
