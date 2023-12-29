package az.grow.lab.springbootdemo2.dto;

import lombok.*;

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
