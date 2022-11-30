import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ViewPortService {

  private viewBehaviorSubject: BehaviorSubject<string> = new BehaviorSubject("");
  public viewObservable$: Observable<string> = this.viewBehaviorSubject.asObservable();


  public updateView(newView: string){
    this.viewBehaviorSubject.next(newView);
  }

  constructor() { }
}
