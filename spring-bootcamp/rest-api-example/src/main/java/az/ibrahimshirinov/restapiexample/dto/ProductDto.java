package az.ibrahimshirinov.restapiexample.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {

    private Integer id;
    private String make;
    private String model;
    private BigDecimal price;
}
