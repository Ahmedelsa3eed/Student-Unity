package csed.swe.studentunity.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity(name = "Material")
@Table(name = "material")
public class Material implements Serializable {

    public Material() {}

    public Material(String title, String url, MaterialCategory materialCategory) {
        this.title = title;
        this.url = url;
        this.materialCategory = materialCategory;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "material_id", nullable = false, columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "material_title", nullable = false, columnDefinition = "VARCHAR(100)")
    private String title;

    @Column(name = "material_url", nullable = false, columnDefinition = "VARCHAR(256)")
    private String url;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "material_category_id", nullable = false)
    private MaterialCategory materialCategory;

}