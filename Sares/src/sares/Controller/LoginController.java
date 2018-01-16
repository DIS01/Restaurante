/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sares.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sares.Sares;

/**
 * FXML Controller class
 *
 * @author steevenrodriguez
 */
public class LoginController implements Initializable {

    @FXML
    private Label userName;
    @FXML
    private TextField textField_user;
    @FXML
    private Label Password;
    @FXML
    private PasswordField passField;
    @FXML
    private Label titulo;
    @FXML
    private Label notLog;
    @FXML
    private Button logButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onLogAction(ActionEvent event) throws IOException {
        Sares.setContent("sares/fxml/Mesero.fxml", notLog);
    }
    
}
