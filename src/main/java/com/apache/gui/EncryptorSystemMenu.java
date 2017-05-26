package com.apache.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.naming.directory.InvalidAttributeIdentifierException;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EncryptorSystemMenu {
    final static String ENCRYPTION_PANEL = "Encryption";
    final static String DECRYPTION_PANEL = "Decryption";
    final static String ENCRYPTION_AND_DECRYPTION_PANEL = "Encryption And Decryption";
    final static int extraWindowWidth = 100;
	static EncryptorMenu theexample1;
	private JPanel fileChooserEPanel , fileChooserDPanel , fileChooserEDPanel;
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
	private JPanel card1,card2,card3;

    public void addComponentToPane(JFrame frame) {
        final JTabbedPane tabbedPane = new JTabbedPane();
        init();
        //Create the cards
        card1 = createEncyptionPanel();
        //final JPanel card2; // = createDecyptionPanel();
        //final JPanel card3; // = createEncyptionAndDecryptionPanel();

        //insert cards
        tabbedPane.addTab(ENCRYPTION_PANEL, card1);
        tabbedPane.addTab(DECRYPTION_PANEL, card2);
        tabbedPane.addTab(ENCRYPTION_AND_DECRYPTION_PANEL, card3);
        
        tabbedPane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {

				// TODO Auto-generated method stub
				int index = tabbedPane.getSelectedIndex();
				switch(tabbedPane.getSelectedIndex()){
				case 0 : card1 = createEncyptionPanel(); break;
				case 1 : card2 = createDecyptionPanel(); break;
				case 2 : card3 = createEncyptionAndDecryptionPanel(); break;
				}
			}
		});
        
        console.setText(console.getText()+"Hi");
        //apply tabbedPane in pane
        frame.add(tabbedPane);
        frame.add(console);
    }
    
	private void init() {
		// TODO Auto-generated method stub
		selectionPanel = new JPanel();
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
		fileChooserEPanel = new JPanel();
	}

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

    private JPanel createEncyptionPanel() {
    	JPanel card = new JPanel();
    	card.setLayout(new GridLayout(2, 1));
		card.add(fileChooserEPanel);
		card.add(encryptionPanel);
        return card;
	}
    
    private JPanel createDecyptionPanel() {
    	JPanel card = new JPanel();
    	card.setLayout(new GridLayout(3, 1));
		card.add(selectionPanel);
		card.add(encryptionPanel);
		card.add(consolePanel);
        return card;
	}
    
    private JPanel createEncyptionAndDecryptionPanel() {
    	JPanel card = new JPanel();

        return card;
	}

	/**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void runApp() {
        //Create and set up the window.
        JFrame frame = new JFrame("TabDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(2, 1));

        //Create and set up the content pane.
        EncryptorSystemMenu demo = new EncryptorSystemMenu();
        demo.addComponentToPane(frame);

        //Display the window.
        
        frame.pack();
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
    	runApp();
    }
}