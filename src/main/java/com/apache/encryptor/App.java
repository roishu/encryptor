package com.apache.encryptor;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.mockito.Mockito.*; //check

/**
 * Roi Shukrun
 * T.Z : 313454464
 * M.I : 8079738
 *
 */
public class App 
{
	
	public static final String[] functions = { "Encryption", "Decryption"};
	FileHolder mFileHolder;

	
    public static void main( String[] args )
    {
		JFrame frame = new JFrame("Path InputDialog Example");
        String choose = (String) JOptionPane.showInputDialog(frame, 
                "What do you wish to do ?",
                "Functions:",
                JOptionPane.QUESTION_MESSAGE, null, functions, functions[0]);
            System.out.printf("Function is %s.\n", choose);

            
        if (choose.equals(functions[0])){
        	//Encryption
        }
        else{
        	//Encryption
        }
        
        
    }
}
