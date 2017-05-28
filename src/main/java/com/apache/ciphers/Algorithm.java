package com.apache.ciphers;

import java.io.IOException;

import javax.xml.bind.annotation.XmlTransient;

import com.apache.encryptor.FileHolder;
import com.apache.exception.NoSuchFunctionException;

@XmlTransient
public abstract class Algorithm {
	private String name;
	
	public Algorithm(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public abstract void execute(FileHolder fileHolder , String choice) throws IOException, NoSuchFunctionException;
	

}
