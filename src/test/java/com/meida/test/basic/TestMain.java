package com.meida.test.basic;

public class TestMain {
	public static void main(String[] args) {
		Drink drink=new Coffee(10,"猫屎咖啡");
		System.out.println(drink.calcTotalPrice());
		System.out.println(drink.contractName());

		 drink = new Sugar(3,"大白兔",drink);
		System.out.println(drink.calcTotalPrice());
		System.out.println(drink.contractName());


	}
}
 