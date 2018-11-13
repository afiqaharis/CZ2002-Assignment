import java.util.Calendar;
import java.util.Random;

public class Professor {
	//Prfessor class
	private String id;
	private String name;
	private String email;

	private String generateProfID() {
		Calendar dateNow = Calendar.getInstance();
		Random random = new Random();

		String frontNum = String.valueOf(dateNow.get(Calendar.YEAR)).substring(2);
		int endNum = random.nextInt(99999);
		char endAlphabet = (char)(random.nextInt(26) + 'A');

		return String.format("P%s%d%s", frontNum, endNum, endAlphabet);
	}

	public Professor(String name, String email) {
		this.id = this.generateProfID();
		this.name = name;
		this.email = email;
	};

	public Professor(String name, String id, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	};

	public String getName() {
		return this.name;
	}

	public String getId() {
		return this.id;
	}

	public String getEmail() {
		return this.email;
	}
}
