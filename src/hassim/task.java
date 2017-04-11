/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hassim;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import data.Manipulator;
/**
 *
 * @author Shamodh
 */
public class task 
{
    LocalDate date;
    String time_period;
    String task;
    
    public task(LocalDate date,String time_period,String task)
    {
        this.date=date;
        this.task = task;
        this.time_period =time_period ;
    }
    
    public void consolidate(Connection conn)
    {
        
        try
        {   String query = "insert into task(date,task,time_period)";
            PreparedStatement prp = conn.prepareStatement(Manipulator.psFromQuery(query));
            prp.setString(1, time_period);
            prp.setString(2, task);
            prp.setString(3, date.toString());
        }
        catch(SQLException e)
        {
          System.out.println("Consolidation error [Customer]:\n" + e);

        }
    }
    
}
