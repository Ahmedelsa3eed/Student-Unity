import { TestBed } from '@angular/core/testing';

import { ViewPortService } from './view-port.service';

describe('ViewPortService', () => {
  let service: ViewPortService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ViewPortService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
