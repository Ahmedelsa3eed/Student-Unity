import { Component, OnInit } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from './../../models/User';
import { AccountService } from '../../services/account.service';
import { SignInOutService } from '../../services/sign-in-out.service';

@Component({
    selector: 'app-account-page',
    templateUrl: './account-page.component.html',
    styleUrls: ['./account-page.component.css'],
})
export class AccountPageComponent implements OnInit {
    public isLoading: boolean = false;
    public showAlert: boolean = false;
    public error: string = 'You are not authorized to use this page only admins can use this page';
    users?: Observable<User[]>;
    users$ = new BehaviorSubject<User[]>([]);
    public searchString: string = '';

    constructor(private accountsService: AccountService, private signInOutService: SignInOutService) {}

    ngOnInit(): void {
        this.getAccounts();
        this.users = this.users$.asObservable();
    }

    deleteAccount(removedUser: User) {
        this.users$.next(this.users$.value.filter((user) => user.email !== removedUser.email));
        this.users = this.users$.asObservable();
    }

    getAccounts() {
        this.isLoading = true;
        this.accountsService.getAccounts(this.signInOutService.getSignedInUserSessionID()).subscribe(
            (res) => {
                if (res.body) {
                    this.users$.next(res.body);
                    this.isLoading = false;
                }
            },
            (err) => {
                this.isLoading = false;
                this.showAlert = true;
            }
        );
    }

    public search() {
        this.isLoading = true;
        this.accountsService
            .searchAccounts(this.signInOutService.getSignedInUserSessionID(), this.searchString)
            .subscribe((res) => {
                if (res.body) {
                    this.users$.next(res.body);
                    this.isLoading = false;
                }
                this.isLoading = false;
            });
    }
}
