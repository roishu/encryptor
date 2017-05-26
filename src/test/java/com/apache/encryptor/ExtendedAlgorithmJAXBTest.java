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
import com.apache.ciphers.DoubleAlgorithm;
import com.apache.ciphers.ExtendedAlgorithm;
import com.apache.ciphers.MultiplicativeCipher;
import com.apache.ciphers.XORCipher;

import junit.framework.TestCase;

public class ExtendedAlgorithmJAXBTest extends TestCase{
	private String text = "Example Test Content.";
    
    @Mock
    JAXBContext context , doubleContext;
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
    	doubleContext = JAXBContext.newInstance(DoubleAlgorithm.class);
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
    public void testJAXB_ExtendedAlgorithm() throws JAXBException, IOException  {
    	Marshaller marshaller = this.context.createMarshaller();
    	marshaller.marshal(new CaesarCipher(), new File("CaesarCipher.xml"));
    	
    	Unmarshaller unmarshaller = this.context.createUnmarshaller();
    	CaesarCipher cipher = (CaesarCipher) unmarshaller.unmarshal(new File("CaesarCipher.xml"));
    	
    	Marshaller marshaller2 = this.doubleContext.createMarshaller();
    	marshaller2.marshal(new DoubleAlgorithm(), new File("DoubleAlgorithm.xml"));
    	
    	Unmarshaller unmarshaller2 = this.doubleContext.createUnmarshaller();
    	DoubleAlgorithm doubleAlgorithm = (DoubleAlgorithm) unmarshaller2.unmarshal(new File("DoubleAlgorithm.xml"));
    	
    	assertEquals(doubleAlgorithm.getBaseAlgorithmName(), "CaesarCipher");
    	assertEquals(doubleAlgorithm.getSecondaryBaseAlgorithmName(), "MultiplicativeCipher");
    	
    	doubleAlgorithm.execute(fileHolder, "Encryption");
    	doubleAlgorithm.execute(fileHolder, "Decryption");
			String dec_content = new String(Files.readAllBytes
					(Paths.get(fileHolder.getDecryptedResultPath())));
			assertEquals(text, dec_content);
			//delete Files
			Paths.get(fileHolder.getEncryptedResultPath()).toFile().delete();
			Paths.get(fileHolder.getDecryptedResultPath()).toFile().delete();
     }
    
    
    }
