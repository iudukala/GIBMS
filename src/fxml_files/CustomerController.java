/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml_files;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.svg.SVGGlyph;
import core.Entity;
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
import guiMediators.Commons;
import guiMediators.PersonControls;
import handlers.ValidationHandler;
import handlers.dbConcurrent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    private AnchorPane subanchor_tca;
    @FXML
    private JFXTextField text_fullname;
    @FXML
    private JFXDatePicker date_dob;
    @FXML
    private JFXTextField text_pphone;
    @FXML
    private JFXTextField text_email;
    @FXML
    private JFXTextField text_haddress;
    
    //customer controls
    @FXML
    private JFXTextField text_company;
    @FXML
    private JFXTextField text_position;
    @FXML
    private JFXDatePicker date_empl_start;
    @FXML
    private JFXTextField text_wphone;
    @FXML
    private JFXTextField text_accnum;
    @FXML
    private JFXTextField text_accbank;
    @FXML
    private JFXTextField text_accbranch;
    @FXML
    private JFXTextField text_earncareer;
    @FXML
    private JFXTextField text_earnbusiness;
    @FXML
    private JFXTextField text_earnhouses;
    @FXML
    private JFXTextField text_earnvehicles;
    @FXML
    private JFXTextField text_earnland;
    @FXML
    private JFXTextField text_spnic;
    @FXML
    private JFXTextField text_spname;
    @FXML
    private JFXTextField text_spprofession;
    @FXML
    private JFXTextField text_spcareer;
    @FXML
    private JFXTextField text_spbusiness;
    @FXML
    private JFXTextField text_sphouses;
    @FXML
    private JFXTextField text_spvehicles;
    @FXML
    private JFXTextField text_spland;
    
    PersonControls personCont;
    dbConcurrent nbconn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        //initiailizing database connection
        nbconn = new dbConcurrent();
        //initializing gui
        initializeNodes();
    }
    
    
    private void initializeNodes()
    {
        scroll_add.setPrefHeight(580);
        Integrator.integrate(anchor_customer, tabpane_customer);
        JFXDepthManager.setDepth(scroll_add, 2);
        
        JFXButton addButton = Commons.setSubanchorButton(subanchor_tca, "ADD PERSON", 180, Commons.ADD_PERSON_GLYPH);
        
        addButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                Entity person = getPersonInputs();
                if(person.validate(nbconn.get(), true))
                    person.consolidate(nbconn.get());
            }
        });
        
        initializeRadioButtons();
        initializePersonInputs();
    }
    
    public Entity getPersonInputs()
    {
        return personCont.getValues();
    }
    public void setPersonInputs(Entity person)
    {
        personCont.setValues(person);
    }
    
    public void initializePersonInputs()
    {
        personCont = new PersonControls();
        
        ValidationHandler.NICValidator.register(text_nic);
        personCont.add("nic", text_nic);
        
        personCont.add("full_name", text_fullname);
        
        ValidationHandler.EmailValidator.register(text_email);
        personCont.add("email", text_email);
        
        personCont.add("dob", date_dob);
        
        ValidationHandler.PhoneValidator.register(text_pphone);
        personCont.add("phone", text_pphone);
        
        personCont.add("address", text_haddress);
        
        personCont.add("gender", tgroup_gender);
        
        personCont.add("marital_status", tgroup_marital);
    }
    
    public void initializeCustomerInputs()
    {
        
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
}
