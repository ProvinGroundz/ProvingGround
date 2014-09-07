/*
 * 
 * "State machine" class containing the character creation steps.
 * 
 * Author: Josh Blitz
 * 
 */

package main;

import java.util.ArrayList;
import java.util.regex.Pattern;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import character.*;

class CharCreationFSM
{
	//constants
	private final int NUMROLLS=50;
	private final int SEC_BASE = 3;
	// Dice info for rolling stats
	private final int BASE_NUM_DICE = 3;
	private final int BASE_NUM_SIDES = 3;
	private final int BASE_ROLL_MOD = 2;
	private final int SEC_NUM_DICE = 3;
	private final int SEC_NUM_SIDES = 3;
	private final int SEC_ROLL_MOD = -1;
	// validChoices but contains full words instead of letters
	static ArrayList<String> fullOptions = new ArrayList<String>();

	// used for direct user input states
	TextField txtField;
	
	//can be updated independent of state
	private VBox inputLayout;
	private Label inputLabel;
	
	// number of rolls allowed for states 9 and 10
	private int numRolls9 = NUMROLLS;
	private int prevNumRolls9;
	private int numRolls10 = NUMROLLS;
	//private int prevNumRolls10 = numRolls10;
	
	
	static protected int strength,dexterity,twitch,constitution,intelligence,wisdom,commonSense,spirituality,charisma,luck;
	//other attributes
	static protected String name, race, gender, alignment, profession, charClass; 
	static protected int age, status, level, xp, rank, gold;
	static protected int mHit,mMystic, mSkill, mPrayer, mBard;
	static protected int magicResist, commerce, rapport, recovery;
	//armor class b=base, c=current
	static protected int bArmorClass;
	//number attacks per round
	static protected int bNAT; 
	//resurrection modifier
	static protected double resModifier;
	// set up event handlers for user input sections
	private EventHandler<KeyEvent> keyEventAge = new EventHandler<KeyEvent>() {
		public void handle(KeyEvent ke) {
//			System.out.println(ke.getCode()+" state7");
			if (ke.getCode().equals(KeyCode.ENTER)) {
				boolean b = Pattern.matches("[0-9]{2}", txtField.getText());
				if (b) {
					int tempAge = Integer.parseInt(txtField.getText());
					if (tempAge >= 17 && tempAge <= 88) {
						inputLayout.setVisible(false);
						age = tempAge;
						checkState(Game.state=8);
					}
				} else {
				}

			}
			if (ke.getCode().equals(KeyCode.ESCAPE)) {
				inputLayout.setVisible(false);
				Game.textDescr.setVisible(true);
				checkState(Game.state=6);
				return;
			}
		}
	};
	private EventHandler<KeyEvent> keyEventName = new EventHandler<KeyEvent>() {
		public void handle(KeyEvent ke) {
//			System.out.println(ke.getCode()+" state8");
			if (ke.getCode().equals(KeyCode.ENTER)) {
				// System.out.println("working!!");
				// regex
				boolean b = Pattern.matches("[1-9a-zA-Z]{1,14}",
						txtField.getText());
				if (b) {
					name = txtField.getText();
					state9();
				}
			}
			if (ke.getCode().equals(KeyCode.ESCAPE)) {
				Game.state = 7;
				inputLabel.setText("Enter age: ");
				txtField.clear();txtField.requestFocus();
				txtField.setOnKeyReleased(keyEventAge);
				return;
			}
		}
	};
	
	
	/*
	 * Character creation reads JSON files from ./data directory -- 
	 * NOT hard-coded
	 */
	/* choose race */
	public void begin() {
		Game.state = 2;
		

		/* iterate through validChoices to check if any equals userInput */
		for (int i = 0; i < Game.validChoices.size(); i++) {
			if (Game.userInput.equals(Game.validChoices.get(i))) {
				// System.out.println(fullOptions.get(i));
				race = fullOptions.get(i);
				clear();checkState(Game.state = 3);
				return;
			} else if (Game.userInput.equals("escape")) {
				clear();Game.mainFSM.checkState(Game.state=1);
				return;
			}
		}
		new JSONReader().readJSONArray();
	}

