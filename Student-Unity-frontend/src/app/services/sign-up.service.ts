import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { SignUpData } from '../models/sign-up-data.model';
@Injectable({
  providedIn: 'root'
})
export class SignUpService {

  constructor(private http: HttpClient) { }

  // post sign up data to the backend
  postSignUpData(signUpData: SignUpData) : Observable<any> {
    return this.http.post(`${environment.baseUrl}`, signUpData);
  }

}
