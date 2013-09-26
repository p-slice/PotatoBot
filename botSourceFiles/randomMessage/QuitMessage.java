package randomMessage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class QuitMessage {
	
	static Charset charset = Charset.defaultCharset();
	final static Random rand = new Random();
	
	public static String getQuitMessage(){
		
		Path filePath = new File("C:\\PotatoBot Files\\QuitMessage1.txt").toPath();		
		List<String> stringList = null;
		try {
			stringList = Files.readAllLines(filePath, charset);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] stringArray = stringList.toArray(new String[]{});		
		final String response = stringArray[rand.nextInt(stringArray.length)];
		return response;

	}
	public static String getQuitMessage2(){
		
		Path filePath = new File("C:\\PotatoBot Files\\QuitMessage2.txt").toPath();		
		List<String> stringList = null;
		try {
			stringList = Files.readAllLines(filePath, charset);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] stringArray = stringList.toArray(new String[]{});		
		final String response = stringArray[rand.nextInt(stringArray.length)];
		return response;

	}
}
