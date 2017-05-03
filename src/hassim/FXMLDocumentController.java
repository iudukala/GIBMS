///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package hassim;
//
//import handlers.dbConcurrent;
//import java.net.URL;
//import java.sql.Connection;
//import java.time.LocalDate;
//import java.util.ResourceBundle;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.DatePicker;
//import javafx.scene.control.Label;
//import javafx.scene.control.RadioButton;
//import javafx.scene.control.TabPane;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextArea;
//import javafx.scene.control.TextField;
//import javafx.scene.control.ToggleGroup;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.AnchorPane;
//
///**
// *
// * @author Shamodh
// */
//public class FXMLDocumentController implements Initializable {
//   
//    Connection conn=null;
//    private Label label;
//    private TextField empname;
//    private TextField nic;
//    @FXML
//    private RadioButton female;
//    @FXML
//    private RadioButton male;
//    @FXML
//    private RadioButton married;
//    @FXML
//    private RadioButton unmarried;
//    @FXML
//    private Button upload;
//    @FXML
//    private ImageView image;
//    @FXML
//    private Button emp_add;
//    @FXML
//    private Button v_search;
//    @FXML
//    private TextField v_empid;
//    @FXML
//    private Button v_delete;
//    @FXML
//    private TextField v_empname;
//    @FXML
//    private TextField v_nic;
//    @FXML
//    private TextField v_email;
//    @FXML
//    private TextField v_job;
//    @FXML
//    private TextField v_dob;
//    @FXML
//    private TextField v_address;
//    @FXML
//    private TextField v_tp;
//    @FXML
//    private Button v_save;
//    @FXML
//    private TextArea v_skills;
//    @FXML
//    private Button a_search;
//    @FXML
//    private Label a_job_label;
//    @FXML
//    private Label a_name_label;
//    @FXML
//    private TextField a_empid;
//    @FXML
//    private TextField a_in;
//    @FXML
//    private TextField a_out;
//    @FXML
//    private Button a_in_but;
//    @FXML
//    private Button a_out_but;
//    @FXML
//    private Button a_monthly;
//    @FXML
//    private TableView<?> a_monthly_table;
//    @FXML
//    private TextField p_salary;
//    @FXML
//    private TextField p_overtime;
//    @FXML
//    private TextField p_bonus;
//    @FXML
//    private TextField p_medical;
//    @FXML
//    private TextField p_privilages;
//    @FXML
//    private TextField p_epf;
//    @FXML
//    private TextField p_loan;
//    @FXML
//    private TextField p_other;
//    @FXML
//    private Button p_search;
//    @FXML
//    private TextField p_empid;
//    @FXML
//    private Label p_name_label;
//    @FXML
//    private Label p_job_label;
//    @FXML
//    private ComboBox<?> p_method_combo;
//    @FXML
//    private Button p_addsalary;
//    @FXML
//    private Button p_payslip;
//    @FXML
//    private TextArea t_task;
//    @FXML
//    private TextField t_empid;
//    @FXML
//    private TextField t_time;
//    @FXML
//    private DatePicker t_date;
//    @FXML
//    private Button t_assign;
//    @FXML
//    private TextField p_tax;
//    @FXML
//    private TextField e_empid;
//    @FXML
//    private TextField e_empname;
//    @FXML
//    private TextField e_nic;
//    @FXML
//    private TextField e_email;
//    @FXML
//    private TextField e_job;
//    private TextField e_dob;
//    @FXML
//    private TextField e_address;
//    @FXML
//    private TextField e_tp;
//    @FXML
//    private TextArea e_edu_quali;
//    @FXML
//    private TextArea e_work_exp;
//    @FXML
//    private ToggleGroup e_sex;
//    @FXML
//    private ToggleGroup e_marital;
//    @FXML
//    private DatePicker e_datepicker_dob;
//    @FXML
//    private AnchorPane anchor_hr;
//    @FXML
//    private TabPane tabpane_hr;
//    
//    private dbConcurrent nbconn;
//    private void handleButtonAction(ActionEvent event) {
//        System.out.println("You clicked me!");
//        label.setText("Hello World!");
//    }
//    
//    @Override
//    public void initialize(URL url, ResourceBundle rb)
//    {
//        core.Integrator.integrate(anchor_hr, tabpane_hr);
//        nbconn=new dbConcurrent();
//    }    
//
//    @FXML
//    private void upload_image(ActionEvent event) {
//    }
//
//    @FXML
//    public void add_emp(ActionEvent event) 
//    {
//       
//           
//           String empid=e_empid.getText();
//           String empname=e_empname.getText();
//           String nic=e_nic.getText();
//           
//           String email=e_email.getText();
//           String job=e_job.getText();
//           String edu_quali=e_edu_quali.getText();
//           String work_exp=e_work_exp.getText();
//           String tp=e_tp.getText();
//           String address=e_address.getText();
//           
//           char sex=e_sex.getSelectedToggle().toString().endsWith("'Male'")?'m':'f';
//           char marital_status = (e_marital.getSelectedToggle().toString().endsWith("'Married'"))? 'm':'n';
//           LocalDate dob = e_datepicker_dob.getValue();
//           
//           
//           try
//           {
//               employee employee_object = new employee(empid,empname,nic,dob,email,job, edu_quali, work_exp, sex,marital_status,tp,address);
//               
//                employee_object.consolidate(conn);
//                
//           }
//           catch(Exception e)
//        {
//            System.out.println("Customer controller error\n" + e);
//        }
//    }
//          
//           
//           
//           
//           
//           
//           
//       
//       
//        
//   
//
//    @FXML
//    private void v_search_emp(ActionEvent event) {
//    }
//
//    @FXML
//    private void v_delete_emp(ActionEvent event) {
//    }
//
//    @FXML
//    private void v_save_emp(ActionEvent event) {
//    }
//
//    @FXML
//    private void a_search_emp(ActionEvent event) {
//    }
//
//    @FXML
//    private void a_checkin(ActionEvent event) {
//    }
//
//    @FXML
//    private void a_checkout(ActionEvent event) {
//    }
//
//    @FXML
//    private void a_monthlyattendence(ActionEvent event) {
//    }
//
//    @FXML
//    private void p_search_emp(ActionEvent event) {
//    }
//
//    @FXML
//    private void p_addsalary(ActionEvent event) {
//    }
//
//    @FXML
//    private void p_generatepayslip(ActionEvent event) {
//    }
//
//    @FXML
//    private void t_assign(ActionEvent event) {
//    }
//    
//}
