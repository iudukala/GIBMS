/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legacyEntities;

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
public class Insurance_Claim {
    
    public String Claim_Number,Claim_Status,Claim_Type,Description;
    LocalDate Open_Date,Closed_Date;
    public static final DateTimeFormatter DATE_FORMAT=DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:00.0");
    
    public Insurance_Claim(String Claim_Number,String Claim_Status,String Claim_Type,LocalDate Open_Date,LocalDate Closed_Date,String Description){
    
        this.Claim_Number=Claim_Number;
        this.Claim_Status=Claim_Status;
        this.Claim_Type=Claim_Type;
        this.Open_Date=Open_Date;
        this.Closed_Date=Closed_Date;
        this.Description=Description;
         
    }
    
     @Override
    public String toString()
    {
   
       StringBuilder Insurance_Claim =new StringBuilder();
       Insurance_Claim.append("\nClaim_Number\t   :").append(Claim_Number);
       Insurance_Claim.append("\nClaim_Status\t  : ").append(Claim_Status);
       Insurance_Claim.append("\nClaim_Type\t  : ").append(Claim_Type);
       Insurance_Claim.append("\nOpen_Date\t  : ").append(Open_Date);
       Insurance_Claim.append("\nClosed_Date\t  : ").append(Closed_Date);
       Insurance_Claim.append("\nDescription\t  : ").append(Description);
      
       return Insurance_Claim.toString();


    }

    void consolidate(Connection conn){
    
         try
        {
            String query = "insert into Insurance_Claim(Claim_Number,Claim_Status,Claim_Type,Open_Date,Closed_Date,Description)";
            PreparedStatement prp=conn.prepareStatement(Manipulator.psFromQuery(query));
            prp.setString(1,Claim_Number);
            prp.setString(2,Claim_Status);
            prp.setString(3,Claim_Type);
            prp.setString(4,Open_Date.toString());
            prp.setString(5,Closed_Date.toString());
            prp.setString(6,Description);
           
            prp.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println("Consolidation error[Insurance_Claim]:\n" + e);
        }
    }
    
}
