package com.mitrais.onlinetest.registrationapp.repository;
/*
 * Dear Maintainer,
 *
 * When I wrote this code, only I and God knew what it was. Now, only God knows!
 *
 * So, If you're done, trying to 'optimize' this routine (and failed).
 * Please, increment the following counter as a warning to the next guy:
 * total_hours_wasted_here: 999;
 *
 * Sincerely Yours, Hooman
 */

import com.mitrais.onlinetest.registrationapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByMobileNumber(String mobileNumber);
}
