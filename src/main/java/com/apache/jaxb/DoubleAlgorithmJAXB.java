package com.apache.jaxb;

import java.io.IOException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.apache.ciphers.BaseAlgorithm;
import com.apache.ciphers.CaesarCipher;
import com.apache.ciphers.ExtendedAlgorithm;
import com.apache.ciphers.MultiplicativeCipher;
import com.apache.ciphers.XORCipher;
import com.apache.encryptor.FileHolder;
import com.apache.exception.NoSuchFunctionException;

@XmlRootElement(name="double-algorithm")
@XmlAccessorType(XmlAccessType.FIELD)
public class DoubleAlgorithmJAXB {

	
	private CaesarCipher caesarCipher;// = new CaesarCipherJAXB();
	private MultiplicativeCipher multiplicativeCipher;// = new MultiplicativeCipherJAXB();
	private XORCipher xorCipher;// = new XORCipherJAXB();
	@XmlElement(name = "cipher", required = true)
	private String cipher ;
	@XmlElement(name = "secondary-cipher", required = true)
	private String secondaryCipher;

	public DoubleAlgorithmJAXB(String cipher ,String secondaryCipher) {
		this.cipher = cipher;
		this.secondaryCipher = secondaryCipher;
		this.caesarCipher = new CaesarCipher();
		this.multiplicativeCipher = new MultiplicativeCipher();
		this.xorCipher = new XORCipher();
	}

	public DoubleAlgorithmJAXB() {
		//default double algorithm
		this.cipher = "CeasarCipher";
		this.secondaryCipher = "MultiplicativeCipher";
		this.caesarCipher = new CaesarCipher();
		this.multiplicativeCipher = new MultiplicativeCipher();
		this.xorCipher = new XORCipher();
	}

	public String getCipher() {
		return cipher;
	}

	public void setCipher(String cipher) {
		this.cipher = cipher;
	}

	public String getSecondaryCipher() {
		return secondaryCipher;
	}

	public void setSecondaryCipher(String secondaryCipher) {
		this.secondaryCipher = secondaryCipher;
	}

	public void encrypt(FileHolder fileHolder) throws IOException, NoSuchFunctionException {
		if(cipher.equals("CaesarCipher"))
			caesarCipher.execute(fileHolder, "Encryption");
		else if(cipher.equals("MultiplicativeCipher"))
			multiplicativeCipher.execute(fileHolder, "Encryption");
		else if(cipher.equals("XORCipher"))
			xorCipher.execute(fileHolder, "Encryption");
		else;
		if(secondaryCipher.equals("CaesarCipher"))
			caesarCipher.execute(fileHolder, "Encryption");
		else if(secondaryCipher.equals("MultiplicativeCipher"))
			multiplicativeCipher.execute(fileHolder, "Encryption");
		else if(secondaryCipher.equals("XORCipher"))
			xorCipher.execute(fileHolder, "Encryption");
		else;
	}

	public void decrypt(FileHolder fileHolder) throws IOException, NoSuchFunctionException {
		if(secondaryCipher.equals("CaesarCipher"))
			caesarCipher.execute(fileHolder, "Decryption");
		else if(secondaryCipher.equals("MultiplicativeCipher"))
			multiplicativeCipher.execute(fileHolder, "Decryption");
		else if(secondaryCipher.equals("XORCipher"))
			xorCipher.execute(fileHolder, "Decryption");
		else;
		if(cipher.equals("CaesarCipher"))
			caesarCipher.execute(fileHolder, "Decryption");
		else if(cipher.equals("MultiplicativeCipher"))
			multiplicativeCipher.execute(fileHolder, "Decryption");
		else if(cipher.equals("XORCipher"))
			xorCipher.execute(fileHolder, "Decryption");
		else;
	}

	public void execute(FileHolder fileHolder , String choice) throws IOException, NoSuchFunctionException {
		if(choice.equals("Encryption"))
			encrypt(fileHolder);
		else
			decrypt(fileHolder);
	}

}
