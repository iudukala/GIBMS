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
public class trial_balance
{
    public String trial_balance_id,transation_code,transation_name;
    public Double assets,liabilities,balance;
    public LocalDate date;

    public trial_balance(LocalDate date,String trial_balance_id,String transation_code,
            String transation_name,Double assets,Double liabilities,Double balance)
    {
        this.date = date ;
        this.trial_balance_id = trial_balance_id;
        this.transation_code = transation_code;
        this.transation_name = transation_name;
       
      
        this.assets = assets;
        this.liabilities = liabilities;
        this.balance = balance;
       

    }
    
    @Override
    public String toString()
    {
        StringBuilder string =new StringBuilder();
        string.append("\ndate\t :  ").append(date.toString());
        string.append("\ntrial_balance_id\t :  ").append(trial_balance_id);
        string.append("\ntransation_code\t :  ").append(transation_code);
        string.append("\ntransation_name\t :  ").append(transation_name);
       
        string.append("\nassets\t :  ").append(assets);
        string.append("\nliabilities\t :  ").append(liabilities);
        string.append("\nbalance\t :  ").append(balance);
      
        
        
        return string.toString();
    }
    public void consolidate(Connection conn)
    {
        try
        {
            String query = "insert into trial_balance(date,trial_balance_id,transation_code,transation_name,assets,liabilities,balance)";
            PreparedStatement prp=conn.prepareStatement(Manipulator.psFromQuery(query));
            prp.setString(1, date.toString());
            prp.setString(2, trial_balance_id);
            prp.setString(3, transation_code);
            prp.setString(4, transation_name);
            
          
            prp.setDouble(5,assets);
            prp.setDouble(6, liabilities);
            prp.setDouble(7, balance);
          
            prp.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println("Consolidation error[trial_balance]:\n" + e);
        }
    }
}













