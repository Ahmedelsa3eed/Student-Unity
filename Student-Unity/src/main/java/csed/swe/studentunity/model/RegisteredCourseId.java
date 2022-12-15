package csed.swe.studentunity.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class RegisteredCourseId implements Serializable {

    private Long courseId;
    private Long userId;

    public RegisteredCourseId() {}

    public RegisteredCourseId(Long courseId, Long userId) {
        this.courseId = courseId;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisteredCourseId other = (RegisteredCourseId) o;
        return courseId.equals(other.courseId) && userId.equals(other.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, userId);
    }

}
