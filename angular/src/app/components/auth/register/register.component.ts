import { Component, OnInit } from '@angular/core';
import { NgForm } from "@angular/forms";
import {AuthService} from "../auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

    constructor(private authService: AuthService, private route:Router) {}

    ngOnInit(): void {
    }

    // Register function
    onRegister(form: NgForm) {
        // form validation
        if (!form.valid) {
            return;
        }

        // Variables that hold the user form input
        const username = form.value.username;
        const email = form.value.email;
        const country = form.value.country;
        const gender = form.value.gender;
        const password = form.value.password;

        // calls the authService register function with the user data as parameters
        this.authService.register(username, email, country, gender, password).subscribe(responseData => {
            window.localStorage.setItem("token", responseData)
            this.route.navigate(['/home'])
        }, errorRes => {
            console.log(errorRes);
        });
        form.reset();
    }
}
