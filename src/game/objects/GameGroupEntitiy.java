package game.objects;

import java.util.ArrayList;

public class GameGroupEntitiy {
	ArrayList<GameCharacter> characterList;
	public static boolean hasPlayer;
	public static int faction;
	
	public GameGroupEntitiy (int _faction, int charNum) {
		faction = _faction;
		for (int i = 0; i < charNum; i++)
			addCharacter(new GameCharacter());
	}
	public void move () {
		
	}
	public void attack () {
		//characters move into range
	}
	public void defend () {
		//characters
	}
	public void retreat () {
		//characters move offscreen
	}
	
	public boolean addCharacter (GameCharacter added) {
		if (characterList.contains(added)) {
			return false;
		} else {
			characterList.add(added);
			return true;
		}
	}
	
	public boolean removeCharacter (GameCharacter removed) {
		if (characterList.contains(removed)) {
			characterList.remove(removed);
			return true;
		} else return false;
	}
}
