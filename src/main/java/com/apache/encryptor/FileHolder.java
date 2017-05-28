package com.apache.encryptor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.apache.ciphers.CaesarCipher;

@XmlRootElement (name = "file")
@XmlAccessorType (XmlAccessType.FIELD)
public class FileHolder {
	@XmlTransient
	private boolean isEmpty = true;
	@XmlTransient
	private File file;
	@XmlTransient
	private CaesarCipher caesarChiper;
	@XmlTransient
	private String filePath;
	@XmlTransient
	private String content;
	@XmlTransient
	private byte[] data;
	@XmlTransient
	private String directoryPath;
	private String extension;
	@XmlElement
	private String status = "Encryption\\Decryption: true";
	@XmlElement
	private String name;
	
	public String getDirectoryPath() {
		return directoryPath;
	}

	public void setDirectoryPath(String directoryPath) {
		this.directoryPath = directoryPath;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(boolean b) {
		this.status = "Encryption\\Decryption: "+b;
	}

	public FileHolder() {
		// TODO Auto-generated constructor stub
		//caesarChiper = new CaesarCipher("ken");
	}
	
	public FileHolder(File file) {
		// TODO Auto-generated constructor stub
		importFile(file.getPath());
		name = file.getName();
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	

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
	
//	public void importFile(){
//		if(isEmpty){
//			JFrame frame = new JFrame("Path InputDialog Example");
//			filePath = JOptionPane.showInputDialog(frame, "Path :");
//	        file = new File(filePath);
//	        //System.out.println("file.isDirectory()" + file.isDirectory());
//	        while (!isValid()){
//	        	filePath = JOptionPane.showInputDialog(frame, "Enter Valid Path :");
//	        	file = new File(filePath);
//	        }
//	        saveDirectoryPath();
//	        try {
//				content = new String(Files.readAllBytes(Paths.get(filePath)));
//				data = Files.readAllBytes(Paths.get(filePath));
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} 
//	        
//	        isEmpty = false;
//		}
//	}
	
	public void importFile(String filePath){
		if(isEmpty){
	        file = new File(filePath);
	        if(!file.isDirectory())
	        	saveExtension();
	        saveDirectoryPath();
	        try {
				content = new String(Files.readAllBytes(Paths.get(filePath)));
				data = Files.readAllBytes(Paths.get(filePath));
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
	}
	
	public void saveExtension(){
		//extension
		int index = file.getName().indexOf(".");
		extension =  file.getName().substring(index);
	}
	
	public String getFileNameWithoutExtension(){
		String fileName = file.getName();
		int pos = fileName.lastIndexOf(".");
		if (pos > 0) {
		    fileName = fileName.substring(0, pos);
		}
		return fileName;
	}
	
	public String getEncryptedResultPath(){
		return getDirectoryPath()+"\\"+getFileNameWithoutExtension()+".encrypted";
	}
	
	public String getEncryptedResultPath(String path){
		return path+"\\"+getFileNameWithoutExtension()+".encrypted";
	}
	
	public String getDecryptedResultPath(){
		return getDirectoryPath()+"\\"+getFileNameWithoutExtension()+"-decrypted.txt";
	}
	
	public String getDecryptedResultPath(String path){
		return path+"\\"+getFileNameWithoutExtension()+"-decrypted.txt";
	}
	
	public String expectedExtension(){
        return extension;
	}


	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}


	
}//END
