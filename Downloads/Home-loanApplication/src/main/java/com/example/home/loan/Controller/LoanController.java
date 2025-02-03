package com.example.home.loan.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.home.loan.Entity.LoanEntity;
import com.example.home.loan.service.LoanService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/api/loans")
public class LoanController {

	@Autowired
	private LoanService loanService;


	@PostMapping("/add")
	public ResponseEntity<LoanEntity> addLoan(@RequestBody LoanEntity loan) {
		LoanEntity newlLoan = loanService.addLoan(loan);
		return ResponseEntity.ok(newlLoan);

	}

	@PutMapping("/update")
	public ResponseEntity<LoanEntity> updateLoan(@RequestBody LoanEntity loan) {
		LoanEntity updateLoan = loanService.updateLoan(loan);
		return ResponseEntity.ok(updateLoan);

	}

	@GetMapping("/user/{email}")
	public ResponseEntity<List<LoanEntity>> getLoansByUserEmail(@PathVariable String email) {
		List<LoanEntity> loans = loanService.getLoansByUserEmail(email);
		return ResponseEntity.ok(loans);
	}

	/*
	 * @GetMapping("/dashboard") public String showDashboard(Model model) {
	 * List<LoanEntity> loans = new ArrayList<>(); LoanEntity loan = new
	 * LoanEntity(null, 0, null, 0, 0, null, 0, 0, 0, 0, 0, null, null);
	 * loan.setLoanId((long) 1234); loan.setLoanType("Home Loan");
	 * loan.setLoanAmt(250000); loan.setIncome(400000); loan.setTenure(2);
	 * loans.add(loan);
	 * 
	 * System.out.println("Adding loans to model: " + loans);
	 * model.addAttribute("loans", loans); return "dashboard"; }
	 */

	@GetMapping("/dashboard")
	public String showDashboard(Model model) {
		List<LoanEntity> loans = loanService.getAllLoans();
		model.addAttribute("loans", loans);
		return "dashboard";
	}

	
	
	
	@GetMapping("/loanDetails/{loanId}")
	public String viewLoanDetails(@PathVariable("loanId") Long loanId, Model model) {
		System.out.println("Fetching Loan with ID: " + loanId);
		LoanEntity loanEntity = loanService.getLoanById(loanId);

		if (loanEntity == null) {
			System.out.println("Loan not found with ID: " + loanId);
			model.addAttribute("error", "Loan not found");
			return "errorPage"; // If loan not found, handle with error
		}

		System.out.println("Loan retrieved: " + loanEntity.toString());
		model.addAttribute("loanEntity", loanEntity);
		return "loanDetails";
	}

	/*
	 * 
	 * @GetMapping("/loanDetails/{loanId}") public String
	 * viewLoanDetails(@PathVariable("loanId") Long loanId, Model model) { try {
	 * LoanEntity loanEntity = loanService.getLoanById(loanId);
	 * model.addAttribute("loanEntity", loanEntity); return "loanDetails"; } catch
	 * (Exception e) { model.addAttribute("error", "Loan details not found"); return
	 * "redirect:/dashboard"; // Redirect back to dashboard if loan details nfound }
	 * }
	 * 
	 */

	@GetMapping("/newHomeLoan")
	public String newHomeLoanPage(Model model) {
		List<LoanEntity> loanProducts = loanService.getAvailableLoanProducts();
		model.addAttribute("loanProducts", loanProducts);
		return "newHomeLoan";
	}
	
		
	

	@GetMapping("/loanApplication")
	public String getLoanApplicationPage(
			@RequestParam(name = "loanType", required = false, defaultValue = "") String loanType, Model model) {
		System.out.println("Loan Type received: " + loanType); // For debugging purposes
		model.addAttribute("loanType", loanType); // Adding loanType to the model
		return "loanApplication"; 
	}

	/*
	 * @GetMapping("/loanApplication") public String
	 * showLoanApplicationPage(@RequestParam("loanType") String loanType, Model
	 * model) { // Add loanType to the model so it can be used in the view
	 * model.addAttribute("loanType", loanType); return "loanApplication"; // The
	 * name of your HTML view (loanApplication.html) }
	 */

	@GetMapping("/applyLoan/{loanId}")
	public String showLoanApplicationPage(@PathVariable Long loanId, Model model) {
		LoanEntity loan = loanService.getLoanDetails(loanId);
		model.addAttribute("loanType", loan.getLoanType());
		return "loanApplication"; 
	}

	@GetMapping("/applyLoan")
	public String applyLoan(@RequestParam("loanType") String loanType, Model model) {
		// Add the loan type to the model
		model.addAttribute("loanType", loanType);
		return "loanApplication"; 
	}

