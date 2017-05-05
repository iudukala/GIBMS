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
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import handlers.ValidationHandler.PhoneValidator;
import hassim.task;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ToggleGroup;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.control.TableView;
import javafx.scene.control.Toggle;
import javafx.scene.layout.StackPane;
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
    @FXML
    private ToggleGroup tgroup_paybackmode;
    
    //dummy function acting as seperator between autogenerated @FXML tags and code
    private void seperatorFunc(){}
    
    JFXDialog dialog;
    
    private EntityControls personControls,customerControls, spouseControls;
    private EntityControls loanControls, guar1Controls, guar2Controls;
    int LOGGED_BRANCH = 1;
    int MAXIMUM_LOAN_PAYBACK_DURATION = 48;
    
    private tableViewHandler custable_handle;
    private dbConcurrent nbconn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        nbconn = dbConcurrent.getInstance();
        
        initializeCustomerControls();
        initializeLoanControls();
        
        task_addperson.setOnSucceeded(new EventHandler<WorkerStateEvent>()
        {
            @Override
            public void handle(WorkerStateEvent event)
            {
//                int task_status = task_addperson.getValue();
//                switch (task_status)
//                {
//                    case 0:
//                        try{dialog.close();}catch(Exception ex){}
//                        dialog = initializeDialog(1, ContentFactory.getDialog("CONSOLIDATION SUCESSFUL", "Customer record added to database successfully", 1));
//                        dialog.show();
//                        break;
//                    case 1:
//                        try{dialog.close();}catch(Exception ex){}
//                        dialog = initializeDialog(1, ContentFactory.getDialog("REQUIRED FIELDS EMPTY", "Please fill out all required fields before adding a new record", 2));
//                        dialog.show();
//                        break;
//                    case 2:
//                        try{dialog.close();}catch(Exception ex){}
//                        dialog = initializeDialog(1, ContentFactory.getDialog("CONSOLIDATION FAILED", "New record addition failed due to an internal error", 2));
//                        dialog.show();
//                        break;
//                    default:
//                        break;
//                }
            }
        });
    }
    
    Task<Integer> task_addperson = new Task<Integer>()
    {
        @Override
        protected Integer call() throws Exception
        {
            System.out.println(task_addperson.isRunning());
            int person_stat = -1, customer_stat = -1, spouse_stat = -1;
            Entity person ,customer, spouse;
            
            if(personControls.triggerValidators() && customerControls.triggerValidators())
            {
                person = personControls.getValues();
                customer = customerControls.getValues();
            }
            else
                return 1;

            person_stat = person.consolidate();
            customer_stat = customer.consolidate();

            if(!spouseControls.isDisabled())
            {
                spouse = spouseControls.getValues();
                if(spouseControls.triggerValidators())
                {
                    spouse.add("customer_id", customer.getAGKey());
                    spouse.validate(true);
                    spouse_stat = spouse.consolidate();
                }
            }
            else
                spouse_stat = 0;
            
            if(person_stat == 0 && customer_stat == 0 && spouse_stat ==0)
            {
                personControls.clearControls();
                customerControls.clearControls();
                spouseControls.clearControls();
                return 0;
            }
            else
                return 2;
         }
    };
    
    public void initializeCustomerControls()
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
        
        
        personControls = new EntityControls("person",nbconn);
        new ExistingNICValidator(nbconn).register(text_nic);
        personControls.add(new Object[][]
        {
            {"nic", text_nic, new NICValidator()},
            {"full_name", text_fullname},
            {"email", text_email, new EmailValidator()},
            {"dob", date_dob, new birthdayValidator()},
            {"phone", text_pphone, new PhoneValidator()},
            {"address", text_haddress},
            {"gender", tgroup_gender},
            {"marital_status", tgroup_marital}
        });
    
        
        customerControls = new EntityControls("customer_state",nbconn);
        customerControls.add(new Object[][]
        {
            {"nic", text_nic},
            {"work_phone", text_wphone, new PhoneValidator()},
            {"emp_sector", tgroup_sector},
            {"company", text_company},
            {"position", text_position},
            {"emp_startdate", date_empl_start, new pastDateValidator()},
            {"service_nature",tgroup_servicetype},
            {"profession", text_profession},
            {"account_num", text_accnum},
            {"account_branch", text_accbranch},
            {"account_bank", text_accbank},
            {"earn_career", text_earncareer, new IntegerValidator()},
            {"earn_business", text_earnbusiness, new IntegerValidator()},
            {"earn_houses", text_earnhouses, new IntegerValidator()},
            {"earn_vehicles", text_earnvehicles, new IntegerValidator()},
            {"earn_land", text_earnland, new IntegerValidator()},
        });
        
        spouseControls = new EntityControls("spouse", nbconn);
        spouseControls.add(new Object[][]
        {
            {"nic", text_spnic, new NICValidator()},
            {"name", text_spname},
            {"profession", text_spprofession},
            {"earn_career", text_spcareer, new IntegerValidator()},
            {"earn_business", text_spbusiness, new IntegerValidator()},
            {"earn_houses", text_sphouses, new IntegerValidator()},
            {"earn_vehicles", text_spvehicles, new IntegerValidator()},
            {"earn_land", text_spland, new IntegerValidator()}
        });
        
        spouseControls.disable();
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
        Commons.subAnchorButton apsab = new Commons.subAnchorButton(subanchor_tca, "ADD CUSTOMER", Commons.ADD_PERSON_GLYPH);apsab.setButtonLength(160);
        JFXButton addpersonButton = apsab.getButton();
        
        Commons.subAnchorButton alsab = new Commons.subAnchorButton(subanchor_tla, "ADD LOAN REQUEST", Commons.ADD_PERSON_GLYPH);alsab.setButtonLength(200);
        JFXButton addloanButton = alsab.getButton();
        
        addpersonButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                Thread thread = new Thread(task_addperson);
                thread.setDaemon(true);
                thread.start();
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
                Entity x = custable_handle.fetchExtendedSelection("customer_state", "NIC");
                x.deleteFromDB();
                custable_handle.writeToTable();
                        
                //String snic = text_csnic.getText();
                //String sname = text_csname.getText();
