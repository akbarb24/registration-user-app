package com.mitrais.onlinetest.registrationapp.service;
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


import com.mitrais.onlinetest.registrationapp.exception.ResourceNotFoundException;
import com.mitrais.onlinetest.registrationapp.persistence.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User createUser(User user);
    User getUserById(Long userId) throws ResourceNotFoundException;
    User updateUser(Long userId, User userDetail) throws ResourceNotFoundException;
    void deleteUser(Long userId) throws ResourceNotFoundException;
}
