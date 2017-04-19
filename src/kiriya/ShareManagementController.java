/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiriya;

import core.Integrator;
import guiMediators.Commons;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ASUS-PC
 */
public class ShareManagementController implements Initializable {

    @FXML
    private TabPane tabpane_shareholder;
    @FXML
    private AnchorPane anchor_shareholder;
    @FXML
    private AnchorPane anchor_shares;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        Integrator.integrate(anchor_shareholder, tabpane_shareholder);
        Commons.setSubanchorButton(anchor_shares, "ADD SHAREHOLDER", 200, Commons.ADD_PERSON_GLYPH);
    }
}