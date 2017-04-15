/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml_files;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.svg.SVGGlyph;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Isuru Udukala
 */
public class LoginController implements Initializable {

    @FXML
    private StackPane stackpane;
    @FXML
    private JFXButton button;
    
    @FXML
    private JFXTextField user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        JFXDepthManager.setDepth(stackpane, 5);
        SVGGlyph glyph = new SVGGlyph(1,"navigation","M12 2L4.5 20.29l.71.71L12 18l6.79 3 .71-.71z",Color.WHITE);
        glyph.setSize(15, 20);
        
        
        
    }
}
