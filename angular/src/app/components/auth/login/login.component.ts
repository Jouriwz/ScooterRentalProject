import { Component, OnInit } from '@angular/core';
import {NgForm} from "@angular/forms";
import {AuthService} from "../auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

    constructor(private authService: AuthService, private route:Router) {}

    ngOnInit(): void {
    }

    // Login Function
    onLogin(form: NgForm) {
        // form validation
        if (!form.valid) {
            return;
        }

        // variables to hold the user input from the html form
        const username = form.value.username;
        const password = form.value.password;

        // Calls the authService login function with the user input as parameters
        this.authService.login(username, password).subscribe(responseData => {
            window.localStorage.setItem("token", responseData)
            this.route.navigate(['/home'])
        }, errorRes => {
            console.log(errorRes);
        });

        form.reset();
    }

}
