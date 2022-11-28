import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../environments/environment";
import {User} from "./models/User";

@Injectable({
  providedIn: 'root'
})
export class AccountsService {
  private url = environment.baseUrl;

  constructor(private http: HttpClient) { }

  public getAccounts(user: User) {
    return this.http.get<User[]>(this.url + '/users'), {
      observe: 'response',
      responseType: 'json',
      params: {
        sessionId: user.sessionID,
      }
    }
  }



}
