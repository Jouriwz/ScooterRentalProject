import { Component, OnInit } from '@angular/core';
import {AuthService} from "../auth.service";
import {Router} from "@angular/router";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {

    constructor(private authService: AuthService, private route:Router) {}

    ngOnInit(): void {
    }

    // Reset password function
    onReset(form: NgForm) {
        // form validation
        if (!form.valid) {
            return;
        }

        // variables that hold user input from form
        const username = form.value.username;
        const password = form.value.password;

        // calls the authService reset function with the user input as parameters
        this.authService.reset(username, password).subscribe(responseData => {
            window.localStorage.setItem("token", responseData)
            this.route.navigate(['/home'])
        }, errorRes => {
            console.log(errorRes);
        });
        form.reset();
    }

}
