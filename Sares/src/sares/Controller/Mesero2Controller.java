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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import sares.zDiagramaModel.Bebida;
import sares.Model.Categoria;
import sares.Model.Conexion;
import sares.Model.Empleado;
import sares.Model.Item;
import sares.Model.Mesero;
import sares.Model.Platillo;
import sares.Sares;

/**
 * FXML Controller class
 *
 * @author steevenrodriguez
 */
public class Mesero2Controller implements Initializable {
private Mesero mesero;
private Calendar time;
int cont;

//    public Mesero2Controller(Empleado empleado) {
//        this.empleado = empleado;
//    }
    @FXML
    private VBox root;
    @FXML
    private HBox hbox;
    @FXML
    private Label nombre;
    @FXML
    private Label tiempo;
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
    try {
        // TODO
        //this.nombre.setText(this.empleado.getNombres() + " "+ this.empleado.getApellidos());
        //this.tiempo.setText(time.getTime().toString());
        this.time = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
        this.tiempo.setText(sdf1.format(this.time.getTime()));
        this.categoria= new LinkedList<>();
        this.categoria= this.getCategorias();
        ObservableList<String> items = FXCollections.observableArrayList();
        this.categoria.forEach((temp) -> {
            items.add(temp.getNombre());
        });
        this.menu_Options.setItems(items);
        
        
        
        
        this.hbox.setSpacing(100);
        Thread thread = new Thread(new Runnable() {
            @Override

            public void run() {
                while (cont != 100) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MeseroController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Platform.runLater(new Runnable() {

                        public void run() {
                            Mesero2Controller.this.time = Calendar.getInstance();
                            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                            Mesero2Controller.this.tiempo.setText(sdf.format(Mesero2Controller.this.time.getTime()));
                            
                            
                            
                        }
                    });
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        
        
    } catch (SQLException ex) {
        Logger.getLogger(Mesero2Controller.class.getName()).log(Level.SEVERE, null, ex);
    }
    }    

    @FXML
    private void handleSignOutActionhandleSignOutAction(MouseEvent event) throws IOException {
        System.out.println("SignOut");
        Sares.setContent("sares/fxml/login.fxml", root);
    }

    @FXML
    private void seleccionMenu(MouseEvent event) {
    }

    @FXML
    private void handleGoBack(MouseEvent event) {
    }
    public void setList(LinkedList<Categoria> list){
        this.categoria = new LinkedList<>();
        this.categoria= list;
    }
    public void assignMesero(Mesero mesero){
        this.mesero = new Mesero();
        this.mesero=mesero;
        this.nombre.setText(this.mesero.getNombre());
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
            //cate.setListItems(this.getItems(cate,categoriasRS.getInt("id"),c));
            lista.add(cate);
        }
        categoriasRS.close();
        return lista;
    }
    
}
