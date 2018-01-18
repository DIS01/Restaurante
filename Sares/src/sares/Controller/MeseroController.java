/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sares.Controller;

import java.io.IOException;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sares.Sares;

/**
 * FXML Controller class
 *
 * @author steevenrodriguez
 */
public class MeseroController implements Initializable {

    private int cont = 0;
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
    @FXML
    private VBox root;
    @FXML
    private Button btnSignOut;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODOh
        this.nombre.setText("Mesero: Luis Lama");
        ObservableList<String> items = FXCollections.observableArrayList(
                "Crear Pedido", "Modificar Pedido", "Eliminar Pedido");
        this.escogerMenu.setItems(items);

        this.hbox.setSpacing(100);
        Thread thread;
        thread = new Thread(() -> {
            while (cont != 100) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MeseroController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Platform.runLater(() -> {
                    MeseroController.this.calendar = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                    MeseroController.this.tiempo.setText(sdf.format(MeseroController.this.calendar.getTime()));
                    cont++;
                });
            }
        });
        thread.setDaemon(true);
        thread.start();

    }

    @FXML

    private void handleSignOutAction(MouseEvent event) throws IOException {
        System.out.println("SignOut");
        Sares.setContent("sares/fxml/login.fxml", root);

    }

    @FXML
    private void seleccionMenu(MouseEvent event) {
        try {
            System.out.println(this.escogerMenu.getSelectionModel().getSelectedItem());

            if ("Crear Pedido".equals(this.escogerMenu.getSelectionModel().getSelectedItem())) {
                Sares.setContent("sares/fxml/Mesero2.fxml", root);
//        
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

//Stage stage;
//        stage = new Stage();
//      stage.setScene(scene);
//      stage.show();
    }
}
