/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hasini;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import core.Entity;
import core.Integrator;
import guiMediators.Commons;
import guiMediators.EntityControls;
import handlers.ValidationHandler;
import handlers.dbConcurrent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Hasini Subasinghe
 */
public class Arrears_interfaceController implements Initializable {
    
    EntityControls personCont;
    EntityControls arrearsCont;
    EntityControls search_cont;
    EntityControls searchArrearsCont;
    EntityControls addArreasCont;
    EntityControls labelCont;
    
    //private tableViewHandler man_table;
    
    dbConcurrent nbconn;

    @FXML
    private AnchorPane anchorpane;
    
    @FXML
    private TabPane tabpane;
    
    @FXML
    private JFXButton e_search;
    
    @FXML
    private JFXTextField e_nic;
    
    @FXML
    private JFXTextField e_customerID;
    
    @FXML
    private JFXTextField e_fname;
    
    @FXML
    private JFXTextField e_address;
    
    @FXML
    private JFXTextField e_loanAmount;
    
    @FXML
    private JFXDatePicker e_loanDate;
    
    @FXML
    private JFXDatePicker e_dueDate;
    
    @FXML
    private JFXDatePicker e_lastPaymentDate;
    
    @FXML
    private JFXTextField e_lastPaymentAmount;
    
    @FXML
    private JFXTextField e_outstandingAmount;
    
    @FXML
    private AnchorPane anchor_man;
    
    @FXML
    private AnchorPane anchor_edit;
    
    @FXML
    private AnchorPane anchor_list;
    
    @FXML
    private TableView<?> man_table;
    

   /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        nbconn = dbConcurrent.getInstance();
        
        
         Integrator.integrate(anchorpane);
         
        initializeNodes();
        initializeRadioButtons();
        initializePersonInputs();
        initializeArrearsInputs();
        initializeButton();
        initializeLoanControls();
        //setarrearsInput();
       // setPersonInput();
        tabPane();
       
         
    }    
    
            private Entity getPersonInputs() {
                personCont.clearControls();
                return personCont.getValues();            
            }

            private Entity getarrearsInputs() {
                arrearsCont.clearControls();
                return arrearsCont.getValues();            
            }
            
            public void setPersonInputs(Entity person) {
                personCont.setValues(person);
            }
            
            public void setCustomerInputs(Entity shareholder){
                arrearsCont.setValues(shareholder);
            }
            
            public boolean validatePersonInputs(){
                return personCont.triggerValidators();
            }
            private EntityControls loanControls;

    private void initializeButton() {
        
        
        Commons.subAnchorButton asab = new Commons.subAnchorButton(anchor_edit, "ADD Arrears", Commons.ADD_PERSON_GLYPH);
        asab.setButtonLength(200);
        JFXButton addButton = asab.getButton();
        
           addButton.setOnAction(new EventHandler<ActionEvent>() {
               
               
            @Override
            public void handle(ActionEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                
               Entity person = personCont.getValues();
                person.validate(true);
                int p=person.consolidate();
                
                Entity arrears = arrearsCont.getValues();
                arrears.validate(true);
                int a=arrears.consolidate();
                
                if(p == 0 && a ==0)
                {
                
                personCont.clearControls();
                arrearsCont.clearControls();
                
                }
            }  
        });
    }

    private void initializeArrearsInputs() {
        
         arrearsCont = new EntityControls("arrears_edit",nbconn);
         new ValidationHandler.ExistingNICValidator(nbconn).register(e_nic);
     
        arrearsCont.add("customer_id",e_customerID);
        arrearsCont.add("nic", e_nic, new ValidationHandler.NICValidator());
        arrearsCont.add("full_name", e_fname);
        arrearsCont.add("address", e_address);
        arrearsCont.add("loan_amount",e_loanAmount);
        arrearsCont.add("loan_date",e_loanDate);
        arrearsCont.add("due_date",e_dueDate);
        arrearsCont.add("last_payment_date",e_lastPaymentDate);
        arrearsCont.add("last_payment_amount",e_lastPaymentAmount);
        arrearsCont.add("outstanding_amount",e_outstandingAmount);
        
    }

    private void initializePersonInputs() {
        
        personCont= new EntityControls("person", nbconn);
        personCont.add("nic", e_nic, new ValidationHandler.NICValidator());
        personCont.add("full_name", e_fname);
        personCont.add("address", e_address);
    }

    private void initializeLoanControls() {
        loanControls = new EntityControls("loanplan", nbconn);
        loanControls.add(new Object[][]
        {
            {"customer_id", e_customerID},
            {"amount", e_loanAmount},
    });
    }

    private void initializeNodes() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void initializeRadioButtons() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void tabPane() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
