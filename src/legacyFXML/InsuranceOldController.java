/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legacyFXML;

import legacyEntities.Insurance_Claim;
import legacyEntities.Insurance_Fund;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author Dumintha Wijesekara
 */
public class InsuranceOldController implements Initializable {
    
    
    @FXML
    private TextField Customer_ID_if;
    @FXML
    private TextField Insurance_Fee_if;
    @FXML
    private TextField Payed_Amount_if;
    @FXML
    private DatePicker Date_Issued_if;
    @FXML
    private DatePicker Expiry_Date_if;
    @FXML
    private Button Add_if;
    @FXML
    private Button Update_if;
    @FXML
    private Button Delete_if;
    @FXML
    private Button Search_if;
    @FXML
    private TextField Search_Bar_if;
    @FXML
    private TextField Due_Amount_if;
    @FXML
    private TableView<?> Table_if;
    
    Connection conn = null;
    
    @FXML
    private TextField Claim_Number_ic;
    @FXML
    private TextField Claim_Status_ic;
    @FXML
    private TextField Claim_Type_ic;
    @FXML
    private DatePicker Open_Date_ic;
    @FXML
    private DatePicker Closed_Date_ic;
    @FXML
    private TextField Description_ic;
    @FXML
    private Button Add_ic;
    @FXML
    private Button Update_ic;
    @FXML
    private Button Delete_ic;
    @FXML
    private TextField Search_Bar_ic;
    @FXML
    private Button Search_ic;
    @FXML
    private TableView<?> Table_ic;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        //label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn = handlers.dbConcurrent.connect();
    }    

    @FXML
    private void Add_Button_if(ActionEvent event) {
        
        try{
        
        Integer Customer_ID=Integer.valueOf(Customer_ID_if.getText());
        Double Insurance_Fee=Double.parseDouble(Insurance_Fee_if.getText());
        LocalDate Date_Issued=Date_Issued_if.getValue();
        LocalDate Expiry_Date=Expiry_Date_if.getValue();
        Double Payed_Amount=Double.parseDouble(Payed_Amount_if.getText());
        Double Due_Amount=Double.parseDouble(Due_Amount_if.getText());
            
        Insurance_Fund Insurance_Fund_Object = new Insurance_Fund(Customer_ID,Insurance_Fee,Date_Issued,Expiry_Date,Payed_Amount,Due_Amount);
        Insurance_Fund_Object.toString();
        //Insurance_Fund_Object.consolidate(conn);
        
        }
        catch(Exception e){
            
            System.out.println(e);
            
        }
        
    }

    @FXML
    private void Update_Button_if(ActionEvent event) {
    }

    @FXML
    private void Delete_Button_if(ActionEvent event) {
    }

    @FXML
    private void Search_Button_if(ActionEvent event) {
       
        String Customer_ID_Search = Search_Bar_if.getText();
        handlers.DynamicTable.buildData(conn, "select c.Customer_ID,i.Insurance_Fee,i.Date_Issued,i.Expiry_Date,i.Payed_Amount,i.Due_Amount from customer_state c,Insurance_Fund i where c.Customer_ID=i.Customer_ID and i.Customer_ID like ?;", Customer_ID_Search, Table_if);
        
    }

    @FXML
    private void Add_Button_ic(ActionEvent event) {
    
        try{
        
        String Claim_Number=Claim_Number_ic.getText();
        String Claim_Status=Claim_Status_ic.getText();
        String Claim_Type=Claim_Type_ic.getText();
        LocalDate Open_Date=Open_Date_ic.getValue();
        LocalDate Closed_Date=Closed_Date_ic.getValue();
        String Description=Description_ic.getText();
            
        Insurance_Claim Insurance_Claim_Object = new Insurance_Claim(Claim_Number,Claim_Status,Claim_Type,Open_Date,Closed_Date,Description);
        Insurance_Claim_Object.toString();
        //Insurance_Claim_Object.consolidate(conn);
        
        }
        catch(Exception e){
            
            System.out.println(e);
            
        }
        
    }

    @FXML
    private void Update_Button_ic(ActionEvent event) {
    }

    @FXML
    private void Delete_Button_ic(ActionEvent event) {
    }

    @FXML
    private void Search_ic(ActionEvent event) {
        
        String Claim_Number_Search = Search_Bar_ic.getText();
        handlers.DynamicTable.buildData(conn, "select * from Insurance_Claim where `Claim_Number` like ?;", Claim_Number_Search, Table_ic);
        
    }
}
