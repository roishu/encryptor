package com.apache.encryptor;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
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

import junit.framework.TestCase;

public class KeyChangeCipherTest extends TestCase {
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
	        new File("logs/key.bin").delete();
	    }
	    
	    @Test
	    public void testCaesarCipher() throws IOException, NoSuchFunctionException  {
	         
			try {
				String x = "abcd";
				FileOutputStream fos = new FileOutputStream("logs/key.bin");
				ObjectOutputStream out = new ObjectOutputStream(fos);
				out.writeObject(x.getBytes()[0]);
				out.close();
		    	algorithm = new CaesarCipher();
		    	algorithm.setKey("logs/key.bin");
		    	executeAlgorithmForTest();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
	    	
	    }
	    
	    @Test
	    public void testXORCipher() throws IOException, NoSuchFunctionException  {
	         
			try {
				String x = "abcd";
				FileOutputStream fos = new FileOutputStream("logs/key.bin");
				ObjectOutputStream out = new ObjectOutputStream(fos);
				out.writeObject(x.getBytes()[0]);
				out.close();
		    	algorithm = new XORCipher();
		    	algorithm.setKey("logs/key.bin");
		    	executeAlgorithmForTest();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
	    	
	    }
	    
	    @Test
	    public void testMultiplicativeCipher() throws IOException, NoSuchFunctionException  {
	         
			try {
				int x = 15;
				FileOutputStream fos = new FileOutputStream("logs/key.bin");
				ObjectOutputStream out = new ObjectOutputStream(fos);
				out.writeObject((byte)15);
				out.close();
		    	algorithm = new XORCipher();
		    	algorithm.setKey("logs/key.bin");
		    	executeAlgorithmForTest();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
	    	
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
