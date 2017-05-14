/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legacyEntities;

import core.Manipulator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Other
{

    final String TABLE_NAME = "other_bill";

    public int bill_id;
    public String category, description, date;
    public double price, usage, quantity;

    public Other(int bill_id, String category, String description, double price, double usage, double quantity, LocalDate date)
    {
        this.bill_id = bill_id;
        this.category = category;
        this.description = description;
        this.price = price;
        this.usage = usage;
        this.quantity = quantity;
        this.date = date.format(DateTimeFormatter.ISO_DATE);
    }

    public int getBill_id()
    {
        return bill_id;
    }

    public String getCategory()
    {
        return category;
    }

    public String getDescription()
    {
        return description;
    }

    public double getPrice()
    {
        return price;
    }

    public double getUsage()
    {
        return usage;
    }

    public double getQuantity()
    {
        return quantity;
    }

    public String getDate()
    {
        return date;
    }


    @Override
    public String toString()
    {
        StringBuilder strb = new StringBuilder();
        strb.append("\nBill_id\t :  ").append(bill_id);
        strb.append("\nCategory\t :  ").append(category);
        strb.append("\nDescription\t :  ").append(description);
        strb.append("\nPrice\t :  ").append(price);
        strb.append("\nUsage\t :  ").append(usage);
        strb.append("\nQuantity\t :  ").append(quantity);
        strb.append("\nDate\t :  ").append(date);


        return strb.toString();
    }

    public void consolidate(Connection con)
    {
        try
        {
            String query = "insert into other_bill(`bill_id`,`category`,`description`,`price`,`usage`,`quantity`,`date`)";
            PreparedStatement prp = con.prepareStatement(Manipulator.psFromQuery(query));
            prp.setString(1, Integer.toString(bill_id));
            prp.setString(2, category);
            prp.setString(3, description);
            prp.setString(4, Double.toString(price));
            prp.setString(5, Double.toString(usage));
            prp.setString(6, Double.toString(quantity));
            prp.setString(7, date);
            prp.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println("Consolidation error[other]:\n" + e);
        }
    }

}
