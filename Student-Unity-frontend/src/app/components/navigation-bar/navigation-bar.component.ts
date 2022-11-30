import { Component, OnInit } from '@angular/core';
import { ViewPortService } from 'src/app/services/view-port.service';

@Component({
  selector: 'app-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.css']
})
export class NavigationBarComponent implements OnInit {

  constructor(private viewPortService: ViewPortService) { }

  ngOnInit(): void {
  }

  public showAccountPage(): void{
    this.viewPortService.updateView("app-account-page")
  }

}
