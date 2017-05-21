package com.apache.ciphers;

import java.io.IOException;

import com.apache.encryptor.FileHolder;

public abstract class Algorithm {
	public Algorithm(String name) {
		super();
		this.name = name;
	}

	private String name;

	public String getName() {
		return name;
	}
	
	public abstract void execute(FileHolder fileHolder , String choice) throws IOException;
	

}
