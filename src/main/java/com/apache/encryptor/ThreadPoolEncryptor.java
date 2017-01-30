package com.apache.encryptor;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolEncryptor {
	
	private EncryptorExecuter encryptor;
	private ExecutorService executor;
	private File folder;
	private String algorithm;
	private ArrayList<FileHolder> filesInFolder;
	private ArrayList<EncryptorThread> threadEncryptors;
	private int numOfFiles = 0;
	private long startTime=0 , endTime=0 , duration=0;
	
	public ThreadPoolEncryptor(File folder, String algorithm) {
		super();
		this.folder = folder;
		this.algorithm = algorithm;
		this.encryptor = new EncryptorExecuter();
		this.filesInFolder = new ArrayList<FileHolder>();
		this.threadEncryptors = new ArrayList<EncryptorThread>();
		this.executor = null;
		init();
	}
	

	private void init() {
		for (final File fileEntry : folder.listFiles()) 
	    	if(!fileEntry.isDirectory() && fileEntry.exists())
	    		filesInFolder.add(new FileHolder(fileEntry));
		numOfFiles = filesInFolder.size();
	}
	
	public void execute(){
		startTime = System.nanoTime();
		executor = Executors.newFixedThreadPool(filesInFolder.size());
		for(int i =0 ; i<numOfFiles ; i++){
			threadEncryptors.add(new EncryptorThread(filesInFolder.get(i) , encryptor , algorithm));
			executor.execute(threadEncryptors.get(i));
		}
	    executor.shutdown();
	    while (!executor.isTerminated()) ;
	     endTime = System.nanoTime();
		 duration = (endTime - startTime) / 1000000 ;
		System.out.println("-----------Time 1: " + duration + "ms-----------"); 
	}//execute
	
}
