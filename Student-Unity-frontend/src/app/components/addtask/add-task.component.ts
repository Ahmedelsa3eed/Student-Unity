import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Course} from "../../models/Course";
import {BehaviorSubject} from "rxjs";
import {User} from "../../models/User";
import {CoursesService} from "../../services/courses.service";
import {AccountService} from "../../services/account.service";
import {SignInOutService} from "../../services/sign-in-out.service";
import {Task} from "../../models/Task";
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import {AddTaskService} from "../../services/add-task.service";

@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.css']
})
export class AddTaskComponent implements OnInit {
  addTaskForm !: FormGroup;
  task = new Task();
  courses ?: Observable<Course[]>;
  courses$ = new BehaviorSubject<Course[]>([]);

  myControl = new FormControl('');
  options: string[] = ['One', 'Two', 'Three'];
  filteredOptions?: Observable<string[]>;

  constructor(private formBuilder:FormBuilder,private coursesService:CoursesService, private signInOutService:SignInOutService, private addTaskService:AddTaskService) { }



  ngOnInit(): void {
    this.addTaskForm = this.formBuilder.group(
      {
        Title : this.formBuilder.control('',[Validators.required]),
        DueDate : this.formBuilder.control(''),
        Course : this.formBuilder.control('',[Validators.required]),
        Time : this.formBuilder.control('')
      }

    )
    this.courses$.next([
      {
        "id": 1,
        "name": "AI",
        "code": "ABC",
        "timeTable": "12:00",
        "telegramLink": "",
        "status":true ,
        "notificationsToken": "",
        "revisionSubscription": false
      },
      {
        "id": 1,
        "name": "ER",
        "code": "AI123",
        "timeTable": "12:00",
        "telegramLink": "",
        "status":true ,
        "notificationsToken": "",
        "revisionSubscription": false
      },
      {
        "id": 1,
        "name": "CO",
        "code": "AI123",
        "timeTable": "12:00",
        "telegramLink": "",
        "status":true ,
        "notificationsToken": "",
        "revisionSubscription": false
      },
      {
        "id": 1,
        "name": "Math",
        "code": "AI123",
        "timeTable": "12:00",
        "telegramLink": "",
        "status":true ,
        "notificationsToken": "",
        "revisionSubscription": false
      },
      {
        "id": 1,
        "name": "EEEI",
        "code": "AI123",
        "timeTable": "12:00",
        "telegramLink": "",
        "status":true ,
        "notificationsToken": "",
        "revisionSubscription": false
      },
      {
        "id": 1,
        "name": "SWE",
        "code": "AI123",
        "timeTable": "12:00",
        "telegramLink": "",
        "status":true ,
        "notificationsToken": "",
        "revisionSubscription": false
      },
      {
        "id": 1,
        "name": "Algorithms",
        "code": "AI123",
        "timeTable": "12:00",
        "telegramLink": "",
        "status":true ,
        "notificationsToken": ""
      }] as Course[]);
    this.courses = this.courses$.asObservable();
  }

  getCourses()  {
    // return dummy data courses for now

    this.courses$.next([
        {
            id: 1,
            name: 'AI',
            code: 'AI123',
            timeTable: '12:00',
            telegramLink: '',
            status: true,
            notificationsToken: '',
        },
        {
            id: 1,
            name: 'ER',
            code: 'AI123',
            timeTable: '12:00',
            telegramLink: '',
            status: true,
            notificationsToken: '',
        },
        {
            id: 1,
            name: 'CO',
            code: 'AI123',
            timeTable: '12:00',
            telegramLink: '',
            status: true,
            notificationsToken: '',
        },
        {
            id: 1,
            name: 'Math',
            code: 'AI123',
            timeTable: '12:00',
            telegramLink: '',
            status: true,
            notificationsToken: '',
        },
        {
            id: 1,
            name: 'EEEI',
            code: 'AI123',
            timeTable: '12:00',
            telegramLink: '',
            status: true,
            notificationsToken: '',
        },
        {
            id: 1,
            name: 'SWE',
            code: 'AI123',
            timeTable: '12:00',
            telegramLink: '',
            status: true,
            notificationsToken: '',
        },
        {
            id: 1,
            name: 'Algorithms',
            code: 'Algo123',
            timeTable: '12:00',
            telegramLink: '',
            status: true,
            notificationsToken: '',
        },
    ] as Course[])
    this.courses = this.courses$.asObservable();


    // this.coursesService.getUserRegisteredCourse(this.signInOutService.getSignedInUserSessionID())
    // .subscribe(
    //   res => {
    //     this.courses$.next(res);
    //     console.log(res)
    //
    // });
    //

  }
  addTask() {
    console.log(this.addTaskForm.value);
    this.task.title = this.addTaskForm.value.Title;
    this.task.dueDate = this.addTaskForm.value.DueDate;
    this.task.course = this.addTaskForm.value.Course;
    this.task.dueDate += this.addTaskForm.value.Time;
    console.log(this.task);
    this.addTaskService.addTask( this.signInOutService.getSignedInUserSessionID() ,this.task ).subscribe(
      res => {
        console.log(res);
        if (res.status == 200) {
          console.log("Task Added Successfully");
        }
      },
      err => {
        console.log(err);

      });




  }

}
