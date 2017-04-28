/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hassim;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Isuru Udukala
 */
public class FXMLTemplateController implements Initializable {

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private TabPane tabpane;
    @FXML
    private JFXDatePicker date_dob;
    @FXML
    private JFXRadioButton rb_married1;
    @FXML
    private ToggleGroup tgroup_marital1;
    @FXML
    private JFXRadioButton rb_notmarried1;
    @FXML
    private JFXRadioButton rb_married11;
    @FXML
    private ToggleGroup tgroup_marital11;
    @FXML
    private JFXRadioButton rb_notmarried11;
    @FXML
    private JFXRadioButton rb_married111;
    @FXML
    private ToggleGroup tgroup_marital111;
    @FXML
    private JFXRadioButton rb_notmarried111;
    @FXML
    private JFXRadioButton rb_married12;
    @FXML
    private ToggleGroup tgroup_marital12;
    @FXML
    private JFXRadioButton rb_notmarried12;
    @FXML
    private JFXDatePicker date_dob1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        core.Integrator.integrate(anchorpane);
    }    
    
}
