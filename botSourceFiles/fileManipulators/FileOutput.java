package fileManipulators;

import java.io.*;

import org.pircbotx.Channel;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.WaitForQueue;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

@SuppressWarnings("rawtypes")
public class FileOutput extends ListenerAdapter {
	
	 static String fileName;
	 static String fullFileName;
	 static String transferMessage;
	
	public void onPrivateMessage(PrivateMessageEvent event) throws InterruptedException{
		
		String message = event.getMessage();
		String masterName = event.getUser().getLogin().toString();
		String masterList = sourceFiles.UserList.getMasterList();
		
		if (masterList.contains(masterName)){
			
			String[] messageSplit = message.split("[ ]");
			
			if (message.startsWith(".text")){
				fileName = messageSplit[1] + ".txt";
				fullFileName = "C:\\PotatoBot Files\\" + fileName;
				transferMessage = messageSplit[2] + " ";
				for(int i = 3; i < messageSplit.length; i++){
					transferMessage = transferMessage + messageSplit[i] + " ";
				}
				File f = new File(fullFileName);
				if(!f.exists()){
					event.respond("File location doesn't exist. Create new file?");
					WaitForQueue queue = new WaitForQueue(event.getBot());
					while (true) {
						MessageEvent currentEvent = queue.waitFor(MessageEvent.class);
						if (currentEvent.getMessage().equalsIgnoreCase(".No")){
							event.respond("Aborting save.");
							queue.close();
							return;
						}
						if (currentEvent.getMessage().equalsIgnoreCase(".Yes")){
							event.respond("Saving file...");
							try {
					            FileWriter fileWriter = new FileWriter(fullFileName, true);

					            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

					            bufferedWriter.append(transferMessage);
					            bufferedWriter.close();
					        }
					        catch(IOException ex) {
					            System.out.println("Error writing to file '" + fileName + "'");
					        }
							queue.close();
					        return;							
						}
					}
				}
				else {
					event.respond("Saving file...");
					try {
			            FileWriter fileWriter = new FileWriter(fullFileName, true);

			            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			            bufferedWriter.newLine();
			            bufferedWriter.append(transferMessage);
			            bufferedWriter.close();
			        }
			        catch(IOException ex) {
			            System.out.println("Error writing to file '" + fileName + "'");
			        }
			        return;	
				}
			}			
		}
	}
	@SuppressWarnings({ "resource" })
	public void onMessage(MessageEvent event) throws InterruptedException{
		
		String message = event.getMessage();
		String masterName = event.getUser().getLogin().toString();
		String masterList = sourceFiles.UserList.getMasterList();
		String ownNick = event.getBot().getNick().toString();
		String userName = event.getUser().getNick().toString();
		Channel chanName = event.getChannel();
		if (masterList.contains(masterName)){
			
			String[] messageSplit = message.split("[ ]");
			
			if (message.startsWith(ownNick + ", save")){
				fileName = messageSplit[2] + ".txt";
				fullFileName = "C:\\PotatoBot Files\\" + fileName;
				transferMessage = messageSplit[3] + " ";
				for(int i = 4; i < messageSplit.length; i++){
					transferMessage = transferMessage + messageSplit[i] + " ";
				}
				File f = new File(fullFileName);
				if(!f.exists()){
					event.getBot().sendMessage(chanName, "File location doesn't exist. Create new file?");
					String commandGiver = userName;
					WaitForQueue queue = new WaitForQueue(event.getBot());
					while (true) {
						MessageEvent currentEvent = queue.waitFor(MessageEvent.class);
						if (!userName.equals(commandGiver)){
							return;
						}
						if (currentEvent.getMessage().equalsIgnoreCase("No")){
							event.getBot().sendMessage(chanName, "Aborting save.");
							queue.close();
							return;
						}
						if (currentEvent.getMessage().equalsIgnoreCase("Yes")){
							try {
					            FileWriter fileWriter = new FileWriter(fullFileName, true);

					            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

					            bufferedWriter.append(transferMessage);
					            bufferedWriter.close();
					            event.getBot().sendMessage(chanName, "Saved to '" + fileName + "'");
					        }
					        catch(IOException ex) {
					            System.out.println("Error writing to file '" + fileName + "'");
					        }
							queue.close();
					        return;
						}
					}
				}
				else {
					event.getBot().sendMessage(chanName, "Saved to '" + fileName + "'");
					try {
			            FileWriter fileWriter = new FileWriter(fullFileName, true);

			            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			            bufferedWriter.newLine();
			            bufferedWriter.append(transferMessage);
			            bufferedWriter.close();
			        }
			        catch(IOException ex) {
			            System.out.println("Error writing to file '" + fileName + "'");
			        }
			        return;	
				}
			}
		}
	}
}
