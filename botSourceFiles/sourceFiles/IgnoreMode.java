package sourceFiles;

import org.pircbotx.hooks.ListenerAdapter;

@SuppressWarnings("rawtypes")
public class IgnoreMode  extends ListenerAdapter{	

	static boolean ignoreInChat = false;
	static boolean ignorePMs = true;
	static boolean ignoreEvents = true;
	static boolean ignoreCommands = false;
	static boolean ignoreRelay = true;
	
	public static void ignoreMode(String type, boolean toggled){
		if (type.equalsIgnoreCase("Chat"))
			ignoreInChat = toggled;
		if (type.equalsIgnoreCase("PMs"))
			ignorePMs = toggled;
		if (type.equalsIgnoreCase("Events"))
			ignoreEvents = toggled;
		if (type.equalsIgnoreCase("Commands"))
			ignoreCommands = toggled;
		if (type.equalsIgnoreCase("Relay"))
			ignoreRelay = toggled;
	}
	public static String getIgnorePlayers(){
		String ignorePlayers = "PotatoBot";
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
