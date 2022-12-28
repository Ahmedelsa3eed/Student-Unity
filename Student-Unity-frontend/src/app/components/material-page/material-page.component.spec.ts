import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MaterialPageComponent } from './material-page.component';

describe('MaterialPageComponent', () => {
    let component: MaterialPageComponent;
    let fixture: ComponentFixture<MaterialPageComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [MaterialPageComponent],
        }).compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(MaterialPageComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
