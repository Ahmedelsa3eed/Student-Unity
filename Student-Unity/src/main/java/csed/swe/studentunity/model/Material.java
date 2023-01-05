package csed.swe.studentunity.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity(name = "Material")
@Table(name = "material", uniqueConstraints = {
        @UniqueConstraint(name = "unique_material_title", columnNames = {"material_title", "material_category_id"})
})
public class Material implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "material_id", nullable = false, columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "material_title", nullable = false, columnDefinition = "VARCHAR(100)")
    private String title;

    @Column(name = "material_url", nullable = false, columnDefinition = "VARCHAR(256)")
    private String url;

    @Column(name = "material_category_id", nullable = false, columnDefinition = "BIGINT")
    private Long materialCategoryId;

    public Material() {}

    public Material(Long id, String title, String url, Long materialCategoryId) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.materialCategoryId = materialCategoryId;
    }

    public Material(String title, String url) {
        this.title = title;
        this.url = url;
    }

}

// alter table material add foreign key(material_category_id) references material_category (material_category_id) on delete cascade