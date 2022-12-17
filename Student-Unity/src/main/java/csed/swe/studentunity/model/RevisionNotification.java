package csed.swe.studentunity.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(name = "RevisionNotification")
public class RevisionNotification {

    @Id
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "revision_notification_token", nullable = false, columnDefinition = "TEXT")
    private String revisionNotificationToken;
}
