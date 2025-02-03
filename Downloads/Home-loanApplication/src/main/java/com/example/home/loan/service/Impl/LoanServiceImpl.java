package com.example.home.loan.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.home.loan.Entity.LoanEntity;
import com.example.home.loan.Repository.LoanRepository;
import com.example.home.loan.service.LoanService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;


@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	private LoanRepository loanRepository;

	@Override
	public LoanEntity addLoan(LoanEntity loan) {
		return loanRepository.save(loan);
	}

	@Override
	public LoanEntity updateLoan(LoanEntity loan) {
		return loanRepository.save(loan);
	}

	@Override
	public void deleteLoan(Long loanId) {
		loanRepository.deleteById(loanId);
	}


	/*
	 * @Override public LoanEntity getLoanById(Long loanId) throws Exception {
	 * return loanRepository.findById(loanId) .orElseThrow(() -> new
	 * Exception("Loan not found")); }
	 * 
	 */
	@Override
	public LoanEntity getLoanById(Long loanId) {
		return loanRepository.findById(loanId).orElse(null);
	}

	/*
	 * @Override public List<LoanEntity> getAllLoans() { return
	 * loanRepository.findAll(); // Make sure this is correctly returning the li of
	 * loans
	 * 
	 * }
	 */



	@Override
	public List<LoanEntity> getAllLoans() {
		return loanRepository.findAll(); // Or the appropriate method for fetching loans
	}

	@Override
	public List<LoanEntity> getLoansByUserEmail(String email) {
		return loanRepository.findByCustomerEmail(email);
	}

	@Override
	public LoanEntity getLoanDetails(Long loanId) {
		return loanRepository.findById(loanId)
				.orElseThrow(() -> new EntityNotFoundException("Loan not found with ID: " + loanId));
	}

	@Override
	public List<LoanEntity> getAvailableLoanProducts() {
		return loanRepository.findAll(); // Assuming all loans are products
	}

	@Override
	public LoanEntity applyForNewLoan(LoanEntity loanEntity) {
		// Save the new loan details to the database
		return loanRepository.save(loanEntity);
	}

	@Override
	public void saveLoanApplication(LoanEntity loan) {
		// Log loan data before saving
		System.out.println("Saving Loan Application: " + loan.getLoanType() + ", Amount: " + loan.getLoanAmt());

		loanRepository.save(loan);

	}

	@Override
	public void saveLoan(LoanEntity loan) {
		loanRepository.save(loan);
	}

}
