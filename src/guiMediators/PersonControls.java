/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiMediators;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import core.Entity;
import core.Manipulator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author Isuru Udukala
 */
public class PersonControls
{
    public final String TABLE_NAME = "person";
    Map<String,Object> nodeList = new HashMap<>();
    
    public void add(String key, Object obj)
    {
        nodeList.put(key, obj);
    }
    
    public Entity getValues()
    {
        Entity person = new Entity(TABLE_NAME);
        for(Entry<String,Object> entry : nodeList.entrySet())
        {
            String key = entry.getKey();
            Object control = entry.getValue();
            if(entry.getValue().getClass().equals(ToggleGroup.class))
            {
                ToggleGroup tg = (ToggleGroup)control;
                person.add(key, tg.getSelectedToggle().getUserData());
            }
            else if(control.getClass().equals(JFXTextField.class))
                person.add(key, ((JFXTextField)control).getText());
            else if(control.getClass().equals(JFXDatePicker.class))
                person.add(key,((JFXDatePicker)control).getValue());
        }
        return person;
    }
    
    public void setValues(Entity person)
    {
        for(Entry<String,Object> entry : nodeList.entrySet())
        {
            String key = entry.getKey();
            Object control = entry.getValue();
            if(entry.getValue().getClass().equals(ToggleGroup.class))
                Manipulator.setToggleSelection((ToggleGroup)control, person.getString(key));
            else if(control.getClass().equals(JFXTextField.class))
                ((JFXTextField)control).setText(person.getString(key));
            else if(control.getClass().equals(JFXDatePicker.class))
                ((JFXDatePicker)control).setValue(person.getLocalDate(key));
        }
    }
}
