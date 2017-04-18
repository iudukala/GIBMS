/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class DynamicTable
{
    public static void buildData(Connection con, String query, String key, TableView tableview)
    {
        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        tableview.getColumns().clear();
        try
        {
            PreparedStatement prp=con.prepareStatement(query);
            prp.setString(1, "%" + key + "%");
            ResultSet rs = prp.executeQuery();

            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++)
            {
                //We are using non property style for making dynamic table
                final int j = i;                
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>()
                {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param)
                    {                                                                                             
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }                    
                });
                tableview.getColumns().addAll(col); 
                //System.out.println("Column ["+i+"] ");
            }

            while(rs.next())
            {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++)
                {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                //System.out.println("Row [1] added "+row );
                data.add(row);
            }
            tableview.setItems(data);
        }
        catch(SQLException e)
        {
          e.printStackTrace();
          System.out.println("Error on Building Data\n" + e);             
        }
    }
    
    public static void buildData(Connection con, String query, TableView tableview)
    {
        buildData(con, query, "", tableview);
    }
    
    public static void getColumns(Connection con, String query, TableView tableview)
    {
          ObservableList<ObservableList> data = FXCollections.observableArrayList();
          tableview.getColumns().clear();
          try
          {
                ResultSet rs = (con.prepareStatement(query)).executeQuery();
                
                for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++)
                {
                    final int j = i;                
                    TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                    tableview.getColumns().addAll(col); 
                }
          }
          catch(Exception e)
          {
              e.printStackTrace();
              System.out.println("Column fetch error" + e);             
          }
    }
}

