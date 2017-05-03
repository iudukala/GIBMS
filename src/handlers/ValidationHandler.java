/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.svg.SVGGlyph;
import com.jfoenix.validation.base.ValidatorBase;
import javafx.scene.control.TextInputControl;

import core.Validator;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.paint.Color;

/**
 *
 * @author Isuru Udukala
 */
public class ValidationHandler 
{
    private static final SVGGlyph GLYPH_ERROR = new SVGGlyph(1,"menuicon","M1 21h22L12 2 1 21zm12-3h-2v-2h2v2zm0-4h-2v-4h2v4z",Color.valueOf("#CB503F"));
    
    private static <T extends ValidatorBase> void register(T validator, JFXTextField textField)
    {
        textField.getValidators().add(validator);
        textField.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
            {
                if(!newValue)
                    textField.validate();
            }
        });
    }
    
    private static void fieldInvalid(SimpleObjectProperty<Node> icon, ReadOnlyBooleanWrapper hasErrors)
    {
        GLYPH_ERROR.setTranslateY(3);
        GLYPH_ERROR.setSize(15, 15);
        icon.set(GLYPH_ERROR);
        hasErrors.set(true);
    }
    
    public static class NICValidator extends ValidatorBase implements ValidationInterface
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
        @Override
        public void register(JFXTextField textField)
        {
            ValidationHandler.register(this, textField);
        }
    }
    
    public static class ExistingNICValidator extends ValidatorBase implements ValidationInterface
    {
        private dbConcurrent nbconn = null;
        public ExistingNICValidator(dbConcurrent nbconn)
        {
            this.nbconn = nbconn;
        }
        @Override
        public void eval()
        {
            TextInputControl textField = (TextInputControl) srcControl.get();
            if (!Validator.isExistingNIC(textField.getText(), nbconn) || textField.getText().equals(""))
            {
                hasErrors.set(false);
            }
            else
            {
                message.set("NIC already exists in system");
                fieldInvalid(icon, hasErrors);
            }
        }
        //public void register(JFXTextField textField, dbConcurrent p_nbconn)
        public void register(JFXTextField textField)
        {
            ValidationHandler.register(this, textField);
        }
    }
    
    public static class PhoneValidator extends ValidatorBase implements ValidationInterface
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
                message.set("Invalid phone number");
                fieldInvalid(icon, hasErrors);
            }
        }
        @Override
        public void register(JFXTextField textField)
        {
            ValidationHandler.register(this, textField);
        }
    }
    
    public static class EmailValidator extends ValidatorBase implements ValidationInterface
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
                message.set("Invalid email address");
                fieldInvalid(icon, hasErrors);
            }
        }
        @Override
        public void register(JFXTextField textField)
        {
            ValidationHandler.register(this, textField);
        }
    }
    
    public static class IntegerValidator extends ValidatorBase implements ValidationInterface
    {
        @Override
        public void eval()
        {
            TextInputControl textField = (TextInputControl) srcControl.get();
            if (Validator.isInteger(textField.getText()) || textField.getText().equals(""))
            {
                hasErrors.set(false);
            }
            else
            {
                message.set("Invalid value");
                fieldInvalid(icon, hasErrors);
            }
        }
        @Override
        public void register(JFXTextField textField)
        {
            ValidationHandler.register(this, textField);
        }
    }
    
    public static class DoubleValidator extends ValidatorBase implements ValidationInterface
    {
        @Override
        public void eval()
        {
            TextInputControl textField = (TextInputControl) srcControl.get();
            if(Validator.isDouble(textField.getText()) || textField.getText().equals(""))
            {
                hasErrors.set(false);
            }
            else
            {
                message.set("Invalid value");
                fieldInvalid(icon, hasErrors);
            }
        }
        @Override
        public void register(JFXTextField textField)
        {
            ValidationHandler.register(this, textField);
        }
    }
    
    public static class birthdayValidator extends ValidatorBase implements ValidationInterface
    {
        @Override
        public void eval()
        {
            TextInputControl textField = (TextInputControl) srcControl.get();
            if (Validator.isValidBirthday(textField.getText()) || textField.getText().equals(""))
            {
                hasErrors.set(false);
            }
            else
            {
                message.set("Applicant must be over 18");
                fieldInvalid(icon, hasErrors);
            }
        }
        @Override
        public void register(JFXTextField textField)
        {
            ValidationHandler.register(this, textField);
        }
    }
    
    public static class pastDateValidator extends ValidatorBase implements ValidationInterface
    {
        @Override
        public void eval()
        {
            TextInputControl textField = (TextInputControl) srcControl.get();
            if (Validator.isPastDate(textField.getText()) || textField.getText().equals(""))
            {
                hasErrors.set(false);
            }
            else
            {
                message.set("Cannot be a future date");
                fieldInvalid(icon, hasErrors);
            }
        }
        @Override
        public void register(JFXTextField textField)
        {
            ValidationHandler.register(this, textField);
        }
    }
}
