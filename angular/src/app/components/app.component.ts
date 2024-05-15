import { Component } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
    title = 'Pokemon';
    token = window.localStorage.getItem('token');

    constructor(private route:Router) {
        this.checkToken()
    }

    checkToken(){
        if(this.token == null || this.token == ""){
            if (this.route.url === "/register"){
                this.route.navigate(['/register']);
            } else {
                this.route.navigate(['/']);
            }
        }else if(this.token != null){
            if (this.route.url === "/login"){
                this.route.navigate(['/home']);
            }else if (this.route.url === "/register"){
                this.route.navigate(['/home']);
            }
        }
    }
}
