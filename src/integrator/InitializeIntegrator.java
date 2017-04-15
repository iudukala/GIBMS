/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrator;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.svg.SVGGlyph;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author Isuru Udukala
 */
public class InitializeIntegrator
{
    private final static int DRAWER_WIDTH = 300;
    
    public static JFXTabPane initializeTabs(AnchorPane anchor, TabPane tabpane)
    {
        //generate tabpane
        JFXTabPane jfx_tp = new JFXTabPane();
        for(Tab tab : tabpane.getTabs())
            jfx_tp.getTabs().add(tab);
        jfx_tp.setPrefSize(anchor.getPrefWidth(), anchor.getPrefHeight());
        
        JFXDrawer drawer = initializeDrawer(anchor);
        JFXButton button = initializeMenuButton(anchor, jfx_tp,drawer);
        
        anchor.getChildren().clear();
        anchor.getChildren().addAll(jfx_tp,button,drawer);
        return jfx_tp;
    }
    
    private static JFXDrawer initializeDrawer(AnchorPane anchor)
    {
        JFXDrawer drawer = new JFXDrawer();
        JFXListView<Label> list = Navigator.getCategoryList();
        list.setPrefHeight(600);
        list.getStyleClass().add("navigator");
        //list.setExpanded(true);
        //list.expandedProperty().set(true);
        list.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) 
            {
                anchor.getChildren().clear();
                try
                {
                    if((Integer)newValue == 0)
                        anchor.getChildren().add(FXMLLoader.load(getClass().getResource("testrun.fxml")));
                    else if((Integer)newValue == 1)
                        anchor.getChildren().add(FXMLLoader.load(getClass().getResource("customer.fxml")));
                }
                catch(Exception e){}
                System.out.println(oldValue);
                System.out.println(newValue);
            }
        });
        
        //Label hlabel = new Label();hlabel.setPrefHeight(40);
        Label dlabel = new Label("Navigator");dlabel.setPrefHeight(80);
        dlabel.setTextFill(Color.WHITE);
        dlabel.setPadding(new Insets(10,10,10,80));
        SVGGlyph glyph = new SVGGlyph(1,"navigation","M12 2L4.5 20.29l.71.71L12 18l6.79 3 .71-.71z"
                ,Color.WHITE);
        glyph.setSize(20,25);
        dlabel.setGraphic(glyph);
        dlabel.setGraphicTextGap(20);
        
        VBox box = new VBox(dlabel, list);
        JFXDepthManager.setDepth(box, 1);
        box.setPrefHeight(700);
        box.getStyleClass().add("navigator");
        
        drawer.setSidePane(box);
        drawer.setDefaultDrawerSize(DRAWER_WIDTH);
        
        drawer.setOverLayVisible(false);
        drawer.setResizableOnDrag(false);
        
        return drawer;
    }
    
    private static JFXButton initializeMenuButton(AnchorPane anchor, JFXTabPane tp, JFXDrawer drawer)
    {
        JFXButton button = new JFXButton();
        SVGGlyph glyph = new SVGGlyph(1,"menuicon","M3 18h18v-2H3v2zm0-5h18v-2H3v2zm0-7v2h18V6H3z",Color.BLACK);
        glyph.setSize(20,15);
        
        button.setButtonType(JFXButton.ButtonType.RAISED);
        button.getStyleClass().add("menubutton");
        button.setGraphic(glyph);
        button.setTranslateX(anchor.getPrefWidth() - 60);
        button.setTranslateY(20);
        
        button.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                if(drawer.isShown())
                    drawer.close();
                else
                    drawer.open();
//                int val,time,delay;
//                if(tp.translateXProperty().get() == 0)
//                {
//                    delay = 100;
//                    drawer.open();
//                    time = 350;
//                    val = DRAWER_WIDTH;
//                }
//                else
//                {
//                    delay = 50;
//                    time = 350;
//                    drawer.close();
//                    val = 0;
//                }
//                Timeline animation = new Timeline(new KeyFrame(Duration.millis(time),
//                                                               new KeyValue(tp.translateXProperty(),
//                                                                            val,
//                                                                            EASE_BOTH),
//                                                               new KeyValue(tp.translateYProperty(),
//                                                                            0,
//                                                                            EASE_BOTH)));
//                animation.setDelay(Duration.millis(delay));
//                animation.play();
            }
        });
        return button;
    }
}

