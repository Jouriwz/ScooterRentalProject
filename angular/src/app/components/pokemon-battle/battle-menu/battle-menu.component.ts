import { Component, OnInit } from '@angular/core';
import {HttpService} from "../../../services/http.service";

@Component({
  selector: 'app-battle-menu',
  templateUrl: './battle-menu.component.html',
  styleUrls: ['./battle-menu.component.css']
})
export class BattleMenuComponent implements OnInit {

    constructor(private http: HttpService) {

    }

    ngOnInit(): void {
    }

    attack(){
        this.http.get("battle/attack/"+localStorage.getItem("battle_id")).subscribe( e =>{
            console.log(e)
        })
    }
}
