/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hassim;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import core.Entity;
import core.Integrator;
import guiMediators.Commons;
import guiMediators.EntityControls;
import guiMediators.tableViewHandler;
import handlers.dbConcurrent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import handlers.ValidationHandler. *;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


/**
 * FXML Controller class
 *
 * @author Shamodh
 */
public class FXMLTemplateController implements Initializable {
    
    dbConcurrent nbconn;
    EntityControls empcont;
    EntityControls personCont;
     EntityControls atcont;
     EntityControls atcontl;
     EntityControls paycont;
     JFXTabPane jfxtabpane_employee;
    private tableViewHandler emptable_handle;

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private TabPane tabpane;
    @FXML
    private AnchorPane a_ap1;
    @FXML
    private JFXTextField a_empid;
    @FXML
    private JFXTextField a_jobtitle;
    @FXML
    private JFXTextField a_empname;
    @FXML
    private JFXTextField a_nic;
    @FXML
    private JFXTextField a_email;
    @FXML
    private JFXTextField a_address;
    @FXML
    private JFXDatePicker a_dob_date;
    @FXML
    private JFXRadioButton a_male_rb;
    @FXML
    private JFXRadioButton a_female_rb;
    @FXML
    private JFXTextField a_bankname;
    @FXML
    private JFXTextField a_accountholder;
    @FXML
    private JFXTextField a_tp1;
    @FXML
    private JFXTextField a_tp2;
    @FXML
    private JFXTextField a_accountnumber;
    @FXML
    private JFXDatePicker a_joiningdate_date;
    @FXML
    private JFXRadioButton a_married_rb;
    @FXML
    private JFXRadioButton a_notmarried_rb;
    @FXML
    private JFXTextField a_department;
    @FXML
    private JFXTextField a_inusarancenumber;
    @FXML
    private JFXTextField a_insuarancecompany;
    @FXML
    private ToggleGroup tgroup_sex;
    @FXML
    private ToggleGroup tgroup_marital;
    @FXML
    private JFXButton u_select;
    @FXML
    private JFXButton search;
    @FXML
    private TableView<?> u_employee_table;
    @FXML
    private JFXTextField u_namee;
    @FXML
    private JFXTextField u_searchhh;
    @FXML
    private JFXButton delete;
    @FXML
    private AnchorPane at_ap1;
    @FXML
    private JFXTextField at_reason;
    @FXML
    private JFXTextField at_type;
    private JFXDatePicker at_leavedate_date;
    @FXML
    private JFXTextField at_empid1;
    @FXML
    private JFXTextField a_salary;
    @FXML
    private JFXDatePicker a_salarydate_date;
    @FXML
    private JFXTextField p_overtime;
    @FXML
    private JFXTextField p_privilages;
    @FXML
    private JFXTextField p_other;
    @FXML
    private JFXTextField p_tax;
    @FXML
    private JFXTextField p_loan;
    @FXML
    private JFXTextField p_provident;
    @FXML
    private JFXTextField p_medical;
    @FXML
    private JFXTextField p_bonus;
    @FXML
    private JFXTextField p_empid;
    @FXML
    private AnchorPane p_ap1;
    @FXML
    private JFXTextField p_total;
    @FXML
    private JFXTextField p_basic;
    @FXML
    private JFXDatePicker at_date_date;
    @FXML
    private JFXTextField at_tp;
    @FXML
    private JFXTextField at_remarks;
    @FXML
    private JFXDatePicker at_to_date;
    @FXML
    private JFXDatePicker at_from_date;
    @FXML
    private JFXButton ireport;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        nbconn = dbConcurrent.getInstance();
         jfxtabpane_employee = Integrator.integrate(anchorpane);
        initializeaddbutton();
        initializeRadioButtons();
        initializeupdatebutton();
        //initializesubmitbutton();
        initializebookbutton();
        initializepersoninputs();
        initializeEmpinputs();
        //initializeattendenceinputs();
        initializeleaveinputs();
        select();
        search();
        delete();
        initializecalculatebutton();
        initializepayrollinputs();
        ireport();
    
    } 
    
    public void initializepersoninputs()
    {
        personCont=new EntityControls("person",nbconn);
        personCont.add("full_name", a_empname);
        personCont.add("nic",a_nic,new NICValidator());
        personCont.add("dob",a_dob_date, new birthdayValidator() );
        personCont.add("email",a_email,new EmailValidator() );
        personCont.add("gender",tgroup_sex );
        personCont.add("marital_status",tgroup_marital );
        personCont.add("address",a_address);
        personCont.add("phone",a_tp1,new PhoneValidator());
    }
    
    public void initializeEmpinputs()
    {
        empcont=new EntityControls("employee_details",nbconn);
        empcont.add("nic",a_nic,new NICValidator());
        empcont.add("empid",a_empid);
        empcont.add("job",a_jobtitle );
        empcont.add("department",a_department );
        empcont.add("joining_date",a_joiningdate_date,new pastDateValidator());
        empcont.add("account_no",a_accountnumber );
        empcont.add("account_holder",a_accountholder );
        empcont.add("bank_name",a_bankname);
        empcont.add("insurance_no",a_inusarancenumber );
        empcont.add("insurance_company",a_insuarancecompany );
        empcont.add("tp2",a_tp2,new PhoneValidator());
        empcont.add("salary",a_salary);
        empcont.add("salary_date",a_salarydate_date);
    
    }
    