//                Entity x = custable_handle.fetchExtendedSelection("person", "NIC");
//                Entity y = custable_handle.fetchExtendedSelection("customer_state", "NIC");
//                
//                
//                //System.out.println(x);
//                //System.out.println(y);
//                personControls.setValues(x);
//                customerControls.setValues(y);
//                jfxtabpane_customer.getSelectionModel().select(0);

            }
        });
    }
    
    private void initializeLoanControls()
    {
        rb_lmonthly.setUserData("M");
        rb_lpaycheck.setUserData("P");
        rb_lbank.setUserData("B");
        
        rb_lg1male.setUserData("M");
        rb_lg1female.setUserData("F");
        
        rb_lg2male.setUserData("M");
        rb_lg2female.setUserData("F");
        
        loanControls = new EntityControls("loanplan", nbconn);
        loanControls.add(new Object[][]
        {
            {"customer_id", text_lcid},
            {"amount", text_lamount, new DoubleValidator()},
            {"interest", text_linterest, new DoubleValidator()},
            {"payback_duration", text_lexppayback, new IntegerValidator(MAXIMUM_LOAN_PAYBACK_DURATION)},
            {"payback_method", tgroup_paybackmode},
            {"bank", text_lbank},
            {"branch", text_lbranch},
            {"account", text_laccount},
            {"guarantor1", text_lg1nic},
            {"guarantor1_income", text_lg1income, new DoubleValidator()},
            {"guarantor2", text_lg1nic},
            {"guarantor2_income", text_lg2income, new DoubleValidator()},
            {"branch_id", LOGGED_BRANCH}
        });
        
        guar1Controls = new EntityControls("person", nbconn);
        guar1Controls.add(new Object[][]
        {
            {"nic", text_lg1nic, new NICValidator()},
            {"full_name", text_lg1name},
            {"dob", date_lg1dob},
            {"phone", text_lg1phone, new PhoneValidator()},
            {"address", text_lg1address},
            {"gender", tgroup_lg1gender}
        });
        
        guar2Controls = new EntityControls("person", nbconn);
        guar2Controls.add(new Object[][]
        {
            {"nic", text_lg2nic, new NICValidator()},
            {"full_name", text_lg2name},
            {"dob", date_lg2dob},
            {"phone", text_lg2phone, new PhoneValidator()},
            {"address", text_lg2address},
            {"gender", tgroup_lg2gender}
        });
        
        
        EntityControls tmp_bankgroup = new EntityControls(null,null);
        tmp_bankgroup.add(new Object[][]
        {
            {"X",text_lbank},
            {"Y",text_lbranch},
            {"Z",text_laccount}
        });
        tmp_bankgroup.disable();
        tgroup_paybackmode.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
        {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue)
            {
                
                if(newValue!=null)
                {
                    if(newValue.getUserData().equals("B"))
                    {
                        tmp_bankgroup.enable();
                    }
                    else
                    {
                        tmp_bankgroup.disable();
                    }
                }
            }
        });
        /*
        loanid           | int(11)      | NO   | PRI | NULL              | auto_increment |
| customer_id      | int(11)      | NO   | MUL | NULL              |                |
| amount           | int(11)      | YES  |     | NULL              |                |
| interest         | double       | NO   |     | NULL              |                |
| payback_duration | int(11)      | YES  |     | NULL              |                |
| payback_method   | varchar(1)   | YES  |     | NULL              |                |
| bank             | varchar(200) | YES  |     | NULL              |                |
| branch           | varchar(200) | YES  |     | NULL              |                |
| account          | varchar(40)  | YES  |     | NULL              |                |
| guarantor1       | varchar(10)  | YES  |     | NULL              |                |
| guarantor2       | varchar(10)  | YES  |     | NULL              |                |
| timestamp        | datetime     | YES  |     | CURRENT_TIMESTAMP |   
        */
        
        
        btn_lcidsearch.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
            }
        });
    }
    
    private JFXDialog initializeDialog(int tabnum, JFXDialog dialog)
    {
        Control control;
        int translate = 0;
        switch(tabnum)
        {
            case 1:
                control = text_nic;
                translate = -445;
                break;
            case 2:
                control = text_lcid;
                translate = -445;
                break;
            case 3:
                control = text_csname;
                break;
            default:
                return null;
        }
        dialog.setDialogContainer((StackPane)control.getParent().getParent());
        dialog.setTranslateY(translate);
        
        return dialog;
    }
    
    private void toggleSpinner(JFXButton button, AnchorPane subanchor)
    {
//        addpersonButton.setText("");
//                addpersonButton.setGraphic(null);
//                
//                JFXSpinner spinner = new JFXSpinner();
//                subanchor_tca.getChildren().add(spinner);
//                System.out.println(spinner.getMinWidth());
//                spinner.setTranslateX(addpersonButton.getTranslateX() + addpersonButton.getWidth()/2 - spinner.getMinWidth()/2);
//                spinner.setTranslateY(addpersonButton.getTranslateY() + addpersonButton.getHeight()/2 - spinner.getMinHeight()/2);
//                System.out.println(task_addperson.isRunning());
    }
}
