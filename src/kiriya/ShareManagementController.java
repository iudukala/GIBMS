/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiriya;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import core.Entity;
import core.Integrator;
import guiMediators.Commons;
import guiMediators.EntityControls;
import guiMediators.tableViewHandler;
import handlers.ValidationHandler. *;
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
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ASUS-PC
 */
public class ShareManagementController implements Initializable
{

    EntityControls personCont;
    EntityControls shareholderCont;
    EntityControls search_cont;
    EntityControls searchShareCont;
    JFXTabPane jfxtabpane_shareholder;
    
    private tableViewHandler custable_handle;
    private tableViewHandler viewTable;
    
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
    @FXML
    private JFXButton selectButton;
    private TreeTableView<?> apply_table;
    @FXML
    private TableView<?> app_table;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        nbconn = new dbConcurrent();
        jfxtabpane_shareholder=Integrator.integrate(anchor_shareholder);
        custable_handle = new tableViewHandler(u_table, nbconn);
        
        
        initializePersonInputs();
        initializeShareholderInputs();
        initializeButton();
        selectButton();
        setshareholderInput();
        setPersonInput();
        tabPane();
        
        
   
   
    }
 
     public void initializePersonInputs()
    {
        personCont= new EntityControls("person", nbconn);
        personCont.add("nic", a_nic, new NICValidator());
        personCont.add("full_name", a_fullname);
        personCont.add("email", a_email, new EmailValidator());
        personCont.add("dob", a_dob);
        personCont.add("phone", a_phone, new PhoneValidator());
        personCont.add("address", a_address);
    }
     public void initializeShareholderInputs()
     {
        shareholderCont = new EntityControls("shareholder",nbconn);
        shareholderCont.add("nic", a_nic, new NICValidator());
        shareholderCont.add("share_amount", a_shareamount, new IntegerValidator());
        shareholderCont.add("share_price", a_shareprice, new DoubleValidator());
        shareholderCont.add("account_no", a_accountno,new IntegerValidator());
        shareholderCont.add("bank_name", a_bankname);
        shareholderCont.add("issue_date", a_dateofissue);
        shareholderCont.add("expiry_date", a_dateofexpire);
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
                Entity person = personCont.getValues();
                person.validate(true);
                int p=person.consolidate();
                
                
                Entity shareholder = shareholderCont.getValues();
                shareholder.validate(true);
                int s=shareholder.consolidate();
                
             if(p == 0 && s ==0)
                {
                
                personCont.clearControls();
                shareholderCont.clearControls();
                
                }
             
            }
        });
        
        Commons.subAnchorButton usab = new Commons.subAnchorButton();
        usab.setButtonLength(180);
        usab.setGlyphWidth(20);
        usab.setCoordinates(670, 550);
        JFXButton updateButton = usab.getButton(anchor_update, "UPDATE SHAREHOLDER", Commons.UPDATE_GLYPH);
//               
//        updateButton.setOnAction(new EventHandler<ActionEvent>()
//        {
//        @Override
//            public void handle(ActionEvent e)
//            {
//                Entity person ,shareholder;
//                
//             
//            
//            try{
//            
//                person = personCont.getValues();
//                shareholder=shareholderCont.getValues();
//                }
//            catch(Exception ex)
//            {
//            System.out.println("nullpointer no inputs");
//               
//            }
//            
//            }
//        });
//          

        usab.setButtonLength(110);
        usab.setButtonHeigth(20);
        usab.setGlyphWidth(20);
        usab.setCoordinates(650, 450);
        JFXButton searchButton=usab.getButton(anchor_update, "SEARCH", Commons.UPDATE_GLYPH);
     
        
         searchButton.setOnAction(new EventHandler<ActionEvent>()
                {
            @Override
            public void handle(ActionEvent e)
            {   custable_handle.writeToTable("select p.nic, p.full_name,s.share_amount , s.share_price ,"
                    + " s.issue_date , s.expiry_date from person p inner join shareholder s on p.nic=s.nic");
                String search = u_search.getText();
                custable_handle.getSelection("select p.nic, p.full_name,s.share_amount , s.share_price ,"
                        + " s.issue_date , s.expiry_date from person p inner join shareholder s on p.nic=s.nic like ?");
//                handlers.DynamicTable.buildData(nbconn.get(), "select p.nic, p.full_name  , s.share_amount, s.share_price, s.share_range_start, s.share_range_close\n" +
//"from person p ,shareholder s where p.nic=s.nic and p.nic like ?", search, u_table);   
            }    
                });
         
        usab.setButtonLength(100);
        usab.setButtonHeigth(20);
        usab.setGlyphWidth(20); 
        usab.setCoordinates(800, 550);
        JFXButton viewButton=usab.getButton(anchor_view, "VIEW", Commons.LIST_GLYPH);
        
//        viewButton.setOnAction(new EventHandler<ActionEvent>()
//        {
//        @Override
//            public void handle(ActionEvent e)
//            {
//                Entity person ,shareholder;
//                
//             
//            
//            try{
//            
//                person = personCont.getValues();
//                shareholder=shareholderCont.getValues();
//                }
//            catch(Exception ex)
//            {
//            System.out.println("nullpointer no inputs");
//               
//            }
//            
//            }
//        });
        
        usab.setButtonLength(110);
        usab.setButtonHeigth(20);
        usab.setGlyphWidth(20);
        usab.setCoordinates(100, 550);
        JFXButton deleteButton=usab.getButton(anchor_view, "DELETE", Commons.DELETE_GLYPH);

     }

    private void selectButton()
    {

          tableViewHandler tvh=new tableViewHandler(u_table ,nbconn);
          
         custable_handle = new tableViewHandler(u_table, nbconn);

        
        selectButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                search_cont.setValues(tvh.getSelection("person", "NIC"));
                searchShareCont.setValues(tvh.getSelection("shareholder", "NIC"));

            }
        });
    }
    
    public void tabPane()
    {
        viewTable = new tableViewHandler(app_table, nbconn);
        jfxtabpane_shareholder.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
                //second tab has search
                if((int)newValue == 1)
                    viewTable.writeToTable("select p.nic, p.full_name,p.address,p.dob,p.email,p.phone,s.bank_name,"
                            + "s.account_no,s.share_amount , s.share_price ,"
                        + " s.issue_date , s.expiry_date from person p inner join shareholder s on p.nic=s.nic ");
            }
        });
    }
    
    
       public void setPersonInput()
       {
            search_cont = new EntityControls("person",nbconn);

            search_cont.add("nic", u_nic);
            search_cont.add("full_name", u_fullname);
            search_cont.add("email", u_email);
            search_cont.add("dob", u_dob);
            search_cont.add("phone", u_phone);
            search_cont.add("address", u_address);
            search_cont.add("nic", a_nic);
        
       }
       public void setshareholderInput()
       {
            searchShareCont = new EntityControls("shareholder",nbconn);

            searchShareCont.add("nic", u_nic);
            searchShareCont.add("share_amount", u_shareamount);
            searchShareCont.add("share_price", u_shareprice);
            searchShareCont.add("account_no", u_accountno);
            searchShareCont.add("bank_name", u_bankname);
            searchShareCont.add("issue_date", u_dateofissue);
            searchShareCont.add("expiry_date", u_dateofexpire);
       }
}