/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/**
 *
 * @author Isuru Udukala
 */
public class dbConcurrent
{
    Future<Connection> dbFuture = null;
    public dbConcurrent()
    {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Callable<Connection> connCallable = new dbCallable();
        dbFuture = executor.submit(connCallable);
    }
    public Connection get()
    {
        Connection conn = null;
        try
        {
            conn = dbFuture.get();
        }
        catch(Exception e)
        {
            System.out.println("Database connection future<Connection> fetch error\n" + e);
        }
        return conn;
    }
    
    public class dbCallable implements Callable<Connection>
    {
        @Override
        public Connection call()
        {
            Connection conn=null;
            try
            {
                conn=DriverManager.getConnection("jdbc:mysql://gildb.cxrwzu2u3mfq.us-west-2.rds.amazonaws.com:3306/bank","root","alpine64");
                //conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","");
                System.out.println("Database connection successful");
            }
            catch(SQLException e)
            {
                System.out.println("dbConnect exception:\n" + e);
            }
            return conn;
        }
    }
    
    //legacy support
    public static Connection connect()
    {
        System.out.println("Requesting connection from depracated method. use dbConcurrent.get() instead");
        return null;
    }
}