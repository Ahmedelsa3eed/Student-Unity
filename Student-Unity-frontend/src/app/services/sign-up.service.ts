import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SignUpService {

  constructor(private http: HttpClient) { }

  // post sign up data to the backend
  postSignUpData(signUpData: any) : Observable<any> {
    return this.http.post('https://putsreq.com/afrlyoTyESdpuT0E20Zk', signUpData);
  }

}
