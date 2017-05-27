package com.apache.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import com.apache.gui.FileChooserTest.OpenL;
import com.apache.gui.FileChooserTest.SaveL;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LinearGradientPaint;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Enumeration;

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
	private JPanel selectionPanel , encryptorPanel;
	private JLabel lbLabel1;
	private JLabel lbLabel2;
	private JComboBox jcmbFunction , jcmbAlgorithm , jcmbCipher1 , jcmbCipher2 ;
	private JComboBox cmbCombo1;
	private static final String []dataCombo1 = { "ReverseAlgorithm" , "DoubleAlgorithm" , "SplitAlgorithm" };
	private static final String []dataCombo2 = { "CaesarCipher" , "MultiplicativeCipher" , "XORCipher" };
	private JScrollPane consolePanel ;
	private JButton btBut1 = new JButton("Accept");
	private JButton browseButton = new JButton("Browse");
	private final JFileChooser fc = new JFileChooser();
	private JTextField filename = new JTextField("",30), dir = new JTextField();
	private ButtonGroup selectionGroup;
	private JPanel encryporOptionPanel;
	private JPanel encryptorExecutePanel;
	//public elements (MVC)
	public JButton executeButton;
	public JButton exportXMLButton;
	public JTextArea console;

	public EncryptorMenu() 
	{ 
		fileChooserInit();
		setLayout(new GridLayout(3, 1));
		//Browse
		selectionPanel = new JPanel();
		selectionPanel.add(fileChooserPanel);
		//Encryption / Decryption - Choose
		selectionPanel.setBorder(BorderFactory.createTitledBorder("Choose File"));
		//Algorithms - Options
		encryptorPanel = new JPanel();
		encryptorPanel.setLayout(new GridLayout(2, 1));
		encryptorPanel.setBorder(BorderFactory.createTitledBorder("Options"));
		encryporOptionPanel = new JPanel();
		encryptorExecutePanel = new JPanel();
		encryporOptionPanel.setLayout(new GridLayout(3, 3));
		jcmbAlgorithm = new JComboBox<>(dataCombo1);
		jcmbAlgorithm.addActionListener(cbActionListener);
		jcmbCipher1 = new JComboBox<>(dataCombo2);
		jcmbCipher2 = new JComboBox<>(dataCombo2);
		encryporOptionPanel.add(new JLabel("Algorithm:"));
		encryporOptionPanel.add(jcmbAlgorithm);
		encryporOptionPanel.add(new JLabel("Cipher:"));
		encryporOptionPanel.add(jcmbCipher1);
		encryporOptionPanel.add(new JLabel("Secondary Cipher:"));
		encryporOptionPanel.add(jcmbCipher2);
		jcmbAlgorithm.setSelectedIndex(-1);
		jcmbCipher1.setSelectedIndex(-1);
		jcmbCipher2.setSelectedIndex(-1);
		jcmbAlgorithm.setEnabled(false);
		jcmbCipher1.setEnabled(false);
		jcmbCipher2.setEnabled(false);
		encryptorPanel.add(encryporOptionPanel);
		executeButton = new JButton("Execute");
		encryptorExecutePanel.add(executeButton);
		exportXMLButton = new JButton("Export algorithm to xml");
		encryptorExecutePanel.add(exportXMLButton);
		encryptorPanel.add(encryptorExecutePanel);
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
		add(encryptorPanel);
		add(consolePanel);
		pack();
		setSize(500,500);
		setVisible( true );
		//setResizable(false);
	} 
	
	public boolean checkBeforeExecution(){
			if (!filename.getText().isEmpty())
				if (jcmbAlgorithm.getSelectedIndex()!=-1){
					if (jcmbAlgorithm.getSelectedIndex()==0 && jcmbCipher1.getSelectedIndex()!=-1)
						return true;
					else if (jcmbAlgorithm.getSelectedIndex()!=0 && jcmbCipher1.getSelectedIndex()!=-1
							&& jcmbCipher2.getSelectedIndex()!=-1)
						return true;
				}
		return false;
	}
	
	public void print(String str){
		console.setText(console.getText()+str+"\n");
	}
	
	public String getAlgorithm(){
		return dataCombo1[jcmbAlgorithm.getSelectedIndex()];
	}
	
	public String getCipher(){
		return dataCombo2[jcmbCipher1.getSelectedIndex()];
	}
	
	public String getSecondaryCipher(){
		if(jcmbCipher2.isEnabled())
		return dataCombo2[jcmbCipher2.getSelectedIndex()];
		return "";
	}
	
	public String getPath(){
		return filename.getText();
	}

	private void fileChooserInit() {
		// TODO Auto-generated method stub
		fileChooserPanel = new JPanel();
		fileChooserPanel.setLayout(new BorderLayout());
		browseButton.addActionListener(browseActionListener);
	    //dir.setEditable(false);
	    //filename.setEditable(false);
	    
	    fileChooserPanel.add(browseButton,BorderLayout.WEST);
	    fileChooserPanel.add(filename,BorderLayout.CENTER);
	    filename.setSize(100, 20);
	}
	
    ActionListener cbActionListener = new ActionListener() {//add actionlistner to listen for change
        @Override
        public void actionPerformed(ActionEvent e) {
    		/**
    		 * { "ReverseAlgorithm" , "DoubleAlgorithm" , "SplitAlgorithm" };
    		 * @param e
    		 */
            int index = jcmbAlgorithm.getSelectedIndex();//get the selected item

            switch (index) {
                case 0: //ReverseAlgorithm
                    jcmbCipher1.setEnabled(true);
                    jcmbCipher2.setEnabled(false);
                    break;
                case 1: //DoubleAlgorithm
                	jcmbCipher1.setEnabled(true);
                	jcmbCipher2.setEnabled(true);
                    break;
                case 2: //SplitAlgorithm
                	jcmbCipher1.setEnabled(true);
                	jcmbCipher2.setEnabled(true);
                    break;

            }
        }
    };
	
	private ActionListener browseActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		      JFileChooser c = new JFileChooser();
		      c.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		      // Demonstrate "Open" dialog:
		      int rVal = c.showOpenDialog(EncryptorMenu.this);
		      if (rVal == JFileChooser.APPROVE_OPTION) {
		        filename.setText(c.getSelectedFile().getPath());
		        dir.setText(c.getCurrentDirectory().toString());
		        jcmbAlgorithm.setEnabled(true);
		      }
		      if (rVal == JFileChooser.CANCEL_OPTION) {
		        filename.setText("");
		        dir.setText("");
		        jcmbAlgorithm.setEnabled(false);
		        jcmbCipher1.setEnabled(false);
		        jcmbCipher2.setEnabled(false);
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
