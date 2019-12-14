package com.mitrais.onlinetest.registrationapp.controller;
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
import com.mitrais.onlinetest.registrationapp.exception.ExceptionResponse;
import com.mitrais.onlinetest.registrationapp.exception.ResourceNotFoundException;
import com.mitrais.onlinetest.registrationapp.repository.UserRepository;
import com.mitrais.onlinetest.registrationapp.service.UserServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.HandlerMethod;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.lang.reflect.Parameter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import static com.mitrais.onlinetest.registrationapp.controller.UserControllerTest.asJsonString;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserDuplicateValueTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Validator validator;

    @Before
    public void setup(){
        userRepository = Mockito.mock(UserRepository.class);
    }

    @Test
    public void duplicateEmailTest() throws Exception{
        User user = User.builder()
                .firstName("User1")
                .lastName("Awesome")
                .email("this@mail.com")
                .mobileNumber("62878340000011")
                .build();

        User userNew = User.builder()
                .firstName("User1")
                .lastName("Awesome")
                .email("this@mail.com")
                .mobileNumber("62878340000000")
                .build();

        userRepository.save(user);
        // when
        Set<ConstraintViolation<User>> violations = validator.validate(userNew);
        // then
        assertEquals(1, violations.size());
    }

    @Test
    public void duplicateMobileNumberTest() throws Exception{
        User user = User.builder()
                .firstName("User1")
                .lastName("Awesome")
                .email("this@mail.com")
                .mobileNumber("62878340000000")
                .build();

        User userNew = User.builder()
                .firstName("User1")
                .lastName("Awesome")
                .email("this123@mail.com")
                .mobileNumber("62878340000000")
                .build();

        userRepository.save(user);
        // when
        Set<ConstraintViolation<User>> violations = validator.validate(userNew);
        // then
        assertEquals(1, violations.size());
    }

}
