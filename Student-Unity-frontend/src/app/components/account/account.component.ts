import { Component, Input, OnInit } from '@angular/core';
import { User } from '../../models/User';
import { AccountService } from '../../services/account.service';
import {SignInOutService} from "../../services/sign-in-out.service";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  @Input() user = new User();

  constructor(private accountService: AccountService, public signInOutService: SignInOutService) { }

  ngOnInit(): void {
  }

  removeUser() {
    this.accountService.deleteAccount(this.signInOutService.getSignedInUser(), this.user).subscribe(
      (response: any) => {
        console.log(response);
      });
  }

  changeRole() {
    console.log("The new role is "+this.user.role)
    this.accountService.changeRole(this.signInOutService.getSignedInUser(), this.user, this.user.role).
    subscribe(res => {
        console.log(res);
      });
  }

}
