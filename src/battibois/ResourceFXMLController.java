/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battibois;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import core.Entity;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;


import guiMediators.Commons;
import guiMediators.EntityControls;
import handlers.dbConcurrent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;
/**
 * FXML Controller class
 *
 * @author Isuru Udukala
 */
public class ResourceFXMLController implements Initializable
{
    @FXML
    private AnchorPane anchor_resource;
    @FXML
    private TabPane tabpane_resource;
    @FXML
    private JFXTextField text_license;
    @FXML
    private JFXTextField text_brand;
    @FXML
    private JFXTextField text_model;
    @FXML
    private JFXTextField text_colour;
    @FXML
    private JFXTextField text_year;
    @FXML
    private JFXTextField text_engine;
    @FXML
    private JFXTextField text_seat;
    @FXML
    private JFXTextField build_address;
    @FXML
    private JFXTextField floor;
    @FXML
    private JFXTextField build_con;
    @FXML
    private JFXTextField build_value;
    @FXML
    private JFXDatePicker rent;
    @FXML
    private JFXTextField build_des;
    @FXML
    private JFXDatePicker end;
    
    @FXML
    private AnchorPane subanchor_vehicle;
    @FXML
    private AnchorPane subanchor_building;
    @FXML
    private Tab tab_customer_add;
    @FXML
    private ScrollPane scroll_add;
    @FXML
    private StackPane stack_add;
    @FXML
    private Tab tab_customer_add1;
    @FXML
    private ScrollPane scroll_add1;
    @FXML
    private StackPane stack_add1;
    @FXML
    private Tab tab_customer_add2;
    @FXML
    private ScrollPane scroll_add2;
    @FXML
    private StackPane stack_add2;
    @FXML
    private JFXTextField text_nic2;
    @FXML
    private Tab tab_customer_add3;
    @FXML
    private ScrollPane scroll_add3;
    @FXML
    private StackPane stack_add3;
    @FXML
    private JFXTextField text_nic3;
    
    private dbConcurrent nbconn;
    EntityControls vehicleControls;
    EntityControls buildingControls;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        //initiailizing database connection
        nbconn = new dbConcurrent();
//        //initializing gui
        initializeNodes();
//       // initializeRadioButtons();
        initializeVehicleInputs();
        initializeBuildingInputs();
    }
    
    
    private void initializeNodes()
    {
        //core.Integrator.integrate(anchor_resource);
        Commons.subAnchorButton usab = new Commons.subAnchorButton();       
        usab.setButtonLength(110);
        usab.setButtonHeigth(20);
        usab.setGlyphWidth(20);
        usab.setCoordinates(650, 450);

        JFXButton add_vehicle = new Commons.subAnchorButton().getButton(subanchor_vehicle, "ADD VEHICLE", Commons.ADD_PERSON_GLYPH);
        JFXButton add_building = new Commons.subAnchorButton().getButton(subanchor_building, "ADD PERSON", Commons.ADD_PERSON_GLYPH);

        add_vehicle.setOnAction(new EventHandler<ActionEvent>()
        
        {
            @Override
            public void handle(ActionEvent e)
            {
             {
                Entity vehicle;
                try
                {
                    vehicle = getVehicleInputs();
                    System.out.println(vehicle);
                    System.out.println(vehicle.validate(true));
                    vehicle.consolidate();
                }
                catch(NullPointerException exc)
                {
                    System.out.println("nullpointer no inputs");
                }
             }
             {    
                Entity building;
                try
                {
                    building = getBuildingInputs();
                    System.out.println(building);
                    System.out.println(building.validate(true));
                    building.consolidate();
                }
                catch(NullPointerException exc)
                {
                    System.out.println("nullpointer no inputs");
                }
             }
            }
        });
    }
    
    public Entity getVehicleInputs()
    {
        return vehicleControls.getValues();
    }
    public void setVehicleInputs(Entity vehicle)
    {
        vehicleControls.setValues(vehicle);
    }
    public boolean validateVehicleInputs()
    {
        return vehicleControls.validateValues();
    }
    
    public Entity getBuildingInputs()
    {
        return buildingControls.getValues();
    }
    public void setBuildingInputs(Entity building)
    {
        buildingControls.setValues(building);
    }
    
    public void initializeVehicleInputs()
    {
        vehicleControls = new EntityControls("vehicle",nbconn);
        
        
        vehicleControls.add("license", text_license);
        
        vehicleControls.add("brand", text_brand);
        
        vehicleControls.add("year",text_year );
        
        vehicleControls.add("model",text_model );
        
        vehicleControls.add("colour", text_colour );
        
        vehicleControls.add("engine_no",text_engine );
        
        vehicleControls.add("seat_count", text_seat );
       
    }
    
    public void initializeBuildingInputs()
    {
        buildingControls = new EntityControls("building",nbconn);
    
        buildingControls.add("address", build_address);
        
        buildingControls.add("floor", floor  );
        
        buildingControls.add("rent_date", rent  );
        
        buildingControls.add("condition", build_con  );
        
        buildingControls.add("rent_value", build_value  );
        
        buildingControls.add("end_date", end  );
        
        buildingControls.add("description", build_des  );
        
    }
}
