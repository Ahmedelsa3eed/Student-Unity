import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MaterialCategory } from 'src/app/models/MaterialCategory';
import { MaterialsService } from 'src/app/services/materials.service';

@Component({
    selector: 'app-material-category-page',
    templateUrl: './material-category-page.component.html',
    styleUrls: ['./material-category-page.component.css'],
})
export class MaterialCategoryPageComponent implements OnInit {
    constructor(
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private materialsService: MaterialsService
    ) {}

    courseId: number = -1;
    materialCategories: MaterialCategory[] = [];

    ngOnInit(): void {
        this.activatedRoute.queryParams.subscribe((params) => {
            this.courseId = params['courseId'];
            this.materialsService.getCourseMaterialCategories(this.courseId).subscribe(
                (response) => {
                    this.materialCategories = response;
                },
                (error: HttpErrorResponse) => {
                    alert('Something is wrong!');
                }
            );
        });
    }

    onOpenCategory(id: number, name: string) {
        this.router.navigate(['material'], {
            queryParams: { courseId: this.courseId, materialCategoryId: id, materialCategoryName: name },
            relativeTo: this.activatedRoute,
        });
    }
}
