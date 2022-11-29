import { TestBed } from '@angular/core/testing';

import { AccountsService } from './accounts-service.service';

describe('AccountsServiceService', () => {
  let service: AccountsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AccountsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
