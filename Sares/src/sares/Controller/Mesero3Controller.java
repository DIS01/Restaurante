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
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
import sares.Model.Platillo;
import sares.Sares;
import sares.Model.Bebida;

/**
 * FXML Controller class
 *
 * @author steevenrodriguez
 */
public class Mesero3Controller extends MeseroController {

    private Categoria categoria;    
    private LinkedList<Item> items;
    private ObservableList<HBox> items1;
    private HashMap<Item, LinkedList<Object>> pedido ;
    @FXML
    private VBox root;
    @FXML
    private HBox hbox;
    @FXML
    private Label mesero3LblNombre;
    @FXML
    private Label mesa;
    @FXML
    private Label cuenta;

    @FXML
    private ListView<HBox> mesero3ListViewItems;
    @FXML
    private Button BtnGuardar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reloj();
    }
    
    @FXML
    private void handleGoBack(MouseEvent event) {
        try {
            this.items1.forEach((t) -> {
                TextField tf = (TextField) t.getChildren().get(1);
                
                if (!"0".equals(tf.getText())) {
                    VBox vboxTemp;
                    vboxTemp = (VBox) t.getChildren().get(0);
                    Label nombre = (Label) vboxTemp.getChildren().get(0);
                    String nombre1 = nombre.getText();
                    this.items.forEach((item) -> {
                        
                        if (item.getNombre().equals(nombre1.split(".-")[1])) {
                            System.out.println(item.getNombre());
                            LinkedList<Object> details = new LinkedList<>();
                            TextArea ta1 = (TextArea) vboxTemp.getChildren().get(1);
                            Integer cantidad = Integer.parseInt(tf.getText());
                            details.add(cantidad);
                            details.add(ta1);
                            
                            this.pedido.put(item, details);
                        }
                    });
                }
            });
            Mesero2Controller control = (Mesero2Controller) Sares.setContent("sares/fxml/Mesero2.fxml", hbox);
            control.setPedido(pedido);
            control.meseroControllerCreate(this.getMesero());
            control.setCuentaMesa(mesa.getText().split("#:")[1], cuenta.getText().split("#:")[1]);
        } catch (IOException ex) {
            Logger.getLogger(Mesero3Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void assignCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public LinkedList<Item> getItems(Categoria c) throws SQLException, ParseException {
        LinkedList<Item> lista = new LinkedList();
        if ("Bebidas".equals(c.getNombre())) {
            ResultSet itemsRS = Conexion.consultar("SELECT * FROM Item,Bebida where Item.id=Bebida.item");
            while (itemsRS.next()) {
                lista.add(new Bebida(itemsRS.getString("marca"),itemsRS.getInt("id"), itemsRS.getFloat("valor"), itemsRS.getString("nombre"), itemsRS.getBoolean("activo"), Categoria.getCategoria(itemsRS.getInt("categoria"))));
            }
        } else if ("Combo".equals(c.getNombre())) {

        } else if ("Platillos de entrada".equals(c.getNombre())) {
            ResultSet itemsRS = Conexion.consultar("SELECT * FROM Item,Categoria,Platillo where Item.id=Platillo.item and Item.categoria=Categoria.id and Categoria.nombre=\""+c.getNombre()+"\"");
            while (itemsRS.next()) {
                lista.add(new Platillo(itemsRS.getTime("tiempoEstimado"),itemsRS.getInt("id"), itemsRS.getFloat("valor"), itemsRS.getString("nombre"), itemsRS.getBoolean("activo"), Categoria.getCategoria(itemsRS.getInt("categoria"))));
            }
        } else if ("Platos Fuerte".equals(c.getNombre())) {
            ResultSet itemsRS = Conexion.consultar("SELECT * FROM Item,Categoria,Platillo where Item.id=Platillo.item and Item.categoria=Categoria.id and Categoria.nombre=\""+c.getNombre()+"\"");
            while (itemsRS.next()) {
                lista.add(new Platillo(itemsRS.getTime("tiempoEstimado"),itemsRS.getInt("id"), itemsRS.getFloat("valor"), itemsRS.getString("nombre"), itemsRS.getBoolean("activo"), Categoria.getCategoria(itemsRS.getInt("categoria"))));
            }
        } else if ("Postres".equals(c.getNombre())) {
            ResultSet itemsRS = Conexion.consultar("SELECT * FROM Item,Categoria,Platillo where Item.id=Platillo.item and Item.categoria=Categoria.id and Categoria.nombre=\""+c.getNombre()+"\"");
            while (itemsRS.next()) {
                lista.add(new Platillo(itemsRS.getTime("tiempoEstimado"),itemsRS.getInt("id"), itemsRS.getFloat("valor"), itemsRS.getString("nombre"), itemsRS.getBoolean("activo"), Categoria.getCategoria(itemsRS.getInt("categoria"))));
            }
        }
        return lista;
    }
    
    public void setVentana() throws SQLException, ParseException{
        this.items = new LinkedList<>();
        this.items = this.getItems(this.categoria);
        items1 = FXCollections.observableArrayList();

        this.items.forEach((temp) -> {
            HBox hbox1 = new HBox();
            VBox temp1 = new VBox();
            Label platillo = new Label(temp.getId()+".-"+temp.getNombre());
            platillo.setOnMouseClicked((event) -> {
                if (temp1.getChildren().get(1).isVisible()) {
                    temp1.getChildren().get(1).setVisible(false);
                } else {
                    temp1.getChildren().get(1).setVisible(true);
                }
            });
            TextArea ta = new TextArea();
            ta.setVisible(false);
            ta.setMaxSize(200, 10);
            temp1.getChildren().addAll(platillo, ta);
            TextField tf1 = new TextField();
            tf1.setMaxSize(40, 40);
            tf1.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                if (!newValue.matches("\\d*")) {
                    tf1.setText(newValue.replaceAll("[^\\d]", ""));
                }
            });
            tf1.setText("0");
            hbox1.getChildren().addAll(temp1, tf1);
            hbox1.setSpacing(290);
            items1.add(hbox1);
        });
        this.mesero3ListViewItems.setItems(items1);
    }
    public void setCuentaMesa(String mesa,String cuenta,HashMap<Item, LinkedList<Object>> pedido ){
        this.mesa.setText("Mesa #:"+ mesa);
        this.cuenta.setText("Cuenta #:"+cuenta);   
        this.pedido=pedido;
    }
}
