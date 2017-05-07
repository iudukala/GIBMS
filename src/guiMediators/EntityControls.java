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
import handlers.ValidationHandler;
import handlers.ValidationInterface;
import handlers.dbConcurrent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Control;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Isuru Udukala
 */
public class EntityControls
{
    public final String TABLE_NAME;
    private final dbConcurrent nbconn;
    private final Map<String,Object> nodeList = new LinkedHashMap<>();
    private int dyn_val_count = 0;
    private final String VALIDATION_IDENTIFIER = "EXCLUSION-VAL-";
    private Entity entity;
    private boolean disabled;
    
    public EntityControls(String tablename, dbConcurrent nbconn)
    {
        TABLE_NAME = tablename;
        this.nbconn = nbconn;
        disabled = false;
    }
    
    public void add(String key, Object obj)
    {
        key = key.toLowerCase();
        nodeList.put(key, obj);
    }
    
    public void add(Object entries[][])
    {
        List<Control> nic_dob = new ArrayList<>();
        for(int i=0;i<entries.length;i++)
        {
            Object key = entries[i][0];
            Object control = entries[i][1];
            if(entries[i].length == 2)
                add(key.toString(), control);
            
            else if(entries[i].length == 3)
            {
                Object validator = entries[i][2];
                if(ValidationInterface.class.isAssignableFrom(validator.getClass()))
                    add(key.toString(), control, (ValidationInterface)validator);
                if(validator.getClass().equals(ValidationHandler.NICValidator.class))
                    nic_dob.add(0, (Control)control);
            }
        }
    }
    
    public <E extends ValidationInterface> void add(String key, Object obj, E validator)
    {
        add(key, obj);
        
        if(obj.getClass().equals(JFXTextField.class))
            validator.register((JFXTextField)obj);
        else if(obj.getClass().equals(JFXDatePicker.class))
        {
            //strong gridpane assumption
            JFXDatePicker datepicker = (JFXDatePicker)obj;
            GridPane grid = (GridPane)datepicker.getParent();
            
            JFXTextField vtf = new JFXTextField();
            
            //adding to entityControl with identifier
            String ec_val_key = VALIDATION_IDENTIFIER + dyn_val_count++;
            this.add(ec_val_key, vtf);
            
            vtf.setStyle("-jfx-focus-color: transparent; -jfx-unfocus-color: transparent; -fx-text-fill: transparent;");
            
            validator.register(vtf);
            datepicker.valueProperty().addListener(new ChangeListener<LocalDate>()
            {
                @Override
                public void changed(ObservableValue<? extends LocalDate> ov, LocalDate oldValue, LocalDate newValue)
                {
                    try
                    {
                        vtf.setText(newValue.format(DateTimeFormatter.ISO_DATE));
                        vtf.validate();
                    }
                    catch(Exception e){}
                }
            });
            
            Integer column = GridPane.getColumnIndex(datepicker), row = GridPane.getRowIndex(datepicker);
            if(column == null){
                column = 0;
                //System.out.println("Date validator registration warning : null on column - " + datepicker);
            }
            if(row == null){
                row = 0;
                //System.out.println("Date validator registration warning : null on row - " + datepicker);
            }
            grid.add(vtf, column, row);
            datepicker.toFront();
        }
        else
            System.out.println("Error: attempting to register validator to invalid control : \nControl : " + obj + "\nValidator : " + validator);
    }
    
    public Entity getValues()
    {
        int countx=0;
        
        entity = new Entity(TABLE_NAME, nbconn);
        for(Entry<String,Object> entry : nodeList.entrySet())
        {
            String key = entry.getKey();
            Object control = entry.getValue();
            
            if(control.getClass().equals(JFXTextField.class) || control.getClass().equals(JFXDatePicker.class))
                if(((Control)control).isDisabled())
                    continue;
            try
            {
                if(entry.getValue().getClass().equals(ToggleGroup.class))
                {
                    ToggleGroup tg = (ToggleGroup)control;
                    if(tg.getSelectedToggle()!=null)
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
                                if(!value.equals(""))
                                {
                                    if(list.get(1).equals("double"))
                                        entity.add(key, Double.parseDouble(value));
                                    else if(list.get(1).equals("int"))
                                        entity.add(key, Integer.parseInt(value));
                                    else if(list.get(1).equals("long"))
                                        entity.add(key, Long.parseLong(value));
                                }
                            }
                        }
                }
            }
            catch(Exception e){System.out.println(entry.getValue() + "nulhhhl" + countx++);}
        }
        return entity;
    }
    
    public void addToEntity(String key, Object value)
    {
        entity.add(key, value);
    }
    
    public void setValues(Entity entity)
    {
        if(entity == null)
            return;
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
        
        List<String> primaryKeys = entity.fetchPrimaryKeys();
        for(Iterator<String> iterator = primaryKeys.listIterator(); iterator.hasNext();)
        {
            String key = iterator.next();
            if(nodeList.get(key)!=null)
                ((Control)nodeList.get(key)).setDisable(true);
        }
    }
    
    public boolean triggerValidators()
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
                    valid = (textField.validate());// && !textField.getText().equals(""));
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
        disabled = true;
    }
    
    public void enable()
    {
        for(Entry<String,Object> entry : nodeList.entrySet())
            ((Control)entry.getValue()).setDisable(false);
        disabled = false;
    }
    
    public boolean isDisabled()
    {
        return disabled;
    }
    
    public int getAGKey()
    {
        return entity.getAGKey();
    }
    
    @Override
    public String toString()
    {
        StringBuilder strb = new StringBuilder();
        strb.append("-----[").append(TABLE_NAME).append("] EntityControls-------------\n");
        for(Entry<String,Object> entry : nodeList.entrySet())
        {
            strb.append(Manipulator.formatTabs(entry.getKey(),3,true))
                    .append(" - ").append(entry.getValue()).append("\n");
        }
        return strb.toString();
    }
}
