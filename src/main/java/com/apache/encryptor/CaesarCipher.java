package com.apache.encryptor;

public class CaesarCipher implements CryptographicUtilities {
	
	public CaesarCipher(){
		/*
		 * 
		 */
	}
	
	@Override
	public String encrypt(String str, int keyLength) 
	{
		String encrypted = "";
		for(int i = 0; i < str.length(); i++) 
		{
			int c = str.charAt(i);
			
			if (Character.isUpperCase(c)) 
			{
				//26 letters of the alphabet so mod by 26
				c = c + (keyLength % 26);
				if (c > 'Z')
				c = c - 26;
			} 
			else if (Character.isLowerCase(c)) 
			{
				c = c + (keyLength % 26);
				if (c > 'z')
				c = c - 26;
			}
			encrypted += (char) c;
		}
	return encrypted;
	}
	
	@Override
	public String decrypt(String str, int keyLength) 
	{
		String decrypted = "";
		for(int i = 0; i < str.length(); i++) 
		{
			int c = str.charAt(i);
			if (Character.isUpperCase(c)) 
			{
				c = c - (keyLength % 26);
				if (c < 'A')
				c = c + 26;
			} 
			else if (Character.isLowerCase(c)) 
			{
				c = c - (keyLength % 26);
				if (c < 'a')
				c = c + 26;
			}
			decrypted += (char) c;
		}
		return decrypted;
	}
}
