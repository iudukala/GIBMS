/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battibois;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Isuru Udukala
 */
public class ResourceFXMLController implements Initializable {

    @FXML
    private Tab tab_customer_add;
    @FXML
    private ScrollPane scroll_add;
    @FXML
    private StackPane stack_add;
    @FXML
    private JFXTextField text_nic;
    @FXML
    private Tab tab_customer_add1;
    @FXML
    private ScrollPane scroll_add1;
    @FXML
    private StackPane stack_add1;
    @FXML
    private JFXTextField text_nic1;
    @FXML
    private Tab tab_customer_add2;
    @FXML
    private ScrollPane scroll_add2;
    @FXML
    private StackPane stack_add2;
    @FXML
    private JFXTextField text_nic2;
    @FXML
    private Tab tab_customer_add3;
    @FXML
    private ScrollPane scroll_add3;
    @FXML
    private StackPane stack_add3;
    @FXML
    private JFXTextField text_nic3;
    @FXML
    private AnchorPane anchor_resource;
    @FXML
    private TabPane tabpane_resource;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        core.Integrator.integrate(anchor_resource, tabpane_resource);
        // TODO
    }    
    
}
