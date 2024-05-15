import {Injectable, Optional} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import * as globals from "../globals"
import {Observable} from "rxjs";

@Injectable({providedIn: 'root'})
export class HttpService {
    baseurl = globals.url;

    constructor(private http: HttpClient) {}

    get(endpoint: string){
        return this.http.get(endpoint);
    }

    post(endpoint: string, body: any ){
        const httpOptions = {
            headers: new HttpHeaders({
            'Content-Type':  'application/json',
            'Authorization': 'Bearer Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJuaWNrc2ViIiwiaWF0IjoxNjQxMTM0MjU1LCJleHAiOjE2NDExNDE0NTV9.0frtfw40eZGLafIgajX8gB3tSNGQzqSxER2QU3zh72lVd1ZxK8ZTDt67gLH6iE0Y'
            })
        };

        return this.http.post(endpoint,
                body
           ,
            httpOptions
        )
    }
}
