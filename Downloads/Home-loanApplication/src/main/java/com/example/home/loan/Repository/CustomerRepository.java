package com.example.home.loan.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.home.loan.Entity.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
	
	CustomerEntity findByEmailAndPassword(String email, String password);
	
	 CustomerEntity findByEmail(String email);
	   

 
	 @Query("SELECT cus.id FROM CustomerEntity cus WHERE cus.email = ?1 AND cus.password = ?2")
	    Integer findCustomerByEmailAndPassword(String email, String password);
	 
        @Query("SELECT cus FROM CustomerEntity cus WHERE cus.email = ?1 OR cus.adhaar = ?2 OR cus.panCard = ?3 OR cus.phone = ?4")
        CustomerEntity checkCustomer(String email, long adhaar, String panCard, long phone);

}
