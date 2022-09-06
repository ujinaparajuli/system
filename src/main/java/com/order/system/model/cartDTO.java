package com.order.system.model;

public class cartDTO {
	private Long itemId;
	
	private String title;
	
	private Double price;
	
	private String img;
	
	private int count;
	
	private Double itemTotal;
	
	private Double grandTotal;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Double getItemTotal() {
		return itemTotal;
	}

	public void setItemTotal(Double itemTotal) {
		this.itemTotal = itemTotal;
	}

	@Override
	public String toString() {
		return "cartDTO [itemId=" + itemId + ", title=" + title + ", price=" + price + ", img=" + img + ", count="
				+ count + ", itemTotal=" + itemTotal + ", grandTotal=" + grandTotal + "]";
	}
}
