package Repositories.Interfaces;

import java.util.List;
import Entities.Student;

/**
 * Interface for the student repository
 * @author jaubin
 * @return 
 *
 */
public interface IStudentRepository {
	/**
	 * Retrieves the grades for a given student
	 * @param studentId The student's ID
	 * @return A list of grades
	 */
	List<Integer> getGrades(String studentId);
	
	/**
	 * Saves a student entity
	 * @param student The student entity to save
	 */
	void save(Student student);
}
