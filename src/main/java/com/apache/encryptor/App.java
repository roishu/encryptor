package com.apache.encryptor;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Hello world!
 *
 */
public class App 
{
	
	public static final String[] functions = { "Encryption", "Decryption"};

	
    public static void main( String[] args )
    {
        JFrame frame = new JFrame("Path InputDialog Example");
        String path = JOptionPane.showInputDialog(frame, "Path :");
        System.out.println("Path is : " + path);
        //String choose = JOptionPane.showInputDialog(frame, " :");
        
        String choose = (String) JOptionPane.showInputDialog(frame, 
                "What do you wish to do ?",
                "Functions:",
                JOptionPane.QUESTION_MESSAGE, null, functions, functions[0]);

            // favoritePizza will be null if the user clicks Cancel
            System.out.printf("Function is %s.\n", choose);

        
        
    }
}
