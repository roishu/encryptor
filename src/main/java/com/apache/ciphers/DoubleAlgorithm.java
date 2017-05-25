package com.apache.ciphers;

import java.io.IOException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.apache.encryptor.FileHolder;

public class DoubleAlgorithm extends ExtendedAlgorithm{

	public DoubleAlgorithm(BaseAlgorithm baseAlgorithm, BaseAlgorithm secondaryBaseAlgorithm) {
		super("DoubleAlgorithm",baseAlgorithm, secondaryBaseAlgorithm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void encrypt(FileHolder fileHolder) throws IOException {
		baseAlgorithm.execute(fileHolder, "Encryption");
		secondaryBaseAlgorithm.execute(fileHolder, "Encryption");
	}

	@Override
	public void decrypt(FileHolder fileHolder) throws IOException {
		// TODO Auto-generated method stub
		secondaryBaseAlgorithm.execute(fileHolder, "Decryption");
		baseAlgorithm.execute(fileHolder, "Decryption");
	}
	
	@Override
	public void execute(FileHolder fileHolder , String choice) throws IOException {
		if(choice.equals("Encryption"))
			encrypt(fileHolder);
		else
			decrypt(fileHolder);
	}

}
