import { TestBed } from '@angular/core/testing';

import { AddCourseService } from './add-course.service';

describe('AddCourseService', () => {
  let service: AddCourseService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AddCourseService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
