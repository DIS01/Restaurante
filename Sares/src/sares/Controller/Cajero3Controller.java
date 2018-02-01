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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import org.controlsfx.control.PropertySheet.Item;
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
    private float subtotal=0.0f;
    private float totalvalor=0.0f;
    
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
    
     @FXML
    private ListView<String>listapagos;
    
    @FXML
    private Button registrar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reloj();
        listaCuenta=new CheckBox();
        listaCliente=new CheckBox();
        descuentoCheck=new CheckBox();
        this.descuentoPorcentaje.setEditable(false);
        this.registrar.setDisable(true);
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
    private void setCliente(MouseEvent e) throws SQLException, ParseException{
        if (listaCliente.isSelected()){
            listaCliente.setSelected(false);
        }else{
            listaCliente.setSelected(true);
            clientes.setEditable(false);
        }
    }
    
    @FXML 
    private void descuentocheckporcentaje(MouseEvent e){
        if (descuentoCheck.isSelected()){
            descuentoCheck.setSelected(false);
            this.descuentoPorcentaje.setText("0.0");
            this.descuentoPorcentaje.setEditable(false);
            this.descuentoValor.setText("0.0");
            this.getTotal();
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
        this.descuentoPorcentaje.setStyle(" -fx-background-color: silver; -fx-border-width: 2px ;");
        try {
            if (this.descuentoPorcentaje.getText() != null || !" ".equals(this.descuentoPorcentaje.getText()) ||  this.descuentoPorcentaje.getText().isEmpty() 
                    || !"  ".equals(this.descuentoPorcentaje.getText()) ){
                this.descuentoValor.setText(Float.toString(subtotal*Float.parseFloat(descuentoPorcentaje.getText())/100f));
            }else{
                this.descuentoValor.setText("0.0");     
            }
        }catch(Exception e){
            this.descuentoPorcentaje.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
            this.descuentoValor.setText("0.0");
        }
        this.totalvalor=this.subtotal-Float.parseFloat(this.descuentoValor.getText())+Float.parseFloat(this.propina.getText());
        this.total.setText(Float.toString(this.totalvalor));
    }
    
    @FXML
    private void elegirOpcion(MouseEvent event) throws IOException, SQLException {
        if ("Tarjeta de crédito".equals(this.formaPagos.getSelectionModel().getSelectedItem())) {
            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("Forma de pago: tarjeta de pago");
            dialog.setHeaderText(null);
            ButtonType loginButtonType = new ButtonType("Agregar Pago", ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            TextField numCuenta = new TextField();
            numCuenta.setPromptText("número de cuenta");
           TextField monto = new TextField();
            monto.setText(this.total.getText());

            grid.add(new Label("ingrese número de cuenta:"), 0, 0);
            grid.add(numCuenta, 1, 0);
            grid.add(new Label("monto:"), 0, 1);
            grid.add(monto, 1, 1);

            Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
            loginButton.setDisable(true);

            numCuenta.textProperty().addListener((observable, oldValue, newValue) -> {
                loginButton.setDisable(newValue.trim().isEmpty());
            });
            monto.textProperty().addListener((observable, oldValue, newValue) -> {
                loginButton.setDisable(newValue.trim().isEmpty());
            });

            dialog.getDialogPane().setContent(grid);

             dialog.setResultConverter(dialogButton -> {
                if (dialogButton == loginButtonType) {
                    return new Pair<>(numCuenta.getText(),monto.getText());
                }
                return null;
            });
            Optional<Pair<String, String>> result = dialog.showAndWait();
            result.ifPresent(pago -> {
                this.listapagos.getItems().add(listapagos.getItems().size(), pago.getValue()); 
            });
        }else if ("Dinero electrónico".equals(this.formaPagos.getSelectionModel().getSelectedItem())) {
         
        }else if ("Efectivo".equals(this.formaPagos.getSelectionModel().getSelectedItem())) {
            TextInputDialog dialog = new TextInputDialog(this.total.getText());
            dialog.setTitle("Forma de Pago: efectivo");
            dialog.setHeaderText(null);
            dialog.setContentText("Ingrese la cantidad a pagar:");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(name -> this.listapagos.getItems().add(listapagos.getItems().size(), name));
        }
        enableRegistro();
    } 
    
    /**
     *
     */
    public void enableRegistro(){
        this.registrar.setDisable(! pagado() );       
    }
        
    public boolean pagado(){
        float pagos=0.0f;
        for (String pago : listapagos.getItems()) {
            pagos+=Float.parseFloat(pago);
        }
        System.out.println(pagos);
        return pagos>=Float.parseFloat(this.total.getText());
    }
}
