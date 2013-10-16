package commands;

import org.pircbotx.PircBotX;

import source.PotatoBot;

public class Solve {
	
	private static PircBotX bot = PotatoBot.PotatoBot;

	public static void solve(String chanName, String userName, String[] messageSplit){
		if (messageSplit.length != 5){
			bot.sendNotice(userName, "Incorrect parameters! Do '.math help' for help!");
		} else {
			
			String operation = messageSplit[3];
			double a = Double.parseDouble(messageSplit[2]);
			double b = Double.parseDouble(messageSplit[4]);
			double c = 0;
			
			if (operation.equals("/") && b == 0){
				bot.sendMessage(chanName, "Result: Undefined");
				return;
			}
			switch (operation){
				case "+": c = a + b;
					break;
				case "-": c = a - b;
					break;
				case "*": c = a * b;
					break;
				case "/": c = a / b;
					break;
				case "^": c = Math.pow(a, b);
					break;
				default: bot.sendMessage(chanName, "Couldn't understand question!");
					return;
			}
			String result = "Result: " + c;
			bot.sendMessage(chanName, result.replaceAll("\\.0\\b", ""));
		}
	}
}
