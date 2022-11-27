import { Component, OnInit } from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {User} from "../models/User";
import {AccountsService} from "../accounts-service.service";

@Component({
  selector: 'app-account-page',
  templateUrl: './account-page.component.html',
  styleUrls: ['./account-page.component.css']
})
export class AccountPageComponent implements OnInit {

  public isLoading: boolean = false;
  users ?: Observable<User[]>;
  users$ = new BehaviorSubject<User[]>([]);

  constructor(/*private accountsService: AccountsService*/) { }

  ngOnInit(): void {
    this.getAccounts();
    this.users = this.users$.asObservable();
  }

  getAccounts() {
    // this.isLoading = true;
    // // @ts-ignore
    // this.accountsService.getAccounts().subscribe((data: User[]) => {
    //   this.users$.next(data);
    //   this.isLoading = false;
    // }, (error: any) => {
    //   console.log(error);
    //   this.isLoading = false;
    //
    // }, () => {
    //   this.isLoading = false;
    //
    // });
    // @ts-ignore
    this.users$.next([
      {
        "studentId": 1,
        "name": "Leanne Graham",
        "email": "abc@cde.fg",
        "password":"123456",
        "role": "admin",
        "sessionID": "1234567890"
      },
      {
        "studentId": 2,
        "name": "Ervin Howell",
        "email": "aaaa@gmail.com",
        "password":"123456",
        "role": "user",
        "sessionID": "1234567890"
      },
      {
        "studentId": 3,
        "name": "Clementine Bauch",
        "email": "aaqwq@aadsasd.aada",
        "password":"123456",
        "role": "user",
        "sessionID": "1234567890"
      },
      {
        "studentId": 4,
        "name": "Patricia Lebsack",
        "email": "adsads@gmail.com",
        "password":"11",
        "role": "user",
        "sessionID": "1234567890"
      },
      {
        "studentId": 5,
        "name": "Chelsey Dietrich",
        "email": "adsads@ddd.dd",
        "password":"sdd",
        "role": "user",
        "sessionID": "1234567890"
      },
      {
        "studentId": 6,
        "name": "Mrs. Dennis Schulist",
        "email": "sasas.eee@dsdsd.com",
        "password":"qqqq" ,
        "role": "user",
        "sessionID": "1234567890"
      },
      {
        "studentId": 7,
        "name": "Kurtis Weissnat",
        "email": "abc@gmail.com",
        "password":"sadads",
        "role": "user",
        "sessionID": "1234567890"
      }]);

  }

}
