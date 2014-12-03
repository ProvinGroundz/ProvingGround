package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import character.Model;
import main.Game;
import main.CharCreationFSM;
import main.PlayerParty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GuiController implements Initializable {

    @FXML
    private ImageView img;

    @FXML
	private TableView<Model> partyTable;

	@FXML
	private TableColumn<Model, String> nameCol, profCol;

	@FXML
    private TableColumn<Model, Integer> lvlCol,acCol,hpCol,mpCol,ppCol,spCol,bpCol,
    									staCol,goldCol;

	@FXML
    private TableColumn<Model, ?> wpnCol;

    @FXML
    private Menu menuFile;

    @FXML
    private TextArea textDescr;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Button backButton, nextButton, stUpButton, stDownButton, twUpButton, twDownButton, dxUpButton, dxDownButton, cnUpButton, cnDownButton,
    				inUpButton, inDownButton, wiUpButton, wiDownButton, csUpButton, csDownButton, spUpButton, spDownButton, chUpButton, chDownButton,
    				lkUpButton, lkDownButton, hitUpButton, hitDownButton, goldUpButton, goldDownButton;

    @FXML
    private TextField txtField;
    @FXML
    private Label bonusPointsLabel;
    
    @FXML 
    private ListView<Integer> statNumberList;
    
    @FXML
    private ListView<String> statNameList;
    
    private StringProperty textProperty;

	private int numStClicked, numTwClicked, numDxClicked, numCnClicked, numInClicked, numWiClicked, numCsClicked, numSpClicked, numChClicked, numLkClicked,
    				numHitClicked, numGoldClicked;
	private String stringForLabel = "";
    ObservableList<Integer> statNumbers;
   

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		textProperty = new SimpleStringProperty();
		assert textDescr != null : "fx:id=\"textDescr\" was not injected: check your FXML file 'GuiFXML.fxml'.";
        textDescr.textProperty().bind(textProperty);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<Model, String>("name"));
        lvlCol.setCellValueFactory(
                new PropertyValueFactory<Model, Integer>("level"));
        
	}

	public void setupStatLists() {
		statNameList.setVisible(true);
		statNumberList.setVisible(true);
		bonusPointsLabel.setVisible(true);
		nextButton.setVisible(true);
		stringForLabel += Game.charCreationFSM.bonusPoints;
		bonusPointsLabel.setText(stringForLabel);
		stUpButton.setVisible(true); twUpButton.setVisible(true); dxUpButton.setVisible(true); cnUpButton.setVisible(true);
		inUpButton.setVisible(true); wiUpButton.setVisible(true); csUpButton.setVisible(true); spUpButton.setVisible(true); 
		chUpButton.setVisible(true); lkUpButton.setVisible(true); hitUpButton.setVisible(true); goldUpButton.setVisible(true);
		ObservableList<String> statNames = FXCollections.observableArrayList("Strength", "Twitch", "Dexterity", "Constitution", "Intelligence",
										"Wisdom", "Common Sense", "Spirituality", "Charisma", "Luck", "Hit Points", "Gold Pieces");
		statNameList.setItems(statNames);
		statNumbers = FXCollections.observableArrayList(Game.charCreationFSM.strength, Game.charCreationFSM.twitch, Game.charCreationFSM.dexterity, Game.charCreationFSM.constitution, 
				Game.charCreationFSM.intelligence,Game.charCreationFSM.wisdom, Game.charCreationFSM.commonSense, Game.charCreationFSM.spirituality, Game.charCreationFSM.charisma, 
				Game.charCreationFSM.luck, Game.charCreationFSM.mHit, Game.charCreationFSM.gold);
		statNumberList.setItems(statNumbers);
	}
	
	public ReadOnlyStringProperty textProperty(){
        return textProperty;
  }
	public void setTextArea(String str) {
		textProperty.set(str);
	}
	public void setTextDescrVisible(boolean b) {
		textDescr.setVisible(b);
	}
	public void setButtonsVisible(boolean b) {
		backButton.setVisible(b);
		nextButton.setVisible(b);
	}
	public void setOnKeyReleased(EventHandler<KeyEvent> e) {
		txtField.setOnKeyReleased(e);
	}
	public void setTxtFieldVisible(boolean b) {
		txtField.setVisible(b);
		txtField.requestFocus();
	}
	public String getTxtField() {
		return txtField.getText();
	}

	public void clearTxtField() {
		// TODO Auto-generated method stub
		txtField.clear();
	}
	public void addToPartyTable(Model m) {
		
	}

	public void setItems(PlayerParty playerParty) {
		// TODO Auto-generated method stub
		partyTable.setItems(playerParty);
	}

	public ObservableList<Model> getItems() {
		// TODO Auto-generated method stub
		return partyTable.getItems();
	}
	
	public void nextClick(ActionEvent e) {
		
		switch (Game.state){
		case 7:
			boolean b = Pattern.matches("[0-9]{2}", getTxtField());
			if (b) {
				int tempAge = Integer.parseInt(getTxtField());
				if (tempAge >= 17 && tempAge <= 88) {
					txtField.clear();
					Game.charCreationFSM.age = tempAge;
					Game.charCreationFSM.checkState(Game.state=8);
				}
			txtField.clear();
			txtField.requestFocus();
			Game.charCreationFSM.checkState(Game.state=8);
			}
			break;
		case 8:
			boolean c = Pattern.matches("[1-9a-zA-Z]{1,14}",
					getTxtField());
			if (c) {
				Game.charCreationFSM.name = getTxtField();
				txtField.clear();
				txtField.setVisible(false);
				setButtonsVisible(false);
				Game.charCreationFSM.checkState(Game.state=9);
			}
			break;
		case 11:
			Game.charCreationFSM.strength = statNumbers.get(0);
			Game.charCreationFSM.twitch = statNumbers.get(1);
			Game.charCreationFSM.dexterity = statNumbers.get(2);
			Game.charCreationFSM.constitution = statNumbers.get(3);
			Game.charCreationFSM.intelligence = statNumbers.get(4);
			Game.charCreationFSM.wisdom = statNumbers.get(5);
			Game.charCreationFSM.commonSense = statNumbers.get(6);
			Game.charCreationFSM.spirituality = statNumbers.get(7);
			Game.charCreationFSM.charisma = statNumbers.get(8);
			Game.charCreationFSM.luck = statNumbers.get(9);
			Game.charCreationFSM.mHit = statNumbers.get(10);
			Game.charCreationFSM.gold = statNumbers.get(11);
			removeStatLists();
			Game.charCreationFSM.checkState(Game.state=12);
			break;
	}
		}
	private void removeStatLists() {
		statNameList.setVisible(false);
		statNumberList.setVisible(false);
		bonusPointsLabel.setVisible(false);
		nextButton.setVisible(false);
		stUpButton.setVisible(false); twUpButton.setVisible(false); dxUpButton.setVisible(false); cnUpButton.setVisible(false);
		inUpButton.setVisible(false); wiUpButton.setVisible(false); csUpButton.setVisible(false); spUpButton.setVisible(false); 
		chUpButton.setVisible(false); lkUpButton.setVisible(false); hitUpButton.setVisible(false); goldUpButton.setVisible(false);

		stDownButton.setVisible(false); twDownButton.setVisible(false); dxDownButton.setVisible(false); cnDownButton.setVisible(false);
		inDownButton.setVisible(false); wiDownButton.setVisible(false); csDownButton.setVisible(false); spDownButton.setVisible(false); 
		chDownButton.setVisible(false); lkDownButton.setVisible(false); hitDownButton.setVisible(false); goldDownButton.setVisible(false);
	}

	public void backClick(ActionEvent e) {
		switch (Game.state){
			case 7:
				setButtonsVisible(false);
				txtField.setVisible(false);
				txtField.clear();
				Game.charCreationFSM.checkState(Game.state=6);
				break;
			case 8:
				txtField.clear();
				Game.charCreationFSM.checkState(Game.state=7);
				break;
		}
	}

	public void stUpClick(ActionEvent e) {
		if(Game.charCreationFSM.bonusPoints != 0) {
			numStClicked++;
			stDownButton.setVisible(true);
			statNumbers.set(0, statNumbers.get(0) + 1);
			stringForLabel = "";
			stringForLabel += --Game.charCreationFSM.bonusPoints;
			bonusPointsLabel.setText(stringForLabel);
		}
	}
	
	public void twUpClick(ActionEvent e) {
		if(Game.charCreationFSM.bonusPoints != 0) {
			numTwClicked++;
			twDownButton.setVisible(true);
			statNumbers.set(1, statNumbers.get(1) + 1);
			stringForLabel = "";
			stringForLabel += --Game.charCreationFSM.bonusPoints;
			bonusPointsLabel.setText(stringForLabel);
		}
	}
	
	public void dxUpClick(ActionEvent e) {
		if(Game.charCreationFSM.bonusPoints != 0) {
			numDxClicked++;
			dxDownButton.setVisible(true);
			statNumbers.set(2, statNumbers.get(2) + 1);
			stringForLabel = "";
			stringForLabel += --Game.charCreationFSM.bonusPoints;
			bonusPointsLabel.setText(stringForLabel);
		}
	}
	public void cnUpClick(ActionEvent e) {
		if(Game.charCreationFSM.bonusPoints != 0) {
			numCnClicked++;
			cnDownButton.setVisible(true);
			statNumbers.set(3, statNumbers.get(3) + 1);
			stringForLabel = "";
			stringForLabel += --Game.charCreationFSM.bonusPoints;
			bonusPointsLabel.setText(stringForLabel);
		}
	}
	public void inUpClick(ActionEvent e) {
		if(Game.charCreationFSM.bonusPoints != 0) {
			numInClicked++;
			inDownButton.setVisible(true);
			statNumbers.set(4, statNumbers.get(4) + 1);
			stringForLabel = "";
			stringForLabel += --Game.charCreationFSM.bonusPoints;
			bonusPointsLabel.setText(stringForLabel);
		}
	}
	public void wiUpClick(ActionEvent e) {
		if(Game.charCreationFSM.bonusPoints != 0) {
			numWiClicked++;
			wiDownButton.setVisible(true);
			statNumbers.set(5, statNumbers.get(5) + 1);
			stringForLabel = "";
			stringForLabel += --Game.charCreationFSM.bonusPoints;
			bonusPointsLabel.setText(stringForLabel);
		}
	}
	public void csUpClick(ActionEvent e) {
		if(Game.charCreationFSM.bonusPoints != 0) {
			numCsClicked++;
			csDownButton.setVisible(true);
			statNumbers.set(6, statNumbers.get(6) + 1);
			stringForLabel = "";
			stringForLabel += --Game.charCreationFSM.bonusPoints;
			bonusPointsLabel.setText(stringForLabel);
		}
	}
	public void spUpClick(ActionEvent e) {
		if(Game.charCreationFSM.bonusPoints != 0) {
			numSpClicked++;
			spDownButton.setVisible(true);
			statNumbers.set(7, statNumbers.get(7) + 1);
			stringForLabel = "";
			stringForLabel += --Game.charCreationFSM.bonusPoints;
			bonusPointsLabel.setText(stringForLabel);
		}
	}
	public void chUpClick(ActionEvent e) {
		if(Game.charCreationFSM.bonusPoints != 0) {
			numChClicked++;
			chDownButton.setVisible(true);
			statNumbers.set(8, statNumbers.get(8) + 1);
			stringForLabel = "";
			stringForLabel += --Game.charCreationFSM.bonusPoints;
			bonusPointsLabel.setText(stringForLabel);
		}
	}
	public void lkUpClick(ActionEvent e) {
		if(Game.charCreationFSM.bonusPoints != 0) {
			numLkClicked++;
			lkDownButton.setVisible(true);
			statNumbers.set(9, statNumbers.get(9) + 1);
			stringForLabel = "";
			stringForLabel += --Game.charCreationFSM.bonusPoints;
			bonusPointsLabel.setText(stringForLabel);
		}
	}
	public void hitUpClick(ActionEvent e) {
		if(Game.charCreationFSM.bonusPoints != 0) {
			numHitClicked++;
			hitDownButton.setVisible(true);
			statNumbers.set(10, statNumbers.get(10) + 1);
			stringForLabel = "";
			stringForLabel += --Game.charCreationFSM.bonusPoints;
			bonusPointsLabel.setText(stringForLabel);
		}
	}
	public void goldUpClick(ActionEvent e) {
		if(Game.charCreationFSM.bonusPoints != 0) {
			numGoldClicked++;
			goldDownButton.setVisible(true);
			statNumbers.set(11, statNumbers.get(11) + 1);
			stringForLabel = "";
			stringForLabel += --Game.charCreationFSM.bonusPoints;
			bonusPointsLabel.setText(stringForLabel);
		}
	}
	public void stDownClick(ActionEvent e) {
		if(--numStClicked == 0) {
			stDownButton.setVisible(false);
		}
		stringForLabel = "";
		stringForLabel += ++Game.charCreationFSM.bonusPoints;
		bonusPointsLabel.setText(stringForLabel);
		statNumbers.set(0, statNumbers.get(0) - 1);
	}
	public void twDownClick(ActionEvent e) {
		if(--numTwClicked == 0) {
			twDownButton.setVisible(false);
		}
		stringForLabel = "";
		stringForLabel += ++Game.charCreationFSM.bonusPoints;
		bonusPointsLabel.setText(stringForLabel);
		statNumbers.set(1, statNumbers.get(1) - 1);
	}
	public void dxDownClick(ActionEvent e) {
		if(--numDxClicked == 0) {
			dxDownButton.setVisible(false);
		}
		stringForLabel = "";
		stringForLabel += ++Game.charCreationFSM.bonusPoints;
		bonusPointsLabel.setText(stringForLabel);
		statNumbers.set(2, statNumbers.get(2) - 1);
	}
	public void cnDownClick(ActionEvent e) {
		if(--numCnClicked == 0) {
			cnDownButton.setVisible(false);
		}
		stringForLabel = "";
		stringForLabel += ++Game.charCreationFSM.bonusPoints;
		bonusPointsLabel.setText(stringForLabel);
		statNumbers.set(3, statNumbers.get(3) - 1);
	}
	public void inDownClick(ActionEvent e) {
		if(--numInClicked == 0) {
			inDownButton.setVisible(false);
		}
		stringForLabel = "";
		stringForLabel += ++Game.charCreationFSM.bonusPoints;
		bonusPointsLabel.setText(stringForLabel);
		statNumbers.set(4, statNumbers.get(4) - 1);
	}
	public void wiDownClick(ActionEvent e) {
		if(--numWiClicked == 0) {
			wiDownButton.setVisible(false);
		}
		stringForLabel = "";
		stringForLabel += ++Game.charCreationFSM.bonusPoints;
		bonusPointsLabel.setText(stringForLabel);
		statNumbers.set(5, statNumbers.get(5) - 1);
	}
	public void csDownClick(ActionEvent e) {
		if(--numCsClicked == 0) {
			csDownButton.setVisible(false);
		}
		stringForLabel = "";
		stringForLabel += ++Game.charCreationFSM.bonusPoints;
		bonusPointsLabel.setText(stringForLabel);
		statNumbers.set(6, statNumbers.get(6) - 1);
	}
	public void spDownClick(ActionEvent e) {
		if(--numSpClicked == 0) {
			spDownButton.setVisible(false);
		}
		stringForLabel = "";
		stringForLabel += ++Game.charCreationFSM.bonusPoints;
		bonusPointsLabel.setText(stringForLabel);
		statNumbers.set(7, statNumbers.get(7) - 1);
	}
	public void chDownClick(ActionEvent e) {
		if(--numChClicked == 0) {
			chDownButton.setVisible(false);
		}
		stringForLabel = "";
		stringForLabel += ++Game.charCreationFSM.bonusPoints;
		bonusPointsLabel.setText(stringForLabel);
		statNumbers.set(8, statNumbers.get(8) - 1);
	}
	public void lkDownClick(ActionEvent e) {
		if(--numLkClicked == 0) {
			lkDownButton.setVisible(false);
		}
		stringForLabel = "";
		stringForLabel += ++Game.charCreationFSM.bonusPoints;
		bonusPointsLabel.setText(stringForLabel);
		statNumbers.set(9, statNumbers.get(9) - 1);
	}
	public void hitDownClick(ActionEvent e) {
		if(--numHitClicked == 0) {
			hitDownButton.setVisible(false);
		}
		stringForLabel = "";
		stringForLabel += ++Game.charCreationFSM.bonusPoints;
		bonusPointsLabel.setText(stringForLabel);
		statNumbers.set(10, statNumbers.get(10) - 1);
	}
	public void goldDownClick(ActionEvent e) {
		if(--numGoldClicked == 0) {
			goldDownButton.setVisible(false);
		}
		stringForLabel = "";
		stringForLabel += ++Game.charCreationFSM.bonusPoints;
		bonusPointsLabel.setText(stringForLabel);
		statNumbers.set(11, statNumbers.get(11) - 1);
	}
}