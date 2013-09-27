package sourceFiles;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.pircbotx.PircBotX;
import org.pircbotx.cap.SASLCapHandler;

public class PotatoBot {
	

	public static void main(String[] args) {
		
		Charset charset = Charset.defaultCharset();
		
		Path filePath = new File("C:\\PotatoBot Files\\BotInfo.txt").toPath();
		List<String> stringList = null;
		try {
			stringList = Files.readAllLines(filePath, charset);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] stringArray = stringList.toArray(new String[]{});
		
		String name = stringArray[0];
		String login = stringArray[1];
		String user = stringArray[2];
		String password = stringArray[3];
		String server = stringArray[4];
		String channel = stringArray[5];
		
		PircBotX potatoBot = new PircBotX();
    
		potatoBot.getListenerManager().addListener(new commandFiles.PrivateMaster());
		potatoBot.getListenerManager().addListener(new commandFiles.PublicMaster());
		potatoBot.getListenerManager().addListener(new commandFiles.GeneralMaster());
		potatoBot.getListenerManager().addListener(new commandFiles.MathBot());
		potatoBot.getListenerManager().addListener(new commandFiles.FileMaster());
		potatoBot.getListenerManager().addListener(new chatFiles.EventReply());
		potatoBot.getListenerManager().addListener(new chatFiles.PrivateReply());
		potatoBot.getListenerManager().addListener(new chatFiles.PublicReply());
		
		potatoBot.setName(name);
		potatoBot.setLogin(login);
		potatoBot.setVerbose(false);
		potatoBot.setAutoNickChange(false);
		potatoBot.setCapEnabled(true);
		potatoBot.getCapHandlers().add(new SASLCapHandler(user, password));

		try {
            
            potatoBot.connect(server);
            potatoBot.joinChannel(channel);
		}
		catch (Exception ex) {
            ex.printStackTrace();
		}
	}
}
