package com.meida.test.basic;

public abstract class Drink {
	private double price;
	private String name;
	public abstract double calcTotalPrice();
	public abstract String contractName();
	

	public Drink(double price, String name) {
		super();
		this.price = price;
		this.name = name;
	}



	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
