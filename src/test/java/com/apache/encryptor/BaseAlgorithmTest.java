package com.apache.encryptor;

import junit.framework.TestCase;
import static org.mockito.Mockito.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.apache.ciphers.BaseAlgorithm;
import com.apache.ciphers.CaesarCipher;
import com.apache.ciphers.MultiplicativeCipher;
import com.apache.ciphers.XORCipher;

public class BaseAlgorithmTest extends TestCase {
	private String text = "Example Test Content.";
	     
	    @Mock
	    File file;
	    @Mock
	    BaseAlgorithm algorithm;
	    @Mock
	    FileHolder fileHolder;
	 
	    @Before
	    public void setUp() throws IOException {
	    	file = new File("logs/fileTest.txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(text);
            out.close();
	    	fileHolder = new FileHolder();
		    fileHolder.importFile(file.getPath());
	    }
	 
	    @After
	    public void tearDown() {
	        fileHolder = null;
	        algorithm = null;
	        file.delete();   
	        System.gc();
	    }
	 
	    @Test
	    public void testCaesarCipher() throws IOException  {
	         
	    	algorithm = new CaesarCipher();
	    	executeAlgorithmForTest();

	    }
	    
	    @Test
	    public void testMultiplicativeCipher() throws IOException  {
	         
	    	algorithm = new MultiplicativeCipher();
	    	executeAlgorithmForTest();

	    }
	     
	    @Test
	    public void testXORCipher() throws IOException  {
	         
	    	algorithm = new XORCipher();
	    	executeAlgorithmForTest();

	    }
	    
	    public void executeAlgorithmForTest() throws IOException{
	    	algorithm.execute(fileHolder, "Encryption");
	    	final FileHolder mFileEncHolder = new FileHolder();
	    	mFileEncHolder.importFile(fileHolder.getDirectoryPath()+"\\encrypted-algorithm.txt");
	    	algorithm.execute(mFileEncHolder, "Decryption");
			String dec_content = new String(Files.readAllBytes
					(Paths.get(fileHolder.getDirectoryPath()+"\\decrypted-algorithm.txt")));
			assertEquals(text, dec_content);
			//delete Files
			mFileEncHolder.getFile().delete();
			Paths.get(fileHolder.getDirectoryPath()+"\\decrypted-algorithm.txt").toFile().delete();
	    }
	 
	
}
