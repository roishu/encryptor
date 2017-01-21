package com.apache.encryptor;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FileHolder implements EncryptionManager {
	private File file;

	public FileHolder() {
		// TODO Auto-generated constructor stub
		
	}
	
	public boolean isValid(){
		return !(file.isDirectory() || !file.exists());
	}
	
	public void importFile(){
		JFrame frame = new JFrame("Path InputDialog Example");
        String path = JOptionPane.showInputDialog(frame, "Path :");
        file = new File(path);
        System.out.println(file.isDirectory());
        while (!isValid()){
        	path = JOptionPane.showInputDialog(frame, "Enter Valid Path :");
        	file = new File(path);
        }
	}

	public String encrypt() {
		// TODO Auto-generated method stub
		return null;
	}

}
