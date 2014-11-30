package gui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import character.Model;
import main.Game;
import main.CharCreationFSM;
import main.PlayerParty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
	private TableColumn<Model, String> nameCol;

	@FXML
    private TableColumn<Model, Integer> lvlCol;

    @FXML
	private TableColumn<Model, Integer> acCol;

	@FXML
	private TableColumn<Model, Integer> hpCol;

	@FXML
	private TableColumn<Model, Integer> mpCol;
	
    @FXML
    private TableColumn<Model, Integer> ppCol;

	@FXML
	private TableColumn<Model, Integer> spCol;

	@FXML
	private TableColumn<Model, Integer> bpCol;

	@FXML
	private TableColumn<Model, String> profCol;

	@FXML
	private TableColumn<Model, Integer> staCol;

	@FXML
	private TableColumn<Model, Integer> goldCol;

	@FXML
    private TableColumn<Model, ?> wpnCol;

    @FXML
    private Menu menuFile;

    @FXML
    private TextArea textDescr;
    
    @FXML
    private TextArea userStatChange;

    @FXML
    private MenuBar menuBar;
    
    @FXML
    private Button backButton;

    @FXML
    private Button nextButton;

    @FXML
    private TextField txtField;
    
    private StringProperty textProperty;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		textProperty = new SimpleStringProperty();
		textDescr.setEditable(false);
		assert textDescr != null : "fx:id=\"textDescr\" was not injected: check your FXML file 'GuiFXML.fxml'.";
        textDescr.textProperty().bind(textProperty);
        txtField.setVisible(false);
        backButton.setVisible(false);
		nextButton.setVisible(false);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<Model, String>("name"));
        lvlCol.setCellValueFactory(
                new PropertyValueFactory<Model, Integer>("level"));
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
						
	}
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
}