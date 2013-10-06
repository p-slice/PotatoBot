package source;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class UserList {
	
	static Charset charset = Charset.defaultCharset();
	final static Random rand = new Random();
	
	public static void changeMasterName(String beforeNick, String afterNick){
		
		Path filePath = new File("C:\\PotatoBot Files\\MasterList.txt").toPath();
		List<String> stringList = null;
		try {
			stringList = Files.readAllLines(filePath, charset);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] stringArray = stringList.toArray(new String[]{});
		for(int i = 0; i < stringArray.length; i++){
			if (stringArray[i].contains(beforeNick)){
				String editLine = stringArray[i];
		
				editLine = editLine.replace(beforeNick, afterNick);
				String content = null;
				try {
					content = new String(Files.readAllBytes(filePath), charset);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				content = content.replaceAll(stringArray.toString(), editLine);
				try {
					Files.write(filePath, content.getBytes(charset));
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}
		}
	}
	
	public static boolean isMaster(String userName){
		Path filePath = new File("C:\\PotatoBot Files\\MasterList.txt").toPath();
		List<String> stringList = null;
		try {
			stringList = Files.readAllLines(filePath, charset);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] stringArray = stringList.toArray(new String[]{});
		for(int i = 0; i < stringArray.length; i++){
			if (stringArray[i].contains(userName)){
				return true;
			}
		}
		return false;
	}
	public static boolean isIgnoring(String userName){
		Path filePath = new File("C:\\PotatoBot Files\\IgnoreList.txt").toPath();
		List<String> stringList = null;
		try {
			stringList = Files.readAllLines(filePath, charset);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] stringArray = stringList.toArray(new String[]{});
		for(int i = 0; i < stringArray.length; i++){
			if (stringArray[i].contains(userName)){
				return true;
			}
		}
		return false;
	}
    
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