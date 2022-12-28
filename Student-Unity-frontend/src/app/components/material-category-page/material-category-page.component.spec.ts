import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MaterialCategoryPageComponent } from './material-category-page.component';

describe('MaterialCategoryPageComponent', () => {
    let component: MaterialCategoryPageComponent;
    let fixture: ComponentFixture<MaterialCategoryPageComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [MaterialCategoryPageComponent],
        }).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(MaterialCategoryPageComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
