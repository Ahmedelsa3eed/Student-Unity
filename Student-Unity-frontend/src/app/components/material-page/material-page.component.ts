import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Material } from 'src/app/models/Material';
import { MaterialsService } from 'src/app/services/materials.service';
import { SignInOutService } from 'src/app/services/sign-in-out.service';

@Component({
    selector: 'app-material-page',
    templateUrl: './material-page.component.html',
    styleUrls: ['./material-page.component.css'],
})
export class MaterialPageComponent implements OnInit {
    constructor(
        private activatedRoute: ActivatedRoute,
        private materialsService: MaterialsService,
        private router: Router,
        private signInOutService: SignInOutService
    ) {}

    courseId: number = -1;
    materialCategoryId: number = -1;
    materialCategoryName: string = '';
    materials: Material[] = [];
    materialToDeleteId: number = -1;
    materialToEditId: number = -1;
    deleteMaterialLoading: boolean = false;
    editMaterialLoading: boolean = false;
    signedInUserRole: string = this.signInOutService.getSignedInUserRole();

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

    openDeleteMaterialModal(materialId: number) {
        this.materialToDeleteId = materialId;
        document.getElementById('openDeleteMaterialBtn')?.click();
    }

    openEditMaterialModal(materialId: number, materialTitle: string, materialURL: string) {
        this.materialToEditId = materialId;
        document.getElementById('openEditMaterialBtn')?.click();
        document.getElementById('editMaterialTitleInput')?.setAttribute('value', materialTitle);
        document.getElementById('editMaterialURLInput')?.setAttribute('value', materialURL);
    }

    onDeleteMaterial() {
        this.deleteMaterialLoading = true;
        this.materialsService.deleteMaterial(this.materialToDeleteId).subscribe(() => {
            this.deleteMaterialLoading = false;
            document.getElementById('closeDeleteMaterialBtn')?.click();
            window.location.reload();
        });
    }

    onEditMaterial(materialTitle: string, materialURL: string) {
        this.editMaterialLoading = true;
        let material: Material = new Material();
        material.id = this.materialToEditId;
        material.title = materialTitle;
        material.url = materialURL;
        material.materialCategoryId = this.materialCategoryId;
        this.materialsService.editMaterial(material).subscribe(
            () => {
                this.editMaterialLoading = false;
                document.getElementById('closeEditMaterialBtn')?.click();
                window.location.reload();
            },
            (error: HttpErrorResponse) => {
                this.editMaterialLoading = false;
                alert('Something is wrong; editing the material, the new title may be already existed!');
            }
        );
    }
}
