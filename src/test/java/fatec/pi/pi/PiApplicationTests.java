package fatec.pi.pi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import fatec.pi.pi.jUnit.Classroom;
import fatec.pi.pi.jUnit.ClassroomServiceTests;

@SpringBootTest
class PiApplicationTests {
	private Classroom lab;
	private Classroom notLab;
	private ClassroomServiceTests classroomService;

	@BeforeEach
	public void setUp() {
		lab = new Classroom("Lab");
		notLab = new Classroom("Sala");
		classroomService = new ClassroomServiceTests();
	}
	@Test
	@DisplayName("A classroom should not be a lab when it's type is not Lab")
	void isLab_ReturnFalse_WhenTypeIsSala() {
		
		Assertions.assertFalse(classroomService.isLab(notLab));
	}

	@Test
	@DisplayName("A classroom should be a lab when it's type is Lab")
	void isLab_ReturnTrue_WhenTypeIsLab() {
	
		Assertions.assertTrue(classroomService.isLab(lab));
	}

}
