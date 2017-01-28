package com.apache.ciphers;

import java.io.File;
import java.io.IOException;

import com.apache.encryptor.FileHolder;

public class DoubleCipher extends ExtendedAlgorithm{

	public DoubleCipher(BaseAlgorithm baseAlgorithm, BaseAlgorithm secondaryBaseAlgorithm) {
		super(baseAlgorithm, secondaryBaseAlgorithm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void encrypt(FileHolder fileHolder) throws IOException {
		baseAlgorithm.execute(fileHolder, "Encryption");
		FileHolder fileEncHolder = new FileHolder();
		fileEncHolder.importFile(fileHolder.getDirectoryPath()+"\\encrypted-algorithm.txt");
		secondaryBaseAlgorithm.execute(fileEncHolder, "Encryption");
	}

	@Override
	public void decrypt(FileHolder fileHolder) throws IOException {
		// TODO Auto-generated method stub
		secondaryBaseAlgorithm.execute(fileHolder, "Decryption");
		FileHolder fileDecHolder = new FileHolder();
		fileDecHolder.importFile(fileHolder.getDirectoryPath()+"\\decrypted-algorithm.txt");
		baseAlgorithm.execute(fileDecHolder, "Decryption");
	}

}
