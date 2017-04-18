/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paniya;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import core.Manipulator;
/**
 *
 * @author Isuru Udukala
 */
public class bank_receipt_voucher 
{
    String serial_no,receipt_voucher,branch_no,branch_name,transation_code,transation_name,
            narration,nic,resource_id,name,description,payment_type,cheque_no;
    Double amount,total_amount;
    LocalDate date,cheque_date;

   public bank_receipt_voucher(String serial_no,LocalDate date,String receipt_voucher,String branch_no,String branch_name,String transation_code,String transation_name,String narration,String nic,String resource_id,String name,String description,Double amount,Double total_amount,String payment_type,String cheque_no,LocalDate cheque_date)
    {
        this.serial_no = serial_no;
        this.date = date ;
        this.receipt_voucher = receipt_voucher;
        this.branch_no = branch_no;
        this.branch_name = branch_name;
        this.transation_code = transation_code;
        this.transation_name = transation_name;
        this.narration = narration;
        this.nic = nic;
        this.resource_id = resource_id;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.total_amount = total_amount;
        this.payment_type = payment_type;
        this.cheque_no = cheque_no;
        this.cheque_date = cheque_date;
    }

    bank_receipt_voucher(String serial_no, LocalDate date, String transaction_code, String transaction_name, String nic, String resource_id, String name, String description, Double amount, Double total_amount, String payment_type, String cheque_no, LocalDate cheque_date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
   @Override
    public String toString()
    {
        StringBuilder string =new StringBuilder();
        string.append("\nserial_no\t :  ").append(serial_no);
        string.append("\ndate\t :  ").append(date.toString());
        string.append("\nreceipt_voucher\t :  ").append(receipt_voucher);
        string.append("\nbranch_no\t :  ").append(branch_no);
        string.append("\nbranch_name\t :  ").append(branch_name);
        string.append("\ntransation_code\t :  ").append(transation_code);
        string.append("\ntransation_name\t :  ").append(transation_name);
        string.append("\nnarration\t :  ").append(narration);
        string.append("\nnic\t :  ").append(nic);
        string.append("\nresource_id\t :  ").append(resource_id);
        string.append("\nname\t :  ").append(name);
        string.append("\ndescription\t :  ").append(description);
        string.append("\namount\t :  ").append(amount);
        string.append("\ntotal_amount\t :  ").append(total_amount);
        string.append("\npayment_type\t :  ").append(payment_type);
        string.append("\ncheque_no\t :  ").append(cheque_no);
        string.append("\ncheque_date\t :  ").append(cheque_date);
        
        
        return string.toString();
    }
    
    public void consolidate(Connection conn)
    {
        System.out.println("AA");
        try
        {   String query = "insert into bank_receipt_voucher(serial_no,date,receipt_voucher,branch_no,branch_name,transation_code,transation_name,narration,nic,resource_id,name,description,amount,total_amount,payment_type,cheque_no,cheque_date)";
            PreparedStatement prp = conn.prepareStatement(Manipulator.psFromQuery(query));
            prp.setString(1,serial_no);
            prp.setString(2, date.toString());
            prp.setString(3,receipt_voucher);
            prp.setString(4, branch_no);
            prp.setString(5, branch_name);
            prp.setString(6, transation_code);
            prp.setString(7, transation_name);
            prp.setString(8, narration);
            prp.setString(9, nic);
            prp.setString(10, resource_id);
            prp.setString(11, name);
            prp.setString(12, description);
            prp.setDouble(13, amount);
            prp.setDouble(14, total_amount);
            prp.setString(15, payment_type);
            prp.setString(16, cheque_no);
            prp.setString(17, cheque_date.toString());
            prp.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println("Consolidation error [bank_receipt_voucher]:\n" + e);
        }
    }
    
    
}








