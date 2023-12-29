package az.ibrahimshirinov.jdbctemplatesample.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class Product {

    private Integer id;
    private String name;
    private BigDecimal price;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
