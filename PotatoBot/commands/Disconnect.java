package commands;

import org.pircbotx.PircBotX;

import source.PotatoBot;

public class Disconnect {
	
	private static PircBotX bot = PotatoBot.PotatoBot;
	
	public static void disconnect(String chanName, String quitMessage){
		bot.sendMessage(chanName, quitMessage);
		bot.quitServer("Bai bai");
	}
}
