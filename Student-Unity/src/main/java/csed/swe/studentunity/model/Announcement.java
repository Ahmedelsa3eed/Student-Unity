package csed.swe.studentunity.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "announcement")
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "announcement_id", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(columnDefinition = "Date")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date postedTime;

    @Column(columnDefinition = "varchar(500)")
    private String body;

    public Announcement

}