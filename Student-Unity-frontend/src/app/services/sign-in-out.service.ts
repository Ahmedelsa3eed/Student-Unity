import { Injectable } from '@angular/core';
import { User } from '../models/user.model';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class SignInOutService {

  constructor(private httpClient: HttpClient) { }

  private signedInUser: User = {} as User;

  public signIn(email: string, password: string): Observable<string>{
    let httpParams = new HttpParams();
    httpParams = httpParams.append("email", email);
    httpParams = httpParams.append("password", password);
    return this.httpClient.get(`${environment.baseUrl}/logIn/logIn`, {params: httpParams, responseType: 'text'});
  }

  public getSignedInUser(): User{
    return this.signedInUser;
  }

  public signOut(): void{
    this.signedInUser = {} as User;
  }
  
}
