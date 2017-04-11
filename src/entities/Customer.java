/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import data.Manipulator;
/**
 *
 * @author Isuru Udukala
 */
public class Customer 
{
    public String nic,work_phone,company,position,acc_num, acc_bank, acc_branch;
    public char emp_sector,service_nature;
    public String emp_start_date;
    public int earn_career, earn_business, earn_houses, earn_vehicles, earn_land;

    public Customer(String nic, String work_phone, String company, String position, String acc_num, String acc_bank, String acc_branch, 
            char emp_sector, char service_nature, LocalDate emp_start_date, int earn_career, int earn_business, int earn_houses, int earn_vehicles, int earn_land)
    {
        this.nic=nic;
        this.work_phone = work_phone;
        this.company = company;
        this.position = position;
        this.acc_num = acc_num;
        this.acc_bank = acc_bank;
        this.acc_branch = acc_branch;
        this.emp_sector = emp_sector;
        this.service_nature = service_nature;
        this.emp_start_date = emp_start_date.format(DateTimeFormatter.ISO_DATE);
        this.earn_career = earn_career;
        this.earn_business = earn_business;
        this.earn_houses = earn_houses;
        this.earn_vehicles = earn_vehicles;
        this.earn_land = earn_land;
    }
    
    public void consolidate(Connection conn)
    {
        System.out.println("AA");
        try
        {   String query = "insert into customer_state(nic, work_phone, emp_sector, company, position, emp_startdate, service_nature, account_num, "
                + "account_bank, account_branch, earn_career, earn_bussiness, earn_houses, earn_vehicles, earn_land)";
            PreparedStatement prp = conn.prepareStatement(Manipulator.psFromQuery(query));
            prp.setString(1, nic);
            prp.setString(2, work_phone);
            prp.setString(3, Character.toString(emp_sector));
            prp.setString(4, company);
            prp.setString(5, position);
            prp.setString(6, emp_start_date);
            prp.setString(7, Character.toString(service_nature));
            prp.setString(8, acc_num);
            prp.setString(9, acc_bank);
            prp.setString(10, acc_branch);
            prp.setInt(11, earn_career);
            prp.setInt(12, earn_business);
            prp.setInt(13, earn_houses);
            prp.setInt(14, earn_vehicles);
            prp.setInt(15, earn_land);
            prp.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println("Consolidation error [Customer]:\n" + e);
        }
    }
    
    
}
