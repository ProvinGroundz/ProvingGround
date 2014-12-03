/*
 * 
 * The entry class of the application containing main. The JavaFX 
 * GUI is initialized here and the state machine is created and entered.
 * 
 * Author: Josh Blitz
 * 
 */

package main;
	
import gui.GuiController;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import main.MainFSM;


public class Game extends Application 
{	
	// empty string when no valid key has been pressed
	static String userInput = "";

	// contains STATE represented by an integer
	public static int state = 0;
	
	// contains array of valid choices based on what is presented to user
	static ArrayList<String> validChoices = new ArrayList<String>();
	
	// necessary FSMs -- add more as they are developed
	static MainFSM mainFSM = new MainFSM();
	public static CharCreationFSM charCreationFSM = new CharCreationFSM();
	public static GuiController controller;
	
	
	// GUI elements
	private Scene scene;
	
	public static void main(String[] args) { launch(args);}
	
	/* */
	@Override
	public void start(Stage stage) {
		
		try {			

			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/GuiFXML.fxml"));
			Parent root = (Parent) loader.load();
			controller = loader.getController();
			scene = new Scene(root,1024,768);
			stage.setScene(scene);
			stage.setTitle("Proving Grounds");
			stage.show();
			
			// event handler for the keyboard
			EventHandler<KeyEvent> keyEvent = new EventHandler<KeyEvent>(){
				public void handle(KeyEvent ke)
				{
//					System.out.println(ke.getCode().toString());
//					System.out.println(validChoices);
					
					/*call validateInput method to set userInput field if key is in validChoices array*/
					/*returns true if userInput changed -- reduce CPU cycles compared to previous code*/
					if(validateInput(ke.getCode().toString().toLowerCase()))
					{
						if(Game.state>=2 && Game.state<=11)charCreationFSM.checkState();
						else mainFSM.checkState(state);
					}
				}
			};
			// add event handler defined above to the scene 
			scene.setOnKeyReleased(keyEvent);
			
			// enter the game
			mainFSM.enter();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	/*checks every key typed against validChoice array -- return true if userInput changed*/
	private boolean validateInput(String ch)
	{
		for(String s : Game.validChoices)
			if(ch.equals(s))
			{
				userInput=ch;
				return true;
			}
		return false;
	}

}
	
