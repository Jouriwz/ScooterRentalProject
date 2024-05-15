import {Component } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import * as globals from "../../globals"

@Component({
    selector: 'app-pokedex',
    templateUrl: './pokedex.component.html',
    styleUrls: ['./pokedex.component.css']
})

export class PokedexComponent {
  //  Array with all the Pokemons
  pokemons: Array<any> = [];

    constructor(private http: HttpClient) {
    }

    // Fetches all the pokemons on pageload
    ngOnInit() {
        this.fetchPokemons();
    }

    // function to get the base url
    getBaseUrl(){
        return globals.url
    }

    // function to add 0 to the image id. for example 001 or 011
    pad(num:number, size:number): string {
        let s = num+"";
        while (s.length < size) s = "0" + s;
        return s;
    }

    // Function that calls the pokemon endpoint from the server and pushes it in the pokemons array
    fetchPokemons() {
        this.http.get('pokemon/')
            .subscribe(pokemons => {
                this.pokemons.push(pokemons)
            })
    }
}




