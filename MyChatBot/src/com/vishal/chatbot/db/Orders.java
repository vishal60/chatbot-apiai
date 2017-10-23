package com.vishal.chatbot.db;

public class Orders {
	
	private int customerId;
	private String orderNumber;
	private String status;
	private String items;
	private String noOfItems;
	private String dateOfOrder;
	private String deliveryDate;
	private int outstandingPrice;
	
	public Orders(int customerId, String orderNumber, String status, String items, 
			String noOfItems, String dateOfOrder, String deliveryDate, int outstandingPrice) {
		this.customerId = customerId;
		this.orderNumber = orderNumber;
		this.status = status;
		this.items = items;
		this.noOfItems = noOfItems;
		this.dateOfOrder = dateOfOrder;
		this.deliveryDate = deliveryDate;
		this.outstandingPrice = outstandingPrice;
	}
 
	public String getOrderNumber() {
		return orderNumber;
	}
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getStatus() {
		return status;
	}

	public String getItems() {
		return items;
	}

	public String getNoOfItems() {
		return noOfItems;
	}

	public String getDateOfOrder() {
		return dateOfOrder;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public int getOutstandingPrice() {
		return outstandingPrice;
	}

}
