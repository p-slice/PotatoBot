package commands;

import org.pircbotx.PircBotX;

import source.PotatoBot;

public class Kick {
	
	private static PircBotX bot = PotatoBot.PotatoBot;
	
	public static void kick(String chanName, String userName, String reason){
		bot.kick(bot.getChannel(chanName), bot.getUser(userName), reason);
	}

}