	/* choose gender */
	private void state3() {
		Game.state = 3;

		/* iterate through validChoices to check if any equals userInput */
		for (int i = 0; i < Game.validChoices.size(); i++) {
			if (Game.userInput.equals(Game.validChoices.get(i))) {
				gender = fullOptions.get(i);
				clear();checkState(Game.state=4);
				return;
			} else if (Game.userInput.equals("escape")) {
				clear();
				checkState(Game.state=2);
				return;
			}
		}
		new JSONReader().readJSONArray();
	}

	/* choose class */
	private void state4() {
		Game.state = 4;

		/* iterate through validChoices to check if any equals userInput */
		for (int i = 0; i < Game.validChoices.size(); i++) {
			if (Game.userInput.equals(Game.validChoices.get(i))) {
				// System.out.println(fullOptions.get(i));
				charClass = fullOptions.get(i);
				clear();checkState(Game.state=5); 
				return;
			} else if (Game.userInput.equals("escape")) {
				clear();checkState(Game.state=3); 
				return;
			}
		}
		new JSONReader().readJSONArray();

	}

	/* choose profession */
	private void state5() {
		Game.state = 5;

		/* iterate through validChoices to check if any equals userInput */
		for (int i = 0; i < Game.validChoices.size(); i++) {
			if (Game.userInput.equals(Game.validChoices.get(i))) {
				profession = fullOptions.get(i);
				clear();checkState(Game.state=6);
				return;
			} else if (Game.userInput.equals("escape")) {
				clear();checkState(Game.state=4);
				return;
			}
		}
		new JSONReader().readJSONObject();
	}

	/* choose alignment */
	private void state6() {
		Game.state = 6;


		/* iterate through validChoices to check if any equals userInput */
		for (int i = 0; i < Game.validChoices.size(); i++) {
			if (Game.userInput.equals(Game.validChoices.get(i))) {
				// System.out.println(fullOptions.get(i));
				alignment = fullOptions.get(i);
				clear();checkState(Game.state=7);
				return;
			} else if (Game.userInput.equals("escape")) {
				clear();checkState(Game.state=5);
				return;
			}
		}
		new JSONReader().readJSONArray();
	}

	/* input age */
	private void state7() {
		Game.state = 7;
		initLayout();

		if(Game.state==8)txtField.setOnKeyReleased(keyEventName);
		else if(Game.state==7)txtField.setOnKeyReleased(keyEventAge);
	}

	/* input name */
	private void state8() {
		Game.state = 8;
		initLayout();
		

		if(Game.state==8)txtField.setOnKeyReleased(keyEventName);
		else if(Game.state==7)txtField.setOnKeyReleased(keyEventAge);
	}

	/* reroll state where base stats are chosen */
	private void state9() {
		Game.state = 9;
		inputLayout.setVisible(false);
		Game.textDescr.setVisible(true);

		// Timeline object that runs on UI thread allowing timed events to occur
		// remove for now
		/*
		 * Timeline ellipsis = new Timeline(new KeyFrame(Duration.seconds(1),new
		 * EventHandler<ActionEvent>() {
		 * 
		 * @Override public void handle(ActionEvent event) {
		 * //System.out.println("this is called every 1 seconds on UI thread");
		 * Game.textDescr.appendText(" ."); }
		 * }));ellipsis.setCycleCount(3);ellipsis.playFromStart();
		 */
		Game.validChoices.add("k");
		Game.validChoices.add("r");
		Game.validChoices.add("escape");
		// stats set OR reroll OR exit
		switch (Game.userInput) {
		case "k":
			clear();
			prevNumRolls9 = numRolls9;
			checkState(Game.state=10);
			return;
		case "r":
			if(numRolls9==0)return;
			clear();numRolls9--;checkState();
			return;
		case "escape":
			Game.state = 8;
			Game.textDescr.setVisible(false);
			inputLayout.setVisible(true);
			clear();txtField.clear();txtField.requestFocus();numRolls9=NUMROLLS;
			return;
		default:
			break;
		}
		
		if ((prevNumRolls9!=numRolls9) && numRolls9 > 0) {
			rollBaseStats(BASE_NUM_DICE, BASE_NUM_SIDES, BASE_ROLL_MOD); 
			applyBaseBonuses();
		}
		Game.textDescr.setText("Ah.. yer Base Stats shall be. . .");
		String output = String
				.format("\n\n# of rolls left:%3s\n\n%-12s%-10s%-10s\n%-12s%-10s%-10s\n"
						+ "%-12s%-10s%-10s\n%-12s%-10s%-10s\n%-12s%-10s\n\n(K)eep\n(R)eroll\n\n(Esc)ape",
						numRolls9, "Physical", "Mental", "Ineffable",
						"ST " + strength, "IN " + intelligence,
						"SP " + spirituality, "TW " + twitch,
						"WI " + wisdom, "CH " + charisma, "DX "
						+ dexterity, "CS " + commonSense,
						"LK " + luck, "CN " + constitution, "AVG "+ meanBaseStats());
		Game.textDescr.appendText(output);
	}
	
