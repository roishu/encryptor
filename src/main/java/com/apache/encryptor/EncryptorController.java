package com.apache.encryptor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

import com.apache.exception.IllegalKeyException;
import com.apache.gui.EncryptorMenu;

public class EncryptorController {
	private EncryptorThreadPoolModel model;
	private EncryptorMenu view;

	private ActionListener executeListener = new ActionListener() {
		final String EMPTY_FIELD ="you need to fill every field before execute Encryption/Decryption!";

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (view.checkBeforeExecution()){
				try {
					model = new EncryptorThreadPoolModel(new File(view.getPath()),
							view.getAlgorithm(),view.getCipher(),view.getSecondaryCipher(),view.console);
					if(!view.getKeyFileName().isEmpty())
						model.setCipherKeysAndExecute(view.getCipher(),view.getSecondaryCipher(),view.getKeyFileName());
					else 
						model.execute();
				} catch (JAXBException | IllegalKeyException | NoSuchAlgorithmException e1) {
					EncryptorController.logger.error(e1.getMessage());
				}
				finally {
					view.exportKeyButton.setEnabled(true);
				}
			}
			else{
				EncryptorController.logger.info(EMPTY_FIELD);
				view.console.setText(view.console.getText()+EMPTY_FIELD);
			}
		}
	};
	
	private ActionListener exportKeyListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(model!=null)
				model.exportKey();
			else
				model.print("you can't export key before your first execution");
		}
	};

	public final static Logger logger = Logger.getLogger(EncryptorController.class);

	public EncryptorController(){
		view = new EncryptorMenu();
		view.executeButton.addActionListener(executeListener);
		view.exportKeyButton.addActionListener(exportKeyListener);
	}

	public static void main(String[] args) {
		EncryptorController controller = new EncryptorController();
	}

}
