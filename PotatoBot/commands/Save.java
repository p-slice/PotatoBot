package commands;

import java.io.File;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.WaitForQueue;
import org.pircbotx.hooks.events.MessageEvent;

import source.PotatoBot;

public class Save {

	private static PircBotX bot = PotatoBot.PotatoBot;
	
	@SuppressWarnings("rawtypes")
	public static void save(String chanName, String message, String fileName, String userName) throws InterruptedException{
		
		String[] messageSplit = message.split("[ ]");
		
		String saveText = messageSplit[3] + " ";
		for(int i = 4; i < messageSplit.length; i++){
			saveText = saveText + messageSplit[i] + " ";
		}
		File f = new File("C:\\PotatoBot Files\\" + fileName + ".txt");
		if(!f.exists()){
			bot.sendMessage(chanName, "File location doesn't exist. Create new file?");
			WaitForQueue queue = new WaitForQueue(bot);
			while (true) {
				MessageEvent currentEvent = queue.waitFor(MessageEvent.class);
				boolean isMaster = source.UserList.isMaster(userName);
				if (currentEvent.getMessage().equalsIgnoreCase("No") && isMaster == true){
					bot.sendMessage(chanName, "Aborting save.");
					queue.close();
					return;
				}
				if (currentEvent.getMessage().equalsIgnoreCase("Yes") && isMaster == true){
					bot.sendMessage(chanName, "Saved new file, " + fileName + ".txt.");
					textFiles.FileOutput.saveText(fileName, saveText);
					queue.close();
					return;
				}
			}
		}
		else {
			bot.sendMessage(chanName, "Saved to '" + fileName + ".txt'.");
			textFiles.FileOutput.saveText(fileName, saveText);
		}
	}
}
