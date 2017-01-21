package com.apache.encryptor;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.mockito.Mockito.*;

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

        File file = new File(path);
        System.out.println(file.isDirectory());
        while (file.isDirectory() || !file.exists()){
        	path = JOptionPane.showInputDialog(frame, "Enter Valid Path :");
        	file = new File(path);
        }

                System.out.println("Path is : " + path +"\nIsDirectory: "+file.isDirectory());
//        String choose = (String) JOptionPane.showInputDialog(frame, 
//                "What do you wish to do ?",
//                "Functions:",
//                JOptionPane.QUESTION_MESSAGE, null, functions, functions[0]);
//            System.out.printf("Function is %s.\n", choose);

        
        
    }
}
