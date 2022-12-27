import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Course } from '../../models/Course';
import { map } from 'rxjs';
import { CoursesService } from '../../services/courses.service';
import { SignInOutService } from '../../services/sign-in-out.service';
import { Task } from '../../models/Task';
import { AddTaskService } from '../../services/add-task.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
    selector: 'app-add-task',
    templateUrl: './add-task.component.html',
    styleUrls: ['./add-task.component.css'],
})
export class AddTaskComponent implements OnInit {
    addTaskForm!: FormGroup;
    task = new Task();
    registeredCourses: Course[] = [];

    constructor(
        private formBuilder: FormBuilder,
        private coursesService: CoursesService,
        private signInOutService: SignInOutService,
        private addTaskService: AddTaskService
    ) {}

    ngOnInit(): void {
        this.addTaskForm = this.formBuilder.group({
            Title: this.formBuilder.control('', [Validators.required]),
            DueDate: this.formBuilder.control(''),
            Course: this.formBuilder.control('', [Validators.required]),
            Time: this.formBuilder.control(''),
        });
        this.getCourses();
    }

    getCourses() {
        this.coursesService
            .getUserRegisteredCourse(this.signInOutService.getSignedInUserSessionID())
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

    addTask() {
        this.task.title = this.addTaskForm.value.Title;
        this.task.dueDate = this.addTaskForm.value.DueDate;
        this.task.course = this.addTaskForm.value.Course;
        this.task.dueDate = this.task.dueDate?.concat(':00');
        this.addTaskService.addTask(this.signInOutService.getSignedInUserSessionID(), this.task).subscribe(
            (res) => {
                if (res.status == 200) {
                    console.log('Task Added Successfully');
                }
            },
            (err) => {
                console.log(err);
            }
        );
    }
}
