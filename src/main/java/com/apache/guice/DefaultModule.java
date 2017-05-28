package com.apache.guice;

import java.io.File;

import javax.xml.bind.JAXBException;

import com.apache.ciphers.BaseAlgorithm;
import com.apache.ciphers.CaesarCipher;
import com.apache.ciphers.ExtendedAlgorithm;
import com.apache.ciphers.ReverseAlgorithm;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

public class DefaultModule extends AbstractModule {
	/*
	 * (non-Javadoc)
	 * @see com.google.inject.AbstractModule#configure()
	 * this class will create a default EncryptorModel
	 * constructer :
	 * EncryptorThreadPoolModel
	 * File folder
	 * @Named("AlgorithmName") String algorithm
	 * @Named("Cipher") String cipher1
	 */
	@Override
	protected void configure() {
		//empty file
		bind(File.class).toInstance(new File("logs\\emptyFile"));
		//algorithm -> reverseAlgorithm
		bind(ExtendedAlgorithm.class).annotatedWith(Names.named("Algorithm"))
		.toInstance(new ReverseAlgorithm(new CaesarCipher()));
		//cipher -> CaesarCipher
		bind(BaseAlgorithm.class).annotatedWith(Names.named("Cipher"))
		.toInstance(new CaesarCipher());
	}    
}