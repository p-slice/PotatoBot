package chatFiles;

import org.pircbotx.Channel;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.NickChangeEvent;

@SuppressWarnings("rawtypes")
public class EventReply extends ListenerAdapter{
	
	public void onJoin(JoinEvent event){
		
		String userName = event.getUser().getNick().toString();
		String masterName = event.getUser().getLogin().toString();
		Channel chanName = event.getChannel();
		String ownNick = event.getBot().getNick().toString();
		boolean ignoreEvents = sourceFiles.IgnoreMode.getIgnoreEvents();
		String ignorePlayers = sourceFiles.IgnoreMode.getIgnorePlayers();	
		
		if (!ignorePlayers.contains(masterName)
				&& (ignoreEvents == false)){
			
			if (!userName.equals(ownNick)){
				event.getBot().sendMessage(event.getChannel(), "Welcome, " + userName);
				event.getBot().sendMessage("p_slice", userName + " joined #p_slice.");
				return;
			}
		}
		if (!ignorePlayers.contains(userName) && (ignoreEvents == true)){
			if (!userName.equals(ownNick) && (chanName.equals("#p_slice")))
				event.getBot().sendMessage("p_slice", userName + " joined #p_slice.");
		}
	}
	
	public void onNickChange(NickChangeEvent event){
		
		String userName = event.getUser().getNick().toString();
		String beforeNick = event.getOldNick();
		String afterNick = event.getNewNick();
		String[] split = beforeNick.split("\\|");
		
		boolean ignoreEvents = sourceFiles.IgnoreMode.getIgnoreEvents();
		boolean isMaster = sourceFiles.UserList.isMaster(beforeNick);
		
		if (isMaster == true){
			sourceFiles.UserList.changeMasterName(beforeNick, afterNick);
		}
		
		String masterList = sourceFiles.UserList.getMasterList();

		if (split[1].equalsIgnoreCase("afk")
				|| split[1].equalsIgnoreCase("away")
				&& (!afterNick.toLowerCase().contains("|afk"))
				&& (!afterNick.toLowerCase().contains("|away"))){
			if (masterList.contains(afterNick)){
				event.getBot().sendMessage("#p_slice", "Welcome back, Master!");
				return;
			}
			if (ignoreEvents == false){
				event.getBot().sendMessage("#p_slice", "Welcome back, " + userName + "!");
				event.getBot().sendMessage("p_slice", userName + " is back now, just so you know.");
				return;
			}
			event.getBot().sendMessage("p_slice", userName + " is back now, just so you know.");
		}
	}
	public void onMessage(MessageEvent event) throws Exception{

		String message = event.getMessage();
		String userName = event.getUser().getNick().toString();
		String chanName = event.getChannel().getName().toString();
		String ownNick = event.getBot().getNick().toString();
		boolean ignoreRelay = sourceFiles.IgnoreMode.getIgnoreRelay();
		
		if (ignoreRelay == false && !userName.equals("p_slice") && !message.startsWith(ownNick + ",")){
			event.getBot().sendNotice("p_slice", "<" + chanName + "> " + userName + ": " + message);
		}
	}
}
