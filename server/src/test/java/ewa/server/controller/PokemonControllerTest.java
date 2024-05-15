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
class PokemonControllerTest {

    // Creates a new instance of mockMvc
    @Autowired
    MockMvc mockMvc;

    // Creates a new instance of WebapplicationContext
    @Autowired
    WebApplicationContext webApplicationContext;

    // Variable to hold bearer token needed for authorisation
    String token = JwtUtil.createToken("nickseb");

    @Test
    public void getAllPokemons_success() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("http://localhost:3010/api/pokemon/")
                .header("authorization", token)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void getAllPokemons_failure() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("http://localhost:3010/api/pokemon/")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertNotEquals(200, status);
    }

    @Test
    public void getStarterPokemons_success() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("http://localhost:3010/api/pokemon/starters")
                .header("authorization", token)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        System.out.println(mvcResult.getResponse().getHeader("body"));
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void getStarterPokemons_failure() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("http://localhost:3010/api/pokemon/starters")
                .header("authorization", token)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void getImage_success() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("http://localhost:3010/api/pokemon/images/pokemons/1.gif")
                .header("authorization", token)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertNotEquals(200, status);
    }

    @Test
    public void getImage_failure() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("http://localhost:3010/api/pokemon/images/pokemons/1.gif")
                .header("authorization", token)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertNotEquals(200, status);
    }
}
