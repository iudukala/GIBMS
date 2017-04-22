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
    @FXML
    private AnchorPane anchor_update;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        nbconn = new dbConcurrent();
        
        Integrator.integrate(anchor_shareholder, tabpane_shareholder);
        JFXButton addButton = Commons.setSubanchorButton(anchor_shares, "ADD SHAREHOLDER",200,15,400,600,Commons.ADD_PERSON_GLYPH);
        Commons.setSubanchorButton(anchor_update, "UPDATE SHAREHOLDER", 220,20,670,550,Commons.UPDATE_GLYPH);
        
        initializePersonInputs();
        initializeShareholderInputs();
        
        addButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                Entity person = getPersonInputs();
                System.out.println(person.validate(true));
                person.consolidate();
                
                
                Entity shareholder = getShareholderInputs();
                System.out.println(shareholder.validate(true));
                shareholder.consolidate();
            }
        });
    }
    
    public Entity getPersonInputs()
    {
        return personCont.getValues();
    }
    
    public Entity getShareholderInputs()
    {
        return shareholderCont.getValues();
    }
    
    public void initializePersonInputs()
    {
        personCont= new EntityControls("person", nbconn);
        
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
        shareholderCont = new EntityControls("shareholder",nbconn);
         
        ValidationHandler.NICValidator.register(a_nic);
        shareholderCont.add("nic", a_nic);
        
        ValidationHandler.IntegerValidator.register(a_shareamount);
        shareholderCont.add("share_amount", a_shareamount);
        
        
        ValidationHandler.DoubleValidator.register(a_shareprice);
        shareholderCont.add("share_price", a_shareprice);
        
        ValidationHandler.IntegerValidator.register(a_accountno);
        shareholderCont.add("account_no", a_accountno);
        
        shareholderCont.add("bank_name", a_bankname);
        
        shareholderCont.add("share_range_start", a_dateofissue);
        
        shareholderCont.add("share_range_close", a_dateofexpire);
     }
}