package com.apollogix.managerskill.repository;

import com.apollogix.managerskill.entity.MUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<MUser, Integer> {

    /**
     * Finds a user by their email.
     *
     * @param email the email of the user to find
     * @return the user with the specified email, or null if not found
     */
    MUser findByEmail(String email);

    /**
     * Checks if a user with the specified email exists.
     *
     * @param email the email to check
     * @return true if a user with the specified email exists, false otherwise
     */
    Boolean existsByEmail(String email);
}