package com.meida.test.basic;

public class Coffee extends Drink {

	public Coffee(double price,String name) {
		super(price,name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double calcTotalPrice() {
		// TODO Auto-generated method stub
		return this.getPrice();
	}

	@Override
	public String contractName() {
		// TODO Auto-generated method stub
		return this.getName();
	}

}
