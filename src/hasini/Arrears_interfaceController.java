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
import handlers.DynamicTable;
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
    private JFXTextField e_lname;
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
        
        nbconn=new dbConcurrent();
        
         Integrator.integrate(anchorpane);
         
        //initializeNodes();
        //initializeRadioButtons();
        initializePersonInputs();
        initializeArrearsInputs();
        initializeButton();
        
     /*   DynamicTable.getColumns(nbconn.get() , "select p.nic, p.full_name  , s.share_amount, s.share_price, s.share_range_start, s.share_range_close\n" +
"from person p ,shareholder s where p.nic=s.nic" , man_table);*/
         
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
                return personCont.validateValues();
            }

    private void initializeButton() {
        
        
        Commons.subAnchorButton asab = new Commons.subAnchorButton();
        asab.setButtonLength(200);
        JFXButton addButton = asab.getButton(anchor_edit, "ADD Arrears", Commons.ADD_PERSON_GLYPH);
        
           addButton.setOnAction(new EventHandler<ActionEvent>() {
               
               
            @Override
            public void handle(ActionEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                
                Entity person = getPersonInputs();
                System.out.println(person.validate(true));
                person.consolidate();
                
                
                Entity arrears = getarrearsInputs();
                System.out.println(arrears.validate(true));
                arrears.consolidate();
            }  
        });
    }

    private void initializeArrearsInputs() {
        
         arrearsCont = new EntityControls("arrears",nbconn);
         
         ValidationHandler.NICValidator.register(e_nic);
        arrearsCont.add("nic", e_nic);
        
    }

    private void initializePersonInputs() {
        
        personCont= new EntityControls("person", nbconn);
        
        ValidationHandler.NICValidator.register(e_nic);
        personCont.add("nic", e_nic);
        
        personCont.add("full_name", e_lname);
     
        //ValidationHandler.PhoneValidator.register(a_phone);
        //personCont.add("phone", a_phone);
        
        personCont.add("address", e_address);
    }
    
}
