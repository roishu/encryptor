package com.apache.ciphers;

import lombok.NonNull;

import java.io.Serializable;

/**
 * Serializable class that holds a key/keys
 */
public class Key implements Serializable {
    /**
	 * 
	 */
	@NonNull
    public final Byte key;

    /**
     * Construct a Key object with only one valid key
     * @param key only key
     */
    public Key(byte key) {
        this.key = key;
    }

}