	// secondary stats determined
	private void state10() {
		Game.state = 10;
		Game.validChoices.add("k");
		Game.validChoices.add("r");
		Game.validChoices.add("escape");
		switch (Game.userInput) {
		case "k":
			//prevNumRolls10 = numRolls10;
			clear();checkState(Game.state=11);
			return;
		case "r":
			if(numRolls10==0)return;
			clear();numRolls10--;checkState();
			return;
		case "escape":
			clear();numRolls10=NUMROLLS;
			checkState(Game.state=9);
			return;
		default:
			break;
		}
		Game.textDescr.setText("..and ye shall begin with these. . .");
		bArmorClass = 12;
		rollSecondaryStats(SEC_NUM_DICE, SEC_NUM_SIDES, SEC_ROLL_MOD);
		applySecondaryBonuses();
		
		String output = String.format("\n\n# of rolls left:%3s\n\n%-15s%-3s%-15s%-3s\n%-15s%-3s\n%-15s%-3s"
				+ "\n%-15s%-3s%-15s%-3s\n\n%22s\n\n(K)eep\n(R)eroll\n\n(Esc)ape",numRolls10,
				"Mystic Points", mMystic,"Hit Points", mHit,"Prayer Points", mPrayer,"Skill Points", mSkill,
				"Bard Points", mBard,"Gold Pieces", gold,"Armor Class "+ bArmorClass);
		Game.textDescr.appendText(output);
	}
	
	// character summary screen
	private void state11()
	{
		Game.state = 11;
		Game.validChoices.add("escape");Game.validChoices.add("c");Game.validChoices.add("s");
		Game.validChoices.add("h");Game.validChoices.add("i");Game.validChoices.add("p");
	
		
		switch (Game.userInput) {
		case "c": 
			Model m = new Model(strength,dexterity,twitch,constitution,intelligence,wisdom,commonSense,spirituality,
					charisma,luck,mHit,mMystic,mSkill,mPrayer,mBard,bArmorClass,bNAT,resModifier);
			MainFSM.playerParty.add(m);
			clear();checkState(Game.state=12);
			return;
		case "s": 
			return;
		case "h":
			return;
		case "i":
			return;
		case "p":
			return;
		case "escape":
			Game.textDescr.setVisible(true);
			clear();checkState(Game.state=10);
			return;
		default:
			break;
		}
		
		String output = String
				.format("%s,Lvl%-4d %s %s"
						+ "\n\n%d yr old %s with %3d GP"
						+ "\n%s Class, %s"
						+ "\n\nExp Pts %d%s %s"
						+ "\n\n%-12s%-10s%-10s"
						+ "\n%-12s%-10s%-10s"
						+ "\n%-12s%-10s%-10s"
						+ "\n%-12s%-10s%-10s"
						+ "\n%-12s"
						+ "\n\n%-12s %-12s"
						+ "\n%-12s %-12s"
						+ "\n%-12s %-15s"
						+ "\n%-12s %-12s"
						+ "\n%-12s %-12s"
						+ "\n%-20s"//Rez mod
						+ "\n\n(S)hare/(H)oard Gold\n(I)tems\n(Esc)ape\n(C)ontinue",
						name, level, race, profession, 
						age, gender, gold,
						charClass,alignment,
						xp,"/1000",status, 
						"Physical", "Mental", "Ineffable", 
						"ST "+ strength, "IN " + intelligence,"SP " + spirituality, 
						"TW " + twitch, "WI "+ wisdom, "CH " + charisma, 
						"DX " + dexterity, "CS " + commonSense, "LK " + luck,
						"CN " + constitution,
						"Mystic Pts " + mMystic, "Hit Pts " + mHit,
						"Prayer Pts " + mPrayer, "NAT " + bNAT,
						"Skill Pts "+ mSkill, "Armor Class "+ bArmorClass,
						"Bard Pts "+ mBard,"Magic Resist " + magicResist,
						"Commerce Mod " + commerce, "Rapport Mod " + rapport, 
						"Resurrect Mod " + resModifier+"%");
		Game.textDescr.setText(output);
	}
	
