package az.grow.lab.jdbctemplatetask4.repository;

import az.grow.lab.jdbctemplatetask4.domain.Product;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class ProductRepo {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void saveProductList(List<Product> productList) {
        for (Product product : productList) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            String query = "INSERT INTO product (name, price) VALUES (:name, :price)";
            namedParameterJdbcTemplate.update(query, new MapSqlParameterSource()
                            .addValue("name", product.getName())
                            .addValue("price", product.getPrice())
                    , keyHolder);
            int productId = keyHolder.getKey().intValue();
            product.setId(productId);
        }
    }

    public Product getProductById(Integer id) {
        String query = "SELECT * FROM product WHERE id = :ID";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("ID", id);
        return namedParameterJdbcTemplate.queryForObject(query, mapSqlParameterSource, (resultSet, rowNumber) ->
        {
            Product product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setName(resultSet.getString("name"));
            product.setPrice(resultSet.getBigDecimal("price"));
            return product;
        });
    }
}
