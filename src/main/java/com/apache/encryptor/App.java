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
    	CaesarCipher cipher = new CaesarCipher();
    	FileHolder mFileHolder = new FileHolder();
    	mFileHolder.importFile();
    	
		String str = new String(Files.readAllBytes(Paths.get(mFileHolder.getFilePath()))); 
		str += "ROI IS THE KING";
		//(JOptionPane.showInputDialog("Input Data to encypt:"));
		
		//gets a key 
		String key = (JOptionPane.showInputDialog("Input the key:"));
		int keyLength=key.length(); //
		
		
	
		
		//prints encryption
		String encrypted = cipher.encrypt(str, keyLength);
		System.out.println("Encrypted:" + encrypted);

		//prints decryption
		String decrypted = cipher.decrypt(encrypted, keyLength);
		System.out.println("Decrypted:" + decrypted);
		
		//prints key
		System.out.println("Key:" + key);
    	
    	//String key = "squirrel123"; // needs to be at least 8 characters for DES

		//FileInputStream fis = new FileInputStream(new File ("C:/Users/Roi/Documents/hello.txt"));
		FileOutputStream fos = new FileOutputStream("C:/Users/Roi/Documents/encrypted2.txt");
		byte data[] = encrypted.getBytes();
		fos.write(data);
		fos.close();

    			
    			
        
    }
}
