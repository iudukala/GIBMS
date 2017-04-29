/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legacyEntities;

/**
 *
 * @author Hasini Subasinghe
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class arrears_entity { 
    public String nic;
    public String customerID;
    public String fName;
    public String lName;
    public String address;
    public String loanAmount;
    public LocalDate loanDate;
    public LocalDate dueDate;
    public String lastPayment;
    public LocalDate lastPaymentDate;
    public String outstanding;

    public arrears_entity(String nic, String customerID, String fName, String lName, String address, String loanAmount, LocalDate loanDate, LocalDate dueDate, String lastPayment, LocalDate lastPaymentDate, String outstanding)
    {
        this.nic=nic;
        this.customerID=customerID;
        this.fName=fName;
        this.lName=lName;
        this.address=address;
        this.loanAmount=loanAmount;
        this.loanDate=loanDate;
        this.dueDate=dueDate;
        this.lastPayment=lastPayment;
        this.lastPaymentDate=lastPaymentDate;
        this.outstanding=outstanding;
        
        
    }
    
    @Override
    public String toString()
    {
        StringBuilder strb=new StringBuilder();
        //StringBuilder strb=new StringBuilder();
        strb.append("NIC\t:").append(nic);
        strb.append("\ncustomerID\t:").append(customerID);
        strb.append("\nFName\t:").append(fName);
        strb.append("\nLName\t:").append(lName);
        strb.append("\nAddress\t:").append(address);
        strb.append("\nLoanAmount\t:").append(loanAmount);
        strb.append("\nLoanDate\t:").append(loanDate);
        strb.append("\nDueDate\t:").append(dueDate);
        strb.append("\nLastPayment\t:").append(lastPayment);
        strb.append("\nLastPaymentDate\t:").append(lastPaymentDate);
        strb.append("\nOutstanding\t:").append(outstanding);
        
        return strb.toString();
    }
    public void consolidate(Connection conn)
    {
        try
        {
           PreparedStatement prp=conn.prepareStatement("insert into bank.arrears(nic,customerID,fName,lName,address,loanAmount,loanDate,dueDate,lastPayment,lastPaymentDate,outstanding) values(?,?,?,?,?,?,?,?,?,?,?)");
           prp.setString(1,nic);
           prp.setString(2,customerID);
           prp.setString(3, fName);
           prp.setString(4, lName);
           prp.setString(5, address);
           prp.setString(6, loanAmount);
           prp.setString(7, loanDate.toString());
           prp.setString(8, dueDate.toString());
           prp.setString(9, lastPayment);
           prp.setString(10, lastPaymentDate.toString());
           prp.setString(11, outstanding);
           
           prp.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println("Consolidation error:\n" + e);
        }
    }
    
}
