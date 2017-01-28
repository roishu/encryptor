package com.apache.ciphers;

import java.io.IOException;

import lombok.NonNull;

public abstract class ExtendedAlgorithm {
	@NonNull
	protected BaseAlgorithm baseAlgorithm;
	protected BaseAlgorithm secondaryBaseAlgorithm;
	
    protected abstract byte encryptByte(byte b, Key key)
            throws IOException;

    protected abstract byte decryptByte(byte b, Key key)
            throws IOException;
	

}
