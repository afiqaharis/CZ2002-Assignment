import java.util.ArrayList;
import java.util.Random;

public class Group {
	private ArrayList<Student> students = new ArrayList<Student>();
	private int size = 10;
	private int groupId;
	private String type;	
	
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
		Utility.printErrorMessage("Group does not have any slots left!");
		return false;
	}
	
	public boolean checkStudentExists(Student student) {
		if (students.indexOf(student) >= 0) {
			Utility.printErrorMessage("Student already exists within the group.");
			return false;
		}
		return true;
	}
	
	public void registerStudent(Student student) {
		students.add(student);
		String successMsg = String.format("Added %s to %s Group %d\n", student.getName(), this.type, this.groupId);
		Utility.printSuccessMessage(successMsg);
	}
	
	public void printStudents() {
		if (students.isEmpty()) {			
			String noticeMsg = String.format("There are currently no students in %s Group %d\n", this.type, this.groupId);	
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
