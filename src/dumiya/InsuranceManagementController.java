/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dumiya;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import core.Entity;
import core.Integrator;
import guiMediators.Commons;
import guiMediators.EntityControls;
import handlers.dbConcurrent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Isuru Udukala
 */
public class InsuranceManagementController implements Initializable
{
    dbConcurrent nbconn;
    EntityControls personCont;
    EntityControls insuranceCont;
    
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private TabPane tabpane;
    @FXML
    private AnchorPane anchor_customer_search;
    @FXML
    private JFXTextField nic_cs;
    @FXML
    private TableView<?> table_cs;
    @FXML
    private AnchorPane anchor_insurance_fund;
    @FXML
    private JFXTextField nic_if;
    @FXML
    private JFXTextField insurance_fee_if;
    @FXML
    private JFXTextField payed_amount_if;
    @FXML
    private JFXTextField due_amount_if;
    @FXML
    private JFXDatePicker date_issued_if;
    @FXML
    private JFXDatePicker expiry_date_if;
    @FXML
    private JFXTextField search_bar_if;
    @FXML
    private TableView<?> table_if;
    @FXML
    private AnchorPane anchor_insurance_claim;
    @FXML
    private JFXTextField claim_number_ic;
    @FXML
    private JFXTextField claim_status_ic;
    @FXML
    private JFXTextField claim_type_ic;
    @FXML
    private JFXTextField description_ic;
    @FXML
    private JFXDatePicker open_date_ic;
    @FXML
    private JFXDatePicker closed_date_ic;
    @FXML
    private JFXTextField search_bar_ic;
    @FXML
    private TableView<?> table_ic;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        nbconn = new dbConcurrent();
        
        Integrator.integrate(anchorpane);
        initializeButton();
        
        initializePersonInputs();
        initializeInsuranceFundInputs();
//        initializeInsuranceClaimInputs();
    }
    
    public void initializeButton()
    {
//        Commons.subAnchorButton search_cs = new Commons.subAnchorButton();
//        search_cs.setButtonLength(200);
//        search_cs.setCoordinates(400, 150);
//        JFXButton searchbutton_cs = search_cs.getButton(anchor_customer_search, "SEARCH", Commons.LIST_GLYPH);
        
        Commons.subAnchorButton add_if = new Commons.subAnchorButton();
        add_if.setButtonLength(200);
        add_if.setCoordinates(150, 600);
        JFXButton addbutton_if = add_if.getButton(anchor_insurance_fund, "ADD", Commons.ADD_PERSON_GLYPH);
        
//        Commons.subAnchorButton update_if = new Commons.subAnchorButton();
//        update_if.setButtonLength(200);
//        update_if.setCoordinates(400, 600);
//        JFXButton updatebutton_if = update_if.getButton(anchor_insurance_fund, "UPDATE", Commons.UPDATE_GLYPH);
//        
//        Commons.subAnchorButton delete_if = new Commons.subAnchorButton();
//        delete_if.setButtonLength(200);
//        delete_if.setCoordinates(650, 600);
//        JFXButton deletebutton_if = delete_if.getButton(anchor_insurance_fund, "DELETE", Commons.DELETE_GLYPH);
//        
//        Commons.subAnchorButton search_if = new Commons.subAnchorButton();
//        search_if.setButtonLength(200);
//        search_if.setCoordinates(400, 300);
//        JFXButton searchbutton_if = search_if.getButton(anchor_insurance_fund, "SEARCH", Commons.LIST_GLYPH);
//        
//        Commons.subAnchorButton add_ic = new Commons.subAnchorButton();
//        add_ic.setButtonLength(200);
//        add_ic.setCoordinates(150, 600);
//        JFXButton addbutton_ic = add_ic.getButton(anchor_insurance_claim, "ADD", Commons.ADD_PERSON_GLYPH);
//        
//        Commons.subAnchorButton update_ic = new Commons.subAnchorButton();
//        update_ic.setButtonLength(200);
//        update_ic.setCoordinates(400, 600);
//        JFXButton updatebutton_ic = update_ic.getButton(anchor_insurance_claim, "UPDATE", Commons.UPDATE_GLYPH);
//        
//        Commons.subAnchorButton delete_ic = new Commons.subAnchorButton();
//        delete_ic.setButtonLength(200);
//        delete_ic.setCoordinates(650, 600);
//        JFXButton deletebutton_ic = delete_ic.getButton(anchor_insurance_claim, "DELETE", Commons.DELETE_GLYPH);
//        
//        Commons.subAnchorButton search_ic = new Commons.subAnchorButton();
//        search_ic.setButtonLength(200);
//        search_ic.setCoordinates(400, 300);
//        JFXButton searchbutton_ic = search_ic.getButton(anchor_insurance_claim, "SEARCH", Commons.LIST_GLYPH);
        
        addbutton_if.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                Entity person = personCont.getValues();
                
                System.out.println(person);
                person.validate(true);
                person.consolidate();
                
                Entity Insurance_Fund = insuranceCont.getValues();
                System.out.println(Insurance_Fund);
                Insurance_Fund.validate(true);
                Insurance_Fund.consolidate();
            }
        });
        
//        addbutton_ic.setOnAction(new EventHandler<ActionEvent>()
//        {
//            @Override
//            public void handle(ActionEvent e)
//            {
////                Entity Insurance_Claim = in
////                System.out.println(Insurance_Claim.validate(true));
////                Insurance_Claim.consolidate();
//                
//            }
//        });
        
    }
 
     public void initializeInsuranceFundInputs()
     {
        insuranceCont = new EntityControls("Insurance_Fund",nbconn);
        
        insuranceCont.add("NIC", nic_if);
        
        insuranceCont.add("Insurance_Fee", insurance_fee_if);
        
        insuranceCont.add("Date_Issued", date_issued_if);
        
        insuranceCont.add("Expiry_Date", expiry_date_if);
        
        insuranceCont.add("Payed_Amount", payed_amount_if);
        
        insuranceCont.add("Due_Amount", due_amount_if);
        
    }
        
    public void initializePersonInputs()
    {
        personCont= new EntityControls("person", nbconn);
        personCont.add("nic", nic_if);  
    
    }
    
//    public void initializeInsuranceClaimInputs()
//     {
//        insuranceCont = new EntityControls("Insurance_Claim",nbconn);
//        
//        insuranceCont.add("Claim_Number", claim_number_ic);
//        
//        insuranceCont.add("Claim_Status", claim_status_ic);
//        
//        insuranceCont.add("Claim_Type", claim_type_ic);
//        
//        insuranceCont.add("Open_Date", open_date_ic);
//        
//        insuranceCont.add("Closed_Date", closed_date_ic);
//        
//        insuranceCont.add("Description", description_ic);
//    }
}
