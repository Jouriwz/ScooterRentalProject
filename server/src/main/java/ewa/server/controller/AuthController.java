package ewa.server.controller;

import ewa.server.model.User;
import ewa.server.model.UserAuth;
import ewa.server.service.PokemonService;
import ewa.server.service.UserService;
import ewa.server.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth/")
public class AuthController {
    private final UserService userService;
    private final PokemonService pokemonService;

    @Autowired
    public AuthController(UserService userService, PokemonService pokemonService) {
        this.userService = userService;
        this.pokemonService = pokemonService;
    }

    @GetMapping("check/token")
    public void checkToken(){}

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserAuth user){
        User u = userService.login(user);
        if (u != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(JwtUtil.createToken(u.getUsername()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user found with those credentials.");
        }
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody User user){
        try{
            userService.storeUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(JwtUtil.createToken(user.getUsername()));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
