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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import sares.Model.Categoria;
import sares.Model.Conexion;
import sares.Model.Item;
import sares.Model.Mesero;
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
private HashMap<Item,LinkedList<Object>> pedido = new HashMap<>();

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
    private void seleccionMenu(MouseEvent event) throws IOException, SQLException {
        if ("Platillos de entrada".equals(this.menu_Options.getSelectionModel().getSelectedItem())) {
            Categoria cat = new Categoria("Platillos de entrada");
            Mesero3Controller control = (Mesero3Controller)Sares.setContent("sares/fxml/Mesero3.fxml", (Node)event.getSource());
            control.assignCategoria(cat);
            control.assignMesero(this.mesero);
            control.setVentana();

            }

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

    @FXML
    private void handleSignOutAction(MouseEvent event) {
    try {
        System.out.println("SignOut");
        Sares.setContent("sares/fxml/Sesion.fxml", root);
    } catch (IOException ex) {
        Logger.getLogger(Mesero2Controller.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    /**
     * @param pedido the pedido to set
     */
    public void setPedido(HashMap<Item,LinkedList<Object>> pedido) {
        this.pedido = pedido;
    }
    
}
