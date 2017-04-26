/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import com.mysql.jdbc.Statement;
import handlers.dbConcurrent;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author Isuru Udukala
 */
public class PreparedStatementWrapper
{
    private PreparedStatement prp;
    private dbConcurrent nbconn;
    private Integer ag_key = null;
    private int update_count;
    
    public PreparedStatementWrapper(dbConcurrent nbconn, String query)
    {
        try
        {
            this.nbconn = nbconn;
            prp = nbconn.get().prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        }
        catch(SQLException e)
        {
            System.out.println("prepared statement wrapper error\n" + e);
        }
    }
    public boolean setObject(int position, Object field)
    {
        boolean status;
        try
        {
            if(field.getClass().equals(String.class))
                prp.setString(position, field.toString());
            else if(field.getClass().equals(Integer.class))
                prp.setInt(position, (Integer)field);
            else if(field.getClass().equals(LocalDate.class))
                prp.setDate(position, Date.valueOf((LocalDate)field));
            else if(field.getClass().equals(Double.class))
                prp.setDouble(position, (Double)field);
            status = true;
        }
        catch(Exception e)
        {
            System.out.println("error int setObject()\n" + e);
            status = false;
        }
        return status;
    }
    
    public boolean executeUpdate()
    {
        boolean status;
        try
        {
            update_count = prp.executeUpdate();
            ResultSet rs = prp.getGeneratedKeys();
            if(rs.next())
            {
                ag_key = rs.getInt(1);
                rs.close();
            }
            status = true;
        }
        catch (Exception e)
        {
            System.out.println("error executing psWrapper\n" + e);
            status = false;
        }
        return status;
    }
    
    public Integer getGeneratedKeys()
    {
        return ag_key;
    }
    
    public int getUpdateCount()
    {
        return update_count;
    }
}
