import { TestBed } from '@angular/core/testing';

import { VerficationService } from './verfication.service';

describe('VerficationService', () => {
    let service: VerficationService;

    beforeEach(() => {
        TestBed.configureTestingModule({});
        service = TestBed.inject(VerficationService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});
