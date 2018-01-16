/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sares;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

/**
 *
 * @author mdleiton
 */
public class Sares extends Application {
    public static Stage stage1;
    @Override
    public void start(Stage primaryStage) throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("fxml/login.fxml"));
        Scene scene = new Scene(root, 500, 500);
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
    
    public static <T> T setContent(String fxml, Node n) throws IOException {
        FXMLLoader fm = new FXMLLoader(Sares.class.getClassLoader().getResource(fxml));
        Parent root2 = fm.load();
        Stage stg = (Stage) n.getScene().getWindow();
        
        T controller = fm.getController();
        
        
        stg.setScene(new Scene(root2));
        
        return controller;
        
        
    }
    
}
