import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from "@angular/common/http";
import { environment } from '../../environments/environment';
import { User } from '../models/User';
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  private url = environment.baseUrl + "/accounts";

  constructor(private http: HttpClient) { }

  public getAccounts(sessionID: string): Observable<HttpResponse<User[]>> {
    return this.http.get<User[]>(this.url + '/all', {
      observe: 'response',
      params: {
        sessionId: sessionID,
      },
      responseType: 'json',
    });
  }

  public searchAccounts(sessionId: string, searchString: string): Observable<HttpResponse<User[]>> {
    return this.http.get<User[]>(this.url + '/search', {
      observe: 'response',
      responseType: 'json',
      params: {
        sessionId: sessionId,
        searchString: searchString
      }
    });
  }

  public changeRole( sessionId: string, targetUser: User, role: string): Observable<HttpResponse<any>> {
    return this.http.put<boolean>(this.url + '/changeRole',{}, {
      observe: 'response',
      responseType: 'json',
      params: {
        sessionId: sessionId,
        targetUserId: targetUser.studentId,
        role: role
      }
    });
  }

  public deleteAccount(sessionID: string, targetUser: User): Observable<HttpResponse<any>> {
    console.log("deleteAccount");
    console.log(sessionID);
    console.log(targetUser);
    return this.http.delete(this.url + '/delete', {
      observe: 'response',
      params: {
        sessionId: sessionID,
        targetUserId: targetUser.studentId
      },
      responseType: 'json'
    });
  }

}
