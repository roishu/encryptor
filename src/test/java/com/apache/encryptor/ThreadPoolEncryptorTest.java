package com.apache.encryptor;

import junit.framework.TestCase;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.bind.JAXBException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class ThreadPoolEncryptorTest extends TestCase {
	private String text = "Example Test Content.";
	     
	    @Mock
	    File file1, file2 , file3 , file4 , folder;
	    @Mock
	    ArrayList<FileHolder> filesInFolder;
	    @Mock
	    EncryptorThreadPoolModel tpEncryptor;
	 
	    @Override
		@Before
	    public void setUp() throws IOException, JAXBException {
	    	file1 = new File("logs/fileTest1.txt");
	    	testFileSize(10,'A',file1);	
	    	file2 = new File("logs/fileTest2.txt");
	    	testFileSize(10,'B',file2);	
	    	file3 = new File("logs/fileTest3.txt");
	    	testFileSize(10,'C',file3);	
	    	file4 = new File("logs/fileTest4.txt");
	    	testFileSize(10,'D',file4);	
	    	folder = new File("logs");
	    	filesInFolder = new ArrayList<FileHolder>();
	    }
	 
	    @Override
		@After
	    public void tearDown() {
	        tpEncryptor = null;
	        file1.delete();
	        file2.delete();   
	        file3.delete();   
	        file4.delete(); 
	        for (FileHolder fileHolder : filesInFolder) 
	        	fileHolder = null;
	        System.gc();
	    }
	 
	    @Test
	    public void testCaesarCipher() throws IOException, JAXBException, NoSuchAlgorithmException  {
	    	System.out.println("testCaesarCipher");
	    	EncryptorThreadPoolModel tpEncryptor = new EncryptorThreadPoolModel(folder, "CaesarCipher");
	    	tpEncryptor.execute();
	    	finishTest();
	    }
	    
	    @Test
	    public void testMultiplicativeCipher() throws IOException, JAXBException, NoSuchAlgorithmException  {
	    	System.out.println("testMultiplicativeCipher");
	    	EncryptorThreadPoolModel tpEncryptor = new EncryptorThreadPoolModel(folder, "MultiplicativeCipher");
	    	tpEncryptor.execute();
	    	finishTest();
	    }
	    
	    @Test
	    public void testXORCipher() throws IOException, JAXBException, NoSuchAlgorithmException  {
	    	System.out.println("testXORCipher");
	    	EncryptorThreadPoolModel tpEncryptor = new EncryptorThreadPoolModel(folder, "XORCipher");
	    	tpEncryptor.execute();
	    	finishTest();
	    }
	    
	    @Test
	    public void testDoubleAlgorithm_Caesar_Multiplicative() throws IOException, JAXBException, NoSuchAlgorithmException  {
	    	System.out.println("testDoubleAlgorithm");
	    	EncryptorThreadPoolModel tpEncryptor = new EncryptorThreadPoolModel(folder, "DoubleAlgorithm" ,"CaesarCipher" , "MultiplicativeCipher");
	    	tpEncryptor.execute();
	    	finishTest();
	    }
	    
	    @Test
	    public void testDoubleAlgorithm_Multiplicative_XOR() throws IOException, JAXBException, NoSuchAlgorithmException  {
	    	System.out.println("testDoubleAlgorithm");
	    	EncryptorThreadPoolModel tpEncryptor = new EncryptorThreadPoolModel(folder, "DoubleAlgorithm" ,"MultiplicativeCipher" , "XORCipher");
	    	tpEncryptor.execute();
	    	finishTest();
	    }
	    
	    @Test
	    public void testDoubleAlgorithm_XOR_Caesar() throws IOException, JAXBException, NoSuchAlgorithmException  {
	    	System.out.println("testDoubleAlgorithm");
	    	EncryptorThreadPoolModel tpEncryptor = new EncryptorThreadPoolModel(folder, "DoubleAlgorithm" ,"XORCipher" , "CaesarCipher");
	    	tpEncryptor.execute();
	    	finishTest();
	    }
	    
	    @Test
	    public void testReverseAlgorithm() throws IOException, JAXBException, NoSuchAlgorithmException  {
	    	System.out.println("testReverseAlgorithm");
	    	EncryptorThreadPoolModel tpEncryptor = new EncryptorThreadPoolModel(folder, "ReverseAlgorithm" ,"XORCipher");
	    	tpEncryptor.execute();
	    	finishTest();
	    }
	    
	    @Test
	    public void testSplitAlgorithm() throws IOException, JAXBException, NoSuchAlgorithmException  {
	    	System.out.println("testSplitAlgorithm");
	    	EncryptorThreadPoolModel tpEncryptor = new EncryptorThreadPoolModel(folder, "SplitAlgorithm" ,"MultiplicativeCipher" , "MultiplicativeCipher");
	    	tpEncryptor.execute();
	    	finishTest();
	    }
	    
	    private void finishTest() throws IOException {
	    	File encFile1 = new File("logs/fileTest1.encrypted");
	    	File encFile2 = new File("logs/fileTest2.encrypted");
	    	File encFile3 = new File("logs/fileTest3.encrypted");
	    	File encFile4 = new File("logs/fileTest4.encrypted");
	    	File decFile1 = new File("logs/fileTest1-decrypted.txt");
	    	File decFile2 = new File("logs/fileTest2-decrypted.txt");
	    	File decFile3 = new File("logs/fileTest3-decrypted.txt");
	    	File decFile4 = new File("logs/fileTest4-decrypted.txt");
	    	String content1 = new String(Files.readAllBytes
					(Paths.get(file1.getPath())));
	    	String content2 = new String(Files.readAllBytes
					(Paths.get(file2.getPath())));
	    	String content3 = new String(Files.readAllBytes
					(Paths.get(file3.getPath())));
	    	String content4 = new String(Files.readAllBytes
					(Paths.get(file4.getPath())));
	    	String dec_content1 = new String(Files.readAllBytes
					(Paths.get(decFile1.getPath())));
	    	String dec_content2 = new String(Files.readAllBytes
					(Paths.get(decFile2.getPath())));
	    	String dec_content3 = new String(Files.readAllBytes
					(Paths.get(decFile3.getPath())));
	    	String dec_content4 = new String(Files.readAllBytes
					(Paths.get(decFile4.getPath())));
	    	assertEquals(content1,dec_content1);
	    	assertEquals(content2,dec_content2);
	    	assertEquals(content3,dec_content3);
	    	assertEquals(content4,dec_content4);
	    	encFile1.delete();
	    	encFile2.delete();
	    	encFile3.delete();
	    	encFile4.delete();
	    	decFile1.delete();
	    	decFile2.delete();
	    	decFile3.delete();
	    	decFile4.delete();
		}

		private void testFileSize(int mb , char c , File file) throws IOException {
	        char[] chars = new char[1024];
	        Arrays.fill(chars, c);
	        String longLine = new String(chars);
	        long start1 = System.nanoTime();
	        PrintWriter pw = new PrintWriter(new FileWriter(file));
	        for (int i = 0; i < mb * 1024; i++)
	            pw.println(longLine);
	        pw.close();
	    }
	    

}
