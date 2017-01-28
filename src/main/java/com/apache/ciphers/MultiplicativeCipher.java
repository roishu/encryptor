package com.apache.ciphers;

import java.io.IOException;

import com.apache.encryptor.CryptographicUtilities;

public class MultiplicativeCipher extends Algorithm implements CryptographicUtilities{

	public MultiplicativeCipher() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String encrypt(String str, int keyLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String decrypt(String str, int keyLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected byte encryptByte(byte b, int idx, Key key) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected byte decryptByte(byte b, int idx, Key key) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

}
