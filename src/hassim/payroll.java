/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hassim;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import data.Manipulator;
/**
 *
 * @author Shamodh
 */
public class payroll 
{
    public double basic_salary,overtime,bonus,medical,privilages,epf,loan,tax,other;
    


public payroll(double basic_salary,double overtime,double bonus,double medical,double privilages,double epf,double loan,double tax,double other)
{
    this.basic_salary=basic_salary;
    this.bonus=bonus;
    this.epf=epf;
    this.loan=loan;
    this.medical=medical;
    this.other=other;
    this.overtime=overtime;
    this.privilages=privilages;
    this.tax=tax;
}

    @Override
    public String toString()
    {
        StringBuilder strb=new StringBuilder();
        strb.append("\nBasic_Salary\t:").append(basic_salary);
        strb.append("\nBonus\t:").append(bonus);
        strb.append("\nEPF\t:").append(epf);
        strb.append("\nLoan\t:").append(loan);
        strb.append("\nMedical\t:").append(medical);
        strb.append("\nOther\t:").append(other);
        strb.append("\nOvertime\t:").append(overtime);
        strb.append("\nPrivilages\t:").append(privilages);
        strb.append("\nTax\t:").append(tax);
        return strb.toString();
    }
    
     public void consolidate(Connection conn)
    {
        try
        {
            String query = "insert into payroll(basic_salary,overtime,bonus,medical,privilages,epf,loan,tax,other)";
            PreparedStatement prp=conn.prepareStatement(Manipulator.psFromQuery(query));
            prp.setDouble(1,basic_salary);
            prp.setDouble(2,overtime);
            prp.setDouble(3, bonus);
            prp.setDouble(4, medical);
            prp.setDouble(5, privilages);
            prp.setDouble(6, epf);
            prp.setDouble(7, loan);
            prp.setDouble(8, tax);
            prp.setDouble(9,other);
            prp.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println("Consolidation error[Person]:\n" + e);
        }
    }
    



}
