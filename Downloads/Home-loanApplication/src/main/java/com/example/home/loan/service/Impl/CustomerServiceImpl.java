package com.example.home.loan.service.Impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.home.loan.Entity.CustomerEntity;
import com.example.home.loan.Exception.CustomerAlreadyRegisteredException;
import com.example.home.loan.Exception.CustomerNotFoundException;
import com.example.home.loan.Repository.CustomerRepository;
import com.example.home.loan.service.CustomerService;

@Service
@Primary
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    private Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    
    
 
    @Override
    public Integer doLogin(String email, String password) {
        Integer customerId = null;
        try {
            customerId = customerRepository.findCustomerByEmailAndPassword(email, password);
            logger.info("Customer: {} Logged In Successfully", customerId);
            return customerId;
        } catch (Exception e) {
            logger.error("Login failed for email: {} with exception: {}", email, e.getMessage());
            throw new CustomerNotFoundException("Customer Not Found");
        }
    }


   

    @Override
    public CustomerEntity login(String email, String password) {
        return customerRepository.findByEmailAndPassword(email, password);
    }
	
   /* 
    @Override
    public CustomerEntity addCustomer(CustomerEntity customer) throws CustomerAlreadyRegisteredException {
        if (customerRepository.findByEmail(customer.getEmail()) != null) {
            throw new CustomerAlreadyRegisteredException("Customer Already Registered: " + customer.getEmail());
        }
        return customerRepository.save(customer);
    }
    
    */
    
    @Override
    public CustomerEntity addCustomer(CustomerEntity customer) throws CustomerAlreadyRegisteredException {
        logger.info("Attempting to register customer with email: {}", customer.getEmail());
        if (customerRepository.findByEmail(customer.getEmail()) != null) {
            logger.warn("Registration failed: Customer Already Registered with email: {}", customer.getEmail());
            throw new CustomerAlreadyRegisteredException("Customer Already Registered: " + customer.getEmail());
        }
        return customerRepository.save(customer);
    }
 
    
    @Override
    public CustomerEntity updateCustomer(CustomerEntity cus) {
        CustomerEntity customer = customerRepository.findById((long) cus.getId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found: " + cus.getId()));
        BeanUtils.copyProperties(cus, customer);
        return customerRepository.save(customer);
    }
    
    @Override
    public List<CustomerEntity> getCustomers(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return customerRepository.findAll(pageable).toList();
    }
    

    @Override
    public CustomerEntity getCustomerById(Long customerId) { // Use Long
        CustomerEntity customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found: " + customerId));
        logger.info("Customer Found: {}", customerId);
        return customer;
    }




	@Override
	public CustomerEntity getCustomerByEmail(String email) {
		// TODO Auto-generated method stub
		
	        return customerRepository.findByEmail(email);
	    }


}

