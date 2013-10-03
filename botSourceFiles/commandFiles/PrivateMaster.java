package commandFiles;

import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.PrivateMessageEvent;

@SuppressWarnings("rawtypes")
public class PrivateMaster extends ListenerAdapter {
	
	String chatChanName;
	String actionMessage;
	boolean toggleIgnore = true;
	
	public void onPrivateMessage(PrivateMessageEvent event){
		
		String masterList = sourceFiles.UserList.getMasterList();
		String ownNick = event.getBot().getNick().toString();
		
		String message = event.getMessage();
		String[] messageSplit = message.split("[ ]");
		
		String userName = event.getUser().getNick().toString();
		boolean isMaster = sourceFiles.UserList.isMaster(userName);
		
		if (isMaster == true){
			
			if (message.startsWith(".")){
				
				String command = messageSplit[0];
				
				if (command.equalsIgnoreCase(".test")){
					if (messageSplit.length == 2)
						System.out.println(message.length());
					if (messageSplit.length == 3)
						System.out.println(message.length());
					return;
				}
				if (command.equalsIgnoreCase(".action")){
					actionMessage = messageSplit[1];
					for(int i = 2; i < messageSplit.length; i++){
						actionMessage = actionMessage + " " + messageSplit[i];
					}
					event.getBot().sendAction(chatChanName, actionMessage);
					return;
				}
				if (command.equalsIgnoreCase(".Chat")){
					chatChanName = messageSplit[2];
					event.respond("Now talking in " + chatChanName);
					return;
				}
				if (command.equalsIgnoreCase(".checkignore")){
					boolean ignoreChat = sourceFiles.IgnoreMode.getIgnoreInChat();
					boolean ignorePMs = sourceFiles.IgnoreMode.getIgnorePMs();
					boolean ignoreEvents = sourceFiles.IgnoreMode.getIgnoreEvents();
					boolean ignoreCommands = sourceFiles.IgnoreMode.getIgnoreCommands();
					boolean ignoreRelay = sourceFiles.IgnoreMode.getIgnoreRelay();
					event.respond("Chat: " + ignoreChat);
					event.respond("PMs: " + ignorePMs);
					event.respond("Events: " + ignoreEvents);
					event.respond("Commands: " + ignoreCommands);
					event.respond("Relay: " + ignoreRelay);
					return;
				}
				if (command.equalsIgnoreCase(".Channel")){
					event.respond("Currently talking in " + chatChanName);
					return;
				}
				if (command.equalsIgnoreCase(".ignore")){
					String ignoreType = messageSplit[1];
					if (messageSplit[2].equalsIgnoreCase("Off"))
						toggleIgnore = false;
					if (messageSplit[2].equalsIgnoreCase("On"))
						toggleIgnore = true;
					sourceFiles.IgnoreMode.ignoreMode(ignoreType, toggleIgnore);
					event.respond("Ignore " + messageSplit[1] + " set to: " + messageSplit[2]);
					return;
				}
				if (command.equalsIgnoreCase(".masters")){
					event.respond(masterList);
					return;
				}
				if (command.equalsIgnoreCase(".Whois")){
					User userNick = event.getBot().getUser(messageSplit[1]);
					event.respond("Nickname: " + userNick.getNick().toString());
					event.respond("Login: " + userNick.getLogin().toString());
					event.respond("Real name: " + userNick.getRealName().toString());
					event.respond("Hostmask: " + userNick.getHostmask().toString());
					return;
				}
				return;
			}
			if (message.startsWith(ownNick + ",")){
				
				String command = messageSplit[1];
				
				if (command.equals("quit")){
					event.getBot().quitServer("Bai bai");
				}
				if (command.equals("join")){
					String joinChanName = messageSplit[2];
					event.getBot().joinChannel(joinChanName);
					return;
				}
				if (command.equals("leave")){
					Channel leaveChanName = event.getBot().getChannel(messageSplit[2]);
					event.getBot().partChannel(leaveChanName, "G'day m8.");
					return;
				}
				if (command.equals("kick")){
					String kickReason = textFiles.RandomFileReader.getRandMessage("KickReason");
					User playerToKick = event.getBot().getUser(messageSplit[2]);
					Channel kickChanName = event.getBot().getChannel(messageSplit[3]);
					event.getBot().kick(kickChanName, playerToKick, kickReason);
					return;
				}
				return;
			}
			if (!message.startsWith(".") && !message.startsWith(ownNick + ","))
					event.getBot().sendMessage(chatChanName, message);
			return;			
		}				
	}

}