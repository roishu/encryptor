package com.apache.ciphers;

import java.io.IOException;

public class XORCipher extends BaseAlgorithm {

	public XORCipher() {
		super("XORCipher");
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte encryptByte(byte b, Key key) throws IOException {
		// TODO Auto-generated method stub
		return (byte) (b ^ key.key);
	}

	@Override
	public byte decryptByte(byte b, Key key) throws IOException {
		// TODO Auto-generated method stub
		return encryptByte( b , key);
	}

}
