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

import core.Manipulator;

/**
 *
 * @author Isuru Udukala
 */
public class personal_account
{
    public String account_id,serial_no,br_no,bank_slip_no,transaction_code,transaction_name,nic,resource_id,name,description;
    public Double withdrawals,deposits,balance;
    public LocalDate date;

     public personal_account (LocalDate date,String account_id,String serial_no,String br_no,String bank_slip_no,String receipt_voucher,
            String transaction_code,String transaction_name,String nic,String resource_id,String name,String description,Double withdrawals,Double deposits,Double balance)
    {
        this.date = date ;
        this.account_id = account_id;
        this.serial_no = serial_no;
        this.br_no = br_no;
        this.bank_slip_no = bank_slip_no;
        
        this.transaction_code = transaction_code;
        this.transaction_name = transaction_name;
        this.nic = nic;
        this.resource_id = resource_id;
        this.name = name;
        this.description = description;
        this.withdrawals = withdrawals;
        this.deposits = deposits;
        this.balance = balance;
    }

    personal_account(String account_id, String serial_no, String br_no, String bank_slip_no, String transaction_code, String transaction_name, String nic, String resource_id, String name, String description, Double withdrawals, Double deposits, Double balance) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString()
    {
        StringBuilder string =new StringBuilder();
        string.append("\ndate\t :  ").append(date.toString());
        string.append("\naccount_id\t :  ").append(account_id);
        string.append("\nserial_no\t :  ").append(serial_no);
        string.append("\nbr_no\t :  ").append(br_no);
        string.append("\nbank_slip_no\t :  ").append(bank_slip_no);
        
      
        string.append("\ntransaction_code\t :  ").append(transaction_code);
        string.append("\ntransaction_name\t :  ").append(transaction_name);
      
        string.append("\nnic\t :  ").append(nic);
        string.append("\nresource_id\t :  ").append(resource_id);
        string.append("\nname\t :  ").append(name);
        string.append("\ndescription\t :  ").append(description);
        string.append("\nwithdrawals\t :  ").append(withdrawals);
        string.append("\ndeposits\t :  ").append(deposits);
        string.append("\nbalance\t :  ").append(balance);
      
        
        
        return string.toString();
    }
    
    public void consolidate(Connection conn)
    {
        try
        {
            String query = "insert into personal_account(date,account_id,serial_no,br_no,bank_slip_no,transaction_code,transaction_name,nic,resource_id,name,description,withdrawals,deposits,balance)";
            PreparedStatement prp=conn.prepareStatement(Manipulator.psFromQuery(query));
            prp.setString(1, date.toString());
            prp.setString(2,account_id);
            prp.setString(3,serial_no);
            prp.setString(4,br_no);
            prp.setString(5,bank_slip_no);
           
            prp.setString(6, transaction_code);
            prp.setString(7, transaction_name);
            prp.setString(8, nic);
            prp.setString(9, resource_id);
            prp.setString(10, name);
            prp.setString(11, description);
            prp.setDouble(12, withdrawals);
            prp.setDouble(13, deposits);
            prp.setDouble(14, balance);
            prp.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println("Consolidation error[personal_account]:\n" + e);
        }
    }
}















