/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.Map.Entry;

/**
 *
 * @author Isuru Udukala
 */
public class Retriever 
{
    public static String getClassStr(Object obj)
    {
        String class_str=null;
        try
        {
            if(obj.getClass().equals(Class.forName("java.util.HashMap$Node")))
                class_str = ((Entry)obj).getValue().getClass().toString();
            else if(obj.getClass().equals(Class.class))
                class_str = ((Class)obj).toString();
            else
                class_str = obj.getClass().toString();
        }
        catch(ClassNotFoundException e){}
        
        if(class_str!=null)
            return class_str.replace("class java.lang.", "").replace("class java.time.", "");
        else
            return null;
    }
    
}
