/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hassim;

import core.Manipulator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Shamodh
 */
public class employee
{
    public String empid;
    public String empname;
    public String nic;
    public String dob;
    public String email;
    public String job;
    public String edu_quali;
    public String work_exp;
    public char sex;
    public char marital_status;
    public String tp, address;

    public employee(String empid, String empname, String nic, LocalDate dob, String email, String job, String edu_quali, String work_exp, char sex, char marital_status, String tp, String address)
    {
        this.empid = empid;
        this.empname = empname;
        this.nic = nic;
        this.dob = dob.format(DateTimeFormatter.ISO_DATE);
        this.email = email;
        this.job = job;
        this.sex = sex;
        this.marital_status = marital_status;
        this.work_exp = work_exp;
        this.edu_quali = edu_quali;
        this.tp = tp;
        this.address = address;


    }


    @Override
    public String toString()
    {
        StringBuilder strb = new StringBuilder();
        strb.append("EMP_ID\t:").append(empid);
        strb.append("\nEMP_NAME\t:").append(empname);
        strb.append("\nNIC\t:").append(nic);
        strb.append("\nDOB\t:").append(dob);
        strb.append("\nE-MAIL\t:").append(email);
        strb.append("\nJOB\t:").append(job);
        strb.append("\nSEX\t:").append(sex);
        strb.append("\nMARITAL\t:").append(marital_status);
        strb.append("\nEDU_QUALI\t:").append(edu_quali);
        strb.append("\nWORK_EXP\t:").append(work_exp);
        strb.append("\nTELEPHONE\t:").append(tp);
        strb.append("\nADDRESS\t:").append(address);
        return strb.toString();
    }

    public void consolidate(Connection conn)
    {
        try
        {
            String query = "insert into employee_details(empid,empname,nic,dob,email,job,sex,marital_status,edu_quali,work_exp,tp,address)";
            PreparedStatement prp = conn.prepareStatement(Manipulator.psFromQuery(query));
            prp.setString(1, empid);
            prp.setString(2, empname);
            prp.setString(3, nic);
            prp.setString(4, dob);
            prp.setString(5, email);
            prp.setString(6, job);
            prp.setString(7, Character.toString(sex));
            prp.setString(8, Character.toString(marital_status));
            prp.setString(9, edu_quali);
            prp.setString(10, work_exp);
            prp.setString(11, tp);
            prp.setString(12, address);
            prp.executeUpdate();

        }
        catch (SQLException e)
        {
            System.out.println("Consolidation error[Person]:\n" + e);
        }


    }
}


