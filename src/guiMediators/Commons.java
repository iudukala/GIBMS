/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiMediators;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.svg.SVGGlyph;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * @author Isuru Udukala
 */
public class Commons
{
    public static final String ADD_PERSON_GLYPH = "M15 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm-9-2V7H4v3H1v2h3v3h2v-3h3v-2H6zm9 4c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z";
    public static final String PERSON_GLYPH = "M12 5.9c1.16 0 2.1.94 2.1 2.1s-.94 2.1-2.1 2.1S9.9 9.16 9.9 8s.94-2.1 2.1-2.1m0 9c2.97 0 6.1 1.46 6.1 2.1v1.1H5.9V17c0-.64 3.13-2.1 6.1-2.1M12 4C9.79 4 8 5.79 8 8s1.79 4 4 4 4-1.79 4-4-1.79-4-4-4zm0 9c-2.67 0-8 1.34-8 4v3h16v-3c0-2.66-5.33-4-8-4z";
    public static final String UPDATE_GLYPH = "M21 10.12h-6.78l2.74-2.82c-2.73-2.7-7.15-2.8-9.88-.1-2.73 2.71-2.73 7.08 0 9.79 2.73 2.71 7.15 2.71 9.88 0C18.32 15.65 19 14.08 19 12.1h2c0 1.98-.88 4.55-2.64 6.29-3.51 3.48-9.21 3.48-12.72 0-3.5-3.47-3.53-9.11-.02-12.58 3.51-3.47 9.14-3.47 12.65 0L21 3v7.12zM12.5 8v4.25l3.5 2.08-.72 1.21L11 13V8h1.5z";
    public static final String LIST_GLYPH = "M3 13h2v-2H3v2zm0 4h2v-2H3v2zm0-8h2V7H3v2zm4 4h14v-2H7v2zm0 4h14v-2H7v2zM7 7v2h14V7H7z";
    public static final String DELETE_GLYPH = "M6 19c0 1.1.9 2 2 2h8c1.1 0 2-.9 2-2V7H6v12zM19 4h-3.5l-1-1h-5l-1 1H5v2h14V4z";
    public static final String NAVIGATION_GLYPH = "M18 8h-1V6c0-2.76-2.24-5-5-5S7 3.24 7 6v2H6c-1.1 0-2 .9-2 2v10c0 1.1.9 2 2 2h12c1.1 0 2-.9 2-2V10c0-1.1-.9-2-2-2zm-6 9c-1.1 0-2-.9-2-2s.9-2 2-2 2 .9 2 2-.9 2-2 2zm3.1-9H8.9V6c0-1.71 1.39-3.1 3.1-3.1 1.71 0 3.1 1.39 3.1 3.1v2z";
    public static final String SEARCH_GLYPH = "M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z";
    public static final String HAMBURGER_GLYPH = "M3 18h18v-2H3v2zm0-5h18v-2H3v2zm0-7v2h18V6H3z";
    public static final String RESET_GLYPH = "M12 5V1L7 6l5 5V7c3.31 0 6 2.69 6 6s-2.69 6-6 6-6-2.69-6-6H4c0 4.42 3.58 8 8 8s8-3.58 8-8-3.58-8-8-8z";
    public static final String BTNSTYLE_1 = "buttonGR";
    public static final String BTNSTYLE_2 = "buttonS2";
    public static final String BTNSTYLE_3 = "buttonRound";

    public static class subAnchorButton
    {
        final String btext;
        final String glyphID;
        AnchorPane subanchor;
        Integer button_length = null;
        Integer button_height = null;
        Integer glyph_width = null;
        Integer glyph_height = null;

        Integer button_depth = null;
        Integer xcoord = null, ycoord = null;

        String style = null;
        String tooltip = null;

        public subAnchorButton(AnchorPane subanchor, String btext, String glyphID)
        {
            this.subanchor = subanchor;
            this.btext = btext;
            this.glyphID = glyphID;
        }

        public subAnchorButton(String btext, String glyphID)
        {
            this.btext = btext;
            this.glyphID = glyphID;
        }

        public JFXButton generateButton()
        {
            if (glyph_width == null)
                glyph_width = 15;
            if (xcoord == null)
                xcoord = 400;
            if (ycoord == null)
                ycoord = 600;
            if (button_length == null)
                button_length = 180;
            if (button_height == null)
                button_height = 50;
            if (style == null)
                style = BTNSTYLE_1;
            if (button_depth == null)
                button_depth = 0;
            if (glyph_height == null)
                glyph_height = 20;


            JFXButton button = new JFXButton(btext);
            if (glyphID != null)
            {
                SVGGlyph glyph = new SVGGlyph(1, "glyph_name", glyphID, Color.WHITE);
                glyph.setSize(glyph_height, glyph_width);
                button.setGraphicTextGap(10);
                button.setGraphic(glyph);
            }

            button.setPrefSize(button_length, button_height);
            button.getStyleClass().add(style);
            JFXDepthManager.setDepth(button, button_depth);
            if (tooltip != null)
            {
                Tooltip t = new Tooltip(tooltip);
                button.setTooltip(t);
            }

            return button;
        }


        public JFXButton getButton()
        {
            JFXButton button = generateButton();
            button.setTranslateX(xcoord);
            button.setTranslateY(ycoord);
            subanchor.getChildren().add(button);

            return button;
        }

        public void setButtonDepth(int depth) {button_depth = depth;}

        public void setButtonLength(int length) {button_length = length;}

        public void setButtonHeigth(int height) {button_height = height;}

        public void setButtonSize(int length, int heigth)
        {
            button_length = length;
            button_height = heigth;
        }

        public void setButtonTooltip(String tooltip) {this.tooltip = tooltip;}

        public void setGlyphWidth(int width) {glyph_width = width;}

        public void setGlyphSize(int height, int width)
        {
            glyph_width = height;
            glyph_height = width;
        }

        public void setCoordinates(int x, int y)
        {
            xcoord = x;
            ycoord = y;
        }

        public void setStyle(String style) {this.style = style;}

    }
}