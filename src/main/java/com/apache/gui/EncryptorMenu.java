package com.apache.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
/**
 * @author  Roi
 * @created January 28, 2017
 */
public class EncryptorMenu extends JFrame 
{
static EncryptorMenu theexample1;

JPanel systemMainPanel;

JPanel SelectionPanel;
JLabel lbLabel1;
JLabel lbLabel2;


public JComboBox cmbCombo0;
public JComboBox cmbCombo1;
public static final String[] dataCombo0 = { "Encryption", "Decryption"};
public static final String []dataCombo1 = { "CaesarCipher" , "MultiplicativeCipher" , "XORCipher" };
public JButton btBut1 = new JButton("Accept");

public EncryptorMenu() 
{
   super( "Cryptographic Project Menu" );
   
   systemMainPanel = new JPanel();
   systemMainPanel.setLayout(new GridLayout(0, 1));
   
   SelectionPanel = new JPanel();
   cmbCombo0 = new JComboBox<>(dataCombo0);
   SelectionPanel.add(cmbCombo0);
   setDefaultCloseOperation( EXIT_ON_CLOSE );

   systemMainPanel.add(SelectionPanel);
   setContentPane( systemMainPanel );
   pack();
   setSize(500,500);
   setVisible( true );
} 
} 
