package org.test.canema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.canema.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String Email);
}
