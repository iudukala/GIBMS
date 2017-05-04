/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml_files;

import core.Integrator;
import guiMediators.Commons;
import guiMediators.EntityControls;
import guiMediators.tableViewHandler;
import handlers.dbConcurrent;
import core.Entity;
import handlers.ValidationHandler.*;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ToggleGroup;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.scene.control.Toggle;
/**
 * FXML Controller class
 *
 * @author Isuru Udukala
 */
public class CustomerController implements Initializable
{
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
    @FXML
    private JFXTextField text_profession;
    @FXML
    private JFXRadioButton rb_permanent;
    @FXML
    private JFXRadioButton rb_temporary;
    @FXML
    private TableView<Entity> table_customer_search;
    @FXML
    private JFXButton btn_custsearch;
    @FXML
    private AnchorPane anchor_customer;
    @FXML
    private JFXTextField text_csnic;
    @FXML
    private JFXTextField text_csname;
    
    //TAB ADD LOAN
    @FXML
    private AnchorPane subanchor_tla;
    @FXML
    private JFXTextField text_lamount;
    @FXML
    private JFXTextField text_lexppayback;
    @FXML
    private JFXTextField text_lbank;
    @FXML
    private JFXTextField text_lbranch;
    @FXML
    private JFXTextField text_laccount;
    @FXML
    private JFXTextField text_lg1nic;
    @FXML
    private JFXTextField text_lg1name;
    @FXML
    private JFXDatePicker date_lg1dob;
    @FXML
    private JFXTextField text_lg1address;
    @FXML
    private JFXTextField text_lg1phone;
    @FXML
    private JFXRadioButton rb_lg1male;
    @FXML
    private ToggleGroup tgroup_lg1gender;
    @FXML
    private JFXRadioButton rb_lg1female;
    @FXML
    private JFXTextField text_lg1income;
    @FXML
    private JFXRadioButton rb_lmonthly;
    @FXML
    private ToggleGroup toggle_paybackmode;
    @FXML
    private JFXRadioButton rb_lpaycheck;
    @FXML
    private JFXRadioButton rb_lbank;
    @FXML
    private JFXTextField text_lg2nic;
    @FXML
    private JFXTextField text_lg2name;
    @FXML
    private JFXDatePicker date_lg2dob;
    @FXML
    private JFXTextField text_lg2address;
    @FXML
    private JFXTextField text_lg2phone;
    @FXML
    private JFXRadioButton rb_lg2male;
    @FXML
    private ToggleGroup tgroup_lg2gender;
    @FXML
    private JFXRadioButton rb_lg2female;
    @FXML
    private JFXTextField text_lg2income;
    @FXML
    private JFXButton btn_lcidsearch;
    @FXML
    public JFXTextField text_lcid;
    @FXML
    private JFXTextField text_linterest;
    
    //dummy function acting as seperator between autogenerated @FXML tags and code
    private void seperatorFunc(){}
    
    private EntityControls personControls,customerControls, spouseControls;
    private EntityControls loanControls;
    
    private tableViewHandler custable_handle;
    private dbConcurrent nbconn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        nbconn = new dbConcurrent();
        
