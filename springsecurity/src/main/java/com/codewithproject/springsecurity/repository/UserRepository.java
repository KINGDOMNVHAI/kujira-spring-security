package com.codewithproject.springsecurity.repository;

import com.codewithproject.springsecurity.enums.Role;
import com.codewithproject.springsecurity.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE user", nativeQuery = true)
    void truncateTable();

    Optional<User> findByEmail(String username);

    @Query(value = "SELECT id, email, firstname, lastname, role, username, password "
            + " FROM user "
            + " WHERE email = :email "
            + " AND password = :password", nativeQuery = true)
    Optional<User> findByEmailAndPassword(String email, String password);

    User findByRole(Role role);

    @Query(value = "SELECT id, email, firstname, lastname, role, username, password "
            + " FROM user "
            + " WHERE role = :role", nativeQuery = true)
    List<User> getListByRole(Role role);
}
