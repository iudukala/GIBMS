/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hasini;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import guiMediators.EntityControls;
import handlers.dbConcurrent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Hasini Subasinghe
 */
public class Arrears_interfaceController implements Initializable {
    
    //EntityControls personCont;
    //EntityControls arrearsCont;
    //dbConcurrent nbconn;

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private TabPane tabpane;
    @FXML
    private JFXButton e_search;
    @FXML
    private JFXTextField e_nic;
    @FXML
    private JFXTextField e_customerID;
    @FXML
    private JFXTextField e_fname;
    @FXML
    private JFXTextField e_lname;
    @FXML
    private JFXTextField e_address;
    @FXML
    private JFXTextField e_loanAmount;
    @FXML
    private JFXDatePicker e_loanDate;
    @FXML
    private JFXDatePicker e_dueDate;
    @FXML
    private JFXDatePicker e_lastPaymentDate;
    @FXML
    private JFXTextField e_lastPaymentAmount;
    @FXML
    private JFXTextField e_outstandingAmount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        // TODO
    }    
    
}
