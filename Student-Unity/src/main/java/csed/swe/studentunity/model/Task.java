package csed.swe.studentunity.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.net.URL;
import java.util.Date;

@Getter
@Setter
@Entity
public class Task implements Serializable {

    @Id @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @Column(name = "task_id")
    private Long taskId;

    @Column(columnDefinition = "varchar(20)")
    private String title;

//    @ManyToOne(cascade = CascadeType.ALL)
    private String courseCode;

    @Column(columnDefinition = "Date")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date dueDate;

    @Column(columnDefinition = "varchar(50)")
    private URL telegramLink;

    public Task() {}
}
