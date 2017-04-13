/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hassim;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import data.Manipulate;
/**
 *
 * @author Shamodh
 */
public class attendence 
{
    public String attendenceid,intime,outtime;



public attendence(String attendenceid,String intime,String outtime,String empid)
{
    this.attendenceid=attendenceid;
    this.intime=intime;
    this.outtime=outtime;
}

 @Override
    public String toString()
    {
        StringBuilder strb=new StringBuilder();
        strb.append("AttendenceID\t:").append(attendenceid);
        strb.append("\nOUTTIME\t:").append(outtime);
        strb.append("\nINTIME\t:").append(intime);
        return strb.toString();
    }


public void consolidate(Connection conn)
{
    try
    {
        String query = "insert into attendence(attendenceid,intime,outtime)";
        PreparedStatement prp=conn.prepareStatement(Manipulate.psFromQuery(query));
            prp.setString(1,attendenceid);
            prp.setString(2,intime);
            prp.setString(3,outtime);
            prp.executeUpdate();
            
    }
    catch(SQLException e)
            {
                System.out.println("Consolidation error[Person]:\n" + e);
            }
    
}
        
      
        
}



