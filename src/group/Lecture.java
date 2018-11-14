package group;
public class Lecture extends Group {
	public Lecture(int size) {
		super(size);
		this.type = GroupType.LECTURE.toString();
	}
}
