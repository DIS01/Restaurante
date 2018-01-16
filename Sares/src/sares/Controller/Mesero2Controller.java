/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sares.Controller;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import sares.Model.Empleado;

/**
 * FXML Controller class
 *
 * @author steevenrodriguez
 */
public class Mesero2Controller implements Initializable {
private Empleado empleado;
private Calendar time;

    public Mesero2Controller(Empleado empleado) {
        this.empleado = empleado;
    }
    @FXML
    private VBox root;
    @FXML
    private HBox hbox;
    @FXML
    private Label nombre;
    @FXML
    private Label tiempo;
    @FXML
    private Pane pane_medio;
    @FXML
    private Label cuenta;
    @FXML
    private TextField cuentaText;
    @FXML
    private Label mesa;
    @FXML
    private TextField mesaText;
    @FXML
    private ListView<?> menu_Options;
    @FXML
    private Button buttonGoBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.nombre.setText(this.empleado.getNombres() + " "+ this.empleado.getApellidos());
        this.tiempo.setText(time.getTime().toString());
        
    }    

    @FXML
    private void handleSignOutActionhandleSignOutAction(MouseEvent event) {
    }

    @FXML
    private void seleccionMenu(MouseEvent event) {
    }

    @FXML
    private void handleGoBack(MouseEvent event) {
    }
    
}
