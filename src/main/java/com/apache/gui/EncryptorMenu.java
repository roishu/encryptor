package com.apache.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import com.apache.gui.FileChooserTest.OpenL;
import com.apache.gui.FileChooserTest.SaveL;

import javax.swing.BorderFactory;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
/**
 * @author  Roi
 * @created January 28, 2017
 */
public class EncryptorMenu extends JFrame 
{
	static EncryptorMenu theexample1;
	private JPanel fileChooserPanel;
	private JPanel selectionPanel , encryptionPanel , decriptionPanel;
	private JLabel lbLabel1;
	private JLabel lbLabel2;
	private JComboBox jcmbFunction , jcmbAlgorithm , jcmbCipher1 , jcmbCipher2 ;
	private JComboBox cmbCombo1;
	private static final String[] dataCombo0 = { "Encryption", "Decryption"};
	private static final String []dataCombo1 = { "ReverseAlgorithm" , "DoubleAlgorithm" , "SplitAlgorithm" };
	private static final String []dataCombo2 = { "CaesarCipher" , "MultiplicativeCipher" , "XORCipher" };
	private JTextArea console;
	private JScrollPane consolePanel ;
	private JButton btBut1 = new JButton("Accept");
	private JButton browseButton = new JButton("Browse");
	private final JFileChooser fc = new JFileChooser();
	private JTextArea filename = new JTextArea(""), dir = new JTextArea();

	public EncryptorMenu() 
	{ 
		fileChooserInit();
		setLayout(new GridLayout(3, 1));
		selectionPanel = new JPanel();
		selectionPanel.setLayout(new GridLayout(2, 1));
		selectionPanel.add(fileChooserPanel);
		jcmbFunction = new JComboBox<>(dataCombo0);
		jcmbFunction.addActionListener(FunctionActionListener);
		selectionPanel.add(jcmbFunction);
		selectionPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

		encryptionPanel = new JPanel();
		jcmbAlgorithm = new JComboBox<>(dataCombo1);
		encryptionPanel.add(jcmbAlgorithm);
		encryptionPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

		console = new JTextArea( 12, 40 ) ;
		console.setText(">>>Welcome!\n");
		consolePanel = new JScrollPane(console) ;
		consolePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		//consolePanel.add(console);
		console.setLineWrap( true ) ;
		console.setWrapStyleWord( true ) ;
		consolePanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		

		setDefaultCloseOperation( EXIT_ON_CLOSE );
		add(selectionPanel);
		add(encryptionPanel);
		add(consolePanel);
		pack();
		setSize(500,500);
		setVisible( true );
		//setResizable(false);
	} 

	private void fileChooserInit() {
		// TODO Auto-generated method stub
		fileChooserPanel = new JPanel();
		browseButton.addActionListener(browseActionListener);
	    //dir.setEditable(false);
	    //filename.setEditable(false);
	    
	    fileChooserPanel.add(browseButton);
	    fileChooserPanel.add(filename);
	    filename.setSize(100, 20);
	}
	
	private ActionListener browseActionListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		      JFileChooser c = new JFileChooser();
		      c.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		      // Demonstrate "Open" dialog:
		      int rVal = c.showOpenDialog(EncryptorMenu.this);
		      if (rVal == JFileChooser.APPROVE_OPTION) {
		        filename.setText(c.getSelectedFile().getName());
		        dir.setText(c.getCurrentDirectory().toString());
		      }
		      if (rVal == JFileChooser.CANCEL_OPTION) {
		        filename.setText("");
		        dir.setText("");
		      }
		}
	};

	private ActionListener FunctionActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

			String s = (String) jcmbFunction.getSelectedItem();//get the selected item
			console.setText(s);
			switch (s) {//check for a match
			case "Encryption":

				break;
			case "Decryption":

				break;
			}
		}
	};

} 
