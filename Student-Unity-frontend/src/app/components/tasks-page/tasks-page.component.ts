import { StudentTaskService } from './../../services/student-task.service';
import { Component, OnInit } from '@angular/core';
import { Task } from '../../models/Task';
import { STATUS } from 'src/app/models/Status';

@Component({
  selector: 'app-tasks-page',
  templateUrl: './tasks-page.component.html',
  styleUrls: ['./tasks-page.component.css'],
})
export class TasksPageComponent implements OnInit {
  public doneTasks: Task[] = [];
  public toDoTasks: Task[] = [];
  public courses: string[] = [];
  public selectedCourse: string = 'All';

  constructor(private studentTaskService: StudentTaskService) {}

  ngOnInit(): void {
    this.getStudentTasks();
    this.getSubscribedCourses();
  }

  public getStudentTasks() {
    this.studentTaskService.getStudentTasks().subscribe({
      next: (res) => {
        if (res.ok && res.body) {
          this.doneTasks = res.body.filter(
            (task) => task.status === STATUS.DONE
          );
          this.toDoTasks = res.body.filter(
            (task) => task.status === STATUS.TODO
          );
        }
      },
      error: (e) => this.handleResponseError(e),
    });
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
          this.toDoTasks = res.body.filter(
            (task) => task.status === STATUS.TODO
          );
        }
      },
      error: (e) => console.error(e),
    });
  }

  public filterDoneTasks($event: any) {
    this.studentTaskService.filterDoneTasks($event).subscribe({
      next: (res) => {
        if (res.ok && res.body) {
          this.doneTasks = res.body.filter(
            (task) => task.status === STATUS.DONE
          );
        }
      },
      error: (e) => console.error(e),
    });
  }

  public removeDoneTask($taskId: any) {
    this.studentTaskService.removeTask($taskId).subscribe({
      next: (res) => {
        if (res.ok && res.body) {
          this.doneTasks = this.doneTasks.filter(
            (task) => task.status === STATUS.DONE && task.taskId !== $taskId
          );
        }
      },
    });
  }

  public removeToDoTask($taskId: any) {
    this.studentTaskService.removeTask($taskId).subscribe({
      next: (res) => {
        if (res.ok && res.body) {
          this.toDoTasks = this.toDoTasks.filter(
            (task) => task.status === STATUS.TODO && task.taskId !== $taskId
          );
        }
      },
    });
  }

  public trigerDoneStatus($taskId: any) {
    this.studentTaskService.triggerTaskStatus($taskId).subscribe({
      next: (res) => {
        if (res.ok && res.body) {
          let task = this.doneTasks.find((task) => $taskId == task.taskId);
          if (task) this.toDoTasks.push(task);
          this.doneTasks = this.doneTasks.filter(
            (task) => $taskId !== task.taskId
          );
        }
      },
    });
  }

  public trigerToDoStatus($taskId: any) {
    this.studentTaskService.triggerTaskStatus($taskId).subscribe({
      next: (res) => {
        if (res.ok && res.body) {
          let task = this.toDoTasks.find((task) => $taskId == task.taskId);
          if (task) this.doneTasks.push(task);
          this.toDoTasks = this.toDoTasks.filter(
            (task) => $taskId !== task.taskId
          );
        }
      },
    });
  }
}
