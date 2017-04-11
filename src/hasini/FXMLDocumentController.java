/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hasini;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;


import gibms.dbConnect;
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
    private Button edit;
    @FXML
    private Button list;
    @FXML
    private Rectangle lDate;
    private TextField name;
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
    @FXML
    private Button report;
    @FXML
    private Button send;
    @FXML
    private DatePicker ldate;
    private TextField lname;
    @FXML
    private TextField text_employeeId;
    @FXML
    private TextField text_nic;
    @FXML
    private TextField text_customerId;
    @FXML
    private TextField text_fname;
    @FXML
    private TextField text_outstanding;
    @FXML
    private TextField text_loanAmount;
    @FXML
    private TextField text_address;
    @FXML
    private TextField text_lastPayment;
    @FXML
    private TextField text_lname;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        // TODO
         conn=dbConnect.connect();
        //DynamicTable.getColumns(conn, "select * from arrears", table_search);
        
        
    }    

    @FXML
    private void search(ActionEvent event) {
    }

    @FXML
    private void edit(ActionEvent event) {
    }

    @FXML
    private void list(ActionEvent event) {
    }

    @FXML
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

    @FXML
    private void update(ActionEvent event) {
    }

    @FXML
    private void delete(ActionEvent event) {
    }

    @FXML
    private void back(ActionEvent event) {
    }

    @FXML
    private void report(ActionEvent event) {
    }

    @FXML
    private void send(ActionEvent event) {
    }
    
}
