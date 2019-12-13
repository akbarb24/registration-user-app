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

import com.mitrais.onlinetest.registrationapp.entity.User;
import com.mitrais.onlinetest.registrationapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserValidationServiceImpl implements UserValidationService {
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isEmailUnique(Object value, String fieldName) throws Exception {
        if (!fieldName.equals("email")) {
            throw new Exception("Field name not supported");
        }

        System.out.println("this");
        String email = value.toString();
        Optional<User> userList = userRepository.findByEmail(email);
        return !userList.isPresent();
    }

    @Override
    public boolean isMobileNumberUnique(Object value, String fieldName) throws Exception {
        if (!fieldName.equals("mobileNumber")) {
            throw new Exception("Field name not supported");
        }

        String mobileNumber = value.toString();
        Optional<User> userList = userRepository.findByMobileNumber(mobileNumber);
        return !userList.isPresent();
    }
}
