package data;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.*;

@SuppressWarnings("rawtypes")
public class MessageData extends ListenerAdapter{
	
	public void onAction(ActionEvent event){
		String userName = event.getUser().getNick().toString();
		String chanName = event.getChannel().getName().toString();
		String action = event.getAction();
		
		botInterface.Output.sendMessage("(" + chanName + ") " + userName + " " + action);
	}
	public void onMessage(MessageEvent event){
		String userName = event.getUser().getNick().toString();
		String ownName = event.getBot().getNick().toString();
		
		String message = event.getMessage();
    	
    	String chanName = event.getChannel().getName().toString();
    	
    	boolean isMaster = source.UserList.isMaster(userName);
    	boolean ignoreCommands = source.IgnoreMode.getIgnoreCommands();
    	   	
    	if (message.startsWith(ownName + ", ") && ignoreCommands == false){
    		commands.CommandMaster.doCommand(chanName, userName, ownName, message, isMaster);
    		botInterface.Output.sendMessage(userName + " issued command: " + message);
    	}    		
    	else{
    		eventResponder.MessageResponder.respond(chanName, userName, ownName, message, isMaster);
			botInterface.Output.sendMessage("(" + chanName + ") " + userName + ": " + message);
    	}
    	
	}
	public void onPrivateMessage(PrivateMessageEvent event){
		String userName = event.getUser().getNick().toString();
		
		String message = event.getMessage();
    	
    	boolean isMaster = source.UserList.isMaster(userName);
    	    	
    	if (isMaster == false){
    		eventResponder.PrivateMessageResponder.respond(userName);
			botInterface.Output.sendMessage(userName + " PM'd me with: " + message);
    	}
	}
}
