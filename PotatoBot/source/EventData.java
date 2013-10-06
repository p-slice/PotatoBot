package source;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.*;

@SuppressWarnings("rawtypes")
public class EventData extends ListenerAdapter{
	
	public void onJoin(JoinEvent event){
		String userName = event.getUser().getNick().toString();
		String ownName = event.getBot().getNick().toString();
		String chanName = event.getChannel().getName().toString();
		
		boolean isMaster = source.UserList.isMaster(userName);
		
		if (!userName.equalsIgnoreCase(ownName)){
			eventResponder.JoinResponder.respond(chanName, userName, isMaster);
			botInterface.Launcher.sendMessage(userName + " just joined " + chanName + ".");
		}
	}
	public void onPart(PartEvent event){
		String userName = event.getUser().getNick().toString();
		String ownName = event.getBot().getNick().toString();
		String chanName = event.getChannel().getName().toString();
		
		if (!userName.equalsIgnoreCase(ownName)){
			botInterface.Launcher.sendMessage(userName + " just left " + chanName + ".");
		}
	}
	public void onQuit(QuitEvent event){
		String userName = event.getUser().getNick().toString();
		String ownName = event.getBot().getNick().toString();
		
		if (!userName.equalsIgnoreCase(ownName)){
			botInterface.Launcher.sendMessage(userName + " just disconnected.");
		}
	}
	public void onNickChange(NickChangeEvent event){
		String beforeNick = event.getOldNick();
		String afterNick = event.getNewNick();
		String ownName = event.getBot().getNick().toString();
		
		boolean isMaster = source.UserList.isMaster(beforeNick);
		
		if (!afterNick.equalsIgnoreCase(ownName)){
			eventResponder.NameChangeResponder.respond(beforeNick, afterNick, ownName, isMaster);
			botInterface.Launcher.sendMessage(beforeNick + " is now known as " + afterNick);
		}
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
    		botInterface.Launcher.sendMessage(userName + " issued command: " + message);
    	}    		
    	else{
    		eventResponder.MessageResponder.respond(chanName, userName, ownName, message, isMaster);
			botInterface.Launcher.sendMessage("(" + chanName + ") " + userName + ": " + message);
    	}
    	
	}
	public void onPrivateMessage(PrivateMessageEvent event){
		String userName = event.getUser().getNick().toString();
		
		String message = event.getMessage();
    	
    	boolean isMaster = source.UserList.isMaster(userName);
    	    	
    	if (isMaster == false){
    		eventResponder.PrivateMessageResponder.respond(userName);
			botInterface.Launcher.sendMessage(userName + " PM'd me with: " + message);
    	}
	}
}