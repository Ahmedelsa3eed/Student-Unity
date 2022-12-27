import { SignInOutService } from 'src/app/services/sign-in-out.service';
import { TaskService } from './../../services/task.service';
import { StudentTaskService } from './../../services/student-task.service';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Task } from 'src/app/models/Task';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Course } from 'src/app/models/Course';
import { CoursesService } from 'src/app/services/courses.service';
import { HttpErrorResponse } from '@angular/common/http';
import { map } from 'rxjs';
import { User } from 'src/app/models/User';

@Component({
    selector: 'app-task',
    templateUrl: './task.component.html',
    styleUrls: ['./task.component.css'],
})
export class TaskComponent implements OnInit {
    @Input() task: Task = new Task();
    @Output() removingDoneTaskEvent = new EventEmitter<number>();
    @Output() removingToDoTaskEvent = new EventEmitter<number>();
    @Output() markAsDoneEvent = new EventEmitter<Task>();
    @Output() unMarkAsDoneEvent = new EventEmitter<Task>();
    editTaskForm!: FormGroup;
    registeredCourses: Course[] = [];
    loggedInUser = new User();
    showAlert: boolean = false;
    error: string = '';

    constructor(
        private studentTaskService: StudentTaskService,
        private taskService: TaskService,
        private formBuilder: FormBuilder,
        private coursesService: CoursesService,
        private signInOutService: SignInOutService
    ) {
        this.error = "You Can'n edit tasks!";
    }

    ngOnInit(): void {
        this.getSignedInUser();
        this.editTaskForm = this.formBuilder.group({
            Title: this.formBuilder.control(this.task.title, [Validators.required]),
            DueDate: this.formBuilder.control(this.task.dueDate, [Validators.required]),
            Course: this.formBuilder.control(this.task.course, [Validators.required]),
        });
    }

    private getSignedInUser() {
        this.signInOutService.getSignedInUser().subscribe({
            next: (res) => (this.loggedInUser = res.body!),
            error: (err) => console.log(err),
        });
    }

    public removeTask() {
        this.studentTaskService.removeTask(this.task.taskId).subscribe({
            next: (res) => {
                if (res.status === 200)
                    if (this.task.status === true) this.removingDoneTaskEvent.emit(this.task.taskId);
                    else this.removingToDoTaskEvent.emit(this.task.taskId);
            },
        });
    }

    public trigerStatus() {
        this.studentTaskService.triggerTaskStatus(this.task).subscribe({
            next: (res) => {
                if (res.status === 200) {
                    if (this.task.status === true) {
                        this.task.status = false;
                        this.unMarkAsDoneEvent.emit(this.task);
                    } else {
                        this.task.status = true;
                        this.markAsDoneEvent.emit(this.task);
                    }
                }
            },
        });
    }

    public prepareEditTaskForm() {
        this.getCourses();
        console.log(this.task.dueDate);
        this.task.dueDate = this.task.dueDate?.slice(0, 16);
    }

    private getCourses() {
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
            .subscribe({
                error: (err: HttpErrorResponse) => {
                    if (err.status == 404) alert('User Not Found');
                },
            });
    }

    public editTask() {
        console.log(this.task);
        this.taskService.editTask(this.task).subscribe({
            next: (res) => {
                if (res.status === 200) console.log('Task Edited successfully!');
            },
        });
    }
}
