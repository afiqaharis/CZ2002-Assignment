import java.util.ArrayList;

public class Assessment {
	private int weightage;
	private String type;
	
	private ArrayList<Assessment> subComponents = new ArrayList<Assessment>();
	
	public Assessment(String type, int weightage) {
		this.type = type;
		this.weightage = weightage;
	}
	
	public String getType() {
		return this.type;
	}
	
	public int getWeightage() {
		return this.weightage;
	}
	
	public void setWeightage(int weightage) {
		this.weightage = weightage;
	}
	
	public ArrayList<Assessment> getSubComponents() {
		return this.subComponents;
	}
	
	
	public void addSubComponent(Assessment newComponent) {
		if (!this.type.equals("Coursework")) {
			System.out.println("Internal Error: Only can add sub components to Coursework Assessment");
		} else {
			subComponents.add(newComponent);
		}
	}

}
