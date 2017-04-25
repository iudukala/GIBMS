/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import handlers.dbConcurrent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Isuru Udukala
 */
public class Validator
{
    public static boolean isPhone(String str)
    {
        List<Character> allowed=new ArrayList<>(Arrays.asList('+','-','(',')'));
        //allowed.addAll(Arrays.asList('+','-'));
        boolean valid=true;
        for(int i=0; i<str.length(); i++)
        {
            if(! (Character.isDigit(str.charAt(i)) || allowed.contains(str.charAt(i))))
            {
                valid=false;
                break;
            }
        }
        return valid;
    }
    public static boolean isEmail(String str)
    {
        List<Character> allowed=new ArrayList<>(Arrays.asList('@','.'));
        boolean valid=true;
        if(!(str.contains("@") && str.contains(".")))
            return false;
        else if(str.startsWith("@") || str.startsWith("."))
            return false;
        else if(str.endsWith("@") || str.endsWith("."))
            return false;
        
        for(int i=0; i<str.length(); i++)
        {
            if(! (Character.isLetterOrDigit(str.charAt(i)) || allowed.contains(str.charAt(i))))
            {
                valid=false;
                break;
            }
        }
        return valid;
    }
    public static boolean isNIC(String str)
    {
        boolean valid;
        if(str.length() == 10)
            valid = true;
        else 
            return false;
        for(int i=0;i<9;i++)
            if(! (Character.isDigit(str.charAt(i))))
            {
                valid=false;
                break;
            }
        if (!valid)
            return false;
        if(str.charAt(9) == 'v' || str.charAt(9) == 'V')
            return true;
        else
            return false;
    }
    public static boolean isInteger(String str)
    {
        boolean valid=true;
        for(int i=0; i<str.length(); i++)
        {
            if(! (Character.isDigit(str.charAt(i))))
            {
                valid=false;
                break;
            }
        }
        return valid;
    }
    public static boolean isDouble(String str)
    {
        List<Character> allowed=new ArrayList<>(Arrays.asList('.'));
        
        boolean valid=true;
        for(int i=0; i<str.length(); i++)
        {
            if(! (Character.isDigit(str.charAt(i)) || allowed.contains(str.charAt(i))))
            {
                valid=false;
                break;
            }
        }
        return valid;
    }
    public static boolean isExistingNIC(String str, dbConcurrent nbconn)
    {
        boolean valid = true;
        try 
        {
            PreparedStatement prp = nbconn.get().prepareStatement("select * from `person` where `nic` = ?");
            prp.setString(1,str);
            ResultSet rs = prp.executeQuery();
            valid = rs.next();
        } 
        catch (SQLException e)
        {
            System.out.println("Error checking NIC existence\n" + e);
        }
        return valid;
    }
}
