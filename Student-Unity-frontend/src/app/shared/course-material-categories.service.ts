import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { MaterialCategory } from '../models/MaterialCategory';

@Injectable({
    providedIn: 'root',
})
export class CourseMaterialCategoriesService {
    courseId: number = -1;
    materialCategories: MaterialCategory[] = [];
    private materialCategoriesBehaviorSubject = new BehaviorSubject(this.materialCategories);
    materialCategoriesObservable = this.materialCategoriesBehaviorSubject.asObservable();

    constructor() {}

    updateMaterialCategories(materialCategories: MaterialCategory[]) {
        this.materialCategoriesBehaviorSubject.next(materialCategories);
    }
}
