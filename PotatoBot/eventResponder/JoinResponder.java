package eventResponder;

import org.pircbotx.PircBotX;

import source.PotatoBot;

public class JoinResponder {
	
	private static PircBotX bot = PotatoBot.PotatoBot;
	
	public static void respond(String chanName, String userName, boolean isMaster){
		
		boolean ignoreEvents = source.IgnoreMode.getIgnoreEvents();
		String ignorePlayers = source.IgnoreMode.getIgnorePlayers();	
				
		if (!ignorePlayers.contains(userName) && (ignoreEvents == false) && isMaster == false)
				bot.sendMessage(chanName, "Welcome, " + userName);
	}

}
