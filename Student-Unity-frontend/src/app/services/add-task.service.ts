import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {Task} from "../models/Task";
@Injectable({
  providedIn: 'root'
})
export class AddTaskService {

  constructor(private http: HttpClient) { }

  public addTask(sessionId: string, task: Task): Observable<HttpResponse<any>> {
    console.log("addTask");
    console.log(sessionId);
    console.log(task);
    console.log(`${environment.baseUrl}/tasks/addTask`);

    return this.http.post<any>(`${environment.baseUrl}/tasks/addTask`, task, {
      observe: 'response',
      params: {
        sessionId: sessionId,
      }
    });



  }
}
