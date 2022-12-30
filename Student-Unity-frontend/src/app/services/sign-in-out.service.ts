import { Injectable } from '@angular/core';
import { User } from '../models/User';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { BehaviorSubject, Observable } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
    providedIn: 'root',
})
export class SignInOutService {
    constructor(private httpClient: HttpClient, private cookieService: CookieService) {}

    public signIn(email: string, password: string): Observable<HttpResponse<string>> {
        let httpParams = new HttpParams();
        httpParams = httpParams.append('email', email);
        httpParams = httpParams.append('password', password);
        return this.httpClient.get(environment.baseUrl + '/logIn/logIn', {
            observe: 'response',
            params: httpParams,
            responseType: 'text',
        });
    }

    public signOut(): void {
        this.httpClient
            .put(
                environment.baseUrl + '/logout/logout',
                {},
                { params: { sessionID: this.cookieService.get('sessionId') } }
            )
            .subscribe();
        this.cookieService.deleteAll();
    }

    public forgotPassword(email: string): Observable<HttpResponse<any>> {
        let httpParams = new HttpParams();
        httpParams = httpParams.append('email', email);
        return this.httpClient.get(environment.baseUrl + '/logIn/forgetPassword', {
            params: httpParams,
            observe: 'response',
            responseType: 'text',
        });
    }

    // This function should be called in appropriate components' onInit
    public isSignedIn(): boolean {
        return this.cookieService.check('sessionId');
    }

    // This function should be called when the user sign In for the first time, or when he edits his information.
    public fillSignedInUserInfo(sessionId: string): Observable<HttpResponse<User>> {
        return this.httpClient.get<User>(environment.baseUrl + '/logIn/getUser', {
            params: { sessionID: sessionId },
            observe: 'response',
            responseType: 'json',
        });
    }

    public getSignedInUserFirstName(): string {
        return this.cookieService.get('firstName');
    }

    public getSignedInUserLastName(): string {
        return this.cookieService.get('lastName');
    }

    public getSignedInUserEmail(): string {
        return this.cookieService.get('email');
    }

    public getSignedInUserRole(): string {
        return this.cookieService.get('role');
    }

    public getSignedInUserSessionID(): string {
        return this.cookieService.get('sessionId');
    }

    public getSignedInUser(): User {
        let user: User = new User();
        user.firstName = this.getSignedInUserFirstName();
        user.lastName = this.getSignedInUserLastName();
        user.email = this.getSignedInUserEmail();
        user.role = this.getSignedInUserRole();
        user.sessionID = this.getSignedInUserSessionID();
        return user;
    }
}
