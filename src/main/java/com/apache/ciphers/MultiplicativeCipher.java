package com.apache.ciphers;

import java.io.IOException;

import com.apache.encryptor.CryptographicUtilities;

public class MultiplicativeCipher extends BaseAlgorithm{

	public MultiplicativeCipher() {
		super();
		keyFix();
	}

	private void keyFix() {
		// TODO Auto-generated method stub
		while(key.key == 0 || ((key.key & 1) == 0)){
		key = new Key ((byte)((random.nextInt(Byte.MAX_VALUE + 1)
                + Byte.MIN_VALUE/2) * 2 + 1));
		}
			
	}

	@Override
	public byte encryptByte(byte b, Key key) throws IOException {
		// TODO Auto-generated method stub
		return (byte) (b * key.key);
	}

	@Override
	public byte decryptByte(byte b, Key key) throws IOException {
		// TODO Auto-generated method stub
		 for (int i = Byte.MIN_VALUE; i <= Byte.MAX_VALUE; i++) {
	            if ((byte)(key.key * i) == 1) {
	            	//System.out.println("---KEY FOUND--- " + i);
	            	key = new Key((byte)i);
	            	break;
	            }
	        }
		 
		 return (byte) (b * key.key);
	}

}
