package sares.Controller;

import java.io.IOException;
import java.net.URL;
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
import sares.Model.Item;
import sares.Sares;

/**
 * FXML Controller class
 *
 * @author steevenrodriguez
 */
public class Mesero3Controller extends Ventana {

    private Categoria categoria;    
    private LinkedList<Item> items;
    private ObservableList<HBox> items1;
    private HashMap<Item, LinkedList<Object>> pedido ;
    
    @FXML
    private VBox root;
    @FXML
    private HBox hbox;
    @FXML
    private Label mesero3LblNombre,cuenta;
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
                            LinkedList<Object> details = new LinkedList<>();
                            TextArea ta1 = (TextArea) vboxTemp.getChildren().get(1);
                            details.add(Integer.parseInt(tf.getText()));
                            details.add(ta1.getText());
                            this.pedido.put(item, details);
                        }
                    });
                }
            });
            Mesero2Controller control = (Mesero2Controller) Sares.setContent("sares/fxml/Mesero2.fxml", hbox);
            control.setPedido(pedido);
            control.ControllerCreate(this.getP());
            control.setCuentaMesa(cuenta.getText().split("#:")[1]);
        } catch (IOException ex) {
            Logger.getLogger(Mesero3Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void assignCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setVentana() throws SQLException, ParseException{
        this.items = new LinkedList<>();
        this.items = Categoria.getItems(this.categoria);
        items1 = FXCollections.observableArrayList();
        this.items.forEach((Item temp) -> {
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
            ta.setVisible(true);
            ta.setMaxSize(300, 8);
            temp1.getChildren().addAll(platillo, ta);
            TextField tf1 = new TextField();
            tf1.setMaxSize(50, 40);
            tf1.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                try{
                    if (!newValue.matches("\\d*")) {
                        tf1.setText(newValue.replaceAll("[^\\d]", ""));
                    }else if(Integer.parseInt(newValue)<0 || Integer.parseInt(newValue)>temp.getStock()){
                        this.BtnGuardar.setDisable(true);
                        tf1.setText("0");
                    }    
                        this.BtnGuardar.setDisable(false);
                }catch(NumberFormatException e){
                     this.BtnGuardar.setDisable(true);
                }
            });
            tf1.setText("0");
            Label stock=new Label();
            stock.setMaxSize(50, 40);
            stock.setText(" / "+temp.getStock());
            hbox1.getChildren().addAll(temp1, tf1,stock);
            hbox1.setSpacing(50);
            items1.add(hbox1);
        });
        this.mesero3ListViewItems.setItems(items1);
    }

    public void setCuentaMesa(String cuenta,HashMap<Item, LinkedList<Object>> pedido ){
       this.cuenta.setText("Cuenta #:"+cuenta);   
        this.pedido=pedido;
    }
    
    @FXML
    public void regresarCategorias(MouseEvent e) throws IOException{
        Mesero2Controller control = (Mesero2Controller) Sares.setContent("sares/fxml/Mesero2.fxml", this.BtnGuardar);
        control.setPedido(pedido);
        control.ControllerCreate(this.getP());
        control.setCuentaMesa(cuenta.getText().split("#:")[1]);
    }
}
