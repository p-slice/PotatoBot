package commands;

import org.pircbotx.PircBotX;

import source.PotatoBot;

public class LeaveChan {
	
	private static PircBotX bot = PotatoBot.PotatoBot;
	
	public static void leaveChan(String chanName){
		bot.partChannel(bot.getChannel(chanName), "Leaving.");
	}

}
