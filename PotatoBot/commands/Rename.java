package commands;

import org.pircbotx.PircBotX;

import source.PotatoBot;

public class Rename {
	
	private static PircBotX bot = PotatoBot.PotatoBot;
	
	public static void reName(String newNick){
		bot.changeNick(newNick);
	}

}
