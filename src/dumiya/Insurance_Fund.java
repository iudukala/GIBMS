/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dumiya;

import core.Manipulator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Dumintha Wijesekara
 */
public class Insurance_Fund {
    
    public Integer Customer_ID;
    public Double Insurance_Fee,Payed_Amount,Due_Amount;
    LocalDate Date_Issued,Expiry_Date;
    public static final DateTimeFormatter DATE_FORMAT=DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:00.0");
    
    public Insurance_Fund(Integer Customer_ID,Double Insurance_Fee,LocalDate Date_Issued,LocalDate Expiry_Date,Double Payed_Amount,Double Due_Amount){
    
        this.Customer_ID=Customer_ID;
        this.Insurance_Fee=Insurance_Fee;
        this.Date_Issued=Date_Issued;
        this.Expiry_Date=Expiry_Date;
        this.Payed_Amount=Payed_Amount;
        this.Due_Amount=Due_Amount;
         
    }
    
    @Override
    public String toString()
    {
   
       StringBuilder Insurance_Fund =new StringBuilder();
       Insurance_Fund.append("\nCustomer_ID\t   :").append(Customer_ID);
       Insurance_Fund.append("\nInsurance_Fee\t  : ").append(Insurance_Fee);
       Insurance_Fund.append("\nDate_Issued\t  : ").append(Date_Issued);
       Insurance_Fund.append("\nExpiry_Date\t  : ").append(Expiry_Date);
       Insurance_Fund.append("\nPayed_Amount\t  : ").append(Payed_Amount);
       Insurance_Fund.append("\nDue_Amount\t  : ").append(Due_Amount);
      
       return Insurance_Fund.toString();


    }
   
   
    
    void consolidate(Connection conn){
    
        try
        {
            String query = "insert into Insurance_Fund(Customer_ID,Insurance_Fee,Date_Issued,Expiry_Date,Payed_Amount,Due_Amount)";
            PreparedStatement prp=conn.prepareStatement(Manipulator.psFromQuery(query));
            prp.setInt(1,Customer_ID);
            prp.setDouble(2,Insurance_Fee);
            prp.setString(3,Date_Issued.toString());
            prp.setString(4,Expiry_Date.toString());
            prp.setDouble(5,Payed_Amount);
            prp.setDouble(6,Due_Amount);
           
            prp.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println("Consolidation error[Insurance_Fund]:\n" + e);
        }
    }
    
}
