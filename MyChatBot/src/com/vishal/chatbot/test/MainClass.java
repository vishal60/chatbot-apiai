package com.vishal.chatbot.test;

import java.util.Scanner;

import com.vishal.chatbot.MyChatBotApi;
import com.vishal.chatbot.db.CustomerDAO;
import com.vishal.chatbot.messenger.ChatBotGUI;

public class MainClass {

	private static Scanner scanner = new Scanner(System.in);
	private static CustomerDAO customerDAO;
	
	private static String userName;
	private static String password;
	
	public static void main(String[] args) {
		
		System.out.println("Welcome, Please enter the credentials: ");
		System.out.println("---------------------------------------");
		System.out.print("Enter the User name: ");
		//remove this//userName = scanner.nextLine();
		System.out.print("Enter the Password: ");
		//remove this//password = scanner.nextLine();
		
		String passwordFlag = verifyCredentials(userName, password);
		
		if (passwordFlag.equals("password correct")) {
			System.out.println("Opening Chat application...");
			runChatBot();
		} else if(passwordFlag.equals("password incorrect")){
			System.out.println("Incorrect password entered.. Exiting...");
			System.exit(0);
		} else {
			System.out.println("No such user exists.. Exiting...");
			System.exit(0);
		}
		
	}

	private static void runChatBot() {
		new ChatBotGUI(new MyChatBotApi(customerDAO));	
	}

	private static String verifyCredentials(String userName, String password) {
		customerDAO = new CustomerDAO();
		return customerDAO.verifyCredentials(userName, password);
	}

	protected void finalize() throws Throwable {
		scanner.close();
	}
}
