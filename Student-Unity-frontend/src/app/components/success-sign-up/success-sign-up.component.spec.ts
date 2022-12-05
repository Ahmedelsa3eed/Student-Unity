import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuccessSignUpComponent } from './success-sign-up.component';

describe('SuccessSignUpComponent', () => {
  let component: SuccessSignUpComponent;
  let fixture: ComponentFixture<SuccessSignUpComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SuccessSignUpComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SuccessSignUpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
