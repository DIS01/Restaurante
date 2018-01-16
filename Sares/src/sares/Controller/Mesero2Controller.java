/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sares.Controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
int cont;

//    public Mesero2Controller(Empleado empleado) {
//        this.empleado = empleado;
//    }
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
    private ListView<String> menu_Options;
    @FXML
    private Button buttonGoBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //this.nombre.setText(this.empleado.getNombres() + " "+ this.empleado.getApellidos());
        //this.tiempo.setText(time.getTime().toString());
        this.nombre.setText("Mesero: Luis Lama");
        ObservableList<String> items = FXCollections.observableArrayList(
                "Entrada", "Plato Fuerte", "Bebidas","Combos");
        this.menu_Options.setItems(items);

        this.hbox.setSpacing(100);
        Thread thread = new Thread(new Runnable() {
            @Override

            public void run() {
                while (cont != 100) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MeseroController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Platform.runLater(new Runnable() {

                        public void run() {
                            Mesero2Controller.this.time = Calendar.getInstance();
                            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                            Mesero2Controller.this.tiempo.setText(sdf.format(Mesero2Controller.this.time.getTime()));
                            
                            cont++;
                            
                        }
                    });
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        
        
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
