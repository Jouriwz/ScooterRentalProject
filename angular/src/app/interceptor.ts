// @ts-nocheck
import { Injectable } from '@angular/core';
import {HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import * as globals from "./globals"

@Injectable()
export class interceptor implements HttpInterceptor {
    intercept (req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (req.headers.get("skip")){
            const authReq = req.clone({
                url: globals.url+req.url
            });
            return next.handle(authReq);
        }

        const authReq = req.clone({
            url: globals.url+req.url,
            headers: new HttpHeaders({
                'Content-Type':  'application/json',
                'Authorization': globals.token
            })
        });

        console.log('Intercepted HTTP call', authReq);

        return next.handle(authReq);
    }
}
