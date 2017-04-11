/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author Isuru Udukala
 */
public class Manipulator
{
    public static String psFromQuery(String sqlQuery)
    {
        StringBuilder strb=new StringBuilder(sqlQuery);
        strb.append(" values(");
        for(int i=1; i<sqlQuery.split(",").length; i++)
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
        if(tabs*8 < str.length())
            return str;
        else
        {
            StringBuilder strb = new StringBuilder();
            if(after)
                strb.append(str);
            for(int i=0;i<(tabs - str.length()/8);i++)
                strb.append("\t");
            if(!after)
                strb.append(str);
            return strb.toString();
        }
    }
}
