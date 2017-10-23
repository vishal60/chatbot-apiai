package com.vishal.chatbot.messenger;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import com.vishal.chatbot.MyChatBotApi;

@SuppressWarnings("serial")
public class ChatBotGUI extends JFrame implements KeyListener{

	private static final String YOU   = "  YOU     :    ";
	private static final String AGENT = "  AGENT :    ";
	private JPanel panel = new JPanel();
	private JTextArea dialog = new JTextArea(20,50);
	private JTextArea input = new JTextArea(1,50);
	private JScrollPane scroll = new JScrollPane(
			dialog, 
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
	);

	private MyChatBotApi chatBot;
	
	public ChatBotGUI(MyChatBotApi chatbot) {
		super("ChatBot Agent");
		setSize(700,400);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		dialog.setEditable(false);
		dialog.setBackground(new Color(234, 238, 242));

		input.addKeyListener(this);
		input.append("Type your Message here...");
		
		panel.add(scroll);
		panel.add(input);
		panel.setBackground(new Color(25, 44, 61));		
		add(panel);
		
		setVisible(true);
		
		this.chatBot = chatbot;
	}
	
	public void keyPressed(KeyEvent e) {	
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			input.setEditable(false);
			
		}
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			input.setEditable(true);
			
			//quote from the user
			String quote = input.getText();
			input.setText("");
			addTextToDialog(quote, YOU);
			
			//reply from agent
			String agentMsg = chatBot.processInput(quote);
			addTextToDialog(agentMsg, AGENT);
			addTextToDialog("","");
		}
	}

	public void keyTyped(KeyEvent e) {}
	
	private void addTextToDialog(String string, String who) {
		string = who + string + "\n";
		dialog.setText(dialog.getText() + string);
		dialog.setFont(new Font("Verdana", Font.BOLD, 10));
	}
	
	
}
