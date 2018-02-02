
package sares.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
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
import org.controlsfx.control.textfield.TextFields;
import sares.Model.Categoria;
import sares.Model.Conexion;
import sares.Model.Cuenta;
import sares.Model.Item;
import sares.Model.Pedido;
import sares.Model.PedidoDetalle;
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
            TextFields.bindAutoCompletion(cuentaText, Cuenta.getCuentas());
        } catch (SQLException ex) {
            Logger.getLogger(Mesero2Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
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
        control.setCuentaMesa( cuentaText.getText(),pedido);
        control.setVentana();
           
    }
    
    public void setList(LinkedList<Categoria> list){
        this.categoria= list;
    }
    
    public LinkedList<Categoria> getCategorias() throws SQLException{
        int id=1;
        LinkedList<Categoria> lista = new LinkedList();
        Categoria cate;
        ResultSet categoriasRS = Conexion.consultar("SELECT * FROM Categoria order by id"); 
        while (categoriasRS.next()){
            cate= new Categoria();
            cate.setNombre(id+".-"+categoriasRS.getString("nombre"));
            lista.add(cate);
            id++;
        }
        return lista;
    }

    /**
     * @param pedido the pedido to set
     */
    public void setPedido(HashMap<Item,LinkedList<Object>> pedido) {
        this.pedido = pedido;
    }
    
     public void setCuentaMesa(String cuenta){
        this.cuentaText.setText(cuenta);
    }
    @FXML
    private void handleGoBack(MouseEvent event) throws SQLException, IOException {
        int cuentaID=Cuenta.insertarCuenta(Integer.parseInt(/*mesaText.getText())*/"s"),this.getMesero().getId());
        int pedidoID=Pedido.insertarPedido("en cola", cuentaID);
        float total=0.0f;
        int contador=0;
        Time temp;
        Time tiempo=new Time(0,0,0);
        for (HashMap.Entry<Item,LinkedList<Object>> entry : pedido.entrySet()) {
            total+=entry.getKey().getValor()*(Integer) entry.getValue().get(0);
            contador++;
            temp=tiempoPlatillo(entry.getKey());
            if(tiempo.compareTo(temp)<0){
                tiempo=temp;
            } 
            PedidoDetalle.insertarPedidoDetalleMesero(pedidoID,entry.getKey().getId() ,total,(Integer) entry.getValue().get(0),(String)entry.getValue().get(1).toString() );
        }
        Pedido.actualizarPedidoCuenta(cuentaID, pedidoID,getTime(tiempo,3*(contador-1)), total);
        MeseroController control = (MeseroController)Sares.setContent("sares/fxml/Mesero.fxml", buttonGoBack);
        control.meseroControllerCreate(this.getMesero());
    }
    
    public Time getTime(Time t1, int adicionalmin){
        Time t2 =Time.valueOf("00:"+adicionalmin+":00");
        return new Time(t1.getTime()+t2.getTime());
        
    }
    public static Time tiempoPlatillo(Item item) throws SQLException{
        if(item.getCategoria().getNombre().equals("Platillos de entrada") || item.getCategoria().getNombre().equals("Postres") || item.getCategoria().getNombre().equals("Platos Fuerte") ){
            ResultSet r=Conexion.consultar("Select * from Item,Platillo where Item.id=Platillo.item and Item.id="+item.getId());
            if (r.next()){
                return r.getTime("tiempoEstimado");
            }
        }
        return null;
    }
    
}
