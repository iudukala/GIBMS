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
import handlers.ValidationInterface;
import handlers.dbConcurrent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javafx.scene.control.Control;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author Isuru Udukala
 */
public class EntityControls
{
    public final String TABLE_NAME;
    private final dbConcurrent nbconn;
    private final Map<String,Object> nodeList = new HashMap<>();
    private Entity entity;
    
    public EntityControls(String tablename, dbConcurrent nbconn)
    {
        TABLE_NAME = tablename;
        this.nbconn = nbconn;
    }
    
    public void add(String key, Object obj)
    {
        key = key.toLowerCase();
        nodeList.put(key, obj);
    }
    
    public <E extends ValidationInterface> void add(String key, Object obj, E validator)
    {
        //nodeList.put(key, obj);
        add(key, obj);
        
        if(obj.getClass().equals(JFXTextField.class))
            validator.register((JFXTextField)obj);
        else
            System.out.println("Error: attempting to register validator to invalid control");
    }
    
    public Entity getValues()
    {
        entity = new Entity(TABLE_NAME, nbconn);
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
            else if(control.getClass().equals(JFXDatePicker.class))
                entity.add(key,((JFXDatePicker)control).getValue());
            else if(control.getClass().equals(JFXTextField.class))
            {
                String value = ((JFXTextField)control).getText();
                
                List<List> tdata = entity.fetchTableStructure();
                
                for(List list : tdata)
                    for(int i=0;i<list.size();i++)
                    {
                        if(list.get(0).equals(key))
                        {
                            if(list.get(1).equals("varchar"))
                                entity.add(key, value);
                            else if(list.get(1).equals("double"))
                                entity.add(key, Double.parseDouble(value));
                            else if(list.get(1).equals("int"))
                                entity.add(key, Integer.parseInt(value));
                        }
                    }
            }
        }
        return entity;
    }
    
    public void addToEntity(String key, Object value)
    {
        entity.add(key, value);
    }
    
    public void setValues(Entity entity)
    {
        for(Entry<String,Object> entry : nodeList.entrySet())
        {
            String key = entry.getKey();
            Object control = entry.getValue();
            if(entry.getValue().getClass().equals(ToggleGroup.class))
                Manipulator.setToggleSelection((ToggleGroup)control, entity.getString(key));
            else if(control.getClass().equals(JFXTextField.class))
                ((JFXTextField)control).setText(entity.getAsString(key));
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
    
    public void clearControls()
    {
        for(Entry<String,Object> entry : nodeList.entrySet())
        {
            String key = entry.getKey();
            Object control = entry.getValue();
            if(entry.getValue().getClass().equals(ToggleGroup.class))
                ((ToggleGroup)control).selectToggle(null);
            
            else if(control.getClass().equals(JFXDatePicker.class))
                ((JFXDatePicker)control).setValue(null);
            else if(control.getClass().equals(JFXTextField.class))
            {
                JFXTextField textField = (JFXTextField)control;
                textField.clear();
                if(! (textField.getValidators().isEmpty()) )
                {
                   textField.validate();
                }
            }
        }
    }
    
    public void disable()
    {
        for(Entry<String,Object> entry : nodeList.entrySet())
            ((Control)entry.getValue()).setDisable(true);
    }
    
    public void enable()
    {
        for(Entry<String,Object> entry : nodeList.entrySet())
            ((Control)entry.getValue()).setDisable(false);
    }
    
    public int getAGKey()
    {
        return entity.getAGKey();
    }
}
