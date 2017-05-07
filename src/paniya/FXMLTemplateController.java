/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paniya;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import core.Entity;
import guiMediators.Commons;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.util.ResourceBundle;
import core.Entity;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import core.Integrator;
import guiMediators.EntityControls;
import guiMediators.tableViewHandler;
import handlers.ValidationHandler.*;
import handlers.dbConcurrent;
import static java.lang.System.load;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javax.swing.JTable;


/**
 * FXML Controller class
 *
 * @author praneethjayawickrama
 */
public class FXMLTemplateController implements Initializable {
    
    
   
    
    
    private EntityControls bvr_addControls,bvr_addmemberControls, cashbookControls, general_ledgerControls, trial_balanceControls, transaction_codeControls, company_listControls ;
    private tableViewHandler bvr_addmember_handle,bvr_add_handle, cashbook_handle,cashbook_list_handle, general_ledger_handle, trial_balance_handle, transaction_code_handle, company_list_handle ;
    private dbConcurrent nbconn;
    JFXTabPane jfxcashflow_tabpane;
    //PreparedStatement pst= null;
    //ResultSet rs = null;
    //final ObservableList options = FXCollections.observableArrayList();
    
    @FXML
    private AnchorPane cashflow_anchorpane;
    @FXML
    private TabPane cashflow_tabpane;
    @FXML
    private AnchorPane bvr_anchorpane;
    @FXML
    private JFXDatePicker bvr_date;
    @FXML
    private JFXTextField bvr_transactioncode;
    @FXML
    private JFXTextField bvr_transactionname;
    @FXML
    private JFXTextField bvr_branchname;
    @FXML
    private HBox bvr_hbox;
    @FXML
    private JFXRadioButton bvr_voucher;
    @FXML
    private JFXRadioButton bvr_receipt;
    @FXML
    private ToggleGroup bvr_tgroup;
    @FXML
    private JFXTextField bvr_totalamount;
    private JFXTextField bvr_amount;
    @FXML
    private JFXTextField bvr_branchno;
    @FXML
    private JFXTextField bvr_chequeno;
    @FXML
    private JFXTextField bvr_paymenttype;
    @FXML
    private JFXDatePicker bvr_chequedate;
    @FXML
    private JFXTextField bvr_companyname;
    @FXML
    private JFXTextField bvr_nic;
    @FXML
    private JFXTextField bvr_description;
   
    @FXML
    private JFXTextField bvr_serialno;
    @FXML
    private AnchorPane bvr_list_anchorpane;
    @FXML
    private Label bvr_serialno111;
    @FXML
    private TableView<?> bvr_add_table;
    @FXML
    private AnchorPane cb_anchorpane;
    @FXML
    private JFXDatePicker cb_id;
    @FXML
    private JFXTextField cb_bdbalance;
    @FXML
    private TableView<?> cb_table;
    @FXML
    private TableView<?> gl_table;
    @FXML
    private AnchorPane gl_anchorpane;
    @FXML
    private TableView<?> tb_table;
    @FXML
    private AnchorPane tb_anchorpane;
    @FXML
    private JFXDatePicker tb_id;
    @FXML
    private AnchorPane tc_anchorpane;
    @FXML
    private Label bvr_serialno1;
    @FXML
    private TableView<?> tc_table;
    @FXML
    private JFXTextField tc_transactioncode;
    @FXML
    private JFXTextField tc_transactionname;
    @FXML
    private AnchorPane cb_list_anchorpane;
    @FXML
    private TableView<?> cb_list_table;
    @FXML
    private AnchorPane cl_anchorpane;
    @FXML
    private TableView<?> cl_table;
    @FXML
    private JFXTextField cl_companyid;
    @FXML
    private JFXTextField cl_companyname;
    @FXML
    private JFXTextField cl_branchno;
    @FXML
    private JFXTextField bvr_debitamount;
    @FXML
    private JFXTextField bvr_creditamount;
    @FXML
    private JFXTextField bvr_narration;
    @FXML
    private JFXTextField bvr_companyid;
    @FXML
    private GridPane gl_transactioncode;
    
