import { SignInOutService } from 'src/app/services/sign-in-out.service';
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Task } from '../models/Task';
@Injectable({
    providedIn: 'root',
})
export class TaskService {
    constructor(private http: HttpClient, private signInOutService: SignInOutService) {}

    public addTask(task: Task): Observable<HttpResponse<any>> {
        let sessionId = this.signInOutService.getSignedInUserSessionID();
        return this.http.post<any>(`${environment.baseUrl}/tasks/addTask`, task, {
            observe: 'response',
            params: {
                sessionId: sessionId,
            },
        });
    }

    public editTask(task: Task): Observable<HttpResponse<any>> {
        let sessionId = this.signInOutService.getSignedInUserSessionID();
        return this.http.put<any>(`${environment.baseUrl}/tasks/editTask`, task, {
            observe: 'response',
            params: {
                sessionId: sessionId,
            },
        });
    }
}
