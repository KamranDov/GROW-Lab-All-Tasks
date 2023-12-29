package az.ibrahimshirinov.jdbctemplatesample.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductResponse {
    private String name;
    private BigDecimal price;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
