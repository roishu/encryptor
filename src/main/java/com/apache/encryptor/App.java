package com.apache.encryptor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.mockito.Mockito.*; //check

import com.apache.ciphers.BaseAlgorithm;
import com.apache.ciphers.CaesarCipher;
import com.apache.ciphers.DoubleCipher;
import com.apache.ciphers.MultiplicativeCipher;
import com.apache.ciphers.ReverseCipher;
import com.apache.ciphers.SplitCipher;
import com.apache.ciphers.XORCipher;
import com.apache.gui.EncryptorMenu;

/**
 * Roi Shukrun
 * T.Z : 313454464
 * M.I : 8079738
 *
 */
public class App 
{
	
	//public static final String[] functions = { "Encryption", "Decryption"};

	
    public static void main( String[] args )  throws Exception
    {
    	//    	Path desktopPath = Paths.get("C:\\Users\\Roi\\Desktop\\files-desktop\\result1");
    	
    	EncryptorExecuter encryptor = new EncryptorExecuter();
       	
    	final File folder = new File("C:\\Users\\Roi\\Desktop\\files-desktop\\result1");
    	ArrayList<FileHolder> filesInFolder = new ArrayList<FileHolder>();
    	ThreadPoolEncryptor tpEncryptor = new ThreadPoolEncryptor(folder, "MultiplicativeCipher");
    	//tpEncryptor.execute();
    	
		//encryptor.deleteEncryptorFiles(fileHolder);
    
		long startTime = System.nanoTime();
		int i = 0;
    	    for (final File fileEntry : folder.listFiles()) {
    	    	if(!fileEntry.isDirectory() && fileEntry.exists()){
    	    		filesInFolder.add(i,new FileHolder(fileEntry));
    	    		encryptor.executeBaseAlgorithm("MultiplicativeCipher",filesInFolder.get(i));
    	    		i++;
    	    	}
    	    }//for
    	    long endTime = System.nanoTime();
    	    long duration = (endTime - startTime) / 1000000 ;
    		System.out.println("-----------Time 2: " + duration + "ms-----------"); 
        
    }   
    
}
