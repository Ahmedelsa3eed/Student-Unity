package csed.swe.studentunity.logic.courses;


import csed.swe.studentunity.dao.ActiveCourseRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ActiveCourseService {

    @Autowired
    private final ActiveCourseRepo activeCourseRepo;

    public ActiveCourseService(ActiveCourseRepo activeCourseRepo) {
        this.activeCourseRepo = activeCourseRepo;
    }

    public void deleteActiveCourse(Long id){
        activeCourseRepo.deleteById(id);
    }

}
