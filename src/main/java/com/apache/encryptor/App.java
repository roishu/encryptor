package com.apache.encryptor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.mockito.Mockito.*; //check

import com.apache.ciphers.BaseAlgorithm;
import com.apache.ciphers.CaesarCipher;
import com.apache.ciphers.DoubleCipher;
import com.apache.ciphers.MultiplicativeCipher;
import com.apache.ciphers.ReverseCipher;
import com.apache.ciphers.SplitCipher;
import com.apache.ciphers.XORCipher;
import com.apache.gui.EncryptorMenu;

/**
 * Roi Shukrun
 * T.Z : 313454464
 * M.I : 8079738
 *
 */
public class App 
{
	
	//public static final String[] functions = { "Encryption", "Decryption"};
	public static final BaseAlgorithm[] algorithms = 
		{new CaesarCipher() , new MultiplicativeCipher() , new XORCipher()};
	
    public static void main( String[] args )  throws Exception
    {
    	
    	//my path : C:\Users\Roi\Desktop\desktop-file.txt

    			
      //  final EncryptorMenu menu = new EncryptorMenu();
//        menu.btBut1.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				System.out.println(menu.dataCombo0[menu.cmbCombo0.getSelectedIndex()]);
//				System.out.println(menu.dataCombo1[menu.cmbCombo1.getSelectedIndex()]);
//				try {
//					switch(menu.cmbCombo0.getSelectedIndex()){
//					case 0: algorithms[menu.cmbCombo1.getSelectedIndex()].execute(mFileHolder, 
//							menu.dataCombo0[menu.cmbCombo0.getSelectedIndex()]);
//					case 1: algorithms[menu.cmbCombo1.getSelectedIndex()].execute(mFileHolderDec, 
//							menu.dataCombo0[menu.cmbCombo0.getSelectedIndex()]);
//					}
//					
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
//		});
        
        
        
        
    } 
}