	// placeholder
	private void state12()
	{
		Game.state = 12;
		Game.validChoices.add("escape");
		switch (Game.userInput) {
		case "escape":
			Game.textDescr.setVisible(true);
			clear();checkState(Game.state=11);
			return;
		default:
			break;
		}
		Game.textDescr.setText("State12 Placeholder\n\n(Esc)ape");
	}

	/*
	 * state controller -- checks state field to determine which method/state to
	 * enter
	 */
	void checkState(int... state) {
		/* if no argument passed, initialize array with current state */
		if (state.length == 0)
			state = new int[] { Game.state };
		switch (state[0]) {
		case 2:
			begin();
			break;
		case 3:
			state3();
			break;
		case 4:
			state4();
			break;
		case 5:
			state5();
			break;
		case 6:
			state6();
			break;
		case 7:
			state7();
			break;
		case 8:
			state8();
			break;
		case 9:
			state9();
			break;
		case 10:
			state10();
			break;
		case 11:
			state11();
			break;
		case 12:
			state12();
			break;
		default:
			return;
		}
	}

	/* clear userInput field to prevent drop through */
	private void clear() {
		Game.userInput = "";
		Game.validChoices.clear();
		fullOptions.clear();
	}
	
	// used for managing textfield/label pair in age/name
	private void initLayout()
	{
		Game.textDescr.setVisible(false);
		txtField = new TextField();
		inputLayout = new VBox();
		// age
		if(Game.state==7)
		{
			inputLabel = new Label("Enter age: ");
		}
		// name
		else
		{
			inputLabel = new Label("Enter name: ");
		}
		inputLabel.setStyle("-fx-font-size: 20px;");
		inputLayout.getChildren().addAll(inputLabel, txtField);
		if(!Game.grid.getChildren().contains(inputLayout))Game.grid.add(inputLayout, 0, 1, 1, 1);
		txtField.requestFocus();
	}
	
	// determines base stats
	private void rollBaseStats(int numOfDice,int numOfSides, int modifier)
	{
		//rolls for base stats
		strength = Const.rollDice(numOfDice,numOfSides,modifier);
		dexterity = Const.rollDice(numOfDice, numOfSides,modifier);
		twitch  = Const.rollDice(numOfDice, numOfSides,modifier);
		intelligence = Const.rollDice(numOfDice, numOfSides,modifier);
		wisdom = Const.rollDice(numOfDice, numOfSides,modifier);
		commonSense = Const.rollDice(numOfDice, numOfSides,modifier);
		spirituality = Const.rollDice(numOfDice, numOfSides,modifier);
		charisma = Const.rollDice(numOfDice, numOfSides,modifier);
		luck = Const.rollDice(numOfDice, numOfSides,modifier);
		constitution = Const.rollDice(numOfDice, numOfSides,modifier);
	}
	
	// determines base secondary stats
	private void rollSecondaryStats(int numOfDice, int numOfSides, int modifier)
	{
		
		mHit = SEC_BASE+Const.rollDice(numOfDice, numOfSides, modifier);
		mPrayer = SEC_BASE+Const.rollDice(numOfDice, numOfSides, modifier);
		mSkill = SEC_BASE+Const.rollDice(numOfDice, numOfSides, modifier);
		mBard = SEC_BASE+Const.rollDice(numOfDice, numOfSides, modifier);
		mMystic = SEC_BASE+Const.rollDice(numOfDice, numOfSides, modifier);
		gold = Const.rollDice(2,3);
	}
	
