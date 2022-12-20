import { StudentTaskService } from './../../services/student-task.service';
import { Component, OnInit } from '@angular/core';
import { Task } from '../../models/Task';
import { STATUS } from 'src/app/models/Status';
import {BehaviorSubject, map, Observable} from "rxjs";
import {Course} from "../../models/Course";
import {HttpErrorResponse} from "@angular/common/http";
import {CoursesService} from "../../services/courses.service";
import {SignInOutService} from "../../services/sign-in-out.service";

@Component({
  selector: 'app-tasks-page',
  templateUrl: './tasks-page.component.html',
  styleUrls: ['./tasks-page.component.css'],
})
export class TasksPageComponent implements OnInit {
  public doneTasks?: Observable<Task[]> ;
  public toDoTasks?: Observable<Task[]>;
  public doneTasks$ = new BehaviorSubject<Task[]>([])
  public toDoTasks$ = new BehaviorSubject<Task[]>([])
  public registeredCourses: Course[] = [];
  public selectedCourse: string = 'All';

  constructor(private studentTaskService: StudentTaskService,
              private coursesService:CoursesService,
              private signInOutService:SignInOutService) {}

  ngOnInit(): void {
    this.getStudentTasks();
    this.getSubscribedCourses();
    this.doneTasks = this.doneTasks$.asObservable()
    this.toDoTasks = this.toDoTasks$.asObservable()
  }

  public getStudentTasks() {
    this.studentTaskService.getStudentTasks().subscribe({
      next: (res) => {
        if (res.ok && res.body) {
          this.doneTasks$.next( res.body.filter((task: Task) => task.status == true));
          this.toDoTasks$.next(  res.body.filter((task: Task) => task.status == false));
        }
      },
      error: (e) => this.handleResponseError(e),
    });
  }

  private handleResponseError(err: any) {
    console.error('Error happend', err);
  }

  public getSubscribedCourses() {
    this.coursesService.getUserRegisteredCourse(this.signInOutService.getSignedInUserSessionID()).pipe(
      map(list => {list.forEach((data: any) => {
        let course: Course = new Course;
        course.id = data[0];
        course.name = data[1];
        course.code = data[2];
        course.revisionSubscription = data[3];
        this.registeredCourses.push(course);
      });})
    ).subscribe(
      () => {},
      (error: HttpErrorResponse) => {
        if(error.status == 404)
          alert("User Not Found");
      }
    );
  }

  public filterToDoTasks($event: any) {
    this.studentTaskService.filterToDoTasks($event).subscribe({
      next: (res) => {
        if (res.ok && res.body) {
          this.toDoTasks$.next( res.body.filter((task) => task.status === false));
        }
      },
      error: (e) => console.error(e),
    });
  }

  public filterDoneTasks($event: any) {
    this.studentTaskService.filterDoneTasks($event).subscribe({
      next: (res) => {
        if (res.ok && res.body) {
          this.doneTasks$.next(res.body.filter((task) => task.status === true));
        }
      },
      error: (e) => console.error(e),
    });
  }

  public removeDoneTask($taskId: any) {
    this.doneTasks$.next( this.doneTasks$.value.filter((task) => task.taskId !== $taskId));
    this.doneTasks = this.doneTasks$.asObservable()
  }

  public removeToDoTask($taskId: any) {
    this.toDoTasks$.next( this.toDoTasks$.value.filter((task) => task.taskId !== $taskId));
    this.toDoTasks = this.toDoTasks$.asObservable()
  }

  public triggerDoneStatus($task: Task) {
    this.doneTasks$.next( this.doneTasks$.value.filter((task) => task.taskId !== $task.taskId));
    this.doneTasks = this.doneTasks$.asObservable()
    this.toDoTasks$.next( [...this.toDoTasks$.value, $task]);
    this.toDoTasks = this.toDoTasks$.asObservable()
  }


  public triggerToDoStatus($task: Task) {
    this.toDoTasks$.next( this.toDoTasks$.value.filter((task) => task.taskId !== $task.taskId));
    this.toDoTasks = this.toDoTasks$.asObservable()
    this.doneTasks$.next( [...this.doneTasks$.value, $task]);
    this.doneTasks = this.doneTasks$.asObservable()

  }

}
