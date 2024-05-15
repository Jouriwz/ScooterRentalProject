package ewa.server.repository;

import ewa.server.model.Pokemon;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class PokemonRepoTest {

    // instance of pokemon repository
    @Autowired
    PokemonRepo pokemonRepo;

    @Test
    void findPokemonById_success() {
        // variable to hold pokemon with id 1
        Pokemon pokemonId = pokemonRepo.findPokemonById(1);
        // if pokemon id returns id 1 test passed
        assertEquals(1, pokemonId.getId());
        System.out.println("Pokemon name " + pokemonId.getName() + " with id " + pokemonId.getId());
    }

    @Test
    void findPokemonById_failure() {
        // variable to hold pokemon with id 1
        Pokemon pokemonId = pokemonRepo.findPokemonById(1);
        // if pokemon id does not returns id 1 test passed
        assertNotEquals(2, pokemonId.getId());
        System.out.println("Pokemon name " + pokemonId.getName() + " with id " + pokemonId.getId());
    }

    @Test
    void save_success() {
        // variable to hold new pokemon
        Pokemon pokemon = new Pokemon("newpokemon", 50, "Fire", "Rare", 40, 40, 40);
        // calls pokemon reposiroty save function to save the pokemon
        pokemonRepo.save(pokemon);
        // if saved pokemon id equals 152 test passed
        assertEquals(152, pokemon.getId());
        System.out.println(pokemon.getName());
    }

    @Test
    void save_failure() {
        // variable to hold new pokemon
        Pokemon pokemon = new Pokemon("newpokemon", 50, "Fire", "Rare", 40, 40, 40);
        // calls pokemon reposiroty save function to save the pokemon
        pokemonRepo.save(pokemon);
        // if saved pokemon id does not equals 152 test passed
        assertNotEquals(151, pokemon.getId());
        System.out.println(pokemon.getName());
    }

    @Test
    void findPokemonByName_success() {
        // variable to hold pokemon with name Bulbasaur
        Pokemon pokemonName = pokemonRepo.findPokemonByName("Bulbasaur");
        // if pokemon name returns Bulbasaur test passed
        assertEquals("Bulbasaur", pokemonName.getName());
        System.out.println(pokemonName.getName());
    }

    @Test
    void findPokemonByName_failure() {
        // variable to hold pokemon with name Bulbasaur
        Pokemon pokemonName = pokemonRepo.findPokemonByName("Bulbasaur");
        // if pokemon name returns Bulbasaur test passed
        assertNotEquals("Charmender", pokemonName.getName());
        System.out.println(pokemonName.getName());
    }
}
