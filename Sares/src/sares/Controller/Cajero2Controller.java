/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sares.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sares.Model.Cliente;
import sares.Sares;

/**
 * FXML Controller class
 *
 * @author mdleiton
 */
public class Cajero2Controller extends CajeroController {
    @FXML
    private TextField dni;
    
    @FXML
    private TextField nombres;
    
    @FXML
    private TextField apellidos;
    
    @FXML
    private TextField domicilio;
    
    @FXML
    private TextField descuento;
    
    @FXML
    private Label estadoRegistro;
    
    @FXML
    private Button cancelar;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       reloj();
      
    } 

    @FXML
    public void registrarCliente(MouseEvent event) throws SQLException{
        Cliente.ingresarCliente(dni.getText(),nombres.getText(),apellidos.getText(),domicilio.getText(),descuento.getText());
        this.estadoRegistro.setText("Se registró con exito el cliente");
        this.vaciarEntradas();
    }
    
    @FXML
    public void cancelarRegistro(MouseEvent event) throws IOException{
        CajeroController controlc = (CajeroController)Sares.setContent("sares/fxml/Cajero.fxml", (Node)event.getSource());
        controlc.setnombre(this.getCajero());
    }
    
    public void vaciarEntradas(){
        this.nombres.setText(" ");
        this.dni.setText(" ");
        this.apellidos.setText(" ");
        this.descuento.setText(" ");
        this.domicilio.setText(" ");
    }
}
