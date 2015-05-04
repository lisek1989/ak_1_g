package game.objects;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameDataHandler {

	ArrayList<String> firstNames;
	ArrayList<String> lastNames;
	
	private String firstNameFileName = "firstNames.txt";
	private String firstNameFileType = ".txt";
	
	private String lastNameFileName = "lastNames.txt";
	private String lastNameFileType = ".txt";
	
	public GameDataHandler () {
		File firstNamesFile;
		firstNames = new ArrayList<String>();
		
		File lastNamesFile;
		lastNames = new ArrayList<String>();
		
		
		try {
			firstNamesFile = new File(getClass().getClassLoader().getResource(firstNameFileName).toURI());
			lastNamesFile = new File (getClass().getClassLoader().getResource(lastNameFileName).toURI());

			Scanner scanner = new Scanner (firstNamesFile);
			String tmp;
			while (scanner.hasNext()) {
				firstNames.add(scanner.next());
			}
			scanner.close();
			
			scanner = new Scanner (lastNamesFile);
			while (scanner.hasNextLine()) {
				lastNames.add(scanner.next());
			}
			scanner.close();
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	public String getRandomFirstName () {
		return firstNames.get((int)(Math.random() * (firstNames.size() - 1)));
	}
	
	public String getRandomLastName () {
		return lastNames.get((int)(Math.random() * (lastNames.size() - 1)));
	}
}
