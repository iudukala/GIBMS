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
      //  custable_handle = new tableViewHandler(u_table, nbconn);
        
        
        initializePersonInputs();
        initializeShareholderInputs();
        initializeButton();
       // selectButton();
        setshareholderInput();
        setPersonInput();
        tabPane();
        buttonStyle2();
        
   
   
    }
 
     public void initializePersonInputs()
    {
        personCont= new EntityControls("person", nbconn);
        personCont.add("nic", a_nic, new NICValidator());
        personCont.add("full_name", a_fullname);
        personCont.add("email", a_email, new EmailValidator());
        personCont.add("dob", a_dob, new birthdayValidator());
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
        shareholderCont.add("issue_date", a_dateofissue, new pastDateValidator()); 
        shareholderCont.add("expiry_date", a_dateofexpire);
     }
     
     public void initializeButton()
     {
        Commons.subAnchorButton asab = new Commons.subAnchorButton(anchor_shares, "ADD SHAREHOLDER", Commons.ADD_PERSON_GLYPH);
        asab.setButtonLength(200);
        JFXButton addButton = asab.getButton();
        
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
        
        Commons.subAnchorButton usab = new Commons.subAnchorButton(anchor_update, "UPDATE SHAREHOLDER", Commons.UPDATE_GLYPH);
        usab.setButtonLength(180);
      
        JFXButton updateButton = usab.getButton();
//               
        updateButton.setOnAction(new EventHandler<ActionEvent>()
        {
        @Override
            public void handle(ActionEvent e)
            {
            
                Entity person ,shareholder;
                
            
            try{
            
                
                
                person = search_cont.getValues();
                person.update();
                
                shareholder=searchShareCont.getValues();
                shareholder.update();
                }
            catch(Exception ex)
            {
            System.out.println("nullpointer no inputs");
               
            }
//                  if(person == 0 && shareholder ==0)
//                {
//                
//                search_cont.clearControls();
//                searchShareCont.clearControls();
//                
//                }
            }
        });
          

         
        

//        usab.setButtonLength(110);

        Commons.subAnchorButton acd = new Commons.subAnchorButton(anchor_view, "DELETE SHAREHOLDER", Commons.DELETE_GLYPH);
        
        JFXButton deleteButton=acd.getButton();

     }

//    private void selectButton()
//    {
//
//         // tableViewHandler tvh=new tableViewHandler(u_table ,nbconn);
//          
//         //custable_handle = new tableViewHandler(u_table, nbconn);
//
//        
//        selectButton.setOnAction(new EventHandler<ActionEvent>()
//        {
//            @Override
//            public void handle(ActionEvent event)
//            {
//                search_cont.setValues(custable_handle.getSelection("person", "NIC"));
//                searchShareCont.setValues(custable_handle.getSelection("shareholder", "NIC"));
//
//            }
//        });
//    }
    public void buttonStyle2()
    {
        Commons.subAnchorButton abc = new Commons.subAnchorButton(anchor_update, "SEARCH", Commons.UPDATE_GLYPH);
        
        abc.setButtonLength(110);
        abc.setButtonHeigth(20);
        abc.setGlyphWidth(20); 
        abc.setCoordinates(650, 450);
        abc.setStyle(Commons.BTNSTYLE_2);
        JFXButton searchButton=abc.getButton();
     
        
         searchButton.setOnAction(new EventHandler<ActionEvent>()
                {
            @Override
            public void handle(ActionEvent e)
            {   
            
           custable_handle = new tableViewHandler(u_table,"select p.nic, p.full_name,s.share_amount , s.share_price ,"
                    + " s.issue_date , s.expiry_date from person p inner join shareholder s on p.nic=s.nic",nbconn);
                    custable_handle.writeToTable();
//                String search = u_search.getText();
//                custable_handle.getSelection("select p.nic, p.full_name,s.share_amount , s.share_price ,"
//                        + " s.issue_date , s.expiry_date from person p inner join shareholder s on p.nic=s.nic like ?");
//                handlers.DynamicTable.buildData(nbconn.get(), "select p.nic, p.full_name  , s.share_amount, s.share_price, s.share_range_start, s.share_range_close\n" +
//"from person p ,shareholder s where p.nic=s.nic and p.nic like ?", search, u_table);   
            }    
                });
         Commons.subAnchorButton zxc = new Commons.subAnchorButton(anchor_update, "SELECT", Commons.UPDATE_GLYPH);
        
        zxc.setButtonLength(110);
        zxc.setButtonHeigth(20);
        zxc.setGlyphWidth(20); 
        zxc.setCoordinates(650, 550);
        zxc.setStyle(Commons.BTNSTYLE_2);
        JFXButton selctButton=zxc.getButton();
        
        selectButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                search_cont.setValues(custable_handle.getSelection());//"person", "NIC"));
                searchShareCont.setValues(custable_handle.getSelection());//"shareholder", "NIC"));

            }
            
               });
                
    
    
    }
    public void tabPane()
    {
        viewTable = new tableViewHandler(app_table, "select p.nic, p.full_name,p.address,p.dob,p.email,p.phone,s.bank_name,"
                            + "s.account_no,s.share_amount , s.share_price ,"
                        + " s.issue_date , s.expiry_date from person p inner join shareholder s on p.nic=s.nic ",nbconn);
        jfxtabpane_shareholder.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
                //second tab has search
                if((int)newValue == 1)
                    viewTable.writeToTable();
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