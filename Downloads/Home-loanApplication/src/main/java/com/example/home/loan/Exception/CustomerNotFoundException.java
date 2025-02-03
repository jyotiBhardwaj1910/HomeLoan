package com.example.home.loan.Exception;



public class CustomerNotFoundException extends RuntimeException{

	public CustomerNotFoundException(String message){
		super(message);
	}
}
