import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { SignInOutService } from './sign-in-out.service';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
@Injectable({
    providedIn: 'root',
})
export class NotificationService {
    url = environment.baseUrl + '/Notification';

    constructor(private httpClient: HttpClient, private signInOutService: SignInOutService) {}

    putToken(token: string): Observable<any> {
        console.log(this.signInOutService.getSignedInUserSessionID(), 'this is my session id');

        let para = new HttpParams();
        para = para.append('sessionId', this.signInOutService.getSignedInUserSessionID());
        para = para.append('token', token);
        let link = this.url + '/addToken';
        console.log(link);
        return this.httpClient.post<any>(link, para);
    }

    subscribeToTopic(topic: string): Observable<any> {
        
        let para = new HttpParams();
        para = para.append('sessionId', this.signInOutService.getSignedInUserSessionID());
        para = para.append('topic', topic);
        let link = this.url + '/subscribeToTopic';
        return this.httpClient.put<any>(link, para);
    }

    sendMessageToTopic(topic: string, body: string, title: string) {
        
        let para = new HttpParams();
        para = para.append('title', title);
        para = para.append('topic', topic);
        para = para.append('body', body);
        let link = this.url + '/sendMessageToTopic';
        return this.httpClient.post<any>(link, para);
    }

    unSubscribe(topic: string): Observable<any>{
        let para = new HttpParams();
        para = para.append('sessionId', this.signInOutService.getSignedInUserSessionID());
        para = para.append('topic', topic);
        let link = this.url + '/unSubscribe';
        return this.httpClient.put<any>(link, para);
    }
}
