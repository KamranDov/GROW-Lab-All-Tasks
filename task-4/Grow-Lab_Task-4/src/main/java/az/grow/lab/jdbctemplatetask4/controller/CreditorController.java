package az.grow.lab.jdbctemplatetask4.controller;

import az.grow.lab.jdbctemplatetask4.domain.PassportInformation;
import az.grow.lab.jdbctemplatetask4.dto.LoanDto;
import az.grow.lab.jdbctemplatetask4.dto.PersonalDto;
import az.grow.lab.jdbctemplatetask4.exception.NotApprovedException;
import az.grow.lab.jdbctemplatetask4.service.CreditorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/creditor/")
public class CreditorController {

    private final CreditorService creditorService;

    @PostMapping("/check-identity/")
    public ResponseEntity<String> checkIdentity(@RequestBody PassportInformation passportInformation){
        creditorService.checkIdentity(passportInformation);
    return ResponseEntity.ok("Identity check successful.");
    }

    @PostMapping("/initial-approve/")
    public ResponseEntity<String> initialApprove(@RequestBody PersonalDto personalDto){
        creditorService.initialApprove(personalDto);
     return ResponseEntity.ok("Authentication completed successfully.");
    }

    @PostMapping("/final-approve/")
    public ResponseEntity<String> finalApprove(@RequestBody LoanDto loanDto){
        try {
            creditorService.finalApprove(loanDto);
            return ResponseEntity.ok("Loan application approved for final review.");
        }catch (NotApprovedException exception){
            String errorMessage = "Loan application is not approved for final review: " + exception.getMessage();
            return ResponseEntity.badRequest().body(errorMessage);
        }
    }
}
