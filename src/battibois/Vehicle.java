/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Battibois;
import java.sql.*;

public class Vehicle 
{
    //public int resource_id;
    public String brand,license_no,model,colour,engine_no,year;
    
    public Vehicle(String brand,String license_no,String model,String colour,String engine_no,String year)
    {
        //this.resource_id=resource_id;
        this.brand=brand;
        this.license_no=license_no;
        this.model=model;
        this.colour=colour;
        this.engine_no=engine_no;
        this.year=year;
    }
    
   //  public int getResource_id()
    //{
       // return resource_id;
    //}
    public String getBrand()
    {
        return brand;
    }
    public String getLicense_no()
    {
        return license_no;
    }
    public String getModel()
    {
        return model;
    }
    public String getColour()
    {
        return colour;
    }
    public String getEngine_no()
    {
        return engine_no;
    }
    public String getYear()
    {
        return year;
    }
    
    
    @Override
    public String toString()
    {
        StringBuilder strb=new StringBuilder();
      //  strb.append("\nResource_id\t:").append(resource_id);
        strb.append("\nBrand\t:").append(brand);
        strb.append("\nLicense_no\t:").append(license_no);
        strb.append("\nModel\t:").append(model);
        strb.append("\nColour\t:").append(colour);
        strb.append("\nengine_no\t:").append(engine_no);
        strb.append("\nyear\t:").append(year);
        
         return strb.toString();
    }
    public void consolidate(Connection conn)
    {
        try
        {
            String query = "insert into vehicle(brand,license,model,colour,engine_no,year)";
            PreparedStatement prp=conn.prepareStatement(data.Manipulate.psFromQuery(query));
        //    prp.setString(1,Integer.toString(resource_id));
            prp.setString(1,brand);
            prp.setString(2, license_no);
            prp.setString(3, model);
            prp.setString(4, colour);
            prp.setString(5, engine_no);
            prp.setString(6, year);
            prp.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println("Consolidation error[vehicles]:\n" + e);
        }
    }   
}
