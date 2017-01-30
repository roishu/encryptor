package com.apache.encryptor;

import junit.framework.TestCase;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class FileHolderTest extends TestCase {
	     
	    @Mock
	    File file;
	    @Mock
	    File file2;
	    @Mock
	    FileHolder fileHolder;
	 
	    @Override
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
	 
	    @Override
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
