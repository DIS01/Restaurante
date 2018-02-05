package sares.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import sares.Sares;

/**
 * FXML Controller class
 *
 * @author mdleiton
 */
public class CajeroController extends Ventana {
    private static final String opcion1="Registrar Cliente";
    private static final String opcion2="Registrar pago cuenta";
    
    @FXML
    private ListView<String> opciones;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reloj();
        ObservableList<String> opcionesO = FXCollections.observableArrayList(
                opcion1,opcion2);
        this.opciones.setItems(opcionesO);
    }
 
   @FXML
    private void elegirOpcion(MouseEvent event) throws IOException, SQLException {
        if (opcion1.equals(this.opciones.getSelectionModel().getSelectedItem())) {
           Cajero2Controller control = (Cajero2Controller)Sares.setContent("sares/fxml/Cajero2RegistrarCliente.fxml", (Node)event.getSource());
           control.ControllerCreate(this.getP());  
        }else if (opcion2.equals(this.opciones.getSelectionModel().getSelectedItem())) {
           Cajero3Controller control = (Cajero3Controller)Sares.setContent("sares/fxml/Cajero3AsignarCuenta.fxml", (Node)event.getSource());
           control.ControllerCreate(this.getP());  
        }
    }
    
}