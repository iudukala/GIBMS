/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legacy;

import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author PNJ
 */
public class PaniyaFXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private ToggleGroup select;
    @FXML
    private TextField txt_serial_no_bvr;
    @FXML
    private TextField txt_branch_no_bvr;
    @FXML
    private TextField txt_transaction_name_bvr;
    @FXML
    private DatePicker txt_date_bvr;
    @FXML
    private TextField txt_transaction_code_bvr;
    @FXML
    private TextField txt_nic_id;
    @FXML
    private TextField txt_nic_bvr;
    @FXML
    private TextField txt_resource_id_bvr;
    @FXML
    private TextField txt_amount_bvr;
    @FXML
    private TextField txt_description_bvr;
    @FXML
    private TextField txt_name_bvr;
    @FXML
    private Button button_add_bvr;
    @FXML
    private Button button_delete1_bvr;
    @FXML
    private Button button_edit1_bvr;
    @FXML
    private TableView<?> table_bvr;
    @FXML
    private TableColumn<?, ?> colum_nic_bvr;
    @FXML
    private TableColumn<?, ?> colum_resource_id_bvr;
    @FXML
    private TableColumn<?, ?> colum_name_bvr;
    @FXML
    private TableColumn<?, ?> colum_description_bvr;
    @FXML
    private SplitMenuButton txt_payment_type_bvr;
    @FXML
    private DatePicker txt_cheque_date_bvr;
    @FXML
    private TextField txt_total_amount_bvr;
    @FXML
    private TextField txt_cheque_no_bvr;
    @FXML
    private Button button_save_bvr;
    @FXML
    private Button button_delete_bvr;
    @FXML
    private Button button_edit_bvr;
    @FXML
    private Button button_list_bvr;
    @FXML
    private TextField txt_br_no_br;
    @FXML
    private TextField txt_bank_name_br;
    @FXML
    private TextField txt_description_br;
    @FXML
    private TextField txt_withdrawals_br;
    @FXML
    private TextField txt_deposits_br;
    @FXML
    private DatePicker txt_date_br;
    @FXML
    private Button button_add_br;
    @FXML
    private Button button_edit_br;
    @FXML
    private TextField txt_balance_br;
    @FXML
    private Button button_delete_br;
    @FXML
    private TableView<?> table_br;
    @FXML
    private TableColumn<?, ?> colum_date_br;
    @FXML
    private TableColumn<?, ?> colum_bank_name_br;
    @FXML
    private TableColumn<?, ?> colum_br_no_br;
    @FXML
    private TableColumn<?, ?> colum_description_br;
    @FXML
    private TableColumn<?, ?> colum_withdrawals_br;
    @FXML
    private TableColumn<?, ?> colum_deposits_br;
    @FXML
    private TableColumn<?, ?> colum_balance_br;
    @FXML
    private TextField txt_total_amount_br;
    @FXML
    private TextField txt_bank_slip_no_bs;
    @FXML
    private TextField txt_bank_name_bs;
    @FXML
    private TextField txt_description_bs;
    @FXML
    private TextField txt_withdrawals_bs;
    @FXML
    private TextField txt_deposits_bs;
    @FXML
    private DatePicker txt_date_bs;
    @FXML
    private Button button_save_bs;
    @FXML
    private Button button_edit_bs;
    @FXML
    private TextField txt_balance_bs;
    @FXML
    private Button button_delete_bs;
    @FXML
    private TableView<?> table_bs;
    @FXML
    private TableColumn<?, ?> colum_date_bs;
    @FXML
    private TableColumn<?, ?> colum_bank_slip_no_bs;
    @FXML
    private TableColumn<?, ?> colum_bank_name_bs;
    @FXML
    private TableColumn<?, ?> colum_description_bs;
    @FXML
    private TableColumn<?, ?> colum_amount_bs;
    @FXML
    private TableView<?> table_cb;
    @FXML
    private TableColumn<?, ?> colum_date_cb;
    @FXML
    private TableColumn<?, ?> colum_bank_slip_no_cb;
    @FXML
    private TableColumn<?, ?> colum_br_no_cb;
    @FXML
    private TableColumn<?, ?> colum_serial_no_cb;
    @FXML
    private TableColumn<?, ?> colum_transaction_code_cb;
    @FXML
    private TableColumn<?, ?> colum_transaction_name_cb;
    @FXML
    private TableColumn<?, ?> colum_nic_cb;
    @FXML
    private TableColumn<?, ?> colum_name_cb;
    @FXML
    private TableColumn<?, ?> colum_description_cb;
    @FXML
    private TableColumn<?, ?> colum_withdrawals_cb;
    @FXML
    private TableColumn<?, ?> colum_deposits_cb;
    @FXML
    private TableColumn<?, ?> colum_balance_cb;
    @FXML
    private TableColumn<?, ?> colum_commercial_bank_cb;
    @FXML
    private TableColumn<?, ?> colum_sampath_cb;
    @FXML
    private TextField txt_cash_book_id_cb;
    @FXML
    private DatePicker txt_date_cb;
    @FXML
    private TextField txt_serial_no_cb;
    @FXML
    private TextField txt_br_no_cb;
    @FXML
    private TextField txt_transaction_code_cb;
    @FXML
    private TextField txt_bank_slip_no_cb;
    @FXML
    private TextField txt_transaction_name_cb;
    @FXML
    private TextField txt_nic_cb;
    @FXML
    private TextField txt_resource_id_cb;
    @FXML
    private TextField txt_name_cb;
    @FXML
    private TextField txt_description_cb;
    @FXML
    private TextField txt_withdrawals_cb;
    @FXML
    private TextField txt_deposits_cb;
    @FXML
    private TextField txt_balance_cb;
    @FXML
    private TextField txt_commercial_bank_cb;
    @FXML
    private TextField txt_sampath_bank_cb;
    @FXML
    private Button button_edit_cb;
    @FXML
    private Button button_add_cb;
    @FXML
    private Button button_delete_cb;
    @FXML
    private TextField txt_account_id_pa;
    @FXML
    private TextField txt_nic_pa;
    @FXML
    private TextField txt_resource_id_pa;
    @FXML
    private TextField txt_name_pa;
    @FXML
    private TextField txt_transaction_code_pa;
    @FXML
    private TextField txt_transaction_name_pa;
    @FXML
    private DatePicker txt_date_pa;
    @FXML
    private TextField txt_withdrawals_pa;
    @FXML
    private TextField txt_deposits_pa;
    @FXML
    private TextField txt_description_pa;
    @FXML
    private Button button_add_pa;
    @FXML
    private TextField txt_balance_pa;
    @FXML
    private TextField txt_serial_no_pa;
    @FXML
    private TextField txt_br_no_pa;
    @FXML
    private TextField txt_bank_slip_no_pa;
    @FXML
    private Button button_edit_pa;
    @FXML
    private Button button_delete_pa;
    @FXML
    private TableView<?> table_pa;
    @FXML
    private TableColumn<?, ?> colum_date_pa;
    @FXML
    private TableColumn<?, ?> colum_bank_slip_no_pa;
    @FXML
    private TableColumn<?, ?> colum_br_no_pa;
    @FXML
    private TableColumn<?, ?> colum_serial_no_pa;
    @FXML
    private TableColumn<?, ?> colum_description_pa;
    @FXML
    private TableColumn<?, ?> colum_withdrawals_pa;
    @FXML
    private TableColumn<?, ?> colum_deposits;
    @FXML
    private TableColumn<?, ?> colum_balance_pa;
    @FXML
    private TextField txt_total_balance_pa;
    @FXML
    private TextField txt_transaction_code_gl;
    @FXML
    private TextField txt_transaction_name_gl;
    @FXML
    private TextField txt_description_gl;
    @FXML
    private TextField txt_withdrawals_gl;
    @FXML
    private TextField txt_deposits_gl;
    @FXML
    private Button button_save_gl;
    @FXML
    private TextField txt_balance_gl;
    @FXML
    private DatePicker txt_date_gl;
    @FXML
    private Button button_edit_gl;
    @FXML
    private Button button_delete_gl;
    @FXML
    private TableView<?> table_gl;
    @FXML
    private TableColumn<?, ?> colum_date_gl;
    @FXML
    private TableColumn<?, ?> colum_transaction_code_gl;
    @FXML
    private TableColumn<?, ?> colum_transaction_name_gl;
    @FXML
    private TableColumn<?, ?> colum_description_gl;
    @FXML
    private TableColumn<?, ?> colum_withdrawals_gl;
    @FXML
    private TableColumn<?, ?> colum_deposits_gl;
    @FXML
    private TableColumn<?, ?> colum_balance_gl;
    @FXML
    private TextField txt_total_balance_gl;
    @FXML
    private TableView<?> table_tb;
    @FXML
    private TableColumn<?, ?> colum_date_tb;
    @FXML
    private TableColumn<?, ?> colum_trial_balance_id_tb;
    @FXML
    private TableColumn<?, ?> colum_transaction_code_tb;
    @FXML
    private TableColumn<?, ?> colum_transaction_name_tb;
    @FXML
    private TableColumn<?, ?> colum_assets_tb;
    @FXML
    private TableColumn<?, ?> colum_balance_tb;
    @FXML
    private TextField txt_total_trial_balance_tb;
    @FXML
    private TextField txt_trial_balance_id_tb;
    @FXML
    private DatePicker txt_date_tb;
    @FXML
    private TextField txt_branch_no_tb;
    @FXML
    private TextField txt_branch_name_tb;
    @FXML
    private TextField txt_transaction_code_tb;
    @FXML
    private TextField txt_transaction_name_tb;
    @FXML
    private TextField txt_balance_tb;
    @FXML
    private Button button_add_tb;
    @FXML
    private Button button_edit_tb;
    @FXML
    private Button button_delete_tb;
    @FXML
    private TableView<?> table_bvrl;
    @FXML
    private TableColumn<?, ?> colum_serial_no_bvrl;
    @FXML
    private TableColumn<?, ?> colum_date_bvrl;
    @FXML
    private TableColumn<?, ?> colum_receipt_voucher_bvrl;
    @FXML
    private TableColumn<?, ?> colum_branch_no_bvrl;
    @FXML
    private TableColumn<?, ?> colum_branch_name_bvrl;
    @FXML
    private TableColumn<?, ?> colum_transation_code_bvrl;
    @FXML
    private TableColumn<?, ?> colum_transation_name_bvrl;
    @FXML
    private TableColumn<?, ?> colum_nic_bvrl;
    @FXML
    private TableColumn<?, ?> colum_resource_id_bvrl;
    @FXML
    private TableColumn<?, ?> colum_description_bvrl;
    @FXML
    private TableColumn<?, ?> colum_amount_bvrl;
    @FXML
    private TableColumn<?, ?> colum_total_amount_bvrl;
    @FXML
    private TableColumn<?, ?> colum_payment_type_bvrl;
    @FXML
    private TableColumn<?, ?> colum_cheque_no_bvrl;
    @FXML
    private TableColumn<?, ?> colum_cheque_date_bvrl;
    
    
     Connection conn = null;
    @FXML
    private TextField txt_assets_tb;
    @FXML
    private TextField txt_liabilities_tb;
    
  
    @FXML private TableView table;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         conn = handlers.dbConcurrent.connect();
         //data.DynamicTable.buildData(con, "select * from person", table);
        // TODO
    }    


    @FXML
    private void button_add_bvr(ActionEvent event) {
    }

    @FXML
    private void button_delete1_bvr(ActionEvent event) {
    }

    @FXML
    private void button_edit_bvr(ActionEvent event) {
    }

    @FXML
    private void button_save_brv(ActionEvent event) {
        
            String serial_no = txt_serial_no_bvr.getText();
            //String receipt_voucher = txt_receipt_voucher_bvr.getText();
           
            LocalDate date = txt_date_bvr.getValue();
            String transaction_code = txt_serial_no_bvr.getText();
            String transaction_name = txt_transaction_name_bvr.getText();
            String nic = txt_nic_bvr.getText();
            String resource_id = txt_resource_id_bvr.getText();
            String name = txt_name_bvr.getText();
            String description = txt_description_bvr.getText();
            Double amount = Double.parseDouble(txt_amount_bvr.getText());
            Double total_amount = Double.parseDouble(txt_total_amount_bvr.getText());
            String payment_type = txt_payment_type_bvr.getText();
            String cheque_no = txt_cheque_no_bvr.getText();
            
           
            LocalDate cheque_date = txt_cheque_date_bvr.getValue();
             try
        {
           bank_receipt_voucher  bank_receipt_voucher_object = new  bank_receipt_voucher(serial_no,date,transaction_code,transaction_name,nic,resource_id,name,description,amount,total_amount,payment_type,cheque_no,cheque_date);
                      bank_receipt_voucher_object.toString();
            bank_receipt_voucher_object.consolidate(conn);
            
        
        }
        catch(Exception e)
                {
                    System.out.println(e);
                }
        
      
    }

    @FXML
    private void button_delete_brv(ActionEvent event) {
    }

    @FXML
    private void button_add_br(ActionEvent event) {
         
            String br_no = txt_br_no_br.getText();
            //String receipt_voucher = txt_receipt_voucher_bvr.getText();
           
            LocalDate date = txt_date_br.getValue();
         
            String bank_name = txt_bank_name_br.getText();
         
         
            
            String description = txt_description_br.getText();
            Double withdrawals = Double.parseDouble(txt_withdrawals_br.getText());
            Double deposits = Double.parseDouble(txt_deposits_br.getText());
            Double balance = Double.parseDouble(txt_balance_br.getText());
           
            
           try
        {
            
                      bank_reconciliation  bank_reconciliation_object = new  bank_reconciliation(br_no,date,description,withdrawals,deposits,balance);          
                bank_reconciliation_object.toString();
                  bank_reconciliation_object.consolidate(conn);
            
        
        }
        catch(Exception e)
                {
                    System.out.println(e);
                }
        
      
    }

    @FXML
    private void button_edit_br(ActionEvent event) {
    }

    @FXML
    private void button_delete_br(ActionEvent event) {
    }

    @FXML
    private void button_save_bs(ActionEvent event) {
       
            String bank_slip_no = txt_bank_slip_no_bs.getText();
            //String receipt_voucher = txt_receipt_voucher_bvr.getText();
           
            LocalDate date = txt_date_bs.getValue();
         
            String bank_name = txt_bank_name_bs.getText();
            String description = txt_description_bs.getText();
            Double withdrawals = Double.parseDouble(txt_withdrawals_bs.getText());
            Double deposits = Double.parseDouble(txt_deposits_bs.getText());
            Double balance = Double.parseDouble(txt_balance_bs.getText());
           
            
           
             try
        {
//            
          bank_slip  bank_slip_object = new  bank_slip(bank_slip_no,date,description,withdrawals,deposits,balance);           
          
          bank_slip_object.toString();
          bank_slip_object.consolidate(conn);
            
        
        }
        catch(Exception e)
                {
                    System.out.println(e);
                }
        
      
        
    }

    @FXML
    private void button_edit_bs(ActionEvent event) {
    }

    @FXML
    private void button_delete_bs(ActionEvent event) {
    }

    @FXML
    private void button_edit_cb(ActionEvent event) {
    }

    @FXML
    private void button_add_cb(ActionEvent event) {
        
            LocalDate date = txt_date_cb.getValue();
            String cashbook_id = txt_cash_book_id_cb.getText();
            String serial_no = txt_serial_no_cb.getText();
            String br_no = txt_br_no_cb.getText();
            String bank_slip_no = txt_bank_slip_no_cb.getText();
            //String receipt_voucher = txt_receipt_voucher_cb.getText();
           
            
            String transaction_code = txt_transaction_code_cb.getText();
            String transaction_name = txt_transaction_name_cb.getText();
            String nic = txt_nic_cb.getText();
            String resource_id = txt_resource_id_cb.getText();
            String name = txt_name_cb.getText();
            String description = txt_description_cb.getText();
            Double withdrawals = Double.parseDouble(txt_withdrawals_cb.getText());
            Double deposits = Double.parseDouble(txt_deposits_cb.getText());
            Double balance = Double.parseDouble(txt_balance_cb.getText());
            Double commercial_bank = Double.parseDouble(txt_commercial_bank_cb.getText());
            Double sampath_bank = Double.parseDouble(txt_sampath_bank_cb.getText());
            
         
        try
        {
           
          cashbook  cashbook_object = new  cashbook(cashbook_id,serial_no,br_no,bank_slip_no,transaction_code,transaction_name,nic,resource_id,name,description,withdrawals,deposits,balance,commercial_bank,sampath_bank);
                     cashbook_object.toString();
          cashbook_object.consolidate(conn);
            
        
        }
        catch(Exception e)
                {
                    System.out.println(e);
                }
        
    }

    @FXML
    private void button_delete_cb(ActionEvent event) {
    }

    @FXML
    private void button_add_pa(ActionEvent event) {
        
            String account_id = txt_account_id_pa.getText();
            //String receipt_voucher = txt_receipt_voucher_bvr.getText();
           String serial_no = txt_serial_no_pa.getText();
           String br_no = txt_br_no_pa.getText();
           String bank_slip_no = txt_bank_slip_no_pa.getText();
           
            String transaction_code = txt_transaction_code_pa.getText();
            String transaction_name = txt_transaction_name_pa.getText();
            String nic = txt_nic_pa.getText();
            String resource_id = txt_resource_id_pa.getText();
            String name = txt_name_pa.getText();
            String description = txt_description_pa.getText();
            Double withdrawals = Double.parseDouble(txt_withdrawals_pa.getText());
            Double deposits = Double.parseDouble(txt_deposits_pa.getText());
            Double balance = Double.parseDouble(txt_balance_pa.getText());
         
            
           
       try
        {
         personal_account  personal_account_object = new  personal_account(account_id,serial_no,br_no,bank_slip_no,transaction_code,transaction_name,nic,resource_id,name,description,withdrawals,deposits,balance);
        
         personal_account_object.toString();
         personal_account_object.consolidate(conn);
            
        
        }
        catch(Exception e)
                {
                    System.out.println(e);
                }
        
      
    }

    @FXML
    private void button_edit_pa(ActionEvent event) {
    }

    @FXML
    private void button_delete_pa(ActionEvent event) {
    }

    @FXML
    private void button_save_gl(ActionEvent event) {
        
            
            //String receipt_voucher = txt_receipt_voucher_bvr.getText();
           
            LocalDate date = txt_date_gl.getValue();
            String transaction_code = txt_transaction_code_gl.getText();
            String transaction_name = txt_transaction_name_gl.getText();
           
            String description = txt_description_gl.getText();
            Double withdrawals = Double.parseDouble(txt_withdrawals_gl.getText());
            Double deposits = Double.parseDouble(txt_deposits_gl.getText());
            Double balance = Double.parseDouble(txt_balance_gl.getText());
            
           
           try
        { 
//            
          general_ledger  general_ledger_object = new  general_ledger(date,transaction_code,transaction_name,description,withdrawals,deposits,balance);
                      general_ledger_object.toString();
           general_ledger_object.consolidate(conn);
            
        
        }
        catch(Exception e)
                {
                    System.out.println(e);
                }
        
      
    }

    @FXML
    private void button_edit_gl(ActionEvent event) {
    }

    @FXML
    private void button_delete_gl(ActionEvent event) {
    }

    @FXML
    private void button_add_tb(ActionEvent event) {
         try
        {
            
            //String receipt_voucher = txt_receipt_voucher_bvr.getText();
           
            LocalDate date = txt_date_tb.getValue();
            String trial_balance_id = txt_trial_balance_id_tb.getText();
            String transaction_code = txt_transaction_code_tb.getText();
            String transaction_name = txt_transaction_name_tb.getText();
           
          
            Double assets = Double.parseDouble(txt_assets_tb.getText());
            Double liabilities = Double.parseDouble(txt_liabilities_tb.getText());
            Double balance = Double.parseDouble(txt_balance_tb.getText());
            
           
            
//            
          general_ledger  general_ledger_object = new  general_ledger(date,transaction_code,transaction_name,assets,liabilities,balance);
           
           general_ledger_object.toString();
           general_ledger_object.consolidate(conn);
            
        
        }
        catch(Exception e)
                {
                    System.out.println(e);
                }
        
      
        
    }

    @FXML
    private void button_edit_tb(ActionEvent event) {
    }

    @FXML
    private void button_delete_tb(ActionEvent event) {
    }
    
}
