import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewPortComponent } from './view-port.component';

describe('ViewPortComponent', () => {
  let component: ViewPortComponent;
  let fixture: ComponentFixture<ViewPortComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewPortComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewPortComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
