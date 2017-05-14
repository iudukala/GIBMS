/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legacyFXML;

import core.Entity;
import core.Integrator;
import core.Validator;
import handlers.DynamicTable;
import handlers.dbConcurrent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import legacyEntities.Customer;
import legacyEntities.Person;

import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Isuru Udukala
 */
public class CustomerOldController implements Initializable
{
    Connection conn = null;


    @FXML
    private AnchorPane anchorP;
    @FXML
    private RadioButton radio_statecorp;
    @FXML
    private RadioButton radio_selfemp;
    @FXML
    private RadioButton radio_state;
    @FXML
    private Tab tab_searchcustomer;
    @FXML
    private Button button_searchnic;
    @FXML
    private Button button_searchname;


    //tab1 person controls
    @FXML
    private ToggleGroup genderGroup;
    @FXML
    private ToggleGroup maritalGroup;
    @FXML
    private ToggleGroup serviceGroup;

    @FXML
    private DatePicker datepicker_dob;

    @FXML
    private TextField text_nic;
    @FXML
    private TextField text_name;
    @FXML
    private TextField text_pphone;
    @FXML
    private TextField text_homeaddr;
    @FXML
    private TextField text_email;

    //tab1 customer controls
    @FXML
    private TextField text_wphone;
    @FXML
    private ToggleGroup sectorGroup;
    @FXML
    private TextField text_company;
    @FXML
    private DatePicker date_employment;
    @FXML
    private TextField text_position;

    @FXML
    private TextField text_account;
    @FXML
    private TextField text_branch;
    @FXML
    private TextField text_bank;

    @FXML
    private TextField text_earncareer;
    @FXML
    private TextField text_earnbusiness;
    @FXML
    private TextField text_earnhouses;
    @FXML
    private TextField text_earnvehicles;
    @FXML
    private TextField text_earnland;
    @FXML
    private Text text_addinvalid;
    @FXML
    private TableView table_search;
    @FXML
    private TextField text_searchnic;
    @FXML
    private TextField text_searchname;
    @FXML
    private RadioButton radio_male;
    @FXML
    private RadioButton radio_female;
    @FXML
    private RadioButton radio_married;
    @FXML
    private RadioButton radio_unmarried;
    @FXML
    private RadioButton radio_permanent;
    @FXML
    private RadioButton radio_temporary;
    @FXML
    private Tab tab_addcustomer;
    @FXML
    private Button button_add;
    @FXML
    private Button button_custupdate;
    @FXML
    private TabPane tabpane_customer;
    @FXML
    private Text textelement_nic;
    @FXML
    private TitledPane tpane_spouse;
    @FXML
    private Button button_searchdelete;
    @FXML
    private Button button_searchupdate;

    private Entity getPersonInputs()
    {
        //person object inputs
        String nic = text_nic.getText();
        if (!Validator.isNIC(nic))
            return null;

        String name = text_name.getText();
        String email = text_email.getText();
        if (!Validator.isEmail(email))
            return null;

        String personal_phone = text_pphone.getText();
        if (!Validator.isPhone(personal_phone))
            return null;

        String home_addr = text_homeaddr.getText();
        char gender = (genderGroup.getSelectedToggle().toString().endsWith("'Male'")) ? 'm' : 'f';
        char marital_status = (maritalGroup.getSelectedToggle().toString().endsWith("'Married'")) ? 'm' : 'n';
        LocalDate dob = datepicker_dob.getValue();

        Entity person = new Entity("person", null);
        person.add("nic", nic);
        person.add("full_name", name);
        person.add("dob", dob);
        person.add("email", email);
        person.add("phone", personal_phone);
        person.add("address", home_addr);
        person.add("gender", Character.toString(gender));
        person.add("marital_status", Character.toString(marital_status));

        return person;
    }

    private Entity getCustomerInputs()
    {
        //customer object inputs
        String nic = text_nic.getText();
        if (!Validator.isNIC(nic))
            return null;

        char emp_sector = 'e';
        if (sectorGroup.getSelectedToggle().toString().endsWith("'State'"))
            emp_sector = 's';
        else if (sectorGroup.getSelectedToggle().toString().endsWith("'State corporation'"))
            emp_sector = 'c';

        String work_phone = text_wphone.getText();
        if (!Validator.isPhone(work_phone))
            return null;

        String company = text_company.getText();
        char service_nature = (serviceGroup.getSelectedToggle().toString().endsWith("'Permanent'")) ? 'p' : 't';
        String position = text_position.getText();
        LocalDate employment_start = date_employment.getValue();

        String acc_num = text_account.getText();

        String acc_bank = text_bank.getText();
        String acc_branch = text_branch.getText();

        int career;
        int business;
        int houses;
        int vehicles;
        int land;

        try
        {
            career = Integer.parseInt(text_earncareer.getText());
            business = Integer.parseInt(text_earnbusiness.getText());
            houses = Integer.parseInt(text_earnhouses.getText());
            vehicles = Integer.parseInt(text_earnvehicles.getText());
            land = Integer.parseInt(text_earnland.getText());
        }
        catch (Exception e)
        {
            return null;
        }
        Entity customer = new Entity("customer_state", null);
        customer.add("nic", nic);
        customer.add("work_phone", work_phone);
        customer.add("emp_sector", Character.toString(emp_sector));
        customer.add("company", company);
        customer.add("position", position);
        customer.add("emp_startdate", employment_start);
        customer.add("service_nature", Character.toString(service_nature));
        customer.add("account_num", acc_num);
        customer.add("account_bank", acc_bank);
        customer.add("account_branch", acc_branch);
        customer.add("earn_career", career);
        customer.add("earn_business", business);
        customer.add("earn_houses", houses);
        customer.add("earn_vehicles", vehicles);
        customer.add("earn_land", land);

        return customer;
        //return new Customer(nic,work_phone,company,position,acc_num,acc_bank,acc_branch,sector,service_type, employment, career,business, houses,vehicles,land);
    }

