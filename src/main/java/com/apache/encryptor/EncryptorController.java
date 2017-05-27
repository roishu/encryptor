package com.apache.encryptor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.apache.gui.EncryptorMenu;

public class EncryptorController {
	private ThreadPoolEncryptor model;
	private EncryptorMenu view;

	private ActionListener executeListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (view.checkBeforeExecution()){
				view.print(view.getPath());
				view.print(view.getAlgorithm());
				view.print(view.getCipher());
				if (!view.getAlgorithm().equals("ReverseAlgorithm"))
					view.print(view.getSecondaryCipher());
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
