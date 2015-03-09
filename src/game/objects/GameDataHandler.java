package game.objects;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameDataHandler {

	ArrayList<String> firstNames;
	ArrayList<String> lastNames;
	
	public GameDataHandler () {
		File firstNamesFile = new File("firstNames.txt");
		File lastNamesFile = new File ("lastNames.txt");
		try {
			Scanner scanner = new Scanner (firstNamesFile);
			while (scanner.hasNext()) {
				firstNames.add(scanner.next());
			}
			scanner.close();
			
			scanner = new Scanner (lastNamesFile);
			while (scanner.hasNext()) {
				lastNames.add(scanner.next());
			}
			scanner.close();
		} catch (FileNotFoundException e) {
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
