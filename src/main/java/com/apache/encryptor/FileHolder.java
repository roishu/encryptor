package com.apache.encryptor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.apache.ciphers.CaesarCipher;

public class FileHolder {
	private boolean isEmpty = true;
	private File file;
	private CaesarCipher caesarChiper;
	private String filePath;
	private String content;
	
	public FileHolder() {
		// TODO Auto-generated constructor stub
		caesarChiper = new CaesarCipher();
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	private String directoryPath;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String path) {
		this.filePath = path;
	}


	
	public boolean isValid(){
		return !(file.isDirectory() || !file.exists());
	}
	
	public void importFile(){
		if(isEmpty){
			JFrame frame = new JFrame("Path InputDialog Example");
			filePath = JOptionPane.showInputDialog(frame, "Path :");
	        file = new File(filePath);
	        System.out.println("file.isDirectory()" + file.isDirectory());
	        while (!isValid()){
	        	filePath = JOptionPane.showInputDialog(frame, "Enter Valid Path :");
	        	file = new File(filePath);
	        }
	        saveDirectoryPath();
	        try {
				content = new String(Files.readAllBytes(Paths.get(filePath)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	        
	        isEmpty = false;
		}
	}

	private void saveDirectoryPath() {
		// TODO Auto-generated method stub
		directoryPath = file.getParent();
		System.out.println("Directory Path: " + directoryPath);
	}

	public void encrypt() {
		if (file!=null){
			long startTime = System.nanoTime();
			CaesarCipher cipher = new CaesarCipher();
			try {
				String str = new String(Files.readAllBytes(Paths.get("C:/Users/Roi/Documents/hello.txt")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			long endTime = System.nanoTime();
			long duration = (endTime - startTime);
			System.out.println(duration);
		}//end of if
	}

}
