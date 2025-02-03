package com.example.home.loan.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.home.loan.Entity.LoanEntity;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Long> {

	List<LoanEntity> findByCustomerEmail(String email);

	LoanEntity findByLoanId(Long loanId);

	List<LoanEntity> findAll();

}
