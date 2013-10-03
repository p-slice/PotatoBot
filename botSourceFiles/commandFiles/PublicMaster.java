package commandFiles;

import org.pircbotx.*;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

@SuppressWarnings("rawtypes")
public class PublicMaster extends ListenerAdapter{
	
    public void onMessage(MessageEvent event) throws Exception {
    	
    	String message = event.getMessage();
    	String[] messageSplit = message.split("[ ]");
    	String command = messageSplit[1];
    	
        String userName = event.getUser().getNick().toString();
		String ownNick = event.getBot().getNick().toString();
		
		boolean isMaster = sourceFiles.UserList.isMaster(userName);
		
		Channel chanName = event.getChannel();
		
		if (isMaster == true
				&& message.startsWith(ownNick + ",")){
			
			if (command.equalsIgnoreCase("go") && messageSplit[2].equalsIgnoreCase("away")) {
				String quitMessage = textFiles.RandomFileReader.getRandMessage("QuitMessage1");
				event.getBot().sendMessage(chanName, quitMessage);
				event.getBot().quitServer("Bai bai");
			}
			if (command.equalsIgnoreCase("you're") && messageSplit[2].equalsIgnoreCase("fired")) {
				String quitMessage = textFiles.RandomFileReader.getRandMessage("QuitMessage2");
				event.getBot().sendMessage(chanName, quitMessage);
				event.getBot().quitServer("G'day, I say!");
			}
			if (command.equalsIgnoreCase("kick")) {
				String kickReason = textFiles.RandomFileReader.getRandMessage("KickMessage");
				User playerToKick = event.getBot().getUser(messageSplit[2]);
				event.getBot().kick(chanName, playerToKick, kickReason);
			}
			if (command.equalsIgnoreCase("join")) {
				String channelToJoin = messageSplit[2];
				event.getBot().joinChannel(channelToJoin);
				return;
			}
			if (command.equalsIgnoreCase("leave")) {
				String channelToLeave = messageSplit[2];
					if (!channelToLeave.equals("#p_slice"))
						event.getBot().partChannel(event.getBot().getChannel(channelToLeave), "G'day m8.");
				return;
			}
			if (command.equalsIgnoreCase("rename")) {
				String newBotNick = messageSplit[2];
				event.getBot().changeNick(newBotNick);
				return;
			}
		}
		if (isMaster == false){
			
			if (message.equalsIgnoreCase("PotatoBot, go away")) {
				String antiQuit = textFiles.RandomFileReader.getRandMessage("AntiQuitMessage1");
				event.getBot().sendMessage(chanName, antiQuit);
				return;
			}
			if (command.equalsIgnoreCase("you're") && messageSplit[2].equalsIgnoreCase("fired")) {
				String quitMessage = textFiles.RandomFileReader.getRandMessage("AntiQuitMessage2");
				event.getBot().sendMessage(chanName, quitMessage);
				return;
			}
			if (message.startsWith("PotatoBot, kick")) {
				String antiKick = textFiles.RandomFileReader.getRandMessage("AntiKickMessage");
				event.getBot().sendMessage(chanName, antiKick);
			}
			if (message.startsWith("PotatoBot, join")) {
				String antiJoin = textFiles.RandomFileReader.getRandMessage("AntiJoinMessage");
				event.getBot().sendMessage(chanName, antiJoin);
				return;
			}
			if (message.startsWith("PotatoBot, leave")) {
				String antiQuit = textFiles.RandomFileReader.getRandMessage("AntiQuitMessage1");
				event.getBot().sendMessage(chanName, antiQuit);
				return;
			}
			if (message.equalsIgnoreCase("PotatoBot, are we friends?")) {
				event.getBot().sendMessage(chanName, "No, no we aren't.");
				return;
			}
		}
    }
}