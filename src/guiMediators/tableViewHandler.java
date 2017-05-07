/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiMediators;

import core.Entity;
import handlers.dbConcurrent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 *
 * @author Isuru Udukala
 */
public class tableViewHandler
{
    private final dbConcurrent nbconn;
    private final TableView<Entity> table;
    private final String query;
    
    private String table_name = null;
    
    public tableViewHandler(TableView table, String query, dbConcurrent nbconn)
    {
        this.table = table;
        this.nbconn = nbconn;
        this.query = query;
    }
    
    public void writeToTable()
    {
        if(nbconn.get() == null)
        {
            table.getItems().clear();
            return;
        }
        
        List<Entity> entity_list = null;
        try{
            ResultSet rs = nbconn.get().createStatement().executeQuery(query);
            table_name = rs.getMetaData().getTableName(1);
            entity_list = Entity.parseFromRS(rs, nbconn);
        }
        catch (SQLException e){
            System.out.println("Error executing SQL statement in writeToTable()\n" + e);
        }
        writeData(entity_list);
    }
    
    public void writeToTable(ResultSet rs)
    {
        writeData(Entity.parseFromRS(rs, nbconn));
    }
    
    private void writeData(List<Entity> entity_list)
    {
        if(entity_list.isEmpty() || nbconn.get() == null)
        {
            table.getItems().clear();
            return;
        }
        
        List<TableColumn<Entity,String>> column_list = new ArrayList<>();
        
        for(String colname : entity_list.get(0).getColumnNamesFromEntity())
        {
            TableColumn<Entity,String> column = new TableColumn<>(colname.toUpperCase());
            column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Entity, String>, ObservableValue<String>>()
            {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Entity, String> param)
                {
                    return new SimpleStringProperty(param.getValue().getAsString(colname));
                }
            });
            column_list.add(column);
        }
        
        table.getColumns().clear();
        table.getColumns().addAll(column_list);
        ObservableList<Entity> items = FXCollections.observableArrayList(entity_list);
        table.getItems().clear();
        table.setItems(items);
    }
    
    public Entity fetchExtendedSelection(String ovr_searchtable, String over_tvpkey, String ovr_dbpkey)
    {
        String exsearch_query = "select * from `" + ovr_searchtable + "` where `" + ovr_dbpkey + "` = ?";
        PreparedStatement prp;
        
        Object identifier;
        try{
            identifier = table.getSelectionModel().getSelectedItem().getAsString(over_tvpkey);
            if(identifier==null)
                return null;
        }
        catch(Exception e){
            return null;
        }
        
        //testingmarker
        System.out.println(exsearch_query);
        Entity selected_entity;
        try{
            prp = nbconn.get().prepareStatement(exsearch_query);
            prp.setObject(1, Entity.recastJavaObject(identifier));
            selected_entity = Entity.parseFromRS(prp.executeQuery(), nbconn).get(0);
        }
        catch(SQLException e){
            System.out.println("Failed to fetch extended table-selection data from DB :\n" + e);
            return null;
        }
        
        return selected_entity;
    }
    
    public Entity fetchExtendedSelection(String ovr_searchtable, String ovr_pkey)
    {
        return fetchExtendedSelection(ovr_searchtable, ovr_pkey, ovr_pkey);
    }
    
    
    public Entity getSelection()
    {
        return table.getSelectionModel().getSelectedItem();
    }
    
    public Entity getSelection(String pkeyName)
    {
        return fetchExtendedSelection(table_name, pkeyName);
    }
}