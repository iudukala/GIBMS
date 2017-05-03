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
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;

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
    private JFXTextField bvr_description;
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
    private JFXComboBox<?> bvr_branchno;
    @FXML
    private JFXTextField bvr_chequeno;
    @FXML
    private JFXComboBox<?> bvr_paymenttype;
    @FXML
    private JFXDatePicker bvr_chequedate;
    @FXML
    private JFXComboBox<?> bvr_companyname;
    @FXML
    private JFXTextField bvr_nic;
    @FXML
    private TableView<?> bvr_table;
    @FXML
    private Label cb_id;
    @FXML
    private JFXTextField cb_openingbalance;
    @FXML
    private JFXTextField cb_bdbalance;
    @FXML
    private JFXDatePicker cb_date;
    @FXML
    private JFXTextField cb_serialno;
    @FXML
    private JFXTextField cb_nic;
    @FXML
    private JFXTextField cb_transactioncode;
    @FXML
    private JFXTextField cb_description;
    @FXML
    private JFXTextField cb_debit;
    @FXML
    private JFXTextField cb_transactionname;
    @FXML
    private JFXTextField cb_credit;
    @FXML
    private JFXRadioButton cb_voucher;
    @FXML
    private ToggleGroup tgroup_marital1;
    @FXML
    private JFXRadioButton cb_receipt;
    @FXML
    private JFXTextField cb_companyname;
    @FXML
    private TableView<?> cb_table;
    @FXML
    private Tab gl_table;
    @FXML
    private Label gl_id;
    @FXML
    private JFXDatePicker gl_date;
    @FXML
    private JFXTextField gl_nic;
    @FXML
    private JFXTextField gl_description;
    @FXML
    private JFXTextField gl_debit;
    @FXML
    private JFXTextField gl_credit;
    @FXML
    private JFXComboBox<?> gl_transactioncode;
    @FXML
    private JFXTextField gl_bdbalance;
    @FXML
    private JFXTextField gl_companyname;
    @FXML
    private Tab tb_table;
    @FXML
    private Label tb_id;
    @FXML
    private JFXDatePicker tb_date;
    @FXML
    private JFXComboBox<?> tb_transactioncode;
    @FXML
    private JFXComboBox<?> tb_transactionname;
    @FXML
    private JFXTextField tb_assets;
    @FXML
    private JFXTextField tb_liabilities;
    @FXML
    private JFXTextField tb_bdbalance;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        Integrator.integrate(anchorpane);
    }
}
