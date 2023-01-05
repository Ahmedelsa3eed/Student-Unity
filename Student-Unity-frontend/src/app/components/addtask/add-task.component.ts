import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Course } from '../../models/Course';
import { map } from 'rxjs';
import { CoursesService } from '../../services/courses.service';
import { Task } from '../../models/Task';
import { TaskService } from '../../services/task.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NotificationService } from 'src/app/services/notification.service';

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
        private addTaskService: TaskService,
        private notificationService: NotificationService
    ) {}

    ngOnInit(): void {
        this.taskAdded = false;
        this.addTaskForm = this.formBuilder.group({
            Title: this.formBuilder.control(this.task.title, [Validators.required]),
            DueDate: this.formBuilder.control(this.task.dueDate, [Validators.required]),
            Course: this.formBuilder.control(this.task.course, [Validators.required]),
            TelegramLink: this.formBuilder.control(this.task.telegramLink),
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
        this.prepareTaskData();
        this.addTaskService.addTask(this.task).subscribe({
            next: (res) => {
                this.addingTaskLoading = false;
                if (res.ok) {
                    this.taskAdded = true;
                }
            },
            error: (error: HttpErrorResponse) => {
                this.addingTaskLoading = false;
            },
        });
        this.notificationService.sendMessageToTopic(this.task.course.name, "New task is added", "Task").subscribe();
    }

    private prepareTaskData() {
        this.task.title = this.addTaskForm.value.Title;
        this.task.dueDate = this.addTaskForm.value.DueDate;
        this.task.course = this.addTaskForm.value.Course;
        this.task.telegramLink = this.addTaskForm.value.TelegramLink;
        this.task.dueDate = this.task.dueDate?.concat(':00');
    }
}
