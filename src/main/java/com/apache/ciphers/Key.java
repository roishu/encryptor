package com.apache.ciphers;

import lombok.NonNull;

import java.io.Serializable;
import java.security.SecureRandom;

/**
 * Serializable class that holds a key/keys
 */
public class Key implements Serializable {
    /**
	 * 
	 */
    public final Byte key;

    /**
     * Construct a Key object with only one valid key
     * @param key only key
     */
    public Key(byte key) {
        this.key = key;
    }
    
    public Key() {
        this.key = (byte)(new SecureRandom().nextInt(2*Byte.MAX_VALUE + 2) + Byte.MIN_VALUE); 
    }

}
