package commandFiles;

import org.pircbotx.Channel;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

@SuppressWarnings("rawtypes")
public class GeneralMaster extends ListenerAdapter{
	
	public void onMessage(MessageEvent event) throws Exception {
    	
		String message = event.getMessage();
		String[] messageSplit = message.split("[ ]");
		
		String ignorePlayers = sourceFiles.IgnoreMode.getIgnorePlayers();
		String userName = event.getUser().getNick().toString();
		String masterName = event.getUser().getLogin().toString();
		
		Channel chanName = event.getChannel();
		
		if (!ignorePlayers.contains(masterName)){
			
			boolean ignoreCommands = sourceFiles.IgnoreMode.getIgnoreCommands();
			
			
			if (ignoreCommands == false){
				if (message.equalsIgnoreCase(".test")){
					return;
				}
				if (message.toLowerCase().startsWith("potatobot, google")){
					if (messageSplit.length < 3){
						event.getBot().sendMessage(chanName, "You have to tell me what to google!");
						return;
					}
					event.getBot().sendMessage(chanName, "I'm not here for you to be lazy, go do it yourself. https://www.google.com/");
					return;
				}
				if (message.toLowerCase().startsWith("potatobot, solve")){
					if (messageSplit.length < 5){
						event.getBot().sendMessage(chanName, "Not enough params!");
						return;
					}
					double a = Double.parseDouble(messageSplit[2]);
					double b = Double.parseDouble(messageSplit[4]);
					if (b == 0 && messageSplit[3].equals("/")){
						event.getBot().sendMessage(chanName, "Result: undefined");
						return;
					}
					String result = commandFiles.MathBot.solve(messageSplit[3], a, b);
					event.getBot().sendMessage(chanName, result.replaceAll("\\.0\\b", ""));
					return;
				}
				if (message.equalsIgnoreCase(".commands")){
					String [] stringArray = textFiles.FileReader.getCommandList();
					for(int i = 0; i < stringArray.length; i++){
						event.getBot().sendNotice(userName, stringArray[i]);
					}
					return;
				}
				if (message.equalsIgnoreCase(".math help")){
					String [] stringArray = textFiles.FileReader.getMathHelp();
					for(int i = 0; i < stringArray.length; i++){
						event.getBot().sendNotice(userName, stringArray[i]);
					}
				}
				if (message.equalsIgnoreCase(".Version")){
					String[] version = textFiles.FileReader.getBotInfo();
					event.getBot().sendMessage(chanName, "Currently running " + version[6]);
				}
			}
		}
	}
}
