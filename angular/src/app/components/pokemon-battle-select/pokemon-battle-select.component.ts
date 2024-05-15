import { Component, OnInit } from '@angular/core';
import * as globals from "../../globals";
import {Stomp} from "@stomp/stompjs";
import {HttpService} from "../../services/http.service";
import {Router} from "@angular/router";
import {Battle} from "src/app/models/battle";

@Component({
    selector: 'app-pokemon-battle-select',
    templateUrl: './pokemon-battle-select.component.html',
    styleUrls: ['./pokemon-battle-select.component.css']
})
export class PokemonBattleSelectComponent implements OnInit {
    max_pokemons = 6;
    // @ts-ignore
    player_pokemons;
    countdown = 30;
    matches: any;
    // @ts-ignore
    stompClient;
    battleID = "";
    private tries = 0;

    constructor(private http: HttpService, private route:Router) {
        this.allMatches()
        this.newMatches()
        this.getPartyPokemons()
    }

    ngOnInit(): void {

    }

    getPartyPokemons(){
        return this.http.get("user/pokemons").subscribe(e => {
            this.player_pokemons = e;
        })
    }


    ongoingBattle(){
        try{
            let ws = new WebSocket(globals.ws);
            this.stompClient = Stomp.over(ws);

            let _this = this;
            this.stompClient.connect({}, function() {

                _this.stompClient.subscribe("/battle/"+window.localStorage.getItem('battle_id'), function (e: any) {
                    _this.route.navigate(['/battle', {id: JSON.stringify(new Battle(JSON.parse(e.body)).battle_id)}]);
                });
            })

        } catch (e) {
            console.log(e)
            if (this.tries != 3){
                this.ongoingBattle()
                this.tries += 1;
            }
            this.tries = 0
        }
    }

    //Triggers webhook which listens to new battles
    newMatches(){
        try{
            let ws = new WebSocket(globals.ws);
            this.stompClient = Stomp.over(ws);

            let _this = this;
            this.stompClient.connect({}, function() {

                _this.stompClient.subscribe("/battle/all", function (message: any) {
                    if (message.body) {
                        _this.matches = Object.entries(JSON.parse(message.body))
                    }
                });

            })

        } catch (e) {
            if (this.tries != 3){
                this.newMatches()
                this.tries += 1;
            }
            this.tries = 0
        }
    }

    send(endpoint: string, message: string){
        try{
            this.stompClient.send(endpoint, {}, JSON.stringify([
                globals.token,
                {
                    message
                }
            ]));

        } catch (e) {
            if (this.tries != 3){
                this.send(endpoint, message)
                this.tries += 1;
            }
            this.tries = 0
        }
    }

    disconnect(){
        try{
            this.stompClient.disconnect()
        } catch (e) {
            if (this.tries != 3){
                this.disconnect()
                this.tries += 1;
            }
            this.tries = 0
        }
    }

    newBattle(){
        this.http.post('battle/new',
            {
                max_pokemons: this.max_pokemons,
                countdown: this.countdown
            }
        ).subscribe((e:any)=>{
            window.localStorage.setItem("battle_id", e.battle_id)
            window.localStorage.setItem("user", "1")
            this.ongoingBattle()
        })
    }

    allMatches() {
        this.http.get('battle/all').subscribe(e =>{
            this.matches = Object.entries(e)
        });
    }

    joinMatch(battleData: any) {
        let battleId = battleData.battle_id

        this.http.post('battle/join',
            {
                battleId
            }
        ).subscribe((e:any)=>{
            window.localStorage.setItem("battle_id", e.battle_id)
            window.localStorage.setItem("user", "2")
            this.route.navigate(['/battle', {id: JSON.stringify(new Battle(e).battle_id)}]);
        })
    }

    joinMatchById() {
        let battleId = this.battleID;

        this.http.post('battle/join',
            {
                battleId
            }
        ).subscribe((e:any)=>{
            window.localStorage.setItem("battle_id", e.battle_id)
            window.localStorage.setItem("user", "2")
            this.route.navigate(['/battle', {id: JSON.stringify(new Battle(e).battle_id)}]);
        })
    }
}
