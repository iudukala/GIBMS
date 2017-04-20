/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiMediators;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.svg.SVGGlyph;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 *
 * @author Isuru Udukala
 */
public class Commons
{
    public static final String ADD_PERSON_GLYPH = "M15 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm-9-2V7H4v3H1v2h3v3h2v-3h3v-2H6zm9 4c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z";
    public static final String PERSON_GLYPH = "M12 5.9c1.16 0 2.1.94 2.1 2.1s-.94 2.1-2.1 2.1S9.9 9.16 9.9 8s.94-2.1 2.1-2.1m0 9c2.97 0 6.1 1.46 6.1 2.1v1.1H5.9V17c0-.64 3.13-2.1 6.1-2.1M12 4C9.79 4 8 5.79 8 8s1.79 4 4 4 4-1.79 4-4-1.79-4-4-4zm0 9c-2.67 0-8 1.34-8 4v3h16v-3c0-2.66-5.33-4-8-4z";
    public static JFXButton setSubanchorButton(AnchorPane subanchor, String btext, int length, String glyphID)
    {
        JFXButton button = new JFXButton(btext);
        if(glyphID!=null)
        {
            SVGGlyph glyph = new SVGGlyph(1,"addperson", glyphID, Color.WHITE);
            glyph.setSize(20,15);
            button.setGraphicTextGap(10);
            button.setGraphic(glyph);
        }
        
        button.setPrefSize(length,50);
        button.setTranslateX(400);
        button.setTranslateY(600);
        button.getStyleClass().add("addbutton");
        subanchor.getChildren().add(button);
        JFXDepthManager.setDepth(button, 5);
        
        return button;
    }
    
    public static JFXButton setSubanchorButton(AnchorPane subanchor, String btext, int length)
    {
        return setSubanchorButton(subanchor, btext, length, null);
    }
}
