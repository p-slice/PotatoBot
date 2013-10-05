package interfaceFiles;

import java.io.File;

import commandFiles.PublicMaster;

public class Analyzer extends PublicMaster{
	
	static boolean toggleIgnore;
	
	public static void doCommand(String commandLine){
		String[] messageSplit = commandLine.split("[ ]");
		String command = messageSplit[0];
		
		if (command.equalsIgnoreCase("ignore")){
			String ignoreType = messageSplit[1];
			if (messageSplit[2].equalsIgnoreCase("Off"))
				toggleIgnore = false;
			if (messageSplit[2].equalsIgnoreCase("On"))
				toggleIgnore = true;
			sourceFiles.IgnoreMode.ignoreMode(ignoreType, toggleIgnore);
			interfaceFiles.Launcher.sendMessage("Ignore " + ignoreType + " set to: " + toggleIgnore);
			return;
		}
		if (command.equalsIgnoreCase("Read")){
			String fileName = messageSplit[1];
			File f = new File("C:\\PotatoBot Files\\" + fileName + ".txt");				
			if(!f.exists()){
				interfaceFiles.Launcher.sendMessage("File doesn't exist!");
				return;
			}
			if (messageSplit.length == 2){
				String[] wholeText = textFiles.FileReader.wholeText(fileName);
					for(int i = 0; i < wholeText.length; i++){
						int q = i + 1;
						interfaceFiles.Launcher.sendMessage("Line " + q + ": " + wholeText[i]);
					}
				return;
			}
			if (messageSplit.length == 3){
				int s = Integer.parseInt(messageSplit[2]) - 1;
				int q = s + 1;
				String lineText = textFiles.FileReader.lineText(fileName, s);
				interfaceFiles.Launcher.sendMessage("Line " + q + " from '" + fileName + ".txt': " + lineText);
				return;
			}
		}
		if (command.equalsIgnoreCase("Link")){
			String link = textFiles.FileReader.link(messageSplit[1]);
			interfaceFiles.Launcher.sendMessage(link);
			return;
		}
		if (command.equalsIgnoreCase("Test")){
			System.out.println("Test");
			chatFiles.PublicReply.sendMessage("Test");
			return;
		}
	}

}
