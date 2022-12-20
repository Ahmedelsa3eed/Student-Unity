import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
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
  @Output() public onDelete: EventEmitter<any> = new EventEmitter();
  public isRemoving: boolean = false;
  public isChangingRole: boolean = false;

  constructor(private accountService: AccountService, public signInOutService: SignInOutService) { }

  ngOnInit(): void {
  }

  removeUser() {
    this.isRemoving = true;
    this.accountService.deleteAccount(this.signInOutService.getSignedInUserSessionID(), this.user).subscribe(
      (response: any) => {
        this.isRemoving = false;
        this.onDelete.emit(this.user);
        console.log(response);
      },(error: any) => {
        this.isRemoving = false;
        console.log(error);
      }
      );
  }

  changeRole() {
    console.log("The new role is "+this.user.role)
    this.isChangingRole = true;
    this.accountService.changeRole(this.signInOutService.getSignedInUserSessionID(), this.user, this.user.role).
    subscribe(
      res => {
        this.isChangingRole = false;
        console.log(res);

        },
        err => {
          this.isChangingRole = false;
          console.log(err);
        }
      );
  }

}
