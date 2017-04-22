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
import guiMediators.EntityControls;
import handlers.ValidationHandler;
import handlers.ValidationHandler.PhoneValidator;
import handlers.dbConcurrent;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    
    EntityControls personControls;
    EntityControls customerControls;
    
    dbConcurrent nbconn;
    
    @FXML
    private JFXTextField text_profession;
    @FXML
    private JFXRadioButton rb_permanent;
    @FXML
    private JFXRadioButton rb_temporary;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        //initiailizing database connection
        nbconn = new dbConcurrent();
        //initializing gui
        initializeNodes();
        initializeRadioButtons();
        initializePersonInputs();
        initializeCustomerInputs();
    }
    
    
    private void initializeNodes()
    {
        scroll_add.setPrefHeight(580);
        Integrator.integrate(anchor_customer, tabpane_customer);
        JFXDepthManager.setDepth(scroll_add, 2);
        
        JFXButton addButton = Commons.setSubanchorButton(subanchor_tca, "ADD PERSON", 180,15, 400, 600, Commons.ADD_PERSON_GLYPH);
        
        addButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                Entity person ,customer;
                try
                {
                    person = getPersonInputs();
                    customer = getCustomerInputs();
                    
                    //System.out.println(person);
                    System.out.println(person.validate(true));
                    
                    //System.out.println(customer);
                    System.out.println(customer.validate(true));
                    
                    person.consolidate();
                    customer.consolidate();
                }
                catch(java.lang.NullPointerException exc)
                {
                    System.out.println("nullpointer no inputs");
//                    JFXDialog dialog = new JFXDialog();
//                    dialog.setContent(new Label("Content"));
//                    dialog.setTranslateY(-500);
//                    dialog.show(stack_add);
                }
            }
        });
    }
    
    public Entity getPersonInputs()
    {
        return personControls.getValues();
    }
    public void setPersonInputs(Entity person)
    {
        personControls.setValues(person);
    }
    public boolean validatePersonInputs()
    {
        return personControls.validateValues();
    }
    
    public Entity getCustomerInputs()
    {
        return customerControls.getValues();
    }
    public void setCustomerInputs(Entity customer)
    {
        customerControls.setValues(customer);
    }
    
    public void initializePersonInputs()
    {
        personControls = new EntityControls("person",nbconn);
        
        ValidationHandler.NICValidator.register(text_nic);
        personControls.add("nic", text_nic);
        
        personControls.add("full_name", text_fullname);
        
        ValidationHandler.EmailValidator.register(text_email);
        personControls.add("email", text_email);
        
        personControls.add("dob", date_dob);
        
        ValidationHandler.PhoneValidator.register(text_pphone);
        personControls.add("phone", text_pphone);
        
        personControls.add("address", text_haddress);
        
        personControls.add("gender", tgroup_gender);
        
        personControls.add("marital_status", tgroup_marital);
    }
    
    public void initializeCustomerInputs()
    {
        customerControls = new EntityControls("customer_state",nbconn);
    
        customerControls.add("nic", text_nic);
        
        PhoneValidator.register(text_wphone);
        customerControls.add("work_phone", text_wphone);
        
        customerControls.add("emp_sector", tgroup_sector);
        
        customerControls.add("company", text_company);
        
        customerControls.add("position", text_position);
        
        customerControls.add("emp_startdate", date_empl_start);
        
        customerControls.add("service_nature",tgroup_servicetype);
        
        customerControls.add("profession", text_profession);
        
        customerControls.add("account_num", text_accnum);
        
        customerControls.add("account_branch", text_accbranch);
        
        ValidationHandler.DoubleValidator.register(text_earncareer);
        customerControls.add("earn_career", text_earncareer);
        
        ValidationHandler.DoubleValidator.register(text_earnbusiness);
        customerControls.add("earn_business", text_earnbusiness);
        
        ValidationHandler.DoubleValidator.register(text_earnhouses);
        customerControls.add("earn_houses", text_earnhouses);
        
        ValidationHandler.DoubleValidator.register(text_earnvehicles);
        customerControls.add("earn_vehicles", text_earnvehicles);
        
        ValidationHandler.DoubleValidator.register(text_earnland);
        customerControls.add("earn_land", text_earnland);
        
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
        
        rb_permanent.setUserData("P");
        rb_temporary.setUserData("T");
    }
}
