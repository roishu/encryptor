package com.apache.ciphers;

import com.apache.encryptor.CryptographicUtilities;

public class XORCipher implements CryptographicUtilities{

	@Override
	public String encrypt(String str, int keyLength) {
		char[] key = {'K', 'C', 'Q'}; //Can be any chars, and any length array
		StringBuilder output = new StringBuilder();
		
		for(int i = 0; i < str.length(); i++) {
			output.append((char) (str.charAt(i) ^ key[i % key.length]));
		}
 		
		return output.toString();
	}

	@Override
	public String decrypt(String str, int keyLength) {
		return encrypt( str,  keyLength); //same as encryption
	}

}
