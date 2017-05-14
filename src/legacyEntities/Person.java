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

/**
 * @author Isuru Udukala
 */
public class Person
{
    public String name;
    public String nic;
    public String email;
    public String dob;
    public String personal_phone;
    public String home_address;
    public char gender;
    public char marital_status;

    public Person(String name, String nic, LocalDate dob, String personal_phone, String home_address, char gender, char marital_status, String email)
    {
        this.name = name;
        this.nic = nic;
        this.dob = dob.format(DateTimeFormatter.ISO_DATE);
        this.personal_phone = personal_phone;
        this.home_address = home_address;
        this.gender = gender;
        this.marital_status = marital_status;
        this.email = email;
    }

    @Override
    public String toString()
    {
        StringBuilder strb = new StringBuilder();
        strb.append("NIC\t:").append(nic);
        strb.append("\nName\t:").append(name);
        strb.append("\nDOB\t:").append(dob);
        strb.append("\nEmail\t:").append(email);
        strb.append("\nPhone\t:").append(personal_phone);
        strb.append("\nAddress\t:").append(home_address);
        strb.append("\nGender\t:").append(gender);
        strb.append("\nMarital\t:").append(marital_status);
        return strb.toString();
    }

    public void consolidate(Connection conn)
    {
        try
        {
            String query = "insert into person(nic, full_name, email, dob, phone, address, gender, marital_status)";
            PreparedStatement prp = conn.prepareStatement(Manipulator.psFromQuery(query));
            prp.setString(1, nic);
            prp.setString(2, name);
            prp.setString(3, email);
            prp.setString(4, dob);
            prp.setString(5, personal_phone);
            prp.setString(6, home_address);
            prp.setString(7, Character.toString(gender));
            prp.setString(8, Character.toString(marital_status));
            prp.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println("Consolidation error[Person]:\n" + e);
        }
    }
}
