
public enum GroupType {
	LEC("Lecture"), TUT("Tutorial"), LAB("Lab");
	private String value;
	
	GroupType(final String value) {
		this.value = value;
	}
	
	public String toString() {
        return value;
    }
}	