//      public void initializeattendenceinputs()
//    {
//        atcont=new EntityControls("Attendence",nbconn);
//        atcont.add("empid",at_empid);
//        atcont.add("date",at_date_date);
//        atcont.add("check_in",at_checkin);
//        atcont.add("check_out",at_checkout);
//        
//    }
    
      public void initializeleaveinputs()
      {
        atcontl=new EntityControls("Attendence_leave",nbconn);
        atcontl.add("empid",at_empid1);
        atcontl.add("type",at_type);
        atcontl.add("reason",at_reason);
        atcontl.add("leave_date",at_date_date);
        atcontl.add("phone",at_tp);
        atcontl.add("from",at_from_date);
        atcontl.add("to",at_to_date);
        atcontl.add("remarks",at_remarks);
        
      }
      
      public void initializepayrollinputs()
      {
          paycont=new EntityControls("payroll",nbconn);
          paycont.add("empid",p_empid);
          paycont.add("overtime",p_overtime);
          paycont.add("bonus",p_bonus);
          paycont.add("medical",p_medical);
          paycont.add("privilages",p_privilages);
          paycont.add("provident_fund",p_provident);
          paycont.add("loan",p_loan);
          paycont.add("tax",p_tax);
          paycont.add("other",p_other);
          
      }
      
      
    
        public void initializeaddbutton()
    {
        Commons.subAnchorButton a_emp_button = new Commons.subAnchorButton(a_ap1,"ADD EMPLOYEE", Commons.ADD_PERSON_GLYPH);
        JFXButton addButton = a_emp_button.getButton();
        
        addButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                
                Entity person = personCont.getValues();
                person.validate(true);
                int p=person.consolidate();
                
                Entity emp=empcont.getValues();
                emp.validate(true);
                int E =emp.consolidate();
                
                if(E==0 && p==0)
                {
                    personCont.clearControls();
                    empcont.clearControls();
                }
                
            }
            
        });
    }
     
        
        public void initializeupdatebutton()
    {
        Commons.subAnchorButton u_emp_button = new Commons.subAnchorButton(a_ap1,"UPDATE EMPLOYEE", Commons.UPDATE_GLYPH);
         u_emp_button.setCoordinates(100, 600);
         u_emp_button.setButtonLength(200);
        JFXButton updateButton = u_emp_button.getButton();
        
        updateButton.setOnAction(new EventHandler<ActionEvent>()
       {
        @Override
            public void handle(ActionEvent e)
            {
                Entity Employee,person;
                
             
            
            try{
            
                Employee = empcont.getValues();
                Employee.update();
                
                person = personCont.getValues();
                person.update();
                }
            catch(Exception ex)
            {
            System.out.println("nullpointer no inputs");
            }
            
            }
        });
    }
    private void initializeRadioButtons()
    {
        a_male_rb.setUserData("M");
        a_female_rb.setUserData("F");
        
        a_married_rb.setUserData("M");
        a_notmarried_rb.setUserData("N");
    
    }
        
     
