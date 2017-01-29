package com.apache.ciphers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.apache.encryptor.FileHolder;

public class ReverseCipher extends ExtendedAlgorithm{

	public ReverseCipher(BaseAlgorithm baseAlgorithm) {
		super(baseAlgorithm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void encrypt(FileHolder fileHolder) throws IOException {
		baseAlgorithm.execute(fileHolder, "Decryption");
		
	}

	@Override
	public void decrypt(FileHolder fileHolder) throws IOException {
		// TODO Auto-generated method stub
		baseAlgorithm.execute(fileHolder, "Encryption");
		
	}
	
	public void swapFiles(FileHolder fileHolder) throws IOException{
		String e_content = new String(Files.readAllBytes(Paths.get(fileHolder.getDirectoryPath()+"\\decrypted-algorithm.txt")));
		String d_content = new String(Files.readAllBytes(Paths.get(fileHolder.getDirectoryPath()+"\\encrypted-algorithm.txt")));

		FileOutputStream fos_e = new FileOutputStream(fileHolder.getDirectoryPath()+"\\encrypted-algorithm.txt");
		FileOutputStream fos_d = new FileOutputStream(fileHolder.getDirectoryPath()+"\\decrypted-algorithm.txt");
		
		
		fos_d.write(d_content.getBytes());
		fos_e.write(e_content.getBytes());
		
		fos_d.close();
		fos_e.close();

	}
	
	

}
