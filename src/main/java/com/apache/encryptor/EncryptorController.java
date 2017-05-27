package com.apache.encryptor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.xml.bind.JAXBException;

import com.apache.gui.EncryptorMenu;

public class EncryptorController {
	private ThreadPoolEncryptor model;
	private EncryptorMenu view;

	private ActionListener executeListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (view.checkBeforeExecution()){
				try {
					model = new ThreadPoolEncryptor(new File(view.getPath()),
							view.getAlgorithm(),view.getCipher(),view.getSecondaryCipher(),view.console);
					model.execute(false);
				} catch (JAXBException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	};

	public EncryptorController(){
		view = new EncryptorMenu();
		view.executeButton.addActionListener(executeListener);
	}

	   public static void main(String[] args) {
		   EncryptorController controller = new EncryptorController();
		   }

}
