package com.apache.encryptor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.apache.ciphers.BaseAlgorithm;
import com.apache.ciphers.CaesarCipher;
import com.apache.ciphers.DoubleAlgorithm;
import com.apache.ciphers.ExtendedAlgorithm;
import com.apache.ciphers.MultiplicativeCipher;
import com.apache.ciphers.ReverseAlgorithm;
import com.apache.ciphers.SplitAlgorithm;
import com.apache.ciphers.XORCipher;

public class EncryptorJAXBManager {
	private	CaesarCipher caesarCipher = new CaesarCipher();
	private	MultiplicativeCipher multiplicativeCipher = new MultiplicativeCipher();
	private	XORCipher xorCipher = new XORCipher();
	private	DoubleAlgorithm doubleAlgorithm;
	private	ReverseAlgorithm reverseAlgorithm;
	private	SplitAlgorithm splitAlgorithm;
	private ExtendedAlgorithm extendedAlgorithm;
	private	BaseAlgorithm[] baseAlgorithms ={caesarCipher,multiplicativeCipher,xorCipher};
	//JAXB
	private JAXBContext caesarCipherContext;
	private JAXBContext multiplicativeCipherContext;
	private JAXBContext xorCipherContext;
	private JAXBContext reverseAlgorithmContext;
	private JAXBContext splitAlgorithmContext;
	private JAXBContext doubleAlgorithmContext;
	private Marshaller marshaller;
	private Unmarshaller caesarUnmarshaller , multiplicativeUnmarshaller , xorUnmarshaller;
	
	public EncryptorJAXBManager(){
		
	}
}
