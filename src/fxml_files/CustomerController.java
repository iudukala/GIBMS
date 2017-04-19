/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml_files;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.svg.SVGGlyph;
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
import core.Manipulator;
import handlers.ValidationHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Toggle;
import javafx.scene.paint.Color;
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
    @FXML
    private JFXRadioButton rb_male;
    @FXML
    private JFXRadioButton rb_female;
    @FXML
    private JFXRadioButton rb_married;
    @FXML
    private JFXRadioButton rb_notmarried;
    @FXML
    private JFXRadioButton rb_state;
    @FXML
    private JFXRadioButton rb_statecorp;
    @FXML
    private JFXRadioButton rb_selfemployed;
    @FXML
    private JFXButton btn_addupdate;
    @FXML
    private AnchorPane subanchor_tca;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        //initializing gui
        initializeRadioButtons();
        initializeNodes();
        
        ValidationHandler.NICValidator.register(text_nic);
    }
    
    private void initializeRadioButtons()
    {
        rb_male.setUserData("M");
        rb_female.setUserData("F");
        
        rb_married.setUserData("M");
        rb_notmarried.setUserData("N");
        
        rb_state.setUserData("S");
        rb_statecorp.setUserData("C");
        rb_selfemployed.setUserData("E");
    }
    private void initializeNodes()
    {
        scroll_add.setPrefHeight(580);
        Integrator.integrate(anchor_customer, tabpane_customer);
        JFXDepthManager.setDepth(scroll_add, 2);
        
        
        JFXButton addButton = new JFXButton("ADD RECORD");
        SVGGlyph glyph = new SVGGlyph(1,"addperson", "M15 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm-9-"
                + "2V7H4v3H1v2h3v3h2v-3h3v-2H6zm9 4c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z", Color.WHITE);
        glyph.setSize(20,15);
        addButton.setGraphicTextGap(10);
        addButton.setGraphic(glyph);
        addButton.setPrefSize(180,50);
        addButton.setTranslateX(400);
        addButton.setTranslateY(600);
        addButton.getStyleClass().add("addbutton");
        subanchor_tca.getChildren().add(addButton);
        JFXDepthManager.setDepth(addButton, 5);
        
        
        Manipulator.setToggleSelection(tgroup_gender,"F");
        
        addButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                text_nic.getUserData();
            }
        });
    }
}