	// adds bonuses (bonus.json) to ten base stats
	private void applyBaseBonuses()
	{		
		ArrayList<BonusWrapper> bonuses = MainFSM.bonuses; 
		int tempAge = age;
		
		//set age for bonus application
		if(age<=24)tempAge=24;
		else if(age<=35)tempAge=35;
		else if(age<=49)tempAge=49;
		else if(age<=69)tempAge=69;
		else if(age<=88)tempAge=88;
		
		// iterate through list of wrappers
		for(BonusWrapper bw : bonuses)
		{
			// get "name" of wrapper
			String name=bw.getName();
			// String version of age for easy comparison
			String ageStr = String.valueOf(tempAge);
			
			// if any wrapper's name matches a user choice, then apply that wrapper's bonuses
			if(name.equals(race)||name.equals(gender)||name.equals(profession)||name.equals(alignment)||name.equals(ageStr))
			{
				// add bonuses to base stats 
				strength += bw.getSt(); dexterity += bw.getDx();
				twitch += bw.getTw(); intelligence += bw.getIn();
				wisdom += bw.getWi(); commonSense += bw.getCs();
				spirituality += bw.getSp(); charisma += bw.getCh();
				luck += bw.getLk(); constitution += bw.getCn();
			}
		}

	}
	
	// adds bonuses (bonus.json & range_bonus.json) to seven secondary stats -- AC,Hit,Mystic,Prayer,SKill,Bard,Gold
	private void applySecondaryBonuses()
	{		
		ArrayList<BonusWrapper> bonuses = MainFSM.bonuses; 
		ArrayList<RangeWrapper> rangeBonuses = MainFSM.rangeBonuses; 
		int tempAge = age;
		
		//set age for bonus application
		if(age<=24)tempAge=24;
		else if(age<=35)tempAge=35;
		else if(age<=49)tempAge=49;
		else if(age<=69)tempAge=69;
		else if(age<=88)tempAge=88;
		
		// iterate through list of wrappers
		for(BonusWrapper bw : bonuses)
		{
			// get "name" of wrapper
			String name=bw.getName();
			// String version of age for easy comparison
			String ageStr = String.valueOf(tempAge);
			
			// if any wrapper's name matches a user choice, then apply that wrapper's bonuses
			if(name.equals(race)||name.equals(gender)||name.equals(profession)||name.equals(alignment)||name.equals(ageStr))
			{
				// set bonuses using mXXXX, current will equal base for now
				bArmorClass += bw.getAc(); mHit += bw.getHit();
				mMystic += bw.getMystic(); mPrayer += bw.getPrayer();
				mSkill += bw.getSkill(); mBard += bw.getBard();
				gold += bw.getGold();
			}
//			System.out.println(m.getcArmorClass());
			// consider adding AC/GOLD bonuses to separate ArrayLists for speed in next steps
		}
		
		// apply range bonuses separately
		for(RangeWrapper rw : rangeBonuses)
		{
			switch(rw.getType())
			{
				case "AC":
					// level section
					if(rw.getName().equals("Level"))
					{
						// match found
						if(level>=rw.getLower() && level<=rw.getUpper())
							bArmorClass += rw.getBonus();
					}
					// TW+DX section
					else if(rw.getName().equals("TW+DX"))
					{
						int sum = twitch + dexterity;
						// match found
						if(sum>=rw.getLower() && sum<=rw.getUpper())
							bArmorClass += rw.getBonus();
					}
//					System.out.println(m.getcArmorClass());
					break;
				case "Gold":
					// LK section
					if(rw.getName().equals("LK"))
					{
						// match found
						if(luck>=rw.getLower() && luck<=rw.getUpper())
							gold += rw.getBonus();
					}
					// CS+CH section
					else if(rw.getName().equals("CS+CH"))
					{
						int sum = commonSense + charisma;
						// match found
						if(sum>=rw.getLower() && sum<=rw.getUpper())
							gold += rw.getBonus();
					}
					break;
				case "Hit":
					// LK section
					if(rw.getName().equals("LK"))
					{
						// match found
						if(luck>=rw.getLower() && luck<=rw.getUpper())
							mHit += rw.getBonus();
					}
					// CN section
					else if(rw.getName().equals("CN"))
					{
						// match found
						if(constitution>=rw.getLower() && constitution<=rw.getUpper())
							mHit += rw.getBonus();
					}
					break;
				case "Mystic":
					// LK section
					if(rw.getName().equals("LK"))
					{
						// match found
						if(luck>=rw.getLower() && luck<=rw.getUpper())
							mMystic += rw.getBonus();
					}
					// IN+CS section
					else if(rw.getName().equals("IN+CS"))
					{
						int sum =intelligence + commonSense;
						// match found
						if(sum>=rw.getLower() && sum<=rw.getUpper())
							mMystic += rw.getBonus();
					}
					break;
				case "Prayer":
					// LK section
					if(rw.getName().equals("LK"))
					{
						// match found
						if(luck>=rw.getLower() && luck<=rw.getUpper())
							mPrayer += rw.getBonus();
					}
					// WI+SP section
					else if(rw.getName().equals("WI+SP"))
					{
						int sum = wisdom + spirituality;
						// match found
						if(sum>=rw.getLower() && sum<=rw.getUpper())
							mPrayer += rw.getBonus();
					}
					break;
				case "Skill":
					// LK section
					if(rw.getName().equals("LK"))
					{
						// match found
						if(luck>=rw.getLower() && luck<=rw.getUpper())
							mSkill += rw.getBonus();
					}
					// DX+CS section
					else if(rw.getName().equals("DX+CS"))
					{
						int sum = dexterity + commonSense;
						// match found
						if(sum>=rw.getLower() && sum<=rw.getUpper())
							mSkill += rw.getBonus();
					}
					break;
				case "Bard":
					// LK section
					if(rw.getName().equals("LK"))
					{
						// match found
						if(luck>=rw.getLower() && luck<=rw.getUpper())
							mBard += rw.getBonus();
					}
					// DX+CH section
					else if(rw.getName().equals("DX+CS"))
					{
						int sum = dexterity + charisma;
						// match found
						if(sum>=rw.getLower() && sum<=rw.getUpper())
							mBard += rw.getBonus();
					}
					break;
				case "Magic Resist":
					// CN section
					if(rw.getName().equals("CN"))
					{
						// CN section
						if(constitution>=rw.getLower() && constitution<=rw.getUpper())
							magicResist += rw.getBonus();
					}
					// SP section
					else if(rw.getName().equals("SP"))
					{
						// match found
						if(spirituality>=rw.getLower() && spirituality<=rw.getUpper())
							magicResist += rw.getBonus();
					}
					break;
				case "Commerce":
					// CH section
					if(rw.getName().equals("CH"))
					{
						// CH section
						if(charisma>=rw.getLower() && charisma<=rw.getUpper())
							commerce += rw.getBonus();
					}
					// CS section
					else if(rw.getName().equals("CS"))
					{
						// match found
						if(commonSense>=rw.getLower() && commonSense<=rw.getUpper())
							commerce += rw.getBonus();
					}
					break;
				case "Rapport":
					// CH section
					if(rw.getName().equals("CH"))
					{
						// CN section
						if(charisma>=rw.getLower() && charisma<=rw.getUpper())
							rapport += rw.getBonus();
					}
					// WI section
					else if(rw.getName().equals("WI"))
					{
						// match found
						if(wisdom>=rw.getLower() && wisdom<=rw.getUpper())
							rapport += rw.getBonus();
					}
					break;
				case "Recovery":
					// CN section
					if(rw.getName().equals("CN"))
					{
						// CN section
						if(constitution>=rw.getLower() && constitution<=rw.getUpper())
							recovery += rw.getBonus();
					}
					// SP section
					else if(rw.getName().equals("SP"))
					{
						// match found
						if(spirituality>=rw.getLower() && spirituality<=rw.getUpper())
							recovery += rw.getBonus();
					}
					break;
				default:break;
			}
		}
	}
	// returns mean of base stats
	private double meanBaseStats()
	{
		double mean = (strength + dexterity + twitch + intelligence + wisdom
				+ commonSense + spirituality + charisma + luck + constitution)/10.0;
		return mean;
	}
}
