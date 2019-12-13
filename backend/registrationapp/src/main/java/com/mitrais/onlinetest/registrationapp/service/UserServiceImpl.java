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
import com.mitrais.onlinetest.registrationapp.entity.User;
import com.mitrais.onlinetest.registrationapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Validator validator;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        try {
            System.out.println("SAVE!");
//        Set<ConstraintViolation<User>> validate = validator.validate(user);
//        System.out.println(validate);
            return userRepository.saveAndFlush(user);
        }catch (Exception e){

            System.out.println("HAH!: " + e.getMessage());
            return null;
        }

    }

    @Override
    public User getUserById(Long userId) throws ResourceNotFoundException{
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }

    @Override
    public User updateUser(Long userId, User userDetail) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        user.setMobileNumber(userDetail.getMobileNumber());
        user.setFirstName(userDetail.getFirstName());
        user.setLastName(userDetail.getLastName());
        user.setEmail(userDetail.getEmail());
        user.setGender(userDetail.getGender());

        User updateduser = userRepository.save(user);
        return updateduser;
    }

    @Override
    public void deleteUser(Long userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        userRepository.delete(user);
    }

    @Override
    public boolean isEmailUnique(Object value, String fieldName) throws Exception {
        if (!fieldName.equals("email")) {
            throw new Exception("Field name not supported");
        }

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
