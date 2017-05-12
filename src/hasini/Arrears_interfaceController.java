/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hasini;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import core.Entity;
import core.Integrator;
import guiMediators.Commons;
import guiMediators.EntityControls;
import guiMediators.tableViewHandler;
import handlers.ValidationHandler;
import handlers.ValidationHandler.DoubleValidator;
import handlers.ValidationHandler.NICValidator;
import handlers.ValidationHandler.pastDateValidator;
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
    EntityControls loanCont;
    EntityControls custCont;
    
    EntityControls searchArrearsCont;
    EntityControls searchloanControls;
    EntityControls searchPersoncont;
    
    private tableViewHandler personSearchTableHandle;
     private tableViewHandler customerSearchTableHandle;
    private tableViewHandler arrearsSearchTableHandle;
    private tableViewHandler loanSearchTableHandle;
    
    dbConcurrent nbconn;
    JFXTabPane jfxtabpane_arrears;

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
    
    @FXML
    private JFXTextField s_nic;
    
    @FXML
    private JFXTextField s_fname;
    
    @FXML
    private TableView<?> e_table;
    

   /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        nbconn = dbConcurrent.getInstance();
        
        jfxtabpane_arrears=Integrator.integrate(anchorpane);
         
        initializeButton();
        tableHandler();
        initializeArrearsInputs();
        setarrearsInput();
        initializePersonInputs();
        initializeLoanControls();
        setPersonInput();
        setLoanControls();
        //tabPane();
       
         
    }    
    
  
    private void initializeButton() {
        
        Commons.subAnchorButton search_mn = new Commons.subAnchorButton(anchor_man, "SEARCH", Commons.LIST_GLYPH);
        search_mn.setButtonLength(110);
        search_mn.setButtonHeigth(20);
        search_mn.setGlyphWidth(20); 
        search_mn.setCoordinates(400, 138);
        search_mn.setStyle(Commons.BTNSTYLE_2);
        JFXButton searchbutton_mn = search_mn.getButton();
        
        searchbutton_mn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                
                Entity search_NIC_mn = new Entity("select * from person ",nbconn);
                search_NIC_mn.add("nic",s_nic.getText());
                //search_NIC_mn.validate(true);
                personSearchTableHandle.writeToTable(search_NIC_mn.executeAsSearch());
                
                Entity search_fname_mn = new Entity("select * from person ",nbconn);
                search_fname_mn.add("full_name",s_fname.getText());
                personSearchTableHandle.writeToTable(search_fname_mn.executeAsSearch());
                
                Entity search_address_mn = new Entity("select * from person ",nbconn);
                search_address_mn.add("address",e_address.getText());
                personSearchTableHandle.writeToTable(search_address_mn.executeAsSearch());
            }
        });
        
        Commons.subAnchorButton select_mn = new Commons.subAnchorButton(anchor_man, "SELECT", Commons.UPDATE_GLYPH);       
        select_mn.setButtonLength(110);
        select_mn.setButtonHeigth(20);
        select_mn.setGlyphWidth(20); 
        select_mn.setCoordinates(750, 138);
        select_mn.setStyle(Commons.BTNSTYLE_2);
        JFXButton selectbutton_mn=select_mn.getButton();
        
        selectbutton_mn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                Entity customer_search = personSearchTableHandle.getSelection();
                e_nic.setText(customer_search.getAsString("nic"));
                e_fname.setText(customer_search.getAsString("full_name"));
                e_address.setText(customer_search.getAsString("address"));
                
                
                jfxtabpane_arrears.getSelectionModel().select(1);
                
            }
        });
      
       Commons.subAnchorButton add_ed = new Commons.subAnchorButton(anchor_edit, "ADD", Commons.ADD_PERSON_GLYPH);
        add_ed.setButtonLength(110);
        add_ed.setButtonHeigth(20);
        add_ed.setGlyphWidth(20); 
        add_ed.setCoordinates(150, 610);
        JFXButton addbutton_ed = add_ed.getButton();
        
        addbutton_ed.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                
                Entity arrears_add = arrearsCont.getValues();
                System.out.println(arrears_add);
                arrears_add.validate(true);
                arrears_add.consolidate();
                arrearsSearchTableHandle.writeToTable(); 
            }
        });
        
        Commons.subAnchorButton update_ed = new Commons.subAnchorButton(anchor_edit, "UPDATE", Commons.UPDATE_GLYPH);
        update_ed.setButtonLength(110);
        update_ed.setButtonHeigth(20);
        update_ed.setGlyphWidth(20); 
        update_ed.setCoordinates(400, 610);
        JFXButton updatebutton_ed = update_ed.getButton();
        
        updatebutton_ed.setOnAction(new EventHandler<ActionEvent>()
        {
        @Override
            public void handle(ActionEvent e)
            {
            
                Entity arrears_up;
                
            
                try{

                    arrears_up=searchArrearsCont.getValues();
                    System.out.println(arrears_up);
                    arrears_up.update();
                    arrearsSearchTableHandle.writeToTable();

                    }
                catch(Exception ex)
                {

                    System.out.println("nullpointer no inputs");

                }

            }
        });
        
        Commons.subAnchorButton search_ed = new Commons.subAnchorButton(anchor_edit, "SEARCH", Commons.LIST_GLYPH);
        search_ed.setButtonLength(110);
        search_ed.setButtonHeigth(20);
        search_ed.setGlyphWidth(20); 
        search_ed.setCoordinates(600, 230);
        search_ed.setStyle(Commons.BTNSTYLE_2);
        JFXButton searchbutton_ed = search_ed.getButton();
        
        searchbutton_ed.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                
                Entity search_NIC_cs = new Entity("select * from arrears_edit ",nbconn);
                search_NIC_cs.add("nic",s_nic.getText());
                arrearsSearchTableHandle.writeToTable(search_NIC_cs.executeAsSearch());
                
            }
        });
        
        Commons.subAnchorButton select_ed = new Commons.subAnchorButton(anchor_edit, "SELECT", Commons.UPDATE_GLYPH);       
        select_ed.setButtonLength(110);
        select_ed.setButtonHeigth(20);
        select_ed.setGlyphWidth(20); 
        select_ed.setCoordinates(600, 300);
        select_ed.setStyle(Commons.BTNSTYLE_2);
        JFXButton selectbutton_if=select_ed.getButton();
        
        selectbutton_if.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                arrearsSearchTableHandle = new tableViewHandler(e_table,"select p.nic, p.full_name,p.dob,p.address,p.phone,p.email,s.share_amount , s.share_price ,"
                    + " s.issue_date , s.expiry_date,s.bank_name,s.account_no,s.approval from person p inner join shareholder s on p.nic=s.nic",nbconn);
               arrearsSearchTableHandle.writeToTable();
               searchArrearsCont.setValues(arrearsSearchTableHandle.getSelection());

            }
        });
        
        Commons.subAnchorButton delete_ed = new Commons.subAnchorButton(anchor_edit, "DELETE", Commons.DELETE_GLYPH);
        delete_ed.setButtonLength(110);
        delete_ed.setButtonHeigth(20);
        delete_ed.setGlyphWidth(20);
        delete_ed.setCoordinates(650, 610);
        JFXButton deletebutton_ed= delete_ed.getButton();
        
        deletebutton_ed.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                
               Entity arrears_del = arrearsSearchTableHandle.fetchExtendedSelection("arrears_edit", "nic");
               arrears_del.deleteFromDB();
               arrearsSearchTableHandle.writeToTable();
                       
            }
        });
    }

    private void initializeArrearsInputs() {
        
        arrearsCont = new EntityControls("arrears_edit",nbconn);
     
        arrearsCont.add("customer_id",e_customerID);
        arrearsCont.add("nic", e_nic,new NICValidator());
        arrearsCont.add("full_name", e_fname);
        arrearsCont.add("address", e_address);
        arrearsCont.add("loan_amount",e_loanAmount,new DoubleValidator());
        arrearsCont.add("loan_date",e_loanDate,new pastDateValidator());
        arrearsCont.add("due_date",e_dueDate,new pastDateValidator());
        arrearsCont.add("last_payment_date",e_lastPaymentDate,new DoubleValidator());
        arrearsCont.add("last_payment_amount",e_lastPaymentAmount,new DoubleValidator());
        arrearsCont.add("outstanding_amount",e_outstandingAmount);
        
    }

   private void initializePersonInputs() {
        
        personCont = new EntityControls("customer_state",nbconn);
        personCont.add("nic", e_nic, new ValidationHandler.NICValidator());
        personCont.add("full_name", e_fname);
        personCont.add("address", e_address);
    }

    private void initializeLoanControls() {
        EntityControls loanControls = new EntityControls("loanplan", nbconn);
        loanControls.add(new Object[][]
        {
            {"customer_id", e_customerID},
            {"amount", e_loanAmount},
    });
    }
    public void tableHandler()
    {
        
        personSearchTableHandle = new tableViewHandler(man_table,"select * from person",nbconn);
        personSearchTableHandle.writeToTable();
        
        arrearsSearchTableHandle = new tableViewHandler(e_table,"select * from arrears_edit", nbconn);
        arrearsSearchTableHandle.writeToTable();
        
        //loanSearchTableHandle = new tableViewHandler(e_table, "select * from loanplan;", nbconn);
       // loanSearchTableHandle.writeToTable();
    
    }

    private void setarrearsInput() {
        
        searchArrearsCont = new EntityControls("arrears_edit",nbconn);
      
        searchArrearsCont.add("customer_id",e_customerID);
        searchArrearsCont.add("nic", e_nic,new NICValidator());
        searchArrearsCont.add("full_name", e_fname);
        searchArrearsCont.add("address", e_address);
        searchArrearsCont.add("loan_amount",e_loanAmount,new DoubleValidator());
        searchArrearsCont.add("loan_date",e_loanDate,new pastDateValidator());
        searchArrearsCont.add("due_date",e_dueDate,new pastDateValidator());
        searchArrearsCont.add("last_payment_date",e_lastPaymentDate,new DoubleValidator());
        searchArrearsCont.add("last_payment_amount",e_lastPaymentAmount,new DoubleValidator());
        searchArrearsCont.add("outstanding_amount",e_outstandingAmount);
        
    }

    private void setLoanControls() {
        
    }

    private void setPersonInput() {
        
        searchPersoncont = new EntityControls("customer_state",nbconn);
        searchPersoncont.add("nic", e_nic, new ValidationHandler.NICValidator());
        searchPersoncont.add("full_name", e_fname);
        searchPersoncont.add("address", e_address);
    }
}
