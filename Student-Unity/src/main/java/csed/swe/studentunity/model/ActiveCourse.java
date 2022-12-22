package csed.swe.studentunity.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ActiveCourse")
@Table(name = "active_course")
public class ActiveCourse implements Serializable {

    @Id
    @Column(name = "active_id")
    private Long id;

    @JsonBackReference
    @OneToOne
    @MapsId
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "time_table", columnDefinition = "TEXT")
    private String timeTable;

    @Column(name = "telegram_link", columnDefinition = "TEXT")
    private String telegramLink;

    @Column(name = "notifications_token")
    private String notificationsToken;

    @Override
    public String toString() {
        return "ActiveCourse{" +
                "timeTable='" + timeTable + '\'' +
                ", telegramLink='" + telegramLink + '\'' +
                ", notificationsToken='" + notificationsToken + '\'' +
                '}';
    }
}
