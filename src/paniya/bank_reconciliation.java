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
public class bank_reconciliation
{
    public String br_no,description;
    public Double withdrawals,deposits,balance;
    public LocalDate date;

     public bank_reconciliation(LocalDate date,String br_no,String description,Double withdrawals,Double deposits,Double balance)
    {
        
        
        this.br_no = br_no;
        this.date = date ;
        this.description = description;
        this.withdrawals = withdrawals;
        this.deposits = deposits;
        this.balance = balance;
       

    }

    bank_reconciliation(String br_no, LocalDate date, String description, Double withdrawals, Double deposits, Double balance) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public String toString()
    {
        StringBuilder string =new StringBuilder();
        string.append("\ndate\t :  ").append(date);
        string.append("\nbr_no\t :  ").append(br_no);
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
            String query = "insert into bank_reconciliation(date,br_no,description,withdrawals,deposits,balance)";
            PreparedStatement prp=conn.prepareStatement(Manipulator.psFromQuery(query));
            prp.setString(1, date.toString());
            prp.setString(2,br_no);
            prp.setString(3, description);
            prp.setDouble(4, withdrawals);
            prp.setDouble(5, deposits);
            prp.setDouble(6, balance);
            prp.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println("Consolidation error[bank_reconciliation]:\n" + e);
        }
    }
}

















