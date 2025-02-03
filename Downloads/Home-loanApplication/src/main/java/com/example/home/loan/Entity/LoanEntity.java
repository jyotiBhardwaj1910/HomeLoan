package com.example.home.loan.Entity;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "loan")
public class LoanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long loanId;
    private int randomLoanId;
    private String loanType;
    private String nomineeDetails;
    
    @Column(name = "current_rate_of_interest", nullable = false)
    private Double currentRateOfInterest;
   
   
   @Column(name = "loan_amt", nullable = false)  
   private Double loanAmt;


   @Column(name = "total_loan_amount", nullable = false)
    private double totalLoanAmount;
   

    @Column(name = "loan_tenure", nullable = false) 
    private int tenure;
    
    @NotNull(message = "Income is required")
    private Double income;

    

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TransactionEntity> transactions = new ArrayList<>();

    
    	// Constructors, Getters, and Setter

    public void generateRandomLoanId() {
        int randomLoanId = 1000 + new Random().nextInt(9000); // Generates a number between 1000 and 9999
        this.randomLoanId = randomLoanId;
    }

    
    public String getNomineeDetails() {
		return nomineeDetails;
	}


	public void setNomineeDetails(String nomineeDetails) {
		this.nomineeDetails = nomineeDetails;
	}


	public int getRandomLoanId() {
        return randomLoanId;
    }

    public void setRandomLoanId(int randomLoanId) {
        this.randomLoanId = randomLoanId;
    }
    
	public Long getLoanId() {
		return loanId;
	}

	

	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}


	public int getTenure() {
		return tenure;
	}


	public void setTenure(int tenure) {
		this.tenure = tenure;
	}


	public Double getIncome() {
		return income;
	}
	
	public void setIncome(Double income) {
		this.income = income;
	}

	 public Double getCurrentRateOfInterest() {
	        return currentRateOfInterest;
	    }

	    public void setCurrentRateOfInterest(Double currentRateOfInterest) {
	        this.currentRateOfInterest = currentRateOfInterest;
	    }


	


	public String getLoanType() {
		return loanType;
	}


	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}	


	public CustomerEntity getCustomer() {
		return customer;
	}


	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}


	public Double getLoanAmt() {
		return loanAmt;
	}


	public void setLoanAmt(Double loanAmt) {
		this.loanAmt = loanAmt;
	}


	public List<TransactionEntity> getTransactions() {
		return transactions;
	}


	public void setTransactions(List<TransactionEntity> transactions) {
		this.transactions = transactions;
	}


    
}