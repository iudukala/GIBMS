/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gibms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Isuru Udukala
 */
public class dbConnect
{
    public static Connection connect()
    {
        Connection con=null;
        try
        {
            con=DriverManager.getConnection("jdbc:mysql://gildb.cxrwzu2u3mfq.us-west-2.rds.amazonaws.com:3306/bank","root","alpine64");
            //con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","");
            System.out.println("Database connection successful");
        }
        catch(SQLException e)
        {
            System.out.println("dbConnect exception:\n" + e);
        }
        return con;
    }
}