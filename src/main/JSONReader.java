/*
 * 
 * Utility class that reads JavaScript Object Notation files containing user-moddable aspects of the game.
 * 
 * Author: Josh Blitz
 * 
 */

package main;

import java.util.*;
import javax.json.*;
import character.*;

class JSONReader
{
	// used by JSON reader methods
	ArrayList<String> items = new ArrayList<String>();
	
	// file paths
	private String bonus = "resources/data/bonus.json";
	private String range_bonus = "resources/data/range_bonus.json";
	private String races = "resources/data/races.json";
	private String genders = "resources/data/genders.json";
	private String alignments = "resources/data/alignments.json";
	private String classes = "resources/data/classes.json";
	private String professions = "resources/data/professions.json";
	
	// reads in bonus.json and range_bonus.json then places them in Wrapper objects
	public void readBonusFile()
	{
		MainFSM.bonuses.clear();
		try {
			// bonus.json
			JsonReader reader = Json.createReader(getClass().getClassLoader().getResourceAsStream(bonus));
			JsonArray arr = reader.readArray();

//			System.out.println(arr);
			List<JsonObject> x = arr.getValuesAs(JsonObject.class);

			/* iterate through objects in bonus JSON array */
			for (JsonObject obj : x) {
				MainFSM.bonuses.add(new BonusWrapper(obj.entrySet()));
			}
			
			//range_bonus.json
			reader = Json.createReader(getClass().getClassLoader().getResourceAsStream(range_bonus));
			arr = reader.readArray();

//			System.out.println(arr);
			x = arr.getValuesAs(JsonObject.class);

			/* iterate through objects in bonus JSON array */
			for (JsonObject obj : x) {
				MainFSM.rangeBonuses.add(new RangeWrapper(obj.entrySet()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	/*
	 * read user-moddable .json files containing only arrays depending on
	 * current Game.state
	 */
	public void readJSONArray() {
		try {
			JsonReader reader = null;
			items.clear();
			/* generate GUI text choices */
			StringBuilder output = new StringBuilder();
			/* current game state decides which file to read in */
			switch (Game.state) {
			case 2:
				output.append("Choose Race!\n\n");
				reader = Json.createReader(getClass().getClassLoader().getResourceAsStream(races));
				break;
			case 3:
				output.append("Choose Gender!\n\n");
				reader = Json
						.createReader(getClass().getClassLoader().getResourceAsStream(genders));
				break;
			case 4:
				output.append("Choose Class!\n\n");
				reader = Json
						.createReader(getClass().getClassLoader().getResourceAsStream(classes));
				break;
			case 6:
				output.append("Choose Alignment!\n\n");
				reader = Json.createReader(getClass().getClassLoader().getResourceAsStream(alignments));
				break;
			default:
				return;
			}

			JsonArray arr = reader.readArray();

			/*
			 * convert all JsonValues into Strings -- trim quotes and check
			 * length isn't ridiculous
			 */
			for (JsonValue v : arr) {
				String s = v.toString();
				if (s.length() > 25)
					throw new Exception(
							"Value in JSON too long! Keep identifiers under 25 letters.");
				items.add(s.substring(1, s.length() - 1));
				CharCreationFSM.fullOptions.add(s.substring(1, s.length() - 1));
			}
			finishJSON(output);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	/* read JSON file containing object maps */
	public void readJSONObject() {
		try {
			JsonReader reader = null;
			JsonArray arr = null;
			/* generate GUI text choices */
			StringBuilder output = new StringBuilder();
			items.clear();

			/*
			 * current game state decides which file to parse then read in top
			 * level object
			 */
			switch (Game.state) {
			case 5:
				reader = Json.createReader(getClass().getClassLoader().getResourceAsStream(professions));
				output.append("Choose Profession!\n\n");
				arr = reader.readObject().getJsonArray("professions");
				break;
			default:
				return;
			}

			// System.out.println(arr);
			List<JsonObject> x = arr.getValuesAs(JsonObject.class); // System.out.println(x);

			/* iterate through objects in profession */
			for (JsonObject obj : x) {
				/* object contains chosen charclass */
				if (obj.containsKey(CharCreationFSM.charClass)) {
					/*
					 * convert all JsonValues into Strings -- trim quotes and
					 * check length isn't ridiculous
					 */
					for (JsonValue v : obj.getJsonArray(CharCreationFSM.charClass)) {
						String s = v.toString();
						if (s.length() > 25)
							throw new Exception(
									"Value in JSON too long! Keep inputs under 25 letters.");
						items.add(s.substring(1, s.length() - 1));
						CharCreationFSM.fullOptions.add(s.substring(1, s.length() - 1));
					}
				}
			}
			finishJSON(output);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	public void finishJSON(StringBuilder output) throws Exception {
		ArrayList<String> validChoices = new ArrayList<String>();
		validChoices.add("escape");

		/*
		 * dynamic processing actions -- must set Game.validChoices and
		 * Game.textDescr
		 */
		/* iterate over choice Strings */
		for (String s : items) {
			/*
			 * check each letter in s against each letter in validChoice
			 * ArrayList
			 */
			middle: for (int i = 0; i < s.length(); i++) {
				/* letter to be checked against valid array */
				String letter = String.valueOf(s.charAt(i)).toLowerCase();
				/* check valid ArrayList for letter */
				for (int j = 0; j < validChoices.size(); j++)
					/* match found -- try next letter */
					if (letter.equals(validChoices.get(j))) {
						continue middle;
					}
				/*
				 * no match found returns control to middle loop -- add to valid
				 * choices array
				 */
				validChoices.add(letter);
				break middle;
			}
		}
		/* remove "escape" for now */
		validChoices.remove(0);

		for (int i = 0; i < items.size(); i++) {
			/* retrieve first choice FULL word */
			StringBuilder sb = new StringBuilder(items.get(i).toLowerCase());
			/* get index of letter that needs to be wrapped */
			int index = sb.indexOf(validChoices.get(i));
			// System.out.println(index);
			/* reset first letter to uppercase by subtracting 32 */
			sb.setCharAt(0, (char) (sb.charAt(0) - 32));
			String mod = sb.insert(index, "(").insert(index + 2, ")")
					.toString();
			output.append(mod + "\n");
		}
		output.append("\n(Esc)ape");

		/* dump all dynamically generated choices to GUI --add "escape" */
		validChoices.add("escape");
		Game.validChoices = validChoices;
		Game.textDescr.setText(output.toString());
		// for(String s :
		// Game.validChoices)System.out.print(s);System.out.println();

	}
}
