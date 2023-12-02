package fatec.pi.pi.jUnit;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ClassroomServiceTest01 {
    public static void main(String[] args){
        Classroom classroom = new Classroom("Lab");
        ClassroomServiceTests classroomService = new ClassroomServiceTests();
        log.info("Is Lab? '{}'", classroomService.isLab(classroom));
    }
}
