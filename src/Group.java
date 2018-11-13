import java.util.ArrayList;
import java.util.Random;

public class Group {
	/*UML:
	Group is an aggregration of Student (Student stored as an attribute and passed as method parameter)
	*/
	/*Attributes of Group Class*/
	private ArrayList<Student> students = new ArrayList<Student>();
	private int size = 10;
	private int groupId;
	private String type;

	// START: Might need to shift this else where
	public enum GroupType {
		LEC("Lecture"), TUT("Tutorial"), LAB("Lab");
		private String value;

		GroupType(final String value) {
			this.value = value;
		}

		public String getValue() {
	        return value;
	    }
	}
	// END
 /*Methods of Group Class*/
	public Group(String type, int size) {
		Random rand = new Random();
		this.groupId = 10000 + rand.nextInt(80000);
		this.size = size;
		this.type = type;
	}

	public int getSize() {
		return this.size;
	}

	public String getType() {
		return this.type;
	}

	public int getGroupId() {
		return this.groupId;
	}

	public ArrayList<Student> getStudents() {
		return this.students;
	}

	public boolean checkVacancy() {
		if (this.size - students.size() > 0) {
			return true;
		}
		System.out.println();
		System.out.println("Error: Group does not have any slots left!");
		System.out.println();
		return false;
	}

	public boolean checkStudentExists(Student student) {
		if (students.indexOf(student) >= 0) {
			System.out.println();
			System.out.println("Error: Student already exists within the group!");
			System.out.println();
			return false;
		}
		return true;
	}

	public void registerStudent(Student student) {
		students.add(student);
		System.out.println();
		System.out.printf("Successfully added %s to %s Group %d\n", student.getName(), this.type, this.groupId);
		System.out.println();
	}

	public void printStudents() {
		if (students.isEmpty()) {
			System.out.println();
			System.out.printf("There are currently no students in %s Group %d\n", this.type, this.groupId);
			System.out.println();
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
			System.out.println();
		}
	}
}
