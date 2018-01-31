/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sares.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import sares.Model.Mesero;
import sares.Sares;

/**
 * FXML Controller class
 *
 * @author steevenrodriguez
 */
public class MeseroController implements Initializable {
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
    private ListView<?> listarPedidos;
    @FXML
    private VBox root;
    @FXML
    private MenuItem cerrar,info,sesioncierre;
    @FXML
    private MenuButton opcionesUsuario;

    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODOh
        reloj();
        ObservableList<String> items = FXCollections.observableArrayList(
                "Crear Pedido", "Modificar Pedido", "Eliminar Pedido");
        this.escogerMenu.setItems(items);
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
        
        System.out.println(this.escogerMenu.getSelectionModel().getSelectedItem());

        if ("Crear Pedido".equals(this.escogerMenu.getSelectionModel().getSelectedItem())) {
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
}
