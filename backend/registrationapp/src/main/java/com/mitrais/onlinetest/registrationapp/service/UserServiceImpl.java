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
import com.mitrais.onlinetest.registrationapp.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
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
}
