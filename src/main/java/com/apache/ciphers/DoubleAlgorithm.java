package com.apache.ciphers;

import java.io.IOException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.apache.encryptor.FileHolder;
import com.apache.exception.NoSuchFunctionException;

@XmlRootElement(name="double-algorithm")
public class DoubleAlgorithm extends ExtendedAlgorithm{

	public DoubleAlgorithm(BaseAlgorithm baseAlgorithm, BaseAlgorithm secondaryBaseAlgorithm) {
		super("DoubleAlgorithm",baseAlgorithm, secondaryBaseAlgorithm);
	}
	
	public DoubleAlgorithm() {
		//default double algorithm (to avoid MarshalExepction)
		super("DoubleAlgorithm",new CaesarCipher(), new MultiplicativeCipher());
	}

	@Override
	public void encrypt(FileHolder fileHolder) throws IOException, NoSuchFunctionException {
		baseAlgorithm.execute(fileHolder, "Encryption");
		secondaryBaseAlgorithm.execute(fileHolder, "Encryption");
	}

	@Override
	public void decrypt(FileHolder fileHolder) throws IOException, NoSuchFunctionException {
		secondaryBaseAlgorithm.execute(fileHolder, "Decryption");
		baseAlgorithm.execute(fileHolder, "Decryption");
	}
	
	@Override
	public void execute(FileHolder fileHolder , String choice) throws IOException, NoSuchFunctionException {
		if(choice.equals("Encryption"))
			encrypt(fileHolder);
		else
			decrypt(fileHolder);
	}

}
