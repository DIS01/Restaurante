/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sares.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sares.Model.Cajero;
import sares.Model.Conexion;
import sares.Sares;

/**
 * FXML Controller class
 *
 * @author steevenrodriguez
 */
public class LoginController implements Initializable {

    @FXML
    private Label userName;
    @FXML
    private TextField textField_user;
    @FXML
    private Label Password;
    @FXML
    private PasswordField passField;
    @FXML
    private Label titulo;
    @FXML
    private Label notLog;
    @FXML
    private Button logButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onLogAction(ActionEvent event) throws IOException, SQLException {
        Conexion co=new Conexion();
        CallableStatement statement = co.getConexion().prepareCall("{call iniciarSesion(?,?)}");
        statement.setString(1,textField_user.getText()); 
        statement.setString(2,passField.getText()); 
        statement.execute();
        ResultSet usuario = statement.getResultSet();
        if (usuario.next()) {
            switch(usuario.getInt("rol")){
                case 1:
                    System.out.println("administrador");
                    break;
                case 2:
                    System.out.println("mesero");
                    ResultSet mesero = co.consultar("Select * From Persona where dni="+usuario.getInt("persona"));
                    mesero.next();
                    MeseroController control = (MeseroController)Sares.setContent("sares/fxml/Mesero.fxml", (Node)event.getSource());
                    control.meseroControllerCreate(mesero);
                    break;
                case 3:
                    Cajero c= Cajero.getInformacionCajero(usuario.getInt("persona"),co);
                    CajeroController controlc = (CajeroController)Sares.setContent("sares/fxml/Cajero.fxml", (Node)event.getSource());
                    controlc.setnombre(c);
                    break;
                case 4:
                    System.out.println("cocinero");
                    break;
                default:
                    System.out.println("Usuario no tiene acceso al sistema");
                }
            }else{
                System.out.println("Usuario no registrado");
            }   
        }
        
}
