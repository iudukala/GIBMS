package battibois;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import core.Entity;
import core.Integrator;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;


import guiMediators.Commons;
import guiMediators.EntityControls;
import guiMediators.tableViewHandler;
import handlers.dbConcurrent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;




public class ResourceFXMLController implements Initializable
{
    @FXML
    private AnchorPane anchor_resource;
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
    
    
    private dbConcurrent nbconn;
    EntityControls vehicleControls;
    EntityControls buildingControls;
    EntityControls otherControls;
    private tableViewHandler tablevehi_handle;
    JFXTabPane tabpane_resource;
    @FXML
    private JFXButton add_vehicle;
    @FXML
    private TableView<?> table_vehi;
    @FXML
    private JFXButton btn_searchvehi;
    @FXML
    private JFXButton btn_selectvehi;
    @FXML
    private JFXButton btn_upvehi;
    @FXML
    private JFXButton btn_searchbul;
    @FXML
    private JFXButton btn_upbuilding;
    @FXML
    private JFXButton btn_delbul;
    @FXML
    private JFXButton add_building;
    @FXML
    private JFXTextField other_no;
    @FXML
    private JFXTextField other_cat;
    @FXML
    private JFXTextField other_price;
    @FXML
    private JFXTextField other_use;
    @FXML
    private JFXTextField other_qun;
    @FXML
    private JFXDatePicker other_date;
    @FXML
    private JFXTextArea other_des;
    @FXML
    private TableView<?> table_building;
    @FXML
    private JFXButton other_search;
    @FXML
    private JFXButton other_up;
    @FXML
    private JFXButton other_del;
    @FXML
    private JFXButton other_add;
    @FXML
    private TableView<?> table_other;
    @FXML
    private AnchorPane subanchor_other;
    @FXML
    private Button btn_selectbuil;
    @FXML
    private JFXTextField building_ID;
    @FXML
    private Button select_other;
    @FXML
    private JFXTextField bill_id;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        nbconn = dbConcurrent.getInstance();

        initializeNodes();
        initializeVehicleInputs();
        initializeBuildingInputs();
//       initializeOtherInputs();
        initializeButton();
//        initializeButton_building();
    }
    
    
    private void initializeNodes()
    { 
        tabpane_resource = Integrator.integrate(anchor_resource);
        
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
                   int v= vehicle.consolidate();
                   
                   if(v == 0)
                {
                vehicleControls.clearControls();
                }
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
                    building = buildingControls.getValues();
                    System.out.println(building);
                    System.out.println(building.validate(true));
                    int b= building.consolidate();
                    
                 if(b == 0)
                    {
                        buildingControls.clearControls();
                    }
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
        return vehicleControls.triggerValidators();
    }
    
    public Entity getBuildingInputs()
    {
        return buildingControls.getValues();
    }
    
    public void setBuildingInputs(Entity building)
    {
        buildingControls.setValues(building);
    }
    
    public boolean validateBuildingInputs()
    {
        return buildingControls.triggerValidators();
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
    public void initializeButton()
    {
       
        btn_searchvehi.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                 tablevehi_handle = new tableViewHandler(table_vehi,"select * from vehicle;",nbconn);
                    tablevehi_handle.writeToTable();       
            }         
               
                
        });
        
          /*  public void initializeButton_building()
            {
       
                 btn_searchbul.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent event)
                {
                 table_building_handle = new tableViewHandler(table_building,"select * from building;",nbconn);
                    tablevehi_handle.writeToTable();       
                }         
               
                
        });*/     
                btn_upvehi.setOnAction(new EventHandler<ActionEvent>()
        {
        @Override
            public void handle(ActionEvent e)
            {
            
                Entity vehicle;
            try{
                vehicle = vehicleControls.getValues();
                vehicle.update();
                }
            catch(Exception ex)
            {
            System.out.println("nullpointer no inputs");
               
            }
            }
        });
                
        
                   btn_selectvehi.setOnAction(new EventHandler<ActionEvent>()
                   {
            @Override
            public void handle(ActionEvent event)
            {
                vehicleControls.setValues(tablevehi_handle.getSelection());//"vehicle", "license"));
            }
        });


         
}
   
}
     
    