    int max_tc = 999 ;
    int maxbranchno = 10;
    int maxserialno = 999999;
    double maxmemberamount = 500000;
    @FXML
    private JFXTextField gl_debitbdbalance;
    @FXML
    private JFXTextField gl_creditbdbalance;
    @FXML
    private JFXTextField tb_debitbdbalance;
    @FXML
    private JFXTextField tb_creditbdbalance;
    @FXML
    private TableView<?> bvr_addmember_table;
    
            
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
        nbconn = dbConcurrent.getInstance();
        
       
        jfxcashflow_tabpane = Integrator.integrate(cashflow_anchorpane);
       
        
        initializeRadioButtons();
        initializebvr_addInputs();
        initializebvr_addmemberInputs();
        initializecashbookInputs();
        initializegeneral_ledgerInputs();
        initializetrial_balanceInputs();
        initializetransaction_codeInputs();
        initializecompany_listInputs();
        initializetcButtons();
        initializeclButtons();
        initializebvr_addButtons();
        initializebvr_addmemberButtons();
        initializecbButtons();
        initializetbButtons();
        initializeglButtons();
        initializecomboBox();
        initializecashbookButtons();
       initializegoglButtons();
                
      
       
        
        
         
    }    
    
    
    
    
    

    private void initializetransaction_codeInputs() {
        
         transaction_codeControls = new EntityControls("transaction_code", nbconn);
         transaction_codeControls.add("transaction_code", tc_transactioncode,new IntegerValidator(max_tc));
         transaction_codeControls.add("transaction_name", tc_transactionname);
         
    }

    private void initializecompany_listInputs() {
        
         company_listControls = new EntityControls("company_list", nbconn);
         company_listControls.add("company_id", cl_companyid,new IntegerValidator(max_tc));
         company_listControls.add("branch_no", cl_branchno,new IntegerValidator(maxbranchno));
         company_listControls.add("company_name", cl_companyname);
         
         
         
         
    }

   

    private void initializeRadioButtons() {
        
    }
    

    private void initializebvr_addInputs() {
        
         bvr_voucher.setUserData("Voucher");
         bvr_receipt.setUserData("Receipt");
       
         bvr_addControls = new EntityControls("bvr_add", nbconn);
         bvr_addControls.add("voucher_receipt", bvr_tgroup);
         bvr_addControls.add("date", bvr_date,new pastDateValidator());
         bvr_addControls.add("serial_no", bvr_serialno,new IntegerValidator(maxserialno));
         bvr_addControls.add("transaction_code", bvr_transactioncode,new IntegerValidator(max_tc));
         bvr_addControls.add("transaction_name", bvr_transactionname);
         
         
         
         
       
         
         
         bvr_addControls.add("branch_no", bvr_branchno,new IntegerValidator(maxbranchno));
         bvr_addControls.add("branch_name", bvr_branchname);
         
         
        bvr_addControls.add("payment_type", bvr_paymenttype);
        bvr_addControls.add("cheque_no", bvr_chequeno);
        bvr_addControls.add("cheque_date", bvr_chequedate);
        bvr_addControls.add("total_amount",bvr_totalamount );
        bvr_addControls.add("narration",bvr_narration );
        bvr_addControls.add("company_id",bvr_companyid );
        bvr_addControls.add("company_name",bvr_companyname );
        
        
      
      
        
       
        
         
         
    }

    private void initializebvr_addmemberInputs() {
       
         bvr_addmemberControls = new EntityControls("bvr_addmember",nbconn);
         bvr_addmemberControls.add("serial_no", bvr_serialno,new IntegerValidator(maxserialno));
         bvr_addmemberControls.add("nic", bvr_nic,new NICValidator());
         bvr_addmemberControls.add("description",bvr_description);
         bvr_addmemberControls.add("credit_amount",bvr_creditamount,new DoubleValidator() );
         bvr_addmemberControls.add("debit_amount",bvr_debitamount,new DoubleValidator() );
         
        
        
        
    }

    private void initializecashbookInputs() {
        
        cashbookControls = new EntityControls("cashbook", nbconn);
        cashbookControls.add("cashbook_id",cb_id);
        cashbookControls.add("b/d_balance",cb_bdbalance);
         
    }

    private void initializegeneral_ledgerInputs() {
        
        general_ledgerControls = new EntityControls("general_ledger", nbconn);
        general_ledgerControls.add("transaction_code",gl_transactioncode,new IntegerValidator(max_tc));
        general_ledgerControls.add("credit_b/d_balance",gl_creditbdbalance);
        general_ledgerControls.add("debit_b/d_balance",gl_debitbdbalance);
         
         
    }

    private void initializetrial_balanceInputs() {
        
        trial_balanceControls = new EntityControls("trial_balance", nbconn);
        
          trial_balanceControls.add("trial_balance_id",tb_id);
        trial_balanceControls.add("credit_b/d_balance",tb_creditbdbalance);
        trial_balanceControls.add("debit_b/d_balance",tb_debitbdbalance);
         
    }

    private void initializeNodes() {
        
       
        
        
    }

    private void initializeButtons() {
        
        
        
   
    }

    private void initializeclButtons() {
        
         Commons.subAnchorButton tc = new Commons.subAnchorButton(tc_anchorpane, "ADD CODES", Commons.ADD_PERSON_GLYPH);
         tc.setCoordinates(750, 120);
         tc.setButtonLength(160);
         JFXButton addButton = tc.getButton();
        
        addButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                Entity pnj = transaction_codeControls.getValues();
                pnj.validate(true);
                int p=pnj.consolidate();
                
               transaction_codeControls.clearControls();
               
               transaction_code_handle = new tableViewHandler(tc_table,"select* from transaction_code",nbconn);
                    transaction_code_handle.writeToTable();
                    
                    
            }
        });
                    
        
    }

    private void initializetcButtons() {
        
        
         Commons.subAnchorButton cl = new Commons.subAnchorButton(cl_anchorpane, "ADD COMPANY", Commons.ADD_PERSON_GLYPH);
         cl.setCoordinates(750, 120);
         cl.setButtonLength(180);
         JFXButton addButton = cl.getButton();
        
        addButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                Entity pnj = company_listControls.getValues();
                pnj.validate(true);
                int p=pnj.consolidate();
                
               company_listControls.clearControls();
               
               company_list_handle = new tableViewHandler(cl_table,"select* from company_list",nbconn);
                    company_list_handle.writeToTable();
                    
                    
            }
        });
                    
        
    }

    
    private void initializebvr_addButtons() {
        
        Commons.subAnchorButton bvr_add = new Commons.subAnchorButton(bvr_anchorpane, "SAVE", Commons.ADD_PERSON_GLYPH);
         bvr_add.setCoordinates(390, 610);
         bvr_add.setButtonHeigth(20);
         bvr_add.setButtonLength(200);
         JFXButton addButton = bvr_add.getButton();
         
         addButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                Entity pnj = bvr_addControls.getValues();
                pnj.validate(true);
                int p=pnj.consolidate();
                
               //bvr_addControls.clearControls();
               
               bvr_add_handle = new tableViewHandler(bvr_add_table,"select* from bvr_add",nbconn);
                    bvr_add_handle.writeToTable();
               jfxcashflow_tabpane.getSelectionModel().select(1);     
                    
            }
        });
         
        
        
    }
    
    

    private void initializebvr_addmemberButtons() {
        
         Commons.subAnchorButton cb = new Commons.subAnchorButton(bvr_anchorpane, "ADD MEMBER", Commons.ADD_PERSON_GLYPH);
         cb.setCoordinates(565, 330);
         cb.setButtonHeigth(10);
         cb.setGlyphWidth(15);
         cb.setButtonLength(180);
         JFXButton addButton = cb.getButton();
         
         addButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                Entity pnj = bvr_addmemberControls.getValues();
                pnj.validate(true);
                int p=pnj.consolidate();
                
                Entity pnn = bvr_addControls.getValues();
                pnn.validate(true);
                int n=pnn.consolidate();
                
               //bvr_addmemberControls.clearControls();
               
               bvr_addmember_handle = new tableViewHandler(bvr_addmember_table,"select a.serial_no,b.nic,b.description,b.credit_amount,b.debit_amount from bvr_add a inner join bvr_addmember b on a.serial_no =b.serial_no;",nbconn);
                    bvr_addmember_handle.writeToTable();
                    
                    
            }
        });
         
       Commons.subAnchorButton dl = new Commons.subAnchorButton(bvr_anchorpane, "DELETE", Commons.DELETE_GLYPH);
         dl.setCoordinates(750, 330);
         dl.setButtonHeigth(10);
         dl.setGlyphWidth(15);
         dl.setButtonLength(180);
         JFXButton deleteButton = dl.getButton();  
       
        
        
    }

    private void initializecbButtons() {
        Commons.subAnchorButton cb = new Commons.subAnchorButton(cb_anchorpane, "SAVE", Commons.ADD_PERSON_GLYPH);
         //cb.setCoordinates(750, 120);
         cb.setButtonLength(150);
         JFXButton addButton = cb.getButton();
         
         
        addButton.setOnAction((ActionEvent e) -> {
            Entity pnj = cashbookControls.getValues();
            pnj.validate(true);
            int p=pnj.consolidate();
            
            cashbookControls.clearControls();
            
            cashbook_list_handle = new tableViewHandler(cb_list_table,"select* from cashbook",nbconn);
            cashbook_list_handle.writeToTable();
            jfxcashflow_tabpane.getSelectionModel().select(3);
            
        });
        
    }

    private void initializetbButtons() {
        Commons.subAnchorButton tb = new Commons.subAnchorButton(tb_anchorpane, "SAVE", Commons.ADD_PERSON_GLYPH);
         //tb.setCoordinates(750, 120);
         tb.setButtonLength(150);
         JFXButton addButton = tb.getButton();
         
       
        
        
    }

    private void initializeglButtons() {
        Commons.subAnchorButton gl = new Commons.subAnchorButton(gl_anchorpane, "SAVE", Commons.ADD_PERSON_GLYPH);
         //gl.setCoordinates(750, 120);
         gl.setButtonLength(150);
         JFXButton addButton = gl.getButton();
         
           addButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                Entity pnj = general_ledgerControls.getValues();
                pnj.validate(true);
                int p=pnj.consolidate();
                
               general_ledgerControls.clearControls();
               
               trial_balance_handle = new tableViewHandler(tb_table,"select* from general_ledger",nbconn);
                    trial_balance_handle.writeToTable();
                    jfxcashflow_tabpane.getSelectionModel().select(5);
                    
                    
            }
        });
        
        
    }

             private Entity getmemberInputs() {
                 throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             }

    private void initializecomboBox() {
        
       
        
       
    }

    private void initializecashbookButtons() {
        
        Commons.subAnchorButton gl = new Commons.subAnchorButton(bvr_anchorpane, "ADD CASHBOOK", Commons.ADD_PERSON_GLYPH);
         gl.setCoordinates(595,610 );
         gl.setButtonHeigth(20);
         //gl.setGlyphWidth(20); 
         gl.setButtonLength(200);
         JFXButton addButton = gl.getButton();
         
         addButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                Entity pnn = bvr_addControls.getValues();
                pnn.validate(true);
                int p=pnn.consolidate();
                
                
                Entity pmm = bvr_addmemberControls.getValues();
                pmm.validate(true);
                int s=pmm.consolidate();
                
             if(p == 0 && s ==0)
                {
                
                //bvr_addControls.clearControls();
                //bvr_addmemberControls.clearControls();
                
                }
             cashbook_handle = new tableViewHandler(cb_table,"select a.serial_no,a.date,a.branch_name,a.transaction_name,a.narration,b.nic,b.description,a.payment_type,b.credit_amount,b.debit_amount from bvr_add a inner join bvr_addmember b on a.serial_no =b.serial_no",nbconn);
                    cashbook_handle.writeToTable();
             jfxcashflow_tabpane.getSelectionModel().select(2);
            }
        });
        
    }

    private void initializegoglButtons() {
        
        Commons.subAnchorButton gl = new Commons.subAnchorButton(bvr_anchorpane, "ADD GENERAL LEDGER", Commons.ADD_PERSON_GLYPH);
         gl.setCoordinates(145,610 );
         gl.setButtonHeigth(20);
         gl.setGlyphWidth(15); 
         gl.setButtonLength(240);
         JFXButton addButton = gl.getButton();
         
         addButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                Entity pnn = bvr_addControls.getValues();
                pnn.validate(true);
                int p=pnn.consolidate();
                
                
                Entity pmm = bvr_addmemberControls.getValues();
                pmm.validate(true);
                int s=pmm.consolidate();
                
             if(p == 0 && s ==0)
                {
                
                //bvr_addControls.clearControls();
                //bvr_addmemberControls.clearControls();
                
                }
             general_ledger_handle = new tableViewHandler(gl_table,"select a.transaction_code,a.transaction_name,a.date,a.branch_no,a.serial_no,a.narration,a.company_name,a.voucher_receipt,b.nic,b.description,b.credit_amount,b.debit_amount from bvr_add a inner join bvr_addmember b on a.serial_no =b.serial_no;",nbconn);
                general_ledger_handle.writeToTable();
             
                jfxcashflow_tabpane.getSelectionModel().select(4);
            }
        });
        
    }
    
}
                
