/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gibms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Isuru Udukala
 */
public class GIBMS extends Application
{    
    @Override
    public void start(Stage stage) throws Exception
    {   Parent root = FXMLLoader.load(getClass().getResource("/fxml_files/login.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Graduate Investment system login");
        stage.setResizable(false);
        stage.setScene(scene);
        
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }   
}