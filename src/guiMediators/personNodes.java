/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiMediators;

import core.Entity;
import java.util.Map;
import java.util.Map.Entry;
import javafx.scene.Node;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author Isuru Udukala
 */
public class personNodes
{
    public final String TABLE_NAME = "person";
    Map<String,Node> nodeList;
    
    public void add(String key, Node node)
    {
        nodeList.put(key, node);
    }
    
    public Entity getValues()
    {
        for(Entry<String,Node> entry : nodeList.entrySet())
        {
            if(entry.getKey().equals("gender"))
            {
                //ToggleGroup tg = (ToggleGroup)entry.getValue();
                //char gender=(()entry.getValue().getSelectedToggle().toString().endsWith("'Male'"))? 'm':'f';
            }
        }
        return null;
    }
}
