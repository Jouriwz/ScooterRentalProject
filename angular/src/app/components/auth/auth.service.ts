import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";

// providedIn is a alternative to adding the service to the app.module providers
@Injectable({providedIn: 'root'})
export class AuthService {

    constructor(private http: HttpClient) {}

    // Register API function that sends the user input to the server
    register(username: string, email: string, country: string, gender: string, password: string) {
        return this.http.post('auth/register',
        {
                username: username,
                email: email,
                country: country,
                gender: gender,
                password: password
            },
            {responseType: 'text', headers:{skip:"true"}}
        );
    }

    // Login API function that sends the user input to the server
    login(username: string, password: string) {
        return this.http.post('auth/login',
        {
                username: username,
                password: password
            },
            {responseType: 'text', headers:{skip:"true"}}
        );
    }

    // Password Reset API function that sends the new credentials to the server
    reset(username: string, password: string) {
        return this.http.post('user/reset/password',
            {
                username: username,
                password: password
            },
            {responseType: 'text', headers:{skip:"true"}}
        );
    }
}
