import { SignInOutService } from './sign-in-out.service';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Task } from '../models/Task';

@Injectable({
  providedIn: 'root'
})
export class StudentTaskService {

  private url = environment.baseUrl + "/myTasks";

  constructor(private http: HttpClient, private userService: SignInOutService) { }

  public getStudentTasks(): Observable<HttpResponse<Task[]>> {
    return this.http.get<Task[]>(this.url + '/all', {
      observe: 'response',
      params: {
        sessionId: this.userService.getSignedInUserSessionID(),
      },
      responseType: 'json',
    });
  }

  public getSubscribedCourses(): Observable<HttpResponse<string[]>> {
    return this.http.get<string[]>(this.url + '/allSubscribedCourses', {
      observe: 'response',
      params: {
        sessionId: this.userService.getSignedInUserSessionID(),
      },
      responseType: 'json'
    })
  }

  public filterToDoTasks(courseCode: string): Observable<HttpResponse<Task[]>> {
    return this.http.get<Task[]>(this.url + '/filterToDoTasks', {
      observe: 'response',
      params: {
        sessionId: this.userService.getSignedInUserSessionID(),
        courseCode: courseCode
      },
      responseType: 'json',
    });
  }
  
  public filterDoneTasks(courseCode: string): Observable<HttpResponse<Task[]>> {
    return this.http.get<Task[]>(this.url + '/filterDoneTasks', {
      observe: 'response',
      params: {
        sessionId: this.userService.getSignedInUserSessionID(),
        courseCode: courseCode
      },
      responseType: 'json',
    });
  }

  public removeTask(taskId: string): Observable<HttpResponse<any>> {
    return this.http.delete(this.url + '/deleteTask', {
      observe: 'response',
      params: {
        sessionId: this.userService.getSignedInUserSessionID(),
        taskId: taskId
      },
      responseType: 'json',
    });
  }

  public trigerStatus(taskId: string): Observable<HttpResponse<any>> {
    return this.http.put(this.url + '/trigerStatus', taskId, {
      observe: 'response',
      params: {
        sessionId: this.userService.getSignedInUserSessionID()
      },
      responseType: 'json',
    });
  }

}
