package com.example.home.loan.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.home.loan.Entity.CustomerEntity;
import com.example.home.loan.Entity.LoginRequest;
import com.example.home.loan.Exception.CustomerAlreadyRegisteredException;
import com.example.home.loan.service.CustomerService;
import org.springframework.http.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addCustomer(@RequestBody CustomerEntity customerEntity) {
        customerService.addCustomer(customerEntity);
        return ResponseEntity.ok("Customer added successfully");
    }

    
    
    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        CustomerEntity customer = customerService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (customer == null) {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
        
        // Logic to redirect after successful login can be added here, or managed by the frontend
        Map<String, Object> response = new HashMap<>();
        response.put("customerId", customer.getId());
       
        return ResponseEntity.ok(response);
        
    }
    
    @GetMapping("/login")
    public String login() {
        return "login"; 
    }
    
    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody CustomerEntity customer) {
        try {
            customerService.addCustomer(customer);
            return ResponseEntity.ok("Registration successful");
        } catch (CustomerAlreadyRegisteredException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    
    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }
    
 
    
	  @GetMapping("/homeLoanOffering") 
	  public String homeLoanOffering() {
		  return "HomeLoanOffering";
	 
	  }
   
       
	/*
	 * @PutMapping public ResponseEntity<CustomerEntity> updateCustomer(@RequestBody
	 * CustomerEntity customer) { CustomerEntity updatedCustomer =
	 * customerService.updateCustomer(customer); return new
	 * ResponseEntity<>(updatedCustomer, HttpStatus.OK); }
	 */
    
 
    
    @PutMapping("/update")
    public ResponseEntity<CustomerEntity> updateCustomer(@RequestBody CustomerEntity customer) {
        CustomerEntity updatedCustomer = customerService.updateCustomer(customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    

        @GetMapping("/about")
        public String aboutUs() {
            return "about"; 
        }
    

	/*
	 * @GetMapping public ResponseEntity<List<CustomerEntity>>
	 * getCustomers(@RequestParam int pageNumber, @RequestParam int pageSize) {
	 * List<CustomerEntity> customers = customerService.getCustomers(pageNumber,
	 * pageSize); return new ResponseEntity<>(customers, HttpStatus.OK); }
	 */
    
    @GetMapping
    public ResponseEntity<List<CustomerEntity>> getCustomers(@RequestParam int pageNumber, @RequestParam int pageSize) {
        List<CustomerEntity> customers = customerService.getCustomers(pageNumber, pageSize);
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerEntity> getCustomerById(@PathVariable Long id) { 
        CustomerEntity customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }
}



