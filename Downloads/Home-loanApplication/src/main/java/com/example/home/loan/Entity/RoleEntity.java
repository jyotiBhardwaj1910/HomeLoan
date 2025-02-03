package com.example.home.loan.Entity;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<CustomerEntity> customers;
    
    
    // Constructors, Getters, and Setters
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<CustomerEntity> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<CustomerEntity> customers) {
		this.customers = customers;
	}

	public RoleEntity(Long id, String name, Set<CustomerEntity> customers) {
		super();
		this.id = id;
		this.name = name;
		this.customers = customers;
	}

}
