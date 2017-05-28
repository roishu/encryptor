package com.apache.jaxb;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.apache.encryptor.FileHolder;

@XmlRootElement(name="directory-document")
@XmlAccessorType(XmlAccessType.FIELD)
public class DircetoryDocumentJAXB {

	@XmlElement(name="file")
	private ArrayList<FileHolder> files = new ArrayList<FileHolder>();
	
	public DircetoryDocumentJAXB(ArrayList<FileHolder> names){
		files = names;
	}
	
	public DircetoryDocumentJAXB(){
		files = new ArrayList<FileHolder>();
	}
	
}
