package com.apache.encryptor;

import junit.framework.TestCase;
import static org.mockito.Mockito.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FileHolderTest extends TestCase {
	     
	    @Mock
	    File file;
	    @Mock
	    File file2;
	    @Mock
	    FileHolder fileHolder;
	 
	    @Before
	    public void setUp() throws IOException {
	    	file = new File("logs/fileTest.txt");
	    	file2 = new File("logs/fileTest2.txt"); //will be empty
	    	String text = "Example Test Content.";
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(text);
            out.close();
	    	
	    	    fileHolder = new FileHolder();
		    	fileHolder.importFile(file.getPath());
	    }
	 
	    @After
	    public void tearDown() {
	        fileHolder = null;
	        file.delete();  
	        file2.delete();     
	    }
	 
	    @Test
	    public void testFileExistance()  {
	         
	        assertTrue(file.exists());
	    }
	     
	    @Test
	    public void testFileExistanceAtFileHolder()  {
	         
	    	assertTrue(fileHolder.getFile().exists());
	    }
	    
	    @Test
	    public void testFileOverrideBehaviour(){
	    	fileHolder.importFile(file2.getPath());
	    	assertNotSame(file2, fileHolder.getFile());
	    }
	    
	    @Test
	    public void testFileEqualToHolder(){
	    	assertSame(file.getPath(), fileHolder.getFile().getPath());
	    }
	 
	
}
