package commandFiles;

import java.io.File;

import org.pircbotx.*;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.WaitForQueue;
import org.pircbotx.hooks.events.MessageEvent;

@SuppressWarnings("rawtypes")
public class FileMaster extends ListenerAdapter{
	
	static boolean editToggle = false;
	static String editFile;
	
	static int n;
	
    public void onMessage(MessageEvent event) throws Exception {
    	
    	String userName = event.getUser().getNick().toString();
        String ownNick = event.getBot().getNick().toString();
        
        boolean isMaster = sourceFiles.UserList.isMaster(userName);
        
		String message = event.getMessage();
		String[] messageSplit = message.split("[ ]");
		String command = messageSplit[1];
		String fileName = messageSplit[2];
		
		Channel chanName = event.getChannel();
		
		if (isMaster == true
				&& message.startsWith(ownNick + ",")){
			if (command.equalsIgnoreCase("Read")){
				File f = new File("C:\\PotatoBot Files\\" + fileName + ".txt");				
				if(!f.exists()){
					event.getBot().sendMessage(chanName, "File doesn't exist!");
					return;
				}
				if (messageSplit.length == 3){
					String[] wholeText = textFiles.FileReader.wholeText(fileName);
					if (wholeText.length > 3){
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
								for(int i = 0; i < wholeText.length; i++){
									int q = i + 1;
									event.getBot().sendMessage(chanName, "Line " + q + ": " + wholeText[i]);
								}
								return;
							}
						}
					}
					else {
						for (int i = 0; i < wholeText.length; i++){
						event.getBot().sendMessage(chanName, wholeText[i]);
						}
					}
				}
				if (messageSplit.length == 4){
					int s = Integer.parseInt(messageSplit[3]) - 1;
					int q = s + 1;
					String lineText = textFiles.FileReader.lineText(fileName, s);
					event.getBot().sendMessage(chanName, "Line " + q + " from '" + fileName + ".txt': " + lineText);
					return;
				}
			}
			if (command.equalsIgnoreCase("Link")){
				String link = textFiles.FileReader.link(messageSplit[2]);
				event.getBot().sendMessage(chanName, link);
				return;
			}
			if (command.equalsIgnoreCase("Save")){
				String saveText = messageSplit[3] + " ";
				for(int i = 4; i < messageSplit.length; i++){
					saveText = saveText + messageSplit[i] + " ";
				}
				File f = new File("C:\\PotatoBot Files\\" + fileName + ".txt");
				if(!f.exists()){
					event.getBot().sendMessage(chanName, "File location doesn't exist. Create new file?");
					WaitForQueue queue = new WaitForQueue(event.getBot());
					while (true) {
						MessageEvent currentEvent = queue.waitFor(MessageEvent.class);
						if (currentEvent.getMessage().equalsIgnoreCase("No")){
							event.getBot().sendMessage(chanName, "Aborting save.");
							queue.close();
							return;
						}
						if (currentEvent.getMessage().equalsIgnoreCase("Yes")){
							event.getBot().sendMessage(chanName, "Saved new file, " + fileName + ".txt.");
							textFiles.FileOutput.saveText(fileName, saveText);
							queue.close();
							return;
						}
					}
				}
				else {
					event.getBot().sendMessage(chanName, "Saved to '" + fileName + ".txt'.");
					textFiles.FileOutput.saveText(fileName, saveText);
				}
			}
			if (command.equalsIgnoreCase("Edit")){
				n = Integer.parseInt(messageSplit[3]) - 1;
				int q = n + 1;
				
				event.getBot().sendMessage(chanName, "Editing line " + q + " of file '" + fileName + ".txt'. Proceed?");
				WaitForQueue queue = new WaitForQueue(event.getBot());
				while (true) {
					MessageEvent currentEvent = queue.waitFor(MessageEvent.class);
					if (currentEvent.getMessage().equalsIgnoreCase("No")){
						event.getBot().sendMessage(chanName, "Edit cancelled.");
						queue.close();
						return;
					}
					if (currentEvent.getMessage().equalsIgnoreCase("Yes")){
						
						event.getBot().sendMessage(chanName, "Now editing.");
						editToggle = true;
						editFile = fileName;
						queue.close();
						return;
					}
				}
			}
			if (command.equalsIgnoreCase("Replace")
					&& messageSplit[3].equalsIgnoreCase("with")
					&& editToggle == true){				
				String newLine = textFiles.FileEditor.replaceText(editFile, n, messageSplit[2], messageSplit[4]);			
				event.getBot().sendMessage(chanName, "Now says: " + newLine);
				return;
			}
			if (command.equalsIgnoreCase("Add")
					&& editToggle == true){
				String newText = messageSplit[2];
				for(int m = 3; m < messageSplit.length; m++){
					newText = newText + " " + messageSplit[m];
				}
				String newLine = textFiles.FileEditor.addText(editFile, n, newText);
				event.getBot().sendMessage(chanName, "Now says: " + newLine);
			}
			if (command.equalsIgnoreCase("Finish")
					&& messageSplit[2].equalsIgnoreCase("edit")
					&& editToggle == true){
				event.getBot().sendMessage(chanName, "Edit closed!");
				editToggle = false;
				return;
			}
		}
    }
}