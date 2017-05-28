package com.apache.encryptor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

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
					model.execute();
				} catch (JAXBException e1) {
					EncryptorController.logger.error(e1.getMessage());
				}
			}
		}
	};
	
	public final static Logger logger = Logger.getLogger(EncryptorController.class);

	public EncryptorController(){
		view = new EncryptorMenu();
		view.executeButton.addActionListener(executeListener);
	}

	   public static void main(String[] args) {
		   EncryptorController controller = new EncryptorController();
		   }

}