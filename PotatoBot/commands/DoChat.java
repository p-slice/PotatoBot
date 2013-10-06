package commands;

import org.pircbotx.PircBotX;

import source.PotatoBot;

public class DoChat {
	
	private static PircBotX bot = PotatoBot.PotatoBot;
	
	static boolean doChat = false;
	static String chanName;
	
	public static void setChan(String channel){
		chanName = channel;
		doChat = true;
	}
	
	public static void toggleChat(boolean chat){
		doChat = chat;
	}
	
	public static void doChat(String message){
		if (doChat == true){
			bot.sendMessage(chanName, message);
		}
	}
	public static void doAction(String message){
		if (doChat == true){
			bot.sendAction(chanName, message);
		}
	}
}
