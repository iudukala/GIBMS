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
import core.Validator;
import guiMediators.Commons;
import guiMediators.EntityControls;
import handlers.DynamicTable;
import handlers.ValidationHandler;
import handlers.dbConcurrent;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import legacy.Person;
import legacy.shareholder;
import legacy.shareholder_search;

/**
 * FXML Controller class
 *
 * @author ASUS-PC
 */
public class ShareManagementController implements Initializable {

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
    @FXML
    private JFXTextField u_fullname;
    @FXML
    private JFXTextField u_nic;
    @FXML
    private JFXDatePicker u_dob;
    @FXML
    private JFXTextField u_email;
    @FXML
    private JFXTextField u_phone;
    @FXML
    private JFXTextField u_address;
    @FXML
    private JFXDatePicker u_dateofexpire;
    @FXML
    private JFXDatePicker u_dateofissue;
    @FXML
    private JFXTextField u_accountno;
    @FXML
    private JFXTextField u_shareprice;
    @FXML
    private JFXTextField u_bankname;
    @FXML
    private JFXTextField u_shareamount;
    @FXML
    private JFXTextField u_search;
    @FXML
    private TableView<?> u_table;
    @FXML
    private AnchorPane anchor_view;
    @FXML
    private TableView<?> view_table;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        nbconn = new dbConcurrent();
        
        Integrator.integrate(anchor_shareholder);
        
        
        initializePersonInputs();
        initializeShareholderInputs();
        initializeButton();
        setmethod();
               
       DynamicTable.getColumns(nbconn.get() , "select p.nic, p.full_name  , s.share_amount, s.share_price, s.share_range_start, s.share_range_close\n" +
"from person p ,shareholder s where p.nic=s.nic" , u_table);
       
              DynamicTable.getColumns(nbconn.get() , "select p.nic, p.full_name, p.phone , s.share_amount, s.share_price, s.share_range_start, s.share_range_close\n" +
"from person p ,shareholder s where p.nic=s.nic" , view_table);
    }
    
    public Entity getPersonInputs()
    {
        personCont.clearControls();
        return personCont.getValues();
    }
    
     public Entity getShareholderInputs()
    {
        shareholderCont.clearControls();
        return shareholderCont.getValues();
    }
     public void setPersonInputs(Entity person)
    {
        personCont.setValues(person);
    }
     public void setCustomerInputs(Entity shareholder)
    {
        shareholderCont.setValues(shareholder);
    }
     public boolean validatePersonInputs()
    {
        return personCont.validateValues();
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
     public void initializeButton()
     {
     
        Commons.subAnchorButton asab = new Commons.subAnchorButton();
        asab.setButtonLength(200);
        JFXButton addButton = asab.getButton(anchor_shares, "ADD SHAREHOLDER", Commons.ADD_PERSON_GLYPH);
        
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
        
        Commons.subAnchorButton usab = new Commons.subAnchorButton();
        usab.setButtonLength(180);
        usab.setGlyphWidth(20);
        usab.setCoordinates(670, 550);
        JFXButton updateButton = usab.getButton(anchor_update, "UPDATE SHAREHOLDER", Commons.UPDATE_GLYPH);


        usab.setButtonLength(110);
        usab.setButtonHeigth(20);
        usab.setGlyphWidth(20);
        usab.setCoordinates(650, 450);
        JFXButton searchButton=usab.getButton(anchor_update, "SEARCH", Commons.UPDATE_GLYPH);
     
        
         searchButton.setOnAction(new EventHandler<ActionEvent>()
                {
            @Override
            public void handle(ActionEvent e)
            {    
                String search = u_search.getText();
                handlers.DynamicTable.buildData(nbconn.get(), "select p.nic, p.full_name  , s.share_amount, s.share_price, s.share_range_start, s.share_range_close\n" +
"from person p ,shareholder s where p.nic=s.nic and p.nic like ?", search, u_table);   
            }    
                });
         
        usab.setButtonLength(100);
        usab.setButtonHeigth(20);
        usab.setGlyphWidth(20);
        usab.setCoordinates(800, 550);
        JFXButton viewButton=usab.getButton(anchor_view, "VIEW", Commons.LIST_GLYPH);
        
        viewButton.setOnAction(new EventHandler<ActionEvent>()
        {
        @Override
            public void handle(ActionEvent e)
            {
                
                System.out.println(Validator.isExistingNIC(u_nic.getText(), nbconn));
                Entity person ,shareholder;
                
             
            
            try{
            
                person = getPersonInputs();
                shareholder=getShareholderInputs();
                }
            catch(Exception ex)
            {
            System.out.println("nullpointer no inputs");
            updateButton.setStyle("-fx-background-color: #CB503F");
                
                
                
            
            }
            
            }
        });
        
        
        
        
        
        usab.setButtonLength(110);
        usab.setButtonHeigth(20);
        usab.setGlyphWidth(20);
        usab.setCoordinates(100, 550);
        JFXButton deleteButton=usab.getButton(anchor_view, "DELETE", Commons.DELETE_GLYPH);

     }
     public void setmethod()
     {
       u_table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> 
        {
            if (newSelection != null)
            {
                int index=u_table.getSelectionModel().getSelectedIndex();
                String x=u_table.getItems().get(index).toString();
                String nic = x.split(",")[0].substring(1);
                System.out.println(nic);
                u_nic.setDisable(true);
                
                shareholder shareholder_object = shareholder_search.shareholderFromSQL(nic,nbconn.get());
                Person person_object = legacy.customer_search.personFromSQL(nic, nbconn.get());
                
                u_nic.setText(person_object.nic);
                u_fullname.setText(person_object.name);
                u_email.setText(person_object.email);
                u_phone.setText(person_object.personal_phone);
                u_address.setText(person_object.home_address);
                u_dob.setValue(LocalDate.parse(person_object.dob,DateTimeFormatter.ISO_DATE));
                
                u_nic.setText(shareholder_object.nic);
                u_bankname.setText(shareholder_object.bank_name);
                u_accountno.setText(Integer.toString(shareholder_object.account_no));
                u_shareamount.setText(Integer.toString(shareholder_object.share_amount));
                u_shareprice.setText(Double.toString(shareholder_object.share_price));
                u_dateofissue.setValue(shareholder_object.share_range_start);
                u_dateofexpire.setValue(shareholder_object.share_range_close);
                //udescription.setText(shareholder_object.description);
            }
        });
     
     }
     
}