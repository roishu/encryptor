package com.apache.encryptor;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.mockito.Mock;

import com.apache.ciphers.BaseAlgorithm;
import com.apache.ciphers.CaesarCipher;
import com.apache.ciphers.ExtendedAlgorithm;
import com.apache.ciphers.MultiplicativeCipher;
import com.apache.ciphers.XORCipher;
import com.apache.exception.NoSuchAlgorithmException;
import com.apache.exception.NoSuchCipherException;
import com.apache.object.EncryptorTestThread;

import junit.framework.TestCase;

public class ExceptionTest extends TestCase {
	private String text = "Example Test Content.";

	@Mock
	File file;
	@Mock
	EncryptorManager encryptor;
	@Mock
	FileHolder fileHolder;
	@Mock
	EncryptorTestThread thread;
	@Mock
	ExecutorService executor;


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
		encryptor = null;
		thread = null;
		executor = null;
		file.delete();   
		System.gc();
	}

	@Test(expected=NoSuchAlgorithmException.class)
	public void testExtendedAlgorithmException() throws IOException  {
		executor= Executors.newFixedThreadPool(1);
		thread = new EncryptorTestThread(fileHolder , encryptor , "FakeAlgorithm","CaesarCipher","XORCipher");
		executor.execute(thread);
		executor.shutdown();
		finishTest();
		checkAlgorithmException();
	}
	
	@Test(expected=NoSuchAlgorithmException.class)
	public void testBaseAlgorithmException() throws IOException  {
		executor= Executors.newFixedThreadPool(1);
		thread = new EncryptorTestThread(fileHolder , encryptor , "FakeAlgorithm");
		executor.execute(thread);
		executor.shutdown();
		finishTest();
		checkAlgorithmException();
	}
	
	@Test(expected=NoSuchCipherException.class)
	public void testCipherException() throws IOException  {
		executor= Executors.newFixedThreadPool(1);
		thread = new EncryptorTestThread(fileHolder , encryptor , "DoubleAlgorithm","FakeCipher","XORCipher");
		executor.execute(thread);
		executor.shutdown();
		finishTest();
		checkCipherException();
	}
	
	@Test(expected=NoSuchCipherException.class)
	public void testSecondaryCipherException() throws IOException  {
		executor= Executors.newFixedThreadPool(1);
		thread = new EncryptorTestThread(fileHolder , encryptor , "DoubleAlgorithm","XORCipher","FakeCipher");
		executor.execute(thread);
		executor.shutdown();
		finishTest();
		checkCipherException();
	}
	
	@Test(expected=NoSuchCipherException.class)
	public void testReverseCipherException() throws IOException  {
		executor= Executors.newFixedThreadPool(1);
		thread = new EncryptorTestThread(fileHolder , encryptor , "ReverseAlgorithm","FakeCipher");
		executor.execute(thread);
		executor.shutdown();
		finishTest();
		checkCipherException();
	}

	private void checkAlgorithmException() {
		try{
			thread.checkForExceptionForTest();
		}
		catch(Exception e){
			assertEquals(e.getClass(), NoSuchAlgorithmException.class);
		}
	}
	
	private void checkCipherException() {
		try{
			thread.checkForExceptionForTest();
		}
		catch(Exception e){
			assertEquals(e.getClass(), NoSuchCipherException.class);
		}
	}


	public void finishTest() throws IOException{
		//delete Files
		Paths.get(fileHolder.getEncryptedResultPath()).toFile().delete();
		Paths.get(fileHolder.getDecryptedResultPath()).toFile().delete();
	}

}
