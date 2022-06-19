package com.order.system.model;

import java.io.Serializable;

public class ItemCart implements Serializable{
	private Long itemId;
	private int itemCount;
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public int getItemCount() {
		return itemCount;
	}
	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
}
