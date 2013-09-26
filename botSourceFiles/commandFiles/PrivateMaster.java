package commandFiles;

import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.PrivateMessageEvent;

@SuppressWarnings("rawtypes")
public class PrivateMaster extends ListenerAdapter {
	
	String chatChanName;
	String actionMessage;
	
	public void onPrivateMessage(PrivateMessageEvent event){
		
		String masterName = event.getUser().getLogin().toString();
		String masterList = sourceFiles.UserList.getMasterList();
		String ownNick = event.getBot().getNick().toString();
		
		String message = event.getMessage();
		String[] messageSplit = message.split("[ ]");
		
		if (masterList.contains(masterName)){
			
			if (message.startsWith(".")){
				
				String command = messageSplit[0];
				
				if (command.equalsIgnoreCase(".test")){
					event.respond("3test");
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
				if (command.equalsIgnoreCase(".masters")){
					event.respond(masterList);
					return;
				}
				if (command.equalsIgnoreCase(".Whois")){
					User userName = event.getBot().getUser(messageSplit[1]);
					event.respond("Nickname: " + userName.getNick().toString());
					event.respond("Login: " + userName.getLogin().toString());
					event.respond("Real name: " + userName.getRealName().toString());
					event.respond("Hostmask: " + userName.getHostmask().toString());
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
					String kickReason = randomMessage.KickMessage.getKickReason();
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