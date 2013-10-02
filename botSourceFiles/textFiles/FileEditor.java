package textFiles;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileEditor{
	
	static Charset charset = Charset.defaultCharset();
	
	public static String replaceText(String fileName, int n, String oldWord, String newWord){
		fileName = "C:\\PotatoBot Files\\" + fileName + ".txt";
		Path filePath = new File(fileName).toPath();
		List<String> stringList = null;
		try {
			stringList = Files.readAllLines(filePath, charset);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] stringArray = stringList.toArray(new String[]{});
		String editLine = stringArray[n];
		
		editLine = editLine.replace(oldWord, newWord);
		String content = null;
		try {
			content = new String(Files.readAllBytes(filePath), charset);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		content = content.replaceAll(stringArray[n], editLine);
		try {
			Files.write(filePath, content.getBytes(charset));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return editLine;
	}
	
	public static String addText(String fileName, int n, String newText){
		fileName = "C:\\PotatoBot Files\\" + fileName + ".txt";
		Path filePath = new File(fileName).toPath();
		List<String> stringList = null;
		try {
			stringList = Files.readAllLines(filePath, charset);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] stringArray = stringList.toArray(new String[]{});
		String editLine = stringArray[n];
		
		editLine = editLine.concat(" " + newText);
		String content = null;
		try {
			content = new String(Files.readAllBytes(filePath), charset);
		} catch (IOException e) {
			e.printStackTrace();
		}
		content = content.replaceAll(stringArray[n], editLine);
		try {
			Files.write(filePath, content.getBytes(charset));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return editLine;
	}
}
