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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import sares.Model.Categoria;
import sares.Model.Conexion;
import sares.Model.Item;
import sares.Sares;

/**
 * FXML Controller class
 *
 * @author steevenrodriguez
 */
public class Mesero2Controller extends MeseroController {
    
    private HashMap<Item,LinkedList<Object>> pedido=new  HashMap<>();
    
    @FXML
    private VBox root;
    @FXML
    private Pane pane_medio;
    @FXML
    private Label cuenta;
    @FXML
    private TextField cuentaText;
    @FXML
    private Label mesa;
    @FXML
    private TextField mesaText;
    @FXML
    private ListView<String> menu_Options;
    @FXML
    private Button buttonGoBack;
    
    private LinkedList<Categoria> categoria;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reloj();
        this.categoria= new LinkedList<>();
        try {
            this.categoria= this.getCategorias();
        } catch (SQLException ex) {
            Logger.getLogger(Mesero2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<String> items = FXCollections.observableArrayList();
        this.categoria.forEach((temp) -> {
            items.add(temp.getNombre());
        });
        this.menu_Options.setItems(items);
    }    

    @FXML
    private void seleccionMenu(MouseEvent event) throws IOException, SQLException, ParseException {
        int idcategoria= Integer.parseInt(this.menu_Options.getSelectionModel().getSelectedItem().split(".-")[0]);
        Mesero3Controller control = null;
        if ("Platillos de entrada".equals(this.menu_Options.getSelectionModel().getSelectedItem().split(".-")[1])) {
            control = (Mesero3Controller)Sares.setContent("sares/fxml/Mesero3.fxml", (Node)event.getSource());
        }else if("Platos Fuerte".equals(this.menu_Options.getSelectionModel().getSelectedItem().split(".-")[1])) {
            control = (Mesero3Controller)Sares.setContent("sares/fxml/Mesero3.fxml", (Node)event.getSource());
        }else if("Bebidas".equals(this.menu_Options.getSelectionModel().getSelectedItem().split(".-")[1])) {
            control = (Mesero3Controller)Sares.setContent("sares/fxml/Mesero3.fxml", (Node)event.getSource());
        }else if("Postres".equals(this.menu_Options.getSelectionModel().getSelectedItem().split(".-")[1])) {
            control = (Mesero3Controller)Sares.setContent("sares/fxml/Mesero3.fxml", (Node)event.getSource());
        }
        control.assignCategoria(Categoria.getCategoria(idcategoria));
        control.meseroControllerCreate(this.getMesero());
        control.setCuentaMesa(mesaText.getText(), cuentaText.getText(),pedido);
        control.setVentana();
            
    
    }
    
    public void setCuentaMesa(String mesa,String cuenta){
        this.cuentaText.setText(cuenta);
        this.mesaText.setText(mesa);
    }
    public void setList(LinkedList<Categoria> list){
        this.categoria= list;
    }
    
    public LinkedList<Categoria> getCategorias() throws SQLException{
        int id=1;
        Conexion c=new Conexion();
        LinkedList<Categoria> lista = new LinkedList();
        Categoria cate;
        ResultSet categoriasRS = c.consultar("SELECT * FROM Categoria order by id"); 
        while (categoriasRS.next()){
            cate= new Categoria();
            cate.setNombre(id+".-"+categoriasRS.getString("nombre"));
            lista.add(cate);
            id++;
        }
        categoriasRS.close();
        return lista;
    }

    /**
     * @param pedido the pedido to set
     */
    public void setPedido(HashMap<Item,LinkedList<Object>> pedido) {
        this.pedido = pedido;
    }
    
    @FXML
    private void handleGoBack(MouseEvent event) {
        for (HashMap.Entry<Item,LinkedList<Object>> entry : pedido.entrySet()) {
            System.out.println("Item : " + entry.getKey() + " Count : " + entry.getValue());
        }
    }
}
