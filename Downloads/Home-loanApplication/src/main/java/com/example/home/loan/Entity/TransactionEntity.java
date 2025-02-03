package com.example.home.loan.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "transactions")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int transactionId;
    private double amount;
    private String transactionType;
    private String transactionDate;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    
    private LoanEntity loan;
    
    
    
    // Constructors, Getters, and Setters
    

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public LoanEntity getLoan() {
		return loan;
	}

	public void setLoan(LoanEntity loan) {
		this.loan = loan;
	}

	public TransactionEntity(int transactionId, double amount, String transactionType, String transactionDate,
			LoanEntity loan) {
		super();
		this.transactionId = transactionId;
		this.amount = amount;
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
		this.loan = loan;
	}

  
}
