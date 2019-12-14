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
import com.mitrais.onlinetest.registrationapp.service.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.mitrais.onlinetest.registrationapp.controller.UserControllerTest.asJsonString;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserEmailTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    @Test
    public void createUserWithoutEmailTest() throws Exception {
        User user = User.builder()
                .firstName("User1")
                .lastName("Awesome")
                .mobileNumber("62878340000000")
                .build();

        Mockito.when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform( MockMvcRequestBuilders
                .post("/v0/api/users")
                .content(asJsonString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Validation Error!"))
                .andExpect(jsonPath("$.errorDetails.email").value("Email is required"));
    }

    @Test
    public void createUserWithInvalidEmailFormatTest() throws Exception {
        User user = User.builder()
                .firstName("User1")
                .lastName("Awesome")
                .mobileNumber("62878340000000")
                .email("mail.com")
                .build();

        Mockito.when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform( MockMvcRequestBuilders
                .post("/v0/api/users")
                .content(asJsonString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Validation Error!"))
                .andExpect(jsonPath("$.errorDetails.email").value("Invalid email format"));
    }
}
