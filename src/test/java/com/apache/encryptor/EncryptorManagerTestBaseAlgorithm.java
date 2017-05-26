package com.apache.encryptor;

import junit.framework.TestCase;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.bind.JAXBException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class EncryptorManagerTestBaseAlgorithm extends TestCase {
	private String text = "Example Test Content.";
	     
	    @Mock
	    File file;
	    @Mock
	    EncryptorManager encryptor;
	    @Mock
	    FileHolder fileHolder;
	 
	    @Override
		@Before
	    public void setUp() throws IOException, JAXBException {
	    	encryptor = new EncryptorManager();
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
	        encryptor = null;
	        file.delete();   
	        System.gc();
	    }
	 
	    @Test
	    public void testCaesarCipher() throws IOException  {
	    	encryptor.executeBaseAlgorithm("CaesarCipher", fileHolder);
	    	finish();
	    }
	    
		@Test
	    public void testMultiplicativeCipher() throws IOException  {
	         
	    	encryptor.executeBaseAlgorithm("MultiplicativeCipher", fileHolder);
	    	finish();
	    }
	     
	    @Test
	    public void testXORCipher() throws IOException  {
	         
	    	encryptor.executeBaseAlgorithm("XORCipher", fileHolder);
	    	finish();
	    }
	    
	    public void finish() throws IOException {
	    	String dec_content = new String(Files.readAllBytes
					(Paths.get(fileHolder.getDecryptedResultPath())));
			assertEquals(text, dec_content);
			encryptor.deleteEncryptorFiles(fileHolder);
		}
	 
	
}
