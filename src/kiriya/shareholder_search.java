/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiriya;

import entities.Customer;
import entities.Person;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author ASUS-PC
 */
public class shareholder_search
{
    public static shareholder shareholderFromSQL(String search_nic, Connection conn)
    {
        String query = "select * from shareholder where nic = ?";
        try
        {
            System.out.println("parsing customers");
            PreparedStatement prp=conn.prepareStatement(query);
            prp.setString(1, search_nic);
            ResultSet rs = prp.executeQuery();
            
            rs.next();
            String nic=rs.getString("nic");
            String bank_name = rs.getString("bank_name");
            int account_no = rs.getInt("account_no");
            int share_amount = rs.getInt("share_amount");
            double share_price = rs.getDouble("share_price");
            LocalDate share_range_start = LocalDate.parse(rs.getString("share_range_start"),DateTimeFormatter.ISO_DATE);
            LocalDate share_range_close = LocalDate.parse(rs.getString("share_range_close"),DateTimeFormatter.ISO_DATE);
            String description = rs.getString("description");
           
            return new shareholder(nic,bank_name,account_no,share_amount,share_price,share_range_start,share_range_close,description);
        }
        catch(SQLException e)
        {
            System.out.println("Error parsing customer <- resultset\n" + e);
        }
        return null;
    }
    
    
}
