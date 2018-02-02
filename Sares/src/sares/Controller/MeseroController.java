package sares.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import sares.Model.Cuenta;
import sares.Model.Mesero;
import sares.Sares;

/**
 * FXML Controller class
 *
 * @author steevenrodriguez
 */
public class MeseroController implements Initializable {
    private final String[] opciones={"Crear cuenta(s)","Agregar Pedido","Consultar pedido","Modificar Pedido","Eliminar Pedido"};
    
    private Mesero mesero;

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
    private ListView<String> cuentasRecientes;
    @FXML
    private ListView<Cuenta> cuentasCola;
    
    @FXML
    private VBox root;
    @FXML
    private MenuItem cerrar,info,sesioncierre;
    @FXML
    private MenuButton opcionesUsuario;


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reloj();
        ObservableList<String> items = FXCollections.observableArrayList(opciones);
        this.escogerMenu.setItems(items);
        try {
            this.cuentasCola.setItems(FXCollections.observableList(Cuenta.getCuentas()));
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(MeseroController.class.getName()).log(Level.SEVERE, null, ex);
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
                        int cuentaID=Cuenta.insertarCuenta(Integer.parseInt(cuentas.getValue()),this.getMesero().getId());
                        this.cuentasRecientes.getItems().add(cuentasRecientes.getItems().size(), "Cuenta #: "+cuentaID+", Mesa #:"+cuentas.getValue()); 
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MeseroController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }else if (opciones[1].equals(this.escogerMenu.getSelectionModel().getSelectedItem())) {
            Mesero2Controller control = (Mesero2Controller)Sares.setContent("sares/fxml/Mesero2.fxml", (Node)event.getSource());
            control.meseroControllerCreate(this.mesero);  
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

        grid.add(new Label("Cuentas:"), 0, 0);
        grid.add(numCuentas, 1, 0);
        grid.add(new Label("mesa:"), 0, 1);
        grid.add(mesa, 1, 1);

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
                return new Pair<>(numCuentas.getText(),mesa.getText());
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
}
