package com.apache.ciphers;

import java.io.IOException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class CaesarCipher extends BaseAlgorithm {
		
	public CaesarCipher() {
		super("CaesarCipher");
	}

	@Override
	public byte encryptByte(byte b , Key key) throws IOException {
		return (byte)(b + key.key);
	}

	@Override
	public byte decryptByte(byte b, Key key) throws IOException {
		return (byte)(b - key.key);
	}

	@Override
	public void checkValidKey(){
		/*
		 * nothing to check
		 */
	}
}
