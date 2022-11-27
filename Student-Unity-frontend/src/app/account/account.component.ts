import {Component, Input, OnInit} from '@angular/core';
import { User} from "../models/User";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {
  @Input() user = new User();
  constructor() { }

  ngOnInit(): void {
  }

  // This is a method that is called when the user clicks the "remove" button.
  // It will remove the user from the list of users.
  removeUser() {

  }

  // This is a method that is called when the user clicks the "make admin" button.
  // It will make the user an admin.
  makeAdmin() {

  }

}
