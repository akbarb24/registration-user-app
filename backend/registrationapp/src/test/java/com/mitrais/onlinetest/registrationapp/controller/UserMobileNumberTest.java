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
public class UserMobileNumberTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    @Test
    public void createUserWithoutMobileNumberTest() throws Exception {
        User user = User.builder()
                .firstName("User1")
                .lastName("Awesome")
                .email("this@mail.com")
                .build();

        Mockito.when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform( MockMvcRequestBuilders
                .post("/v0/api/users")
                .content(asJsonString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Validation Error!"))
                .andExpect(jsonPath("$.errorDetails.mobileNumber").value("Mobile number is required"));
    }

    @Test
    public void createUserWithInvalidMobileNumberFormatTest() throws Exception {
        User user = User.builder()
                .firstName("User1")
                .lastName("Awesome")
                .mobileNumber("087834000808")
                .email("this@mail.com")
                .build();

        Mockito.when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform( MockMvcRequestBuilders
                .post("/v0/api/users")
                .content(asJsonString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Validation Error!"))
                .andExpect(jsonPath("$.errorDetails.mobileNumber").value(
                        "Invalid Indonesian Mobile Number Format\n(Start with '62' and followed by 9-12 digits)"));
    }

    @Test
    public void createUserWithInvalidMobileNumberFormatTest2() throws Exception {
        User user = User.builder()
                .firstName("User1")
                .lastName("Awesome")
                .mobileNumber("6287834000808000000")
                .email("this@mail.com")
                .build();

        Mockito.when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform( MockMvcRequestBuilders
                .post("/v0/api/users")
                .content(asJsonString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Validation Error!"))
                .andExpect(jsonPath("$.errorDetails.mobileNumber").value(
                        "Invalid Indonesian Mobile Number Format\n(Start with '62' and followed by 9-12 digits)"));
    }

    @Test
    public void createUserWithInvalidMobileNumberFormatTest3() throws Exception {
        User user = User.builder()
                .firstName("User1")
                .lastName("Awesome")
                .mobileNumber("6287834000abc")
                .email("this@mail.com")
                .build();

        Mockito.when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform( MockMvcRequestBuilders
                .post("/v0/api/users")
                .content(asJsonString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Validation Error!"))
                .andExpect(jsonPath("$.errorDetails.mobileNumber").value(
                        "Invalid Indonesian Mobile Number Format\n(Start with '62' and followed by 9-12 digits)"));
    }
}
