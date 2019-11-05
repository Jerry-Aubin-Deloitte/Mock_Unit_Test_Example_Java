package Repositories;

import java.util.List;
import Repositories.Interfaces.IStudentRepository;
import Entities.Student;

/**
 * The repository for student entities
 * @author jaubin
 *
 */
public class StudentRepository implements IStudentRepository {
	/**
	 * Retrieves the grades for a given student
	 * @param studentId The student's ID
	 * @return A list of grades
	 */
	public List<Integer> getGrades(String studentId) {
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	/**
	 * Saves a student entity
	 * @param student The student entity to save
	 */
	public void save(Student student) {
		throw new UnsupportedOperationException("Not yet implemented");
	}
}