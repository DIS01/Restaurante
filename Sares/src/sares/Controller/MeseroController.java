/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sares.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sares.Model.Bebida;
import sares.Model.Categoria;
import sares.Model.Conexion;
import sares.Model.Item;
import sares.Model.Mesero;
import sares.Model.Platillo;
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
    private Button btnSignOut;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODOh
        this.calendar = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
        this.tiempo.setText(sdf1.format(this.calendar.getTime()));
        ObservableList<String> items = FXCollections.observableArrayList(
                "Crear Pedido", "Modificar Pedido", "Eliminar Pedido");
        this.escogerMenu.setItems(items);

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

    @FXML

    private void handleSignOutAction(MouseEvent event) throws IOException {
        System.out.println("SignOut");
        Sares.setContent("sares/fxml/login.fxml", root);

    }

    @FXML
    private void seleccionMenu(MouseEvent event) {
        try {
            System.out.println(this.escogerMenu.getSelectionModel().getSelectedItem());

            if ("Crear Pedido".equals(this.escogerMenu.getSelectionModel().getSelectedItem())) {
                Sares.setContent("sares/fxml/Mesero2.fxml", root);
//        
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

//Stage stage;
//        stage = new Stage();
//      stage.setScene(scene);
//      stage.show();
    }
    
 public LinkedList<Item> getItems(Categoria c,int code,Conexion co) throws SQLException{ 
        LinkedList<Item> lista = new LinkedList();
        if (c.getNombre()=="Bebidas"){
            ResultSet itemsRS = co.consultar("SELECT * FROM Item,Bebida where Item.id=Bebida.item");
            while (itemsRS.next()){
                lista.add(new Bebida(itemsRS.getString("marca") ,itemsRS.getFloat("contenido"),itemsRS.getFloat("valor"),itemsRS.getString("nombre"),itemsRS.getString("descripcion"), itemsRS.getBoolean("promo"),itemsRS.getFloat("porcentaje")));
            }  
        }else if(c.getNombre()=="Combo"){
                 
        }else{
            ResultSet itemsRS = co.consultar("SELECT * FROM Item,Platillo where Item.id=Platillo.item");
            while (itemsRS.next()){
                lista.add(new Platillo(itemsRS.getTime("tiempoEstimado") ,itemsRS.getFloat("valor"),itemsRS.getString("nombre"),itemsRS.getString("descripcion"), itemsRS.getBoolean("promo"),itemsRS.getFloat("porcentaje")));
            }
            itemsRS.close();
        }
       return lista;
     }
    
    public LinkedList<Categoria> getCategorias() throws SQLException{
        Conexion c=new Conexion();
        LinkedList<Categoria> lista = new LinkedList();
        Categoria cate;
        ResultSet categoriasRS = c.consultar("SELECT * FROM Categoria"); 
        while (categoriasRS.next()){
            cate= new Categoria();
            cate.setNombre(categoriasRS.getString("nombre"));
            cate.setListItems(this.getItems(cate,categoriasRS.getInt("id"),c));
            lista.add(cate);
        }
        categoriasRS.close();
        return lista;
    }
    
    public void changeView(Node a) throws IOException{
         Sares.setContent("sares/fxml/Mesero.fxml",a);
    }
    public void meseroControllerCreate(ResultSet meseroInfo) {
        try {
            this.mesero = new Mesero(meseroInfo.getString("nombres"));
            this.nombre.setText(mesero.getNombre());
        } catch (SQLException ex) {
            Logger.getLogger(MeseroController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
