package ewa.server.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Pokemon")
@Table(name = "pokemon")
@Data
public class Pokemon implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private int id;

    @CsvBindByName(column = "Name")
    @Column(nullable = false)
    private String name;

    @CsvBindByName(column = "Type1")
    @Column(nullable = false)
    private String type;

    @CsvBindByName(column = "Category")
    @Column(nullable = false)
    private String rarity;

    @CsvBindByName(column = "HP")
    @Column(nullable = false)
    private int health;

    @CsvBindByName(column = "Defense")
    @Column(nullable = false)
    private int defense;

    @CsvBindByName(column = "Attack")
    @Column(nullable = false)
    private int attack;

    @CsvBindByName(column = "Speed")
    @Column(nullable = false)
    private int speed;

    public Pokemon(String name, int health, String type, String rarity, int defense, int speed, int attack) {
        this.name = name;
        this.health = health;
        this.type = type;
        this.rarity = rarity;
        this.defense = defense;
        this.speed = speed;
        this.attack = attack;
    }

    public Pokemon() {

    }
}
