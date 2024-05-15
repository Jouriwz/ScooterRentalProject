import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-pokemon-party',
    templateUrl: './pokemon-party.component.html',
    styleUrls: ['./pokemon-party.component.css']
})
export class PokemonPartyComponent implements OnInit {
    public show:boolean = false;
    public buttonName:any = 'Show';

    constructor() { }

    ngOnInit() {
    }

    select() {
        this.show = !this.show;

        if(this.show)
            this.buttonName = "Hide";
        else
            this.buttonName = "Show";
    }

}
