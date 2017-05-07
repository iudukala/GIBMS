package battibois;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import core.Entity;
import core.Integrator;
import fxml_files.ContentFactory;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;


import guiMediators.EntityControls;
import guiMediators.tableViewHandler;
import handlers.ValidationHandler.IntegerValidator;
import handlers.dbConcurrent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
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
    private ScrollPane scroll_add1;
    @FXML
    private StackPane stack_add1;
    @FXML
    private ScrollPane scroll_add2;
    @FXML
    private StackPane stack_add2;
    @FXML
    private JFXTextField text_nic2;
    @FXML
    private ScrollPane scroll_add3;
    @FXML
    private StackPane stack_add3;
    
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
    @FXML
    private JFXTextField text_vsearch;
    @FXML
    private JFXButton btn_delvehicle;
    
    
    private void seperatorFunction(){}
    
    private JFXTabPane tabpane_resource;
    private JFXDialog dialog;
    
    private dbConcurrent nbconn;
    
    private EntityControls vehicleControls;
    private EntityControls buildingControls;
    private EntityControls otherControls;
    
    private tableViewHandler tableVehicle_handle;
    private tableViewHandler tableBuilding_handle;
    private tableViewHandler tableOther_handle;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        nbconn = dbConcurrent.getInstance();
        tabpane_resource = Integrator.integrate(anchor_resource);
        
        initializeVehicleInputs();
        initializeBuildingInputs();
        initializeOtherInputs();
        
        tableVehicle_handle.writeToTable();
        tableBuilding_handle.writeToTable();
        tableOther_handle.writeToTable();
        tabpane_resource.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
                if((int)newValue == 0)
                    tableVehicle_handle.writeToTable();
                else if((int)newValue == 1)
                    tableBuilding_handle.writeToTable();
                else if((int)newValue == 2)
                    tableOther_handle.writeToTable();
            }
        });
    }
    
    public void initializeVehicleInputs()
    {
        tableVehicle_handle = new tableViewHandler(table_vehi,"select * from vehicle;",nbconn);
        
        vehicleControls = new EntityControls("vehicle",nbconn);
        vehicleControls.add(new Object[][]
        {
            {"license", text_license},
            {"brand", text_brand},
            {"year",text_year, new IntegerValidator(2018)},
            {"model",text_model},
            {"colour", text_colour},
            {"engine_no",text_engine},
            {"seat_count", text_seat, new IntegerValidator(50)}
        });
        
        add_vehicle.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                Entity vehicle = null;
                if(vehicleControls.triggerValidators())
                {
                    vehicle = vehicleControls.getValues();
                }
                else
                {
                    displayDialog(1, "vehicle", 1);
                }
                try
                {
                    int vehicleStatus=vehicle.consolidate();
                    if(vehicleStatus == 0)
                    {
                        vehicleControls.clearControls();
                        displayDialog(0, "vehicle", 1);
                    }
                }
                catch(Exception ex)
                {
                    displayDialog(1, "vehicle", 1);
                }
            }
        });
        
        btn_searchvehi.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                Entity vsearch = new Entity("select * from vehicle", nbconn);
                vsearch.add("license", text_vsearch.getText());
                tableVehicle_handle.writeToTable(vsearch.executeAsSearch());       
            }
        });
        
        btn_selectvehi.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                vehicleControls.setValues(tableVehicle_handle.getSelection());
            }
        });
        
        btn_upvehi.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                Entity vehicle;
                if(vehicleControls.triggerValidators())
                {
                    vehicle = vehicleControls.getValues();
                }
                else
                {
                    displayDialog(1, "vehicle", 2);
                    return;
                }
                
                if(vehicle.update())
                {
                    displayDialog(0, "vehicle", 2);
                }
            }
        });
        
        btn_delvehicle.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                if(tableVehicle_handle.getSelection().deleteFromDB())
                {
                    displayDialog(0, "vehicle", 3);
                }
                tableVehicle_handle.writeToTable();
            }
        });
        
    }
    
    public void initializeBuildingInputs()
    {
        tableBuilding_handle = new tableViewHandler(table_building,"select * from building;",nbconn);
        
        buildingControls = new EntityControls("building",nbconn);
        buildingControls.add(new Object[][]
        {
            {"address", build_address},
            {"floor", floor, new IntegerValidator()},
            {"rent_date", rent},
            {"condition", build_con},
            {"rent_value", build_value},
            {"end_date", end},
            {"description", build_des},
        });
        
        add_building.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                Entity building = null;
                if(buildingControls.triggerValidators())
                {
                    building = buildingControls.getValues();
                }
                try
                {
                    int buildingStatus=building.consolidate();
                    if(buildingStatus == 0)
                    {
                        buildingControls.clearControls();
                    }
                }
                catch(Exception ex)
                {
                    System.out.println("nullpointer no inputs");
                }
            }
        });
        
         btn_searchbul.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                Entity builsearch = new Entity("select * from building", nbconn);
                builsearch.add("", building_ID.getText());
               // tableVehicle_handle.writeToTable(vsearch.executeAsSearch());       
            }
        });
        
        btn_selectbuil.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                buildingControls.setValues(tableBuilding_handle.getSelection());
            }
        });
        
        btn_upbuilding.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                Entity building;
                if(buildingControls.triggerValidators())
                {
                    building = buildingControls.getValues();
                }
                else
                {
                    displayDialog(1, "building", 2);
                    return;
                }
                
                if(building.update())
                {
                    displayDialog(0, "building", 2);
                }
            }
        });
        
        btn_delbul.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                if(tableBuilding_handle.getSelection().deleteFromDB())
                {
                    displayDialog(0, "building", 3);
                }
                tableBuilding_handle.writeToTable();
            }
        });
    }
    
    public void initializeOtherInputs()
    {
        tableOther_handle = new tableViewHandler(table_other,"select * from other_bill;",nbconn);
        
        otherControls = new EntityControls("other",nbconn);
        otherControls.add(new Object[][]
        {
            {"bill no", other_no},
            {"category", other_cat},
            {"date", other_date},
            {"price", other_price},
            {"usage", other_use},
            {"quantity", other_qun},
            {"description", other_des},

        });
        
        other_add.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                Entity other = null;
                if(otherControls.triggerValidators())
                {
                    other = otherControls.getValues();
                }
                else
                {
                    displayDialog(1, "other", 1);
                }
                try
                {
                    int otherStatus=other.consolidate();
                    if(otherStatus == 0)
                    {
                        otherControls.clearControls();
                        displayDialog(0, "other", 1);
                    }
                }
                catch(Exception ex)
                {
                    displayDialog(1, "other", 1);
                }
            }
        });
        
        other_search.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                Entity osearch = new Entity("select * from other", nbconn);
                osearch.add("bill_ID", text_vsearch.getText());
                tableOther_handle.writeToTable(osearch.executeAsSearch());       
            }
        });
        
        select_other.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                otherControls.setValues(tableOther_handle.getSelection());
            }
        });
        
        other_up.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                Entity other;
                if(otherControls.triggerValidators())
                {
                    other = otherControls.getValues();
                }
                else
                {
                    displayDialog(1, "other", 2);
                    return;
                }
                
                if(other.update())
                {
                    displayDialog(0, "other", 2);
                }
            }
        });
        
        other_del.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                if(tableOther_handle.getSelection().deleteFromDB())
                {
                    displayDialog(0, "other", 3);
                }
                tableOther_handle.writeToTable();
            }
        });
        
    }
    
    public void displayDialog(int status, String recordtype, int optype)
    {
        String operation;
        switch (optype)
        {
            case 1:
                operation = "CONSOLIDATION";
                break;
            case 2:
                operation = "UPDATE";
                break;
            case 3:
                operation = "DELETE";
                break;
            default:
                return;
        }
        
        switch (status)
        {
            case 0:
                try{dialog.close();}catch(Exception ex){}
                dialog = initializeDialog(1, ContentFactory.getDialog(operation + " SUCESSFUL", recordtype + " record added to database successfully", 1));
                dialog.show();
                break;
            case 1:
                try{dialog.close();}catch(Exception ex){}
                dialog = initializeDialog(1, ContentFactory.getDialog("FIELDS EMPTY/INVALID", "Please fill out all required fields correctly", 2));
                dialog.show();
                break;
            case 2:
                try{dialog.close();}catch(Exception ex){}
                dialog = initializeDialog(1, ContentFactory.getDialog(operation + " FAILED", "failed due to an internal error", 2));
                dialog.show();
                break;
            default:
                break;
        }
    }
    
    private JFXDialog initializeDialog(int tabnum, JFXDialog dialog)
    {
        Control control;
        switch(tabnum)
        {
            case 1:
                control = text_license;
                break;
            case 2:
                control = build_address;
                break;
            case 3:
                control = other_no;
                break;
            default:
                return null;
        }
        dialog.setDialogContainer((StackPane)control.getParent().getParent());
        
        return dialog;
    }
}