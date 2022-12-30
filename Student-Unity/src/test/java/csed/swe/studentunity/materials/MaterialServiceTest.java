package csed.swe.studentunity.materials;

import csed.swe.studentunity.logic.MaterialService;
import csed.swe.studentunity.logic.courses.AllCourseService;
import csed.swe.studentunity.logic.user.ActiveUserService;
import csed.swe.studentunity.logic.user.UserService;
import csed.swe.studentunity.model.Course;
import csed.swe.studentunity.model.Material;
import csed.swe.studentunity.model.MaterialCategory;
import csed.swe.studentunity.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Objects;

@SpringBootTest
public class MaterialServiceTest {

    @Autowired
    private AllCourseService allCourseService;
    @Autowired
    private UserService userService;
    @Autowired
    private MaterialService materialService;

    User adminUser = new User("coursetest12230@test.com", 12130, "fir123st name", "last13 name", "pass3word", "admin");
    Course course = new Course("COURSE_TEX123T_NAME", "COURSE_TE123T_CODE", 2);
    MaterialCategory materialCategory = new MaterialCategory("TEST123_MATERIAL_CATEGORY");
    Material material = new Material("TEST_MA21TERIAL", "TEST_MATERIA23L_URL");

    @Test
    void addMaterialCategoryForCourseExists(){
        userService.addUser(adminUser);
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        activeUserService.login(adminUser.getEmail(), adminUser.getRole(), adminUser.getId());
        allCourseService.addCourse(activeUserService.getSessionIdAsString(adminUser.getEmail()), course);
        materialCategory.setCourseId(course.getId());
        materialService.addMaterialCategory(materialCategory);
        List<MaterialCategory> courseMaterialCategories = (List<MaterialCategory>)(((Object[])materialService.getCourseMaterialCategories(course.getId()))[0]);
        assert (courseMaterialCategories.size() == 1);
        assert (Objects.equals(courseMaterialCategories.get(0).getName(), materialCategory.getName()));
        allCourseService.removeCourse(activeUserService.getSessionIdAsString(adminUser.getEmail()), course.getCode());
        activeUserService.deleteSession(activeUserService.getSessionIdAsString(adminUser.getEmail()));
        userService.deleteUser(adminUser.getId());
    }

    @Test
    void addMaterialCategoryForCourseNotExists(){
        userService.addUser(adminUser);
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        activeUserService.login(adminUser.getEmail(), adminUser.getRole(), adminUser.getId());
        materialService.addMaterialCategory(materialCategory);
        List<MaterialCategory> courseMaterialCategories = (List<MaterialCategory>)(((Object[])materialService.getCourseMaterialCategories(course.getId()))[0]);
        assert (courseMaterialCategories.size() == 0);
        activeUserService.deleteSession(activeUserService.getSessionIdAsString(adminUser.getEmail()));
        userService.deleteUser(adminUser.getId());
    }

    @Test
    void editMaterialCategory(){
        userService.addUser(adminUser);
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        activeUserService.login(adminUser.getEmail(), adminUser.getRole(), adminUser.getId());
        allCourseService.addCourse(activeUserService.getSessionIdAsString(adminUser.getEmail()), course);
        materialCategory.setCourseId(course.getId());
        materialService.addMaterialCategory(materialCategory);
        materialCategory.setName("EDITED");
        materialService.editMaterialCategory(materialCategory);
        List<MaterialCategory> courseMaterialCategories = (List<MaterialCategory>)(((Object[])materialService.getCourseMaterialCategories(course.getId()))[0]);
        assert (courseMaterialCategories.get(0).getName().equals("EDITED"));
        allCourseService.removeCourse(activeUserService.getSessionIdAsString(adminUser.getEmail()), course.getCode());
        activeUserService.deleteSession(activeUserService.getSessionIdAsString(adminUser.getEmail()));
        userService.deleteUser(adminUser.getId());
    }

    @Test
    void deleteMaterialCategory(){
        userService.addUser(adminUser);
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        activeUserService.login(adminUser.getEmail(), adminUser.getRole(), adminUser.getId());
        allCourseService.addCourse(activeUserService.getSessionIdAsString(adminUser.getEmail()), course);
        materialCategory.setCourseId(course.getId());
        materialService.addMaterialCategory(materialCategory);
        materialService.deleteMaterialCategory(materialCategory.getId());
        List<MaterialCategory> courseMaterialCategories = (List<MaterialCategory>)(((Object[])materialService.getCourseMaterialCategories(course.getId()))[0]);
        assert (courseMaterialCategories.size() == 0);
        allCourseService.removeCourse(activeUserService.getSessionIdAsString(adminUser.getEmail()), course.getCode());
        activeUserService.deleteSession(activeUserService.getSessionIdAsString(adminUser.getEmail()));
        userService.deleteUser(adminUser.getId());
    }

