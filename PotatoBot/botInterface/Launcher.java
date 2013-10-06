package botInterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Launcher extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	static JTextArea outputArea;
	JTextField inputArea;
	JButton toggleRelay;
	JButton button;
	
	String newText = "";
	
	static final String NEWLINE = System.getProperty("line.separator");
	
	public static void newWindow(){
		JFrame baseFrame = new JFrame("PotatoBot Interface");
		
		baseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JComponent newContentPane = new Launcher();
		newContentPane.setOpaque(true);
		baseFrame.setContentPane(newContentPane);
		
		baseFrame.pack();
		baseFrame.setVisible(true);
	}
	
	public Launcher(){
		super(new GridBagLayout());
		
		outputArea = new JTextArea(5,20);
		outputArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(outputArea);
		
		inputArea = new JTextField(20);
		inputArea.addActionListener(this);
		
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		GridBagConstraints outputWindow = new GridBagConstraints();
		GridBagConstraints inputWindow = new GridBagConstraints();
		
		outputWindow.gridwidth = GridBagConstraints.REMAINDER;
		
		outputWindow.fill = GridBagConstraints.BOTH;
		outputWindow.weightx = 0.1;
		outputWindow.weighty = 0.1;
		outputWindow.insets = new Insets(0,0,10,0);
		add(scrollPane, outputWindow);
		
		inputWindow.fill = GridBagConstraints.PAGE_END;
		inputWindow.fill = GridBagConstraints.HORIZONTAL;
		add(inputArea, inputWindow);
	}
	
	public void actionPerformed(ActionEvent evt){
		String text = inputArea.getText();
		if (text.startsWith("/")){
			botInterface.Input.doCommand(text);
		}
		else{
			commands.DoChat.doChat(text);
			botInterface.Output.sendMessage(text);
		}
		inputArea.setText("");
	}
}