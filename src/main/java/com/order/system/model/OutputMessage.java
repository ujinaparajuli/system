package com.order.system.model;

public class OutputMessage {
	private long orderId;
	
	
	public OutputMessage(Long orderId) {
		super();
		this.orderId = orderId;
	}


	public long getOrderId() {
		return orderId;
	}


	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	
}
