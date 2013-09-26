package commandFiles;

import org.pircbotx.*;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

@SuppressWarnings("rawtypes")
public class PublicMaster extends ListenerAdapter{
	
    public void onMessage(MessageEvent event) throws Exception {
    	
        String masterList = sourceFiles.UserList.getMasterList();	
		String message = event.getMessage();
		String masterName = event.getUser().getLogin().toString();
		Channel chanName = event.getChannel();
		String[] messageSplit = message.split("[ ]");
		String command = messageSplit[1];
		String ownNick = event.getBot().getNick().toString();
		
		if (masterList.contains(masterName) && message.startsWith(ownNick + ",")){
			
			if (command.equalsIgnoreCase("go") && messageSplit[2].equalsIgnoreCase("away")) {
				String quitMessage = randomMessage.QuitMessage.getQuitMessage();
				event.getBot().sendMessage(chanName, quitMessage);
				event.getBot().quitServer("Bai bai");
			}
			if (command.equalsIgnoreCase("kick")) {
				String kickReason = randomMessage.KickMessage.getKickReason();
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
		if (!masterList.contains(masterName)){
			
			if (message.equalsIgnoreCase("PotatoBot, go away")) {
				String antiQuit = randomMessage.AntiQuitMessage.getAntiQuitMessage();
				event.getBot().sendMessage(chanName, antiQuit);
				return;
			}
			if (message.startsWith("PotatoBot, kick")) {
				String antiKick = randomMessage.KickMessage.getAntiKickReason();
				event.getBot().sendMessage(chanName, antiKick);
			}
			if (message.equalsIgnoreCase("PotatoBot, leave")){
				String antiQuit = randomMessage.AntiQuitMessage.getAntiQuitMessage();
				event.getBot().sendMessage(chanName, antiQuit);
				return;
			}
			if (message.startsWith("PotatoBot, join")) {
				String antiJoin = randomMessage.GenericMessage.getAntiJoinMessage();
				event.getBot().sendMessage(chanName, antiJoin);
				return;
			}
			if (message.startsWith("PotatoBot, leave")) {
				String antiQuit = randomMessage.AntiQuitMessage.getAntiQuitMessage();
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