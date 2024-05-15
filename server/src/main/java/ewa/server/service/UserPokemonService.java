package ewa.server.service;

import ewa.server.model.Pokemon;
import ewa.server.model.User;
import ewa.server.model.UserPokemon;
import ewa.server.repository.UserPokemonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPokemonService {
    private final UserPokemonRepo userPokemonRepo;

    @Autowired
    public UserPokemonService(UserPokemonRepo userPokemonRepo) {
        this.userPokemonRepo = userPokemonRepo;
    }

    public void save(UserPokemon userPokemon){
        userPokemonRepo.save(userPokemon);
    }

    public UserPokemon findById(int id){
        return userPokemonRepo.findById(id);
    }

    public void registerPokemonToUser(Pokemon pokemon, User user) {
        UserPokemon newUserPokemon = new UserPokemon(user, pokemon);
        save(newUserPokemon);
    }

    public List<UserPokemon> getUserTeamPokemons(int id) {
        return userPokemonRepo.findAllTeamPokemons(id);
    }
}
