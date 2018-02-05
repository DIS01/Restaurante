package sares.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
public class MeseroController implements Initializable {
    private final String[] opciones={"Crear cuenta(s)","Agregar Pedido","Consultar pedido","Modificar Pedido","Eliminar Pedido"};
    
    private Mesero mesero;
    private LinkedList<Cuenta> cuentasLista=new LinkedList<>();
    private int cont = 0;
    private Calendar calendar;
    private LinkedList<Pedido> listaPedido=new LinkedList<>();
    private LinkedList<Pedido> listaPedidosPrioridad;
    
    @FXML
    private HBox hbox;
    @FXML
    private Label nombre;
    @FXML
    private Label tiempo;
    @FXML
    private ListView<String> escogerMenu;
    @FXML
    private ListView<String> cuentasRecientes;
    @FXML
    private ListView<Cuenta> cuentasCola;
    
    @FXML
    private VBox root;
    @FXML
    private MenuItem cerrar,info,sesioncierre;
    
    
    @FXML
    private MenuButton opcionesUsuario;

    @FXML 
    private ListView<Pedido> listaPedidos;
    
   @FXML 
    private ListView<Pedido> listaPedidosPrioridades;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaPedidosPrioridad=new LinkedList<>();
        reloj();
        ObservableList<String> items = FXCollections.observableArrayList(opciones);
        this.escogerMenu.setItems(items);
        try {
            cuentasLista=Cuenta.getCuentas();
            if(!cuentasLista.isEmpty()){
                this.getListaPedidos(cuentasLista);
                if(!this.listaPedido.isEmpty()){
                   this.listaPedidos.setItems(FXCollections.observableList(this.listaPedido));
                }
                if(!this.listaPedidosPrioridad.isEmpty()){
                    System.out.println(this.listaPedidosPrioridad);
                    this.listaPedidosPrioridades.setItems(FXCollections.observableList(this.listaPedidosPrioridad));
                }
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
            if(c.isPrioridad() && !p.isEmpty() && p!= null){
               this.listaPedidosPrioridad.addAll(p);
            }else if(!p.isEmpty() && p!= null){
                this.listaPedido.addAll(p);
            } 
              } 
    }
    
    @FXML
    public void cerrarSesion(ActionEvent e) throws IOException{
         Sares.setContent("sares/fxml/Sesion.fxml",opcionesUsuario);
    }
    
    @FXML
    public void cerrar(ActionEvent e) {
         System.exit(0);
    }
    
    @FXML
    public void informacionUsuario(ActionEvent e){
        Dialog dialog = new Dialog<>();
        dialog.setTitle("Información usuario");
        dialog.setHeaderText(null);

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        
        TextField dni = new TextField();
        dni.setText(mesero.getDni());
        dni.setEditable(false);
        TextField nombres = new TextField();
        nombres.setText(mesero.getNombres());
        nombres.setEditable(false);
        TextField apellidos = new TextField();
        apellidos.setText(mesero.getApellidos());
        apellidos.setEditable(false);
        TextField domicilio = new TextField();
        domicilio.setText(mesero.getDomicilio());
        domicilio.setEditable(false);
        
        grid.add(new Label("Dni:"), 0, 0);
        grid.add(dni, 1, 0);
        grid.add(new Label("Nombres:"), 0, 1);
        grid.add(nombres, 1, 1);
        grid.add(new Label("Apellidos:"), 0, 2);
        grid.add(apellidos, 1, 2);
        grid.add(new Label("Domicilio:"), 0, 3);
        grid.add(domicilio, 1, 3);

        dialog.getDialogPane().setContent(grid);
        dialog.showAndWait();
    }
    
    @FXML
    private void seleccionMenu(MouseEvent event) throws IOException, SQLException {
        if (opciones[0].equals(this.escogerMenu.getSelectionModel().getSelectedItem())) {
            crearCuentas().ifPresent(cuentas -> {
                int cant_cuentas=0;
                try {
                    cant_cuentas=Integer.parseInt(cuentas.getKey());
                    for (int i=1; i<=cant_cuentas; i++){
                        int cuentaID=Cuenta.insertarCuenta(Integer.parseInt(cuentas.getValue().split(",")[0]),this.getMesero().getId(),cuentas.getValue().split(",")[0].equals("1"));
                        this.cuentasRecientes.getItems().add(cuentasRecientes.getItems().size(), "Cuenta #: "+cuentaID+", Mesa #:"+cuentas.getValue()); 
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MeseroController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }else if (opciones[1].equals(this.escogerMenu.getSelectionModel().getSelectedItem())) {
            Mesero2Controller control = (Mesero2Controller)Sares.setContent("sares/fxml/Mesero2.fxml", (Node)event.getSource());
            control.meseroControllerCreate(this.mesero);  
        }else if(opciones[2].equals(this.escogerMenu.getSelectionModel().getSelectedItem())) {
            TextInputDialog dialog = new TextInputDialog("Consultar pedido");
            dialog.setTitle("Consultar pedido");
            dialog.setHeaderText(null);
            dialog.setContentText("ingrese numero de pedido");
            Optional<String> name=dialog.showAndWait();
            name.ifPresent(nPedidos ->{
                try {
                    this.consultarPedido(Integer.parseInt(nPedidos));
                } catch (SQLException ex) {
                    Logger.getLogger(MeseroController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(MeseroController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
    }
   
    public void meseroControllerCreate(Mesero mesero) {
        this.mesero = mesero;
        this.nombre.setText(nombre.getText()+mesero.getNombres());
    }
    
    public Mesero getMesero(){
        return this.mesero;
    }
    public void reloj(){
        this.calendar = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
        this.tiempo.setText(sdf1.format(this.calendar.getTime()));
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
  
    private Optional<Pair<String, String>>crearCuentas(){
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Crear cuentas(s)");
        dialog.setHeaderText(null);
        ButtonType loginButtonType = new ButtonType("Registrar cuentas(s)", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

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
        dialog.getDialogPane().setContent(grid);

         dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(numCuentas.getText(),mesa.getText()+","+prioridad.isSelected());
            }
            return null;
        });
        Optional<Pair<String, String>> result = dialog.showAndWait();
        return result;
    }
    
    public void mensajeExitoso(String mjs){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Sares");
        alert.setHeaderText(null);
        alert.setContentText(mjs);
        alert.showAndWait();
    }
    
    public void consultarPedido(int id) throws SQLException, ParseException{
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Sares");
        alert.setHeaderText(null);
        Pedido p=Pedido.getPedido(id);
        if(p!=null){
            alert.setContentText("Pedido # "+p.getId()+"\n Fecha:hora de ingreso: "+p.getFecha()+ ":"+p.getHoraingreso()+"\n Estado: "+ p.getEstado()+"\n Tiempo estimado: "+ p.getTiempoestimado());
        }else{
            alert.setContentText("Dicho numero de pedido no existe");
        }
            alert.showAndWait();
    }
}
