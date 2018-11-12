import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		School school = new School("SCSE");
		
		// User input variables:
		Course selectedCourse;
		Student selectedStudent;
		int option1, option2;
		
		// TO-DO:
		// Error handling for user input values: Application should never crash!!
		// int type - throw error
		
		do {
			System.out.println("=====================================================");
			System.out.println("| Student Course Registration and Mark Entry System |");
			System.out.println("=====================================================");
			System.out.println("| 1 |  View records                                 |");
			System.out.println("| 2 |  Add records                                  |");
			System.out.println("| 3 |  Register Student to Course                   |");
			System.out.println("| 4 |  Course Assessment Settings                   |");
			System.out.println("| 5 |  Mark Entry For Student                       |");
			System.out.println("| 6 |  Print Student Transcript                     |");
			System.out.println("=====================================================");
			System.out.println("Select an option from the above list:");
			option1 = sc.nextInt();
			sc.nextLine();
			
			switch(option1) {
				case 1:
					do {
						System.out.println("=====================================================");
						System.out.println("| Select which records to view                      |");
						System.out.println("=====================================================");
						System.out.println("| 1 |  View All Students                            |");
						System.out.println("| 2 |  View All Courses                             |");
						System.out.println("| 3 |  View All Professors                          |");
						System.out.println("| 4 |  View Student List By Course                  |");
						System.out.println("| 5 |  View Course Availability                     |");
						System.out.println("| 6 |  View Course Assessments                      |");
						System.out.println("| 7 |  View Course Statistics                       |");
						System.out.println("| 0 |  Back                                         |");
						System.out.println("=====================================================");
						option2 = sc.nextInt();
						sc.nextLine();
						
						switch(option2) {
							case 1:
								school.printStudents();
								break;
							case 2:
								school.printCourses();
								break;
							case 3:
								school.printProfessors();
								break;
							case 4:
								selectedCourse = school.getCourse();
								selectedCourse.printStudents();
								break;
							case 5:
								selectedCourse = school.getCourse();
								selectedCourse.printAvailability();
								
								break;
							case 6:
								selectedCourse = school.getCourse();
								selectedCourse.printWeightages();
								break;
							case 7:
								selectedCourse = school.getCourse();
								selectedCourse.printStatistics();
								break;
							case 0:
								break;
							default:
								System.out.println();
								System.out.println("Error: Please choose from the options in the list.");
								System.out.println();
								break;
						}
					} while (option2 > 0);
					break;
				case 2:
					do {
						System.out.println("=====================================================");
						System.out.println("| Select which records to add                       |");
						System.out.println("=====================================================");
						System.out.println("| 1 |  Add New Student                              |");
						System.out.println("| 2 |  Add New Course                               |");
						System.out.println("| 3 |  Add New Professor                            |");
						System.out.println("| 0 |  Back                                         |");
						System.out.println("=====================================================");
						option2 = sc.nextInt();
						sc.nextLine();
						
						switch(option2) {
							case 1:
								school.addStudent();
								break;
							case 2:
								school.addCourse();
								break;
							case 3:
								school.addProfessor();
								break;
							case 0:
								break;
							default:
								System.out.println();
								System.out.println("Error: Please choose from the options in the list.");
								System.out.println();
								break;
						}
					} while (option2 > 0);
					break;
				case 3:
					school.registerStudentToCourse();
					break;
				case 4:
					selectedCourse = school.getCourse();
					do {
						System.out.println();
						System.out.println("==========================================================");
						System.out.printf("| Select which setting you\'d like to change under %-6s |\n", selectedCourse.getCode());
						System.out.println("==========================================================");
						System.out.println("| 1 |  Change Assessment Weightages                      |");
						System.out.println("| 2 |  Add Subcomponent to Coursework Assessment         |");
						System.out.println("| 0 |  Back                                              |");
						System.out.println("==========================================================");
						option2 = sc.nextInt();
						sc.nextLine();
						
						switch(option2) {
							case 1:
								selectedCourse.updateAssessmentWeightage();
								break;
							case 2:
								selectedCourse.addSubComponent();
								break;
							case 0:
								break;
							default:
								System.out.println();
								System.out.println("Error: Please choose from the options in the list.");
								System.out.println();
								break;
						}
					} while (option2 > 0);
					break;
				case 5:
					selectedStudent = school.getStudent();
					MarkEntry.enterStudentMarks(sc, selectedStudent);
					break;
				case 6:
					selectedStudent = school.getStudent();
					selectedStudent.printTranscript(school);
					break;
				default:
					System.out.println();
					System.out.println("Error: Please choose from the options in the list.");
					System.out.println();
					break;
			}
		} while (option1 >= 0);
		
		System.out.println("Closing Program...");
		sc.close();
	}
}
