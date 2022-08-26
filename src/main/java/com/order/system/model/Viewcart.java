package com.order.system.model;

import java.util.List;

public class Viewcart {
	private List<cartDTO> cartDtos;
	private String itemCount;
	private String grandTotal;
	private String grandtotalWithTax;
	private boolean isCartEmpty;
	public List<cartDTO> getCartDtos() {
		return cartDtos;
	}
	public void setCartDtos(List<cartDTO> cartDtos) {
		this.cartDtos = cartDtos;
	}
	public String getItemCount() {
		return itemCount;
	}
	public void setItemCount(String itemCount) {
		this.itemCount = itemCount;
	}
	public String getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(String grandTotal) {
		this.grandTotal = grandTotal;
	}
	public String getGrandtotalWithTax() {
		return grandtotalWithTax;
	}
	public void setGrandtotalWithTax(String grandtotalWithTax) {
		this.grandtotalWithTax = grandtotalWithTax;
	}
	public boolean isCartEmpty() {
		return isCartEmpty;
	}
	public void setCartEmpty(boolean isCartEmpty) {
		this.isCartEmpty = isCartEmpty;
	}

}
