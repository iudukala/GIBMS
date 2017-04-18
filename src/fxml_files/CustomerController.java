/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml_files;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;

import core.Integrator;
import handlers.ValidationHandler;
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
    @FXML
    private StackPane stack_add;
    @FXML
    private ScrollPane scroll_add;
    @FXML
    private JFXTextField text_nic;
    @FXML
    private ToggleGroup tgroup_gender;
    @FXML
    private ToggleGroup tgroup_marital;
    @FXML
    private ToggleGroup tgroup_servicetype;
    @FXML
    private ToggleGroup tgroup_sector;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        //initializing gui
        scroll_add.setPrefHeight(580);
        Integrator.integrate(anchor_customer, tabpane_customer);
        JFXDepthManager.setDepth(scroll_add, 3);
        
        ValidationHandler.NICValidator.register(text_nic);
    }
}
