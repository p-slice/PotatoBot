package commands;

import java.io.File;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.WaitForQueue;
import org.pircbotx.hooks.events.MessageEvent;

import source.PotatoBot;

public class Read {
	
	//This is incomplete, will be finishing later.
	
	private static PircBotX bot = PotatoBot.PotatoBot;
	
	@SuppressWarnings("rawtypes")
	public static void read(String chanName, String message, String fileName) throws InterruptedException{
		
		String[] messageSplit = message.split("[ ]");
		
		File f = new File("C:\\PotatoBot Files\\" + fileName + ".txt");				
		if(!f.exists()){
			bot.sendMessage(chanName, "File doesn't exist!");
			return;
		}
		if (messageSplit.length == 3){
			String[] wholeText = textFiles.FileReader.wholeText(fileName);
			if (wholeText.length > 3){
				bot.sendMessage(chanName, "That's a fairly big file. Still want me to read it?");
				WaitForQueue queue = new WaitForQueue(bot);
				while (true) {
					MessageEvent currentEvent = queue.waitFor(MessageEvent.class);
					if (currentEvent.getMessage().equalsIgnoreCase("no")){
						bot.sendMessage(chanName, "Good idea.");
						queue.close();
						return;
					}
					if (currentEvent.getMessage().equalsIgnoreCase("yes")){
						bot.sendMessage(chanName, "Well, here goes...");
						queue.close();
						for(int i = 0; i < wholeText.length; i++){
							int q = i + 1;
							bot.sendMessage(chanName, "Line " + q + ": " + wholeText[i]);
						}
						return;
					}
				}
			}
			else {
				for (int i = 0; i < wholeText.length; i++){
					bot.sendMessage(chanName, wholeText[i]);
				}
			}
		}
		if (messageSplit.length == 4){
			int s = Integer.parseInt(messageSplit[3]) - 1;
			int q = s + 1;
			String lineText = textFiles.FileReader.lineText(fileName, s);
			bot.sendMessage(chanName, "Line " + q + " from '" + fileName + ".txt': " + lineText);
			return;
		}
	}
}
