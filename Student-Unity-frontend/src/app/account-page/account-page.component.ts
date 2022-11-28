import { Component, OnInit } from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {User} from "../models/User";
import { AccountsService } from '../services/accounts-service.service';

@Component({
  selector: 'app-account-page',
  templateUrl: './account-page.component.html',
  styleUrls: ['./account-page.component.css']
})
export class AccountPageComponent implements OnInit {

  public user = new User();// just till we made the currentUser service
  public searchLoading = false;
  public isLoading: boolean = false;
  users ?: Observable<User[]>;
  users$ = new BehaviorSubject<User[]>([]);
  public searchString: string = "";

  constructor(private accountsService: AccountsService) { }

  ngOnInit(): void {
    this.getAccounts();
    this.users = this.users$.asObservable();
  }

  getAccounts() {
    this.isLoading = true;
    this.accountsService.getAccounts(this.user).subscribe(res => {
      if(res.body) {
        this.users$.next(res.body);
        console.log(res.body)
        this.isLoading = false;
      }
    });
    // @ts-ignore
    // this.users$.next([
    //   {
    //     "studentId": 1,
    //     "name": "Leanne Graham",
    //     "email": "abc@cde.fg",
    //     "password":"123456",
    //     "role": "admin",
    //     "sessionID": "1234567890"
    //   },
    //   {
    //     "studentId": 2,
    //     "name": "Ervin Howell",
    //     "email": "aaaa@gmail.com",
    //     "password":"123456",
    //     "role": "user",
    //     "sessionID": "1234567890"
    //   },
    //   {
    //     "studentId": 3,
    //     "name": "Clementine Bauch",
    //     "email": "aaqwq@aadsasd.aada",
    //     "password":"123456",
    //     "role": "user",
    //     "sessionID": "1234567890"
    //   },
    //   {
    //     "studentId": 4,
    //     "name": "Patricia Lebsack",
    //     "email": "adsads@gmail.com",
    //     "password":"11",
    //     "role": "user",
    //     "sessionID": "1234567890"
    //   },
    //   {
    //     "studentId": 5,
    //     "name": "Chelsey Dietrich",
    //     "email": "adsads@ddd.dd",
    //     "password":"sdd",
    //     "role": "user",
    //     "sessionID": "1234567890"
    //   },
    //   {
    //     "studentId": 6,
    //     "name": "Mrs. Dennis Schulist",
    //     "email": "sasas.eee@dsdsd.com",
    //     "password":"qqqq" ,
    //     "role": "user",
    //     "sessionID": "1234567890"
    //   },
    //   {
    //     "studentId": 7,
    //     "name": "Kurtis Weissnat",
    //     "email": "abc@gmail.com",
    //     "password":"sadads",
    //     "role": "user",
    //     "sessionID": "1234567890"
    //   }]);

  }
  public search() {
    this.searchLoading = true;
    this.accountsService.searchAccounts(this.user, this.searchString).subscribe(res => {
      if(res.body) {
        this.users$.next(res.body);
        console.log(res.body)
        this.isLoading = false;

      }
      this.searchLoading = false;
    });

  }

}
