import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Battle} from "../../models/battle";
import * as globals from "../../globals";
import {Stomp} from "@stomp/stompjs";
import {HttpService} from "../../services/http.service";

@Component({
  selector: 'app-pokemon-battle',
  templateUrl: './pokemon-battle.component.html',
  styleUrls: ['./pokemon-battle.component.css']
})

export class PokemonBattleComponent implements OnInit {
    battle: any;
    stompClient;
    modal = false;
    endMessage = "";
    // userId = window.localStorage.getItem("user);

    constructor(private route: ActivatedRoute, private http: HttpService, private router:Router) {
        this.route.params.subscribe( (e:any) =>{
            this.http.get("battle/"+JSON.parse(e.id)+"/data").subscribe( e => {
                this.battle = new Battle(e)
                console.log(this.battle)
            })
        })

        let ws = new WebSocket(globals.ws);
        this.stompClient = Stomp.over(ws);

        let _this = this;
        this.stompClient.connect({}, function() {

            _this.stompClient.subscribe("/battle/"+localStorage.getItem('battle_id'), function (e: any) {
                _this.battle = new Battle(JSON.parse(e.body));

                if(_this.battle.finished == 1){
                    _this.modal = true;
                    if (_this.checkWinner()){
                        _this.endMessage ="Congratulations you've have won the battle!"
                    } else {
                        _this.endMessage = "You've lost the battle. Good luck next time."
                    }
                }
            });
        })
    }

    ngOnInit() {
        console.log(this.battle);
    }

    checkUser(){
        return this.battle.user_turn == window.localStorage.getItem("user");
    }

    checkWinner(){
        return this.battle.winner == window.localStorage.getItem("user");
    }
}
