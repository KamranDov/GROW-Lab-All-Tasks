package az.grow.lab.jdbctemplatetask4.repository;

import az.grow.lab.jdbctemplatetask4.domain.User;

public interface UserRepository {

    public User getUserByUsername(int userId);
}
