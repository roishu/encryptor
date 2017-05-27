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

public class EncryptorManagerExtendedAlgorithmTest extends TestCase {
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
	    public void testDoubleCipher_Caesar_XOR() throws IOException, JAXBException  {
	    	encryptor.executeDoubleAlgorithm("CaesarCipher", "XORCipher", fileHolder);
	    	finish();
	    }
	    
	    @Test
	    public void testDoubleCipher_XOR_Multi() throws IOException, JAXBException  {
	    	encryptor.executeDoubleAlgorithm("XORCipher", "MultiplicativeCipher", fileHolder);
	    	finish();
	    }
	    
	    @Test
	    public void testDoubleCipher_Multi_Caesar() throws IOException, JAXBException  {
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
