package ewa.server.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "UserPokemon")
@Table(name = "user_pokemon")
@Data
@NoArgsConstructor
public class UserPokemon {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(targetEntity = Pokemon.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "pokemon_id")
    private Pokemon pokemon;

    @Column(nullable = false)
    private int exp;

    @Column(nullable = false)
    private int inTeam;

    @Column(nullable = false)
    private int health;

    @Column(nullable = false)
    private boolean alive;

    public UserPokemon(User user, Pokemon pokemon) {
        this.user = user;
        this.pokemon = pokemon;
        this.inTeam = 1;
        this.exp = 0;
        this.alive = true;
        this.health = pokemon.getHealth();
    }
}
