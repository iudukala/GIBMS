package Battibois;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import data.Manipulate;


public class Bill 
{
   final static String TABLE_NAME="bill";

    public String bill_no,date;
    public double unit,amount,arrears,charges;
    public Bill(String bill_no,LocalDate date,double unit,double amount,double arrears,double charges)
    {
        this.bill_no=bill_no;
        this.date=date.format(DateTimeFormatter.ISO_DATE);
        this.unit=unit;
        this.amount=amount;
        this.arrears=arrears;
        this.charges=charges;
    }
   
    public String getDate()
    {
        return date;
    }
   // public String getTax_no()
    //{
     //   return tax_no;
   // }
    public String getBill_no()
    {
        return bill_no;
    }
    public double getUnit()
    {
        return unit;
    }
    public double getAmount()
    {
        return amount;
    }
    public double getArrears()
    {
        return arrears;
    }
    public double getCharges()
    {
        return charges;
    }
    
    @Override
    public String toString()
    {
        StringBuilder strb=new StringBuilder();
        
        strb.append("\nDate\t :  ").append(date);
        //strb.append("\nTax_no\t :  ").append(tax_no);
        strb.append("\nBill_no\t :  ").append(bill_no);        
        strb.append("\nUnit\t : ").append(unit);
        strb.append("\nAmount\t :  ").append(amount);
        strb.append("\nArrears\t :  ").append(arrears);        
        strb.append("\nCharges\t : ").append(charges);
        
        return strb.toString();
    }
    public void consolidate(Connection con)
    {
        try
        {
             String query="insert into bill(`date`,`bill_no`,`units`,`amount`,`arrears`,`charges`)";
            PreparedStatement prp=con.prepareStatement(Manipulate.psFromQuery(query));
           
            prp.setString(1, date);
           // prp.setString(2, tax_no);
            prp.setString(2, bill_no);
            prp.setString(3, Double.toString (unit));
            prp.setString(4, Double.toString (amount));
            prp.setString(5, Double.toString(arrears));
            prp.setString(6, Double.toString(charges));
            prp.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println("Consolidation error[Bill]:\n" + e);
        } 
    } 
}
