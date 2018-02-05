/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sares.Controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import sares.Model.Persona;
import sares.Sares;

/**
 *
 * @author mdleiton
 */
public class Ventana implements Initializable{
    
    private Persona p;
    private int cont = 0;
    private Calendar calendar;
    
    @FXML
    private HBox hbox;
    @FXML
    private Label nombre, tiempo;
    @FXML
    private MenuItem cerrar,info,sesioncierre;
    @FXML
    private MenuButton opcionesUsuario;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reloj();
    }
    
    @FXML
    public void cerrarSesion(ActionEvent e) throws IOException{
         Sares.setContent("sares/fxml/Sesion.fxml",opcionesUsuario);
    }
    
    @FXML
    public void cerrar(ActionEvent e) {
         System.exit(0);
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
                    Ventana.this.calendar = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                    Ventana.this.tiempo.setText(sdf.format(Ventana.this.calendar.getTime()));
                    cont++;
                });
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
    public GridPane getinfoGrid(Persona p){
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        TextField dni = new TextField(p.getDni());
        dni.setEditable(false);
        TextField nombres = new TextField(p.getNombres());
        nombres.setEditable(false);
        TextField apellidos = new TextField(p.getApellidos());
        apellidos.setEditable(false);
        TextField domicilio = new TextField(p.getDomicilio());
        domicilio.setEditable(false);
        grid.add(new Label("Dni:"), 0, 0);
        grid.add(dni, 1, 0);
        grid.add(new Label("Nombres:"), 0, 1);
        grid.add(nombres, 1, 1);
        grid.add(new Label("Apellidos:"), 0, 2);
        grid.add(apellidos, 1, 2);
        grid.add(new Label("Domicilio:"), 0, 3);
        grid.add(domicilio, 1, 3);
        return grid;
    }
    
    @FXML
    public void informacionUsuario(ActionEvent e){
        Dialog dialog = new Dialog<>();
        dialog.setTitle("Información usuario");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK);
        GridPane grid= getinfoGrid(this.p);
        dialog.getDialogPane().setContent(grid);
        dialog.showAndWait();
    }

    public Persona getP() {
        return p;
    }

    public void setP(Persona p) {
        this.p = p;
    }

    public Label getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.setText(nombre);
    }
    
    public void ControllerCreate(Persona persona) {
        this.setP(persona);
        this.setNombre(this.getNombre().getText()+persona.getNombres());
    }
    
}
