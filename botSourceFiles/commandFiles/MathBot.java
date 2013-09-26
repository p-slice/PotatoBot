package commandFiles;

import org.pircbotx.Channel;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

@SuppressWarnings("rawtypes")
public class MathBot extends ListenerAdapter {
	
	public void onMessage(MessageEvent event) throws Exception {
		
		boolean ignoreCommands = sourceFiles.IgnoreMode.getIgnoreCommands();
		String ignorePlayers = sourceFiles.IgnoreMode.getIgnorePlayers();
		String masterName = event.getUser().getLogin().toString();
		String ownNick = event.getBot().getNick().toString();
		
		if (!ignorePlayers.contains(masterName) && (ignoreCommands == false)){			
		
			String message = event.getMessage();
			Channel chanName = event.getChannel();
			String[] messageSplit = message.split("[ ]");
	
			if (message.startsWith(ownNick + ", ") && messageSplit[1].equalsIgnoreCase("solve")) {
						
				double a = Double.parseDouble(messageSplit[2]);
				double b = Double.parseDouble(messageSplit[4]);
				String result = null;
				
				if (messageSplit[3].equals("+")){
					double c = a + b;
					result = "Result: " + c;
					
				}
				if (messageSplit[3].equals("-")){
					double c = a - b;
					result = "Result: " + c;
				}
				if (messageSplit[3].equals("*")){
					double c = a * b;
					result = "Result: " + c;
				}
				if (messageSplit[3].equals("/")){
					double c = a / b;
					if (b == 0){
						event.getBot().sendMessage(chanName, "Result: undefined");
						return;
					}
					else{
					result = "Result: " + c;
					}
				}
				if (messageSplit[3].equals("^")){
					double c = Math.pow(a, b);
					result = "Result: " + c;
				}
				if (result == null)
					result = "Couldn't understand question.";
				event.getBot().sendMessage(chanName, result.replaceAll("\\.0\\b", ""));
				return;
			}
		}
	}
}
