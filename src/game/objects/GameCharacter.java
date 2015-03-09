package game.objects;

import java.awt.Dimension;

public class GameCharacter extends GameObject {
	//stats;
	//relations;
	//equipment;
	//group;
	
	public GameCharacter (String _firstName, String _lastName) {
		this.name = _firstName + " " + _lastName;
	}
	
	public void move (Dimension where) {}
	public void attack (Character attacked) {}

	public boolean addItem (GameItem added) { return true;}
	public boolean removeItem (GameItem removed) { return true;}
	public boolean useItem (GameItem used) {return true;}


}
