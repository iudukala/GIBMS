/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml_files;

import entities.Person;
import gibms.dbConnect;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


/**
 *
 * @author Isuru Udukala
 */
public class loginController implements Initializable
{
    @FXML private Button button;
    
    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        
        try
        {
            Scene main_gui = new Scene(FXMLLoader.load(getClass().getResource("main_gui.fxml")));
            Stage primaryStage =(Stage)button.getScene().getWindow();
            
            primaryStage.setTitle("Graduate Investment Limited");
            primaryStage.setResizable(false);
            primaryStage.setScene(main_gui);
            primaryStage.centerOnScreen();
            
            primaryStage.show();
        }
        catch (Exception e)
        {
            System.out.println("Stage setting exception:\n" + e);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
    }
}