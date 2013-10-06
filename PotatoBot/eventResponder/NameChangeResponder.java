package eventResponder;

import org.pircbotx.PircBotX;

import source.PotatoBot;

public class NameChangeResponder {
	
	private static PircBotX bot = PotatoBot.PotatoBot;
	
	public static void respond(String beforeNick, String afterNick, String ownNick, boolean isMaster){
		
		String[] split = beforeNick.split("\\|");		
		boolean ignoreEvents = source.IgnoreMode.getIgnoreEvents();

		if (split[1].equalsIgnoreCase("afk")
				|| split[1].equalsIgnoreCase("away")
				&& (!afterNick.toLowerCase().contains("|afk"))
				&& (!afterNick.toLowerCase().contains("|away"))){
			if (isMaster == true)
				bot.sendMessage("#p_slice", "Welcome back, Master!");
			if (ignoreEvents == false)
				bot.sendMessage("#p_slice", "Welcome back, " + afterNick + "!");
		}
		if (isMaster == true){
			source.UserList.changeMasterName(beforeNick, afterNick);
		}
	}

}
