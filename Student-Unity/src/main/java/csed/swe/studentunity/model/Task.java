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

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "task_id")
    private Long taskId;

    @Column(columnDefinition = "varchar(20)", name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    private Course course;

    @Column(columnDefinition = "Date")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date dueDate;

    @Column(columnDefinition = "varchar(50)", name = "telegram_link")
    private String telegramLink;

    public Task() {}

    public Task(Long taskId) {
        this.taskId = taskId;
    }

    public Task(String title, Date dueDate, String telegramLink) {
        this.title = title;
        this.dueDate = dueDate;
        this.telegramLink = telegramLink;
    }

    public Task(Long taskId, String title, Course course, Date dueDate, String telegramLink) {
        this.taskId = taskId;
        this.title = title;
        this.course = course;
        this.dueDate = dueDate;
        this.telegramLink = telegramLink;
    }

    public Task(String title, Course course, Date dueDate) {
    }
}
