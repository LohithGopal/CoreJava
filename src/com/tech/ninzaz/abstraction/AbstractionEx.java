package com.tech.ninzaz.abstraction;

public class AbstractionEx {

	public static void main(String[] args) {
		
		//Interface
		Mail m1 = new MailImpl();
		m1.createMail();
		m1.sendMail();
		
		//Abstract Class
		YahooMail m2 = new YahooMailImpl();
		m2.createMail();
		m2.sendMail();
		
	}
	

}
