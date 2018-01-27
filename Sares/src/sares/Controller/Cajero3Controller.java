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
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sares.Model.Cliente;
import sares.Sares;
import org.controlsfx.control.textfield.TextFields;
import sares.Model.Pedido;
/**
 * FXML Controller class
 *
 * @author mdleiton
 */
public class Cajero3Controller extends CajeroController {

    @FXML
    TextField pedidos;
    
    @FXML
    TextField clientes;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reloj();
        try {
            TextFields.bindAutoCompletion(pedidos, Pedido.getPedidos());
            TextFields.bindAutoCompletion(clientes, Cliente.getClientes());
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
