package chatFiles;

import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import sourceFiles.PotatoBot;

@SuppressWarnings("rawtypes")
public class PublicReply extends ListenerAdapter{
	
	private static PircBotX bot = PotatoBot.PotatoBot;
		
	String ignorePlayers = sourceFiles.IgnoreMode.getIgnorePlayers();
	
	public void onMessage(MessageEvent event) throws Exception{

		String message = event.getMessage();
		String userName = event.getUser().getNick().toString();
		String masterName = event.getUser().getLogin().toString();
		Channel chanName = event.getChannel();
		String masterList = sourceFiles.UserList.getMasterList();
		String ownNick = event.getBot().getNick().toString();
				
		if (!ignorePlayers.contains(masterName)){
			
			boolean ignoreInChat = sourceFiles.IgnoreMode.getIgnoreInChat();
			
			if (ignoreInChat == false) {
				if (message.equalsIgnoreCase("Yes."))
					event.getBot().sendMessage(chanName, "No.");
				if (message.equalsIgnoreCase("No."))
					event.getBot().sendMessage(chanName, "Yes.");
				if (message.equalsIgnoreCase("Hello PotatoBot"))
					event.getBot().sendMessage(chanName, "Hai " + userName);
				if (message.equalsIgnoreCase(ownNick)){
					if (masterList.contains(masterName)){
						event.getBot().sendMessage(chanName, "Master");
						return;
					}
					String response = textFiles.RandomFileReader.getRandMessage("NameResponse");
					if (response.equals("*Username*")){
						event.getBot().sendMessage(chanName, userName);
						return;
					}
					event.getBot().sendMessage(chanName, response);
				}
			}
		}
	}
	public static void sendMessage(String message){
		System.out.println(message);
		bot.sendMessage("#p_slice", "Test");
	}
}
