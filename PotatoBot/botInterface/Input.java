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
		if (command.equalsIgnoreCase("")){
			//Will do stuff later...
		}
	}

}
