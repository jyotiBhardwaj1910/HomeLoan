package com.example.home.loan.service;

import java.util.List;

import com.example.home.loan.Entity.LoanEntity;

public interface LoanService {

	LoanEntity addLoan(LoanEntity loan);

	LoanEntity updateLoan(LoanEntity loan);

	void deleteLoan(Long loanId);

	LoanEntity getLoanById(Long loanId);

	List<LoanEntity> getAllLoans();

	List<LoanEntity> getLoansByUserEmail(String email);

	LoanEntity getLoanDetails(Long loanId);

	List<LoanEntity> getAvailableLoanProducts();

	LoanEntity applyForNewLoan(LoanEntity loanEntity);

	void saveLoanApplication(LoanEntity loanEntity);

	void saveLoan(LoanEntity loan);

}
