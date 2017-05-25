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
import com.apache.ciphers.DoubleAlgorithm;
import com.apache.ciphers.ExtendedAlgorithm;
import com.apache.ciphers.MultiplicativeCipher;
import com.apache.ciphers.ReverseAlgorithm;
import com.apache.ciphers.SplitAlgorithm;
import com.apache.ciphers.XORCipher;

public class AbstractExtendedAlgorithmTest extends TestCase {
	private String text = "Example Test Content.";
	     
	    @Mock
	    File file;
	    @Mock
	    ExtendedAlgorithm exAlgorithm;
	    @Mock
	    BaseAlgorithm baseAlgorithm1;
	    @Mock
	    BaseAlgorithm baseAlgorithm2;
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
	        exAlgorithm = null;
	        baseAlgorithm1 = null;
	        baseAlgorithm2 = null;
	        file.delete();   
	        System.gc();
	    }
	 
	    @Test
	    public void testDoubleCipher_Caesar_XOR() throws IOException  {
	    	
	    	baseAlgorithm1 = new CaesarCipher();
	    	baseAlgorithm2 = new XORCipher();
	    	exAlgorithm = new DoubleAlgorithm(baseAlgorithm1, baseAlgorithm2);
	    	executeExtendedAlgorithmForTest();

	    }
	    
	    @Test
	    public void testDoubleCipher_Caesar_Multi() throws IOException  {
	    	
	    	baseAlgorithm1 = new CaesarCipher();
	    	baseAlgorithm2 = new MultiplicativeCipher();
	    	exAlgorithm = new DoubleAlgorithm(baseAlgorithm1, baseAlgorithm2);
	    	executeExtendedAlgorithmForTest();

	    }
	    
	    @Test
	    public void testDoubleCipher_XOR_Multi() throws IOException  {
	    	
	    	baseAlgorithm1 = new XORCipher();
	    	baseAlgorithm2 = new MultiplicativeCipher();
	    	exAlgorithm = new DoubleAlgorithm(baseAlgorithm1, baseAlgorithm2);
	    	executeExtendedAlgorithmForTest();

	    }
	    
	    @Test
	    public void testDoubleCipher_Multi_Multi() throws IOException  {
	    	
	    	baseAlgorithm1 = new MultiplicativeCipher();
	    	baseAlgorithm2 = new MultiplicativeCipher();
	    	exAlgorithm = new DoubleAlgorithm(baseAlgorithm1, baseAlgorithm2);
	    	executeExtendedAlgorithmForTest();

	    }
	    
	    @Test
	    public void testReverseCipher_Caesar() throws IOException  {
	         
	    	 baseAlgorithm1 = new CaesarCipher();
	    	 exAlgorithm = new ReverseAlgorithm(baseAlgorithm1);
	    	 executeExtendedAlgorithmForTest();
	    }
	    
	    @Test
	    public void testReverseCipher_XOR() throws IOException  {
	         
	    	 baseAlgorithm1 = new XORCipher();
	    	 exAlgorithm = new ReverseAlgorithm(baseAlgorithm1);
	    	 executeExtendedAlgorithmForTest();
	    }
	    
	    @Test
	    public void testReverseCipher_Multi() throws IOException  {
	         
	    	 baseAlgorithm1 = new MultiplicativeCipher();
	    	 exAlgorithm = new ReverseAlgorithm(baseAlgorithm1);
	    	 executeExtendedAlgorithmForTest();
	    }
	     
	    @Test
	    public void testSplitCipher_Caesar_XOR() throws IOException  {
	         baseAlgorithm1 = new CaesarCipher();
	         baseAlgorithm2 = new XORCipher();
	         exAlgorithm = new SplitAlgorithm(baseAlgorithm1, baseAlgorithm2);
	         executeExtendedAlgorithmForTest();

	    }
	    
	    @Test
	    public void testSplitCipher_Multi_XOR() throws IOException  {
	         baseAlgorithm1 = new MultiplicativeCipher();
	         baseAlgorithm2 = new XORCipher();
	         exAlgorithm = new SplitAlgorithm(baseAlgorithm1, baseAlgorithm2);
	         executeExtendedAlgorithmForTest();

	    }
	    
	    @Test
	    public void testSplitCipher_Caesar_Multi() throws IOException  {
	         baseAlgorithm1 = new CaesarCipher();
	         baseAlgorithm2 = new MultiplicativeCipher();
	         exAlgorithm = new SplitAlgorithm(baseAlgorithm1, baseAlgorithm2);
	         executeExtendedAlgorithmForTest();

	    }
	    
	    @Test
	    public void testSplitCipher_Multi_Multi() throws IOException  {
	         baseAlgorithm1 = new MultiplicativeCipher();
	         baseAlgorithm2 = new MultiplicativeCipher();
	         exAlgorithm = new SplitAlgorithm(baseAlgorithm1, baseAlgorithm2);
	         executeExtendedAlgorithmForTest();

	    }
	    
	    public void executeExtendedAlgorithmForTest() throws IOException{
	    	exAlgorithm.execute(fileHolder, "Encryption");
	    	exAlgorithm.execute(fileHolder, "Decryption");
	    	if(exAlgorithm.getName().equals("ReverseAlgorithm"))
	    		((ReverseAlgorithm)exAlgorithm).swapFiles(fileHolder);
	    	//content
	    	String dec_content = new String(Files.readAllBytes
					(Paths.get(fileHolder.getDecryptedResultPath())));
	    	//test assert
			assertEquals(text, dec_content);
			//delete Files
			Paths.get(fileHolder.getEncryptedResultPath()).toFile().delete();	
			Paths.get(fileHolder.getDecryptedResultPath()).toFile().delete();	    
			}
	 
	
}
