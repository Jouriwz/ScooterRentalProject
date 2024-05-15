package ewa.server.service;

import ewa.server.model.User;
import ewa.server.model.UserAuth;
import ewa.server.repository.UserPokemonRepo;
import ewa.server.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo, UserPokemonRepo userPokemonRepo) {
        this.userRepo = userRepo;
    }

    public List<User> findAllUsers(){
        return userRepo.findAll();
    }

    public User find(Integer id){
        return userRepo.findUserById(id);
    }

    public User storeUser(User user) {
        return userRepo.save(user);
    }

    public void deleteUser(Integer id){
        userRepo.deleteUserById(id);
    }

    public User createUser() {
        User user = new User("user1", "seb@seb.com", "netherlands", "m", "password");
        User user2 = new User("nickseb", "u@u.com", "taiwan", "f", "assword");
        userRepo.save(user);
        userRepo.save(user2);
        return user;
    }

    public User findByUsername(String username){
        return userRepo.findByUsername(username);
    }

    public User login(UserAuth user) {
        try{
            return userRepo.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        } catch (Exception e) {
            return null;
        }
    }
}
