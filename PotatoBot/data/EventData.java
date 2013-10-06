package data;

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
			botInterface.Output.sendMessage(userName + " just joined " + chanName + ".");
		}
	}
	public void onPart(PartEvent event){
		String userName = event.getUser().getNick().toString();
		String ownName = event.getBot().getNick().toString();
		String chanName = event.getChannel().getName().toString();
		
		if (!userName.equalsIgnoreCase(ownName)){
			botInterface.Output.sendMessage(userName + " just left " + chanName + ".");
		}
	}
	public void onQuit(QuitEvent event){
		String userName = event.getUser().getNick().toString();
		String ownName = event.getBot().getNick().toString();
		
		if (!userName.equalsIgnoreCase(ownName)){
			botInterface.Output.sendMessage(userName + " just disconnected.");
		}
	}
	public void onNickChange(NickChangeEvent event){
		String beforeNick = event.getOldNick();
		String afterNick = event.getNewNick();
		String ownName = event.getBot().getNick().toString();
		
		boolean isMaster = source.UserList.isMaster(beforeNick);
		
		if (!afterNick.equalsIgnoreCase(ownName)){
			eventResponder.NameChangeResponder.respond(beforeNick, afterNick, ownName, isMaster);
			botInterface.Output.sendMessage(beforeNick + " is now known as " + afterNick);
		}
	}
	public void onKick(KickEvent event){
		String userName = event.getRecipient().getNick().toString();
		String ownName = event.getBot().getNick().toString();
		String chanName = event.getChannel().getName().toString();
		String reason = event.getReason().toString();
		
		if (!userName.equalsIgnoreCase(ownName)){
			botInterface.Output.sendMessage(userName + " was kicked from " + chanName + " for '" + reason + "'.");
		}
	}
}