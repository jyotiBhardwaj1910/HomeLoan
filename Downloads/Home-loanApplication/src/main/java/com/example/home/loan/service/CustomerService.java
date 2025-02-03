package com.example.home.loan.service;

import java.util.List;
import com.example.home.loan.Entity.CustomerEntity;


public interface CustomerService {


	Integer doLogin(String email, String password);

    CustomerEntity addCustomer(CustomerEntity cus);

    CustomerEntity updateCustomer(CustomerEntity cus);

    List<CustomerEntity> getCustomers(int pageNumber, int pageSize);

    CustomerEntity getCustomerById(Long custId);

	CustomerEntity login(String email, String password);
	
	 CustomerEntity getCustomerByEmail(String email);


}