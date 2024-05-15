package ewa.server.controller;

import ewa.server.model.Pokemon;
import ewa.server.model.User;
import ewa.server.model.UserPokemon;
import ewa.server.service.PokemonService;
import ewa.server.service.UserPokemonService;
import ewa.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("api/user/")
public class UserController {
    private final UserService userService;
    private final PokemonService pokemonService;
    private final UserPokemonService userPokemonService;

    @Autowired
    public UserController(UserService userService, PokemonService pokemonService, UserPokemonService userPokemonService) throws FileNotFoundException {
        this.userService = userService;
        this.pokemonService = pokemonService;
        this.userPokemonService = userPokemonService;

        //For testing  only.
        pokemonService.createPokemon();
        userService.createUser();
        userPokemonService.registerPokemonToUser(pokemonService.find(1), userService.find(2));
        userPokemonService.registerPokemonToUser(pokemonService.find(2), userService.find(2));
        userPokemonService.registerPokemonToUser(pokemonService.find(3), userService.find(1));
        userPokemonService.registerPokemonToUser(pokemonService.find(4), userService.find(1));
    }

    @GetMapping
    public ResponseEntity<User> getUsers(@RequestAttribute("username") String username){
        return ResponseEntity.ok().body(userService.find(userService.findByUsername(username).getId()));
    }

    @GetMapping("pokemons")
    public ResponseEntity<List<UserPokemon>> getUserPokemons(@RequestAttribute("username") String username){
        return ResponseEntity.ok().body(userService.find(userService.findByUsername(username).getId()).getPokemons());
    }

    @PostMapping("save/starter")
    public ResponseEntity<List<UserPokemon>> saveStarterPokemon(@RequestAttribute("username") String username, @RequestBody Pokemon pokemon){
        pokemon = pokemonService.findByName(pokemon.getName());
        userPokemonService.registerPokemonToUser(pokemon, userService.findByUsername(username));
        return ResponseEntity.ok().build();
    }
}