    @Test
    void addMaterialForCategoryExists(){
        userService.addUser(adminUser);
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        activeUserService.login(adminUser.getEmail(), adminUser.getRole(), adminUser.getId());
        allCourseService.addCourse(activeUserService.getSessionIdAsString(adminUser.getEmail()), course);
        materialCategory.setCourseId(course.getId());
        materialService.addMaterialCategory(materialCategory);
        material.setMaterialCategoryId(materialCategory.getId());
        materialService.addMaterial(material);
        List<Material> courseMaterial = (List<Material>)(((Object[])materialService.getMaterialCategoryContent(materialCategory.getId()))[0]);
        assert (courseMaterial.size() == 1);
        assert (Objects.equals(courseMaterial.get(0).getTitle(), material.getTitle()));
        allCourseService.removeCourse(activeUserService.getSessionIdAsString(adminUser.getEmail()), course.getCode());
        activeUserService.deleteSession(activeUserService.getSessionIdAsString(adminUser.getEmail()));
        userService.deleteUser(adminUser.getId());
    }

    @Test
    void addMaterialForCategoryNotExists(){
        userService.addUser(adminUser);
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        activeUserService.login(adminUser.getEmail(), adminUser.getRole(), adminUser.getId());
        allCourseService.addCourse(activeUserService.getSessionIdAsString(adminUser.getEmail()), course);
        materialCategory.setCourseId(course.getId());
        material.setMaterialCategoryId(materialCategory.getId());
        materialService.addMaterial(material);
        List<Material> courseMaterial = (List<Material>)(((Object[])materialService.getMaterialCategoryContent(materialCategory.getId()))[0]);
        assert (courseMaterial.size() == 0);
        allCourseService.removeCourse(activeUserService.getSessionIdAsString(adminUser.getEmail()), course.getCode());
        activeUserService.deleteSession(activeUserService.getSessionIdAsString(adminUser.getEmail()));
        userService.deleteUser(adminUser.getId());
    }

    @Test
    void editMaterial(){
        userService.addUser(adminUser);
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        activeUserService.login(adminUser.getEmail(), adminUser.getRole(), adminUser.getId());
        allCourseService.addCourse(activeUserService.getSessionIdAsString(adminUser.getEmail()), course);
        materialCategory.setCourseId(course.getId());
        materialService.addMaterialCategory(materialCategory);
        material.setMaterialCategoryId(materialCategory.getId());
        materialService.addMaterial(material);
        material.setTitle("EDITED");
        materialService.editMaterial(material);
        List<Material> courseMaterial = (List<Material>)(((Object[])materialService.getMaterialCategoryContent(materialCategory.getId()))[0]);
        assert (Objects.equals(courseMaterial.get(0).getTitle(), "EDITED"));
        allCourseService.removeCourse(activeUserService.getSessionIdAsString(adminUser.getEmail()), course.getCode());
        activeUserService.deleteSession(activeUserService.getSessionIdAsString(adminUser.getEmail()));
        userService.deleteUser(adminUser.getId());
    }

    @Test
    void deleteMaterial(){
        userService.addUser(adminUser);
        ActiveUserService activeUserService = ActiveUserService.getInstance();
        activeUserService.login(adminUser.getEmail(), adminUser.getRole(), adminUser.getId());
        allCourseService.addCourse(activeUserService.getSessionIdAsString(adminUser.getEmail()), course);
        materialCategory.setCourseId(course.getId());
        materialService.addMaterialCategory(materialCategory);
        material.setMaterialCategoryId(materialCategory.getId());
        materialService.addMaterial(material);
        materialService.deleteMaterial(material.getId());
        List<Material> courseMaterial = (List<Material>)(((Object[])materialService.getMaterialCategoryContent(materialCategory.getId()))[0]);
        assert (courseMaterial.size() == 0);
        allCourseService.removeCourse(activeUserService.getSessionIdAsString(adminUser.getEmail()), course.getCode());
        activeUserService.deleteSession(activeUserService.getSessionIdAsString(adminUser.getEmail()));
        userService.deleteUser(adminUser.getId());
    }

}
