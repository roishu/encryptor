package com.apache.ciphers;

import java.io.IOException;

import javax.swing.plaf.synth.SynthSeparatorUI;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.apache.exception.IllegalKeyException;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@XmlSeeAlso({BaseAlgorithm.class})
public class MultiplicativeCipher extends BaseAlgorithm{

	public MultiplicativeCipher() {
		super("MultiplicativeCipher");
		keyFix();
	}

	private void keyFix() {
		while(key.key == 0 || ((key.key & 1) == 0)){
		key = new Key ((byte)((random.nextInt(Byte.MAX_VALUE + 1)
                + Byte.MIN_VALUE/2) * 2 + 1));
		}
			
	}

	@Override
	public byte encryptByte(byte b, Key key) throws IOException {
		return (byte) (b * key.key);
	}

	@Override
	public byte decryptByte(byte b, Key key) throws IOException {
		 for (int i = Byte.MIN_VALUE; i <= Byte.MAX_VALUE; i++) {
	            if ((byte)(key.key * i) == 1) {
	            	key = new Key((byte)i);
	            	break;
	            }
	        }
		 return (byte) (b * key.key);
	}
	
	@Override
	public void checkValidKey() throws IllegalKeyException{
		if (key.key == 0 || ((key.key & 1) == 0)) throw new IllegalKeyException("illegal key error.");
	}

}
