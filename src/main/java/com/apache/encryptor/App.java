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
	public static final BaseAlgorithm[] algorithms = 
		{new CaesarCipher() , new MultiplicativeCipher() , new XORCipher()};
	
    public static void main( String[] args )  throws Exception
    {
    	
    	//my path : C:\Users\Roi\Desktop\desktop-file.txt
    	//directoryPath : C:\Users\Roi\Desktop\files-desktop
    	
    	//public void listFilesForFolder(final File folder) {
    	Path desktopPath = Paths.get("C:\\Users\\Roi\\Desktop\\files-desktop\\result1");
    	final File folder = new File("C:\\Users\\Roi\\Desktop\\files-desktop");
    	ArrayList<FileHolder> filesInFolder = new ArrayList<FileHolder>();
    	BaseAlgorithm algorithm = new CaesarCipher();
    	int i = 0;
    
    	    for (final File fileEntry : folder.listFiles()) {
    	    	if(!fileEntry.isDirectory() && fileEntry.exists()){
    	    		filesInFolder.add(i,new FileHolder(fileEntry));
    	    		executeAlgorithm(algorithm,filesInFolder.get(i));
    	    		System.out.println(filesInFolder.get(i).getFileNameWithoutExtension());
    	    		i++;
    	    	}
    	    }
    	    	
    	    
    	   
    	            
    	    

    	    Files.createDirectories(desktopPath);

    	    
    	//}

    	
    			

        
        
        
        
    } 
    
    
    public static void executeAlgorithm
    (BaseAlgorithm algorithm , FileHolder fileHolder) throws IOException{
    	 algorithm.execute(fileHolder, "Encryption");
	    	final FileHolder mFileEncHolder = new FileHolder();
	    	mFileEncHolder.importFile(fileHolder.getDirectoryPath()+"\\encrypted-algorithm.txt");
	    	algorithm.execute(mFileEncHolder, "Decryption");
			String dec_content = new String(Files.readAllBytes
					(Paths.get(fileHolder.getDirectoryPath()+"\\decrypted-algorithm.txt")));

    }
    
    
}
