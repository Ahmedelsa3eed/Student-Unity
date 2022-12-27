import { MaterialCategory } from './MaterialCategory';

export class Material {
    id: number;
    title: string;
    url: string;
    materialCategoryId: number;

    constructor() {
        this.id = -1;
        this.title = '';
        this.url = '';
        this.materialCategoryId = -1;
    }
}
