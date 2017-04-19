/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legacy;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author ASUS-PC
 */
public class add_btn {
    public static void display(String title, String message)
    {
    
    Stage window =new Stage();
    window.setTitle(title);
    window.setMinWidth(300);
    window.setMaxHeight(150);
    
    Label label = new Label();
    label.setText(message);
    Button buttonOK = new Button("OK");
    Button buttoncansel = new Button("cancel");
    buttonOK.setOnAction(e-> window.close());
    
    VBox layout = new VBox(5);
    layout.getChildren().addAll(label,buttonOK,buttoncansel);
    layout.setAlignment(Pos.CENTER);
    
    Scene scene = new Scene(layout);
    window.setScene(scene);
    window.showAndWait();
    
    }
    
    
    
}
