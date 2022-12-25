package csed.swe.studentunity.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "announcement")
public class Announcement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "announcement_id", nullable = false)
    private UUID id;

    // alter table announcement add foreign key(course_id) references course (course_id) on delete cascade
    @JoinColumn(name = "course_id")
    private long courseId;

    @Column(columnDefinition = "Date")
    @JsonFormat(pattern="YYYY-MM-DD")
    private Date postedDate;

    @Column(columnDefinition = "varchar(500)")
    private String body;

    public Announcement() {}

    public Announcement(long courseId, Date postedDate, String body) {
        this.courseId = courseId;
        this.postedDate = postedDate;
        this.body = body;
    }

}