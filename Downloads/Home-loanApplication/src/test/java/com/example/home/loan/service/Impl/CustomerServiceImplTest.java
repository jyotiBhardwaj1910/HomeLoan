package com.example.home.loan.service.Impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.home.loan.Entity.CustomerEntity;
import com.example.home.loan.Exception.CustomerAlreadyRegisteredException;
import com.example.home.loan.Exception.CustomerNotFoundException;
import com.example.home.loan.Repository.CustomerRepository;

public class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDoLogin_Success() {
        // Arrange
        String email = "eva@example.com";
        String password = "password123";
        Integer customerId = 1;
        
        when(customerRepository.findCustomerByEmailAndPassword(email, password)).thenReturn(customerId);

        // Act
        Integer result = customerService.doLogin(email, password);

        // Assert
        assertEquals(customerId, result);
        verify(customerRepository, times(1)).findCustomerByEmailAndPassword(email, password);
    }

    @Test
    public void testDoLogin_Failure() {
        // Arrange
        String email = "wrong@example.com";
        String password = "wrongpassword";
        
        when(customerRepository.findCustomerByEmailAndPassword(email, password)).thenThrow(new CustomerNotFoundException("Customer Not Found"));

        // Act & Assert
        assertThrows(CustomerNotFoundException.class, () -> customerService.doLogin(email, password));
        verify(customerRepository, times(1)).findCustomerByEmailAndPassword(email, password);
    }

    @Test
    public void testLogin_Success() {
        // Arrange
        String email = "test@example.com";
        String password = "password";
        
        CustomerEntity customer = new CustomerEntity();
        customer.setEmail(email);
        customer.setPassword(password);

        when(customerRepository.findByEmailAndPassword(email, password)).thenReturn(customer);

        // Act
        CustomerEntity result = customerService.login(email, password);

        // Assert
        assertNotNull(result);
        assertEquals(email, result.getEmail());
        verify(customerRepository, times(1)).findByEmailAndPassword(email, password);
    }

    @Test
    public void testAddCustomer_Success() {
        // Arrange
        CustomerEntity customer = new CustomerEntity();
        customer.setEmail("test@example.com");
        
        when(customerRepository.findByEmail(customer.getEmail())).thenReturn(null);
        when(customerRepository.save(customer)).thenReturn(customer);

        // Act
        CustomerEntity result = customerService.addCustomer(customer);

        // Assert
        assertNotNull(result);
        assertEquals(customer.getEmail(), result.getEmail());
        verify(customerRepository, times(1)).findByEmail(customer.getEmail());
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    public void testAddCustomer_AlreadyRegistered() {
        // Arrange
        CustomerEntity customer = new CustomerEntity();
        customer.setEmail("test@example.com");

        when(customerRepository.findByEmail(customer.getEmail())).thenReturn(customer);

        // Act & Assert
        assertThrows(CustomerAlreadyRegisteredException.class, () -> customerService.addCustomer(customer));
        verify(customerRepository, times(1)).findByEmail(customer.getEmail());
    }

    @Test
    public void testUpdateCustomer_Success() {
        // Arrange
        CustomerEntity existingCustomer = new CustomerEntity();
        existingCustomer.setId(1L);
        existingCustomer.setEmail("test@example.com");

        CustomerEntity updatedCustomer = new CustomerEntity();
        updatedCustomer.setId(1L);
        updatedCustomer.setEmail("updated@example.com");

        when(customerRepository.findById(1L)).thenReturn(Optional.of(existingCustomer));
        when(customerRepository.save(existingCustomer)).thenReturn(existingCustomer);

        // Act
        CustomerEntity result = customerService.updateCustomer(updatedCustomer);

        // Assert
        assertNotNull(result);
        assertEquals("updated@example.com", result.getEmail());
        verify(customerRepository, times(1)).findById(1L);
        verify(customerRepository, times(1)).save(existingCustomer);
    }

    @Test
    public void testUpdateCustomer_NotFound() {
        // Arrange
        CustomerEntity updatedCustomer = new CustomerEntity();
        updatedCustomer.setId(1L);

        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CustomerNotFoundException.class, () -> customerService.updateCustomer(updatedCustomer));
        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetCustomers() {
        // Arrange
        CustomerEntity customer1 = new CustomerEntity();
        customer1.setId(1L);
        CustomerEntity customer2 = new CustomerEntity();
        customer2.setId(2L);
        
        List<CustomerEntity> customers = Arrays.asList(customer1, customer2);
        Pageable pageable = PageRequest.of(0, 10);
        Page<CustomerEntity> page = new PageImpl<>(customers, pageable, customers.size());
        
        when(customerRepository.findAll(pageable)).thenReturn(page);

        // Act
        List<CustomerEntity> result = customerService.getCustomers(0, 10);

        // Assert
        assertEquals(2, result.size());
        verify(customerRepository, times(1)).findAll(pageable);
    }

    @Test
    public void testGetCustomerById_Success() {
        // Arrange
        CustomerEntity customer = new CustomerEntity();
        customer.setId(1L);
        
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        // Act
        CustomerEntity result = customerService.getCustomerById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetCustomerById_NotFound() {
        // Arrange
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerById(1L));
        verify(customerRepository, times(1)).findById(1L);
    }
}
