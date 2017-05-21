package com.apache.ciphers;

import java.io.IOException;

import com.apache.encryptor.FileHolder;

public class DoubleCipher extends ExtendedAlgorithm{

	public DoubleCipher(BaseAlgorithm baseAlgorithm, BaseAlgorithm secondaryBaseAlgorithm) {
		super("DoubleCipher",baseAlgorithm, secondaryBaseAlgorithm);
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
