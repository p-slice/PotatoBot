package commands;

import org.pircbotx.PircBotX;

import source.PotatoBot;

public class CommandMaster {
	
	private static PircBotX bot = PotatoBot.PotatoBot;
	
	public static void doCommand(String chanName, String userName, String ownNick, String message, boolean isMaster) throws InterruptedException{
		
		String[] messageSplit = message.split("[ ]");
		String command = messageSplit[1];
		
		boolean isIgnoring = source.UserList.isIgnoring(userName);
		
		if (isMaster == true){		
			if (command.equalsIgnoreCase("Go") && messageSplit[2].equalsIgnoreCase("Away")){
				String quitMessage = textFiles.RandomFileReader.getRandMessage("QuitMessage1");
				commands.Disconnect.disconnect(chanName, quitMessage);
			}
			if (command.equalsIgnoreCase("You're") && messageSplit[2].toLowerCase().startsWith("fired")){
				String quitMessage = textFiles.RandomFileReader.getRandMessage("QuitMessage2");
				commands.Disconnect.disconnect(chanName, quitMessage);
			}
			if (command.equalsIgnoreCase("Join")){
				chanName = messageSplit[2];
				commands.JoinChan.joinChan(chanName);
			}
			if (command.equalsIgnoreCase("Leave")){
				if (messageSplit.length == 3){
					chanName = messageSplit[2];
				}
				commands.LeaveChan.leaveChan(chanName);
			}
			if (command.equalsIgnoreCase("Kick")){
				userName = messageSplit[2];
				String kickReason = null;
				if (messageSplit.length == 3){
					kickReason = textFiles.RandomFileReader.getRandMessage("KickMessage");
				}
				else {
					kickReason = messageSplit[3];
					for(int i = 4; i < messageSplit.length; i++){
						kickReason = kickReason + " " + messageSplit[i];
					}
				}
				commands.Kick.kick(chanName, userName, kickReason);
			}			
			if (command.equalsIgnoreCase("Read")){
				String fileName = messageSplit[2];
				commands.Read.read(chanName, message, fileName, userName);
			}
			if (command.equalsIgnoreCase("Save")){
				String fileName = messageSplit[2];
				commands.Save.save(chanName, message, fileName, userName);
			}
			if (command.equalsIgnoreCase("Edit")){
				//Coming soon
			}
		}
		if (isIgnoring == false){
			if (command.equalsIgnoreCase("Solve")){
				if (messageSplit.length < 5){
					bot.sendMessage(chanName, "Not enough params!");
					return;
				}
				double a = Double.parseDouble(messageSplit[2]);
				double b = Double.parseDouble(messageSplit[4]);
				if (b == 0 && messageSplit[3].equals("/")){
					bot.sendMessage(chanName, "Result: undefined");
					return;
				}
				String result = commands.Solve.solve(messageSplit[3], a, b);
				bot.sendMessage(chanName, result.replaceAll("\\.0\\b", ""));
			}
			if (command.equalsIgnoreCase("Link")){
				String link = textFiles.FileReader.link(messageSplit[2]);
				bot.sendMessage(chanName, link);
			}
			if (command.equalsIgnoreCase("Google")){
				if (messageSplit.length < 3){
					bot.sendMessage(chanName, "You have to tell me what to google!");
					return;
				}
				bot.sendMessage(chanName, "I'm not here for you to be lazy, go do it yourself. https://www.google.com/");
			}
		}		
	}
}
