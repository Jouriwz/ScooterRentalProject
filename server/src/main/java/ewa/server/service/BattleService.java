package ewa.server.service;

import ewa.server.exception.BattleNotFoundException;
import ewa.server.exception.NoOpenGames;
import ewa.server.exception.OngoingBattle;
import ewa.server.model.Battle;
import ewa.server.model.User;
import ewa.server.model.UserPokemon;
import ewa.server.storage.BattleStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BattleService {
    private final UserPokemonService userPokemonService;

    //Create a battle which is stored in the battlestorage instance
    public Battle createBattle(User user1){
        Battle battle = new Battle();
        battle.setBattle_id(UUID.randomUUID().toString());
        battle.setUser_1(user1);
        battle.setUser_turn(1);
        battle.setWinner(0);
        battle.setCountdown(30);
        battle.setMax_pokemons(5);
        battle.setUser_1_pokemons(userPokemonService.getUserTeamPokemons(user1.getId()));
        battle.setUser_1_active_index(0);
        battle.setOpen(true);

        BattleStorage.getInstance().setBattle(battle);
        return battle;
    }

    //Connects to an open battle based on the battleID
    public Battle connectToBattle(User user2, String battleId) throws BattleNotFoundException, OngoingBattle {
        if (!BattleStorage.getInstance().getBattles().containsKey(battleId)){
            throw new BattleNotFoundException("Battle with provided id not found");
        }

        Battle battle = BattleStorage.getInstance().getBattles().get(battleId);

        if (!battle.isOpen()){
            throw new OngoingBattle("This battle already has 2 players.");
        }

        battle.setUser_2(user2);
        battle.setUser_2_pokemons(userPokemonService.getUserTeamPokemons(user2.getId()));
        battle.setUser_2_active_index(0);
        battle.setOpen(false);
        BattleStorage.getInstance().setBattle(battle);

        return battle;
    }

    //Find a random open battle to join. -Matchmaking
    public Battle findMatch(User user2) throws NoOpenGames {
        Battle battle = BattleStorage
                .getInstance()
                .getBattles()
                .values()
                .stream()
                .filter(e->e.isOpen()).findFirst().orElseThrow(() -> new NoOpenGames("No open battles found"));

        battle.setUser_2(user2);
        battle.setUser_2_pokemons(userPokemonService.getUserTeamPokemons(user2.getId()));
        battle.setUser_2_active_index(0);
        battle.setOpen(false);
        BattleStorage.getInstance().setBattle(battle);
        return battle;
    }

    public Map<String,ewa.server.model.Battle> getAllBattles() {
        return BattleStorage.getInstance().getBattles();
    }

    //Attacks the opponent
    public Battle attack(String battleId){
        Battle battle = BattleStorage.getInstance().getBattles().get(battleId);
        System.out.println(battle);
        if (battle.isFinished()){
            System.out.println("Battle is already finished.");
            return battle;
        }

        int damage;
        int damageReductionDivide = 4;
        UserPokemon target;
        List<UserPokemon> targetPokemons;
        int totalPokemons;

        System.out.println("Turn of user: "+battle.getUser_turn());
        if (battle.getUser_turn() == 1){
            System.out.println("user 1 turn");

            damage = battle.getUser_1_pokemons().get(battle.getUser_1_active_index()).getPokemon().getAttack()/damageReductionDivide;
            target = battle.getUser_2_pokemons().get(battle.getUser_2_active_index());
            targetPokemons = battle.getUser_2_pokemons();
            totalPokemons = battle.getUser_2_pokemons().size();
            battle.setUser_turn(2);
            System.out.println("damage: "+damage);
            System.out.println("target: "+target.toString());
            System.out.println("pokemons player 2: "+targetPokemons);
            System.out.println("total pokemons player 2: "+totalPokemons);

        } else {
            System.out.println("user 2 turn");

            damage = battle.getUser_2_pokemons().get(battle.getUser_2_active_index()).getPokemon().getAttack()/damageReductionDivide;
            target = battle.getUser_1_pokemons().get(battle.getUser_1_active_index());
            targetPokemons = battle.getUser_1_pokemons();
            totalPokemons = battle.getUser_1_pokemons().size();
            battle.setUser_turn(1);
            System.out.println("damage: "+damage);
            System.out.println("target: "+target.toString());
            System.out.println("pokemons player 1: "+targetPokemons);
            System.out.println("total pokemons player 1: "+totalPokemons);
        }

        System.out.println("Target health before: "+target.getHealth());
        System.out.println("Target alive state before: "+target.isAlive());
        target.setHealth( (Math.max(target.getHealth() - damage, 0)) );
        target.setAlive( (target.getHealth() > 0) );

        UserPokemon userPokemon = userPokemonService.findById(target.getId());
        userPokemon.setHealth(target.getHealth());
        userPokemon.setAlive(target.isAlive());
        userPokemonService.save( userPokemon );

        System.out.println("Target health after: "+target.getHealth());
        System.out.println("Target alive state after: "+target.isAlive());

        System.out.println("target is not alive: "+!target.isAlive());
        if (!target.isAlive()){
            if (totalPokemons > 1){
                System.out.println("Opponent has more than 1 pokemon. Looping.");
                for (int i = 0; i < targetPokemons.size(); i++) {
                    System.out.println("index: "+i);
                    System.out.println("target pokemon:"+targetPokemons.get(i));
                    System.out.println("target pokemon alive:"+targetPokemons.get(i).isAlive());
                    if (targetPokemons.get(i) != null && targetPokemons.get(i).isAlive()) {
                        System.out.println("found alive pokemon");
                        System.out.println("Userturn: "+battle.getUser_turn());
                        System.out.println("Userturn equals 1:"+(battle.getUser_turn() == 1));
                        if (battle.getUser_turn() == 1){
                            System.out.println("battle setUser_1_active_index:"+BattleStorage.getInstance().getBattles());
                            System.out.println("updating user 1 pokemon index to: "+i);
                            battle.setUser_1_active_index(i);
                            return battle;
                        } else {
                            System.out.println("battle setUser_2_active_index:"+BattleStorage.getInstance().getBattles());
                            System.out.println("updating user 2 pokemon index to: "+i);
                            battle.setUser_2_active_index(i);
                            return battle;
                        }
                    }
                }

                System.out.println("battle is finished.");
                System.out.println("winner:"+(battle.getUser_turn() == 1 ? 2 : 1));
                battle.setFinished(true);
                battle.setWinner( (battle.getUser_turn() == 1 ? 2 : 1) );
                return battle;
            } else {
                System.out.println("battle is finished.");
                System.out.println("winner:"+(battle.getUser_turn() == 1 ? 2 : 1));
                battle.setWinner( (battle.getUser_turn() == 1 ? 2 : 1) );
                battle.setFinished(true);
                return battle;
            }
        }
        System.out.println("Continue the battle");
        BattleStorage.getInstance().setBattle(battle);
        return battle;
    }

    //End match based on battleID
    public Battle endMatch(String battleId, User winner){
        Battle battle = BattleStorage
                .getInstance()
                .getBattles()
                .get(battleId);

        BattleStorage.getInstance().setBattle(battle);
        return battle;
    }
}
