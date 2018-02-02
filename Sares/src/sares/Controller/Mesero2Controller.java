
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
import sares.Model.Combo;
import sares.Model.Conexion;
import sares.Model.Cuenta;
import sares.Model.Item;
import sares.Model.Pedido;
import sares.Model.PedidoDetalle;
import sares.Model.Platillo;
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
    private ListView<String> listaitems;
    @FXML
    private Label tiempoEstimado;
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
        }else if("Combo".equals(this.menu_Options.getSelectionModel().getSelectedItem().split(".-")[1])) {
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
        int contador=0;
        float te=0.0f;
        this.pedido = pedido;
        this.listaitems.setItems(null);
        int tamano=pedido.size();
        LinkedList<String> list=new LinkedList<>();
        for (HashMap.Entry<Item,LinkedList<Object>> entry : pedido.entrySet()) {
            if(entry.getKey().getCategoria().getNombre().equals("Platillos de entrada") || entry.getKey().getCategoria().getNombre().equals("Platos Fuerte") || entry.getKey().getCategoria().getNombre().equals("Postres")){
                Platillo c=(Platillo)entry.getKey();
                if(te<c.getTiempoEstimado()){
                    te=c.getTiempoEstimado();
                }
                contador+=(Integer) entry.getValue().get(0);
            }else if(entry.getKey().getCategoria().getNombre().equals("Combo") ){
                Combo c=(Combo)entry.getKey();
                if(te<c.getTiempoEstimado()){
                    te=c.getTiempoEstimado();
                }
                contador+=(Integer) entry.getValue().get(0);
            }
            list.add(entry.getKey()+","+entry.getValue().get(0));
        }
        te=te+3*(contador-1);
        if(contador==0) te=0.0f;
        this.tiempoEstimado.setText("Tiempo: "+te);
        this.listaitems.setItems(FXCollections.observableList(list));
    }
    
     public void setCuentaMesa(String cuenta){
        this.cuentaText.setText(cuenta);
    }
     
    @FXML
    private void handleGoBack(MouseEvent event) throws SQLException, IOException {
        int cuentaID=Integer.parseInt(this.cuentaText.getText().split(",")[0]);
        int pedidoID=Pedido.insertarPedido("en cola", cuentaID);
        float total=0.0f;
        int contador=0;
        for (HashMap.Entry<Item,LinkedList<Object>> entry : pedido.entrySet()) {
            total+=entry.getKey().getValor()*(Integer) entry.getValue().get(0);
            contador++;
            PedidoDetalle.insertarPedidoDetalleMesero(pedidoID,entry.getKey().getId() ,total,(Integer) entry.getValue().get(0),(String)entry.getValue().get(1).toString(), (int) (entry.getKey().getStock()-(Integer) entry.getValue().get(0)));
        }
        Pedido.actualizarPedidoCuenta(cuentaID, pedidoID,Float.parseFloat(tiempoEstimado.getText().split(":")[1]), total);
        MeseroController control = (MeseroController)Sares.setContent("sares/fxml/Mesero.fxml", buttonGoBack);
        control.meseroControllerCreate(this.getMesero());
        control.mensajeExitoso("Registro correcto del pedido");
    }
  
}
