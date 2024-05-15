package ewa.server.controller;

import ewa.server.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringRunner.class)
class AuthControllerTest {

    // Creates a new instance of mockMvc
    @Autowired
    MockMvc mockMvc;

    //
    @Autowired
    WebApplicationContext webApplicationContext;

    // Unit test for login failure
    @Test
    public void login_failure() throws Exception {
        // new calls MvcResults interface
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                // endpoint URL
                .post("http://localhost:3010/api/auth/login")
                // Header data
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        // variable to hold the response return
        int status = mvcResult.getResponse().getStatus();
        System.out.println(mvcResult.getRequest());
        // if response status not equals 200 return test passed
        assertNotEquals(200, status);
    }

    @Test
    public void register_failure() throws Exception {
        // new calls MvcResults interface
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                // endpoint URL
                .post("http://localhost:3010/api/auth/register")
                // Header data
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        // variable to hold the response return
        int status = mvcResult.getResponse().getStatus();
        System.out.println(mvcResult.getRequest());
        // if response status not equals 200 return test passed
        assertNotEquals(200, status);
    }
}
