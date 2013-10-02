package sourceFiles;

import org.pircbotx.PircBotX;
import org.pircbotx.cap.SASLCapHandler;

public class PotatoBot {
	

	public static void main(String[] args) {
		
		String[] botInfo = textFiles.FileReader.getBotInfo();
		//Bot info is kept in seperate files as it contains private info.
		PircBotX potatoBot = new PircBotX();
    
		potatoBot.getListenerManager().addListener(new commandFiles.PrivateMaster());
		potatoBot.getListenerManager().addListener(new commandFiles.PublicMaster());
		potatoBot.getListenerManager().addListener(new commandFiles.GeneralMaster());
		potatoBot.getListenerManager().addListener(new commandFiles.FileMaster());
		potatoBot.getListenerManager().addListener(new chatFiles.EventReply());
		potatoBot.getListenerManager().addListener(new chatFiles.PrivateReply());
		potatoBot.getListenerManager().addListener(new chatFiles.PublicReply());
		
		potatoBot.setName(botInfo[0]);
		potatoBot.setLogin(botInfo[1]);
		potatoBot.setVerbose(false);
		potatoBot.setAutoNickChange(false);
		potatoBot.setCapEnabled(true);
		potatoBot.getCapHandlers().add(new SASLCapHandler(botInfo[2], botInfo[3]));

		try {
            
            potatoBot.connect(botInfo[4]);
            potatoBot.joinChannel(botInfo[5]);
		}
		catch (Exception ex) {
            ex.printStackTrace();
		}
	}
}
