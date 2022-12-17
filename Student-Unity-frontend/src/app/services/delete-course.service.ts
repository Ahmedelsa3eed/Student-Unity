import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Course } from '../models/Course';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DeleteCourseService {

  constructor(private http: HttpClient) { }

  postCourseData(course: Course): Observable<HttpResponse<string>> {
    return this.http.post(`${environment.baseUrl}/delete-course`, course, {
      observe: "response",
      responseType: "text",
    });
  }
}
