package az.grow.lab.jdbctemplatetask4.domain;

import az.grow.lab.jdbctemplatetask4.enums.ActionStatus;
import az.grow.lab.jdbctemplatetask4.enums.FinalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanInformation {

    private Integer id;
    private List<Product> productList;
    private Integer passportId;
    private Integer productId;
    private BigDecimal totalAmount;
    private BigDecimal preAmount;
    private BigDecimal interestRate;
    private ActionStatus actionStatus;
    private FinalStatus finalStatus;

}
