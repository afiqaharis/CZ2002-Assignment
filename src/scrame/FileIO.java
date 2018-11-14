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

public class FileIO {
	private static final String FILE_PATH 	= "data/";
	private static final String FILE_TYPE 	= ".txt";
	
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
