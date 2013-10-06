package textFiles;

import java.io.*;
import java.nio.charset.Charset;

public class FileOutput{
	
	 static Charset charset = Charset.defaultCharset();
	 
	 public static void saveText(String fileName, String saveText){
		 fileName = "C:\\PotatoBot Files\\" + fileName + ".txt";
		 try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            
            bufferedWriter.newLine();
            bufferedWriter.append(saveText);
            bufferedWriter.close();
	    }
        catch(IOException ex) {
            System.out.println("Error writing to file '" + fileName + "'");
        }
        return;	
	 }
}
