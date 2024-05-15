import {Component, OnInit, Input} from '@angular/core';
import {ActivatedRoute, Params} from "@angular/router";
import {PokemonBattleComponent} from "../pokemon-battle.component";

@Component({
    selector: 'app-player-info',
    templateUrl: './player-info.component.html',
    styleUrls: ['./player-info.component.css']
})
export class PlayerInfoComponent implements OnInit {
    @Input() data: any;

    constructor(private route: ActivatedRoute) {
    }


    ngOnInit() {

    }



}
