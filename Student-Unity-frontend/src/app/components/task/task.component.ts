import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Task } from 'src/app/models/Task';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

  @Input() task: Task = new Task;
  @Output() taskRemovedEvent = new EventEmitter<String>();
  @Output() taskMarkedEvent = new EventEmitter<String>();

  constructor() { }

  ngOnInit(): void {
  }

  public removeTask() {
    this.taskRemovedEvent.emit(this.task.taskId);
  }

  public trigerStatus() {
    this.taskMarkedEvent.emit(this.task.taskId);
  }

}
