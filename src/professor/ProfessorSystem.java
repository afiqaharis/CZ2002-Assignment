package professor;
import java.util.ArrayList;

public class ProfessorSystem {
	private ArrayList<Professor> professors = new ArrayList<Professor>();
	
	public ProfessorSystem(ArrayList<String[]> data) {
		parseData(data);
	}
	
	private void parseData(ArrayList<String[]> data) {
		for (String[] profData:data) {
			String profName 	= profData[0];
			String profId 		= profData[1];
			String profEmail 	= profData[2];
			Professor professor = new Professor(profName, profId, profEmail);
			professors.add(professor);
		}
	}
	
	public ArrayList<Professor> getProfessors() {
		return this.professors;
	}
}
