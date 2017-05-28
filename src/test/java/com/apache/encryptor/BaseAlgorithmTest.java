package com.apache.encryptor;

import junit.framework.TestCase;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import com.apache.ciphers.BaseAlgorithm;
import com.apache.ciphers.CaesarCipher;
import com.apache.ciphers.MultiplicativeCipher;
import com.apache.ciphers.XORCipher;
import com.apache.exception.NoSuchFunctionException;

public class BaseAlgorithmTest extends TestCase {
	private String text = "Example Test Content.";
	     
	    @Mock
	    File file;
	    @Mock
	    BaseAlgorithm algorithm;
	    @Mock
	    FileHolder fileHolder;
	 
	    @Override
		@Before
	    public void setUp() throws IOException {
	    	file = new File("logs/fileTest.txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(text);
            out.close();
	    	fileHolder = new FileHolder();
		    fileHolder.importFile(file.getPath());
	    }
	 
	    @Override
		@After
	    public void tearDown() {
	        fileHolder = null;
	        algorithm = null;
	        file.delete();   
	        System.gc();
	    }
	 
	    @Test
	    public void testCaesarCipher() throws IOException, NoSuchFunctionException  {
	         
	    	algorithm = new CaesarCipher();
	    	executeAlgorithmForTest();

	    }
	    
	    @Test
	    public void testMultiplicativeCipher() throws IOException, NoSuchFunctionException  {
	         
	    	algorithm = new MultiplicativeCipher();
	    	executeAlgorithmForTest();

	    }
	     
	    @Test
	    public void testXORCipher() throws IOException, NoSuchFunctionException  {
	         
	    	algorithm = new XORCipher();
	    	executeAlgorithmForTest();

	    }
	    
	    public void executeAlgorithmForTest() throws IOException, NoSuchFunctionException{
	    	algorithm.execute(fileHolder, "Encryption");
	    	algorithm.execute(fileHolder, "Decryption");
			String dec_content = new String(Files.readAllBytes
					(Paths.get(fileHolder.getDecryptedResultPath())));
			assertEquals(text, dec_content);
			//delete Files
			Paths.get(fileHolder.getEncryptedResultPath()).toFile().delete();
			Paths.get(fileHolder.getDecryptedResultPath()).toFile().delete();
	    }
	 
	
}
