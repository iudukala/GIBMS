/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legacyFXML;

import legacyEntities.Vehicle;
import legacyEntities.Other;
import legacyEntities.Building;
import legacyEntities.Bill;
import handlers.DynamicTable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.time.LocalDate;

/**
 *
 * @author xidew
 */
public class ResourceFXML_oldController implements Initializable
{
    Connection conn = null;
    @FXML
    private Button vehadd;
    @FXML
    private Button vehdel;
    @FXML
    private TextField vehsearch;
    @FXML
    private Button vehedit;
    @FXML
    private Button vehsh;
    @FXML
    private TextField brand;
    @FXML
    private TextField mod;
    @FXML
    private TextField lice;
    @FXML
    private TextField colo;
    @FXML
    private TextField engi;
    @FXML
    private TextField year;
    @FXML
    private Button builadd;
    @FXML
    private Button builedit;
    @FXML
    private Button buildel;
    @FXML
    private TextField builsearch;
    @FXML
    private Button builser;
    @FXML
    private TextField builaddr;
    @FXML
    private TextField builfl;
    @FXML
    private DatePicker buildate;
    @FXML
    private TextField builcon;
    @FXML
    private TextField builval;
    @FXML
    private DatePicker builend;
    @FXML
    private Button taxadd;
    @FXML
    private Button taxedit;
    @FXML
    private Button taxdel;
    @FXML
    private Button taxser;
    @FXML
    private TextField taxse;
    @FXML
    private TextField tax;
    @FXML
    private  DatePicker taxdate;
    @FXML
    private TextField taxarr;
    @FXML
    private TextField taxamo;
    @FXML
    private TextField watser;
    @FXML
    private Button watsearch;
    @FXML
    private TextField watno;
    @FXML
    private TextField watuni;
    @FXML
    private DatePicker watdate;
    @FXML
    private TextField watarr;
    @FXML
    private TextField watamo;
    @FXML
    private Button watadd;
    @FXML
    private Button watdel;
    @FXML
    private Button watedit;
    @FXML
    private TextField eleser;
    @FXML
    private Button elesearch;
    @FXML
    private TextField eleno;
    @FXML
    private TextField eleuni;
    @FXML
    private DatePicker eledate;
    @FXML
    private TextField elearr;
    @FXML
    private TextField eleamo;
    @FXML
    private Button eleadd;
    @FXML
    private Button eleedit;
    @FXML
    private Button eledel;
    @FXML
    private TextField telno;
    @FXML
    private TextField telcha;
    @FXML
    private DatePicker teldate;
    @FXML
    private TextField telarr;
    @FXML
    private TextField telamo;
    @FXML
    private Button teladd;
    @FXML
    private Button teledit;
    @FXML
    private Button teldel;
    //@FXML
    //private TableView<?> teltab;
    @FXML
    private TextField othbill;
    @FXML
    private TextField othcat;
    @FXML
    private TextField othdes;
    @FXML
    private TextField othpri;
    @FXML
    private TextField othus;
    @FXML
    private TextField othqun;
    @FXML
    private DatePicker othdate;
    //@FXML
    //private TextField othser;
    @FXML
    private Button othadd;
    @FXML
    private Button othedit;
    @FXML
    private Button othdel;
    @FXML
    private Button othsearch;
    @FXML
    private TextField builde;
    @FXML
    private TextField othser;
    @FXML
    private Button telsearch;
    @FXML
    private TextField telsear;
    
    @FXML
    private void handle_vehicleadd(ActionEvent event)
    {
        String brandstr = brand.getText();
        String license_no = lice.getText();
        String model = mod.getText();
        String colour = colo.getText();
        String engine_no = engi.getText();
        String yearstr = year.getText();
        
        Vehicle vehicle_object = new Vehicle(brandstr,license_no,model,colour,engine_no,yearstr);
        System.out.println(vehicle_object.toString());
        vehicle_object.consolidate(conn);    
    }
    
    @FXML
    private void handle_addbuilding(ActionEvent event)
    {
        
        String address = builaddr.getText();
        String floor = builfl.getText();
        double rent_value = Double.parseDouble(builval.getText());
        String description = builde.getText();
        LocalDate end_date = builend.getValue();
        LocalDate rent_date = buildate.getValue();
        String condition = builcon.getText();
        
        Building building_object = new Building(address,floor,description,condition,rent_value,end_date,rent_date);
        System.out.println(building_object.toString());
        //building_object.consolidate(conn);
    }
    
    @FXML
    private void handle_addtax(ActionEvent event)
    {
        
        String tax_no = tax.getText();
        LocalDate date  = taxdate.getValue();
        double arrears = Double.parseDouble(taxarr.getText());
        double amount = Double.parseDouble(taxamo.getText());
       
        
        Bill tax_object = new Bill(tax_no, date, 0, amount,arrears,0);
        System.out.println(tax_object.toString());
        tax_object.consolidate(conn);
    }
    
