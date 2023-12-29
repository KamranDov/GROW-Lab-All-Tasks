package az.grow.lab.jdbctemplatetask4.service;

import az.grow.lab.jdbctemplatetask4.domain.User;
import az.grow.lab.jdbctemplatetask4.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class UserDetailsService implements UserRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public User getUserByUsername(int userId) {
        String sql = "SELECT * FROM users WHERE id = :userId";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userId", userId);

        return namedParameterJdbcTemplate.queryForObject(sql, paramMap, (rs, rowNum) ->
                new User(rs.getInt("id"), rs.getString("username"), rs.getString("password")));
    }
}
