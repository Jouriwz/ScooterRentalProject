import {Component, Input, OnInit} from '@angular/core';
import {HttpService} from "../../../services/http.service";
import * as globals from "../../../globals";

@Component({
    selector: 'app-enemy-pokemon',
    templateUrl: './enemy-pokemon.component.html',
    styleUrls: ['./enemy-pokemon.component.css']
})
export class EnemyPokemonComponent implements OnInit {
    @Input() data: any;
    pokemonImg: string | undefined;

    constructor(private http: HttpService) {

    }

    // inits a variable with the active pokemon ID with padding for the api URL
    ngOnInit(): void {
        this.pokemonImg = globals.url+"pokemon/image/"+this.data.active_pokemon.pokemon.id.toString().padStart(3, "0");
    }

    // changes a variable with the active pokemon ID with padding for the api URL
    ngOnChanges() {
        this.pokemonImg = globals.url+"pokemon/image/"+this.data.active_pokemon.pokemon.id.toString().padStart(3, "0");
    }

}
