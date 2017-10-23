package com.vishal.chatbot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import com.vishal.chatbot.db.CustomerDAO;
import com.vishal.chatbot.db.Orders;

public class MyChatBotApi {

	private static final String NULL = "null";
	private static final int MAX_GREETINGS = 4;
	private boolean finalMsgFlag = false;
	private boolean nameFlag = false;
	private boolean ordersFlag = false;
	
	private CustomerDAO dao;

	public MyChatBotApi(CustomerDAO dao) {
		this.dao = dao;
	}

	private String[][] defaultReplys = { { "hi", "hello", "hello there", "namaste" },
			{ "Im sorry?", "Pardon me?", "I didn't get you.", "I dont understand.", 
			  "Ahh.. What??", "I didn't get you", "What did you say?"},
			{ "bye", "good bye", "have a nice day,", "catch you later", "see ya" }, 
			{ "I'm fedup of greeting you", "Hmm..", "Okay..", "Do you need some water" }
	};

	private List<String> greetingsList = new ArrayList<>(Arrays.asList(defaultReplys[0]));
	private List<String> farewellList = new ArrayList<>(Arrays.asList(defaultReplys[2]));

	private short greetings = 0;

	public String processInput(String quote) {
		
		quote = quote.trim().toLowerCase(); 
		String finalMsg = NULL;
		
		//greeting reply
		finalMsg = greetings(quote);
		if(finalMsgFlag) { finalMsgFlag = false; return finalMsg;}
		
		//date and time reply
		finalMsg = getDate(quote);
		if(finalMsgFlag) { finalMsgFlag = false; return finalMsg;}
		
		//your name reply
		finalMsg = yourName(quote);
		if(finalMsgFlag) { finalMsgFlag = false; return finalMsg;}
		
		//your orders reply
		finalMsg = myOrders(quote);
		if(finalMsgFlag) { finalMsgFlag = false; return finalMsg;}

		//farewell reply
		finalMsg = farewell(quote);
		if(finalMsgFlag) { finalMsgFlag = false; return finalMsg;}
		
		//if nothing matches
		return (finalMsg.equals(NULL)) ? 
				defaultReplys[1][random(defaultReplys[1].length)] : finalMsg;
	}

	private String myOrders(String quote){
		
		if(ordersFlag) {
			finalMsgFlag = true;
			ordersFlag = false;
			switch(quote){
			case "order number": case "order numbers":
				return "Your order numbers:\n\n" + orderNumbers();
			case "status": case "statuses":
				return "The status of your orders: \n\n" + orderStatus();
			case "items": case "item list": case "items list": case "list items": case "items of items":
				return "The items in your orders: \n\n" + orderItems();
			default:
				return "Not available";
			}
		}
		
		if(quote.contains("orders") && !quote.contains("not my orders") && 
				!quote.contains("your orders") && !quote.contains("others")){
			finalMsgFlag = true;
			ordersFlag = true;
			return "what do you like to know about your orders: \n" +
				" - Order Number \n" +	" - Status \n" + " - items \n" + " - Number Of Items \n"+
				" - Date Of Order \n" + " - Delivery Date \n" + " - Price \n";
		}
		
		return NULL;
	}
	
	private String orderItems() {
		String orderItems = "";
		int count = 0;
		
		List<Orders> orderList = dao.getOrders();
		
		ListIterator<Orders> iterator = orderList.listIterator();
		
		while(iterator.hasNext()){
			Orders next = iterator.next();
			orderItems += "ORDER " + ++count + ", ORDER NUMBER: " + next.getOrderNumber() +
					": " + next.getItems() + "\n";
		}
		
		return orderItems;
	}

	private String orderStatus() {

		String tempStatus = "";
		int count = 0;
		
		List<Orders> orderList = dao.getOrders();
		
		ListIterator<Orders> iterator = orderList.listIterator();
		
		while(iterator.hasNext()){
			Orders next = iterator.next();
			tempStatus += "ORDER " + ++count + ", ORDER NUMBER: " + next.getOrderNumber()+
					": " + next.getStatus() + "\n";
		}
		
		return tempStatus;
	}

	private String orderNumbers() {
		String tempOrdernumber = "";
		int count = 0;
		
		List<Orders> orderList = dao.getOrders();
		
		ListIterator<Orders> iterator = orderList.listIterator();
		
		while(iterator.hasNext()){
			tempOrdernumber += "ORDER " + ++count + ", ORDER NUMBER: " + iterator.next().getOrderNumber() + "\n";
		}
		
		return tempOrdernumber;
	}

	private String yourName(String quote) {
		
		if(quote.contains("not mine") || quote.contains("not my name")||
				quote.contains("not your") || quote.contains("not yours") || quote.contains("not your name")){
			this.finalMsgFlag = true;
			this.nameFlag = true;
			return "whose name?";
		}
		
		if(this.nameFlag){
			if(quote.contains("yours") || quote.contains("your") || quote.contains("your name")){
				this.finalMsgFlag = true;
				this.nameFlag = false;
				return "I'm not allowed to disclose my name";
			} else if (quote.contains("my") || quote.contains("mine") || quote.contains("my name")){
				this.finalMsgFlag = true;
				this.nameFlag = false;
				return "You are " + dao.getName();
			}
		}
		if(quote.contains("your") && quote.contains("name")){
			this.finalMsgFlag = true;
			return "I'm not allowed to disclose my name";
		}
		
		if(quote.contains("my") && quote.contains("name")){
			this.finalMsgFlag = true;
			return "You are " + dao.getName();
		}
		
		if(quote.contains("tell name") || quote.contains("what name") || quote.contains("name")){
			this.finalMsgFlag = true;
			this.nameFlag = true;
			return "whose name?";
		}
		
		return NULL;
	}

	private String getDate(String quote) {
		if(quote.contains("date") && quote.contains("time")){
			finalMsgFlag = true;
			return new Date().toString();
		}
		else if(quote.contains("day")||quote.contains("date")||quote.contains("today")){
			finalMsgFlag = true;
			return java.time.LocalDate.now().toString();
		} else if(quote.contains("time")){
			finalMsgFlag = true;
			return java.time.LocalTime.now().toString();
		}
		return NULL;
	}

	private String farewell(String quote) {
		if(farewellList.contains(quote)){
			finalMsgFlag = true;
			return defaultReplys[2][random(defaultReplys[2].length)] + " " + dao.getName();
		}
		return NULL;
	}

	private String greetings(String quote) {
		if (greetingsList.contains(quote)){
			if (greetings < MAX_GREETINGS){ 
				greetings++;
				finalMsgFlag = true;
				return defaultReplys[0][random(defaultReplys[0].length)] + " " + dao.getName();
			}
			else
				finalMsgFlag = true;
				return defaultReplys[3][random(defaultReplys[3].length)];
		}
		return NULL;
	}

	private int random(int max) {
		return (int) (Math.random() * 100 % max);
	}

}
