/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legacy;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Isuru Udukala
 */
public class Main_guiController implements Initializable
{
    @FXML private TreeView tree;
    @FXML private Pane pane_object;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        tree.setRoot(core.Navigator.getCategoryTree());
        tree.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>()
        {
            @Override
            public void changed(ObservableValue<? extends TreeItem<String>> observable, TreeItem<String> old_val, TreeItem<String> new_val)
            {
                    TreeItem<String> selectedItem = new_val;
                    System.out.println("Selected Text : " + selectedItem.getValue());
                    System.out.println(tree.getSelectionModel().getSelectedIndex());
                    try
                    {
                        core.Navigator.switchPane(pane_object,tree.getSelectionModel().getSelectedIndex());
                    }
                    catch(Exception e)
                    {
                        System.out.println("Pane switch error\n" + e);
                    }
            }
        });
        tree.getSelectionModel().clearSelection();
        tree.setShowRoot(false);
    }
}
