package com.apache.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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

JPanel pnPanel1;

JPanel pnPanel2;
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

   pnPanel1 = new JPanel();
   GridBagLayout gbPanel1 = new GridBagLayout();
   GridBagConstraints gbcPanel1 = new GridBagConstraints();
   pnPanel1.setLayout( gbPanel1 );

   pnPanel2 = new JPanel();
   pnPanel2.setBorder( BorderFactory.createTitledBorder( "Base Interface" ) );
   GridBagLayout gbPanel2 = new GridBagLayout();
   GridBagConstraints gbcPanel2 = new GridBagConstraints();
   pnPanel2.setLayout( gbPanel2 );

   lbLabel1 = new JLabel( "Name"  );
   gbcPanel2.gridx = 0;
   gbcPanel2.gridy = 0;
   gbcPanel2.gridwidth = 4;
   gbcPanel2.gridheight = 1;
   gbcPanel2.fill = GridBagConstraints.NONE;
   gbcPanel2.weightx = 0;
   gbcPanel2.weighty = 0;
   gbcPanel2.anchor = GridBagConstraints.WEST;
   gbPanel2.setConstraints( lbLabel1, gbcPanel2 );
   pnPanel2.add( lbLabel1 );

   lbLabel2 = new JLabel( "Last name"  );
   gbcPanel2.gridx = 0;
   gbcPanel2.gridy = 1;
   gbcPanel2.gridwidth = 4;
   gbcPanel2.gridheight = 1;
   gbcPanel2.fill = GridBagConstraints.NONE;
   gbcPanel2.weightx = 0;
   gbcPanel2.weighty = 0;
   gbcPanel2.anchor = GridBagConstraints.WEST;
   gbPanel2.setConstraints( lbLabel2, gbcPanel2 );
   pnPanel2.add( lbLabel2 );

   
   //String []dataCombo0 = { "Chocolate", "Ice Cream", "Apple Pie" };
   cmbCombo0 = new JComboBox( dataCombo0 );
   gbcPanel2.gridx = 4;
   gbcPanel2.gridy = 0;
   gbcPanel2.gridwidth = 5;
   gbcPanel2.gridheight = 1;
   gbcPanel2.fill = GridBagConstraints.BOTH;
   gbcPanel2.weightx = 1;
   gbcPanel2.weighty = 0;
   gbcPanel2.anchor = GridBagConstraints.NORTH;
   gbPanel2.setConstraints( cmbCombo0, gbcPanel2 );
   pnPanel2.add( cmbCombo0 );

   //String []dataCombo1 = { "Chocolate", "Ice Cream", "Apple Pie" };
   cmbCombo1 = new JComboBox( dataCombo1 );
   gbcPanel2.gridx = 4;
   gbcPanel2.gridy = 1;
   gbcPanel2.gridwidth = 5;
   gbcPanel2.gridheight = 1;
   gbcPanel2.fill = GridBagConstraints.BOTH;
   gbcPanel2.weightx = 1;
   gbcPanel2.weighty = 0;
   gbcPanel2.anchor = GridBagConstraints.NORTH;
   gbPanel2.setConstraints( cmbCombo1, gbcPanel2 );
   pnPanel2.add( cmbCombo1 );
   gbcPanel1.gridx = 2;
   gbcPanel1.gridy = 2;
   gbcPanel1.gridwidth = 11;
   gbcPanel1.gridheight = 6;
   gbcPanel1.fill = GridBagConstraints.BOTH;
   gbcPanel1.weightx = 1;
   gbcPanel1.weighty = 0;
   gbcPanel1.anchor = GridBagConstraints.NORTH;
   gbPanel1.setConstraints( pnPanel2, gbcPanel1 );
   pnPanel1.add( pnPanel2 );

   
   gbcPanel1.gridx = 2;
   gbcPanel1.gridy = 8;
   gbcPanel1.gridwidth = 11;
   gbcPanel1.gridheight = 2;
   gbcPanel1.fill = GridBagConstraints.BOTH;
   gbcPanel1.weightx = 1;
   gbcPanel1.weighty = 0;
   gbcPanel1.anchor = GridBagConstraints.NORTH;
   gbPanel1.setConstraints( btBut1, gbcPanel1 );
   pnPanel1.add( btBut1 );

   setDefaultCloseOperation( EXIT_ON_CLOSE );

   
   setContentPane( pnPanel1 );
   pack();
   setSize(500,500);
   setVisible( true );
} 
} 
