package az.grow.lab.jdbctemplatetask4.controller;

import az.grow.lab.jdbctemplatetask4.enums.ConfirmStatus;
import az.grow.lab.jdbctemplatetask4.exception.QueryNullException;
import az.grow.lab.jdbctemplatetask4.service.LeadService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/lead")
public class LeadController {

    private final LeadService leadService;

    @PostMapping("/identity-status/{id}")
    public ResponseEntity<String> updatePassportActionStatus(@PathVariable(name = "id") Integer id,
                                                             @RequestParam(value = "Confirm status") ConfirmStatus confirmStatus,
                                                             @RequestParam(value = "Reject reason", required = false) String rejectReason) {
        try {
            leadService.assignPassportActionStatus(id, confirmStatus, rejectReason);
            return ResponseEntity.ok("Identity status updated.");
        } catch (QueryNullException ex) {
            String errorMessage = "an error occurred: " + ex.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @PostMapping("initial-status/{id}")
    public ResponseEntity<String> updatePersonalActionStatus(@PathVariable(name = "id") Integer id,
                                                             @RequestParam(value = "Confirm status") ConfirmStatus confirmStatus,
                                                             @RequestParam(value = "Reject reason", required = false) String rejectReason) {
        try {
            leadService.assignPersonalActionStatus(id, confirmStatus, rejectReason);
            return ResponseEntity.ok("Personal status updated.");
        } catch (QueryNullException ex) {
            String errorMessage = "an error occurred: " + ex.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }


    @PostMapping("final-status/{id}")
    public ResponseEntity<String> updateLoanActionStatus(@PathVariable(name = "id") Integer id,
                                                         @RequestParam(value = "Confirm status") ConfirmStatus confirmStatus,
                                                         @RequestParam(value = "Reject reason", required = false) String rejectReason) {
        try {
            leadService.assignLoanActionStatus(id, confirmStatus, rejectReason);
            return ResponseEntity.ok("Loan status updated.");
        } catch (QueryNullException ex) {
            String errorMessage = "an error occurred: " + ex.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
}
