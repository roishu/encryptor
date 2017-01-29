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
import com.apache.ciphers.DoubleCipher;
import com.apache.ciphers.ExtendedAlgorithm;
import com.apache.ciphers.MultiplicativeCipher;
import com.apache.ciphers.ReverseCipher;
import com.apache.ciphers.SplitCipher;
import com.apache.ciphers.XORCipher;

public class ExtendedAlgorithmTest extends TestCase {
	private String text = "Example Test Content.";
	     
	    @Mock
	    File file;
	    @Mock
	    DoubleCipher doubleAlgorithm;
	    @Mock
	    ReverseCipher reverseAlgorithm;
	    @Mock
	    SplitCipher splitAlgorithm;
	    @Mock
	    BaseAlgorithm baseAlgorithm1;
	    @Mock
	    BaseAlgorithm baseAlgorithm2;
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
	        doubleAlgorithm = null;
	        reverseAlgorithm = null;
	        splitAlgorithm = null;
	        baseAlgorithm1 = null;
	        baseAlgorithm2 = null;
	        file.delete();   
	        System.gc();
	    }
	 
	    @Test
	    public void testDoubleCipher_Caesar_XOR() throws IOException  {
	    	
	    	baseAlgorithm1 = new CaesarCipher();
	    	baseAlgorithm2 = new XORCipher();
	    	doubleAlgorithm = new DoubleCipher(baseAlgorithm1, baseAlgorithm2);
	    	executeDoubleAlgorithmForTest();

	    }
	    
	    @Test
	    public void testDoubleCipher_Caesar_Multi() throws IOException  {
	    	
	    	baseAlgorithm1 = new CaesarCipher();
	    	baseAlgorithm2 = new MultiplicativeCipher();
	    	doubleAlgorithm = new DoubleCipher(baseAlgorithm1, baseAlgorithm2);
	    	executeDoubleAlgorithmForTest();

	    }
	    
	    @Test
	    public void testDoubleCipher_XOR_Multi() throws IOException  {
	    	
	    	baseAlgorithm1 = new XORCipher();
	    	baseAlgorithm2 = new MultiplicativeCipher();
	    	doubleAlgorithm = new DoubleCipher(baseAlgorithm1, baseAlgorithm2);
	    	executeDoubleAlgorithmForTest();

	    }
	    
	    @Test
	    public void testDoubleCipher_Multi_Multi() throws IOException  {
	    	
	    	baseAlgorithm1 = new MultiplicativeCipher();
	    	baseAlgorithm2 = new MultiplicativeCipher();
	    	doubleAlgorithm = new DoubleCipher(baseAlgorithm1, baseAlgorithm2);
	    	executeDoubleAlgorithmForTest();

	    }
	    
	    @Test
	    public void testReverseCipher_Caesar() throws IOException  {
	         
	    	 baseAlgorithm1 = new CaesarCipher();
	    	 reverseAlgorithm = new ReverseCipher(baseAlgorithm1);
	    	 executeReverseAlgorithmForTest();
	    }
	    
	    @Test
	    public void testReverseCipher_XOR() throws IOException  {
	         
	    	 baseAlgorithm1 = new XORCipher();
	    	 reverseAlgorithm = new ReverseCipher(baseAlgorithm1);
	    	 executeReverseAlgorithmForTest();
	    }
	    
	    @Test
	    public void testReverseCipher_Multi() throws IOException  {
	         
	    	 baseAlgorithm1 = new MultiplicativeCipher();
	    	 reverseAlgorithm = new ReverseCipher(baseAlgorithm1);
	    	 executeReverseAlgorithmForTest();
	    }
	     
	    @Test
	    public void testSplitCipher_Caesar_XOR() throws IOException  {
	         baseAlgorithm1 = new CaesarCipher();
	         baseAlgorithm2 = new XORCipher();
	    	 splitAlgorithm = new SplitCipher(baseAlgorithm1, baseAlgorithm2);
	    	 executeSplitAlgorithmForTest();

	    }
	    
	    @Test
	    public void testSplitCipher_Multi_XOR() throws IOException  {
	         baseAlgorithm1 = new MultiplicativeCipher();
	         baseAlgorithm2 = new XORCipher();
	    	 splitAlgorithm = new SplitCipher(baseAlgorithm1, baseAlgorithm2);
	    	 executeSplitAlgorithmForTest();

	    }
	    
	    @Test
	    public void testSplitCipher_Caesar_Multi() throws IOException  {
	         baseAlgorithm1 = new CaesarCipher();
	         baseAlgorithm2 = new MultiplicativeCipher();
	    	 splitAlgorithm = new SplitCipher(baseAlgorithm1, baseAlgorithm2);
	    	 executeSplitAlgorithmForTest();

	    }
	    
	    @Test
	    public void testSplitCipher_Multi_Multi() throws IOException  {
	         baseAlgorithm1 = new MultiplicativeCipher();
	         baseAlgorithm2 = new MultiplicativeCipher();
	    	 splitAlgorithm = new SplitCipher(baseAlgorithm1, baseAlgorithm2);
	    	 executeSplitAlgorithmForTest();

	    }
	    
	    public void executeDoubleAlgorithmForTest() throws IOException{
	    	doubleAlgorithm.encrypt(fileHolder);
	    	final FileHolder mFileEncHolder = new FileHolder();
	    	mFileEncHolder.importFile(fileHolder.getDirectoryPath()+"\\encrypted-algorithm.txt");
	    	doubleAlgorithm.decrypt(mFileEncHolder);
	    	//content
	    	String dec_content = new String(Files.readAllBytes
					(Paths.get(fileHolder.getDirectoryPath()+"\\decrypted-algorithm.txt")));
	    	//test assert
			assertEquals(text, dec_content);
			//delete Files
			mFileEncHolder.getFile().delete();
			Paths.get(fileHolder.getDirectoryPath()+"\\decrypted-algorithm.txt").toFile().delete();	    }
	    
	    public void executeReverseAlgorithmForTest() throws IOException{
	    	reverseAlgorithm.encrypt(fileHolder);
	    	final FileHolder mFileEncHolder = new FileHolder();
	    	mFileEncHolder.importFile(fileHolder.getDirectoryPath()+"\\decrypted-algorithm.txt");
	    	reverseAlgorithm.decrypt(fileHolder);
	    	reverseAlgorithm.swapFiles(fileHolder);
	    	//content
	    	String dec_content = new String(Files.readAllBytes
					(Paths.get(fileHolder.getDirectoryPath()+"\\decrypted-algorithm.txt")));
	    	//test assert
			assertEquals(text, dec_content);
			//delete Files
			mFileEncHolder.getFile().delete();
			Paths.get(fileHolder.getDirectoryPath()+"\\decrypted-algorithm.txt").toFile().delete();
	    }
	    
	    public void executeSplitAlgorithmForTest() throws IOException{
	    	splitAlgorithm.execute(fileHolder, "Encryption");
	    	final FileHolder mFileEncHolder = new FileHolder();
	    	mFileEncHolder.importFile(fileHolder.getDirectoryPath()+"\\encrypted-algorithm.txt");
	    	splitAlgorithm.execute(mFileEncHolder, "Decryption");
	    	//content
	    	String dec_content = new String(Files.readAllBytes
					(Paths.get(fileHolder.getDirectoryPath()+"\\decrypted-algorithm.txt")));
	    	//test assert
			assertEquals(text, dec_content);
			//delete Files
			mFileEncHolder.getFile().delete();
			Paths.get(fileHolder.getDirectoryPath()+"\\decrypted-algorithm.txt").toFile().delete();
	    }
	 
	
}
