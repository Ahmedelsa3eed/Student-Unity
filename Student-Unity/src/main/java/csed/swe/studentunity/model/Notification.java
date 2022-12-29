package csed.swe.studentunity.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Notification")
@Table(name = "notification")

public class Notification {
    @Id
    @Column(name = "user_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    @MapsId
    @JsonBackReference
    private User user;



    @Column(columnDefinition = "VARCHAR(200)", name = "notification_token")
    private String token;




}

