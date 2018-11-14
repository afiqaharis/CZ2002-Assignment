package group;

public enum GroupType {
	LECTURE("Lecture"), TUTORIAL("Tutorial"), LAB("Lab");
	private String value;
	
	GroupType(final String value) {
		this.value = value;
	}
	
	public String toString() {
        return value;
    }
}	
