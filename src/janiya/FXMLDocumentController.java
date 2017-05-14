/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package janiya;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author sTc
 */
public class FXMLDocumentController implements Initializable
{

    @FXML
    private TextField EmployeeId;
    @FXML
    private TextField BankID;
    @FXML
    private DatePicker Date;

    @FXML
    private void handle_addButton(ActionEvent event)
    {
        //        //person object inputs
        //        String EmployeeId=text_EmployeeId.getText();
        //        String BankID=text_BankID.getText();
        //
        //
        //        LocalDate Date = datepicker_Date.getValue();
        //
        //        //customer object inputs
        //        char sector='e';
        //        if(sectorGroup.getSelectedToggle().toString().endsWith("'State'"))
        //            sector='s';
        //        else if(sectorGroup.getSelectedToggle().toString().endsWith("'State corporation'"))
        //            sector='c';
        //
        //        String work_phone=text_wphone.getText();
        //        String company=text_company.getText();
        //        char service_type = (serviceGroup.getSelectedToggle().toString().endsWith("'Permanent'")) ? 'p':'t';
        //        String position = text_position.getText();
        //        LocalDate employment = date_employment.getValue();
        //
        //        String acc_num = text_account.getText();
        //        String acc_bank = text_bank.getText();
        //        String acc_branch = text_branch.getText();
        //
        //        int career=Integer.parseInt(text_earncareer.getText());
        //        int business=Integer.parseInt(text_earnbusiness.getText());
        //        int houses = Integer.parseInt(text_earnhouses.getText());
        //        int vehicles = Integer.parseInt(text_earnvehicles.getText());
        //        int land = Integer.parseInt(text_earnland.getText());
        //
        //        try
        //        {
        //            Person person_object = new Person(name,nic,dob,personal_phone, home_addr, gender, marital_status, email);
        //            person_object.consolidate(conn);
        //
        //            Customer customer_object = new Customer(nic,work_phone,company,position,acc_num,acc_bank,acc_branch,sector,service_type, employment, career,
        //            business, houses,vehicles,land);
        //            customer_object.consolidate(conn);
        //        }
        //        catch(Exception e)
        //        {
        //            System.out.println("Customer controller error\n" + e);
        //        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }
}
