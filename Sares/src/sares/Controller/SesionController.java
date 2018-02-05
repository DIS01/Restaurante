package sares.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import sares.Model.Cajero;
import sares.Model.Conexion;
import sares.Model.Mesero;
import sares.Sares;

/**
 * FXML Controller class
 *
 * @author LULANB
 */
public class SesionController implements Initializable {

    @FXML
    private ImageView imageLogo;
    @FXML
    private TextField user;
    @FXML
    private PasswordField password;
    @FXML
    private Button btnIngresar;
    
    final int ADMINISTRADOR = 1;
    final int MESERO = 2;
    final int CAJERO = 3;
    final int COCINERO = 4;

    Connection conexionConDB;
    
    ResultSet usuario;
    @FXML
    private Label wrongUser;
    @FXML
    private Label wrongPassword;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.wrongUser.setVisible(false);
        this.wrongPassword.setVisible(false);
    }    
    
    private void pruebaIngresoCredenciales() throws SQLException{
        this.conexionConDB = Conexion.getConexion();
        CallableStatement statement = conexionConDB.prepareCall("{call iniciarSesion(?,?)}");
        statement.setString(1,user.getText()); 
        statement.setString(2,password.getText()); 
        statement.execute();
        this.usuario = statement.getResultSet();
    }

    private void accederVistaUsuario(int usuarioRol) throws SQLException, IOException{
        switch(usuarioRol){
            case ADMINISTRADOR:
                System.out.println("administrador");
                break;
            case MESERO:
                Mesero mesero= Mesero.getMesero(usuario.getInt("persona"));
                MeseroController control = (MeseroController)Sares.setContent("sares/fxml/Mesero.fxml", btnIngresar);
                control.ControllerCreate(mesero);
                break;
            case CAJERO:
                Cajero c= Cajero.getInformacionCajero(usuario.getInt("persona"));
                CajeroController controlc = (CajeroController)Sares.setContent("sares/fxml/Cajero.fxml", btnIngresar);
                controlc.ControllerCreate(c);
                break;
            case COCINERO:
                System.out.println("cocinero");
                break;
            default:
                System.out.println("Usuario no tiene acceso al sistema");
            }
    }
    
    private void verifyPassword() throws SQLException, IOException{
            if(this.password.getText().equals(usuario.getString("contrasena"))){
                int usuarioRol = this.usuario.getInt("rol");
                accederVistaUsuario(usuarioRol);
            }
            else{
                this.wrongPassword.setVisible(true);
            }
    }
    
    @FXML
    private void onMouseClickedBtnIngresar(MouseEvent event) throws SQLException, IOException {
        pruebaIngresoCredenciales();
        
        if(usuario.next()){   
            verifyPassword();
        }
        else{
            this.wrongUser.setVisible(true);
        }
    }

    private void hideWarning(){
        this.wrongUser.setVisible(false);
        this.wrongPassword.setVisible(false);
    }

    @FXML
    private void onSelectToInputUser(MouseEvent event) {
        this.user.setText("");
        hideWarning();
    }

    @FXML
    private void onSelectToInputPassword(MouseEvent event) {
        this.password.setText("");
        hideWarning();
    }
}
