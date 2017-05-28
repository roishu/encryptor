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
import com.apache.exception.NoSuchFunctionException;
import com.apache.jaxb.DoubleAlgorithmJAXB;

import junit.framework.TestCase;

public class DoubleAlgorithmJAXBTest extends TestCase{
	private String text = "Example Test Content.";
    
    @Mock
    JAXBContext jc;
    @Mock
    DoubleAlgorithmJAXB doubleAlgorithmJAXB;
    @Mock
    FileHolder fileHolder;
    @Mock 
    File file;
    @Mock
    File xmlFile;
    @Mock 
    Marshaller marshaller;
 
    @Override
	@Before
    public void setUp() throws JAXBException, IOException {
    	jc = JAXBContext.newInstance(DoubleAlgorithmJAXB.class);
        marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    	file = new File("logs/fileTest.txt");
    	xmlFile = new File("DoubleAlgorithmJAXB.xml");
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        out.write(text);
        out.close();
    	fileHolder = new FileHolder();
	    fileHolder.importFile(file.getPath());
    }
 
    @Override
	@After
    public void tearDown() {
        jc = null;
        doubleAlgorithmJAXB = null;
        fileHolder = null; 
        file.delete();
        System.gc();
    }
 
    @Test
    public void testCaesarXOR() throws JAXBException, IOException, NoSuchFunctionException{
        // Create Domain Objects
        String baseAlgorithm = "CaesarCipher";
        String secondaryBaseAlgorithm = "XORCipher";
        doubleAlgorithmJAXB = new DoubleAlgorithmJAXB();
        doubleAlgorithmJAXB.setCipher(baseAlgorithm);
        doubleAlgorithmJAXB.setSecondaryCipher(secondaryBaseAlgorithm);
        finishTest();
    }
    
    @Test
    public void testCaesarMultiplicative() throws JAXBException, IOException, NoSuchFunctionException{
        // Create Domain Objects
        String baseAlgorithm = "MultiplicativeCipher";
        String secondaryBaseAlgorithm = "CaesarCipher";
        doubleAlgorithmJAXB = new DoubleAlgorithmJAXB();
        doubleAlgorithmJAXB.setCipher(baseAlgorithm);
        doubleAlgorithmJAXB.setSecondaryCipher(secondaryBaseAlgorithm);
        finishTest();
    }
    
    @Test
    public void testCaesarCaesar() throws JAXBException, IOException, NoSuchFunctionException{
        // Create Domain Objects
    	String baseAlgorithm = "CaesarCipher";
        String secondaryBaseAlgorithm = "CaesarCipher";
        doubleAlgorithmJAXB = new DoubleAlgorithmJAXB();
        doubleAlgorithmJAXB.setCipher(baseAlgorithm);
        doubleAlgorithmJAXB.setSecondaryCipher(secondaryBaseAlgorithm);
        finishTest();
    }
    
    @Test
    public void testXORXOR() throws JAXBException, IOException, NoSuchFunctionException{
        // Create Domain Objects
        String baseAlgorithm = "XORCipher";
        String secondaryBaseAlgorithm = "XORCipher";
        doubleAlgorithmJAXB = new DoubleAlgorithmJAXB();
        doubleAlgorithmJAXB.setCipher(baseAlgorithm);
        doubleAlgorithmJAXB.setSecondaryCipher(secondaryBaseAlgorithm);
        finishTest();
    }
    
    @Test
    public void testXORMultiplicative() throws JAXBException, IOException, NoSuchFunctionException{
        // Create Domain Objects
        String baseAlgorithm = "XORCipher";
        String secondaryBaseAlgorithm = "MultiplicativeCipher";
        doubleAlgorithmJAXB = new DoubleAlgorithmJAXB();
        doubleAlgorithmJAXB.setCipher(baseAlgorithm);
        doubleAlgorithmJAXB.setSecondaryCipher(secondaryBaseAlgorithm);
        finishTest();
    }
    
    @Test
    public void testMultiplicativeMultiplicative() throws JAXBException, IOException, NoSuchFunctionException{
        // Create Domain Objects
        String baseAlgorithm = "MultiplicativeCipher";
        String secondaryBaseAlgorithm = "MultiplicativeCipher";
        doubleAlgorithmJAXB = new DoubleAlgorithmJAXB();
        doubleAlgorithmJAXB.setCipher(baseAlgorithm);
        doubleAlgorithmJAXB.setSecondaryCipher(secondaryBaseAlgorithm);
        finishTest();
    }
    
    public void finishTest() throws JAXBException, IOException, NoSuchFunctionException  {
        // Marshal doubleAlgorithmJAXB
        marshaller.marshal(doubleAlgorithmJAXB, xmlFile);
        doubleAlgorithmJAXB=null;
        //Unmarshal doubleAlgorithmJAXB
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        doubleAlgorithmJAXB = 
        		(DoubleAlgorithmJAXB) unmarshaller.unmarshal(xmlFile);
        //JAXB.unmarshal(new ByteArrayInputStream(string.getBytes("UTF-8")), Delivery.class);
    	doubleAlgorithmJAXB.execute(fileHolder, "Encryption");
    	doubleAlgorithmJAXB.execute(fileHolder, "Decryption");
			String dec_content = new String(Files.readAllBytes
					(Paths.get(fileHolder.getDecryptedResultPath())));
			assertEquals(text, dec_content);
			//delete Files
			Paths.get(fileHolder.getEncryptedResultPath()).toFile().delete();
			Paths.get(fileHolder.getDecryptedResultPath()).toFile().delete();
     }
    
    
    }
