package Entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.javatuples.Pair;

import Repositories.Interfaces.IStudentRepository;

/**
 * The student entity
 * @author jaubin
 *
 */
public class Student {
	private IStudentRepository studentRepository;
	
	private String id;
	private String surname;
	private String givenName;
	private List<Pair<Boolean, Integer>> grades;
	
	/**
	 * Gets the student's system ID
	 * @return The student's system ID
	 */
	public String getId() { return id; }
	
	/**
	 * Gets the student's last name
	 * @return The student's last name
	 */
	public String getSurname() { return surname; }
	/**
	 * Sets the student's last name
	 * @param _surname The student's last name
	 */
	public void setSurname(String _surname) { surname = _surname; }
	
	/**
	 * Gets the student's first name
	 * @return The student's first name
	 */
	public String getGivenName() { return givenName; }
	/**
	 * Sets the student's first name
	 * @param _givenName The student's first name
	 */
	public void setGivenName(String _givenName) { givenName = _givenName; }
	
	/**
	 * Gets the student's grades
	 * @return The student's grades
	 */
	public List<Integer> getGrades() {
		getGradesFromRepository();
		
		List<Integer> returnGrades = new ArrayList<Integer>();
		for(Pair<Boolean, Integer> grade : grades) {
			returnGrades.add(grade.getValue1());
		}
		return Collections.unmodifiableList(returnGrades);
	}
	
	/**
	 * Gets the student's GPA
	 * @return The student's GPA
	 */
	public double getGradeAverage() {
		getGradesFromRepository();
		
		double sum = 0;
		for(Pair<Boolean, Integer> grade : grades) {
			sum = sum + grade.getValue1();
		}
		return sum/grades.size();
	}
	
	/**
	 * Constructor
	 * @param _studentRepository The repository used for data system interaction
	 * @param _id The student's system ID
	 * @param _surname The student's last name
	 * @param _givenName The student's first name
	 */
	public Student(IStudentRepository _studentRepository, String _id, String _surname, String _givenName) {
		studentRepository = _studentRepository;
		id = _id;
		surname = _surname;
		givenName = _givenName;
	}
	
	/**
	 * Whether or not the student has grades not yet saved to the data system
	 * @return Whether or not unsaved grades are present
	 */
	public boolean hasUnsavedGrades() {
		getGradesFromRepository();
		
		for(Pair<Boolean, Integer> grade : grades) {
			if(grade.getValue0())
				return true;
		}
		
		return false;
	}
	
	/**
	 * Attempts to add a new grade to the student
	 * @param grade The grade to add
	 */
	public void addGrade(int grade) {
		getGradesFromRepository();
		
		if(grade >= 0 && grade <= 100)
			grades.add(new Pair<Boolean, Integer>(true, grade));
	}
	
	/**
	 * Saves all changes made to the data system
	 */
	public void save() {
		studentRepository.save(this);
		
		List<Pair<Boolean, Integer>> tempGrade = new ArrayList<Pair<Boolean, Integer>>();
		
		for(Pair<Boolean, Integer> grade : grades) {
			tempGrade.add(new Pair<Boolean, Integer>(false, grade.getValue1()));
		}
		
		grades = tempGrade;
	}
	
	/**
	 * Gets the student's grades from the data system
	 */
	private void getGradesFromRepository() {
		if (grades == null) {
			List<Integer> gradeResults = studentRepository.getGrades(id);
			
			grades = new ArrayList<Pair<Boolean, Integer>>();
			
			for(Integer grade : gradeResults) {
				grades.add(new Pair<Boolean, Integer>(false, grade));
			}
		}		
	}
}
