package sourceFiles;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.PrivateMessageEvent;

@SuppressWarnings("rawtypes")
public class IgnoreMode  extends ListenerAdapter{	

	static boolean ignoreInChat = false;
	static boolean ignorePMs = true;
	static boolean ignoreEvents = true;
	static boolean ignoreCommands = false;
	static boolean ignoreRelay = true;
	
	public void onPrivateMessage(PrivateMessageEvent event){
		
		String message = event.getMessage();
		String masterName = event.getUser().getLogin().toString();
		String masterList = sourceFiles.UserList.getMasterList();
	
		if (masterList.contains(masterName)){
			
			String[] messageSplit = message.split("[ ]");
			String mode = messageSplit[2];
			
			if (message.toLowerCase().startsWith(".ignore chat:")){
				if (mode.toLowerCase().equals("on")){
					ignoreInChat = true;
					event.respond("Ignore Chat set to: On");
					event.getBot().sendMessage("#p_slice", "Ignore Chat set to: On");
				}
				if (mode.toLowerCase().equals("off")){
					ignoreInChat = false;
					event.respond("Ignore Chat set to: Off");
					event.getBot().sendMessage("#p_slice", "Ignore Chat set to: Off");
				}
			}
			if (message.toLowerCase().startsWith(".ignore pms:")){
				if (mode.toLowerCase().equals("on")){
					ignorePMs = true;
					event.respond("Ignore PMs set to: On");
					event.getBot().sendMessage("#p_slice", "Ignore PMs set to: On");
				}
				if (mode.toLowerCase().equals("off")){
					ignorePMs = false;
					event.respond("Ignore PMs set to: Off");
					event.getBot().sendMessage("#p_slice", "Ignore PMs set to: Off");
				}
			}
			if (message.toLowerCase().startsWith(".ignore events:")){
				if (mode.toLowerCase().equals("on")){
					ignoreEvents = true;
					event.respond("Ignore Events set to: On");
					event.getBot().sendMessage("#p_slice", "Ignore Events set to: On");
				}
				if (mode.toLowerCase().equals("off")){
					ignoreEvents = false;
					event.respond("Ignore Events set to: Off");
					event.getBot().sendMessage("#p_slice", "Ignore Events set to: Off");
				}
			}
			if (message.toLowerCase().startsWith(".ignore commands:")){
				if (mode.toLowerCase().equals("on")){
					ignoreCommands = true;
					event.respond("Ignore Commands set to: On");
					event.getBot().sendMessage("#p_slice", "Ignore Commands set to: On");
				}
				if (mode.toLowerCase().equals("off")){
					ignoreCommands = false;
					event.respond("Ignore Commands set to: Off");
					event.getBot().sendMessage("#p_slice", "Ignore Commands set to: Off");
				}
			}
			if (message.toLowerCase().startsWith(".ignore relay:")){
				if (mode.toLowerCase().equals("on")){
					ignoreRelay = true;
					event.respond("Ignore Relay set to: On");
				}
				if (mode.toLowerCase().equals("off")){
					ignoreRelay = false;
					event.respond("Ignore Relay set to: Off");
				}
			}
		}
	}
	public static String getIgnorePlayers(){
		String ignorePlayers = "FoxBot, TheReverend403, PotatoBot";
		return ignorePlayers;
	}
	public static boolean getIgnoreInChat(){		
		return ignoreInChat;
	}
	public static boolean getIgnorePMs(){		
		return ignorePMs;
	}
	public static boolean getIgnoreEvents(){		
		return ignoreEvents;
	}
	public static boolean getIgnoreCommands(){		
		return ignoreCommands;
	}
	public static boolean getIgnoreRelay(){		
		return ignoreRelay;
	}
}