    @FXML
    private void handle_addwater(ActionEvent event)
    {
        
        String bill_no = watno.getText();
        LocalDate date  = watdate.getValue();
        double arrears = Double.parseDouble(watarr.getText());
        double amount = Double.parseDouble(watamo.getText());
        double units = Double.parseDouble(watuni.getText());
       
        
        Bill wat_object = new Bill(bill_no, date,units, amount,arrears,0);
        System.out.println(wat_object.toString());
        wat_object.consolidate(conn);
    }
    
    @FXML
    private void handle_elect(ActionEvent event)
    {
        
        String bill_no = eleno.getText();
        LocalDate date  = eledate.getValue();
        double arrears = Double.parseDouble(elearr.getText());
        double amount = Double.parseDouble(eleamo.getText());
        double units = Double.parseDouble(eleuni.getText());
       
        
        Bill wat_object = new Bill(bill_no, date,units, amount,arrears,0);
        System.out.println(wat_object.toString());
        wat_object.consolidate(conn);
    }
    
    @FXML
    private void handle_tele(ActionEvent event)
    {
        
        String bill_no = telno.getText();
        LocalDate date  = teldate.getValue();
        double arrears = Double.parseDouble(telarr.getText());
        double amount = Double.parseDouble(telamo.getText());
        double charges = Double.parseDouble(telcha.getText());
       
        
        Bill tel_object = new Bill(bill_no, date,0, amount,arrears,charges);
        System.out.println(tel_object.toString());
        tel_object.consolidate(conn);
    }
    
    @FXML
    private void handle_other(ActionEvent event)
    {
        
        int bill_id = Integer.parseInt(othbill.getText());
        String category = othcat.getText();
        String description = othdes.getText();
        double price = Double.parseDouble(othpri.getText());
        double usage = Double.parseDouble(othus.getText());
        double quantity = Double.parseDouble(othqun.getText());
        LocalDate date  = othdate.getValue();
        
        Other other_object = new Other(bill_id,category,description,price,usage,quantity,date);
        System.out.println(other_object.toString());
        other_object.consolidate(conn);
    }
    
    @FXML private TableView table_vehsearch;
    @FXML private TableView table_builsearch;
    @FXML private TableView table_bill;
    @FXML private TableView table_other;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        conn=handlers.dbConcurrent.connect();
        DynamicTable.getColumns(conn, "select * from vehicle", table_vehsearch);
        DynamicTable.getColumns(conn, "select * from building", table_builsearch);
        DynamicTable.getColumns(conn, "select * from bill", table_bill);
        DynamicTable.getColumns(conn, "select * from other_bill", table_other);
        
        
        table_vehsearch.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> 
        {
            if (newSelection != null)
            {
                int index=table_vehsearch.getSelectionModel().getSelectedIndex();
                String x=table_vehsearch.getItems().get(index).toString();
                String nic = x.split(",")[0].substring(1);
                System.out.println(nic);
            }
        });
        
        table_builsearch.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> 
        {
            if (newSelection != null)
            {
                int index=table_builsearch.getSelectionModel().getSelectedIndex();
                String x=table_builsearch.getItems().get(index).toString();
                String nic = x.split(",")[0].substring(1);
                System.out.println(nic);
            }
        });
        
        table_bill.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> 
        {
            if (newSelection != null)
            {
                int index=table_bill.getSelectionModel().getSelectedIndex();
                String x=table_bill.getItems().get(index).toString();
                String nic = x.split(",")[0].substring(1);
                System.out.println(nic);
            }
        });
        
        table_other.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> 
        {
            if (newSelection != null)
            {
                int index=table_other.getSelectionModel().getSelectedIndex();
                String x=table_other.getItems().get(index).toString();
                String nic = x.split(",")[0].substring(1);
                System.out.println(nic);
            }
        });
        
        
    }
    
    @FXML
    private void handle_vehiclesearch(ActionEvent event)
    {
        String search = vehsearch.getText();
        handlers.DynamicTable.buildData(conn, "select * from vehicle where `license` like ?", search, table_vehsearch);
    }
    @FXML
    private void handle_buildingsearch(ActionEvent event)
    {
        String search = builsearch.getText();
        DynamicTable.buildData(conn, "select * from building where `building_id` like ?",search, table_builsearch);
    }
    @FXML
    private void handle_billsearch(ActionEvent event)
    {
        String search = taxse.getText();
       // String search = watser.getText();
       // String search = eleser.getText();
       // String search = telsear.getText();
        
        DynamicTable.buildData(conn, "select * from bill where `bill_id` like ?",search ,table_bill);
    }
    @FXML
    private void handle_othersearch(ActionEvent event)
    {
        String search = othser.getText();
        DynamicTable.buildData(conn, "select * from other_bill where `bill_id` like ?",search,table_other);
    }
}