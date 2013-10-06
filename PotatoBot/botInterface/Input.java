package botInterface;

public class Input {
	
	public static void doCommand(String message){
		
		String[] messageSplit = message.split("[ ]");
		String command = messageSplit[0];
		boolean toggleIgnore = true;
		
		if (command.equalsIgnoreCase("/ignore")){
			String ignoreType = messageSplit[1];
		    if (messageSplit[2].equalsIgnoreCase("Off"))
		    	toggleIgnore = false;
		    if (messageSplit[2].equalsIgnoreCase("On"))
		    	toggleIgnore = true;
		    source.IgnoreMode.ignoreMode(ignoreType, toggleIgnore);
			botInterface.Output.sendMessage("Ignore " + ignoreType + " set to: " + toggleIgnore);
			return;
		}
		if (command.equalsIgnoreCase("/join")){
			String chanName = messageSplit[1];
			commands.JoinChan.joinChan(chanName);
			botInterface.Output.sendMessage("Joining " + chanName);
		}
		if (command.equalsIgnoreCase("/leave")){
			String chanName = messageSplit[1];
			commands.LeaveChan.leaveChan(chanName);
			botInterface.Output.sendMessage("Leaving " + chanName);
		}
		if (command.equalsIgnoreCase("/disconnect")){
			commands.Disconnect.disconnectFull();
			botInterface.Output.sendMessage("Disconnected.");
		}
		if (command.equalsIgnoreCase("/chat") && messageSplit[1].equalsIgnoreCase("in")){
			String chanName = messageSplit[2];
			commands.DoChat.setChan(chanName);
			botInterface.Output.sendMessage("Now talking in " + chanName);
		}
		if (command.equalsIgnoreCase("/chat") && messageSplit[1].equalsIgnoreCase("toggle")){
			boolean toggleChat = false;
			if (messageSplit[2].equalsIgnoreCase("Off"))
		    	toggleChat = false;
		    if (messageSplit[2].equalsIgnoreCase("On"))
		    	toggleChat = true;
			commands.DoChat.toggleChat(toggleChat);
			botInterface.Output.sendMessage("Chat set to " + toggleChat);
		}
		if (command.equalsIgnoreCase("/me")){
			String action = messageSplit[1];
			for(int i = 2; i < messageSplit.length; i++){
				action = action + " " + messageSplit[i];
			}
			commands.DoChat.doAction(action);
			botInterface.Output.sendMessage("*PotatoBot " + action);
		}
	}
}
