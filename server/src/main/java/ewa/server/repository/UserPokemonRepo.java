package ewa.server.repository;

import ewa.server.model.UserPokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPokemonRepo extends JpaRepository<UserPokemon, Integer> {

    @Query("SELECT e from UserPokemon e where e.user.id =:id AND e.inTeam = 1")
    List<UserPokemon> findAllTeamPokemons(int id);

    UserPokemon findById(int id);
}
