package com.example.home.loan.service.Impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.home.loan.Entity.LoanEntity;
import com.example.home.loan.Repository.LoanRepository;
import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
public class LoanServiceImplTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanServiceImpl loanService;

    private LoanEntity loanEntity;

    @BeforeEach
    public void setup() {
        loanEntity = new LoanEntity();
        loanEntity.setLoanId(1L);
        loanEntity.setLoanAmt(500000.00);
        loanEntity.setTenure(20);
        loanEntity.setLoanType("Home Loan");
        loanEntity.setCurrentRateOfInterest(7.5);
    }

    @Test
    public void testAddLoan() {
        when(loanRepository.save(any(LoanEntity.class))).thenReturn(loanEntity);

        LoanEntity savedLoan = loanService.addLoan(loanEntity);

        assertNotNull(savedLoan);
        assertEquals(loanEntity.getLoanId(), savedLoan.getLoanId());
        verify(loanRepository, times(1)).save(any(LoanEntity.class));
    }

    @Test
    public void testUpdateLoan() {
        when(loanRepository.save(any(LoanEntity.class))).thenReturn(loanEntity);

        LoanEntity updatedLoan = loanService.updateLoan(loanEntity);

        assertNotNull(updatedLoan);
        assertEquals(loanEntity.getLoanAmt(), updatedLoan.getLoanAmt());
        verify(loanRepository, times(1)).save(any(LoanEntity.class));
    }

    @Test
    public void testDeleteLoan() {
        doNothing().when(loanRepository).deleteById(any(Long.class));

        loanService.deleteLoan(loanEntity.getLoanId());

        verify(loanRepository, times(1)).deleteById(any(Long.class));
    }

    @Test
    public void testGetLoanById() {
        when(loanRepository.findById(any(Long.class))).thenReturn(Optional.of(loanEntity));

       // LoanEntity foundLoan = loanService.getLoanById(loanEntity.getLoanId());

      //  assertNotNull(foundLoan);
        //assertEquals(loanEntity.getLoanId(), foundLoan.getLoanId());
        verify(loanRepository, times(1)).findById(any(Long.class));
    }

    @Test
    public void testGetLoanById_NotFound() {
        when(loanRepository.findById(any(Long.class))).thenReturn(Optional.empty());

    //    LoanEntity foundLoan = loanService.getLoanById(1L);

       // assertNull(foundLoan);
        verify(loanRepository, times(1)).findById(any(Long.class));
    }
 


	
	  @Test public void testGetAllLoans() { List<LoanEntity> loanList =
	  Arrays.asList(loanEntity);
	  when(loanRepository.findAll()).thenReturn(loanList);
	  
	  List<LoanEntity> loans = loanService.getAllLoans();
	  
	  assertEquals(1, loans.size()); verify(loanRepository, times(1)).findAll(); }
	 

    @Test
    public void testGetLoansByUserEmail() {
        List<LoanEntity> loanList = Arrays.asList(loanEntity);
        when(loanRepository.findByCustomerEmail(anyString())).thenReturn(loanList);

        List<LoanEntity> loans = loanService.getLoansByUserEmail("test@example.com");

        assertEquals(1, loans.size());
        verify(loanRepository, times(1)).findByCustomerEmail(anyString());
    }

    @Test
    public void testGetLoanDetails_Success() {
        when(loanRepository.findById(any(Long.class))).thenReturn(Optional.of(loanEntity));

        LoanEntity foundLoan = loanService.getLoanDetails(1L);

        assertNotNull(foundLoan);
        assertEquals(loanEntity.getLoanId(), foundLoan.getLoanId());
        verify(loanRepository, times(1)).findById(any(Long.class));
    }

    @Test
    public void testGetLoanDetails_NotFound() {
        when(loanRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            loanService.getLoanDetails(1L);
        });

        assertEquals("Loan not found with ID: 1", exception.getMessage());
        verify(loanRepository, times(1)).findById(any(Long.class));
    }

    @Test
    public void testGetAvailableLoanProducts() {
        List<LoanEntity> loanList = Arrays.asList(loanEntity);
        when(loanRepository.findAll()).thenReturn(loanList);

        List<LoanEntity> loans = loanService.getAvailableLoanProducts();

        assertEquals(1, loans.size());
        verify(loanRepository, times(1)).findAll();
    }
}
