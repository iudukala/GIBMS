/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml_files;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import integrator.*;
import javafx.scene.Scene;
/**
 * FXML Controller class
 *
 * @author Isuru Udukala
 */
public class CustomerController implements Initializable
{
    @FXML private Tab tab_customer_add;
    @FXML private Tab tab_customer_search;
    @FXML private AnchorPane anchor_customer;
    private JFXButton butt;
    
    JFXTabPane customer_tabpane;
    @FXML
    private TabPane tabpane_customer;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        InitializeIntegrator.initializeTabs(anchor_customer, tabpane_customer);
    }
}
