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
  @Output() removingDoneTaskEvent = new EventEmitter<String>();
  @Output() removingToDoTaskEvent = new EventEmitter<String>();
  @Output() markAsDoneEvent = new EventEmitter<String>();
  @Output() unMarkAsDoneEvent = new EventEmitter<String>();

  constructor(private studentTaskService: StudentTaskService) { }

  ngOnInit(): void {
  }

  public removeTask() {
    this.studentTaskService.removeTask(this.task.taskId).subscribe({
      next: (res) => {
        if (res.ok && res.body)
          if (this.task.status === STATUS.DONE)
            this.removingDoneTaskEvent.emit(this.task.taskId);
          else this.removingToDoTaskEvent.emit(this.task.taskId);
      }
    })
  }

  public trigerStatus() {
    this.studentTaskService.triggerTaskStatus(this.task.taskId).subscribe({
      next: (res) => {
        if (res.ok && res.body)
          if (this.task.status === STATUS.DONE) {
            this.task.status = STATUS.TODO;
            this.unMarkAsDoneEvent.emit(this.task.taskId);
          }
          else {
            this.task.status = STATUS.DONE;
            this.markAsDoneEvent.emit(this.task.taskId);
          }
      }
    })
  }

}
