import {Component, Input, OnInit} from '@angular/core';
import {HttpService} from "../../../services/http.service";
import * as globals from "../../../globals"

@Component({
    selector: 'app-player-pokemon',
    templateUrl: './player-pokemon.component.html',
    styleUrls: ['./player-pokemon.component.css']
})
export class PlayerPokemonComponent implements OnInit {
    @Input() data: any;
    pokemonImg: string | undefined;

    constructor(private http: HttpService) {

    }

    ngOnInit(): void {
        this.pokemonImg = globals.url+"pokemon/image/"+this.data.active_pokemon.pokemon.id.toString().padStart(3, "0");
    }

    ngOnChanges() {
        this.pokemonImg = globals.url+"pokemon/image/"+this.data.active_pokemon.pokemon.id.toString().padStart(3, "0");
    }

}
