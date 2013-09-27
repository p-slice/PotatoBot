package fileManipulators;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.pircbotx.hooks.ListenerAdapter;

@SuppressWarnings("rawtypes")
public class FileReader extends ListenerAdapter{
	
	static Charset charset = Charset.defaultCharset();
	
	public static String lineText(String fileName, int n){
		fileName = "C:\\PotatoBot Files\\" + fileName + ".txt";
		Path filePath = new File(fileName).toPath();
		List<String> stringList = null;
		try {
			stringList = Files.readAllLines(filePath, charset);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] stringArray = stringList.toArray(new String[]{});
		return stringArray[n];
	}
	
	public static String[] wholeText(String fileName){
		fileName = "C:\\PotatoBot Files\\" + fileName + ".txt";
		Path filePath = new File(fileName).toPath();
		List<String> stringList = null;
		try {
			stringList = Files.readAllLines(filePath, charset);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] stringArray = stringList.toArray(new String[]{});
		return stringArray;
	}
	
	public static String link(String title){
		Path linkPath = new File("C:\\PotatoBot Files\\Links.txt").toPath();
		List<String> stringLinkList = null;
		try {
			stringLinkList = Files.readAllLines(linkPath, charset);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] stringLinkArray = stringLinkList.toArray(new String[]{});
		for(int i = 0; i < stringLinkArray.length; i++){
			if (stringLinkArray[i].startsWith(title)){
				String fullLine = stringLinkArray[i].toString();
				String[] linkSplit = fullLine.split("[ ]");
				String link = linkSplit[1];
				return link;
			}					
		}
		return "No link by that title!";
	}
	
    public static String[] getCommandList(){
    	Charset charset = Charset.defaultCharset();
    	Path filePath = new File("C:\\PotatoBot Files\\CommandList.txt").toPath();
		List<String> stringList = null;
		try {
			stringList = Files.readAllLines(filePath, charset);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] stringArray = stringList.toArray(new String[]{});
		return stringArray;
    }
}