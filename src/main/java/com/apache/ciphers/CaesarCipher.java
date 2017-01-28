package com.apache.ciphers;

import java.io.IOException;

import com.apache.encryptor.CryptographicUtilities;

public class CaesarCipher extends BaseAlgorithm {
		
	public CaesarCipher() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected byte encryptByte(byte b , Key key) throws IOException {
		// TODO Auto-generated method stub
		return (byte)(b + key.key);
	}

	@Override
	protected byte decryptByte(byte b, Key key) throws IOException {
		// TODO Auto-generated method stub
		return (byte)(b - key.key);
	}
}
