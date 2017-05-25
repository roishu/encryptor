package com.apache.encryptor;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.apache.ciphers.Algorithm;
import com.apache.ciphers.BaseAlgorithm;
import com.apache.ciphers.CaesarCipher;
import com.apache.ciphers.DoubleCipher;
import com.apache.ciphers.MultiplicativeCipher;
import com.apache.ciphers.XORCipher;

import junit.framework.TestCase;

public class BaseAlgorithmJAXBTest extends TestCase{
	private String text = "Example Test Content.";
    
    @Mock
    JAXBContext context;
    @Mock
    BaseAlgorithm algorithm;
    @Mock
    FileHolder fileHolder;
    @Mock 
    File file;
 
    @Override
	@Before
    public void setUp() throws JAXBException, IOException {
    	context = JAXBContext.newInstance(CaesarCipher.class);
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
        context = null;
        algorithm = null;
        fileHolder = null; 
        file.delete();
        System.gc();
    }
 
    @Test
    public void test() throws JAXBException, IOException  {
    	Marshaller marshaller = this.context.createMarshaller();
    	marshaller.marshal(new CaesarCipher(), new File("CaesarCipher.xml"));
    	
    	Unmarshaller unmarshaller = this.context.createUnmarshaller();
    	Algorithm cipher = (Algorithm) unmarshaller.unmarshal(new File("CaesarCipher.xml"));
    	
    	cipher.execute(fileHolder, "Encryption");
    	cipher.execute(fileHolder, "Decryption");
			String dec_content = new String(Files.readAllBytes
					(Paths.get(fileHolder.getDecryptedResultPath())));
			assertEquals(text, dec_content);
			//delete Files
			Paths.get(fileHolder.getEncryptedResultPath()).toFile().delete();
			Paths.get(fileHolder.getDecryptedResultPath()).toFile().delete();
	    
     }
    
    
    }