//    public void initializesubmitbutton()
//    
//    {
//        
//        Commons.subAnchorButton at_emp_button = new Commons.subAnchorButton(at_ap1,"SUBMIT", Commons.ADD_PERSON_GLYPH);
//        at_emp_button.setCoordinates(700, 160);
//        at_emp_button.setButtonHeigth(90);
//        JFXButton submitbutton = at_emp_button.getButton();
//        
//        
//        submitbutton.setOnAction(new EventHandler<ActionEvent>()
//        {
//            @Override
//            public void handle(ActionEvent e)
//            {
//                Entity submit=atcont.getValues();
//                submit.validate(true);
//                int at = submit.consolidate();
//                
//                if(at==0)
//                {
//                    atcont.clearControls();
//                }
//                
//            }
//            
//        });
//        
//    }
         public void initializebookbutton()
         {
                Commons.subAnchorButton at_book_button = new Commons.subAnchorButton(at_ap1,"BOOK", Commons.LIST_GLYPH);
               at_book_button.setButtonHeigth(90);
               at_book_button.setCoordinates(700,140);
               
                JFXButton bookbutton = at_book_button.getButton();
        
        bookbutton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                Entity attendence=atcontl.getValues();
                attendence.validate(true);
                int atl = attendence.consolidate();
                
                if(atl==0)
                {
                    atcontl.clearControls();
                }
             
            }
            
        });
         }
      public void select()
      {
            u_select.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                //String snic = text_csnic.getText();
                //String sname = text_csname.getText();
                
                //empcont.setValues(emptable_handle.getSelection("employee_details", "empid"));
                personCont.setValues(emptable_handle.getSelection());
                empcont.setValues(emptable_handle.getSelection());
                
                
                //customerControls.setValues(custable_handle.getSelection("customer_state", "NIC"));
                jfxtabpane_employee.getSelectionModel().select(0);
            }
        });
      }
      
      public void search()
      {
      
      search.setOnAction(new EventHandler<ActionEvent>()
        
                {
            @Override
            public void handle(ActionEvent e)
            { 
                emptable_handle=new tableViewHandler(u_employee_table," select   e.empid , p.nic,  p.full_name  ,e.job, p.address,p.email,e.nic,"
                        + "p.phone,p.gender,p.dob,p.marital_status,e.tp2, "
                        + "e.department,e.joining_date,e.account_no,e.bank_name, e.account_holder,e.insurance_no,e.insurance_company,e.salary,e.salary_date "
                        + " from person p"
                        + " inner join employee_details e on p.nic=e.nic",nbconn);                
                emptable_handle.writeToTable();
                
                Entity search=new Entity("select e.empid , p.nic,  p.full_name  ,e.job, p.address,p.email,e.nic,"
                        + "p.phone,p.gender,p.dob,p.marital_status,e.tp2, "
                        + "e.department,e.joining_date,e.account_no,e.bank_name, e.account_holder,e.insurance_no,e.insurance_company,e.salary,e.salary_date"
                        + " from person p inner join employee_details e on p.nic=e.nic",nbconn);
//                    Entity search=new Entity("select p.nic, p.full_name ,p.dob,p.address,p.phone,p.email,e.job,e.empid,"
//                            + "e.tp2 from person p inner join employee_details e on p.nic=e.nic",nbconn);
                    search.add("e.empid",u_searchhh.getText());
                    search.add("p.full_name",u_namee.getText());
                    System.out.println(search);
                   emptable_handle.writeToTable(search.executeAsSearch());
                   
                   
                   
                   
            }
                });
      }
      public void delete()
      {
      delete.setOnAction(new EventHandler<ActionEvent>()
        
                {
            @Override
            public void handle(ActionEvent e)
            { 
                Entity delete = emptable_handle.fetchExtendedSelection("employee_details", "nic");
                Entity elete = emptable_handle.fetchExtendedSelection("person", "nic");
                delete.deleteFromDB();
                elete.deleteFromDB();
                emptable_handle.writeToTable();
                
            }
      
              });
      }
      
      public void initializecalculatebutton()
      {
            Commons.subAnchorButton p_emp_button = new Commons.subAnchorButton(p_ap1,"CALCULATE SALARY", Commons.HAMBURGER_GLYPH);
            p_emp_button.setButtonLength(250);
            JFXButton calculateButton = p_emp_button.getButton();
            //String value = p_empid.getText();
            
        
        calculateButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
//                double num1;
//                num1 = Double.parseDouble(p_empid.getText());
                
                //
                p_basic.setText(Entity.parseFromQuery("select salary from employee_details where empid=321", nbconn).get(0).getAsString("salary"));
                
               p_total.setText(Entity.parseFromQuery("SELECT (overtime + bonus + medical+privilages-provident_fund-loan-tax-other) as subtotal\n" +
"FROM payroll \n" +
"where empid=321", nbconn).get(0).getAsString("subtotal"));
               
              
                
                Entity pay=paycont.getValues();
                pay.validate(true);
                int P =pay.consolidate();
                
                
                //int overtime = Integer.parseInt(Entity.parseFromQuery("select overtime from payroll",nbconn).get(0).getAsString("overtime"));
//                double bonus = Integer.parseInt(Entity.parseFromQuery("select bonus from payroll",nbconn).get(0).getAsString("payroll"));
//                double medical  = Integer.parseInt(Entity.parseFromQuery("select medical from payroll",nbconn).get(0).getAsString("payroll"));
//                double privilages = Integer.parseInt(Entity.parseFromQuery("select privilages from payroll",nbconn).get(0).getAsString("payroll"));
//                double provident_fund = Integer.parseInt(Entity.parseFromQuery("select provident_fund from payroll",nbconn).get(0).getAsString("payroll"));
//                double loan = Integer.parseInt(Entity.parseFromQuery("select loan from payroll",nbconn).get(0).getAsString("payroll"));
//                double tax = Integer.parseInt(Entity.parseFromQuery("select tax from payroll",nbconn).get(0).getAsString("payroll"));
//                double other = Integer.parseInt(Entity.parseFromQuery("select other from payroll",nbconn).get(0).getAsString("payroll"));
//                
//                double total=(overtime+bonus+medical+privilages)-(provident_fund+loan+tax+other);
                
                

//select (sum(overtime)+sum(bonus)+sum(medical)+sum(privilages)) as allowances from payroll where empid=1212
        
//        int amt = Integer.parseInt(Entity.parseFromQuery("select (sum(overtime)+sum(bonus)+sum(medical)+sum(privilages)) as allowances from payroll where empid=1212",nbconn).get(0).getAsString("allowances"));
//        System.out.println(amt);
                
                
                
                if(P==0 )
                {
                    paycont.clearControls();
                }
             }
            
        });
        
//        int amtt = Integer.parseInt(Entity.parseFromQuery("select (sum(overtime)+sum(bonus)+sum(medical)+sum(privilages)) as "
//                + "allowances from payroll group by empid",nbconn).get(0).getAsString("allowances"));
//        System.out.println(amtt);
        
          
      }
      public void ireport()
      {
      ireport.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                try
                {
                    String path = "/Volumes/Media/Home/isuru/Documents/NetBeansProjects/GIBMS/src/reports/isuru1.jrxml";
                    JasperReport jr =net.sf.jasperreports.engine.JasperCompileManager.compileReport(path);
                    JasperPrint jp =net.sf.jasperreports.engine.JasperFillManager.fillReport(jr,null,nbconn.get());
                    JasperViewer.viewReport(jp,false);
                }
                catch(Exception e)
                {
                    System.out.println("ireport error : \n" + e);
                }
            }
        });
      
      
      
      
      
      }
      
      
     
}
