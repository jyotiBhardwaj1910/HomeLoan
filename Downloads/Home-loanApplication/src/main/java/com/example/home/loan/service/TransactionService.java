package com.example.home.loan.service;

import com.example.home.loan.Entity.TransactionEntity;
import java.util.List;
import java.util.Optional;



public interface TransactionService {
	
    TransactionEntity createTransaction(TransactionEntity transaction);
    
    Optional<TransactionEntity> getTransactionById(int transactionId);
    
    List<TransactionEntity> getTransactionsByLoanId(Long loanId);
    
    List<TransactionEntity> getAllTransactions();
    
    void deleteTransaction(int transactionId);
}
