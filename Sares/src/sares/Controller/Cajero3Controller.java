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
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import sares.Model.Cliente;
import sares.Sares;
import org.controlsfx.control.textfield.TextFields;
import sares.Model.Cuenta;
import sares.Model.MetodoPago;
import sares.Model.PagoContexto;
import sares.Model.Pedido;
/**
 * FXML Controller class
 *
 * @author mdleiton
 */
public class Cajero3Controller extends CajeroController {
    
    private LinkedList<Pedido> pedidos;
    private float subtotal;
    private float totalvalor;
    
    @FXML
    TextField cuentas;
    
    @FXML
    TextField clientes;
     
    @FXML
    Label descuentoValor;
    
    @FXML
    Label propina;
    
    @FXML
    Label total;
    
    @FXML
    TextField descuentoPorcentaje;
    
    @FXML
    CheckBox listaCliente;
    
    @FXML
    CheckBox listaCuenta;
    
    @FXML
    CheckBox descuentoCheck;
    
    @FXML
    ListView<Pedido> listaPedidos;
    
    @FXML 
    Label totalpedidos;
    
    @FXML
    private ListView<String> formaPagos;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reloj();
        listaCuenta=new CheckBox();
        listaCliente=new CheckBox();
        descuentoCheck=new CheckBox();
        try {
            TextFields.bindAutoCompletion(cuentas, Cuenta.getCuentas());
            TextFields.bindAutoCompletion(clientes, Cliente.getClientes());
            ObservableList<String> opcionesO = FXCollections.observableArrayList(PagoContexto.getMetodoCuentas());
            this.formaPagos.setItems(opcionesO);
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(Cajero3Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
   @FXML
    public void cancelarRegistro(MouseEvent event) throws IOException{
        CajeroController controlc = (CajeroController)Sares.setContent("sares/fxml/Cajero.fxml", (Node)event.getSource());
        controlc.setnombre(this.getCajero());
    }
    
    @FXML 
    private void generarlistaCuenta(MouseEvent e) throws SQLException, ParseException{
        if (listaCuenta.isSelected()){
            listaCuenta.setSelected(false);
            this.listaPedidos.setItems(null);
            this.subtotal=0.0f;
            this.totalpedidos.setText("0.0");
            this.total.setText("0.0");
            this.propina.setText("0.0");
            this.descuentoPorcentaje.setText("0");
            this.totalvalor=0.0f;
        }else{
            listaCuenta.setSelected(true);
            cuentas.setEditable(false);
            this.getPedidos();
            this.getPropina();
            this.getDescuento();
            this.getTotal();
        }
    }
    
    @FXML 
    private void descuentocheckporcentaje(MouseEvent e){
        if (descuentoCheck.isSelected()){
            descuentoCheck.setSelected(false);
            this.descuentoPorcentaje.setText("0.0");
            this.descuentoPorcentaje.setEditable(false);
        }else{
            descuentoCheck.setSelected(true);
      
            this.descuentoPorcentaje.setEditable(true);
        }
    }
    public void getPedidos() throws SQLException, ParseException{
        pedidos=Pedido.getPedidos(Integer.parseInt(cuentas.getText().split(",")[0]));
        ObservableList<Pedido> opcionesO = FXCollections.observableArrayList(pedidos);
        this.listaPedidos.setItems(opcionesO);
        this.subtotal=Pedido.getTotal(pedidos);
        this.totalpedidos.setText(Float.toString(this.subtotal));
   }
    
    public void getPropina(){
        this.propina.setText(Float.toString(this.subtotal*0.10f));
    }
    
    public void getDescuento(){
        if (descuentoCheck.isSelected()){
            this.descuentoValor.setText(Float.toString(subtotal*Float.parseFloat(descuentoPorcentaje.getText())/100f));
        }else{
            this.descuentoValor.setText("0.0");
        }
    }
  
    public void getTotal(){
        this.totalvalor=this.subtotal-Float.parseFloat(this.descuentoValor.getText())+Float.parseFloat(this.propina.getText());
        this.total.setText(Float.toString(this.totalvalor));
    }
   
    @FXML
    public void registrarCuenta(MouseEvent event) throws SQLException{
        
    }
    
    @FXML
    public void actualizar(KeyEvent event){
        this.descuentoValor.setText(Float.toString(subtotal*Float.parseFloat(descuentoPorcentaje.getText())/100f));
        this.totalvalor=this.subtotal-Float.parseFloat(this.descuentoValor.getText())+Float.parseFloat(this.propina.getText());
        this.total.setText(Float.toString(this.totalvalor));
    
    }
    
    
    
}
