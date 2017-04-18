/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

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
        List<Character> allowed=new ArrayList<>(Arrays.asList('+','-','(',')', ' '));
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
        {
            if(! (Character.isDigit(str.charAt(i))))
            {
                valid=false;
                break;
            }
        }
        if (!valid)
                return false;
        if(str.charAt(9) == 'v' || str.charAt(9) == 'V')
            return true;
        else
            return false;
    }
    public static boolean isNumber(String str)
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
}
