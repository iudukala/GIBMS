/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiriya;

import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import data.*;
import entities.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ASUS-PC
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TextField idnumber;
    @FXML
    private TextField fname;
    @FXML
    private DatePicker date;
    @FXML
    private TextField contactno;
    @FXML
    private TextField bname;
    @FXML
    private TextField bnumber;
    @FXML
    private TextField noshare;
    @FXML
    private TextField shareprice;
    @FXML
    private DatePicker sharerangestart;
    @FXML
    private DatePicker sharerangeclose;
    @FXML
    private Button add;
    
    Connection conn=null;
    
    
    @FXML
    private TextField addres;
    @FXML
    private TextField emai;
    @FXML
    private TextField descriptio;
    @FXML
    private TableColumn<?, ?> name;
    @FXML
    private TableView<?> TableLoad;
    @FXML
    private TableColumn<?, ?> nic;
    @FXML
    private TableColumn<?, ?> shareno;
    @FXML
    private TableColumn<?, ?> sharepric;
    @FXML
    private TableColumn<?, ?> sharestart;
    @FXML
    private TableColumn<?, ?> shareclose;
    @FXML
    private TextField unic;
    @FXML
    private TextField uname;
    @FXML
    private TextField uaddress;
    @FXML
    private DatePicker udob;
    @FXML
    private TextField uphone;
    @FXML
    private TextField uemail;
    @FXML
    private TextField ubname;
    @FXML
    private TextField ubnumber;
    @FXML
    private TextField ushareamount;
    @FXML
    private TextField ushareprice;
    @FXML
    private DatePicker usharerangestart;
    @FXML
    private DatePicker usharerangeclose;
    @FXML
    private TextField udescription;
    @FXML
    private Button update;
    @FXML
    private TextField usearch;
    @FXML
    private TableColumn<?, ?> tu_nic;
    @FXML
    private TableColumn<?, ?> tu_name;
    @FXML
    private TableColumn<?, ?> tu_shareamount;
    @FXML
    private TableColumn<?, ?> tu_shareprice;
    @FXML
    private TableColumn<?, ?> tu_sharerangestart;
    @FXML
    private TableColumn<?, ?> tu_sharerangeclose;
    @FXML
    private Button uselectt;
    @FXML
    private TableView<?> utable;
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
       conn = gibms.dbConnect.connect();
       DynamicTable.getColumns(conn, "select p.nic, p.full_name  , s.share_amount, s.share_price, s.share_range_start, s.share_range_close\n" +
"from person p ,shareholder s where p.nic=s.nic" , utable);
       
        utable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> 
        {
            if (newSelection != null)
            {
                int index=utable.getSelectionModel().getSelectedIndex();
                String x=utable.getItems().get(index).toString();
                String nic = x.split(",")[0].substring(1);
                System.out.println(nic);
                unic.setDisable(true);
                
                shareholder shareholder_object = shareholder_search.shareholderFromSQL(nic, conn);
                Person person_object = intFunc.customer_search.personFromSQL(nic, conn);
                
                unic.setText(person_object.nic);
                uname.setText(person_object.name);
                uemail.setText(person_object.email);
                uphone.setText(person_object.personal_phone);
                uaddress.setText(person_object.home_address);
                udob.setValue(LocalDate.parse(person_object.dob,DateTimeFormatter.ISO_DATE));
                
                unic.setText(shareholder_object.nic);
                ubname.setText(shareholder_object.bank_name);
                ubnumber.setText(Integer.toString(shareholder_object.account_no));
                ushareamount.setText(Integer.toString(shareholder_object.share_amount));
                ushareprice.setText(Double.toString(shareholder_object.share_price));
                usharerangestart.setValue(shareholder_object.share_range_start);
                usharerangeclose.setValue(shareholder_object.share_range_close);
                udescription.setText(shareholder_object.description);
            }
        });
        
        
    }
    
    @FXML
    private void handleAdd(ActionEvent event) throws Exception 
    {
        String nic = idnumber.getText();
        System.out.println(nic);
        
            String name = fname.getText();
            System.out.println(name);
            
            String email =emai.getText();
            System.out.println(email);
            
            LocalDate dob = date.getValue();
            System.out.println(dob.toString());
            
            String personal_phone = contactno.getText();
            System.out.println(personal_phone);
            
            String home_address = addres.getText();
            System.out.println(home_address);
            
            String bank_name =bname.getText();
            System.out.println(bank_name);
            
            int account_no =Integer.valueOf(bnumber.getText()) ;
            System.out.println(account_no);
            
            int share_amount =Integer.valueOf (noshare.getText());
            System.out.println(share_amount);
            
            Double share_price =Double.valueOf(shareprice.getText());
            System.out.println(share_price);
            
            LocalDate share_range_start = sharerangestart.getValue();
            System.out.println(share_range_start);
            
            LocalDate share_range_close = sharerangeclose.getValue();
            System.out.println(share_range_close);
            
            String description = descriptio.getText();
            System.out.println(description);
            
            alert_box.add_btn.display("info","shares added successfully");
       try
       {
           Person person_object = new Person (name,nic,dob,personal_phone,home_address,'.','.',email);
            person_object.toString();
            person_object.consolidate(conn);
            
            shareholder shareholder_object = new shareholder(nic,bank_name,account_no,share_amount,share_price,share_range_start,share_range_close,description);
            shareholder_object.toString();
            shareholder_object.consolidate(conn);
            } 
            catch(Exception e)
            {
                System.out.println("EX" + e);   
            }
    }
    
    @FXML
    private void handle_update(ActionEvent event)
    {
//        try {
//            PreparedStatement prp = conn.prepareStatement("delete from person where nic = ?");
//            prp = conn.prepareStatement("delete from person where nic = ?");
//            
//            Person person_object = new Person (uname,unic,udob,uphone,uaddress,'.','.',uemail);
//            person_object.toString();
//            person_object.consolidate(conn);
//            
//            
//            shareholder shareholder_object = new shareholder(nic,bank_name,account_no,share_amount,share_price,share_range_start,share_range_close,description);
//            shareholder_object.toString();
//            shareholder_object.consolidate(conn);
//      
//            String nic = unic.getText();
//        
//        
//            String name = uname.getText();
//            
//            
//            String email =uemail.getText();
//            
//            
//            LocalDate dob = udob.getValue();
//            
//            
//            String personal_phone = uphone.getText();
//            
//            
//            String home_address = uaddress.getText();
//            
//            
//            String bank_name =ubname.getText();
//            
//            
//            int account_no =Integer.valueOf(ubnumber.getText()) ;
//            
//            
//            int share_amount =Integer.valueOf (ushareamount.getText());
//            
//            
//            Double share_price =Double.valueOf(ushareprice.getText());
//            
//            
//            LocalDate share_range_start = usharerangestart.getValue();
//            
//            
//            LocalDate share_range_close = usharerangeclose.getValue();
//            
//            
//            String description = udescription.getText();
//            prp.executeUpdate();
//              } 
//        
//            
//        catch (SQLException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        
//        
        
//    try
//        {
//           
//           
//           PreparedStatement prp = conn.prepareStatement("delete from shareholder where nic = ?");
//            
//            
//            prp.setString(1, shareholder_object.nic);
//            prp.setString(2, shareholder_object.bank_name);
//            prp.executeUpdate();
//            prp = conn.prepareStatement("delete from person where nic = ?");
//            prp.setString(1, person_object.nic);
//            prp.executeUpdate();
//        }
//        catch(SQLException e)
//        {
//            System.out.println("Error removing customer\n" + e);
//        }  
//       
 
        
        
    }

    @FXML
    private void handleselectt(ActionEvent event)
    {
        String search = usearch.getText();
        data.DynamicTable.buildData(conn, "select p.nic, p.full_name  , s.share_amount, s.share_price, s.share_range_start, s.share_range_close\n" +
"from person p ,shareholder s where p.nic=s.nic and p.nic like ?", search, utable);
        
    }

   
    
}