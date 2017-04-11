/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paniya;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import data.Manipulator;

/**
 *
 * @author Isuru Udukala
 */
public class cashbook
{
    public String cashbook_id,serial_no,br_no,bank_slip_no,receipt_voucher,transaction_code,transaction_name,nic,resource_id,name,description;
    public Double withdrawals,deposits,balance,commercial_bank,sampath_bank;
    public LocalDate date;

    public cashbook(LocalDate date,String cashbook_id,String serial_no,String br_no,String bank_slip_no,
            String receipt_voucher,String transation_code,String transation_name,String nic,String resource_id,
            String name,String description,Double  withdrawals,Double  deposits,Double  balance,Double  commeriacal_bank,Double sampath_bank)
    {
        this.date = date ;
        this.cashbook_id = cashbook_id;
        this.serial_no = serial_no;
        this.br_no = br_no;
        this.bank_slip_no = bank_slip_no;
        this.receipt_voucher = receipt_voucher;
        this.transaction_code = transation_code;
        this.transaction_name = transation_name;
        this.nic = nic;
        this.resource_id = resource_id;
        this.name = name;
        this.description = description;
        this.withdrawals = withdrawals;
        this.deposits = deposits;
        this.balance = balance;
    }

    cashbook(String cashbook_id, String serial_no, String br_no, String bank_slip_no, String transaction_code, String transaction_name, String nic, String resource_id, String name, String description, Double withdrawals, Double deposits, Double balance, Double commercial_bank, Double sampath_bank) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString()
    {
        StringBuilder string =new StringBuilder();
        string.append("\ndate\t :  ").append(date.toString());
        string.append("\ncashbook_id\t :  ").append(cashbook_id);
        string.append("\nserial_no\t :  ").append(serial_no);
        string.append("\nbr_no\t :  ").append(br_no);
        string.append("\nbank_slip_no\t :  ").append(bank_slip_no);
        string.append("\nreceipt_voucher\t :  ").append(receipt_voucher);
      
        string.append("\ntransaction_code\t :  ").append(transaction_code);
        string.append("\ntransaction_name\t :  ").append(transaction_name);
      
        string.append("\nnic\t :  ").append(nic);
        string.append("\nresource_id\t :  ").append(resource_id);
        string.append("\nname\t :  ").append(name);
        string.append("\ndescription\t :  ").append(description);
        string.append("\nwithdrawals\t :  ").append(withdrawals);
        string.append("\ndeposits\t :  ").append(deposits);
        string.append("\nbalance\t :  ").append(balance);
        string.append("\ncommercial_bank\t :  ").append(commercial_bank);
        string.append("\nsampath_bank\t :  ").append(sampath_bank);
        
      
        
        
        return string.toString();
    }
    public void consolidate(Connection conn) throws SQLException
            
    {
        try
        {
            String query = "insert into cashbook (date,cashbook_id,serial_no,br_no,bank_slip_no,receipt_voucher,transaction_code,transaction_name,nic,resource_id,name,description,withdrawals,deposits,balance,commercial_bank,sampath_bank)";
                    
            PreparedStatement prp=conn.prepareStatement(Manipulator.psFromQuery(query));
            prp.setString(1, date.toString());
            prp.setString(2,cashbook_id);
            prp.setString(3,serial_no);
            prp.setString(4,br_no);
            prp.setString(5,bank_slip_no);
            prp.setString(6, receipt_voucher);
            prp.setString(7, transaction_code);
            prp.setString(8, transaction_name);
            prp.setString(9, nic);
            prp.setString(10, resource_id);
            prp.setString(11, name);
            prp.setString(12, description);
            prp.setDouble(13, withdrawals);
            prp.setDouble(14, deposits);
            prp.setDouble(15, balance);
            prp.setDouble(16, commercial_bank);
            prp.setDouble(17, sampath_bank);
            prp.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println("Consolidation error[cashbook]:\n" + e);
        }
    }
}











