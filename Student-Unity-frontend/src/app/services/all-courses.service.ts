import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Course } from '../models/Course';

@Injectable({
    providedIn: 'root',
})
export class AllCoursesService {
    constructor(private httpClient: HttpClient) {}

    getAllCourses(): Observable<Course[]> {
        return this.httpClient.get<any>(`${environment.baseUrl}/AllCourses/getAllCourses`);
    }

    getAllActiveCourses(): Observable<Course[]> {
        return this.httpClient.get<any>(`${environment.baseUrl}/AllCourses/getAllActiveCourses`);
    }

    postCourseData(sessionId: string, course: Course): Observable<HttpResponse<string>> {
        console.log(course);
        console.log(`${environment.baseUrl}/AllCourses/addCourse`);
        return this.httpClient.post(`${environment.baseUrl}/AllCourses/addCourse`, course, {
            params: {
                sessionId: sessionId,
            },
            observe: 'response',
            responseType: 'text',
        });
    }
    deleteCourse(sessionId: string, code: string): Observable<HttpResponse<string>> {
        return this.httpClient.delete(`${environment.baseUrl}/AllCourses/removeCourse`, {
            params: {
                sessionId: sessionId,
                code: code,
            },
            observe: 'response',
            responseType: 'text',
        });
    }

    registerCourse(sessionId: string, courseId: number): Observable<any> {
        return this.httpClient.put(`${environment.baseUrl}/AllCourses/registerCourse/${sessionId}/${courseId}`, {});
    }
    makeCourseActive(sessionId: string, code: string): Observable<HttpResponse<string>> {
        return this.httpClient.put(
            `${environment.baseUrl}/AllCourses/makeCourseActive`,
            {},
            {
                params: {
                    sessionId: sessionId,
                    code: code,
                },
                observe: 'response',
                responseType: 'text',
            }
        );
    }
}
