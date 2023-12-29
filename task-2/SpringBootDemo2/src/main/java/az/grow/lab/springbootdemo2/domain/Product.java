package az.grow.lab.springbootdemo2.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private Integer Id;
    private String name;
    private BigDecimal price;
    private LocalDate createdAt;
}
