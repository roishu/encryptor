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
import com.apache.ciphers.MultiplicativeCipher;
import com.apache.ciphers.ReverseAlgorithm;
import com.apache.ciphers.SplitAlgorithm;
import com.apache.ciphers.XORCipher;
import com.apache.exception.NoSuchFunctionException;

public class ExtendedAlgorithmTest extends TestCase {
	private String text = "Example Test Content.";
	     /*
	      * before setting ExtendedAlgorithm as Abstract Class.
	      */
	    @Mock
	    File file;
	    @Mock
	    DoubleAlgorithm doubleAlgorithm;
	    @Mock
	    ReverseAlgorithm reverseAlgorithm;
	    @Mock
	    SplitAlgorithm splitAlgorithm;
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
	        doubleAlgorithm = null;
	        reverseAlgorithm = null;
	        splitAlgorithm = null;
	        baseAlgorithm1 = null;
	        baseAlgorithm2 = null;
	        file.delete();   
	        System.gc();
	    }
	 
	    @Test
	    public void testDoubleCipher_Caesar_XOR() throws IOException, NoSuchFunctionException  {
	    	
	    	baseAlgorithm1 = new CaesarCipher();
	    	baseAlgorithm2 = new XORCipher();
	    	doubleAlgorithm = new DoubleAlgorithm(baseAlgorithm1, baseAlgorithm2);
	    	executeDoubleAlgorithmForTest();

	    }
	    
	    @Test
	    public void testDoubleCipher_Caesar_Multi() throws IOException, NoSuchFunctionException  {
	    	
	    	baseAlgorithm1 = new CaesarCipher();
	    	baseAlgorithm2 = new MultiplicativeCipher();
	    	doubleAlgorithm = new DoubleAlgorithm(baseAlgorithm1, baseAlgorithm2);
	    	executeDoubleAlgorithmForTest();

	    }
	    
	    @Test
	    public void testDoubleCipher_XOR_Multi() throws IOException, NoSuchFunctionException  {
	    	
	    	baseAlgorithm1 = new XORCipher();
	    	baseAlgorithm2 = new MultiplicativeCipher();
	    	doubleAlgorithm = new DoubleAlgorithm(baseAlgorithm1, baseAlgorithm2);
	    	executeDoubleAlgorithmForTest();

	    }
	    
	    @Test
	    public void testDoubleCipher_Multi_Multi() throws IOException, NoSuchFunctionException  {
	    	
	    	baseAlgorithm1 = new MultiplicativeCipher();
	    	baseAlgorithm2 = new MultiplicativeCipher();
	    	doubleAlgorithm = new DoubleAlgorithm(baseAlgorithm1, baseAlgorithm2);
	    	executeDoubleAlgorithmForTest();

	    }
	    
	    @Test
	    public void testReverseCipher_Caesar() throws IOException, NoSuchFunctionException  {
	         
	    	 baseAlgorithm1 = new CaesarCipher();
	    	 reverseAlgorithm = new ReverseAlgorithm(baseAlgorithm1);
	    	 executeReverseAlgorithmForTest();
	    }
	    
	    @Test
	    public void testReverseCipher_XOR() throws IOException, NoSuchFunctionException  {
	         
	    	 baseAlgorithm1 = new XORCipher();
	    	 reverseAlgorithm = new ReverseAlgorithm(baseAlgorithm1);
	    	 executeReverseAlgorithmForTest();
	    }
	    
	    @Test
	    public void testReverseCipher_Multi() throws IOException, NoSuchFunctionException  {
	         
	    	 baseAlgorithm1 = new MultiplicativeCipher();
	    	 reverseAlgorithm = new ReverseAlgorithm(baseAlgorithm1);
	    	 executeReverseAlgorithmForTest();
	    }
	     
	    @Test
	    public void testSplitCipher_Caesar_XOR() throws IOException  {
	         baseAlgorithm1 = new CaesarCipher();
	         baseAlgorithm2 = new XORCipher();
	    	 splitAlgorithm = new SplitAlgorithm(baseAlgorithm1, baseAlgorithm2);
	    	 executeSplitAlgorithmForTest();

	    }
	    
	    @Test
	    public void testSplitCipher_Multi_XOR() throws IOException  {
	         baseAlgorithm1 = new MultiplicativeCipher();
	         baseAlgorithm2 = new XORCipher();
	    	 splitAlgorithm = new SplitAlgorithm(baseAlgorithm1, baseAlgorithm2);
	    	 executeSplitAlgorithmForTest();

	    }
	    
	    @Test
	    public void testSplitCipher_Caesar_Multi() throws IOException  {
	         baseAlgorithm1 = new CaesarCipher();
	         baseAlgorithm2 = new MultiplicativeCipher();
	    	 splitAlgorithm = new SplitAlgorithm(baseAlgorithm1, baseAlgorithm2);
	    	 executeSplitAlgorithmForTest();

	    }
	    
	    @Test
	    public void testSplitCipher_Multi_Multi() throws IOException  {
	         baseAlgorithm1 = new MultiplicativeCipher();
	         baseAlgorithm2 = new MultiplicativeCipher();
	    	 splitAlgorithm = new SplitAlgorithm(baseAlgorithm1, baseAlgorithm2);
	    	 executeSplitAlgorithmForTest();

	    }
	    
	    public void executeDoubleAlgorithmForTest() throws IOException, NoSuchFunctionException{
	    	doubleAlgorithm.execute(fileHolder, "Encryption");
	    	doubleAlgorithm.execute(fileHolder, "Decryption");
	    	//doubleAlgorithm.encrypt(fileHolder);
	    	//doubleAlgorithm.decrypt(fileHolder);
	    	//content
	    	String dec_content = new String(Files.readAllBytes
					(Paths.get(fileHolder.getDecryptedResultPath())));
	    	//test assert
			assertEquals(text, dec_content);
			//delete Files
			Paths.get(fileHolder.getEncryptedResultPath()).toFile().delete();	
			Paths.get(fileHolder.getDecryptedResultPath()).toFile().delete();	    
			}
	    
	    public void executeReverseAlgorithmForTest() throws IOException, NoSuchFunctionException{
	    	reverseAlgorithm.execute(fileHolder, "Encryption");
	    	reverseAlgorithm.execute(fileHolder, "Decryption");
	    	//reverseAlgorithm.encrypt(fileHolder);
	    	//reverseAlgorithm.decrypt(fileHolder);
	    	reverseAlgorithm.swapFiles(fileHolder);
	    	//content
	    	String dec_content = new String(Files.readAllBytes
					(Paths.get(fileHolder.getDecryptedResultPath())));
	    	//test assert
			assertEquals(text, dec_content);
			//delete Files
			Paths.get(fileHolder.getEncryptedResultPath()).toFile().delete();	
			Paths.get(fileHolder.getDecryptedResultPath()).toFile().delete();		    
			}
	    
	    public void executeSplitAlgorithmForTest() throws IOException{
	    	splitAlgorithm.execute(fileHolder, "Encryption");
	    	splitAlgorithm.execute(fileHolder, "Decryption");
	    	//splitAlgorithm.encrypt(fileHolder);
	    	//splitAlgorithm.decrypt(fileHolder);
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
