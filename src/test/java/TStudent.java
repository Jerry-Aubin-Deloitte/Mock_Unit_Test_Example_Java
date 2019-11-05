import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import org.mockito.*;

import Repositories.Interfaces.IStudentRepository;
import Entities.Student;

public class TStudent {
	private static IStudentRepository studentRepository;
	
	/**
	 * Runs one time before all the tests
	 * @throws Exception Exception occurring while setting up
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		studentRepository = Mockito.mock(IStudentRepository.class);
		Mockito.when(studentRepository.getGrades(Mockito.any(String.class))).thenReturn(getMockGrades());
	}
	
	/**
	 * Runs one time after all tests have been run
	 * @throws Exception Exception occurring while tearing down
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		studentRepository = null;
	}
	
	/**
	 * Makes sure a valid grade is added to the list
	 */
	@Test
	public void student_addGrade_addAValidGrade_returns_oneMoreGradeInList() {
		// 1) Arrange
		Student student = new Student(studentRepository, "ABC123", "Smith", "John");
		int originalNumGrades = student.getGrades().size();
		
		// 2) Act
		student.addGrade(100);
		
		// 3) Assert
		assertEquals(originalNumGrades + 1, student.getGrades().size());
	}
	
	/**
	 * Makes sure an invalid grade is not added to the list
	 */
	@Test
	public void student_addGrade_addAnInvalidGrade_returns_sameNumberOfGrades() {
		// 1) Arrange
		Student student = new Student(studentRepository, "ABC123", "Smith", "John");
		int originalNumGrades = student.getGrades().size();
		
		// 2) Act
		student.addGrade(1000);
		
		// 3) Assert
		assertEquals(originalNumGrades, student.getGrades().size());
	}
	
	/**
	 * Makes sure the entity says there are unsaved grades when a valid grade is added
	 */
	@Test
	public void student_addGrade_addAGrade_returns_hasUnsavedGrades() {
		// 1) Arrange
		Student student = new Student(studentRepository, "ABC123", "Smith", "John");
		
		// 2) Act
		student.addGrade(100);
		
		// 3) Assert
		assertTrue(student.hasUnsavedGrades());
	}
	
	/**
	 * Makes sure the entity says there are no unsaved grades when the entity is saved
	 */
	@Test
	public void student_save_saveStudent_returns_noUnsavedGrades() {
		// 1) Arrange
		Student student = new Student(studentRepository, "ABC123", "Smith", "John");
		student.addGrade(100);
		
		// 2) Act
		student.save();
		
		// 3) Assert
		assertFalse(student.hasUnsavedGrades());
	}
	
	private static List<Integer> getMockGrades() {
		List<Integer> mockList = new ArrayList<Integer>();
		mockList.add(100);
		mockList.add(0);
		
		return mockList;
	}
}
