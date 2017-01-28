package com.apache.encryptor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.mockito.Mockito.*; //check

import com.apache.ciphers.CaesarCipher;
import com.apache.ciphers.XORCipher;

/**
 * Roi Shukrun
 * T.Z : 313454464
 * M.I : 8079738
 *
 */
public class App 
{
	
	public static final String[] functions = { "Encryption", "Decryption"};
	

	
    public static void main( String[] args )  throws Exception
    {
		//gets a string to encrypt
//    	CaesarCipher cipher = new CaesarCipher("hi");
//    	XORCipher cipher2 = new XORCipher("hello");
//
//    	FileHolder mFileHolder = new FileHolder();
//    	mFileHolder.importFile();
//		String str = mFileHolder.getContent(); 
//
//		//(JOptionPane.showInputDialog("Input Data to encypt:"));
//		
//		//gets a key 
//		String key = (JOptionPane.showInputDialog("Input the key:"));
//		int keyLength=key.length(); //
//		
//		long startTime = System.nanoTime();
//
//		//prints encryption
//		String encrypted = cipher2.encrypt(str, keyLength);
//		System.out.println("Encrypted:" + encrypted);
//
//		//prints decryption
//		String decrypted = cipher2.decrypt(encrypted, keyLength);
//		System.out.println("Decrypted:" + decrypted);
//		
//		//prints key
//		System.out.println("Key:" + key);
//    	
//    	//String key = "squirrel123"; // needs to be at least 8 characters for DES
//
//		//FileInputStream fis = new FileInputStream(new File ("C:/Users/Roi/Documents/hello.txt"));
//		FileOutputStream fos = new FileOutputStream("C:/Users/Roi/Documents/encrypted2.txt");
//		byte data[] = encrypted.getBytes();
//		fos.write(data);
//		fos.close();
//
//		long endTime = System.nanoTime();
//		long duration = (endTime - startTime) / 1000000 ;
//		System.out.println("Time: " + duration + "ms.");
    	
    	XORCipher cipher = new XORCipher();
    	FileHolder mFileHolder = new FileHolder();
    	mFileHolder.importFile();
    	cipher.execute(mFileHolder, "enc");
    	FileHolder mFileHolderDec = new FileHolder();
    	mFileHolderDec.importFile("C:/Users/Roi/Documents/encrypted-algorithm.txt");
    	cipher.execute(mFileHolder, "dec");
    			
        
    }
}
