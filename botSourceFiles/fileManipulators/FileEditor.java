package fileManipulators;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.pircbotx.Channel;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.WaitForQueue;
import org.pircbotx.hooks.events.MessageEvent;

@SuppressWarnings("rawtypes")
public class FileEditor extends ListenerAdapter {
	
	boolean editToggle = false;
	
	Charset charset = Charset.defaultCharset();
	
	String fileName;
	String fullFileName;
	
	@SuppressWarnings("resource")
	public void onMessage(MessageEvent event) throws Exception {
    	
        String masterList = sourceFiles.UserList.getMasterList();
		String masterName = event.getUser().getLogin().toString();
		String userName = event.getUser().getNick().toString();
		String ownNick = event.getBot().getNick().toString();
		String commandGiver = userName;
		
		String message = event.getMessage();		
		String[] messageSplit = message.split("[ ]");
		String command = messageSplit[1];
		
		Channel chanName = event.getChannel();
		
		int n = 0;
		
		if (masterList.contains(masterName)
				&& message.startsWith(ownNick + ",")){
			if (command.equalsIgnoreCase("edit")){
				n = Integer.parseInt(messageSplit[3]) - 1;
				int q = n + 1;			
				fileName = messageSplit[2] + ".txt";
				fullFileName = "C:\\PotatoBot Files\\" + fileName;
				
				event.getBot().sendMessage(chanName, "Editing line " + q + " of file '" + fileName + "'. Proceed?");
				WaitForQueue queue = new WaitForQueue(event.getBot());
				while (true) {
					MessageEvent currentEvent = queue.waitFor(MessageEvent.class);
					if (!userName.equals(commandGiver)){
						return;
					}
					if (currentEvent.getMessage().equalsIgnoreCase("No")){
						event.getBot().sendMessage(chanName, "Edit cancelled.");
						queue.close();
						return;
					}
					if (currentEvent.getMessage().equalsIgnoreCase("Yes")){
						
						event.getBot().sendMessage(chanName, "Now editing.");
						editToggle = true;
						queue.close();
						return;
					}
				}
			}
			
			Path filePath = new File(fullFileName).toPath();		
			List<String> stringList = null;
			try {
				stringList = Files.readAllLines(filePath, charset);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String[] stringArray = stringList.toArray(new String[]{});
			String editLine = stringArray[n];
			
			if (command.equalsIgnoreCase("Replace")
					&& messageSplit[3].equalsIgnoreCase("with")
					&& editToggle == true){
				editLine = editLine.replace(messageSplit[2], messageSplit[4]);
				String content = new String(Files.readAllBytes(filePath), charset);
				content = content.replaceAll(stringArray[n], editLine);
				Files.write(filePath, content.getBytes(charset));
			
				event.getBot().sendMessage(chanName, "Now says: " + editLine);
			}
			if (command.equalsIgnoreCase("Add")
					&& editToggle == true){
				String transferMessage = messageSplit[2];
				for(int m = 3; m < messageSplit.length; m++){
					transferMessage = transferMessage + messageSplit[m] + " ";					
				}
				editLine = editLine.concat(" " + transferMessage);
				String content = new String(Files.readAllBytes(filePath), charset);
				content = content.replaceAll(stringArray[n], editLine);
				Files.write(filePath, content.getBytes(charset));
			
				event.getBot().sendMessage(chanName, "Now says: " + editLine);
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
