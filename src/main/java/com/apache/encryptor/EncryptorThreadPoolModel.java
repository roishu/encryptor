package com.apache.encryptor;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JTextArea;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.apache.ciphers.BaseAlgorithm;
import com.apache.ciphers.ExtendedAlgorithm;
import com.apache.exception.IllegalKeyException;
import com.apache.jaxb.DircetoryDocumentJAXB;
import com.apache.jaxb.DoubleAlgorithmJAXB;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class EncryptorThreadPoolModel {
	private EncryptorManager encryptor;
	private ExecutorService executor;
	private File folder;
	private String algorithm;
	private String cipher1="";
	private String cipher2=""; //Extended Algorithm Issue
	private ArrayList<FileHolder> filesInFolder;
	private ArrayList<EncryptorThread> threadEncryptors;
	private int numOfFiles = 0;
	private long startTime=0 , endTime=0 , duration=0;
	//MVC
	private JTextArea console;
	//JAXB
	private DircetoryDocumentJAXB dirDocument;
	private JAXBContext jc;
	private Marshaller marshaller;
	private File xmlFile;

	public EncryptorThreadPoolModel(File folder, String algorithm) throws JAXBException {
		super();
		this.folder = folder;
		this.algorithm = algorithm;
		this.encryptor = new EncryptorManager();
		this.filesInFolder = new ArrayList<FileHolder>();
		this.threadEncryptors = new ArrayList<EncryptorThread>();
		this.executor = null;
		init();
	}
	
	@Inject
	public EncryptorThreadPoolModel(File folder,@Named("Algorithm") ExtendedAlgorithm algorithm,
			@Named("Cipher") BaseAlgorithm cipher1) throws JAXBException {
		this.folder = folder;
		this.cipher1 = cipher1.getName();
		this.algorithm = algorithm.getName();
		this.encryptor = new EncryptorManager();
		this.filesInFolder = new ArrayList<FileHolder>();
		this.threadEncryptors = new ArrayList<EncryptorThread>();
		this.executor = null;
		init();
	}
	
	public EncryptorThreadPoolModel(File folder,String algorithm,String cipher1) throws JAXBException {
		super();
		this.folder = folder;
		this.cipher1 = cipher1;
		this.algorithm = algorithm;
		this.encryptor = new EncryptorManager();
		this.filesInFolder = new ArrayList<FileHolder>();
		this.threadEncryptors = new ArrayList<EncryptorThread>();
		this.executor = null;
		init();
	}

	public EncryptorThreadPoolModel(File folder, String algorithm, String cipher1 , String cipher2) throws JAXBException {
		super();
		this.folder = folder;
		this.cipher1 = cipher1;
		this.cipher2 = cipher2;
		this.algorithm = algorithm;
		this.encryptor = new EncryptorManager();
		this.filesInFolder = new ArrayList<FileHolder>();
		this.threadEncryptors = new ArrayList<EncryptorThread>();
		this.executor = null;
		init();
	}

	public EncryptorThreadPoolModel(File folder, String algorithm, String cipher1 , String cipher2, JTextArea console) throws JAXBException {
		super();
		this.folder = folder;
		this.cipher1 = cipher1;
		this.cipher2 = cipher2;
		this.algorithm = algorithm;
		this.encryptor = new EncryptorManager();
		this.filesInFolder = new ArrayList<FileHolder>();
		this.threadEncryptors = new ArrayList<EncryptorThread>();
		this.executor = null;
		this.console = console;
		init();
	}


	private void init() {
		if(folder.isDirectory()){
			for (final File fileEntry : folder.listFiles()) 
				if(!fileEntry.isDirectory() && fileEntry.exists())
					filesInFolder.add(new FileHolder(fileEntry));
				dirDocument = new DircetoryDocumentJAXB(filesInFolder);
		}
		else{ //one file
			filesInFolder.add(new FileHolder(folder));
		}
		numOfFiles = filesInFolder.size();
	}
	
	public void setCipherKeysAndExecute(String cipher, String secondaryCipher, String keyFileName) throws IllegalKeyException, NoSuchAlgorithmException, JAXBException {
		File file = new File(keyFileName);
		if (file.length()>0){
			print("new key is signed!");
			encryptor.setCipherKeys(cipher, secondaryCipher, keyFileName);
			execute();
		}	
		else 
			printErrorMVC(new IllegalKeyException("Illegal Key File").getMessage());
	}

	public void execute() throws JAXBException, NoSuchAlgorithmException {
		startTime = System.nanoTime();
		executor = Executors.newFixedThreadPool(numOfFiles);
		print(algorithm+"'s Thread Created.");
		for(int i =0 ; i<numOfFiles ; i++){
			//check for algorithm type
			try {
				createThread(console==null , i);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				filesInFolder.get(i).setStatus(false);
				throw e;
			}
			executor.execute(threadEncryptors.get(i));
			/*
			 * execute: Use it for fire and forget calls
			 * submit: Use it to inspect the result of method call 
			 * take appropriate action on Future objected returned by the call
			 * we are using execute because we don't have to manage the thread after it ends.
			 */
		}
		executor.shutdown();
		time();
		exportXmlDirectoryStatus();
	}

	private void exportXmlDirectoryStatus() throws JAXBException {
		// TODO Auto-generated method stub
		jc = JAXBContext.newInstance(DircetoryDocumentJAXB.class);
        marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        xmlFile = new File("DircetoryDocumentJAXB.xml");
		//marshel into xmlFile
        marshaller.marshal(dirDocument, xmlFile);
        //doubleAlgorithmJAXB=null;
	}

	private void createThread(boolean b , int i) throws NoSuchAlgorithmException {

		if(b){ //system console
			if(cipher1.equals(""))
				threadEncryptors.add(new EncryptorThread(filesInFolder.get(i) , encryptor , algorithm));
			else if (algorithm.equals("DoubleAlgorithm"))
				threadEncryptors.add(new EncryptorThread(filesInFolder.get(i) , encryptor , algorithm , cipher1 , cipher2));
			else if (algorithm.equals("ReverseAlgorithm"))
				threadEncryptors.add(new EncryptorThread(filesInFolder.get(i) , encryptor , algorithm , cipher1));
			else if (algorithm.equals("SplitAlgorithm")) 
				threadEncryptors.add(new EncryptorThread(filesInFolder.get(i) , encryptor , algorithm , cipher1 , cipher2));
			else 
				throw new NoSuchAlgorithmException("NoSuchAlgorithmException: algorithm not found.");
		}
		else{ //app console (MVC)
			if(cipher1.equals(""))
				threadEncryptors.add(new EncryptorThread(filesInFolder.get(i) , encryptor , algorithm));
			else if (algorithm.equals("DoubleAlgorithm"))
				threadEncryptors.add(new EncryptorThread(filesInFolder.get(i) , encryptor , algorithm , cipher1 , cipher2,console));
			else if (algorithm.equals("ReverseAlgorithm"))
				threadEncryptors.add(new EncryptorThread(filesInFolder.get(i) , encryptor , algorithm , cipher1,"",console));
			else if (algorithm.equals("SplitAlgorithm")) 
				threadEncryptors.add(new EncryptorThread(filesInFolder.get(i) , encryptor , algorithm , cipher1 , cipher2,console));
			else 
				throw new NoSuchAlgorithmException("NoSuchAlgorithmException: algorithm not found.");
		}
	}

	private void time() {
		// TODO Auto-generated method stub
		while (!executor.isTerminated()) ;
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000 ;
		print("-----------Time 1: " + duration + "ms-----------"); 
	}

	public void print(String str){
		if(console!=null){
			console.setText(console.getText()+str+"\n");
			EncryptorController.logger.info("Thread Info : " + str); //to log
		}
			
		else
			System.out.println(str);
	}
	
	public void printErrorMVC(String str){
		EncryptorController.logger.error(str);
		console.setText(console.getText()+str+"\n");
	}

	public void exportKey() {
		encryptor.exportKey(cipher1);
		print("export key succeeded! {location:encryptor\\key.bin}");
	}

}
