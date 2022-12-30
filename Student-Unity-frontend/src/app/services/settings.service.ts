import {HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { SignInOutService } from './sign-in-out.service';
import { UserName } from '../models/settings/UserName';
import { UserPassword } from '../models/settings/UserPassword';
import { UserId } from '../models/settings/UserId';

@Injectable({
    providedIn: 'root',
})
export class SettingsService {
    constructor(private httpClient: HttpClient, private signInOutService: SignInOutService) {}

    changeName(userName: UserName): Observable<any> {
        return this.httpClient.put(environment.baseUrl + '/settings/changeName', userName, {
            responseType: 'json',
            params: { sessionId: this.signInOutService.getSignedInUserSessionID() },
        });
    }

    changePassword(userPassword: UserPassword): Observable<any> {
        return this.httpClient.put(environment.baseUrl + '/settings/changePassword', userPassword, {
            responseType: 'json',
            params: { sessionId: this.signInOutService.getSignedInUserSessionID() },
        });
    }

    changeId(userId: UserId): Observable<any> {

      return this.httpClient.put(environment.baseUrl + '/settings/changeId',"", {
            responseType: 'json',
            params: { sessionId: this.signInOutService.getSignedInUserSessionID(), userId: userId.studentId },
        });
    }
}
