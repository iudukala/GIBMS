/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legacy;

import legacy.Customer;
import legacy.Person;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Isuru Udukala
 */
public class customer_search 
{
    //temporary class (+methods)
    public static Person personFromSQL(String search_nic, Connection conn)
    {
        String query = "select * from person where nic = ?";
        try
        {
            System.out.println("parsing");
            PreparedStatement prp=conn.prepareStatement(query);
            prp.setString(1, search_nic);
            ResultSet rs = prp.executeQuery();
            
            rs.next();
            System.out.println(rs.getString("full_name"));
            String name = rs.getString("full_name");
            String nic = rs.getString("nic");
            LocalDate dob = LocalDate.parse(rs.getString("dob"), DateTimeFormatter.ISO_DATE);
            String phone = rs.getString("phone");
            String address = rs.getString("address");
            char gender = rs.getString("gender").charAt(0);
            char marital = rs.getString("marital_status").charAt(0);
            String email = rs.getString("email");
            return new Person(name,nic,dob,phone,address,gender,marital,email);
        }
        catch(SQLException e)
        {
            System.out.println("Error parsing person <- resultset\n" + e);
        }
        return null;
    }
    public static Customer customerFromSQL(String search_nic, Connection conn)
    {
        String query = "select * from customer_state where nic = ?";
        try
        {
            System.out.println("parsing customers");
            PreparedStatement prp=conn.prepareStatement(query);
            prp.setString(1, search_nic);
            ResultSet rs = prp.executeQuery();
            
            rs.next();
            String nic=rs.getString("nic");
            String wphone = rs.getString("work_phone");
            char sector= rs.getString("emp_sector").charAt(0);
            String company = rs.getString("company");
            String position = rs.getString("position");
            LocalDate startdate = LocalDate.parse(rs.getString("emp_startdate"),DateTimeFormatter.ISO_DATE);
            char service = rs.getString("service_nature").charAt(0);
            String profession = rs.getString("profession");
            String accnum = rs.getString("account_num");
            String bank = rs.getString("account_bank");
            String branch = rs.getString("account_branch");
            int career = rs.getInt("earn_career");
            int bussiness = rs.getInt("earn_bussiness");
            int houses = rs.getInt("earn_houses");
            int vehicles = rs.getInt("earn_vehicles");
            int land = rs.getInt("earn_land");
            
            return new Customer(nic,wphone,company,position,accnum,bank,branch,sector,service,startdate,career,bussiness,houses,vehicles,land);
        }
        catch(SQLException e)
        {
            System.out.println("Error parsing customer <- resultset\n" + e);
        }
        return null;
    }
}