    @FXML
    private void handle_addButton(ActionEvent event)
    {
        Entity person = getPersonInputs();
        Entity customer = getCustomerInputs();

        if (person != null && customer != null)
        {
            text_addinvalid.setVisible(false);

            person.validate(true);
            customer.validate(true);


            person.consolidate();
            customer.consolidate();
        }
        else
        {
            text_addinvalid.setVisible(true);
        }

    }

    @FXML
    private void handleSearchnic(ActionEvent event)
    {
        String nic_search = text_searchnic.getText();
        handlers.DynamicTable.buildData(conn, "select * from customer_view where `nic` like ?;", nic_search, table_search);
    }

    @FXML
    private void handleSearchname(ActionEvent event)
    {
        String name_search = text_searchname.getText();
        DynamicTable.buildData(conn, "select * from customer_view where `full name` like ?;", name_search, table_search);
    }

    @FXML
    private void handle_searchupdate(ActionEvent event)
    {
        int index = table_search.getSelectionModel().getSelectedIndex();
        //int pos = ((TablePosition)table_search.getSelectionModel().getSelectedCells().get(0)).getRow();
        String x = table_search.getItems().get(index).toString();
        String nic = x.split(",")[0].substring(1);

        Person person = legacyEntities.customer_search.personFromSQL(nic, conn);
        Customer customer = legacyEntities.customer_search.customerFromSQL(nic, conn);

        //person
        text_nic.setText(person.nic);
        text_name.setText(person.name);
        text_email.setText(person.email);
        text_pphone.setText(person.personal_phone);
        text_homeaddr.setText(person.home_address);
        datepicker_dob.setValue(LocalDate.parse(person.dob, DateTimeFormatter.ISO_DATE));
        if (person.gender == 'm')
            radio_male.setSelected(true);
        else
            radio_female.setSelected(true);

        if (person.marital_status == 'm')
            radio_married.setSelected(true);
        else
            radio_unmarried.setSelected(true);


        //customer
        text_wphone.setText(customer.work_phone);
        text_company.setText(customer.company);
        text_position.setText(customer.position);
        date_employment.setValue(LocalDate.parse(customer.emp_start_date, DateTimeFormatter.ISO_DATE));
        if (customer.service_nature == 'p')
            radio_permanent.setSelected(true);
        else
            radio_temporary.setSelected(true);
        text_account.setText(customer.acc_num);
        text_bank.setText(customer.acc_bank);
        text_branch.setText(customer.acc_branch);

        text_earncareer.setText(Integer.toString(customer.earn_career));
        text_earnbusiness.setText(Integer.toString(customer.earn_business));
        text_earnhouses.setText(Integer.toString(customer.earn_houses));
        text_earnvehicles.setText(Integer.toString(customer.earn_vehicles));
        text_earnland.setText(Integer.toString(customer.earn_land));

        text_nic.setDisable(true);
        button_add.setDisable(true);
        button_custupdate.setDisable(false);
        tab_addcustomer.setText("Update customer");
        SingleSelectionModel<Tab> selectionmodel = tabpane_customer.getSelectionModel();
        selectionmodel.select(tab_addcustomer);

        textelement_nic.setText("Selected NIC");
        textelement_nic.setFill(Color.RED);
    }

    @FXML
    private void handle_customerupdate(ActionEvent event)
    {
        //        Person person_object = getPersonInputs();
        //        Customer customer_object = getCustomerInputs();
        //
        //        if(person_object != null && customer_object != null)
        //        {
        //            text_addinvalid.setVisible(false);
        //
        //            try
        //            {
        //                PreparedStatement prp = conn.prepareStatement("delete from customer_state where nic = ?");
        //                prp.setString(1, person_object.nic);
        //                prp.executeUpdate();
        //                prp = conn.prepareStatement("delete from person where nic = ?");
        //                prp.setString(1, person_object.nic);
        //                prp.executeUpdate();
        //            }
        //            catch(SQLException e)
        //            {
        //                System.out.println("Error removing customer\n" + e);
        //            }
        //
        //            person_object.consolidate(conn);
        //            customer_object.consolidate(conn);
        //        }
        //        else
        //        {
        //            text_addinvalid.setVisible(true);
        //        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        Integrator.integrate(anchorP);

        conn = dbConcurrent.connect();
        //DynamicTable.getColumns(conn, "select * from customer_view", table_search);

        text_addinvalid.setVisible(false);

        maritalGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
        {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle)
            {
                if (maritalGroup.getSelectedToggle() != null)
                {
                    tpane_spouse.setCollapsible(true);
                    tpane_spouse.setExpanded(maritalGroup.getSelectedToggle().toString().endsWith("'Married'"));
                    tpane_spouse.setCollapsible(false);
                }
            }
        });

        table_search.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->
        {
            if (newSelection != null)
            {
                int index = table_search.getSelectionModel().getSelectedIndex();
                String x = table_search.getItems().get(index).toString();
                String nic = x.split(",")[0].substring(1);
                System.out.println(nic);

                button_searchupdate.setText("Update : " + nic);
                button_searchdelete.setText("Delete : " + nic);
            }
        });
    }
}
