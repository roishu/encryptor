package com.apache.encryptor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FileHolder {
	private File file;
	private CaesarCipher caesarChiper;
	private String filePath;
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

	public FileHolder() {
		// TODO Auto-generated constructor stub
		caesarChiper = new CaesarCipher();
	}
	
	public boolean isValid(){
		return !(file.isDirectory() || !file.exists());
	}
	
	public void importFile(){
		JFrame frame = new JFrame("Path InputDialog Example");
		filePath = JOptionPane.showInputDialog(frame, "Path :");
        file = new File(filePath);
        System.out.println(file.isDirectory());
        while (!isValid()){
        	filePath = JOptionPane.showInputDialog(frame, "Enter Valid Path :");
        	file = new File(filePath);
        }
        saveDirectoryPath();
	}

	private void saveDirectoryPath() {
		// TODO Auto-generated method stub
		directoryPath = file.getParent();
		System.out.println("Directory Path: " + directoryPath);
	}

	public void encrypt() {
		if (file!=null){
			CaesarCipher cipher = new CaesarCipher();
			try {
				String str = new String(Files.readAllBytes(Paths.get("C:/Users/Roi/Documents/hello.txt")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}

}
