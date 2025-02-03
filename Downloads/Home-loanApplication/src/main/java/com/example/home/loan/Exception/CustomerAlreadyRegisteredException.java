package com.example.home.loan.Exception;

public class CustomerAlreadyRegisteredException extends RuntimeException {

	public CustomerAlreadyRegisteredException(String message) {
		super(message);
	}
}
