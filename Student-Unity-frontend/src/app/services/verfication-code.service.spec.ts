import { TestBed } from '@angular/core/testing';

import { VerficationCodeService } from './verfication-code.service';

describe('VerficationCodeService', () => {
  let service: VerficationCodeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VerficationCodeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
