package ewa.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "User")
@Table(name = "user")
@Data
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private int id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String password;

    @Column(columnDefinition = "int(11) default '200'")
    private int gold;

    @Column(columnDefinition = "int(11) default '1000'")
    private int rank;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "user",
            fetch = FetchType.EAGER
    )
    @JsonIgnore
    private List<UserPokemon> pokemons = new ArrayList<>();

    public User(String username, String email, String country, String gender, String password) {
        this.username = username;
        this.email = email;
        this.country = country;
        this.gender = gender;
        this.password = password;
        this.gold = 250;
        this.rank = 1;
        this.pokemons = null;
    }

    public User() {
        this.gold = 250;
        this.rank = 1;
        this.pokemons = null;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", gender='" + gender + '\'' +
                ", password='" + password + '\'' +
                ", gold=" + gold +
                ", rank=" + rank +
                '}';
    }
}

