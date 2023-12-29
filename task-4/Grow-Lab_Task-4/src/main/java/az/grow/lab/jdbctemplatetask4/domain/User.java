package az.grow.lab.jdbctemplatetask4.domain;

import az.grow.lab.jdbctemplatetask4.enums.Authority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
    private Integer id;
    private String username;
    private String password;
    private String isActive;
    private Authority authority;

    public User(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
