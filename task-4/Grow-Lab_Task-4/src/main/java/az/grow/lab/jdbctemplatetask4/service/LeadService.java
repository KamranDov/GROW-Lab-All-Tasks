package az.grow.lab.jdbctemplatetask4.service;

import az.grow.lab.jdbctemplatetask4.enums.ConfirmStatus;
import az.grow.lab.jdbctemplatetask4.repository.LoanRepo;
import az.grow.lab.jdbctemplatetask4.repository.PassportRepo;
import az.grow.lab.jdbctemplatetask4.repository.PersonalRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class LeadService {

    private final PassportRepo passportRepo;
    private final PersonalRepo personalRepo;
    private final LoanRepo loanRepo;
    public void assignPassportActionStatus(Integer id, ConfirmStatus confirmStatus, String rejectReason) {
        passportRepo.updatePassportActionStatus(id, confirmStatus, rejectReason);
    }
    public void assignPersonalActionStatus(Integer id, ConfirmStatus confirmStatus, String rejectReason){
        personalRepo.updatePersonalActionStatus(id, confirmStatus, rejectReason);
    }
    public void assignLoanActionStatus(Integer id, ConfirmStatus confirmStatus, String rejectReason){
        loanRepo.updateLoanActionStatus(id, confirmStatus, rejectReason);
    }
}
