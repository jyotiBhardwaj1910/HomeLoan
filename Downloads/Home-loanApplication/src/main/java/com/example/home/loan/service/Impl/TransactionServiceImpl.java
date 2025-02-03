package com.example.home.loan.service.Impl;

import com.example.home.loan.Entity.TransactionEntity;
import com.example.home.loan.Repository.TransactionRepository;
import com.example.home.loan.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public TransactionEntity createTransaction(TransactionEntity transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Optional<TransactionEntity> getTransactionById(int transactionId) {
        return transactionRepository.findById(transactionId);
    }

   

    @Override
    public List<TransactionEntity> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public void deleteTransaction(int transactionId) {
        transactionRepository.deleteById(transactionId);
    }

	@Override
	public List<TransactionEntity> getTransactionsByLoanId(Long loanId) {
		return transactionRepository.findByLoan_LoanId(loanId);
			
	}

}

