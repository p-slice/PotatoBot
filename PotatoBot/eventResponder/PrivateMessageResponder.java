package eventResponder;

import org.pircbotx.PircBotX;

import source.PotatoBot;

public class PrivateMessageResponder {

	private static PircBotX bot = PotatoBot.PotatoBot;
	
	public static void respond(String userName){
		
		String ignorePlayers = source.IgnoreMode.getIgnorePlayers();
		boolean ignorePMs = source.IgnoreMode.getIgnorePMs();
		
		if (!ignorePlayers.contains(userName) && ignorePMs == false){
			bot.sendMessage(userName, "You've reached PotatoBot @ irc.esper.net. I can't answer my private messages right now, probably because I'm a bot with no actual conscience, so leave a message at the beep, or contact my Master, p_slice.");
			bot.sendMessage(userName, "Beeeeeeeeeeeep!!!");
		}
	}
}
