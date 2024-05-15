package ewa.server.controller;

import ewa.server.exception.BattleNotFoundException;
import ewa.server.exception.NoOpenGames;
import ewa.server.exception.OngoingBattle;
import ewa.server.model.Battle;
import ewa.server.model.battleId;
import ewa.server.model.newBattle;
import ewa.server.service.BattleService;
import ewa.server.service.UserService;
import ewa.server.storage.BattleStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/battle/")
public class BattleController {
    private final BattleService battleService;
    private final UserService userService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public BattleController(BattleService battleService, UserService userService, SimpMessagingTemplate simpMessagingTemplate) {
        this.battleService = battleService;
        this.userService = userService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @PostMapping("new")
    public ResponseEntity<?> startBattle(@RequestAttribute("username") String username, @RequestBody newBattle settings){
        Battle batte = battleService.createBattle(userService.findByUsername(username));
        batte.setCountdown(settings.getCountdown());
        batte.setMax_pokemons(settings.getMax_pokemons());
        simpMessagingTemplate.convertAndSend("/battle/all", BattleStorage.getInstance().getBattles());
        return ResponseEntity.ok().body(batte);
    }

    @PostMapping("join")
    public ResponseEntity<Battle> joinBattle(@RequestAttribute("username") String username, @RequestBody battleId battle) throws OngoingBattle, BattleNotFoundException {
        Battle updatedBattle = battleService.connectToBattle(userService.findByUsername(username), battle.getBattleId());
        simpMessagingTemplate.convertAndSend("/battle/"+battle.getBattleId(), updatedBattle);
        simpMessagingTemplate.convertAndSend("/battle/all", BattleStorage.getInstance().getBattles());
        return ResponseEntity.ok().body(updatedBattle);
    }

    @GetMapping("find")
    public ResponseEntity<Battle> findMatch(@RequestAttribute("username") String username) throws NoOpenGames {
        return ResponseEntity.ok().body(battleService.findMatch(userService.findByUsername(username)));
    }

    @GetMapping("{battleId}/data")
    public ResponseEntity<Battle> getBattleData(@PathVariable String battleId){
        return ResponseEntity.ok().body(BattleStorage.getInstance().getBattles().get(battleId));
    }

    @GetMapping("all")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok().body(BattleStorage.getInstance().getBattles());
    }

    @GetMapping("attack/{battleId}")
    public void atack(@PathVariable String battleId) {
        Battle battle = battleService.attack(battleId);
        simpMessagingTemplate.convertAndSend("/battle/"+battleId, battle);
    }
}
