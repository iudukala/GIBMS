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
import guiMediators.tableViewHandler;
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

    EntityControls insuranceFundCont;
    EntityControls insuranceClaimCont;
    
    EntityControls searchInsuranceFundCont;
    EntityControls searchInsuranceClaimCont;
    
    private tableViewHandler customerSearchTableHandle;
    private tableViewHandler insuranceFundTableHandle;
    private tableViewHandler insuranceClaimTableHandle;
    
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
        tableHandler();
        initializeInsuranceFundInputs();
        initializeInsuranceClaimInputs();
        setInsuranceFundInputs(); 
        setInsuranceClaimInputs();
    }
    
    public void initializeButton()
    {
        Commons.subAnchorButton search_cs = new Commons.subAnchorButton(anchor_customer_search, "SEARCH", Commons.LIST_GLYPH);
        search_cs.setButtonLength(110);
        search_cs.setButtonHeigth(20);
        search_cs.setGlyphWidth(20); 
        search_cs.setCoordinates(400, 138);
        search_cs.setStyle(Commons.BTNSTYLE_2);
        JFXButton searchbutton_cs = search_cs.getButton();
        
        Commons.subAnchorButton add_if = new Commons.subAnchorButton(anchor_insurance_fund, "ADD", Commons.ADD_PERSON_GLYPH);
        add_if.setButtonLength(110);
        add_if.setButtonHeigth(20);
        add_if.setGlyphWidth(20); 
        add_if.setCoordinates(150, 610);
        JFXButton addbutton_if = add_if.getButton();
        
        addbutton_if.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                
                Entity Insurance_Fund = insuranceFundCont.getValues();
                System.out.println(Insurance_Fund);
                Insurance_Fund.validate(true);
                Insurance_Fund.consolidate();
                
            }
        });
        
        Commons.subAnchorButton update_if = new Commons.subAnchorButton(anchor_insurance_fund, "UPDATE", Commons.UPDATE_GLYPH);
        update_if.setButtonLength(110);
        update_if.setButtonHeigth(20);
        update_if.setGlyphWidth(20); 
        update_if.setCoordinates(400, 610);
        JFXButton updatebutton_if = update_if.getButton();
        
        updatebutton_if.setOnAction(new EventHandler<ActionEvent>()
        {
        @Override
            public void handle(ActionEvent e)
            {
            
                Entity Insurance_Fund;
                
            
                try{

                    Insurance_Fund=searchInsuranceFundCont.getValues();
                    System.out.println(Insurance_Fund);
                    Insurance_Fund.update();

                    }
                catch(Exception ex)
                {

                    System.out.println("nullpointer no inputs");

                }

            }
        });
        
        Commons.subAnchorButton delete_if = new Commons.subAnchorButton(anchor_insurance_fund, "DELETE", Commons.DELETE_GLYPH);
        delete_if.setButtonLength(110);
        delete_if.setButtonHeigth(20);
        delete_if.setGlyphWidth(20);
        delete_if.setCoordinates(650, 610);
        JFXButton deletebutton_if = delete_if.getButton();
        
        Commons.subAnchorButton search_if = new Commons.subAnchorButton(anchor_insurance_fund, "SEARCH", Commons.LIST_GLYPH);
        search_if.setButtonLength(110);
        search_if.setButtonHeigth(20);
        search_if.setGlyphWidth(20); 
        search_if.setCoordinates(400, 300);
        search_if.setStyle(Commons.BTNSTYLE_2);
        JFXButton searchbutton_if = search_if.getButton();
        
        Commons.subAnchorButton select_if = new Commons.subAnchorButton(anchor_insurance_fund, "SELECT", Commons.UPDATE_GLYPH);       
        select_if.setButtonLength(110);
        select_if.setButtonHeigth(20);
        select_if.setGlyphWidth(20); 
        select_if.setCoordinates(750, 300);
        select_if.setStyle(Commons.BTNSTYLE_2);
        JFXButton selectbutton_if=select_if.getButton();
        
        selectbutton_if.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                
                //searchInsuranceFundCont.setValues(insuranceFundTableHandle.getSelection("Insurance_Fund", "NIC"));
                searchInsuranceFundCont.setValues(insuranceFundTableHandle.getSelection());

            }
        });
        
        Commons.subAnchorButton add_ic = new Commons.subAnchorButton(anchor_insurance_claim, "ADD", Commons.ADD_PERSON_GLYPH);
        add_ic.setButtonLength(110);
        add_ic.setButtonHeigth(20);
        add_ic.setGlyphWidth(20);
        add_ic.setCoordinates(150, 610);
        JFXButton addbutton_ic = add_ic.getButton();
        
        addbutton_ic.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                Entity Insurance_Claim = insuranceClaimCont.getValues();
                System.out.println(Insurance_Claim);
                Insurance_Claim.validate(true);
                Insurance_Claim.consolidate();
            }
        });
        
        Commons.subAnchorButton update_ic = new Commons.subAnchorButton(anchor_insurance_claim, "UPDATE", Commons.UPDATE_GLYPH);
        update_ic.setButtonLength(110);
        update_ic.setButtonHeigth(20);
        update_ic.setGlyphWidth(20);
        update_ic.setCoordinates(400, 610);
        JFXButton updatebutton_ic = update_ic.getButton();
        
        updatebutton_ic.setOnAction(new EventHandler<ActionEvent>()
        {
        @Override
            public void handle(ActionEvent e)
            {
            
                Entity Insurance_Claim;
                
            
                try{

                    Insurance_Claim=searchInsuranceClaimCont.getValues();
                    System.out.println(Insurance_Claim);
                    Insurance_Claim.update();

                    }
                catch(Exception ex)
                {

                    System.out.println("nullpointer no inputs");

                }

            }
        });
        
        Commons.subAnchorButton delete_ic = new Commons.subAnchorButton(anchor_insurance_claim, "DELETE", Commons.DELETE_GLYPH);
        delete_ic.setButtonLength(110);
        delete_ic.setButtonHeigth(20);
        delete_ic.setGlyphWidth(20);
        delete_ic.setCoordinates(650, 610);
        JFXButton deletebutton_ic = delete_ic.getButton();
        
        Commons.subAnchorButton search_ic = new Commons.subAnchorButton(anchor_insurance_claim, "SEARCH", Commons.LIST_GLYPH);
        search_ic.setButtonLength(110);
        search_ic.setButtonHeigth(20);
        search_ic.setGlyphWidth(20); 
        search_ic.setCoordinates(400, 300);
        search_ic.setStyle(Commons.BTNSTYLE_2);
        JFXButton searchbutton_ic = search_ic.getButton();
        
        Commons.subAnchorButton select_ic = new Commons.subAnchorButton(anchor_insurance_claim, "SELECT", Commons.UPDATE_GLYPH);       
        select_ic.setButtonLength(110);
        select_ic.setButtonHeigth(20);
        select_ic.setGlyphWidth(20); 
        select_ic.setCoordinates(750, 300);
        select_ic.setStyle(Commons.BTNSTYLE_2);
        JFXButton selectbutton_ic=select_ic.getButton();
        
        selectbutton_ic.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                
                //searchInsuranceClaimCont.setValues(insuranceClaimTableHandle.getSelection("Insurance_Claim", "Claim_Number"));
                searchInsuranceClaimCont.setValues(insuranceClaimTableHandle.getSelection());

            }
        });
            
    }
 
    public void initializeInsuranceFundInputs()
    {
         
        insuranceFundCont = new EntityControls("Insurance_Fund",nbconn);
        
        insuranceFundCont.add("NIC", nic_if);
        
        insuranceFundCont.add("Insurance_Fee", insurance_fee_if);
        
        insuranceFundCont.add("Date_Issued", date_issued_if);
        
        insuranceFundCont.add("Expiry_Date", expiry_date_if);
        
        insuranceFundCont.add("Payed_Amount", payed_amount_if);
        
        insuranceFundCont.add("Due_Amount", due_amount_if);
        
    }
        
    public void initializeInsuranceClaimInputs()
    {
        insuranceClaimCont = new EntityControls("Insurance_Claim",nbconn);
        
        insuranceClaimCont.add("Claim_Number", claim_number_ic);
        
        insuranceClaimCont.add("Claim_Status", claim_status_ic);
        
        insuranceClaimCont.add("Claim_Type", claim_type_ic);
        
        insuranceClaimCont.add("Open_Date", open_date_ic);
        
        insuranceClaimCont.add("Closed_Date", closed_date_ic);
        
        insuranceClaimCont.add("Description", description_ic);
    }
    
    public void tableHandler()
    {
        
        customerSearchTableHandle = new tableViewHandler(table_cs,"select * from customer_view",nbconn);
        customerSearchTableHandle.writeToTable();
        
        insuranceFundTableHandle = new tableViewHandler(table_if,"select * from Insurance_Fund", nbconn);
        insuranceFundTableHandle.writeToTable();
        
        insuranceClaimTableHandle = new tableViewHandler(table_ic,"select * from Insurance_Claim",nbconn);
        insuranceClaimTableHandle.writeToTable();
    
    }
    
    public void setInsuranceFundInputs()
    {
        
        searchInsuranceFundCont = new EntityControls("Insurance_Fund",nbconn);

        searchInsuranceFundCont.add("NIC", nic_if);
        
        searchInsuranceFundCont.add("Insurance_Fee", insurance_fee_if);
        
        searchInsuranceFundCont.add("Date_Issued", date_issued_if);
        
        searchInsuranceFundCont.add("Expiry_Date", expiry_date_if);
        
        searchInsuranceFundCont.add("Payed_Amount", payed_amount_if);
        
        searchInsuranceFundCont.add("Due_Amount", due_amount_if);
        
    }
    
    public void setInsuranceClaimInputs()
    {
        
        searchInsuranceClaimCont = new EntityControls("Insurance_Claim",nbconn);
        
        searchInsuranceClaimCont.add("Claim_Number", claim_number_ic);
        
        searchInsuranceClaimCont.add("Claim_Status", claim_status_ic);
        
        searchInsuranceClaimCont.add("Claim_Type", claim_type_ic);
        
        searchInsuranceClaimCont.add("Open_Date", open_date_ic);
        
        searchInsuranceClaimCont.add("Closed_Date", closed_date_ic);
        
        searchInsuranceClaimCont.add("Description", description_ic);
        
    }
}
