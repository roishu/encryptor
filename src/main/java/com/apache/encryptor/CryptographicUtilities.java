package com.apache.encryptor;

public interface CryptographicUtilities {
	
	public String encrypt(String str, int keyLength) ;
	
	public String decrypt(String str, int keyLength);


}



/*
 * 
1075
down vote
accepted
Creating a text file (note that this will overwrite the file if it already exists):

try{
    PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
    writer.println("The first line");
    writer.println("The second line");
    writer.close();
} catch (IOException e) {
   // do something
}
Creating a binary file (will also overwrite the file):

byte data[] = ...
FileOutputStream out = new FileOutputStream("the-file-name");
out.write(data);
out.close();
Java 7+ users can use the Files class to write to files:

Creating a text file:

List<String> lines = Arrays.asList("The first line", "The second line");
Path file = Paths.get("the-file-name.txt");
Files.write(file, lines, Charset.forName("UTF-8"));
//Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
Creating a binary file:

byte data[] = ...
Path file = Paths.get("the-file-name");
Files.write(file, data);
//Files.write(file, data, StandardOpenOption.APPEND);
 */
