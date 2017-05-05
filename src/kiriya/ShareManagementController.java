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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

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
    EntityControls addShareCont;
    EntityControls labelCont;
    
    
    JFXTabPane jfxtabpane_shareholder;
    
    private tableViewHandler custable_handle;
    private tableViewHandler viewTable;
    private tableViewHandler delete_handle;
    
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
    @FXML
    private GridPane lable_stock;
    @FXML
    private GridPane lable_stock1;
    @FXML
    private JFXTextField add_shares;
    @FXML
    private JFXButton add_btn;
    @FXML
    private Label stockss;
    @FXML
    private JFXButton d_select;
    @FXML
    private JFXDatePicker datess;
    @FXML
    private JFXTextField share_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        nbconn = dbConcurrent.getInstance();
        jfxtabpane_shareholder=Integrator.integrate(anchor_shareholder);
      //  custable_handle = new tableViewHandler(u_table, nbconn);
        
        
        initializePersonInputs();
        initializeShareholderInputs();
        initializeButton();
        selectButton();
        setshareholderInput();
        setPersonInput();
        tabPane();
        addShares();
        
   
   
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
        //usab.setGlyphWidth(20);
        //usab.setCoordinates(670, 550);
        JFXButton updateButton = usab.getButton();
//               
        updateButton.setOnAction(new EventHandler<ActionEvent>()
        {
        @Override
            public void handle(ActionEvent e)
            {
            
                Entity person ,shareholder;
                
            
            try
            {
            
                
                
                person = search_cont.getValues();
                person.update();
                
                shareholder=searchShareCont.getValues();
                shareholder.update();
                }
            catch(Exception ex)
            {
            System.out.println("nullpointer no inputs");
               
            }

            }
        });
       
           add_btn.setOnAction(new EventHandler<ActionEvent>()
                {
            @Override
            public void handle(ActionEvent e)
            {   
               Entity share = addShareCont.getValues();
                share.validate(true);
                share.consolidate(); 
                addShareCont.clearControls();
                
            }   
                
            });
         
        Commons.subAnchorButton abcc = new Commons.subAnchorButton(anchor_view, "DELETE", Commons.DELETE_GLYPH);
 

        abcc.setButtonLength(110);
        abcc.setButtonHeigth(20);
        abcc.setGlyphWidth(20);
        abcc.setCoordinates(100, 550);
        JFXButton deleteButton=usab.getButton();

     }

    private void selectButton()
    {
   
        Commons.subAnchorButton zvb = new Commons.subAnchorButton(anchor_update, "SEARCH", Commons.UPDATE_GLYPH);

        zvb.setButtonLength(110);
        zvb.setButtonHeigth(20);
        zvb.setGlyphWidth(20); 
        zvb.setCoordinates(650, 450);
        zvb.setStyle(Commons.BTNSTYLE_2);
        JFXButton searchButton=zvb.getButton();
     
        
         searchButton.setOnAction(new EventHandler<ActionEvent>()
                {
            @Override
            public void handle(ActionEvent e)
            {   
            
           custable_handle = new tableViewHandler(u_table,"select p.nic, p.full_name,s.share_amount , s.share_price ,"
                    + " s.issue_date , s.expiry_date from person p inner join shareholder s on p.nic=s.nic",nbconn);
               custable_handle.writeToTable();
                       
                    Entity search=new Entity("select p.nic, p.full_name , s.share_amount, s.share_price, s.issue_date, s.expiry_date \n" +
"from person p ,shareholder s where p.nic=s.nic;",nbconn);
                    search.add("p.nic",u_search.getText());
                    custable_handle.writeToTable(search.executeAsSearch());
                    

         }    
                });
        selectButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                search_cont.setValues(custable_handle.getSelection());
                searchShareCont.setValues(custable_handle.getSelection());

            }
        });
        
        d_select.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {   
                delete_handle=new tableViewHandler(view_table,"select * from shareholder",nbconn);
                delete_handle.writeToTable();
                
                Entity select=new Entity("select * from shareholder",nbconn);
                select.add("expiry_date",datess.getValue());
                delete_handle.writeToTable(select.executeAsSearch());
                
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
                viewTable.writeToTable();
                if((int)newValue == 1)
                {
                    stockss.setText(Entity.parseFromQuery("select shares from shares",nbconn).get(0).getAsString("shares"));
                }
            }
        });
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
        shareholderCont.add("account_no", a_accountno);
        shareholderCont.add("bank_name", a_bankname);
        shareholderCont.add("issue_date", a_dateofissue, new pastDateValidator());
        shareholderCont.add("expiry_date", a_dateofexpire);
     }
    
    
       public void setPersonInput()
       {
            search_cont = new EntityControls("person",nbconn);

            search_cont.add("nic", u_nic,new NICValidator() );
            search_cont.add("full_name", u_fullname);
            search_cont.add("email", u_email, new EmailValidator());
            search_cont.add("dob", u_dob, new birthdayValidator());
            search_cont.add("phone", u_phone, new PhoneValidator());
            search_cont.add("address", u_address);
            search_cont.add("nic", a_nic);
        
       }
       public void setshareholderInput()
       {
            searchShareCont = new EntityControls("shareholder",nbconn);

            searchShareCont.add("nic", u_nic,new NICValidator());
            searchShareCont.add("share_amount", u_shareamount,new IntegerValidator());
            searchShareCont.add("share_price", u_shareprice, new DoubleValidator());
            searchShareCont.add("account_no", u_accountno);
            searchShareCont.add("bank_name", u_bankname);
            searchShareCont.add("issue_date", u_dateofissue, new pastDateValidator());
            searchShareCont.add("expiry_date", u_dateofexpire);
       }
       public void addShares()
       {
         addShareCont=new EntityControls("shares",nbconn);
         
         addShareCont.add("share_id",share_id);
         addShareCont.add("shares",add_shares);
       
       }
}