import { StudentTaskService } from './../../services/student-task.service';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Task } from 'src/app/models/Task';
import { STATUS } from 'src/app/models/Status';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

  @Input() task: Task = new Task;
  @Output() removingDoneTaskEvent = new EventEmitter<number>();
  @Output() removingToDoTaskEvent = new EventEmitter<number>();
  @Output() markAsDoneEvent = new EventEmitter<Task>();
  @Output() unMarkAsDoneEvent = new EventEmitter<Task>();

  constructor(private studentTaskService: StudentTaskService) { }

  ngOnInit(): void {
  }

  public removeTask() {

    this.studentTaskService.removeTask(this.task.taskId).subscribe({
      next: (res) => {
        console.log(res);
        if (res.status === 200)
          if (this.task.status === true)
            this.removingDoneTaskEvent.emit(this.task.taskId);
          else this.removingToDoTaskEvent.emit(this.task.taskId);
      }
    })
  }

  public trigerStatus() {
    this.studentTaskService.triggerTaskStatus(this.task).subscribe({
      next: (res) => {
        console.log(res);
        console.log("task", this.task);
        if (res.status === 200) {
          console.log("in if condition");
          if (this.task.status === true) {
            console.log("unmark as done");
            this.task.status = false;
            this.unMarkAsDoneEvent.emit(this.task);            
          } else {
            console.log("mark as done");
            this.task.status = true;
            this.markAsDoneEvent.emit(this.task);
          }
        }
      }
    })
  }

}
