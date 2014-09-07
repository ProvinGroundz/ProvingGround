package main;
import java.util.ArrayList;
import character.Model;

//Party of player controlled Models
public class PlayerParty {
	//ArrayList to store models
	private ArrayList<Model> party;
	int size;
	public PlayerParty() {
		// TODO Auto-generated constructor stub
		party = new ArrayList<Model>();
		size = 0;
	}
	public void addModel(Model m) {
		party.add(m);
		size++;
	}
	public int getSize(){
		return size;
	}
}
