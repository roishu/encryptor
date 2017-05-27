package com.apache.encryptor;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JTextArea;
import javax.xml.bind.JAXBException;

public class ThreadPoolEncryptor {

	private EncryptorManager encryptor;
	private ExecutorService executor;
	private File folder;
	private String algorithm;
	private String cipher1="",cipher2=""; //Extended Algorithm Issue
	private ArrayList<FileHolder> filesInFolder;
	private ArrayList<EncryptorThread> threadEncryptors;
	private int numOfFiles = 0;
	private long startTime=0 , endTime=0 , duration=0;
	//MVC
	private JTextArea console;

	public ThreadPoolEncryptor(File folder, String algorithm) throws JAXBException {
		super();
		this.folder = folder;
		this.algorithm = algorithm;
		this.encryptor = new EncryptorManager();
		this.filesInFolder = new ArrayList<FileHolder>();
		this.threadEncryptors = new ArrayList<EncryptorThread>();
		this.executor = null;
		init();
	}

	public ThreadPoolEncryptor(File folder, String algorithm, String cipher1) throws JAXBException {
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

	public ThreadPoolEncryptor(File folder, String algorithm, String cipher1 , String cipher2) throws JAXBException {
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
	
	public ThreadPoolEncryptor(File folder, String algorithm, String cipher1 , String cipher2, JTextArea console) throws JAXBException {
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
		}
		else{ //one file
			filesInFolder.add(new FileHolder(folder));
		}
		numOfFiles = filesInFolder.size();
	}

	public void execute(boolean runFromXML){
		startTime = System.nanoTime();
		executor = Executors.newFixedThreadPool(numOfFiles);
		for(int i =0 ; i<numOfFiles ; i++){
			//check for algorithm type
			createThread(console==null , i , runFromXML);
			executor.execute(threadEncryptors.get(i));
		}
		executor.shutdown();
		time();
	}//execute

	private void createThread(boolean b , int i , boolean runFromXML) {
		// TODO Auto-generated method stub
		if(b){ //system console
			if(cipher1.equals(""))
				threadEncryptors.add(new EncryptorThread(filesInFolder.get(i) , encryptor , algorithm,runFromXML));
			else if (algorithm.equals("DoubleAlgorithm"))
				threadEncryptors.add(new EncryptorThread(filesInFolder.get(i) , encryptor , algorithm , cipher1 , cipher2,runFromXML));
			else if (algorithm.equals("ReverseAlgorithm"))
				threadEncryptors.add(new EncryptorThread(filesInFolder.get(i) , encryptor , algorithm , cipher1,runFromXML));
			else if (algorithm.equals("SplitAlgorithm")) 
				threadEncryptors.add(new EncryptorThread(filesInFolder.get(i) , encryptor , algorithm , cipher1 , cipher2,runFromXML));
			else {
				System.out.println("THROW EXCEPTION"); //TODO exception !
			}
		}
		else{ //app console
			if(cipher1.equals(""))
				threadEncryptors.add(new EncryptorThread(filesInFolder.get(i) , encryptor , algorithm,runFromXML));
			else if (algorithm.equals("DoubleAlgorithm"))
				threadEncryptors.add(new EncryptorThread(filesInFolder.get(i) , encryptor , algorithm , cipher1 , cipher2,runFromXML,console));
			else if (algorithm.equals("ReverseAlgorithm"))
				threadEncryptors.add(new EncryptorThread(filesInFolder.get(i) , encryptor , algorithm , cipher1,"",runFromXML,console));
			else if (algorithm.equals("SplitAlgorithm")) 
				threadEncryptors.add(new EncryptorThread(filesInFolder.get(i) , encryptor , algorithm , cipher1 , cipher2,runFromXML,console));
			else {
				System.out.println("THROW EXCEPTION"); //TODO exception !
			}
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
		if(console!=null)
			console.setText(console.getText()+str+"\n");
		else
			System.out.println(str);
	}

}
