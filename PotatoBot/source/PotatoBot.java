package source;

import org.pircbotx.PircBotX;
import org.pircbotx.cap.SASLCapHandler;

public class PotatoBot {
	
	public static final PircBotX PotatoBot = new PircBotX();	

	public static void main(String[] args) {
		
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				botInterface.Launcher.newWindow();
			}
		});
		
		String[] botInfo = textFiles.FileReader.wholeText("botInfo");
		//Bot info is kept in seperate files as it contains private info.
    
		PotatoBot.getListenerManager().addListener(new data.EventData());
		PotatoBot.getListenerManager().addListener(new data.MessageData());
		
		PotatoBot.setName(botInfo[0]);
		PotatoBot.setLogin(botInfo[1]);
		PotatoBot.setVerbose(false);
		PotatoBot.setAutoNickChange(false);
		PotatoBot.setCapEnabled(true);
		PotatoBot.getCapHandlers().add(new SASLCapHandler(botInfo[2], botInfo[3]));

		try {
			PotatoBot.connect(botInfo[4]);
			PotatoBot.joinChannel(botInfo[5]);
		}
		catch (Exception ex) {
            ex.printStackTrace();
		}
	}
	
}
