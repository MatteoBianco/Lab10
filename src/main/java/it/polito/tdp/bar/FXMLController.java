/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.bar;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.bar.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void handleSimula(ActionEvent event) {
    	txtResult.clear();
    	this.model.simula();
    	txtResult.appendText("Gruppi di clienti arrivati al bar: " + this.model.getClienti() + "\n");
    	txtResult.appendText("Gruppi di clienti soddisfatti: " + this.model.getSoddisfatti() + "\n");
    	txtResult.appendText("Gruppi di clienti insoddisfatti: " + this.model.getInsoddisfatti() + "\n");
    	txtResult.appendText("\n");
    	txtResult.appendText("Elenco clienti: \n" + this.model.getElencoClienti());
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
