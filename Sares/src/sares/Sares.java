/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sares;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import sares.Controller.CajeroController;

/**
 *
 * @author mdleiton
 */
public class Sares extends Application {
    public static Stage stage1;
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/Sesion.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("fxml/login.fxml"));
        Scene scene = new Scene(root, 600, 400);
        stage1=primaryStage;
        stage1.setScene(scene);
        stage1.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static <T> T setContent(String fxml,Node n) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Sares.class.getClassLoader().getResource(fxml));
        Parent tableViewParent = loader.load();
        T controlador = loader.getController();
        Stage nueva = (Stage)n.getScene().getWindow();
        nueva.setScene(new Scene(tableViewParent));
        nueva.show();
        return controlador;
}
    
    
           
}
