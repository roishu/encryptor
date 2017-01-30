package com.apache.ciphers;

import java.io.IOException;

import com.apache.encryptor.FileHolder;

import lombok.NonNull;

public abstract class ExtendedAlgorithm extends Algorithm {
	
	public ExtendedAlgorithm(String name,BaseAlgorithm baseAlgorithm, BaseAlgorithm secondaryBaseAlgorithm) {
		super(name);
		this.baseAlgorithm = baseAlgorithm;
		this.secondaryBaseAlgorithm = secondaryBaseAlgorithm;
	}
	
	public ExtendedAlgorithm(String name,BaseAlgorithm baseAlgorithm) {
		super(name);
		this.baseAlgorithm = baseAlgorithm;
		this.secondaryBaseAlgorithm = null;
	}

	@NonNull
	protected BaseAlgorithm baseAlgorithm;
	protected BaseAlgorithm secondaryBaseAlgorithm;
	
    public abstract void encrypt(FileHolder fileHolder)
            throws IOException;

    public abstract void decrypt(FileHolder fileHolder)
            throws IOException;
	

}
