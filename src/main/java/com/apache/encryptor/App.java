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
import com.apache.ciphers.MultiplicativeCipher;
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
    	//path : C:\Users\Roi\Desktop\desktop-file.txt
    	MultiplicativeCipher cipher = new MultiplicativeCipher();
    	FileHolder mFileHolder = new FileHolder();
    	mFileHolder.importFile();
    	cipher.execute(mFileHolder, "enc");
    	FileHolder mFileHolderDec = new FileHolder();
    	mFileHolderDec.importFile(mFileHolder.getDirectoryPath()+"\\encrypted-algorithm.txt");
    	cipher.execute(mFileHolder, "dec");
    			
        
    }
}
