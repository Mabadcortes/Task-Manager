package com.mabadcortes.taskmanager.repository;

import com.mabadcortes.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    /*
     * Retrieves a user by their exact username.
     * Essential for the authentication process.
     */
    Optional<User> findByUsername(String username);
}
