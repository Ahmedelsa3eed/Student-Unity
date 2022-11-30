import { Component, OnInit } from '@angular/core';
import { BehaviorSubject, Observable } from "rxjs";
import { User } from './../../models/User';
import { AccountService } from '../../services/account.service';

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

  constructor(private accountsService: AccountService) { }

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
    // this.users$.next(environment.dummyUsers);
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
