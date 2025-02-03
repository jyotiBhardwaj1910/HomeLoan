package com.example.home.loan.Controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.home.loan.Entity.TransactionEntity;
import com.example.home.loan.service.TransactionService;


@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
		
		@Autowired
		private TransactionService transactionService;

		// Get all transactions
		@GetMapping
		public List<TransactionEntity> getAllTransactions(){
			return transactionService.getAllTransactions();
		}
		
		// Get a transaction by ID
		@GetMapping("/{transactionId}")
		public ResponseEntity<TransactionEntity> getTransactionById(@PathVariable int transactionId){
			Optional<TransactionEntity> transaction = transactionService.getTransactionById(transactionId);
			return transaction.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
		}
		
		// Create a new transaction
		@PostMapping
		public ResponseEntity<TransactionEntity> createTransaction(@RequestBody TransactionEntity transaction) {
			TransactionEntity createdTransaction = transactionService.createTransaction(transaction);
			return ResponseEntity.ok(createdTransaction);
		}
		
		// Update an existing transaction
		@PutMapping("/{transactionId}")
		public ResponseEntity<TransactionEntity> updateTransaction(@PathVariable int transactionId, @RequestBody TransactionEntity transactionDetails) {
			Optional<TransactionEntity> optionalTransaction = transactionService.getTransactionById(transactionId);
			
			if(optionalTransaction.isPresent()) {
				TransactionEntity transaction = optionalTransaction.get();
				transaction.setAmount(transactionDetails.getAmount());
				transaction.setTransactionType(transactionDetails.getTransactionType());
				transaction.setTransactionDate(transactionDetails.getTransactionDate());
				transaction.setLoan(transactionDetails.getLoan());
				
				TransactionEntity updatedTransaction = transactionService.createTransaction(transaction);
				return ResponseEntity.ok(updatedTransaction);
			} else {
				return ResponseEntity.notFound().build();
			}
		}
		
		// Delete a transaction by ID
		@DeleteMapping("/{transactionId}")
		public ResponseEntity<Void> deleteTransaction(@PathVariable int transactionId){
			Optional<TransactionEntity> optionalTransaction = transactionService.getTransactionById(transactionId);
			
			if(optionalTransaction.isPresent()) {
				transactionService.deleteTransaction(transactionId);
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.notFound().build();
			}
		}
	}
