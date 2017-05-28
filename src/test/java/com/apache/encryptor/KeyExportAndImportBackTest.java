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

import javax.xml.bind.JAXBException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.apache.ciphers.BaseAlgorithm;
import com.apache.ciphers.CaesarCipher;
import com.apache.ciphers.ExtendedAlgorithm;
import com.apache.ciphers.MultiplicativeCipher;
import com.apache.ciphers.SplitAlgorithm;
import com.apache.ciphers.XORCipher;
import com.apache.exception.IllegalKeyException;
import com.apache.exception.NoSuchFunctionException;

import junit.framework.TestCase;

public class KeyExportAndImportBackTest extends TestCase {
	private String text = "Example Test Content.";
	     
    @Mock
    File file1,file2;
    @Mock
    EncryptorManager encryptor;
    @Mock
    FileHolder fileHolder1 , fileHolder2;
 
    @Override
	@Before
    public void setUp() throws IOException, JAXBException {
    	encryptor = new EncryptorManager();
    	file1 = new File("logs/fileTest.txt");
    	file2 = new File("logs/fileTest.txt");
        BufferedWriter out = new BufferedWriter(new FileWriter(file1));
        out.write(text);
        out.close();
        BufferedWriter out2 = new BufferedWriter(new FileWriter(file2));
        out2.write(text);
        out2.close();
    	fileHolder1 = new FileHolder();
	    fileHolder1.importFile(file2.getPath());
    	fileHolder2 = new FileHolder();
	    fileHolder2.importFile(file2.getPath());
    }
 
    @Override
	@After
    public void tearDown() {
        fileHolder1 = null;
        fileHolder2 = null;
        encryptor = null;
        file1.delete(); 
        file2.delete();   
        System.gc();
    }
 
    @Test
    public void testDoubleCipher_Caesar_XOR() throws IOException, JAXBException, NoSuchFunctionException, IllegalKeyException  {
    	encryptor.executeDoubleAlgorithm("CaesarCipher", "XORCipher", fileHolder1);
    	encryptor.exportKey("CaesarCipher");
    	encryptor.setCipherKeys("CaesarCipher", "CaesarCipher", "key.bin");
    	encryptor.executeDoubleAlgorithm("CaesarCipher", "XORCipher", fileHolder2);
    	finish();
    }
    
    @Test
    public void testDoubleCipher_XOR_Multi() throws IOException, JAXBException, NoSuchFunctionException, IllegalKeyException  {
    	encryptor.executeDoubleAlgorithm("XORCipher", "MultiplicativeCipher", fileHolder1);
    	encryptor.exportKey("MultiplicativeCipher");
    	encryptor.setCipherKeys("CaesarCipher", "CaesarCipher", "key.bin");
    	encryptor.executeDoubleAlgorithm("XORCipher", "MultiplicativeCipher", fileHolder2);
    	finish();
    }
    
    @Test
    public void testDoubleCipher_Multi_Caesar() throws IOException, JAXBException, NoSuchFunctionException, IllegalKeyException  {
    	encryptor.executeDoubleAlgorithm("MultiplicativeCipher", "CaesarCipher", fileHolder1);
    	encryptor.exportKey("MultiplicativeCipher");
    	encryptor.setCipherKeys("CaesarCipher", "CaesarCipher", "key.bin");
    	encryptor.executeDoubleAlgorithm("MultiplicativeCipher", "CaesarCipher", fileHolder2);
    	finish();    
    	}
    
    @Test
    public void testReverseCipher_Caesar() throws IOException, NoSuchFunctionException, IllegalKeyException, JAXBException  {
    	encryptor.executeReverseAlgorithm("CaesarCipher", fileHolder1);
    	encryptor.exportKey("CaesarCipher");
    	encryptor.setCipherKeys("CaesarCipher", "CaesarCipher", "key.bin");
    	encryptor.executeReverseAlgorithm("CaesarCipher", fileHolder2);
    	finish();    
    	}
    
    @Test
    public void testReverseCipher_XOR() throws IOException, NoSuchFunctionException, IllegalKeyException, JAXBException  {
    	encryptor.executeReverseAlgorithm("XORCipher", fileHolder1);
    	encryptor.exportKey("XORCipher");
    	encryptor.setCipherKeys("XORCipher", "XORCipher", "key.bin");
    	encryptor.executeReverseAlgorithm("XORCipher", fileHolder2);
    	finish();    
    	}
    
    @Test
    public void testReverseCipher_Multi() throws IOException, NoSuchFunctionException, IllegalKeyException, JAXBException  {
    	encryptor.executeReverseAlgorithm("MultiplicativeCipher", fileHolder1);
    	encryptor.exportKey("MultiplicativeCipher");
    	encryptor.setCipherKeys("MultiplicativeCipher", "MultiplicativeCipher", "key.bin");
    	encryptor.executeReverseAlgorithm("MultiplicativeCipher", fileHolder2);
    	finish();    
    	}
    
    @Test
    public void testSplitCipher_Caesar_XOR() throws IOException, NoSuchFunctionException, JAXBException, IllegalKeyException  {
    	encryptor.executeSplitAlgorithm("CaesarCipher", "XORCipher", fileHolder1);
    	encryptor.exportKey("XORCipher");
    	encryptor.setCipherKeys("CaesarCipher", "XORCipher", "key.bin");
    	encryptor.executeSplitAlgorithm("CaesarCipher", "XORCipher", fileHolder2);
    	finish();    
    	}
    
    @Test
    public void testSplitCipher_XOR_Multi() throws IOException, NoSuchFunctionException, IllegalKeyException  {
    	encryptor.executeSplitAlgorithm("XORCipher", "MultiplicativeCipher", fileHolder1);
    	encryptor.exportKey("MultiplicativeCipher");
    	encryptor.setCipherKeys("MultiplicativeCipher", "MultiplicativeCipher", "key.bin");
    	encryptor.executeSplitAlgorithm("XORCipher", "MultiplicativeCipher", fileHolder2);
    	finish();    
    	}
    
    @Test
    public void testSplitCipher_Multi_Caesar() throws IOException, NoSuchFunctionException, IllegalKeyException  {
    	encryptor.executeSplitAlgorithm("MultiplicativeCipher", "CaesarCipher", fileHolder1);
    	encryptor.exportKey("MultiplicativeCipher");
    	encryptor.setCipherKeys("CaesarCipher", "MultiplicativeCipher", "key.bin");
    	encryptor.executeSplitAlgorithm("MultiplicativeCipher", "CaesarCipher", fileHolder2);
    	finish();    
    	}
    
    
    public void finish() throws IOException {
    	String dec_content1 = new String(Files.readAllBytes
				(Paths.get(fileHolder1.getDecryptedResultPath())));
    	String dec_content2 = new String(Files.readAllBytes
				(Paths.get(fileHolder2.getDecryptedResultPath())));
		assertEquals(dec_content1, dec_content2);
		encryptor.deleteEncryptorFiles(fileHolder1);
		encryptor.deleteEncryptorFiles(fileHolder2);
	}
	    
}
