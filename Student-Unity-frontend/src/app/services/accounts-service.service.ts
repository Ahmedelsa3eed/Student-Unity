import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from "@angular/common/http";
import { environment } from './../../environments/environment';
import { User } from './../models/User';
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AccountsService {
  private url = environment.baseUrl + "/accounts";

  constructor(private http: HttpClient) { }

  public getAccounts(user: User): Observable<HttpResponse<User[]>> {
    return this.http.get<User[]>(this.url + '/all', {
      observe: 'response',
      params: {
        sessionId: user.sessionID,
      },
      responseType: 'json',
    });
  }

  public searchAccounts(user: User, searchString: string): Observable<HttpResponse<User[]>> {
    return this.http.get<User[]>(this.url + '/search', {
      observe: 'response',
      responseType: 'json',
      params: {
        sessionId: user.sessionID,
        searchString: searchString
      }
    });
  }

  public changeRole(user: User, targetUser: User, role: string): Observable<HttpResponse<any>> {
    return this.http.put<boolean>(this.url + '/changeRole',{}, {
      observe: 'response',
      responseType: 'json',
      params: {
        sessionId: user.sessionID,
        targetUserId: targetUser.id,
        role: role
      }
    });
  }

  public deleteAccount(user: User, targetUser: User): Observable<HttpResponse<any>> {
    console.log("deleteAccount");
    console.log(user);
    console.log(targetUser);
    return this.http.delete(this.url + '/delete', {
      observe: 'response',
      params: {
        sessionId: "454545" /*user.sessionID*/,
        targetUserId: targetUser.id
      },
      responseType: 'json'
    });
  }

}
