package ewa.server.service;

import ewa.server.model.Pokemon;
import ewa.server.repository.PokemonRepo;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
public class PokemonService {
    private final PokemonRepo pokemonRepo;

    @Autowired
    public PokemonService(PokemonRepo pokemonRepo) {
        this.pokemonRepo = pokemonRepo;
    }

    public List<Pokemon> findAllPokemons(){
        return pokemonRepo.findAll();
    }

    public Pokemon find(Integer id){
        return pokemonRepo.findPokemonById(id);
    }

    public Pokemon storePokemon(Pokemon pokemon) {
        return pokemonRepo.save(pokemon);
    }

    public void deletePokemon(Integer id){
        pokemonRepo.deletePokemonById(id);
    }

    public void createPokemon() throws FileNotFoundException {
        List<Pokemon> beans = new CsvToBeanBuilder(new FileReader("src/main/resources/pokedex.csv"))
                .withType(Pokemon.class)
                .build()
                .parse();

        beans.forEach(pokemon -> {
            storePokemon(pokemon);
        });
    }

    public Pokemon findByName(String name) {
        return pokemonRepo.findPokemonByName(name);
    }
}
