import { TestBed } from '@angular/core/testing';

import { SignInOutService } from './sign-in-out.service';

describe('SignInService', () => {
    let service: SignInOutService;

    beforeEach(() => {
        TestBed.configureTestingModule({});
        service = TestBed.inject(SignInOutService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});
