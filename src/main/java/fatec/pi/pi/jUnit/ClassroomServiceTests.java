package fatec.pi.pi.jUnit;
import java.util.Objects;


public class ClassroomServiceTests {
    public boolean isLab(Classroom classroom) {
        Objects.requireNonNull(classroom, "Classroom can't be null");
        return classroom.getType() == "Lab";
    }
}
