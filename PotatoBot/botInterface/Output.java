package botInterface;

public class Output extends botInterface.Launcher{

	private static final long serialVersionUID = 1L;
	
	public static void sendMessage(String text){
		outputArea.append(text + NEWLINE);
	}

}
