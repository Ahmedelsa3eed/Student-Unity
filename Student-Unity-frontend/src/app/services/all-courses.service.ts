import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CourseCard } from '../models/course-card';

@Injectable({
  providedIn: 'root'
})
export class AllCoursesService {

  constructor(private httpClient: HttpClient) { }

  getAllCourses(): Observable<CourseCard[]>{
    return this.httpClient.get<CourseCard[]>("/assets/all-courses-data.json");
  }
}
