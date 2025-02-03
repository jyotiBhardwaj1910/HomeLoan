package com.example.home.loan;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.home.loan.Entity.CustomerEntity;
import com.example.home.loan.Repository.CustomerRepository;

@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        // Clean up the database
        customerRepository.deleteAll();

        // Insert a test customer
        CustomerEntity customer = new CustomerEntity();
        customer.setId(101);
        customer.setFname("Eva");
        customer.setLname("Bhardwaj");
        customer.setGender("female");
        customer.setPhone(9876543219l);
        customer.setEmail("eva@bhardwaj.com");
        customer.setPassword("password123");
        customer.setConfirmPassword("password123");
        customer.setSalary(50000.0F);
        customer.setAdhaar(123456789012L);
        customer.setPanCard("ABCDE1234F");
        customer.setWalletAmt(1000.0);

        customerRepository.save(customer);
    }

    @Test
    public void testFindCustomerByEmailAndPassword() {
        String email = "eva@bhardwaj.com";
        String password = "password123";

        Integer customerId = customerRepository.findCustomerByEmailAndPassword(email, password);

        assertNotNull(customerId, "The customerId should not be null");
    }
    
    @Test
    public void testFindCustomerByEmailAndPassword1() {
        String email = "jho@bhardwaj.com";
        String password = "password123";

        Integer customerId = customerRepository.findCustomerByEmailAndPassword(email, password);

        assertNotNull(customerId, "The customerId should not be null");
    }
    
    @Test
    public void testFindCustomerByEmailAndPassword2() {
        String email = "rob@bhardwaj.com";
        String password = "password123";

        Integer customerId = customerRepository.findCustomerByEmailAndPassword(email, password);

        assertNotNull(customerId, "The customerId should not be null");
    }
    
    
}