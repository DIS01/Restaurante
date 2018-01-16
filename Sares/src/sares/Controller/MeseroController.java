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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author steevenrodriguez
 */
public class MeseroController implements Initializable {

    private Calendar calendar;
    @FXML
    private HBox hbox;
    @FXML
    private Label nombre;
    @FXML
    private Label tiempo;
    @FXML
    private ListView<String> escogerMenu;
    @FXML
    private ListView<?> listarPedidos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODOh

        ObservableList<String> items = FXCollections.observableArrayList(
                "Crear Pedido", "Modificar Pedido", "Eliminar Pedido");
        this.escogerMenu.setItems(items);

        this.hbox.setSpacing(170);
        new Thread(new Runnable() {
            @Override

            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MeseroController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Platform.runLater(new Runnable() {

                        public void run() {
                            MeseroController.this.calendar = Calendar.getInstance();
                            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                            MeseroController.this.tiempo.setText(sdf.format(MeseroController.this.calendar.getTime()));

                        }
                    });
                }
            }
        }).start();

    }

    @FXML

    private void handleSignOutAction(MouseEvent event) {
        System.out.println("koko");

    }

    @FXML
    private void seleccionMenu(MouseEvent event) {
        System.out.println(this.escogerMenu.getSelectionModel().getSelectedItem());
    }

}
