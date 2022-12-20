import { TestBed } from '@angular/core/testing';

import { StudentTaskService } from './student-task.service';

describe('StudentTaskService', () => {
  let service: StudentTaskService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StudentTaskService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
