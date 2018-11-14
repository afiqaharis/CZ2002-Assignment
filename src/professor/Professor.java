package professor;
import java.util.Calendar;
import java.util.Random;

/**
 * Represents a professor employed in the school.
 * Can be assigned to a course as a course coordinator
 * @author Joshen Lim, Muhammad Salleh, Ng Jing Rui, Bryan Yeap
 * @version 1.0
 * @since 2018-11-14
 */
public class Professor {
	
	/*
	 * ID of this professor
	 */
	private String id;
	
	/**
	 * Name of this professor
	 */
	private String name;
	
	/**
	 * Email of this professor
	 */
	private String email;
	
	/**
	 * Generates an identification number for newly added professors
	 * @return	A randomly generated identification number for this professor
	 */
	private String generateProfID() {
		Calendar dateNow = Calendar.getInstance();
		Random random = new Random();
		
		String frontNum = String.valueOf(dateNow.get(Calendar.YEAR)).substring(2);
		int endNum = random.nextInt(99999);
		char endAlphabet = (char)(random.nextInt(26) + 'A');
		
		return String.format("P%s%d%s", frontNum, endNum, endAlphabet);
	}
	
	/**
	 * Creates a new professor with the specified name and email
	 * @param name		Name of this professor
	 * @param email		Email of this professor
	 */
	public Professor(String name, String email) {
		this.id = this.generateProfID();
		this.name = name;
		this.email = email;
	};
	
	/**
	 * Creates an existing professor with the specified name, ID and email
	 * @param name		Name of this professor
	 * @param id		ID of this professor
	 * @param email		Email of this professor
	 */
	public Professor(String name, String id, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	};
	
	/**
	 * Get the name of this professor
	 * @return	The name of this professor
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Get the ID of this professor
	 * @return	The ID of this professor
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * Get the email of this professor
	 * @return	The email of this professor
	 */
	public String getEmail() {
		return this.email;
	}
}
