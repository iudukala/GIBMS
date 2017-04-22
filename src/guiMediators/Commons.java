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
    public static final String UPDATE_GLYPH = "M21 10.12h-6.78l2.74-2.82c-2.73-2.7-7.15-2.8-9.88-.1-2.73 2.71-2.73 7.08 0 9.79 2.73 2.71 7.15 2.71 9.88 0C18.32 15.65 19 14.08 19 12.1h2c0 1.98-.88 4.55-2.64 6.29-3.51 3.48-9.21 3.48-12.72 0-3.5-3.47-3.53-9.11-.02-12.58 3.51-3.47 9.14-3.47 12.65 0L21 3v7.12zM12.5 8v4.25l3.5 2.08-.72 1.21L11 13V8h1.5z";
    public static final String VIEW_LIST_GLYPH = "M4 14h4v-4H4v4zm0 5h4v-4H4v4zM4 9h4V5H4v4zm5 5h12v-4H9v4zm0 5h12v-4H9v4zM9 5v4h12V5H9z";
    public static final String DELETE_GLYPH = "M6 19c0 1.1.9 2 2 2h8c1.1 0 2-.9 2-2V7H6v12zM19 4h-3.5l-1-1h-5l-1 1H5v2h14V4z";
    
    public static JFXButton setSubanchorButton(AnchorPane subanchor, String btext, int length, int gwidth, int xcoord, int ycoord, String glyphID)
    {
        JFXButton button = new JFXButton(btext);
        if(glyphID!=null)
        {
            SVGGlyph glyph = new SVGGlyph(1,"addperson", glyphID, Color.WHITE);
            glyph.setSize(20,gwidth);
            button.setGraphicTextGap(10);
            button.setGraphic(glyph);
        }
        
        button.setPrefSize(length,50);
        button.setTranslateX(xcoord);
        button.setTranslateY(ycoord);
        button.getStyleClass().add("addbutton");
        subanchor.getChildren().add(button);
        JFXDepthManager.setDepth(button, 5);
        
        return button;
    }
    
    public static JFXButton setSubanchorButton(AnchorPane subanchor, String btext, int length, int gwidth, int xcoord, int ycoord)
    {
        return setSubanchorButton(subanchor, btext, length, gwidth, xcoord, ycoord, null);
    }
}
