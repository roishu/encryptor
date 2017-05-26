package com.apache.jaxb;

import java.io.IOException;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import com.apache.ciphers.BaseAlgorithm;
import com.apache.ciphers.CaesarCipher;
import com.apache.ciphers.Key;
import com.apache.ciphers.MultiplicativeCipher;
import com.apache.ciphers.XORCipher;

@XmlRegistry
public class ObjectFactory {

	private final static QName _Cipher_QNAME = 
			new QName("http://com.apace.jaxb/doublealgorithm-jaxb", "cipher");
	private final static QName _SecondaryCipher_QNAME = 
			new QName("http://com.apace.jaxb/doublealgorithm-jaxb", "secondary-cipher");
	private final static QName _Key_QNAME = 
			new QName("http://com.apace.jaxb/doublealgorithm-jaxb", "key");


	public ObjectFactory() {
	}

	public DoubleAlgorithmJAXB createDoubleAlgorithm() {
		return new DoubleAlgorithmJAXB();
	}

	   @XmlElementDecl(namespace = "http://www.example.org/customer", name = "key")
	    public JAXBElement<Key> createKey(Key value) {
	        return new JAXBElement<Key>(_Key_QNAME, Key.class, null, value);
	    }


}
