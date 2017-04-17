/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml_files;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXSnackbar.SnackbarEvent;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.svg.SVGGlyph;
import integrator.Navigator;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Isuru Udukala
 */
public class LoginController implements Initializable {

    @FXML
    private StackPane stackpane;
    @FXML
    private JFXTextField user;
    @FXML
    private Label label;
    @FXML
    private JFXButton button_login;
    @FXML
    private AnchorPane rootAnchor;
    
    private JFXSnackbar snackbar;
    @FXML
    private JFXSpinner spinner;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        JFXDepthManager.setDepth(stackpane, 5);
        SVGGlyph glyph = new SVGGlyph(1,"navigation","M18 8h-1V6c0-2.76-2.24-5-5-5S7 3.24 7 6v2H6c-1.1 0-2 .9-2 2v10c0 1.1.9 2 2 2h12c1.1 0 2-.9 2-2V10c0-1.1-.9-2-2-2zm-6 9c-1.1 0-2-.9-2-2s.9-2 2-2 2 .9 2 2-.9 2-2 2zm3.1-9H8.9V6c0-1.71 1.39-3.1 3.1-3.1 1.71 0 3.1 1.39 3.1 3.1v2z",Color.WHITE);
        glyph.setSize(25, 30);
        label.setText("");
        label.setGraphic(glyph);
        
        spinner.setVisible(false);
        
        button_login.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e)
            {
                spinner.setVisible(true);
                
                Navigator.switchForm(rootAnchor, 0);
            }
        });
    }
}
