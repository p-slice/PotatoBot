package commandFiles;

import org.pircbotx.Channel;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.WaitForQueue;
import org.pircbotx.hooks.events.MessageEvent;

@SuppressWarnings("rawtypes")
public class GeneralMaster extends ListenerAdapter{	
	
	boolean eventInProgress = false;
	
	public void onMessage(MessageEvent event) throws Exception {
    	
		String message = event.getMessage();
		String userName = event.getUser().getNick().toString();
		String masterName = event.getUser().getLogin().toString();
		Channel chanName = event.getChannel();
		String masterList = sourceFiles.UserList.getMasterList();
		String ignorePlayers = sourceFiles.IgnoreMode.getIgnorePlayers();
		String ownNick = event.getBot().getNick().toString();
		
		if (!ignorePlayers.contains(masterName)){
			
			boolean ignoreCommands = sourceFiles.IgnoreMode.getIgnoreCommands();
			
			
			if (ignoreCommands == false){
				if (message.equalsIgnoreCase(".test")){
					return;
				}
				if (message.toLowerCase().startsWith("potatobot, google")){
					event.getBot().sendMessage(chanName, "I'm not here for you to be lazy, go do it yourself. https://www.google.com/");
					return;
				}
				if (message.equalsIgnoreCase(".commands")){
					String [] stringArray = fileManipulators.FileReader.getCommandList();
					for(int i = 0; i < stringArray.length; i++){
						event.getBot().sendNotice(userName, stringArray[i]);
					}
					return;
				}
				if (message.equalsIgnoreCase(".math help")){
					String [] stringArray = fileManipulators.FileReader.getMathHelp();
					for(int i = 0; i < stringArray.length; i++){
						event.getBot().sendNotice(userName, stringArray[i]);
					}
				}
				if (message.equalsIgnoreCase(".Version"))
					event.getBot().sendMessage(chanName, "Currently running ~PotatoBot v. 3.1.7");
				if (message.toLowerCase().equalsIgnoreCase(ownNick + "?")){
					if (eventInProgress == true){
						event.getBot().sendMessage(chanName, "My attention is currently directed elsewhere!");
						return;
					}
					if (masterList.contains(masterName))
						event.getBot().sendMessage(chanName, "Yes, Master?");
					else
						event.getBot().sendMessage(chanName, "Yes, " + userName + "?");
					String commandGiver = userName;
					WaitForQueue queue = new WaitForQueue(event.getBot());
					while (true) {
						MessageEvent currentEvent = queue.waitFor(MessageEvent.class);
						eventInProgress = true;
						if (!userName.equals(commandGiver)){
							queue.close();
							return;
						}
						if (masterList.contains(masterName)){
							if (currentEvent.getMessage().toLowerCase().startsWith("you're fired")) {
								String quitMessage = randomMessage.QuitMessage.getQuitMessage2();
								event.getBot().sendMessage(chanName, quitMessage);
								event.getBot().quitServer("And a good day to you, sir!");
							}
						}
						if (!masterList.contains(masterName)){
							if (currentEvent.getMessage().toLowerCase().startsWith("you're fired")) {
								String antiQuit = randomMessage.AntiQuitMessage.getAntiQuitMessage2();
								event.getBot().sendMessage(chanName, antiQuit);
								queue.close();
								eventInProgress = false;
								return;
							}
						}
						if (currentEvent.getMessage().toLowerCase().startsWith("y u")){
							event.getBot().sendMessage(chanName, "A better question is, why not?");
							queue.close();
							eventInProgress = false;
							return;
						}
						if (currentEvent.getMessage().equalsIgnoreCase("Never mind")) {
							event.getBot().sendMessage(chanName, "Ok.");
							queue.close();
							eventInProgress = false;
							return;
						}
					}
				}
			}
		}
	}
}
