package az.ibrahimshirinov.jdbctemplatesample.repository;

import az.ibrahimshirinov.jdbctemplatesample.domain.Product;
import az.ibrahimshirinov.jdbctemplatesample.repository.mapper.ProductRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    public void insert(Product product) {

        var keyHolder = new GeneratedKeyHolder();

        var query = "INSERT INTO PRODUCT(name,price,created_at) VALUES (:name,:price,:createdAt)";
        jdbcTemplate.update(query, new MapSqlParameterSource()
                        .addValue("name", product.getName())
                        .addValue("price", product.getPrice())
                        .addValue("createdAt", product.getCreatedAt())
                , keyHolder);
    }

    public Optional<Product> find(Integer id) {

        var query = "SELECT * FROM PRODUCT WHERE id=:id";

        try {
            var product = jdbcTemplate
                    .queryForObject(query,
                            new MapSqlParameterSource().addValue("id", id),
                            new ProductRowMapper());
            return Optional.ofNullable(product);
        } catch (EmptyResultDataAccessException ex) {
            log.error("Product not found with id: {}", id);
            return Optional.empty();
        }
    }

    public List<Product> find() {
        var query = "SELECT * FROM PRODUCT";
        return jdbcTemplate.query(query, new ProductRowMapper());
    }

    public void update(Integer id, Product product) {
        var query = "UPDATE PRODUCT SET name=:name, price=:price, created_at=:createdAt, modified_at=:modifiedAt WHERE id=:id";
        jdbcTemplate.update(query, new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("name", product.getName())
                .addValue("price", product.getPrice())
                .addValue("createdAt", product.getCreatedAt())
                .addValue("modifiedAt", product.getModifiedAt())
        );
    }

    public void delete(Integer id) {
        var query = "DELETE FROM PRODUCT WHERE id = :id";
        try {
            jdbcTemplate.update(query, new MapSqlParameterSource()
                    .addValue("id", id));
        } catch (EmptyResultDataAccessException e) {
            log.error("Product not found with id: {}", id);
        }

    }
}
