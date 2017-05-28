package com.apache.ciphers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.apache.encryptor.FileHolder;
import com.apache.exception.NoSuchFunctionException;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class ReverseAlgorithm extends ExtendedAlgorithm{

	public ReverseAlgorithm(BaseAlgorithm baseAlgorithm) {
		super("ReverseAlgorithm",baseAlgorithm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void encrypt(FileHolder fileHolder) throws IOException, NoSuchFunctionException {
		baseAlgorithm.execute(fileHolder, "Decryption");
		
	}

	@Override
	public void decrypt(FileHolder fileHolder) throws IOException, NoSuchFunctionException {
		// TODO Auto-generated method stub
		baseAlgorithm.execute(fileHolder, "Encryption");
		
	}
	
	public void swapFiles(FileHolder fileHolder) throws IOException{
		String e_content = new String(Files.readAllBytes(Paths.get(fileHolder.getDecryptedResultPath())));
		String d_content = new String(Files.readAllBytes(Paths.get(fileHolder.getEncryptedResultPath())));

		FileOutputStream fos_e = new FileOutputStream(fileHolder.getEncryptedResultPath());
		FileOutputStream fos_d = new FileOutputStream(fileHolder.getDecryptedResultPath());
		
		
		fos_d.write(d_content.getBytes());
		fos_e.write(e_content.getBytes());
		
		fos_d.close();
		fos_e.close();
	}
	
	@Override
	public void execute(FileHolder fileHolder , String choice) throws IOException, NoSuchFunctionException {
		if(choice.equals("Encryption"))
			encrypt(fileHolder);
		else
			decrypt(fileHolder);
	}
	
	

}
