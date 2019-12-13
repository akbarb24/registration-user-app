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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @InjectMocks UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void createUserTest() throws Exception{
        User user = User.builder().id(1L)
                .firstName("Richard")
                .lastName("Hendricks")
                .email("this@mail.com")
                .mobileNumber("08080")
                .gender("M")
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        Mockito.when(userRepository.save(user)).thenReturn(user);

        User serviceUser = userService.createUser(user);

        assertEquals(user.getId(), serviceUser.getId());
        assertEquals(user.getFirstName(), serviceUser.getFirstName());
        assertEquals(user.getLastName(), serviceUser.getLastName());
    }

    @Test
    public void getUserByIdTest() throws Exception {
        User user = User.builder().id(1L)
                .firstName("Richard")
                .lastName("Hendricks")
                .email("this@mail.com")
                .mobileNumber("08080")
                .gender("M")
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        User serviceUser = userService.getUserById(1L);

        assertEquals("Richard" , serviceUser.getFirstName());
        assertEquals("Hendricks", serviceUser.getLastName());
        assertEquals("this@mail.com", serviceUser.getEmail());
    }

    @Test
    public void getAllUserTest() throws Exception{
        User user = User.builder().id(1L)
                .firstName("User 1")
                .lastName("Awesome")
                .email("this@mail.com")
                .mobileNumber("08080")
                .gender("M")
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        User user1 = User.builder().id(1L)
                .firstName("User 2")
                .lastName("Awesome")
                .email("that@mail.com")
                .mobileNumber("08180")
                .gender("M")
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        User user2 = User.builder().id(1L)
                .firstName("User 3")
                .lastName("Awesome")
                .email("thus@mail.com")
                .mobileNumber("08280")
                .gender("M")
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        List<User> userList = Stream.of(user,user1,user2).collect(Collectors.toList());
        Mockito.when(userRepository.findAll()).thenReturn(userList);

        List<User> serviceList = userService.findAll();

        assertEquals(userList.size(), serviceList.size());

    }
}
