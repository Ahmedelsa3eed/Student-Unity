package csed.swe.studentunity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class VerficationRequest {
    private String email;
    private String code;

}
