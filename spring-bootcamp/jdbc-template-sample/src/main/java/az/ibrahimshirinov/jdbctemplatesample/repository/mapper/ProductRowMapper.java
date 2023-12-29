package az.ibrahimshirinov.jdbctemplatesample.repository.mapper;

import az.ibrahimshirinov.jdbctemplatesample.domain.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Product.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .price(rs.getBigDecimal("price"))
                .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                .modifiedAt(rs.getTimestamp("modified_at").toLocalDateTime())
                .build();
    }
}
