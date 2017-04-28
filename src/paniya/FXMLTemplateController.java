/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paniya;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import core.Integrator;

/**
 * FXML Controller class
 *
 * @author praneethjayawickrama
 */
public class FXMLTemplateController implements Initializable {

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private TabPane tabpane;
    @FXML
    private Label bvr_serialno;
    @FXML
    private JFXDatePicker bvr_date;
    @FXML
    private JFXComboBox<?> bvr_transactioncode;
    @FXML
    private JFXComboBox<?> bvr_transactionname;
    @FXML
    private JFXComboBox<?> bvr_branchname;
    @FXML
    private JFXTextArea bvr_description;
    @FXML
    private JFXRadioButton bvr_voucher;
    @FXML
    private ToggleGroup tgroup_marital;
    @FXML
    private JFXRadioButton bvr_receipt;
    @FXML
    private JFXTextField bvr_totalamount;
    @FXML
    private JFXTextField bvr_amount;
    @FXML
    private JFXButton bvr_save;
    @FXML
    private JFXButton bvr_edit;
    @FXML
    private JFXButton bvr_delete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        Integrator.integrate(anchorpane);
    }    

    @FXML
    private void bttn_bvr_save(ActionEvent event) {
    }

    @FXML
    private void bttn_bvr_edit(ActionEvent event) {
    }

    @FXML
    private void bttn_bvr_delete(ActionEvent event) {
    }
    
}
