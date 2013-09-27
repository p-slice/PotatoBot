package commandFiles;

public class MathBot {
	
	public static String solve(String operation, double a, double b){
		String result = null;
		
		if (operation.equals("+")){
			double c = a + b;
			result = "Result: " + c;			
		}
		if (operation.equals("-")){
			double c = a - b;
			result = "Result: " + c;
		}
		if (operation.equals("*")){
			double c = a * b;
			result = "Result: " + c;
		}
		if (operation.equals("/")){
			double c = a / b;
			result = "Result: " + c;
		}
		if (operation.equals("^")){
			double c = Math.pow(a, b);
			result = "Result: " + c;
		}
		return result;
	}
}
