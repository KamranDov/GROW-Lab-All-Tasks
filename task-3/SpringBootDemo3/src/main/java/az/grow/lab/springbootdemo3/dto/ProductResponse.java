package az.grow.lab.springbootdemo3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private String name;
    private BigDecimal price;
    private LocalDate createdAt;

}
