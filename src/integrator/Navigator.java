/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrator;

import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Isuru Udukala
 */
public class Navigator
{
    private static List<List<String>> initializeTree()
    {
        List<List<String>> TREE_LIST = new ArrayList<>();
        
        TREE_LIST.add(new ArrayList(Arrays.asList("/fxml_files/Customer.fxml","Customer & loan plan")));
        TREE_LIST.add(new ArrayList(Arrays.asList("/fxml_files/CustomerOld.fxml","Cashflow management")));
        TREE_LIST.add(new ArrayList(Arrays.asList("/kiriya/FXMLDocument.fxml","Share management")));
        TREE_LIST.add(new ArrayList(Arrays.asList("/janiya/FXMLDocument.fxml", "External bank fund management")));
        TREE_LIST.add(new ArrayList(Arrays.asList("/battibois/FXMLDocument.fxml", "Resource management")));
        TREE_LIST.add(new ArrayList(Arrays.asList("/hasini/FXMLDocument.fxml", "Arrears management")));
        TREE_LIST.add(new ArrayList(Arrays.asList("/dumiya/FXMLDocument.fxml", "Insurance management")));
        TREE_LIST.add(new ArrayList(Arrays.asList("/hassim/FXMLDocument.fxml", "HR & payroll management")));
        
        
//        TREE_LIST.add(new ArrayList(Arrays.asList("/paniya/FXMLDocument.fxml","Cashflow management","<Placeholder>")));
//        TREE_LIST.add(new ArrayList(Arrays.asList("/fxml_files/customer.fxml","Customer & loan plan","Add customer", "Search customers")));
//        TREE_LIST.add(new ArrayList(Arrays.asList("/kiriya/FXMLDocument.fxml","Share management","<Placeholder>")));
//        TREE_LIST.add(new ArrayList(Arrays.asList("/janiya/FXMLDocument.fxml", "External bank fund management","<Placeholder>")));
//        TREE_LIST.add(new ArrayList(Arrays.asList("/battibois/FXMLDocument.fxml", "Resource management","<Placeholder>")));
//        TREE_LIST.add(new ArrayList(Arrays.asList("/hasini/FXMLDocument.fxml", "Arrears management","<Placeholder>")));
//        TREE_LIST.add(new ArrayList(Arrays.asList("/dumiya/FXMLDocument.fxml", "Insurance management","<Placeholder>")));
//        TREE_LIST.add(new ArrayList(Arrays.asList("/hassim/FXMLDocument.fxml", "HR & payroll management","<Placeholder>")));
        return TREE_LIST;
    }
    
    public static JFXListView<Label> getCategoryList()
    {
        JFXListView<Label> list = new JFXListView<>();
        final List<List<String>> TREE_LIST = initializeTree();
        
        for(int i=0;i<TREE_LIST.size();i++)
        {
            Label catLabel = new Label(TREE_LIST.get(i).get(1));
            catLabel.setFont(Font.font(12.5));
            list.getItems().add(catLabel);
        }
        return list;
    }
    
    
    public static void switchForm(AnchorPane anchor, int index)
    {
        final List<List<String>> TREE_LIST = initializeTree();
        if(index>-1 && index<TREE_LIST.size())
        {
            Parent parent = null;
            //anchor.getChildren().clear();
            try
            {
                //anchor.getChildren().add(FXMLLoader.load(Navigator.class.getResource("/fxml_files/Customer.fxml")));
                parent = FXMLLoader.load(Navigator.class.getResource(TREE_LIST.get(index).get(0)));
            }
            catch (IOException e)
            {
                System.out.println("Form switch error\n" + e);
            }
            
            Stage stage = (Stage)anchor.getScene().getWindow();
            Scene scene = new Scene(parent, 1000,700);
            
            stage.setScene(scene);
            stage.show();
        }
//        switch(index)
//        {
//            case 0:
//                anchor.getChildren().add(FXMLLoader.load(Navigator.class.getResource("testrun.fxml")));
//                break;
//            case 1:
//                anchor.getChildren().add(FXMLLoader.load(Navigator.class.getResource("testrun.fxml")));
//        }
    }
    
    //deprecated
    public static TreeItem<String> getCategoryTree()
    {
        final List<List<String>> TREE_LIST = initializeTree();
        TreeItem<String> root = new TreeItem<>("Categories");
        root.setExpanded(true);
        for(int i=0;i<TREE_LIST.size();i++)
        {
            TreeItem<String> category = new TreeItem<>(TREE_LIST.get(i).get(1));
            
            root.getChildren().add(category);
            category.setExpanded(true);
            
            for(int j=2;j<TREE_LIST.get(i).size();j++)
            {
                TreeItem<String> sub= new TreeItem<>(TREE_LIST.get(i).get(j));
                category.getChildren().add(sub);
            }
        }
        return root;
    }
    
    //deprecated
    public static void switchPane(Pane current_pane, int tree_index) throws Exception
    {
        //if(tree_index>0)tree_index--;else    return;
        //TreeView.setShowRoot() should be set to false;
        
        final List<List<String>> TREE_LIST = initializeTree();
        
        int row_index=0, column_index = 0, counter=0;
        boolean found=false;
        for(int i=0;i<TREE_LIST.size() && !found;i++)
        {
            row_index=i;
            for(int j=1;j<TREE_LIST.get(i).size() && !found;j++)
            {
                if(counter == tree_index)
                    found=true;
                counter++;
                column_index=j;
            }
        }
        System.out.println("Indexes: " + counter + "\t" + row_index + "\t" + column_index );
        System.out.println(TREE_LIST.get(row_index).get(column_index));
        System.out.println(TREE_LIST.get(row_index).get(0));
        
        Pane newpane=null;
        if(column_index==1)
            newpane = FXMLLoader.load((new Object() { }.getClass().getEnclosingClass()).getResource(TREE_LIST.get(row_index).get(0)));
        
        current_pane.getChildren().clear();
        current_pane.getChildren().add(newpane);
    }
}
