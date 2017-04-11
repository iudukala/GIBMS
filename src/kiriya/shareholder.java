/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiriya;

import java.sql.Connection;
import java.time.LocalDate;
import java.sql.SQLException;
import java.sql.PreparedStatement;
/**
 *
 * @author ASUS-PC
 */
public class shareholder {
  
 
 
  
  public String nic;
  public String bank_name;
  public int account_no ;
  public int share_amount;
  public double share_price;
  public LocalDate share_range_start;
  public LocalDate share_range_close;
  public String description;
  
  public shareholder(String nic,String bank_name ,int account_no ,int share_amount ,double share_price, LocalDate share_range_start,LocalDate share_range_close, String description)
  {
  
      this.nic = nic;
      this.bank_name = bank_name;
      this.account_no = account_no;
      this.share_amount = share_amount;
      this.share_price = share_price;
      this.share_range_start = share_range_start;
      this.share_range_close = share_range_close;
      this.description = description;
     }  
  
   
   @Override
   public String toString()
   {
   
       StringBuilder share =new StringBuilder();
       share.append("\nnic\t   :").append(nic);
       share.append("\nbank_name\t  : ").append(bank_name);
       share.append("\naccount_no\t  : ").append(account_no);
       share.append("\nshare_amount\t  : ").append(share_amount);
       share.append("\nshare_price\t  : ").append(share_price);
       share.append("\nshare_range_start\t  : ").append(share_range_start);
       share.append("\nshare_range_close\t  : ").append(share_range_close);
       share.append("\ndescription\t  : ").append(description);

       return share.toString();


   }
   void consolidate(Connection conn)
   {
   
       try
       {
       PreparedStatement pst=conn.prepareStatement ("insert into shareholder(nic,bank_name,account_no,share_amount,share_price, share_range_start, share_range_close, description) "
                    + "values(?,?,?,?,?,?, ?,?)");
            pst.setString(1, nic);
            pst.setString(2,bank_name);
            pst.setInt(3,account_no);
            pst.setInt(4,share_amount);
            pst.setDouble(5,share_price);
            pst.setString(6,share_range_start.toString());
            pst.setString(7,share_range_close.toString());
            pst.setString(8,description);
            pst.executeUpdate();
       
             
       }
         catch(SQLException e)
        {
            System.out.println("Table failed.exiting with error code -5");
            System.out.println(this);
            System.exit(-5);
        }
   
          
   }
   
   

}
