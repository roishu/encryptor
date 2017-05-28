package com.apache.ciphers;

import java.io.IOException;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

import com.apache.encryptor.FileHolder;
import com.apache.exception.NoSuchFunctionException;

import lombok.NonNull;

@XmlTransient
@XmlSeeAlso(DoubleAlgorithm.class)
public abstract class ExtendedAlgorithm extends Algorithm {
	@NonNull
	protected BaseAlgorithm baseAlgorithm;
	protected BaseAlgorithm secondaryBaseAlgorithm;
	
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

	public String getBaseAlgorithmName(){
		return baseAlgorithm.getName();
	}
	
	public String getSecondaryBaseAlgorithmName(){
		if(secondaryBaseAlgorithm!=null)
			return secondaryBaseAlgorithm.getName();
		return "NULL";
	}
	
    public abstract void encrypt(FileHolder fileHolder)
            throws IOException, NoSuchFunctionException;

    public abstract void decrypt(FileHolder fileHolder)
            throws IOException, NoSuchFunctionException;
	

}
