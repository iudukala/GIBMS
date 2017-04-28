/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hasini;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;


import handlers.dbConcurrent;
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
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Hasini Subasinghe
 */
public class FXMLDocumentController implements Initializable {
    
       Connection conn=null;
    
    private Label label;
    @FXML
    private MenuButton branch;
    @FXML
    private DatePicker date;
    @FXML
    private TableView<?> table1;
    @FXML
    private Button search;
    @FXML
    private TextField name;
    @FXML
    private TextField customerId;
    @FXML
    private DatePicker dDate;
    @FXML
    private Button add;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private Button back;
    @FXML
    private DatePicker lastPayedDate;
    @FXML
    private TableView<?> table2;
    private DatePicker ldate;
    private TextField lname;
    private TextField text_nic;
    private TextField text_customerId;
    private TextField text_fname;
    private TextField text_outstanding;
    private TextField text_loanAmount;
    private TextField text_address;
    private TextField text_lastPayment;
    private TextField text_lname;
    @FXML
    private TextField employeeId;
    @FXML
    private TextField nic_add;
    @FXML
    private TextField nic_edit;
    @FXML
    private TextField loanAmount;
    @FXML
    private TextField outstanding;
    @FXML
    private TextField addrees;
    @FXML
    private TextField lastPayment;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        // TODO
         conn=dbConcurrent.connect();
        //DynamicTable.getColumns(conn, "select * from arrears", table_search);
        
        
    }    


    private void add(ActionEvent event) {
        
        try
	{
		String nic=text_nic.getText();
		String customerID=text_customerId.getText();
		String fName=text_fname.getText();
		String lName=text_lname.getText();
		String address=text_address.getText();
		String loanAmount=text_loanAmount.getText();
		LocalDate loanDate=ldate.getValue();
		LocalDate dueDate=dDate.getValue();
		String lastPayment=text_lastPayment.getText();
		LocalDate lastPaymentDate=lastPayedDate.getValue();
		String outstanding=text_outstanding.getText();
		
		arrears_entity arrears_object = new arrears_entity(nic,customerID,fName,lName,address,loanAmount,loanDate,dueDate,lastPayment,lastPaymentDate,outstanding);
        //System.out.println(arrears_object.toString());
        arrears_object.consolidate(conn); 
	}
	catch(Exception e)
	{
	System.out.println(e);
	}
}

    
}
