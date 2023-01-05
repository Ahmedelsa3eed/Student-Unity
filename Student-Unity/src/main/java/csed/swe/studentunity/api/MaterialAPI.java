package csed.swe.studentunity.api;

import csed.swe.studentunity.logic.MaterialService;
import csed.swe.studentunity.model.Material;
import csed.swe.studentunity.model.MaterialCategory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materials")
public class MaterialAPI {

    private final MaterialService materialService;

    public MaterialAPI(MaterialService materialService) {
        this.materialService = materialService;
    }

    @PostMapping("/admin/addMaterial")
    public ResponseEntity<?> addMaterial(@RequestBody Material material) {
        return new ResponseEntity<>(HttpStatusCode.valueOf(materialService.addMaterial(material)));
    }

    @DeleteMapping("/admin/deleteMaterial")
    public ResponseEntity<?> deleteMaterial(@RequestParam Long materialId) {
        return new ResponseEntity<>(HttpStatusCode.valueOf(materialService.deleteMaterial(materialId)));
    }

    @PutMapping("/admin/editMaterial")
    public ResponseEntity<?> editMaterial(@RequestBody Material material) {
        return new ResponseEntity<>(HttpStatusCode.valueOf(materialService.editMaterial(material)));
    }

    @PostMapping("/admin/addMaterialCategory")
    public ResponseEntity<?> addMaterialCategory(@RequestBody MaterialCategory materialCategory) {
        return new ResponseEntity<>(HttpStatusCode.valueOf(materialService.addMaterialCategory(materialCategory)));
    }

    @DeleteMapping("/admin/deletedMaterialCategory")
    public ResponseEntity<?> deleteMaterialCategory(@RequestParam Long materialCategoryId) {
        return new ResponseEntity<>(HttpStatusCode.valueOf(materialService.deleteMaterialCategory(materialCategoryId)));
    }

    @PutMapping("/admin/editMaterialCategory")
    public ResponseEntity<?> editMaterialCategory(@RequestBody MaterialCategory materialCategory) {
        return new ResponseEntity<>(HttpStatusCode.valueOf(materialService.editMaterialCategory(materialCategory)));
    }

    @GetMapping("/getCourseMaterialCategories")
    public ResponseEntity<List<MaterialCategory>> getCourseMaterialCategories(@RequestParam Long courseId) {
        Object[] result = materialService.getCourseMaterialCategories(courseId);
        return new ResponseEntity<>((List<MaterialCategory>)result[0], HttpStatusCode.valueOf((int)result[1]));
    }

    @GetMapping("/getMaterialCategoryContent")
    public ResponseEntity<List<Material>> getMaterialCategoryContent(@RequestParam Long materialCategoryId) {
        Object[] result = materialService.getMaterialCategoryContent(materialCategoryId);
        return new ResponseEntity<>((List<Material>)result[0], HttpStatusCode.valueOf((int)result[1]));
    }

}
