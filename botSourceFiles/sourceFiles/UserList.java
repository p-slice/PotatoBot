package sourceFiles;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class UserList {
	
	static Charset charset = Charset.defaultCharset();
	final static Random rand = new Random();
    
	public static String getMasterList(){
			
		Path filePath = new File("C:\\PotatoBot Files\\MasterList.txt").toPath();
		List<String> stringList = null;
		try {
			stringList = Files.readAllLines(filePath, charset);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String response = stringList.toString();
		return response;
	}
}