        initializeCustomerControls();
        initializeLoanControls();
    }
    
    public void customer_add_button()
    {
        Entity person ,customer, spouse;
        try
        {
            person = personControls.getValues();
            customer = customerControls.getValues();
            
            person.validate(true);
            customer.validate(true);

            int p = person.consolidate();
            int c = customer.consolidate();
            int s;
            
            if(!spouseControls.isDisabled())
            {
                spouse = spouseControls.getValues();
                spouse.add("customer_id", customer.getAGKey());
                spouse.validate(true);
                s = spouse.consolidate();
            }
            else
                s = 0;
            
            if(p == 0 && c == 0 && s ==0)
            {
                personControls.clearControls();
                customerControls.clearControls();
                spouseControls.clearControls();
            }
        }
        catch(Exception e)
        {
            System.out.println("nullpointer no inputs\n" + e);
//                    JFXDialog dialog = new JFXDialog();
//                    dialog.setContent(new Label("Content"));
//                    dialog.setTranslateY(-500);
//                    dialog.show(stack_add);
        }
    }
    
    public void initializeCustomerControls()
    {
        personControls = new EntityControls("person",nbconn);
        new ExistingNICValidator(nbconn).register(text_nic);
        personControls.add("nic", text_nic, new NICValidator());
        personControls.add("full_name", text_fullname);
        personControls.add("email", text_email, new EmailValidator());
        personControls.add("dob", date_dob, new birthdayValidator());
        personControls.add("phone", text_pphone, new PhoneValidator());
        personControls.add("address", text_haddress);
        personControls.add("gender", tgroup_gender);
        personControls.add("marital_status", tgroup_marital);
    
        
        customerControls = new EntityControls("customer_state",nbconn);
        customerControls.add("nic", text_nic);
        customerControls.add("work_phone", text_wphone, new PhoneValidator());
        customerControls.add("emp_sector", tgroup_sector);
        customerControls.add("company", text_company);
        customerControls.add("position", text_position);
        customerControls.add("emp_startdate", date_empl_start, new pastDateValidator());
        customerControls.add("service_nature",tgroup_servicetype);
        customerControls.add("profession", text_profession);
        customerControls.add("account_num", text_accnum);
        customerControls.add("account_branch", text_accbranch);
        customerControls.add("account_bank", text_accbank);
        customerControls.add("earn_career", text_earncareer, new IntegerValidator());
        customerControls.add("earn_business", text_earnbusiness, new IntegerValidator());
        customerControls.add("earn_houses", text_earnhouses, new IntegerValidator());
        customerControls.add("earn_vehicles", text_earnvehicles, new IntegerValidator());
        customerControls.add("earn_land", text_earnland, new IntegerValidator());
    
        
        spouseControls = new EntityControls("spouse", nbconn);
        spouseControls.add("nic", text_spnic, new NICValidator());
        spouseControls.add("name", text_spname);
        spouseControls.add("profession", text_spprofession);
        spouseControls.add("earn_career", text_spcareer, new IntegerValidator());
        spouseControls.add("earn_business", text_spbusiness, new IntegerValidator());
        spouseControls.add("earn_houses", text_sphouses, new IntegerValidator());
        spouseControls.add("earn_vehicles", text_spvehicles, new IntegerValidator());
        spouseControls.add("earn_land", text_spland, new IntegerValidator());
        spouseControls.disable();
        
        
        rb_male.setUserData("M");
        rb_female.setUserData("F");
        
        rb_married.setUserData("M");
        rb_notmarried.setUserData("N");
        
        rb_state.setUserData("S");
        rb_statecorp.setUserData("C");
        rb_selfemployed.setUserData("E");
        
        rb_permanent.setUserData("P");
        rb_temporary.setUserData("T");
        
        
        tgroup_marital.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
        {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue)
            {
                if(newValue!=null)
                {
                    if(newValue.getUserData().equals("M"))
                        spouseControls.enable();
                    else
                        spouseControls.disable();
                }
            }
        });
        
        
        JFXTabPane jfxtabpane_customer = Integrator.integrate(anchor_customer);
        
        //add person button
        Commons.subAnchorButton apsab = new Commons.subAnchorButton(subanchor_tca, "ADD PERSON", Commons.ADD_PERSON_GLYPH);apsab.setButtonLength(160);
        JFXButton addpersonButton = apsab.getButton();
        
        Commons.subAnchorButton alsab = new Commons.subAnchorButton(subanchor_tla, "ADD LOAN REQUEST", Commons.ADD_PERSON_GLYPH);alsab.setButtonLength(200);
        JFXButton addloanButton = alsab.getButton();
        
        addpersonButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                customer_add_button();
            }
        });
        
        //setting up customer search table
        
        custable_handle = new tableViewHandler(table_customer_search, "select * from customer_view", nbconn);
        jfxtabpane_customer.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
                //second tab has search
                if((int)newValue == 2)
                    custable_handle.writeToTable();
            }
        });
        
        btn_custsearch.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                //String snic = text_csnic.getText();
                //String sname = text_csname.getText();
                Entity x = custable_handle.fetchExtendedSelection("person", "NIC");
                Entity y = custable_handle.fetchExtendedSelection("customer_state", "NIC");
                
                
                //System.out.println(x);
                //System.out.println(y);
                personControls.setValues(x);
                customerControls.setValues(y);
                jfxtabpane_customer.getSelectionModel().select(0);
            }
        });
    }
    
    private void initializeLoanControls()
    {
        loanControls = new EntityControls("loanplan", nbconn);
        loanControls.add(new Object[][]
        {
            {"customer_id", text_lcid},
            {"amount", text_lamount},
            {"interest", text_linterest},
            {"payback_duration", text_lexppayback},
            {"payback_method", toggle_paybackmode},
            {"bank", text_lbank},
        });
        
        
        btn_lcidsearch.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
            }
        });
    }
}
