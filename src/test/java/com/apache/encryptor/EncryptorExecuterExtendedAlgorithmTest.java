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

public class EncryptorExecuterExtendedAlgorithmTest extends TestCase {
	private String text = "Example Test Content.";
	     
	    @Mock
	    File file;
	    @Mock
	    EncryptorExecuter encryptor;
	    @Mock
	    FileHolder fileHolder;
	 
	    @Before
	    public void setUp() throws IOException {
	    	encryptor = new EncryptorExecuter();
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
	        encryptor = null;
	        file.delete();   
	        System.gc();
	    }
	 
	    @Test
	    public void testDoubleCipher_Caesar_XOR() throws IOException  {
	    	encryptor.executeDoubleAlgorithm("CaesarCipher", "XORCipher", fileHolder);
	    	finish();
	    }
	    
	    @Test
	    public void testDoubleCipher_XOR_Multi() throws IOException  {
	    	encryptor.executeDoubleAlgorithm("XORCipher", "MultiplicativeCipher", fileHolder);
	    	finish();
	    }
	    
	    @Test
	    public void testDoubleCipher_Multi_Caesar() throws IOException  {
	    	encryptor.executeDoubleAlgorithm("MultiplicativeCipher", "CaesarCipher", fileHolder);
	    	finish();
	    }
	    
	    @Test
	    public void testReverseCipher_Caesar() throws IOException  {
	    	encryptor.executeReverseAlgorithm("CaesarCipher", fileHolder);
	    	finish();
	    }
	    
	    @Test
	    public void testReverseCipher_XOR() throws IOException  {
	    	encryptor.executeReverseAlgorithm("XORCipher", fileHolder);
	    	finish();
	    }
	    
	    @Test
	    public void testReverseCipher_Multi() throws IOException  {
	    	encryptor.executeReverseAlgorithm("MultiplicativeCipher", fileHolder);
	    	finish();
	    }
	    
	    @Test
	    public void testSplitCipher_Caesar_XOR() throws IOException  {
	    	encryptor.executeSplitAlgorithm("CaesarCipher", "XORCipher", fileHolder);
	    	finish();
	    }
	    
	    @Test
	    public void testSplitCipher_XOR_Multi() throws IOException  {
	    	encryptor.executeSplitAlgorithm("XORCipher", "MultiplicativeCipher", fileHolder);
	    	finish();
	    }
	    
	    @Test
	    public void testSplitCipher_Multi_Caesar() throws IOException  {
	    	encryptor.executeSplitAlgorithm("MultiplicativeCipher", "CaesarCipher", fileHolder);
	    	finish();
	    }
	    
	    public void finish() throws IOException {
	    	String dec_content = new String(Files.readAllBytes
					(Paths.get(fileHolder.getDecryptedResultPath())));
			assertEquals(text, dec_content);
			encryptor.deleteEncryptorFiles(fileHolder);
		}
	 
	
}
