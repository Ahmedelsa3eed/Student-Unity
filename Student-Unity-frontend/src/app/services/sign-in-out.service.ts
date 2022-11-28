import { Injectable } from '@angular/core';
import { User } from '../models/user.model';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})

export class SignInOutService {

  constructor(private httpClient: HttpClient) { }

  private signedInUser: User = {} as User;

  // TODO: Fill the signedInUser info and check for error or not valid user info response
  public signIn(email: string, password: string): boolean{
    let httpParams = new HttpParams();
    httpParams = httpParams.append("email", email);
    httpParams = httpParams.append("password", password);
    this.httpClient.get<number>(`${environment.backendURL}/sign-in`, {params: httpParams}).subscribe(
      (response: number) => {
        this.signedInUser.sessionId = response;
        return true;
      }
    );
    return false;
  }

  public getSignedInUser(): User{
    return this.signedInUser;
  }

  public signOut(): void{
    this.signedInUser = {} as User;
  }
  
}
