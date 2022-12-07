import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {SignInOutService} from "../../services/sign-in-out.service";

@Component({
  selector: 'app-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.css']
})
export class NavigationBarComponent implements OnInit {

  constructor(private router: Router, private signInOutService:SignInOutService) { }

  ngOnInit(): void {
  }

  navigateToAccounts(){
    this.router.navigateByUrl("home/accountsPage");
  }

  logout(){
    this.signInOutService.signOut();
    this.router.navigateByUrl("sign-in");
  }

}
