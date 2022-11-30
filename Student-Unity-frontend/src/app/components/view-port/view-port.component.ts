import { Component, OnInit } from '@angular/core';
import { ViewPortService } from 'src/app/services/view-port.service';

@Component({
  selector: 'app-view-port',
  templateUrl: './view-port.component.html',
  styleUrls: ['./view-port.component.css']
})

export class ViewPortComponent implements OnInit {

  constructor(private viewPortService: ViewPortService) { }

  public currentView: string = "";

  ngOnInit(): void {
    this.viewPortService.viewObservable$.subscribe(
      newView => this.currentView = newView
    );
  }

}
