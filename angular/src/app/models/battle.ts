import {window} from "rxjs/operators";

export class Battle {
    battle_id: string;
    max_pokemons: number;
    countdown: number;
    open: boolean;
    finished: boolean;
    user_turn: number;
    winner: number;
    user_1: any;
    user_2: any;
    user_1_pokemons: any;
    user_2_pokemons: any;
    user_1_active_index: number;
    user_2_active_index: number;

    constructor(json: any) {
        this.battle_id = json.battle_id;
        this.max_pokemons = json.max_pokemons;
        this.countdown = json.countdown;
        this.open = json.open;
        this.finished = json.finished;
        this.user_turn = json.user_turn;
        this.winner = json.winner;
        this.user_1 = json.user_1;
        this.user_2 = json.user_2;
        this.user_1_pokemons = json.user_1_pokemons;
        this.user_2_pokemons = json.user_2_pokemons;
        this.user_1_active_index = json.user_1_active_index;
        this.user_2_active_index = json.user_2_active_index;
    }

    getUser(){
        // @ts-ignore
        if (localStorage.getItem('user') == 1){
            return {
                "user": this.user_1,
                "pokemons": this.user_1_pokemons,
                "active_pokemon": this.user_1_pokemons[this.user_1_active_index]
            }
        } else {
            return {
                "user": this.user_2,
                "pokemons": this.user_2_pokemons,
                "active_pokemon": this.user_2_pokemons[this.user_2_active_index]
            }
        }
    }

    getOpponent(){
        // @ts-ignore
        if (localStorage.getItem('user') == 2){
            return {
                "user": this.user_1,
                "pokemons": this.user_1_pokemons,
                "active_pokemon": this.user_1_pokemons[this.user_1_active_index]
            }
        } else {
            return {
                "user": this.user_2,
                "pokemons": this.user_2_pokemons,
                "active_pokemon": this.user_2_pokemons[this.user_2_active_index]
            }
        }
    }
}
