/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sares.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import sares.Model.Cliente;
import sares.Sares;

/**
 * FXML Controller class
 *
 * @author mdleiton
 */
public class Cajero3Controller extends CajeroController {

    @FXML
    ComboBox opcionesClientes ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reloj();
        try {
            
            opcionesClientes.getItems().addAll(Cliente.getClientes());
        } catch (SQLException ex) {
            Logger.getLogger(Cajero3Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Cajero3Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }    
   @FXML
    public void cancelarRegistro(MouseEvent event) throws IOException{
        CajeroController controlc = (CajeroController)Sares.setContent("sares/fxml/Cajero.fxml", (Node)event.getSource());
        controlc.setnombre(this.getCajero());
    }
    
    @FXML
    public void registrarCuenta(MouseEvent event) throws SQLException{
        
    }
    
}
