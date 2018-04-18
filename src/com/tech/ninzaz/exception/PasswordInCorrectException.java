package com.tech.ninzaz.exception;

public class PasswordInCorrectException extends Exception {

	private String msg;
	public PasswordInCorrectException(String msg){
		this.msg = msg;
	}
	
}
