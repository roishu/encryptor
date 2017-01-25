package com.apache.encryptor;

public interface CryptographicUtilities {
	
	public String encrypt(String str, int keyLength) ;
	
	public String decrypt(String str, int keyLength);


}




