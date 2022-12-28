import { StudentTaskService } from './../../services/student-task.service';
import { Component, OnInit } from '@angular/core';
import { Task } from '../../models/Task';
import { BehaviorSubject, map, Observable } from 'rxjs';
import { Course } from '../../models/Course';
import { HttpErrorResponse } from '@angular/common/http';
import { CoursesService } from '../../services/courses.service';

@Component({
    selector: 'app-tasks-page',
    templateUrl: './tasks-page.component.html',
    styleUrls: ['./tasks-page.component.css'],
})
export class TasksPageComponent implements OnInit {
    public doneTasks?: Observable<Task[]>;
    public toDoTasks?: Observable<Task[]>;
    public doneTasks$ = new BehaviorSubject<Task[]>([]);
    public toDoTasks$ = new BehaviorSubject<Task[]>([]);
    public registeredCourses: Course[] = [];
    public selectedCourseToDo: string = 'All';
    public selectedCourseDone: string = 'All';

    constructor(private studentTaskService: StudentTaskService, private coursesService: CoursesService) {}

    ngOnInit(): void {
        this.getStudentTasks();
        this.getSubscribedCourses();
    }

    public getStudentTasks() {
        this.studentTaskService.getStudentTasks().subscribe({
            next: (res) => {
                if (res.ok && res.body) {
                    this.doneTasks$.next(res.body.filter((task: Task) => task.status == true));
                    this.toDoTasks$.next(res.body.filter((task: Task) => task.status == false));
                    this.doneTasks = this.doneTasks$.asObservable();
                    this.toDoTasks = this.toDoTasks$.asObservable();
                }
            },
            error: (e) => this.handleResponseError(e),
        });
    }

    private handleResponseError(err: any) {
        console.error('Error happend', err);
    }

    public getSubscribedCourses() {
        this.coursesService
            .getUserRegisteredCourse()
            .pipe(
                map((list) => {
                    list.forEach((data: any) => {
                        let course: Course = new Course();
                        course.id = data[0];
                        course.name = data[1];
                        course.code = data[2];
                        course.revisionSubscription = data[3];
                        this.registeredCourses.push(course);
                    });
                })
            )
            .subscribe(
                () => {},
                (error: HttpErrorResponse) => {
                    if (error.status == 404) alert('User Not Found');
                }
            );
    }

    public filterToDoTasks($event: any) {
        if ($event == 'All') {
            this.getStudentTasks();
        } else {
            this.studentTaskService.filterTasks($event.id, false).subscribe({
                next: (res) => {
                    if (res.status == 200) {
                        console.log(res.body);
                        // @ts-ignore
                        this.toDoTasks$.next(res.body);
                        console.log(this.toDoTasks$.value);
                        this.toDoTasks = this.toDoTasks$.asObservable();
                        console.log(this.toDoTasks);
                    }
                },
                error: (e) => console.error(e),
            });
        }
    }

    public filterDoneTasks($event: any) {
        if ($event == 'All') {
            this.getStudentTasks();
        } else {
            this.studentTaskService.filterTasks($event.id, true).subscribe({
                next: (res) => {
                    if (res.status == 200) {
                        console.log(res.body);
                        // @ts-ignore
                        this.doneTasks$.next(res.body);
                        console.log(this.doneTasks$.value);
                        this.doneTasks = this.doneTasks$.asObservable();
                        console.log(this.doneTasks);
                    }
                },
                error: (e) => console.error(e),
            });
        }
    }

    public removeDoneTask($taskId: any) {
        this.doneTasks$.next(this.doneTasks$.value.filter((task) => task.taskId !== $taskId));
        this.doneTasks = this.doneTasks$.asObservable();
    }

    public removeToDoTask($taskId: any) {
        this.toDoTasks$.next(this.toDoTasks$.value.filter((task) => task.taskId !== $taskId));
        this.toDoTasks = this.toDoTasks$.asObservable();
    }

    public triggerDoneStatus($task: Task) {
        this.doneTasks$.next(this.doneTasks$.value.filter((task) => task.taskId !== $task.taskId));
        this.doneTasks = this.doneTasks$.asObservable();
        this.toDoTasks$.next([...this.toDoTasks$.value, $task]);
        this.toDoTasks = this.toDoTasks$.asObservable();
    }

    public triggerToDoStatus($task: Task) {
        this.toDoTasks$.next(this.toDoTasks$.value.filter((task) => task.taskId !== $task.taskId));
        this.toDoTasks = this.toDoTasks$.asObservable();
        this.doneTasks$.next([...this.doneTasks$.value, $task]);
        this.doneTasks = this.doneTasks$.asObservable();
    }
}
