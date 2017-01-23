package com.apache.encryptor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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

/**
 * Roi Shukrun
 * T.Z : 313454464
 * M.I : 8079738
 *
 */
public class App 
{
	
	public static final String[] functions = { "Encryption", "Decryption"};
	private FileHolder mFileHolder;
	

	
    public static void main( String[] args )  throws Exception
    {
//		JFrame frame = new JFrame("Path InputDialog Example");
//        String choose = (String) JOptionPane.showInputDialog(frame, 
//                "What do you wish to do ?",
//                "Functions:",
//                JOptionPane.QUESTION_MESSAGE, null, functions, functions[0]);
//            System.out.printf("Function is %s.\n", choose);
//
//            
//        if (choose.equals(functions[0])){
//        	//encryption
//        }
//        else{
//        	//decryption
//        }
		//gets a string to encrypt
//		String str = (JOptionPane.showInputDialog("Input Data to encypt:"));
//		
//		//gets a key 
//		String key = (JOptionPane.showInputDialog("Input the key:"));
//		int keyLength=key.length();
//		
//		
//	
//		
//		//prints encryption
//		String encrypted = CryptographicUtilities.encrypt(str, keyLength);
//		System.out.println("Encrypted:" + encrypted);
//
//		//prints decryption
//		String decrypted = CryptographicUtilities.decrypt(encrypted, keyLength);
//		System.out.println("Decrypted:" + decrypted);
//		
//		//prints key
//		System.out.println("Key:" + key);
    	
        KeyGenerator kg = KeyGenerator.getInstance("DES");
        kg.init(new SecureRandom());
        SecretKey key = kg.generateKey();
        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
        Class spec = Class.forName("javax.crypto.spec.DESKeySpec");
        DESKeySpec ks = (DESKeySpec) skf.getKeySpec(key, spec);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("‪C:\\Users\\Roi\\Documents\\hello.txt"));
        oos.writeObject(ks.getKey());

        Cipher c = Cipher.getInstance("DES/CFB8/NoPadding");
        c.init(Cipher.ENCRYPT_MODE, key);
        CipherOutputStream cos = new CipherOutputStream(new FileOutputStream("C:\\Users\\Roi\\Documents\\hello.txt"), c);
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(cos));
        pw.println("Stand and unfold yourself");
        pw.close();
        oos.writeObject(c.getIV());
        oos.close();
        
    }
}
