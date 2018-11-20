package com.meida.test.basic;

public class Sugar extends Drink{
    public Drink drink;
	public Sugar(double price, String name,Drink drink) {
		super(price, name);
		// TODO Auto-generated constructor stub
		this.drink=drink;		
	}

	@Override
	public double calcTotalPrice() {
		// TODO Auto-generated method stub
		return this.getPrice()+drink.getPrice();
	}

	@Override
	public String contractName() {
		// TODO Auto-generated method stub
		return this.getName()+drink.getName();
	}

	
}
