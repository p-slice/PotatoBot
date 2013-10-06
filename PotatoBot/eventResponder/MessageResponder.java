package eventResponder;

import org.pircbotx.PircBotX;

import source.PotatoBot;

public class MessageResponder {
	
	private static PircBotX bot = PotatoBot.PotatoBot;
	
	public static void respond(String chanName, String userName, String ownNick, String message, boolean isMaster){
		
		boolean ignoreInChat = source.IgnoreMode.getIgnoreInChat();
		boolean isIgnoring = source.UserList.isIgnoring(userName);
		
		if (isIgnoring == false){
			
			if (message.equalsIgnoreCase(".commands")){
				String [] stringArray = textFiles.FileReader.wholeText("commandList");
				for(int i = 0; i < stringArray.length; i++){
					bot.sendNotice(userName, stringArray[i]);
				}
			}
			if (message.equalsIgnoreCase(".math help")){
				String [] stringArray = textFiles.FileReader.wholeText("mathBotHelp");
				for(int i = 0; i < stringArray.length; i++){
					bot.sendNotice(userName, stringArray[i]);
				}
			}
			if (message.equalsIgnoreCase(".version")){
				String[] version = textFiles.FileReader.wholeText("botInfo");
				bot.sendMessage(chanName, "Currently running " + version[6]);
			}
			
			if (ignoreInChat == false) {
				if (message.equalsIgnoreCase("Yes."))
					bot.sendMessage(chanName, "No.");
				if (message.equalsIgnoreCase("No."))
					bot.sendMessage(chanName, "Yes.");
				if (message.equalsIgnoreCase("Hello PotatoBot"))
					bot.sendMessage(chanName, "Hai " + userName);
				if (message.equalsIgnoreCase(ownNick)){
					if (isMaster == true)
						bot.sendMessage(chanName, "Master");
					else {
						String response = textFiles.RandomFileReader.getRandMessage("NameResponse");
						if (response.equals("*Username*")){
							bot.sendMessage(chanName, userName);
							return;
						}
						bot.sendMessage(chanName, response);
					}
				}
			}
		}
	}
}
