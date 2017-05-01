/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiMediators;

import core.Entity;
import core.PreparedStatementWrapper;
import handlers.dbConcurrent;
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
    
    private String table_name = null;
    
    public tableViewHandler(TableView table, dbConcurrent nbconn)
    {
        this.table = table;
        this.nbconn = nbconn;
    }
    
    public void writeToTable(String query)
    {
        List<Entity> entity_list = null;
        try
        {
            ResultSet rs = nbconn.get().createStatement().executeQuery(query);
            table_name = rs.getMetaData().getTableName(1);
            entity_list = Entity.parseFromRS(rs, nbconn);
        }
        catch (SQLException e)
        {
            System.out.println("Error executing SQL statement in writeToTable()\n" + e);
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
        table.setItems(items);
    }
    
    public Entity getSelection(String ovr_tname, String pkeyName)
    {
        String query = "select * from `" + ovr_tname + "` where `" + pkeyName + "` = ?";
        PreparedStatementWrapper prpw = new PreparedStatementWrapper(nbconn, query);
        
        Object identifier = table.getSelectionModel().getSelectedItem().getString(pkeyName);
        prpw.setObject(1, identifier);
        Entity selected_entity = Entity.parseFromRS(prpw.executeQuery(), nbconn).get(0);
        
        return selected_entity;
    }
    public Entity getSelection(String pkeyName)
    {
        return getSelection(table_name, pkeyName);
    }
}