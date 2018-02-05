package sares.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import sares.Model.Cuenta;
import sares.Model.Mesero;
import sares.Model.Pedido;
import sares.Sares;

/**
 * FXML Controller class
 *
 * @author steevenrodriguez
 */
public class MeseroController extends Ventana {
    
    private final String[] opciones={"Crear cuenta(s)","Agregar Pedido","Consultar pedido","Modificar Pedido","Eliminar Pedido"};
    private LinkedList<Cuenta> cuentasLista=new LinkedList<>();
    private LinkedList<Pedido> listaPedido=new LinkedList<>();
    private LinkedList<Pedido> listaPedidosPrioridad;
    
    @FXML
    private ListView<String> escogerMenu,cuentasRecientes;
    @FXML
    private ListView<Cuenta> cuentasCola;
    @FXML
    private VBox root;
    @FXML 
    private ListView<Pedido> listaPedidos, listaPedidosPrioridades;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaPedidosPrioridad=new LinkedList<>();
        reloj();
        this.escogerMenu.setItems(FXCollections.observableArrayList(opciones));
        try {
            cuentasLista=Cuenta.getCuentas();
            if(!cuentasLista.isEmpty()){
                this.getListaPedidos(cuentasLista);
                if(!this.listaPedido.isEmpty()) this.listaPedidos.setItems(FXCollections.observableList(this.listaPedido));
                if(!this.listaPedidosPrioridad.isEmpty()) this.listaPedidosPrioridades.setItems(FXCollections.observableList(this.listaPedidosPrioridad));
                this.cuentasCola.setItems(FXCollections.observableList(cuentasLista));
            }
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(MeseroController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getListaPedidos(LinkedList<Cuenta> cuentas) throws SQLException, ParseException{
        LinkedList<Pedido> p=null;
        for(Cuenta c: cuentas){
            p=Pedido.getPedidos(c);
            if(c.isPrioridad() && !p.isEmpty()) this.listaPedidosPrioridad.addAll(p);
            else if(!p.isEmpty()) this.listaPedido.addAll(p);
        } 
    }
    
    public void opcion1(){
        crearCuentas().ifPresent(cuentas -> {
            int cant_cuentas=0;
            cant_cuentas=Integer.parseInt(cuentas.getKey());
            for (int i=1; i<=cant_cuentas; i++){
                int cuentaID=0;
                try {
                    cuentaID = Cuenta.insertarCuenta(Integer.parseInt(cuentas.getValue().split(",")[0]),this.getMesero().getId(),cuentas.getValue().split(",")[0].equals("1"));
                } catch (SQLException ex) {
                    Logger.getLogger(MeseroController.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.cuentasRecientes.getItems().add(cuentasRecientes.getItems().size(), "Cuenta #: "+cuentaID+", Mesa #:"+cuentas.getValue()); 
            }
        });
    }
    
    public void opcion2(){
        TextInputDialog dialog = new TextInputDialog("Consultar pedido");
        dialog.setTitle("Consultar pedido");
        dialog.setHeaderText(null);
        dialog.setContentText("ingrese numero de pedido");
        Optional<String> name=dialog.showAndWait();
        name.ifPresent(nPedidos ->{
            try {
                this.consultarPedido(Integer.parseInt(nPedidos));
            } catch (SQLException | ParseException ex) {
                Logger.getLogger(MeseroController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
}
    
    @FXML
    private void seleccionMenu(MouseEvent event) throws IOException, SQLException {
        if (opciones[0].equals(this.escogerMenu.getSelectionModel().getSelectedItem())) {
            this.opcion1();
        }else if (opciones[1].equals(this.escogerMenu.getSelectionModel().getSelectedItem())) {
            Mesero2Controller control = (Mesero2Controller)Sares.setContent("sares/fxml/Mesero2.fxml", (Node)event.getSource());
            control.ControllerCreate(this.getP());  
        }else if(opciones[2].equals(this.escogerMenu.getSelectionModel().getSelectedItem())) {
            opcion2();
        }
    }
   
    
    
    public Mesero getMesero(){
        return (Mesero)this.getP();
    }
    
    private void validaInput(TextField mesa,TextField numCuentas,Node loginButton){
        mesa.textProperty().addListener((observable, oldValue, newValue) -> {
            try{
            mesa.setStyle(" -fx-background-color: silver; -fx-border-width: 2px ;");
            loginButton.setDisable(mesa.getText().isEmpty() || newValue.trim().isEmpty() || Integer.parseInt(newValue)<=0);      
            }catch(NumberFormatException e){
                mesa.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                 loginButton.setDisable(true);
            }
        });
        numCuentas.textProperty().addListener((observable, oldValue, newValue) -> {
            try{
            numCuentas.setStyle(" -fx-background-color: silver; -fx-border-width: 2px ;");
            loginButton.setDisable(numCuentas.getText().isEmpty() || newValue.trim().isEmpty() || Integer.parseInt(newValue)<=0 );      
            }catch(NumberFormatException e){
                numCuentas.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                loginButton.setDisable(true);
            }
        });
        
    }
    private Optional<Pair<String, String>>crearCuentas(){
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Crear cuentas(s)");
        ButtonType loginButtonType = new ButtonType("Registrar cuentas(s)", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        TextField numCuentas = new TextField();
        numCuentas.setPromptText("elegir cantidad de cuentas");
        TextField mesa = new TextField();
        mesa.setPromptText("elegir mesa");
        CheckBox prioridad = new CheckBox("");
        prioridad.setSelected(false);
        grid.add(new Label("Cuentas:"), 0, 0);
        grid.add(numCuentas, 1, 0);
        grid.add(new Label("mesa:"), 0, 1);
        grid.add(mesa, 1, 1);
        grid.add(new Label("Prioridad:"), 0, 2);
        grid.add(prioridad, 1, 2);
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);
        validaInput(mesa,numCuentas,loginButton);
        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType)return new Pair<>(numCuentas.getText(),mesa.getText()+","+prioridad.isSelected());
            return null;
        });
        return dialog.showAndWait();
    }
    
    public void mensajeExitoso(String mjs){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Sares");
        alert.setContentText(mjs);
        alert.showAndWait();
    }
    
    public void consultarPedido(int id) throws SQLException, ParseException{
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Sares");
        Pedido p=Pedido.getPedido(id);
        if(p!=null){
            alert.setContentText("Pedido #"+p.getId()+"\nFecha:hora ingreso:"+p.getFecha()+ ":"+p.getHoraingreso()+"\nEstado:"+ p.getEstado()+"\nTiempo estimado:"+ p.getTiempoestimado());
        }else{
            alert.setContentText("Dicho numero de pedido no existe");
        }
        alert.showAndWait();
    }
    
    
}
