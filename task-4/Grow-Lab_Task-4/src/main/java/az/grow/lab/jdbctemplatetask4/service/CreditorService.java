package az.grow.lab.jdbctemplatetask4.service;

import az.grow.lab.jdbctemplatetask4.domain.LoanInformation;
import az.grow.lab.jdbctemplatetask4.domain.PassportInformation;
import az.grow.lab.jdbctemplatetask4.domain.PersonalInformation;
import az.grow.lab.jdbctemplatetask4.domain.Product;
import az.grow.lab.jdbctemplatetask4.dto.LoanDto;
import az.grow.lab.jdbctemplatetask4.dto.PersonalDto;
import az.grow.lab.jdbctemplatetask4.enums.ActionStatus;
import az.grow.lab.jdbctemplatetask4.enums.FinalStatus;
import az.grow.lab.jdbctemplatetask4.exception.LoanNotFoundException;
import az.grow.lab.jdbctemplatetask4.exception.NotApprovedException;
import az.grow.lab.jdbctemplatetask4.exception.ProductListException;
import az.grow.lab.jdbctemplatetask4.repository.LoanRepo;
import az.grow.lab.jdbctemplatetask4.repository.PassportRepo;
import az.grow.lab.jdbctemplatetask4.repository.PersonalRepo;
import az.grow.lab.jdbctemplatetask4.repository.ProductRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CreditorService {

    private final PassportRepo passportRepo;
    private final PersonalRepo personalRepo;
    private final LoanRepo loanRepo;
    private final ProductRepo productRepo;
    private final ModelMapper mapper;

    public void checkIdentity(PassportInformation passportInformation) {
        passportInformation.setActionStatus(ActionStatus.WAITING_FOR_IDENTITY_APPROVE);
        passportRepo.savePassport(passportInformation);
        ActionStatus saveActionStatus = passportInformation.getActionStatus();
        log.info("Passport information saved successfully. Action status: {}", saveActionStatus);
    }

    public void initialApprove(PersonalDto personalDto) {
        PersonalInformation personalInformation = mapper.map(personalDto, PersonalInformation.class);
        personalDto.setActionStatus(ActionStatus.IDENTITY_CHECK_APPROVED);
        personalRepo.savePersonal(personalInformation);
        if (personalDto.getActionStatus() != ActionStatus.IDENTITY_CHECK_APPROVED) {
            throw new NotApprovedException("ID verification failed.");
        }
        ActionStatus saveActionStatus = personalDto.getActionStatus();
        log.info("Personal information saved successfully. Action status: {}", saveActionStatus);
    }

    public void finalApprove(LoanDto loanDto) {
        LoanInformation loanInformation = mapper.map(loanDto, LoanInformation.class);
        List<Product> productList = loanDto.getProductList();
        if (productList == null || productList.isEmpty()) {
            throw new ProductListException("Product list cannot be null or empty");
        }
        productRepo.saveProductList(productList);
        loanInformation.setProductList(productList);

        loanDto.setActionStatus(ActionStatus.INITIAL_CHECK_APPROVED);
        if (loanDto.getActionStatus() != ActionStatus.INITIAL_CHECK_APPROVED) {
            throw new NotApprovedException("Loan application is not approved for final review");
        }
        LoanDto existingLoanDto = loanRepo.getLoanDtoById(loanDto.getId());
        if (existingLoanDto == null) {
            throw new LoanNotFoundException("Loan not found with ID: " + loanDto.getId());
        }
        BigDecimal totalAmount = calculateTotalAmount(
                loanDto.getProductList()
                , existingLoanDto.getPreAmount()
                , existingLoanDto.getInterestRate());
        loanDto.setTotalAmount(totalAmount);
        loanInformation.setActionStatus(ActionStatus.WAITING_FOR_FINAL_APPROVE);
        loanInformation.setFinalStatus(FinalStatus.COMPLETED);
        loanRepo.saveLoan(loanInformation);


        ActionStatus saveActionStatus = loanDto.getActionStatus();
        log.info("Loan information saved successfully. Action status: {}", saveActionStatus);
    }

    private BigDecimal calculateTotalAmount(List<Product> productList, BigDecimal preAmount, BigDecimal interestRate) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Product product : productList) {
            totalPrice = totalPrice.add(product.getPrice());
        }
        BigDecimal interestAmount = totalPrice.subtract(preAmount).multiply(interestRate);
        return totalPrice.add(interestAmount);
    }

}
