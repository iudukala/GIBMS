/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiriya;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import core.Entity;
import core.Integrator;
import guiMediators.Commons;
import guiMediators.EntityControls;
//import guiMediators.PersonControls;
import handlers.ValidationHandler;
import handlers.dbConcurrent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ASUS-PC
 */
public class ShareManagementController implements Initializable {

    //PersonControls personCont;
    EntityControls personCont;
    EntityControls shareholderCont;
    dbConcurrent nbconn;
    
    @FXML
    private TabPane tabpane_shareholder;
    @FXML
    private AnchorPane anchor_shareholder;
    @FXML
    private AnchorPane anchor_shares;
    @FXML
    private JFXTextField a_nic;
    @FXML
    private JFXTextField a_fullname;
    @FXML
    private JFXTextField a_address;
    @FXML
    private JFXTextField a_phone;
    @FXML
    private JFXTextField a_email;
    @FXML
    private JFXDatePicker a_dob;
    @FXML
    private JFXTextField a_shareamount;
    @FXML
    private JFXTextField a_bankname;
    @FXML
    private JFXTextField a_shareprice;
    @FXML
    private JFXTextField a_accountno;
    @FXML
    private JFXDatePicker a_dateofissue;
    @FXML
    private JFXDatePicker a_dateofexpire;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        nbconn = new dbConcurrent();
        
        Integrator.integrate(anchor_shareholder, tabpane_shareholder);
       JFXButton addButton= Commons.setSubanchorButton(anchor_shares, "ADD SHAREHOLDER", 200, Commons.ADD_PERSON_GLYPH);
        
        addButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
              //  System.out.println(validatePersonInputs());
                
                Entity person = getPersonInputs();
                if(person.validate(nbconn.get(), true))
                    person.consolidate(nbconn.get());
                
                Entity shareholder = getCustomerInputs();
                if(shareholder.validate(nbconn.get(), true))
                    shareholder.consolidate(nbconn.get());
            }
        });
        
        
        
        initializePersonInputs();
        initializeShareholderInputs();
   }
    
        public Entity getPersonInputs()
    {
        return personCont.getValues();
    }
       
        public Entity getCustomerInputs()
    {
        return shareholderCont.getValues();
    }
        
    
     public void initializePersonInputs()
    {
        personCont= new EntityControls("person");
       
        
        ValidationHandler.NICValidator.register(a_nic);
        personCont.add("nic", a_nic);
        
        personCont.add("full_name", a_fullname);
        
        ValidationHandler.EmailValidator.register(a_email);
        personCont.add("email", a_email);
        
        personCont.add("dob", a_dob);
        
        ValidationHandler.PhoneValidator.register(a_phone);
        personCont.add("phone", a_phone);
        
        personCont.add("address", a_address);
        
    
    }
     public void initializeShareholderInputs()
     {
        shareholderCont= new EntityControls("shareholder");
         
        ValidationHandler.IntegerValidator.register(a_shareamount);
        shareholderCont.add("share_amount", a_shareamount);
        
        
        ValidationHandler.DoubleValidator.register(a_shareprice);
        shareholderCont.add("share_price", a_shareprice);
        
//        ValidationHandler.NumberValidator.register(a_accountno);
        shareholderCont.add("account_no", a_accountno);
//        
//        ValidationHandler.CharacterValidator.register(a_bankname);
        shareholderCont.add("bank_name", a_bankname);
        
        
        shareholderCont.add("share_range_start", a_dateofissue);
        
        shareholderCont.add("share_range_close", a_dateofexpire);
    
     
     
     }
    
}