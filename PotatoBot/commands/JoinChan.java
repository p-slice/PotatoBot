package commands;

import org.pircbotx.PircBotX;

import source.PotatoBot;

public class JoinChan {
	
	private static PircBotX bot = PotatoBot.PotatoBot;
	
	public static void joinChan(String chanName){
		bot.joinChannel(chanName);
	}

}
