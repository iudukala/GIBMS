/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import core.Entity;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Isuru Udukala
 */
public interface ComponentInterface 
{
    
    
    public void add(String key, Object control);
    public Entity getValues();
    public void setValues(Entity entity);
}
