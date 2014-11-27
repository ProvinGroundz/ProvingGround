package gui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import main.Game;
import main.CharCreationFSM;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GuiController implements Initializable {

    @FXML
    private ImageView img;

    @FXML
    private TableColumn<?, ?> lvlCol;

    @FXML
    private TableColumn<?, ?> wpnCol;

    @FXML
    private Menu menuFile;

    @FXML
    private TableColumn<?, ?> bpCol;

    @FXML
    private TableColumn<?, ?> profCol;

    @FXML
    private TableColumn<?, ?> spCol;

    @FXML
    private TableColumn<?, ?> goldCol;

    @FXML
    private TableColumn<?, ?> staCol;

    @FXML
    private TableColumn<?, ?> mpCol;

    @FXML
    private TextArea textDescr;

    @FXML
    private MenuBar menuBar;

    @FXML
    private TableColumn<?, ?> hpCol;

    @FXML
    private TableColumn<?, ?> nameCol;

    @FXML
    private TableView<?> partyTable;

    @FXML
    private TableColumn<?, ?> acCol;
    
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
}