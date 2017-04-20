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
import handlers.ComponentInterface;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javafx.scene.control.Control;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author Isuru Udukala
 */
public class EntityControls implements ComponentInterface
{
    public final String TABLE_NAME;
    final Map<String,Object> nodeList = new HashMap<>();
    
    public EntityControls(String tablename)
    {
        TABLE_NAME = tablename;
    }
    
    @Override
    public void add(String key, Object obj)
    {
        nodeList.put(key, obj);
    }
    
    @Override
    public Entity getValues()
    {
        Entity entity = new Entity(TABLE_NAME);
        for(Entry<String,Object> entry : nodeList.entrySet())
        {
            String key = entry.getKey();
            Object control = entry.getValue();
            
            if(control.getClass().equals(JFXTextField.class) || control.getClass().equals(JFXDatePicker.class))
                if(((Control)control).isDisabled())
                    continue;
            
            if(entry.getValue().getClass().equals(ToggleGroup.class))
            {
                ToggleGroup tg = (ToggleGroup)control;
                entity.add(key, tg.getSelectedToggle().getUserData());
            }
            else if(control.getClass().equals(JFXTextField.class))
                entity.add(key, ((JFXTextField)control).getText());
            else if(control.getClass().equals(JFXDatePicker.class))
                entity.add(key,((JFXDatePicker)control).getValue());
        }
        return entity;
    }
    
    @Override
    public void setValues(Entity entity)
    {
        for(Entry<String,Object> entry : nodeList.entrySet())
        {
            String key = entry.getKey();
            Object control = entry.getValue();
            if(entry.getValue().getClass().equals(ToggleGroup.class))
                Manipulator.setToggleSelection((ToggleGroup)control, entity.getString(key));
            else if(control.getClass().equals(JFXTextField.class))
                ((JFXTextField)control).setText(entity.getString(key));
            else if(control.getClass().equals(JFXDatePicker.class))
                ((JFXDatePicker)control).setValue(entity.getLocalDate(key));
        }
    }
    
    public boolean validateValues()
    {
        boolean valid = true;
        for(Entry<String, Object> entry : nodeList.entrySet())
        {
            String key = entry.getKey();
            Object control = entry.getValue();
            
            if(control.getClass().equals(JFXTextField.class) || control.getClass().equals(JFXDatePicker.class))
                if(((Control)control).isDisabled())
                    continue;
            
            if(control.getClass().equals(JFXTextField.class))
            {
                JFXTextField textField = (JFXTextField)control;
                
                //System.out.println(textField + " empty? " + textField.getText().equals("") + "----" + TABLE_NAME);
                if(! (textField.getValidators().isEmpty()) )
                {
                    valid = (textField.validate() && !textField.getText().equals(""));
                    if(!valid)
                        return false;
                }
            }
        }
        return valid;
    }
}
