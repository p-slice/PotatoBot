package chatFiles;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.PrivateMessageEvent;

@SuppressWarnings("rawtypes")
public class PrivateReply extends ListenerAdapter{
	
	public void onPrivateMessage(PrivateMessageEvent event){
		
		String username = event.getUser().getNick().toString();
		String masterName = event.getUser().getLogin().toString();
		String pMessage = event.getMessage();
		String masterList = sourceFiles.UserList.getMasterList();
		
		if (!masterList.contains(masterName)){
			boolean ignorePMs = sourceFiles.IgnoreMode.getIgnorePMs();
			if (ignorePMs == false){
				event.respond("You've reached PotatoBot @ irc.esper.net. I can't answer my private messages right now, probably because I'm a bot with no actual conscience, so leave a message at the beep, or contact my Master, p_slice.");
				event.respond("Beeeeeeeeeeeep!!!");
				event.getBot().sendMessage("p_slice", "I got a PM from " + username + " saying: " + pMessage);
			}
			if (ignorePMs == true){
				event.getBot().sendMessage("p_slice", username + " says: " + pMessage);
			}
		}
	}	
}
