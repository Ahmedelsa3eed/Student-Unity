import { StudentTaskService } from './../../services/student-task.service';
import { Component, OnInit } from '@angular/core';
import { Task } from '../../models/Task';
import { STATUS } from 'src/app/models/Status';
import {BehaviorSubject, Observable} from "rxjs";

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
  public courses: string[] = [];
  public selectedCourse: string = 'All';

  constructor(private studentTaskService: StudentTaskService) {}

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
          console.log("res.body", res.body);
          console.log(res.body);
          this.doneTasks$.next( res.body.filter((task: Task) => task.status == true));
          this.toDoTasks$.next(  res.body.filter((task: Task) => task.status == false));
        }
        console.log("doneTasks", this.doneTasks$);
        console.log("toDoTasks", this.toDoTasks$);
      },
      error: (e) => this.handleResponseError(e),
    });
    console.log(this.doneTasks);

  }

  private handleResponseError(err: any) {
    console.error('Error happend', err);
  }

  public getSubscribedCourses() {
    this.studentTaskService.getSubscribedCourses().subscribe({
      next: (res) => {
        if (res.ok && res.body) {
          this.courses = res.body;
        }
      },
    });
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
    console.log("trigerToDoStatus", this.toDoTasks$.value);
    this.toDoTasks$.next( this.toDoTasks$.value.filter((task) => task.taskId !== $task.taskId));
    this.toDoTasks = this.toDoTasks$.asObservable()
    this.doneTasks$.next( [...this.doneTasks$.value, $task]);
    this.doneTasks = this.doneTasks$.asObservable()

  }

}
