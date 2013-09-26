package fileManipulators;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.pircbotx.*;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.WaitForQueue;
import org.pircbotx.hooks.events.MessageEvent;

@SuppressWarnings("rawtypes")
public class FileReader extends ListenerAdapter{
	
    public void onMessage(MessageEvent event) throws Exception {
    	
        String masterList = sourceFiles.UserList.getMasterList();	
		String message = event.getMessage();
		String masterName = event.getUser().getLogin().toString();
		Channel chanName = event.getChannel();
		String[] messageSplit = message.split("[ ]");
		String command = messageSplit[1];
		String ownNick = event.getBot().getNick().toString();
		Charset charset = Charset.defaultCharset();
		
		if (message.startsWith(ownNick + ",")){
			
			if (messageSplit[1].equalsIgnoreCase("link")){
				String title = messageSplit[2].toLowerCase();
			
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
						event.getBot().sendMessage(chanName, link);
						return;
					}					
				}					
			}
			if (masterList.contains(masterName)){
				if (command.equalsIgnoreCase("read")) {
					String fileName = messageSplit[2] + ".txt";
					String fullFileName = "C:\\PotatoBot Files\\" + fileName;
					File f = new File(fullFileName);
					
					if(!f.exists()){
						event.getBot().sendMessage(chanName, "File doesn't exist!");
						return;
					}
					if (message.endsWith("1")
							|| message.endsWith("2")
							|| message.endsWith("3")
							|| message.endsWith("4")
							|| message.endsWith("5")
							|| message.endsWith("6")
							|| message.endsWith("7")
							|| message.endsWith("8")
							|| message.endsWith("9")
							|| message.endsWith("0")){
						int n = Integer.parseInt(messageSplit[3]) - 1;
						
						Path filePath = new File(fullFileName).toPath();
						List<String> stringList = null;
						try {
							stringList = Files.readAllLines(filePath, charset);
						} catch (IOException e) {
							e.printStackTrace();
						}
						String[] stringArray = stringList.toArray(new String[]{});
						if (stringArray[n].isEmpty() == true){
							event.getBot().sendMessage(chanName, "Nothing on that line!");
							return;
						}
						int q = n + 1;
						event.getBot().sendMessage(chanName, "Line " + q + " from '" + fileName + "': " + stringArray[n]);
						return;
					}
					else {
						Path filePath = new File(fullFileName).toPath();
						List<String> stringList = null;
						try {
							stringList = Files.readAllLines(filePath, charset);
						} catch (IOException e) {
							e.printStackTrace();
						}
						String[] stringArray = stringList.toArray(new String[]{});
						
						if (stringArray.length > 3){
							event.getBot().sendMessage(chanName, "That's a fairly big file. Still want me to read it?");
							WaitForQueue queue = new WaitForQueue(event.getBot());
							while (true) {
								MessageEvent currentEvent = queue.waitFor(MessageEvent.class);
								if (currentEvent.getMessage().equalsIgnoreCase("no")){
									event.getBot().sendMessage(chanName, "Good idea.");
									queue.close();
									return;
								}
								if (currentEvent.getMessage().equalsIgnoreCase("yes")){
									event.getBot().sendMessage(chanName, "Well, here goes...");
									queue.close();
									for(int i = 0; i < stringArray.length; i++){
										event.getBot().sendMessage(chanName, stringArray[i]);
									}
									return;
								}
							}
						}
						else {
							for (int i = 0; i < stringArray.length; i++){
							event.getBot().sendMessage(chanName, stringArray[i]);
						}
					}					
				}		
			}
		}
		if (!masterList.contains(masterName)){
			
			if (message.equalsIgnoreCase("PotatoBot, read")) {
				event.getBot().sendMessage(chanName, "Looks like you don't have perms to do that...");
				return;
			}
		}
		}
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