package az.ibrahimshirinov.jdbctemplatesample.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class ProductRequest {
    @NotBlank(message = "Name cannot be null or blank")
    private String name;
    @DecimalMin(value = "10", message = "Price cannot less than 10")
    private BigDecimal price;
}
