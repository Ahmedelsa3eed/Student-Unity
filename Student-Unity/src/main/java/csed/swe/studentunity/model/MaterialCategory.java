package csed.swe.studentunity.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity(name = "MaterialCategory")
@Table(name = "material_category", uniqueConstraints = {
        @UniqueConstraint(name = "unique_material_category_name", columnNames = {"course_id", "material_category_name"})
})
public class MaterialCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "material_category_id", nullable = false, columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "course_id", nullable = false, columnDefinition = "BIGINT")
    private Long courseId;

    @Column(name = "material_category_name", nullable = false, columnDefinition = "VARCHAR(100)")
    private String name;

    public MaterialCategory() {}

    public MaterialCategory(Long id, Long courseId, String name) {
        this.id = id;
        this.courseId = courseId;
        this.name = name;
    }

    public MaterialCategory(String name) {
        this.name = name;
    }

}

// alter table material_category add foreign key(course_id) references course (course_id) on delete cascade