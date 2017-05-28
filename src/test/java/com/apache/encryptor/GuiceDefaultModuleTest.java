package com.apache.encryptor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.xml.bind.JAXBException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.apache.exception.NoSuchAlgorithmException;
import com.apache.guice.DefaultModule;
import com.apache.object.EncryptorTestThread;
import com.google.inject.Guice;
import com.google.inject.Injector;

import junit.framework.TestCase;

public class GuiceDefaultModuleTest extends TestCase {
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

	@Test
	public void testReverseAlgorithmInjection() throws IOException  {
	    Injector injector = Guice.createInjector(new DefaultModule());

	    /*
	     * Now that we've got the injector, we can build objects.
	     
	    EncryptorThreadPoolModel model = injector.getInstance(EncryptorThreadPoolModel.class);
	    try {
			model.execute();
		} catch (java.security.NoSuchAlgorithmException | JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	
	
}
