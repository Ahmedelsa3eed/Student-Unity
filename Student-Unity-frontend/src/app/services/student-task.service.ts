import { SignInOutService } from './sign-in-out.service';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import { environment } from 'src/environments/environment';
import { Task } from '../models/Task';
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root',
})
export class StudentTaskService {
  private url = environment.baseUrl + '/myTasks';

  constructor(
    private http: HttpClient,
    private userService: SignInOutService
  ) {}

  public getStudentTasks(): Observable<HttpResponse<Task[]>> {
    console.log('getStudentTasks');
    return this.http.get<Task[]>(this.url + '/all', {
      observe: 'response',
      params: {
        sessionId: this.userService.getSignedInUserSessionID(),
      },
      responseType: 'json',
    }).pipe(
      map(data => {
        console.log( "data is++++++++++++++");
        console.log(data);

        return new HttpResponse<Task[]>({
          body: data.body?.map(list => {
            console.log("list is ---------");
            console.log(list);
            // @ts-ignore
            return new Task( list[0],list[1],list[2],list[3],list[4], list[5]);
            }
          ),
          headers: data.headers,
        });
      }
    ));

  }

  public getSubscribedCourses(): Observable<HttpResponse<string[]>> {
    return this.http.get<string[]>(this.url + '/allSubscribedCourses', {
      observe: 'response',
      params: {
        sessionId: this.userService.getSignedInUserSessionID(),
      },
      responseType: 'json',
    });
  }

  public filterToDoTasks(courseCode: string): Observable<HttpResponse<Task[]>> {
    return this.http.get<Task[]>(this.url + '/filterTasksByCourseCode', {
      observe: 'response',
      params: {
        sessionId: this.userService.getSignedInUserSessionID(),
        courseCode: courseCode,
      },
      responseType: 'json',
    });
  }

  public filterDoneTasks(courseCode: string): Observable<HttpResponse<Task[]>> {
    return this.http.get<Task[]>(this.url + '/filterTasksByCourseCode', {
      observe: 'response',
      params: {
        sessionId: this.userService.getSignedInUserSessionID(),
        courseCode: courseCode,
      },
      responseType: 'json',
    });
  }

  public removeTask(taskId: number): Observable<HttpResponse<any>> {
    return this.http.delete(this.url + '/removeTask', {
      observe: 'response',
      params: {
        sessionId: this.userService.getSignedInUserSessionID(),
        taskId: taskId,
      },
      responseType: 'json',
    });
  }

  public triggerTaskStatus(task: Task): Observable<HttpResponse<any>> {
    return this.http.put(this.url + '/triggerTaskStatus', {}, {
      observe: 'response',
      params: {
        sessionId: this.userService.getSignedInUserSessionID(),
        taskId: task.taskId,
        newStatus: !task.status
      },
      responseType: 'json',
    });
  }
}
