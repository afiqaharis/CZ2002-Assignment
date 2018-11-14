package scrame;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Handles the reading and writing of files that serve as data 
 * @author Joshen Lim, Muhammad Salleh, Ng Jing Rui, Bryan Yeap
 * @version 1.0
 * @since 2018-11-14
 */
public class FileIO {
	
	/**
	 * Fixed path name to the text file
	 */
	private static final String FILE_PATH 	= "data/";
	
	/**
	 * Fixed file type for the data
	 */
	private static final String FILE_TYPE 	= ".txt";
	
	/**
	 * Writes data to the specified file name
	 * @param fileName	Name of the file to be written to
	 * @param data		Data to be written with
	 */
	public static void writeData(String fileName, String[] data) {
		String dataline = "";
		for (int i = 0; i < data.length; i++) {
			dataline += (data[i] + ";"); 
		}
		try {
			FileWriter			fwStream = new FileWriter(FILE_PATH + fileName + FILE_TYPE, true);
			BufferedWriter		bwStream = new BufferedWriter(fwStream);
			PrintWriter			pwStream = new PrintWriter(bwStream);
			pwStream.println(dataline);
			pwStream.close();
		} catch (IOException e) {
			System.out.println("IO Error: " + e.getMessage());
		}
	}
	
	/**
	 * Reads the data from a specified file
	 * @param fileName	Name of the file to be read from
	 * @return			Data from the file, each line in the file is stored as an array of strings.
	 * 					The entire file will be stored as an ArrayList of array of strings.
	 */
	public static ArrayList<String[]> readData(String fileName) {
		ArrayList<String[]> data = new ArrayList<String[]>();	
		String fileLine;
		try {
			FileReader frStream = new FileReader(FILE_PATH + fileName + FILE_TYPE);
			BufferedReader brStream = new BufferedReader(frStream);
			while ((fileLine = brStream.readLine()) != null) {
				String[] inner = fileLine.split(";");
				data.add(inner);
			}
			brStream.close();
		} catch (FileNotFoundException e){
			System.out.println("File Error: " + e.getMessage());
			File newFile = new File(FILE_PATH + fileName + FILE_TYPE);
			try {
				newFile.createNewFile();
			} catch (IOException e1) {
				System.out.println("IO Error: " + e1.getMessage());
			}
		} catch (IOException e) {
			System.out.println("IO Error: " + e.getMessage());
		}
		return data;	
	}
}
