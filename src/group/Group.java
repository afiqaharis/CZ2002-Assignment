package group;
import java.util.ArrayList;
import java.util.Random;

import scrame.Utility;
import student.Student;

/**
 * Represents the lesson group of a course. Will be broken down into 3 
 * subclasses: Lecture, Tutorial and Lab. A course can have multiple groups.
 * @author Joshen Lim, Muhammad Salleh, Ng Jing Rui, Bryan Yeap
 * @version 1.0
 * @since 2018-11-14
 */

public class Group {
	
	/**
	 * List of students registered to this group
	 */
	private ArrayList<Student> students = new ArrayList<Student>();
	
	/**
	 * Size of this group, in terms of number of students
	 */
	private int size;
	
	/**
	 * ID of this group
	 */
	private int groupId;
	
	/**
	 * Type of this group
	 */
	protected String type;	
	
	/**
	 * Creates a new group with the specified size
	 * @param size		Maximum number of students which the group can have
	 */
	public Group(int size) {
		Random rand = new Random();
		this.groupId = 10000 + rand.nextInt(80000);
		this.size = size;
	}
	
	/**
	 * Get the size of this group
	 * @return		The size of this group
	 */
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Get the type of this group
	 * @return		The type of this group
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * Get the identification number of this group
	 * @return		The identification number of this group
	 */
	public int getGroupId() {
		return this.groupId;
	}
	
	/**
	 * Get the list of students registered within this group
	 * @return		The list of students registered within this group
	 */
	public ArrayList<Student> getStudents() {
		return this.students;
	}
	
	/**
	 * Checks if this group still has vacancy
	 * @return		True if vacancy of the group is more than 0
	 */
	public boolean checkVacancy() {
		if (this.size - students.size() > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Check if the specified student is registered within this group
	 * @param student	Student to be checked for
	 * @return			True if the student is registered within this group
	 */
	public boolean checkStudentExists(Student student) {
		if (students.indexOf(student) >= 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Register the specified student into this group
	 * @param student		The student to be registered into this group
	 */
	public void registerStudent(Student student) {
		students.add(student);
		String successMsg = String.format("Added %s to %s Group %d", student.getName(), this.type, this.groupId);
		Utility.printSuccessMessage(successMsg);
	}
	
	/**
	 * Print the list of students registered within this group
	 */
	public void printStudents() {
		if (students.isEmpty()) {			
			String noticeMsg = String.format("There are currently no students in %s Group %d", this.type, this.groupId);	
			Utility.printNoticeMessage(noticeMsg);
		} else {
			System.out.println();
			System.out.println("===================================================");
			System.out.printf("| %-8s Group: %-5d                           |\n", this.type, this.groupId);
			System.out.println("===================================================");
			System.out.println("| No | Name                | Matriculation Number |");
			System.out.println("===================================================");
			for (Student student: students) {
	    		System.out.printf("| %-3d| %-20s| %-21s|\n", students.indexOf(student) + 1, 
	    				student.getName(), student.getMatricNumber(), student.getEmail());
	    	}
			System.out.println("===================================================");
		}
	}
}
