package com.apache.encryptor;
import java.io.File;
import java.util.ArrayList;

/**
 * Roi Shukrun
 * T.Z : 313454464
 * M.I : 8079738
 *
 */
public class App 
{
	
	//public static final String[] functions = { "Encryption", "Decryption"};

	
    public static void main( String[] args )  throws Exception
    {
    	//    	Path desktopPath = Paths.get("C:\\Users\\Roi\\Desktop\\files-desktop\\result1");
    	
    	EncryptorManager encryptor = new EncryptorManager();
       	
    	final File folder = new File("C:\\Users\\Roi\\Desktop\\files-desktop\\result1");
    	ArrayList<FileHolder> filesInFolder = new ArrayList<FileHolder>();
    	ThreadPoolEncryptor tpEncryptor = new ThreadPoolEncryptor(folder, "MultiplicativeCipher");
    	//tpEncryptor.execute();
    	
		//encryptor.deleteEncryptorFiles(fileHolder);
    
		long startTime = System.nanoTime();
		int i = 0;
    	    for (final File fileEntry : folder.listFiles()) {
    	    	if(!fileEntry.isDirectory() && fileEntry.exists()){
    	    		filesInFolder.add(i,new FileHolder(fileEntry));
    	    		encryptor.executeBaseAlgorithm("MultiplicativeCipher",filesInFolder.get(i));
    	    		i++;
    	    	}
    	    }//for
    	    long endTime = System.nanoTime();
    	    long duration = (endTime - startTime) / 1000000 ;
    		System.out.println("-----------Time 2: " + duration + "ms-----------"); 
        
    }   
    
}
