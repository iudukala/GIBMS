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
//        setmethod();
               
//       DynamicTable.getColumns(nbconn.get() , "select p.nic, p.full_name  , s.share_amount, s.share_price, s.share_range_start, s.share_range_close\n" +
//"from person p ,shareholder s where p.nic=s.nic" , u_table);
//       
//              DynamicTable.getColumns(nbconn.get() , "select p.nic, p.full_name, p.phone , s.share_amount, s.share_price, s.share_range_start, s.share_range_close\n" +
//"from person p ,shareholder s where p.nic=s.nic" , view_table);
//    
    }
    
    //-------------------DELETE KARAPAN-------------
//    public Entity getPersonInputs()
//    {
//        personCont.clearControls();
//        return personCont.getValues();
//    }
//    public Entity getShareholderInputs()
//    {
//        shareholderCont.clearControls();
//        return shareholderCont.getValues();
//    }
//     public void setPersonInputs(Entity person)
//    {
//        personCont.setValues(person);
//    }
//     public void setCustomerInputs(Entity shareholder)
//    {
//        shareholderCont.setValues(shareholder);
//    }
//     public boolean validatePersonInputs()
//    {
//        return personCont.validateValues();
//    }//---------------------------------------------------------------  
    
    
     public void initializePersonInputs()
    {
        personCont= new EntityControls("person", nbconn);
        
        
        personCont.add("nic", a_nic);
        
        personCont.add("full_name", a_fullname);
        
        
        personCont.add("email", a_email);
        
        personCont.add("dob", a_dob);
        
        
        personCont.add("phone", a_phone);
        
        personCont.add("address", a_address);
        
    
    }
     public void initializeShareholderInputs()
     {
        shareholderCont = new EntityControls("shareholder",nbconn);
        
        
        shareholderCont.add("nic", a_nic);
        
        
        shareholderCont.add("share_amount", a_shareamount);
        
        
        
        shareholderCont.add("share_price", a_shareprice);
        
        
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
                Entity person = personCont.getValues();
                person.validate(true);
                person.consolidate();
                
                
                Entity shareholder = shareholderCont.getValues();
                shareholder.validate(true);
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
//                handlers.DynamicTable.buildData(nbconn.get(), "select p.nic, p.full_name  , s.share_amount, s.share_price, s.share_range_start, s.share_range_close\n" +
//"from person p ,shareholder s where p.nic=s.nic and p.nic like ?", search, u_table);   
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
                Entity person ,shareholder;
                
             
            
            try{
            
                person = personCont.getValues();
                shareholder=shareholderCont.getValues();
                }
            catch(Exception ex)
            {
            System.out.println("nullpointer no inputs");
                
                
                
            
            }
            
            }
        });
        
        
        
        
        
        usab.setButtonLength(110);
        usab.setButtonHeigth(20);
        usab.setGlyphWidth(20);
        usab.setCoordinates(100, 550);
        JFXButton deleteButton=usab.getButton(anchor_view, "DELETE", Commons.DELETE_GLYPH);

     }
//     public void setmethod()
//     {
//       //u_table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> 
//        //{
//          JFXTabPane jfxtabpane_shareholder=Integrator.integrate(anchor_shareholder);  
//          
//         custable_handle = new tableViewHandler(u_table, nbconn);
//        jfxtabpane_shareholder.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
//        {
//            @Override
//            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
//            {
//               
//                if((int)newValue == 1)
//                custable_handle.writeToTable("select * from person , shareholder");
//            }
//        });
//        
//        selectButton.setOnAction(new EventHandler<ActionEvent>()
//        {
//            @Override
//            public void handle(ActionEvent event)
//            {
////                personCont.setValues(custable_handle.getSelection("person", "NIC"));
////                shareholderCont.setValues(custable_handle.getSelection("shareholder", "NIC"));
////                jfxtabpane_shareholder.getSelectionModel().select(0);
//            }
//        });
//            if (newSelection != null)
//            {
//                int index=u_table.getSelectionModel().getSelectedIndex();
//                String x=u_table.getItems().get(index).toString();
//                String nic = x.split(",")[0].substring(1);
//                System.out.println(nic);
//                u_nic.setDisable(true);
//                
//                shareholder shareholder_object = shareholder_search.shareholderFromSQL(nic,nbconn.get());
//                Person person_object = legacy.customer_search.personFromSQL(nic, nbconn.get());
//                
//                u_nic.setText(person_object.nic);
//                u_fullname.setText(person_object.name);
//                u_email.setText(person_object.email);
//                u_phone.setText(person_object.personal_phone);
//                u_address.setText(person_object.home_address);
//                u_dob.setValue(LocalDate.parse(person_object.dob,DateTimeFormatter.ISO_DATE));
//                
//                u_nic.setText(shareholder_object.nic);
//                u_bankname.setText(shareholder_object.bank_name);
//                u_accountno.setText(Integer.toString(shareholder_object.account_no));
//                u_shareamount.setText(Integer.toString(shareholder_object.share_amount));
//                u_shareprice.setText(Double.toString(shareholder_object.share_price));
//                u_dateofissue.setValue(shareholder_object.share_range_start);
//                u_dateofexpire.setValue(shareholder_object.share_range_close);
//                //udescription.setText(shareholder_object.description);
//            }
        //});
//     
     //}

    private void selectButton()
    {
        
            
       //u_table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> 
        //{
          tableViewHandler tvh=new tableViewHandler(u_table ,nbconn);
          
         custable_handle = new tableViewHandler(u_table, nbconn);
        jfxtabpane_shareholder.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
               
                if((int)newValue == 2)
                    custable_handle.writeToTable("select p.nic, p.address, p.dob, p.full_name, p.email ,s.account_no, s.bank_name , s.share_amount , s.share_price, s.share_range_close, s.share_range_start from person p inner join shareholder s on p.nic=s.nic;");
            }
        });
        
        selectButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                search_cont.setValues(tvh.getSelection("person", "NIC"));
                search_cont.setValues(tvh.getSelection("shareholder", "NIC"));
//                jfxtabpane_shareholder.getSelectionModel().select(0);
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

            searchShareCont.add("share_amount", u_shareamount);
            searchShareCont.add("share_price", u_shareprice);
            searchShareCont.add("account_no", u_accountno);
            searchShareCont.add("bank_name", u_bankname);
            searchShareCont.add("share_range_start", u_dateofissue);
            searchShareCont.add("share_range_close", u_dateofexpire);
       }
}