/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paniya;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import core.Entity;
import guiMediators.Commons;
import guiMediators.tableViewHandler;
import handlers.dbConcurrent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.controlsfx.control.PopOver;

/**
 * @author praneethjayawickrama
 */
public class history
{
    public static void getPopOver(dbConcurrent nbconn, JFXTextField textfield, JFXButton button)
    {
        Label heading = new Label("Find Bank Voucher Receipt Details");
        heading.getStyleClass().add("subheaders");
        heading.setStyle("-fx-padding: 10 5 10 20;");

        HBox hbox = new HBox();
        hbox.setStyle("-fx-padding: 30 5 5 5;");

        String textStyle = "-fx-padding: 0 20 0 20;";
        double textWidth = 270;

        JFXTextField serial = new JFXTextField();
        serial.setPromptText("Serial No");
        serial.setStyle(textStyle);
        serial.setPrefWidth(textWidth);


        Commons.subAnchorButton pnj = new Commons.subAnchorButton("Search", null);
        pnj.setStyle(Commons.BTNSTYLE_2);
        pnj.setButtonSize(((Double) button.getMaxWidth()).intValue(), ((Double) button.getMaxHeight()).intValue());
        JFXButton btn_search = pnj.generateButton();
        btn_search.setTranslateX(50);

        TableView table_pop = new TableView();
        table_pop.setPrefSize(700, 300);

        hbox.getChildren().addAll(serial, btn_search);
        VBox vbox = new VBox(heading, hbox, table_pop);
        JFXDepthManager.setDepth(vbox, 0);

        String query = "select * from bvr_add ";
        tableViewHandler tvh_pnj = new tableViewHandler(table_pop, query, nbconn);
        tvh_pnj.writeToTable();

        btn_search.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                Entity search_ent = new Entity(query, nbconn);
                search_ent.add("serial_no", serial.getText());
                tvh_pnj.writeToTable(search_ent.executeAsSearch());
            }
        });

        table_pop.getSelectionModel().selectedItemProperty().addListener(new ChangeListener()
        {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue)
            {
                if (tvh_pnj.getSelection() != null)
                    textfield.setText(tvh_pnj.getSelection().getAsString("serial_no"));
            }
        });

        PopOver popover = new PopOver(vbox);
        popover.getRoot().getStylesheets().add("resources/tableOverride.css");

        popover.show(button);
    }


    public static void getPopOvergl(dbConcurrent nbconn, JFXTextField textfield, JFXButton button)
    {
        Label heading = new Label("Find General Ledger Details");
        heading.getStyleClass().add("subheaders");
        heading.setStyle("-fx-padding: 10 5 10 20;");

        HBox hbox = new HBox();
        hbox.setStyle("-fx-padding: 30 5 5 5;");

        String textStyle = "-fx-padding: 0 20 0 20;";
        double textWidth = 270;

        JFXTextField serial = new JFXTextField();
        serial.setPromptText("Transaction Code");
        serial.setStyle(textStyle);
        serial.setPrefWidth(textWidth);


        Commons.subAnchorButton pnj = new Commons.subAnchorButton("Search", null);
        pnj.setStyle(Commons.BTNSTYLE_2);
        pnj.setButtonSize(((Double) button.getMaxWidth()).intValue(), ((Double) button.getMaxHeight()).intValue());
        JFXButton btn_search = pnj.generateButton();
        btn_search.setTranslateX(50);

        TableView table_pop = new TableView();
        table_pop.setPrefSize(700, 300);

        hbox.getChildren().addAll(serial, btn_search);
        VBox vbox = new VBox(heading, hbox, table_pop);
        JFXDepthManager.setDepth(vbox, 0);

        String query = "select a.transaction_code,a.transaction_name,a.date,a.branch_no,a.serial_no,a.narration,a.company_name,a.voucher_receipt,b.nic,b.description,b.credit_amount,b.debit_amount from bvr_add a inner join bvr_addmember b on a.serial_no =b.serial_no";
        tableViewHandler tvh_pnj = new tableViewHandler(table_pop, query, nbconn);
        tvh_pnj.writeToTable();

        btn_search.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                Entity search_ent = new Entity(query, nbconn);
                search_ent.add("a.transaction_code", serial.getText());
                tvh_pnj.writeToTable(search_ent.executeAsSearch());
            }
        });

        table_pop.getSelectionModel().selectedItemProperty().addListener(new ChangeListener()
        {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue)
            {
                if (tvh_pnj.getSelection() != null)
                    textfield.setText(tvh_pnj.getSelection().getAsString("transaction_code"));
            }
        });

        PopOver popover = new PopOver(vbox);
        popover.getRoot().getStylesheets().add("resources/tableOverride.css");

        popover.show(button);
    }


}
