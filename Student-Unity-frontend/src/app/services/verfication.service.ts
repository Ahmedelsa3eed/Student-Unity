import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { VerificationRequest } from '../models/verification-request';

@Injectable({
  providedIn: 'root'
})
export class VerficationCodeService {

  constructor(private http: HttpClient) { }
  postVerficationCode(verficationRequest : VerificationRequest): Observable<HttpResponse<string>> {
    console.log(verficationRequest);
    return this.http.post(`${environment.baseUrl}/registration/verify`, verficationRequest, {
      observe: "response",
      responseType: "text",
    });
  }
}
