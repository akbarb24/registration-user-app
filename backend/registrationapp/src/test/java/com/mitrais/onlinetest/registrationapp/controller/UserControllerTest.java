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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitrais.onlinetest.registrationapp.entity.User;
import com.mitrais.onlinetest.registrationapp.service.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@EnableAutoConfiguration
//@EntityScan(basePackages = { "com.mitrais.registrationapp" })
//@WebMvcTest(UserController.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    @Test
    public void getAllUsersTest() throws Exception {

        User user = User.builder().id(1L)
                .firstName("User 1")
                .lastName("Awesome")
                .email("this@mail.com")
                .mobileNumber("627889098908")
                .gender("M")
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        User user1 = User.builder().id(1L)
                .firstName("User 2")
                .lastName("Awesome")
                .email("that@mail.com")
                .mobileNumber("627889098909")
                .gender("M")
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        User user2 = User.builder().id(1L)
                .firstName("User 3")
                .lastName("Awesome")
                .email("thus@mail.com")
                .mobileNumber("627889098900")
                .gender("M")
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        List<User> userList = Stream.of(user,user1,user2).collect(Collectors.toList());
        Mockito.when(userService.findAll()).thenReturn(userList);

        mockMvc.perform( MockMvcRequestBuilders
                .get("/v0/api/users")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{}, {}, {}]"));
    }

    @Test
    public void createUserWithFullContentTest() throws Exception {

        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");

        User user = User.builder()
                .firstName("User1")
                .lastName("Awesome")
                .email("this@mail.com")
                .mobileNumber("62878340000000")
                .gender("M")
                .birthDate(formatter.parse("1999-12-01"))
                .build();

        Mockito.when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform( MockMvcRequestBuilders
                .post("/v0/api/users")
                .content(asJsonString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName").value("Awesome"))
                .andExpect(jsonPath("$.firstName").value("User1"));
    }

    @Test
    public void createUserWithoutFirstNameTest() throws Exception {

        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");

        User user = User.builder()
                .lastName("Awesome")
                .email("this@mail.com")
                .mobileNumber("62878340000000")
                .gender("M")
                .birthDate(formatter.parse("1999-12-01"))
                .build();

        Mockito.when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform( MockMvcRequestBuilders
                .post("/v0/api/users")
                .content(asJsonString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Validation Error!"))
                .andExpect(jsonPath("$.errorDetails.firstName").value("First name is required"));
    }

    @Test
    public void createUserWithoutLastNameTest() throws Exception {

        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");

        User user = User.builder()
                .firstName("User1")
                .email("this@mail.com")
                .mobileNumber("62878340000000")
                .gender("M")
                .birthDate(formatter.parse("1999-12-01"))
                .build();

        Mockito.when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform( MockMvcRequestBuilders
                .post("/v0/api/users")
                .content(asJsonString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Validation Error!"))
                .andExpect(jsonPath("$.errorDetails.lastName").value("Last name is required"));
    }

    @Test
    public void createUserWithoutGenderTest() throws Exception {

        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");

        User user = User.builder()
                .firstName("User1")
                .lastName("Awesome")
                .email("this@mail.com")
                .mobileNumber("62878340000000")
                .birthDate(formatter.parse("1999-12-01"))
                .build();

        Mockito.when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform( MockMvcRequestBuilders
                .post("/v0/api/users")
                .content(asJsonString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName").value("Awesome"))
                .andExpect(jsonPath("$.firstName").value("User1"));
    }

    @Test
    public void createUserWithoutBirthDateTest() throws Exception {

        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");

        User user = User.builder()
                .firstName("User1")
                .lastName("Awesome")
                .email("this@mail.com")
                .mobileNumber("62878340000000")
                .gender("M")
                .build();

        Mockito.when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform( MockMvcRequestBuilders
                .post("/v0/api/users")
                .content(asJsonString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName").value("Awesome"))
                .andExpect(jsonPath("$.firstName").value("User1"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
