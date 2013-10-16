package commands;

import org.pircbotx.PircBotX;

import source.PotatoBot;

public class CommandMaster {
	
	private static PircBotX bot = PotatoBot.PotatoBot;
	
	public static void doCommand(String chanName, String userName, String ownNick, String message, boolean isMaster) throws InterruptedException{
		
		String[] messageSplit = message.split("[ ]");
		String command = messageSplit[1].toLowerCase();
		
		if (command.equalsIgnoreCase("Go") && messageSplit[2].equalsIgnoreCase("Away")){
			command = "go away";
		}
		if (command.equalsIgnoreCase("You're") && messageSplit[2].equalsIgnoreCase("fired")){
			command = "you're fired";
		}
		
		String quitMessage;
		String fileName;
		
		boolean isIgnoring = source.UserList.isIgnoring(userName);
		
		if (isIgnoring == false){
			
			switch (command){
				case "solve": 
					commands.Solve.solve(chanName, userName, messageSplit);
					return;
				case "link":
					String link = textFiles.FileReader.link(messageSplit[2]);
					bot.sendMessage(chanName, link);
					return;
				case "google":
					if (messageSplit.length < 3){
						bot.sendMessage(chanName, "You have to tell me what to google!");
						return;
					}
					bot.sendMessage(chanName, "I'm not here for you to be lazy, go do it yourself. https://www.google.com/");
					return;
				default: break;
			}
		}
		
		if (isMaster == true){
			switch (command){
				case "go away":
						quitMessage = textFiles.RandomFileReader.getRandMessage("QuitMessage1");
						commands.Disconnect.disconnect(chanName, quitMessage);
				case "you're fired":
						quitMessage = textFiles.RandomFileReader.getRandMessage("QuitMessage2");
						commands.Disconnect.disconnect(chanName, quitMessage);
				case "join":
					chanName = messageSplit[2];
					commands.JoinChan.joinChan(chanName);
					return;
				case "leave":{
					if (messageSplit.length == 3){
						chanName = messageSplit[2];
					}
					commands.LeaveChan.leaveChan(chanName);
					return;
				}
				case "kick":{
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
					return;
				}
				case "rename":
					String newNick = messageSplit[2];
					commands.Rename.reName(newNick);
					return;
				case "read":{
					fileName = messageSplit[2];
					commands.Read.read(chanName, message, fileName, userName);
					return;
				}
				case "save":{
					fileName = messageSplit[2];
					commands.Save.save(chanName, message, fileName, userName);
					return;
				}
				default: bot.sendNotice(userName, "Command not recognized! Do '.commands' for a full list.");
			}
		} else {
			bot.sendNotice(userName, "Either something borked or you don't have perms to do that.");
			return;
		}
	}
}
