import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {SignInOutService} from "../../services/sign-in-out.service";
import {User} from "../../models/User";

@Component({
  selector: 'app-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.css']
})
export class NavigationBarComponent implements OnInit {

<<<<<<< HEAD
  loggedInUser = new User();
=======
  loggedInUser ?: User | null ;
>>>>>>> milestone2
  constructor(private router: Router, private signInOutService:SignInOutService) { }
  public isLoading: boolean = false;
  ngOnInit(): void {
    this.getSignedInUser();
  }

  navigateTo(child: string){
    this.router.navigateByUrl("home/" + child);
  }

  logout(){
    this.signInOutService.signOut();
    this.router.navigateByUrl("sign-in");
  }

  getSignedInUser(){
    this.isLoading = true;
    this.signInOutService.getSignedInUser().subscribe(
      res => {
        console.log(res);
        this.isLoading = false;
<<<<<<< HEAD
        if (res.body) {
          this.loggedInUser = res.body;
        }

=======
        this.loggedInUser = res.body;
>>>>>>> milestone2
    },
      err => {
        this.isLoading = false;
        console.log(err);
      }
      );
  }


}
