package ewa.server.repository;

import ewa.server.model.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)

class UserRepoTest {

    // instance of user repository
    @Autowired
    UserRepo UserRepo;

    @Test
    void findUserById_success() {
        User userId = UserRepo.findUserById(2);
        assertEquals(2, userId.getId());
    }

    @Test
    void findUserById_failure() {
        User userId = UserRepo.findUserById(2);
        assertNotEquals(3, userId.getId());
    }


    @Test
    void findByUsername_success() {
        User userName = UserRepo.findByUsername("nickseb");
        assertEquals("nickseb", userName.getUsername());
    }

    @Test
    void findByUsername_failure() {
        User userName = UserRepo.findByUsername("nickseb");
        assertNotEquals("", userName.getUsername());
    }

    @Test
    void findByUsernameAndPassword_success() {
        User user = new User("Jouri", "test2@test3.nl", "nl", "m", "testtest");
        assertEquals(user.getPassword(), "testtest");
    }

    @Test
    void findByUsernameAndPassword_failure() {
        User user = new User("Jouri", "test2@test3.nl", "nl", "m", "testtest");
        assertNotEquals(user.getPassword(), "");
    }
}
