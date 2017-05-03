/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hassim;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import core.Entity;
import core.Integrator;
import guiMediators.Commons;
import guiMediators.EntityControls;
import guiMediators.tableViewHandler;
import handlers.ValidationHandler;
import handlers.dbConcurrent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import handlers.ValidationHandler. *;


/**
 * FXML Controller class
 *
 * @author Shamodh
 */
public class FXMLTemplateController implements Initializable {
    
    dbConcurrent nbconn;
    EntityControls empcont;
     JFXTabPane jfxtabpane_employee;
    private tableViewHandler emptable_handle;

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private TabPane tabpane;
    @FXML
    private JFXRadioButton rb_married1111;
    @FXML
    private ToggleGroup tgroup_marital1111;
    @FXML
    private JFXRadioButton rb_notmarried1111;
    @FXML
    private JFXRadioButton rb_married121;
    @FXML
    private ToggleGroup tgroup_marital121;
    @FXML
    private JFXRadioButton rb_notmarried121;
    @FXML
    private JFXDatePicker date_dob11;
    @FXML
    private JFXDatePicker date_dob21;
    @FXML
    private AnchorPane a_ap1;
    @FXML
    private JFXTextField a_empid;
    @FXML
    private JFXTextField a_jobtitle;
    @FXML
    private JFXTextField a_empname;
    @FXML
    private JFXTextField a_nic;
    @FXML
    private JFXTextField a_email;
    @FXML
    private JFXTextField a_address;
    @FXML
    private JFXDatePicker a_dob_date;
    @FXML
    private JFXRadioButton a_male_rb;
    @FXML
    private JFXRadioButton a_female_rb;
    @FXML
    private JFXTextField a_bankname;
    @FXML
    private JFXTextField a_accountholder;
    @FXML
    private JFXTextField a_tp1;
    @FXML
    private JFXTextField a_tp2;
    @FXML
    private JFXTextField a_accountnumber;
    @FXML
    private JFXDatePicker a_joiningdate_date;
    @FXML
    private JFXRadioButton a_married_rb;
    @FXML
    private JFXRadioButton a_notmarried_rb;
    @FXML
    private JFXTextField a_department;
    @FXML
    private JFXTextField a_inusarancenumber;
    @FXML
    private JFXTextField a_insuarancecompany;
    @FXML
    private JFXTextField u_address;
    @FXML
    private JFXTextField u_email;
    @FXML
    private JFXTextField u_empname;
    @FXML
    private JFXTextField u_empid;
    @FXML
    private ToggleGroup tgroup_sex;
    @FXML
    private ToggleGroup tgroup_marital;
    @FXML
    private JFXButton u_select;
    @FXML
    private JFXButton search;
    @FXML
    private TableView<?> u_employee_table;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        nbconn = new dbConcurrent();
         jfxtabpane_employee = Integrator.integrate(anchorpane);
        
        initializeaddbutton();
        initializeEmpinputs();
        initializeRadioButtons();
        initializeupdatebutton();
        select();
        search();

        
    } 
    
    public void initializeEmpinputs()
    {
        empcont=new EntityControls("employee_details",nbconn);
        empcont.add("empid",a_empid);
        empcont.add("empname", a_empname);
        empcont.add("nic",a_nic,new NICValidator());
        empcont.add("dob",a_dob_date, new birthdayValidator() );
        empcont.add("email",a_email,new EmailValidator() );
        empcont.add("job",a_jobtitle );
        empcont.add("sex",tgroup_sex );
        empcont.add("marital_status",tgroup_marital );
        empcont.add("tp1",a_tp1,new PhoneValidator() );
        empcont.add("address",a_address );
        empcont.add("department",a_department );
        empcont.add("joining_date",a_joiningdate_date,new pastDateValidator() );
        empcont.add("account_no",a_accountnumber );
        empcont.add("account_holder",a_accountholder );
        empcont.add("bank_name",a_bankname);
        empcont.add("insurance_no",a_inusarancenumber );
        empcont.add("insurance_company",a_insuarancecompany );
        empcont.add("tp2",a_tp2,new PhoneValidator());
    
    }
    
        public void initializeaddbutton()
    {
        Commons.subAnchorButton a_emp_button = new Commons.subAnchorButton(a_ap1,"ADD EMPLOYEE", Commons.ADD_PERSON_GLYPH);
        JFXButton addButton = a_emp_button.getButton();
        
        addButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                Entity emp=empcont.getValues();
                emp.validate(true);
                int E = emp.consolidate();
                
                if(E==0)
                {
                    empcont.clearControls();
                }
                
            }
            
        });
    }
        private void initializeRadioButtons()
    {
        a_male_rb.setUserData("M");
        a_female_rb.setUserData("F");
        
        a_married_rb.setUserData("M");
        a_notmarried_rb.setUserData("N");
    }
        
        
        
        public void initializeupdatebutton()
    {
        Commons.subAnchorButton u_emp_button = new Commons.subAnchorButton(a_ap1,"UPDATE EMPLOYEE", Commons.ADD_PERSON_GLYPH);
         u_emp_button.setCoordinates(150, 600);
         u_emp_button.setButtonLength(200);
        JFXButton updateButton = u_emp_button.getButton();
        
        updateButton.setOnAction(new EventHandler<ActionEvent>()
       {
        @Override
            public void handle(ActionEvent e)
            {
                Entity Employee;
                
             
            
            try{
            
                Employee = empcont.getValues();
                Employee.update();
                }
            catch(Exception ex)
            {
            System.out.println("nullpointer no inputs");
            }
            
            }
        });
    }
      public void select()
      {
            u_select.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                //String snic = text_csnic.getText();
                //String sname = text_csname.getText();
                
                //empcont.setValues(emptable_handle.getSelection("employee_details", "empid"));
                empcont.setValues(emptable_handle.getSelection());
                //customerControls.setValues(custable_handle.getSelection("customer_state", "NIC"));
                jfxtabpane_employee.getSelectionModel().select(0);
            }
        });
            
        
        
        
      }
      public void search()
      {
      emptable_handle=new tableViewHandler(u_employee_table,"select empid , empname , job , nic , address, tp1 from employee_details",nbconn);
      search.setOnAction(new EventHandler<ActionEvent>()
        
                {
            @Override
            public void handle(ActionEvent e)
            {  
                emptable_handle.writeToTable();
            }
                });
      }
      
     
}
