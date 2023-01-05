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
    userRole: string = this.signInOutService.getSignedInUserRole();
    showAlert: boolean = false;
    error: string = '';
    removeLoading: Boolean = false;

    constructor(
        private studentTaskService: StudentTaskService,
        private taskService: TaskService,
        private formBuilder: FormBuilder,
        private coursesService: CoursesService,
        private signInOutService: SignInOutService
    ) {
        this.error = "You Can't edit tasks!";
    }

    ngOnInit(): void {
        this.editTaskForm = this.formBuilder.group({
            Title: this.formBuilder.control(this.task.title, [Validators.required]),
            DueDate: this.formBuilder.control(this.task.dueDate, [Validators.required]),
            Course: this.formBuilder.control(this.task.course, [Validators.required]),
            TelegramLink: this.formBuilder.control(this.task.telegramLink),
        });
    }

    public removeTask() {
        this.removeLoading = true;
        this.studentTaskService.removeTask(this.task.taskId).subscribe({
            next: (res) => {
                if (res.status === 200) {
                    this.removeLoading = false;
                    if (this.task.status === true) this.removingDoneTaskEvent.emit(this.task.taskId);
                    else this.removingToDoTaskEvent.emit(this.task.taskId);
                }
                if (this.task.status === true) this.removingDoneTaskEvent.emit(this.task.taskId);
                else this.removingToDoTaskEvent.emit(this.task.taskId);
            },
            error: (err) => {
                this.removeLoading = false;
                this.showAlert = true;
                console.log(err);
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

    private fixDateBeforeSend(date: string | undefined): string {
        let result = '';
        // @ts-ignore
        let dateParts = date.split(' ');
        result = dateParts[0] + 'T' + dateParts[1];
        return result;
    }
    public editTask() {
        let editedTask = new Task();
        editedTask.title = this.editTaskForm.value.Title;
        editedTask.dueDate = this.editTaskForm.value.DueDate;
        editedTask.course = this.editTaskForm.value.Course;
        editedTask.taskId = this.task.taskId;
        editedTask.status = this.task.status;
        editedTask.dueDate = this.fixDateBeforeSend(editedTask.dueDate).substring(0, 16) + ':00';
        console.log(editedTask);
        this.taskService.editTask(editedTask).subscribe({
            next: (res) => {
                console.log('Received Response');
                console.log(res);
                if (res.status == 200) {
                    console.log('Task Edited successfully!');
                    this.task = editedTask;
                    //reload the page
                    window.location.reload();
                } else {
                    console.log('Error Occurred');
                }
            },
        });
    }
}
