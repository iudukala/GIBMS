/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * @author Isuru Udukala
 */
public class Manipulator
{
    //DO NOT USE
    public static String psFromQuery(String sqlQuery)
    {
        StringBuilder strb = new StringBuilder(sqlQuery);
        strb.append(" values(");
        for (int i = 1; i < sqlQuery.split(",").length; i++)
        {
            strb.append("?,");
        }
        strb.append("?);");
        System.out.println(strb.toString());
        return strb.toString();
    }

    public static String formatTabs(Object obj, int tabs, boolean after)
    {
        String str = obj.toString();
        if (tabs * 8 < str.length())
            return str;
        else
        {
            StringBuilder strb = new StringBuilder();
            if (after)
                strb.append(str);
            for (int i = 0; i < (tabs - str.length() / 8); i++)
                strb.append("\t");
            if (!after)
                strb.append(str);
            return strb.toString();
        }
    }

    public static String getClassStr(Object obj)
    {
        String class_str = null;
        try
        {
            if (obj.getClass().equals(Class.forName("java.util.HashMap$Node")))
                class_str = ((Map.Entry) obj).getValue().getClass().toString();
            else if (obj.getClass().equals(Class.class))
                class_str = obj.toString();
            else
                class_str = obj.getClass().toString();
        }
        catch (ClassNotFoundException e)
        {
            return null;
        }

        if (class_str != null)
            return class_str.replace("class java.lang.", "").replace("class java.time.", "");
        else
            return null;
    }

    public static void setToggleSelection(ToggleGroup tgroup, String selection)
    {
        boolean match = false;
        Toggle toggle = null;
        for (int i = 0; i < tgroup.getToggles().size(); i++)
        {
            toggle = tgroup.getToggles().get(i);
            if (toggle.getUserData().equals(selection))
            {
                match = true;
                toggle.setSelected(true);
            }
        }
        if (!match)
            toggle.setSelected(true);
    }

    public static Class translateClass(String classstr)
    {
        if (classstr == null)
            return null;
        switch (classstr)
        {
            case "int":
                return Integer.class;
            case "varchar":
                return String.class;
            case "double":
                return Double.class;
            case "date":
                return LocalDate.class;
            default:
                return null;
        }
    }

    public static String toSentenceCase(String str)
    {
        return str.toLowerCase().replaceFirst(Character.toString(str.charAt(0)), Character.toString(str.charAt(0)).toUpperCase());
    }

    public static LocalDate parseISODate(String datestr)
    {
        LocalDate date;
        try
        {
            date = LocalDate.parse(datestr, DateTimeFormatter.ISO_DATE);
        }
        catch (Exception e)
        {
            date = null;
        }
        return date;
    }
}