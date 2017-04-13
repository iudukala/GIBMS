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
public class bank_slip
{
    public String bank_slip_no,description;
    public Double withdrawals,deposits,balance;
    public LocalDate date;

    public bank_slip(LocalDate date,String bank_slip_no,String description,Double withdrawals,Double deposits,Double balance)
    {
         this.bank_slip_no = bank_slip_no;
        this.date = date ;
        this.description = description;
        this.withdrawals = withdrawals;
        this.deposits = deposits;
        this.balance = balance;
    }

    bank_slip(String bank_slip_no, LocalDate date, String description, Double withdrawals, Double deposits, Double balance) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString()
    {
        StringBuilder string =new StringBuilder();
        string.append("\ndate\t :  ").append(date);
        string.append("\nbank_slip_no\t :  ").append(bank_slip_no);
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
            String query = "insert into bank_slip(date,bank_slip_no,description,withdrawals,deposits,balance)";
            PreparedStatement prp=conn.prepareStatement(Manipulate.psFromQuery(query));
            prp.setString(1, date.toString());
            prp.setString(2,bank_slip_no);
            prp.setString(3, description);
            prp.setDouble(4, withdrawals);
            prp.setDouble(5, deposits);
            prp.setDouble(6, balance);
            
            prp.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println("Consolidation error[bank_slip]:\n" + e);
        }
    }
}


















