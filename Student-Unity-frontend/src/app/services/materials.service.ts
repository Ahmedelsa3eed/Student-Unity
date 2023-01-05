import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Material } from '../models/Material';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { SignInOutService } from './sign-in-out.service';
import { MaterialCategory } from '../models/MaterialCategory';

@Injectable({
    providedIn: 'root',
})
export class MaterialsService {
    constructor(private httpClient: HttpClient, private signInOutService: SignInOutService) {}

    addMaterial(material: Material): Observable<any> {
        return this.httpClient.post(environment.baseUrl + '/materials/admin/addMaterial', material, {
            params: { sessionId: this.signInOutService.getSignedInUserSessionID() },
        });
    }

    deleteMaterial(materialId: number): Observable<any> {
        return this.httpClient.delete(environment.baseUrl + '/materials/admin/deleteMaterial', {
            params: { sessionId: this.signInOutService.getSignedInUserSessionID(), materialId: materialId },
        });
    }

    editMaterial(material: Material): Observable<any> {
        return this.httpClient.put(environment.baseUrl + '/materials/admin/editMaterial', material, {
            params: { sessionId: this.signInOutService.getSignedInUserSessionID() },
        });
    }

    addMaterialCategory(materialCategory: MaterialCategory): Observable<any> {
        return this.httpClient.post(environment.baseUrl + '/materials/admin/addMaterialCategory', materialCategory, {
            params: { sessionId: this.signInOutService.getSignedInUserSessionID() },
        });
    }

    deleteMaterialCategory(materialCategoryId: number): Observable<any> {
        return this.httpClient.delete(environment.baseUrl + '/materials/admin/deletedMaterialCategory', {
            params: {
                sessionId: this.signInOutService.getSignedInUserSessionID(),
                materialCategoryId: materialCategoryId,
            },
        });
    }

    editMaterialCategory(materialCategory: MaterialCategory): Observable<any> {
        return this.httpClient.put(environment.baseUrl + '/materials/admin/editMaterialCategory', materialCategory, {
            params: { sessionId: this.signInOutService.getSignedInUserSessionID() },
        });
    }

    getCourseMaterialCategories(courseId: number): Observable<any> {
        return this.httpClient.get(environment.baseUrl + '/materials/getCourseMaterialCategories', {
            params: { courseId: courseId },
        });
    }

    getMaterialCategoryContent(materialCategoryId: number): Observable<any> {
        return this.httpClient.get(environment.baseUrl + '/materials/getMaterialCategoryContent', {
            params: { materialCategoryId: materialCategoryId },
        });
    }
}
