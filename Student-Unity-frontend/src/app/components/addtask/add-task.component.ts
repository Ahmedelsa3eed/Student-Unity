import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Course } from '../../models/Course';
import { map } from 'rxjs';
import { CoursesService } from '../../services/courses.service';
import { Task } from '../../models/Task';
import { TaskService } from '../../services/task.service';
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
    addingTaskLoading: Boolean = false;
    taskAdded: Boolean = false;

    constructor(
        private formBuilder: FormBuilder,
        private coursesService: CoursesService,
        private addTaskService: TaskService
    ) {}

    ngOnInit(): void {
        this.taskAdded = false;
        this.addTaskForm = this.formBuilder.group({
            Title: this.formBuilder.control(this.task.title, [Validators.required]),
            DueDate: this.formBuilder.control(this.task.dueDate, [Validators.required]),
            Course: this.formBuilder.control(this.task.course, [Validators.required]),
        });
        this.getCourses();
    }

    getCourses() {
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

    addTask() {
        this.addingTaskLoading = true;
        this.task.title = this.addTaskForm.value.Title;
        this.task.dueDate = this.addTaskForm.value.DueDate;
        this.task.course = this.addTaskForm.value.Course;
        this.task.dueDate = this.task.dueDate?.concat(':00');
        this.addTaskService.addTask(this.task).subscribe(
            (response) => {
                this.addingTaskLoading = false;
                this.taskAdded = true;
            },
            (error: HttpErrorResponse) => {
                this.addingTaskLoading = false;
            }
        );
    }
}
