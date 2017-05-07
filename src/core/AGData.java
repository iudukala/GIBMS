/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Isuru Udukala
 */
public class AGData
{
    private static AGData instance = null;
    private final Map<String, Object> data = new HashMap<>();
    
    public static AGData getInstance()
    {
        if(instance == null)
            instance = new AGData();
        return instance;
    }
    
    public void add(String key, Object value)
    {
        key = key.toLowerCase();
        if(value!=null)
            data.put(key, value);
    }
    
    public Object get(String key)
    {
        return data.get(key);
    }

    @Override
    public String toString()
    {
        return "AGData{" + "data=" + data + '}';
    }
    
    
}
