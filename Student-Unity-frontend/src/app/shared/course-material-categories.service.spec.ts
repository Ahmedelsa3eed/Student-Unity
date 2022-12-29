import { TestBed } from '@angular/core/testing';

import { CourseMaterialCategoriesService } from './course-material-categories.service';

describe('CourseMaterialCategoriesService', () => {
    let service: CourseMaterialCategoriesService;

    beforeEach(() => {
        TestBed.configureTestingModule({});
        service = TestBed.inject(CourseMaterialCategoriesService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});
