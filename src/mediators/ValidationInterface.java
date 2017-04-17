/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediators;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.svg.SVGGlyph;
import com.jfoenix.validation.base.ValidatorBase;
import javafx.scene.control.TextInputControl;

import data.Validator;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.paint.Color;

/**
 *
 * @author Isuru Udukala
 */
public class ValidationInterface 
{
    private static void fieldInvalid(SimpleObjectProperty<Node> icon, ReadOnlyBooleanWrapper hasErrors)
    {
        SVGGlyph glyphError = new SVGGlyph(1,"menuicon","M1 21h22L12 2 1 21zm12-3h-2v-2h2v2zm0-4h-2v-4h2v4z",Color.valueOf("#CB503F"));
        glyphError.setSize(15, 15);
        icon.set(glyphError);// = (SimpleObjectProperty<Node>)glyph;
        hasErrors.set(true);
    }
    private static void register(JFXTextField textField)
    {
        NICValidator vv = new NICValidator();
        textField.getValidators().add(vv);//new NICValidator());

        textField.focusedProperty().addListener((o,oldVal,newVal)->
        {
            if(!newVal)
                textField.validate();
        });
    }
    public static class NICValidator extends ValidatorBase
    {
        @Override
        public void eval()
        {
            TextInputControl textField = (TextInputControl) srcControl.get();
            if (Validator.isNIC(textField.getText()) || textField.getText().equals(""))
            {
                hasErrors.set(false);
            }
            else
            {
                message.set("Invalid NIC. (eg : 9595959595V)");
                fieldInvalid(icon, hasErrors);
            }
        }
        public static void register(JFXTextField textField)
        {
            ValidationInterface.register(textField);
        }
    }
    
    public static class PhoneValidator extends ValidatorBase
    {
        @Override
        public void eval()
        {
            TextInputControl textField = (TextInputControl) srcControl.get();
            if (Validator.isPhone(textField.getText()) || textField.getText().equals(""))
            {
                hasErrors.set(false);
            }
            else
            {
                message.set("Invalid phone number. (eg : (077) 123 1234)");
                fieldInvalid(icon, hasErrors);
            }
        }
    }
    
    public static class EmailValidator extends ValidatorBase
    {
        @Override
        public void eval()
        {
            TextInputControl textField = (TextInputControl) srcControl.get();
            if (Validator.isEmail(textField.getText()) || textField.getText().equals(""))
            {
                hasErrors.set(false);
            }
            else
            {
                message.set("Invalid email. (eg: someone@example.com");
                fieldInvalid(icon, hasErrors);
            }
        }
    }
    
    public static class NumberValidator extends ValidatorBase
    {
        @Override
        public void eval()
        {
            TextInputControl textField = (TextInputControl) srcControl.get();
            if (Validator.isNumber(textField.getText()) || textField.getText().equals(""))
            {
                hasErrors.set(false);
            }
            else
            {
                message.set("Invalid value");
                fieldInvalid(icon, hasErrors);
            }
        }
    }
}
