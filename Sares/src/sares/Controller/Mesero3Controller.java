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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sares.Model.Categoria;
import sares.Model.Conexion;
import sares.Model.Item;
import sares.Model.Mesero;
import sares.Model.Platillo;
import sares.Sares;
import sares.Model.Bebida;

/**
 * FXML Controller class
 *
 * @author steevenrodriguez
 */
public class Mesero3Controller implements Initializable {
    private Mesero mesero;
    private Categoria categoria = new Categoria();
    private Calendar time;
    private LinkedList<Item> items;
    private ObservableList<HBox> items1; 
    private HashMap<Item,LinkedList<Object>> pedido = new HashMap<>();
    @FXML
    private VBox root;
    @FXML
    private HBox hbox;
    @FXML
    private Label mesero3LblNombre;
    @FXML
    private Label tiempo;
    @FXML
    private Button BtnSignOut;
    @FXML
    private ListView<HBox> mesero3ListViewItems;
    @FXML
    private Button BtnGuardar;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
            this.time = Calendar.getInstance();
            SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
            this.tiempo.setText(sdf1.format(this.time.getTime()));
            this.hbox.setSpacing(100);
            
            this.items= new LinkedList<>();
            this.items= this.getItems(new Categoria("Platillos de entrada"));
            items1 = FXCollections.observableArrayList();
            
        this.items.forEach((temp) -> {
            HBox hbox1 = new HBox();
            VBox temp1 = new VBox();
            Label platillo = new Label(temp.getNombre());
            platillo.setOnMouseClicked((event) -> {
               if (temp1.getChildren().get(1).isVisible()){
                temp1.getChildren().get(1).setVisible(false);   
               }
               else{
                   temp1.getChildren().get(1).setVisible(true);
               }
                
            });
            TextArea ta =  new TextArea();
            ta.setVisible(false);
            ta.setMaxSize(200, 10);
            temp1.getChildren().addAll(platillo,ta);
            TextField tf1 = new TextField();
            tf1.setMaxSize(40, 40);
            tf1.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                if (!newValue.matches("\\d*")) {
                    tf1.setText(newValue.replaceAll("[^\\d]", ""));
                }   });
            tf1.setText("0");
            hbox1.getChildren().addAll(temp1,tf1);
            hbox1.setSpacing(290);
            
            items1.add(hbox1);
        });
        this.mesero3ListViewItems.setItems(items1);
        
            Thread thread = new Thread(new Runnable() {
                @Override
                
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(MeseroController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Platform.runLater(new Runnable() {
                            
                            public void run() {
                                Mesero3Controller.this.time = Calendar.getInstance();
                                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                                Mesero3Controller.this.tiempo.setText(sdf.format(Mesero3Controller.this.time.getTime()));
                                
                                
                                
                            }
                        });
                    }
                }
            });
            thread.setDaemon(true);
            thread.start();
        } catch (SQLException ex) {
            Logger.getLogger(Mesero3Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    

    @FXML
    private void handleSignOutAction(MouseEvent event) {
        try {
        System.out.println("SignOut");
        Sares.setContent("sares/fxml/login.fxml", root);
    } catch (IOException ex) {
        Logger.getLogger(Mesero2Controller.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @FXML
    private void handleGoBack(MouseEvent event) {
        this.items1.forEach((t) -> {
            TextField tf = (TextField)t.getChildren().get(1);
            
            
            if (!"0".equals(tf.getText())){
                VBox vboxTemp;
                vboxTemp= (VBox)t.getChildren().get(0);
                Label nombre = (Label) vboxTemp.getChildren().get(0);
                String nombre1 = nombre.getText();
                
               
            }
            
            
            
        });
        
    }
    public void assignMesero(Mesero mesero){
        this.mesero = new Mesero();
        this.mesero=mesero;
        this.mesero3LblNombre.setText(this.mesero.getNombre());
    }
    public void assignCategoria(Categoria categoria){
         
        this.categoria= categoria;
    }
    public LinkedList<Item> getItems(Categoria c) throws SQLException{ 
        LinkedList<Item> lista = new LinkedList();
        Conexion co=new Conexion();
        if ("Bebidas".equals(c.getNombre())){
            ResultSet itemsRS = co.consultar("SELECT * FROM Item,Bebida where Item.id=Bebida.item");
            while (itemsRS.next()){
                lista.add(new Bebida(itemsRS.getString("marca") ,itemsRS.getFloat("contenido"),itemsRS.getFloat("valor"),itemsRS.getString("nombre"),itemsRS.getString("descripcion"), itemsRS.getBoolean("promo"),
                        itemsRS.getFloat("porcentaje")));
            }  
        }else if("Combo".equals(c.getNombre())){
                 
        }
        else if("Platillos de entrada".equals(c.getNombre())){
            ResultSet itemsRS = co.consultar("SELECT * FROM Item,CategoriaItem,Platillo where Item.id=Platillo.item and Item.id=CategoriaItem.item and CategoriaItem.categoria=1");
            while (itemsRS.next()){
                lista.add(new Platillo(itemsRS.getTime("tiempoEstimado") ,itemsRS.getFloat("valor"),itemsRS.getString("nombre"),itemsRS.getString("descripcion"), itemsRS.getBoolean("promo"),itemsRS.getFloat("porcentaje")));
            
            }   
        }
        else if ("Platos Fuerte".equals(c.getNombre())){
            ResultSet itemsRS = co.consultar("SELECT * FROM Item,CategoriaItem,Platillo where Item.id=Platillo.item and Item.id=CategoriaItem.item and CategoriaItem.categoria=2");
            while (itemsRS.next()){
                lista.add(new Platillo(itemsRS.getTime("tiempoEstimado") ,itemsRS.getFloat("valor"),itemsRS.getString("nombre"),itemsRS.getString("descripcion"), itemsRS.getBoolean("promo"),itemsRS.getFloat("porcentaje")));
            
            }   
        }
        else if ("Postres".equals(c.getNombre())){
            ResultSet itemsRS = co.consultar("SELECT * FROM Item,CategoriaItem,Platillo where Item.id=Platillo.item and Item.id=CategoriaItem.item and CategoriaItem.categoria=4");
            while (itemsRS.next()){
                lista.add(new Platillo(itemsRS.getTime("tiempoEstimado") ,itemsRS.getFloat("valor"),itemsRS.getString("nombre"),itemsRS.getString("descripcion"), itemsRS.getBoolean("promo"),itemsRS.getFloat("porcentaje")));
            }
            itemsRS.close();
        }
       return lista;
     }
    
}
