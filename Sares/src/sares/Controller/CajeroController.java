/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sares.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import sares.Model.Cajero;
import sares.Model.Mesero;
import sares.Sares;

/**
 * FXML Controller class
 *
 * @author mdleiton
 */
public class CajeroController implements Initializable {
    private static final String opcion1="Registrar Cliente";
    private static final String opcion2="Asignar cuenta a cliente";
    private static final String opcion3="Registrar pago";
    private Cajero cajero;
    private int cont = 0;
    private Calendar calendar;
    
    @FXML
    private Label cajeroNombre;
    @FXML
    private Label tiempo;
    @FXML
    private ListView<String> opciones;

    public Cajero getCajero() {
        return cajero;
    }

    public void setCajero(Cajero cajero) {
        this.cajero = cajero;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reloj();
        ObservableList<String> opcionesO = FXCollections.observableArrayList(
                opcion1,opcion2,opcion3);
        this.opciones.setItems(opcionesO);
        
    }
 
    public void setnombre(Cajero cajero){
            this.cajeroNombre.setText(cajeroNombre.getText()+cajero.getNombres());
            this.cajero=cajero;
        }
    
    public void cerrarSesion(MouseEvent event) throws IOException{
        Sares.setContent("sares/fxml/login.fxml", (Node)event.getSource());
    }
   @FXML
    private void elegirOpcion(MouseEvent event) throws IOException, SQLException {
        if (opcion1.equals(this.opciones.getSelectionModel().getSelectedItem())) {
           Cajero2Controller control = (Cajero2Controller)Sares.setContent("sares/fxml/Cajero2RegistrarCliente.fxml", (Node)event.getSource());
           control.setnombre(cajero);
        }else if (opcion2.equals(this.opciones.getSelectionModel().getSelectedItem())) {
           Cajero3Controller control = (Cajero3Controller)Sares.setContent("sares/fxml/Cajero3AsignarCuenta.fxml", (Node)event.getSource());
           control.setnombre(cajero);
   
        
        }
    }
    
    public void reloj(){
        this.calendar = Calendar.getInstance();
         SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
        this.tiempo.setText(sdf1.format(this.calendar.getTime()));
        Thread thread;
        thread = new Thread(() -> {
            while (cont != 100) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(CajeroController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Platform.runLater(() -> {
                    CajeroController.this.calendar = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                    CajeroController.this.tiempo.setText(sdf.format(CajeroController.this.calendar.getTime()));
                    cont++;
                });
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}


      
    
    