package csed.swe.studentunity.logic;

import csed.swe.studentunity.dao.MaterialCategoryRepo;
import csed.swe.studentunity.dao.MaterialRepo;
import csed.swe.studentunity.model.Material;
import csed.swe.studentunity.model.MaterialCategory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MaterialService {

    MaterialRepo materialRepo;
    MaterialCategoryRepo materialCategoryRepo;

    public MaterialService(MaterialRepo materialRepo, MaterialCategoryRepo materialCategoryRepo) {
        this.materialRepo = materialRepo;
        this.materialCategoryRepo = materialCategoryRepo;
    }

    public int addMaterial(Material material) {
        try {
            materialRepo.save(material);
            return 200;
        } catch (Exception ignored) {}
        return 404;
    }

    public int deleteMaterial(Long materialId) {
        materialRepo.deleteById(materialId);
        return 200;
    }

    public int editMaterial(Material material) {
        Optional<Material> record = materialRepo.findById(material.getId());
        if (record.isPresent()) {
            Material materialToEdit = record.get();
            materialToEdit.setTitle(material.getTitle());
            materialToEdit.setUrl(material.getUrl());
            materialToEdit.setMaterialCategoryId(material.getMaterialCategoryId());
            try {
                materialRepo.save(materialToEdit);
                return 200;
            } catch (Exception ignored) {}
        }
        return 404;
    }

    public int addMaterialCategory(MaterialCategory materialCategory) {
        try {
            materialCategoryRepo.save(materialCategory);
            return 200;
        } catch (Exception ignored) {}
        return 404;
    }

    public int deleteMaterialCategory(Long materialCategoryId) {
        materialCategoryRepo.deleteById(materialCategoryId);
        return 200;
    }

    public int editMaterialCategory(MaterialCategory materialCategory) {
        Optional<MaterialCategory> record = materialCategoryRepo.findById(materialCategory.getId());
        if (record.isPresent()) {
            MaterialCategory materialCategoryToEdit = record.get();
            materialCategoryToEdit.setName(materialCategory.getName());
            try {
                materialCategoryRepo.save(materialCategoryToEdit);
                return 200;
            } catch (Exception ignored) {}
        }
        return 404;
    }

    public Object[] getCourseMaterialCategories(Long courseId) {
        return new Object[]{materialCategoryRepo.getCourseMaterialCategories(courseId), 200};
    }

    public Object[] getMaterialCategoryContent(Long materialCategoryId) {
        return new Object[]{materialRepo.getMaterialCategoryContent(materialCategoryId), 200};
    }

}
