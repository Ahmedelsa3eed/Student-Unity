import { Component, Input, OnInit } from '@angular/core';
import { User } from './../../models/User';
import { AccountService } from '../../services/account.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  @Input() user = new User();

  constructor(private accountService: AccountService) { }

  ngOnInit(): void {
  }

  removeUser() {
    this.accountService.deleteAccount(this.user, this.user).subscribe(
      (response: any) => {
        console.log(response);
      });
  }

  // TODO: replace first parameter with registered user
  changeRole(newRole: string) {
    console.log(this.user.role)
    if(newRole.valueOf() != this.user.role) {
      console.log(newRole)
      this.accountService.changeRole(this.user, this.user, newRole).subscribe(res => {
        console.log(res);
      });
    }
  }

}
