package ewa.server.repository;

import ewa.server.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonRepo extends JpaRepository<Pokemon, Integer> {
    Pokemon findPokemonById(Integer id);

    List<Pokemon> findAll();

    Pokemon save(Pokemon pokemon);

    void deletePokemonById(Integer id);

    Pokemon findPokemonByName(String name);
}
