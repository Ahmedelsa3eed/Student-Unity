import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Material } from 'src/app/models/Material';
import { MaterialsService } from 'src/app/services/materials.service';

@Component({
    selector: 'app-material-page',
    templateUrl: './material-page.component.html',
    styleUrls: ['./material-page.component.css'],
})
export class MaterialPageComponent implements OnInit {
    constructor(private activatedRoute: ActivatedRoute, private materialsService: MaterialsService) {}

    courseId: number = -1;
    materialCategoryId: number = -1;
    materialCategoryName: string = '';
    materials: Material[] = [];

    ngOnInit(): void {
        this.activatedRoute.queryParams.subscribe((params) => {
            this.courseId = params['courseId'];
            this.materialCategoryId = params['materialCategoryId'];
            this.materialCategoryName = params['materialCategoryName'];
            this.materialsService.getMaterialCategoryContent(this.materialCategoryId).subscribe(
                (response) => {
                    this.materials = response;
                },
                (error: HttpErrorResponse) => {
                    alert('Something is wrong!');
                }
            );
        });
    }
}
