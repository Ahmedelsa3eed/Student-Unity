import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {environment} from "../environments/environment";
import {User} from "./models/User";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AccountsService {
  private url = "http://localhost:8090/accounts";

  constructor(private http: HttpClient) { }

  public getAccounts(user: User) : Observable<HttpResponse<User[]>>{
    return this.http.get<User[]>(this.url + '/all', {
      observe: 'response',
      params: {
        sessionId: user.sessionID,
      },
      responseType: 'json',
    });
  }

  public searchAccounts(user: User, searchString: string) {
    return this.http.get<User[]>(this.url + '/search'), {
      observe: 'response',
      responseType: 'json',
      params: {
        sessionId: user.sessionID,
        searchString: searchString
      }
    }
  }

  public changeRole(user: User, targetUser: User, role: string) {
    return this.http.put(this.url + '/changeRole',{}), {
      observe: 'response',
      responseType: 'json',
      params: {
        sessionId: user.sessionID,
        targetUserId: targetUser.studentId,
        role: role
      }
    }
  }

  public deleteAccount(user: User, targetUser: User) {
    return this.http.delete(this.url + '/delete'), {
      observe: 'response',
      responseType: 'json',
      params: {
        sessionId: user.sessionID,
        targetUserId: targetUser.studentId
      }
    }
  }


}