	/*
	 * @PostMapping("/submitLoanApplication") public String
	 * submitLoanApplication(@ModelAttribute LoanEntity loanEntity) { // Check if
	 * the loanType is correctly passed System.out.println("Loan Type: " +
	 * loanEntity.getLoanType()); System.out.println("Loan Amount: " +
	 * loanEntity.getLoanAmt()); System.out.println("Tenure: " +
	 * loanEntity.getTenure()); System.out.println("Income: " +
	 * loanEntity.getIncome());
	 * 
	 * loanService.applyForNewLoan(loanEntity); return
	 * "redirect:/api/loans/dashboard"; }
	 * 
	 */
	
	
	@PostMapping("/submitLoanApplication")
	public String submitLoanApplication(@RequestParam("loanType") String loanType,
			@RequestParam("loanAmt") Double loanAmt,
			@RequestParam("tenure") int tenure,
			@RequestParam("nomineeDetails") String nomineeDetails,
			@RequestParam("income") double income, 
			RedirectAttributes redirectAttributes) {

		System.out.println("Loan Type: " + loanType);
		System.out.println("Loan Amount: " + loanAmt);
		System.out.println("Tenure: " + tenure);
		System.out.println("Income: " + income);

		if (loanAmt == null) {
			System.err.println("loanAmt is null!");
			redirectAttributes.addFlashAttribute("error", "Loan amount is required.");
			return "redirect:/api/loans/dashboard";
		}

		try {
			LoanEntity loan = new LoanEntity();
			loan.setLoanType(loanType);
			loan.setLoanAmt(loanAmt);
			loan.setTenure(tenure);
			loan.setNomineeDetails(nomineeDetails);
			loan.setIncome(income);
			loan.setCurrentRateOfInterest(10.0); 
			loan.generateRandomLoanId();

			loanService.addLoan(loan); // Save the loan

			redirectAttributes.addFlashAttribute("loan", loan);
			return "redirect:/api/loans/dashboard";
		} catch (Exception e) {
			System.err.println("Error during loan submission: " + e.getMessage());
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", "Error while submitting loan application.");
			return "redirect:/api/loans/dashboard";
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteLoan(@PathVariable Long id) {
		loanService.deleteLoan(id);
		return ResponseEntity.noContent().build();
	}

	
	
	@GetMapping("/eligibilityCalculator")
	public String showEligibilityCalculator() {
	    return "eligibilityCalculator";  
	}
	

	@PostMapping("/calculateEligibility")
	public String calculateEligibility(
	    @RequestParam("monthlySalary") double monthlySalary,
	    @RequestParam("cibilScore") int cibilScore,
	    @RequestParam("existingLoanAmount") double existingLoanAmount,
	    Model model) {

	    try {
	        // Add logs to track input parameters
	        System.out.println("Monthly Salary: " + monthlySalary);
	        System.out.println("CIBIL Score: " + cibilScore);
	        System.out.println("Existing Loan Amount: " + existingLoanAmount);

	        // Loan eligibility logic
	        String eligibilityMessage = ""; 
	        double maxLoanAmount = 0;

	        // CIBIL Score based eligibility check
	        if (cibilScore < 600) {
	            eligibilityMessage = "Unfortunately, you are not eligible for a loan due to a low CIBIL score.";
	        } else if (monthlySalary < 20000) {
	            eligibilityMessage = "Unfortunately, your monthly salary does not meet the minimum criteria for loan eligibility.";
	        } else {
	            // Calculate loan eligibility based on salary and existing loans
	            if (monthlySalary >= 20000 && monthlySalary <= 40000) {
	                maxLoanAmount = 500000; // Max loan for mid-level salary
	            } else if (monthlySalary > 40000 && monthlySalary <= 70000) {
	                maxLoanAmount = 1000000; // Max loan for higher salary
	            } else if (monthlySalary > 70000) {
	                maxLoanAmount = 2000000; // Max loan for very high salary
	            }

	            // Adjust loan eligibility if there is an existing loan amount
	            if (existingLoanAmount > 0) {
	                maxLoanAmount -= existingLoanAmount * 0.5; // Reducing loan eligibility due to existing loans
	                eligibilityMessage = "You are eligible for a loan. However, your existing loan reduces the total amount.";
	            } else {
	                eligibilityMessage = "Congratulations! You are eligible for the loan.";
	            }
	        }

	        // Set final eligibility message and max loan amount in the model
	        if (maxLoanAmount > 0) {
	            eligibilityMessage += " The maximum loan amount you are eligible for is: ₹" + maxLoanAmount;
	        } else if (eligibilityMessage.isEmpty()) {
	            eligibilityMessage = "Unfortunately, you are not eligible for a loan.";
	        }

	        model.addAttribute("eligibilityMessage", eligibilityMessage);

	    } catch (Exception e) {
	        // Add detailed error logging
	        System.err.println("Error occurred while calculating eligibility: " + e.getMessage());
	        e.printStackTrace();
	        
	        model.addAttribute("errorMessage", "An error occurred while calculating eligibility.");
	    }

	    return "eligibilityCalculator"; 
	}

	private String postedQuery;

	
	 @GetMapping("/raiseRequest")
	    public String showRaiseRequestForm(Model model) {
	        model.addAttribute("postedQuery", postedQuery); // Send the posted query back to the view
	        return "raise-request"; 
	    }


	 @PostMapping("/submitRequest")
	    public String submitRequest(
	        @RequestParam("customerName") String customerName,
	        @RequestParam("email") String email,
	        @RequestParam("query") String query,
	        		Model model) {

	        // Capture the submitted query
	        postedQuery = query;  // Store it in the class variable
	        

	        // Add the query to the model to display on the raiseRequest page
	        model.addAttribute("postedQuery", postedQuery);

	        // Optionally, log the query details for debugging
	        System.out.println("Customer Name: " + customerName);
	        System.out.println("Email: " + email);
	        System.out.println("Query: " + query);


	        return "/raise-request";
	    }
	

	@GetMapping("/all")
	public ResponseEntity<List<LoanEntity>> getAllLoans() {
		List<LoanEntity> loans = loanService.getAllLoans();
		return ResponseEntity.ok(loans);

	}
				
	
	
	public ResponseEntity<LoanEntity> getLoanById(@PathVariable Long id) throws Exception {
		LoanEntity loan = loanService.getLoanById(id);
		return ResponseEntity.ok(loan);

	}
	
	public class CustomErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

		@RequestMapping("/error")
		public String handleError(HttpServletRequest request, Model model) {
			Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

			// Pass the status code to the view
			model.addAttribute("status", status);

			return "error";
		}
	}

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request, Model model) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		Object exception = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

		// Optionally log the exception
		if (exception != null) {
			Throwable throwable = (Throwable) exception;
			throwable.printStackTrace();
		}

		model.addAttribute("status", status);
		return "error";
	}

}