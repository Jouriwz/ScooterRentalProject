package ewa.server.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@RequiredArgsConstructor
public class Battle implements Serializable {
    private String battle_id;
    private int max_pokemons;
    private int countdown;
    private boolean open;
    private boolean finished;
    private int user_turn;
    private int winner;
    private User user_1;
    private User user_2;
    private List<UserPokemon> user_1_pokemons;
    private List<UserPokemon> user_2_pokemons;
    private int user_1_active_index = 1;
    private int user_2_active_index = 1;
}
