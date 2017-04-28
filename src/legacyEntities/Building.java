/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legacyEntities;
import java.sql.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import core.Manipulator;

public class Building 
{
     final static String TABLE_NAME="building";
    
    //public int building_id;
    public String address,floor,description,condition,end_date,rent_date;
    public double rent_value;
    public Building(String address,String floor,String description,String condition,double rent_value,LocalDate end_date,LocalDate rent_date)
    {
        //this.building_id=building_id;
        this.address=address;
        this.floor=floor;
        this.description=description;
        this.condition=condition;
        this.rent_value=rent_value;
        this.end_date=end_date.format(DateTimeFormatter.ISO_DATE);
        this.rent_date=rent_date.format(DateTimeFormatter.ISO_DATE);
    }
    //public int getBuilding_id()
   // {
   //     return building_id;
   // }
    public String getAddress()
    {
        return address;
    }
    public String getFloor()
    {
        return floor;
    }
    public String getDescription()
    {
        return description;
    }
    public String getCondition()
    {
        return condition;
    }
    public double getRent_value()
    {
        return rent_value;
    }
    public String getEnd_date()
    {
        return end_date;
    }
    public String getRent_date()
    {
        return rent_date;
    }
    
    @Override
    public String toString()
    {
        StringBuilder strb=new StringBuilder();
     //   strb.append("\nBuilding_id\t :  ").append(building_id);
        strb.append("\nAddress\t :  ").append(address);
        strb.append("\nFloor\t :  ").append(floor);
        strb.append("\nDescription\t :  ").append(description);
        strb.append("\nCondition\t :  ").append(condition);
        strb.append("\nEnd_date\t :  ").append(end_date);
        strb.append("\nRent_date\t :  ").append(rent_date);
        strb.append("\nRent_value\t :  ").append(rent_value);
        
        
        return strb.toString();
    }
    void consolidate(Connection con)
    {
        try
        {
            String query="insert into building(`address`,`floor`,`description`,`condition`,`rent_value`,`end_date`,`rent_date`)";
            PreparedStatement prp=con.prepareStatement(Manipulator.psFromQuery(query));
            prp.setString(1, address);
            prp.setString(2, floor);
            prp.setString(3, description);
            prp.setString(4, condition);
            prp.setDouble(5, rent_value);
            prp.setString(6, end_date);
            prp.setString(7, rent_date);
            
            prp.executeUpdate();
        }
        catch(SQLException e)
        {
             System.out.println("Consolidation error[building]:\n" + e);
        }
    }
}
