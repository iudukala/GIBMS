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

import data.Manipulate;

/**
 *
 * @author Isuru Udukala
 */
public class general_ledger
{
    public String transaction_code,transaction_name,description;
    public Double withdrawals,deposits,balance;
    public LocalDate date;
    
    public general_ledger(LocalDate date,String transaction_code,String transaction_name,String description,
            Double withdrawals,Double deposits,Double balance)
    {
        this.date = date ;
        
        this.transaction_code = transaction_code;
        this.transaction_name = transaction_name;
       
        this.description = description;
        this.withdrawals = withdrawals;
        this.deposits = deposits;
        this.balance = balance;
       

    }

    general_ledger(LocalDate date, String transaction_code, String transaction_name, Double assets, Double liabilities, Double balance) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString()
    {
        StringBuilder string =new StringBuilder();
        string.append("\ndate\t :  ").append(date.toString());
        string.append("\ntransaction_code\t :  ").append(transaction_code);
        string.append("\ntransaction_name\t :  ").append(transaction_name);
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
            String query = "insert into general_ledger(date,transaction_code,transaction_name,description,withdrawals,deposits,balance)";
            PreparedStatement prp=conn.prepareStatement(Manipulate.psFromQuery(query));
            prp.setString(1, date.toString());
            
            prp.setString(2, transaction_code);
            prp.setString(3, transaction_name);
            
            prp.setString(4, description);
            prp.setDouble(5, withdrawals);
            prp.setDouble(6, deposits);
            prp.setDouble(7, balance);
            prp.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println("Consolidation error[general_ledger]:\n" + e);
        }
    }
}

















