package csed.swe.studentunity.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class RegisteredCourseId implements Serializable {

    private Course course;
    private User user;

    public RegisteredCourseId() {}

    public RegisteredCourseId(Course course, User user) {
        this.course = course;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisteredCourseId other = (RegisteredCourseId) o;
        return course.equals(other.course) && user.equals(other.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, user);
    }

}
