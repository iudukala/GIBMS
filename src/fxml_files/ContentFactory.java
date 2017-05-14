/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml_files;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
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
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import org.controlsfx.control.PopOver;

/**
 * @author Isuru Udukala
 */
public class ContentFactory
{
    public static void getPopOver(dbConcurrent nbconn, JFXTextField textfield, JFXButton button)
    {
        Label heading = new Label("Select customer state for loan request");
        heading.getStyleClass().add("subheaders");
        heading.setStyle("-fx-padding: 10 5 10 20;");

        HBox hbox = new HBox();
        hbox.setStyle("-fx-padding: 30 5 5 5;");

        String textStyle = "-fx-padding: 0 20 0 20;";
        double textWidth = 270;

        JFXTextField snic = new JFXTextField();
        snic.setPromptText("NIC filter");
        snic.setStyle(textStyle);
        snic.setPrefWidth(textWidth);

        JFXTextField sname = new JFXTextField();
        sname.setPromptText("Name filter");
        sname.setStyle(textStyle);
        sname.setPrefWidth(textWidth);

        Commons.subAnchorButton ssab = new Commons.subAnchorButton("Filter results", null);
        ssab.setStyle(Commons.BTNSTYLE_2);
        ssab.setButtonSize(((Double) button.getMaxWidth()).intValue(), ((Double) button.getMaxHeight()).intValue());
        JFXButton btn_search = ssab.generateButton();
        btn_search.setTranslateX(50);

        TableView table_lcid = new TableView();
        table_lcid.setPrefSize(700, 300);

        hbox.getChildren().addAll(snic, sname, btn_search);
        VBox vbox = new VBox(heading, hbox, table_lcid);
        JFXDepthManager.setDepth(vbox, 0);

        String query = "select * from lcid_view";
        tableViewHandler tvh_lcid = new tableViewHandler(table_lcid, query, nbconn);
        tvh_lcid.writeToTable();

        btn_search.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                Entity search_ent = new Entity(query, nbconn);
                search_ent.add("nic", snic.getText());
                search_ent.add("full name", sname.getText());
                tvh_lcid.writeToTable(search_ent.executeAsSearch());
            }
        });

        table_lcid.getSelectionModel().selectedItemProperty().addListener(new ChangeListener()
        {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue)
            {
                if (tvh_lcid.getSelection() != null)
                    textfield.setText(tvh_lcid.getSelection().getAsString("stateid"));
            }
        });

        PopOver popover = new PopOver(vbox);
        popover.getRoot().getStylesheets().add("resources/tableOverride.css");

        popover.show(button);
    }

    public static JFXDialog getDialog(String str_head, String str_body, int style)
    {
        JFXDialogLayout layout = new JFXDialogLayout();

        Text heading = new Text(str_head);
        if (style == 1)
            heading.setFill(Paint.valueOf("#57E7C5"));
        else if (style == 2)
            heading.setFill(Paint.valueOf("#CB503F"));
        heading.getStyleClass().add("subheaders");
        heading.setStyle("-fx-font-size: 18px;");

        Label body = new Label(str_body);
        body.setMinWidth(450);

        body.setStyle("-fx-font-size: 14px; -fx-text-fill: #D3D3D3;");// -fx-text-alignment: left");
        body.setTextAlignment(TextAlignment.LEFT);
        layout.setAlignment(Pos.BASELINE_LEFT);

        layout.setHeading(heading);
        layout.setBody(body);
        layout.setStyle("-fx-background-color: #424242;");

        JFXButton button = new JFXButton("Okay");
        button.setStyle("-fx-font-family: \"Roboto condensed\"; -fx-font-size: 16; -fx-text-fill: WHITE;");
        layout.setActions(button);

        JFXDialog dialog = new JFXDialog(new StackPane(), layout, JFXDialog.DialogTransition.TOP, true);
        button.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                dialog.close();
            }
        });
        return dialog;
    }
}
