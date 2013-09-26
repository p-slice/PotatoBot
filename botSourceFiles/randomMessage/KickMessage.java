package randomMessage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;

public class KickMessage {

	static Charset charset = Charset.defaultCharset();
	final static Random rand = new Random();
	
	public static String getKickReason(){
		
		Path filePath = new File("C:\\PotatoBot Files\\KickMessage.txt").toPath();
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
	public static String getAntiKickReason(){
		
		Path filePath = new File("C:\\PotatoBot Files\\AntiKickMessage.txt").toPath();
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
