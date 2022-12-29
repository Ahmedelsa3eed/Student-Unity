import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MaterialCategory } from 'src/app/models/MaterialCategory';
import { MaterialsService } from 'src/app/services/materials.service';
import { CourseMaterialCategoriesService } from 'src/app/shared/course-material-categories.service';

@Component({
    selector: 'app-material-category-page',
    templateUrl: './material-category-page.component.html',
    styleUrls: ['./material-category-page.component.css'],
})
export class MaterialCategoryPageComponent implements OnInit {
    constructor(
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private materialsService: MaterialsService,
        private courseMaterialCategoriesService: CourseMaterialCategoriesService
    ) {}

    courseId: number = -1;
    materialCategories: MaterialCategory[] = [];
    editCategoryLoading: boolean = false;
    deleteCategoryLoading: boolean = false;
    categoryToEditId: number = -1;
    categoryToDeleteId: number = -1;

    ngOnInit(): void {
        this.activatedRoute.queryParams.subscribe((params) => {
            this.courseId = params['courseId'];
        });
        this.courseMaterialCategoriesService.materialCategoriesObservable.subscribe(
            (materialCategories) => (this.materialCategories = materialCategories)
        );
    }

    onOpenCategory(id: number, name: string) {
        this.router.navigate(['material'], {
            queryParams: { courseId: this.courseId, materialCategoryId: id, materialCategoryName: name },
            relativeTo: this.activatedRoute,
        });
    }

    openDeleteCategoryModal(materialCategoryId: number) {
        this.categoryToDeleteId = materialCategoryId;
        document.getElementById('openDeleteCategoryBtn')?.click();
    }

    openEditCategoryModal(materialCategoryId: number, materialCategoryName: string) {
        this.categoryToEditId = materialCategoryId;
        document.getElementById('openEditCategoryBtn')?.click();
        document.getElementById('editCategoryNameInput')?.setAttribute('value', materialCategoryName);
    }

    onDeleteCategory() {
        this.deleteCategoryLoading = true;
        this.materialsService.deleteMaterialCategory(this.categoryToDeleteId).subscribe(() => {
            this.deleteCategoryLoading = false;
            document.getElementById('closeDeleteCategoryBtn')?.click();
            window.location.reload();
        });
    }

    onEditCategory(materialCategoryName: string) {
        this.editCategoryLoading = true;
        let materialCategory: MaterialCategory = new MaterialCategory();
        materialCategory.id = this.categoryToEditId;
        materialCategory.name = materialCategoryName;
        this.materialsService.editMaterialCategory(materialCategory).subscribe(
            () => {
                this.editCategoryLoading = false;
                document.getElementById('closeEditCategoryBtn')?.click();
                window.location.reload();
            },
            (error: HttpErrorResponse) => {
                this.editCategoryLoading = false;
                alert('Something is wrong; editing the category name, the new name may be already existed!');
            }
        );
    }
}
