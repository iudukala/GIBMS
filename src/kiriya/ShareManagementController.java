/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiriya;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import core.Entity;
import core.Integrator;
import guiMediators.Commons;
import guiMediators.EntityControls;
import guiMediators.tableViewHandler;
import handlers.ValidationHandler.*;
import handlers.dbConcurrent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author ASUS-PC
 */
public class ShareManagementController implements Initializable
{

    EntityControls personCont;
    EntityControls shareholderCont;
    EntityControls search_cont;
    EntityControls searchShareCont;
    EntityControls addShareCont;
    EntityControls labelCont;
    EntityControls stockShareCont;


    JFXTabPane jfxtabpane_shareholder;
    dbConcurrent nbconn;
    int maximum_share_amount = 40000;
    double maximum_share_price = 150;
    int as = 0;
    private tableViewHandler custable_handle;
    private tableViewHandler viewTable;
    private tableViewHandler delete_handle;
    @FXML
    private TabPane tabpane_shareholder;
    @FXML
    private AnchorPane anchor_shareholder;
    @FXML
    private AnchorPane anchor_shares;
    @FXML
    private JFXTextField a_nic;
    @FXML
    private JFXTextField a_fullname;
    @FXML
    private JFXTextField a_address;
    @FXML
    private JFXTextField a_phone;
    @FXML
    private JFXTextField a_email;
    @FXML
    private JFXDatePicker a_dob;
    @FXML
    private JFXTextField a_shareamount;
    @FXML
    private JFXTextField a_bankname;
    @FXML
    private JFXTextField a_shareprice;
    @FXML
    private JFXTextField a_accountno;
    @FXML
    private JFXDatePicker a_dateofissue;
    @FXML
    private JFXDatePicker a_dateofexpire;
    @FXML
    private AnchorPane anchor_update;
    @FXML
    private JFXTextField u_fullname;
    @FXML
    private JFXTextField u_nic;
    @FXML
    private JFXDatePicker u_dob;
    @FXML
    private JFXTextField u_email;
    @FXML
    private JFXTextField u_phone;
    @FXML
    private JFXTextField u_address;
    @FXML
    private JFXDatePicker u_dateofexpire;
    @FXML
    private JFXDatePicker u_dateofissue;
    @FXML
    private JFXTextField u_accountno;
    @FXML
    private JFXTextField u_shareprice;
    @FXML
    private JFXTextField u_bankname;
    @FXML
    private JFXTextField u_shareamount;
    @FXML
    private JFXTextField u_search;
    @FXML
    private TableView<?> u_table;
    @FXML
    private AnchorPane anchor_view;
    @FXML
    private TableView<?> view_table;
    @FXML
    private JFXButton selectButton;
    private TreeTableView<?> apply_table;
    @FXML
    private TableView<?> app_table;
    @FXML
    private GridPane lable_stock;
    @FXML
    private GridPane lable_stock1;
    @FXML
    private JFXTextField add_shares;
    @FXML
    private Label stockss;
    @FXML
    private JFXButton d_select;
    @FXML
    private JFXDatePicker datess;
    @FXML
    private JFXTextField share_id;
    @FXML
    private JFXButton button_search;
    @FXML
    private AnchorPane achor_add_shares;
    @FXML
    private JFXButton approve_share;
    @FXML
    private JFXButton reject_share;
    @FXML
    private JFXButton share_btn;
    @FXML
    private JFXButton ireport;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        nbconn = dbConcurrent.getInstance();
        jfxtabpane_shareholder = Integrator.integrate(anchor_shareholder);


        initializePersonInputs();
        initializeShareholderInputs();
        initializeButton();
        selectButton();
        setshareholderInput();
        setPersonInput();
        tabPane();
        addShares();
        btndisable();
        IReport();
    }


    public void initializeButton()
    {

        Commons.subAnchorButton asab = new Commons.subAnchorButton(anchor_shares, "ADD SHAREHOLDER", Commons.ADD_PERSON_GLYPH);
        asab.setButtonLength(200);
        JFXButton addButton = asab.getButton();

        addButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                Entity person = personCont.getValues();
                person.validate(true);
                int p = person.consolidate();


                Entity shareholder = shareholderCont.getValues();
                shareholder.validate(true);
                int s = shareholder.consolidate();

                if (p == 0 && s == 0)
                {

                    personCont.clearControls();
                    shareholderCont.clearControls();

                }

            }
        });

        Commons.subAnchorButton usab = new Commons.subAnchorButton(anchor_update, "UPDATE SHAREHOLDER", Commons.UPDATE_GLYPH);
        usab.setButtonLength(200);

        JFXButton updateButton = usab.getButton();
        //
        updateButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {

                Entity person, shareholder;

                try
                {

                    person = search_cont.getValues();
                    person.update();
                    search_cont.clearControls();

                    shareholder = searchShareCont.getValues();
                    shareholder.update();
                    searchShareCont.clearControls();
                }
                catch (Exception ex)
                {
                    System.out.println("nullpointer no inputs");

                }
                custable_handle = new tableViewHandler(u_table, "select p.nic, p.full_name,p.dob,p.address,p.phone,p.email,s.share_amount , s.share_price ,"
                        + " s.issue_date , s.expiry_date,s.bank_name,s.account_no,s.approval from person p inner join shareholder s on p.nic=s.nic", nbconn);
                custable_handle.writeToTable();


            }
        });

        share_btn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {


                Entity shares;

                try
                {

                    shares = addShareCont.getValues();
                    shares.update();
                    addShareCont.clearControls();
                }
                catch (Exception ex)
                {


                }
            }

        });

        Commons.subAnchorButton abcc = new Commons.subAnchorButton(anchor_view, "DELETE SHARES", Commons.DELETE_GLYPH);
        abcc.setGlyphWidth(20);
        JFXButton deleteButton = abcc.getButton();

        deleteButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                Entity delete = delete_handle.fetchExtendedSelection("shareholder", "expiry_date");
                delete.deleteFromDB();
                delete_handle.writeToTable();
            }

        });
    }

    private void selectButton()
    {

        button_search.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {

                custable_handle = new tableViewHandler(u_table, "select p.nic, p.full_name,p.dob,p.address,p.phone,p.email,s.share_amount , s.share_price ,"
                        + " s.issue_date , s.expiry_date,s.bank_name,s.account_no,s.approval from person p inner join shareholder s on p.nic=s.nic", nbconn);
                custable_handle.writeToTable();

                Entity search = new Entity("select p.nic, p.full_name ,p.dob,p.address,p.phone,p.email,s.bank_name,s.account_no, s.share_amount, s.share_price, s.issue_date,"
                        + " s.expiry_date,s.approval from person p inner join shareholder s on p.nic=s.nic", nbconn);
                search.add("p.nic", u_search.getText());
                System.out.println(search);
                custable_handle.writeToTable(search.executeAsSearch());

            }

        });
        selectButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                search_cont.setValues(custable_handle.getSelection());
                searchShareCont.setValues(custable_handle.getSelection());

            }
        });
        //search expiry date
        d_select.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                delete_handle = new tableViewHandler(view_table, "select * from shareholder", nbconn);
                delete_handle.writeToTable();

                Entity select = new Entity("select * from shareholder", nbconn);
                select.add("expiry_date", datess.getValue());
                delete_handle.writeToTable(select.executeAsSearch());

            }
        });

        approve_share.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                int share_amount = Integer.parseInt(viewTable.getSelection().getAsString("share_amount"));
                System.out.println(share_amount);


                Entity shareholder_approval = viewTable.fetchExtendedSelection("shareholder", "nic");
                shareholder_approval.add("approval", "approved");
                shareholder_approval.update();


                viewTable = new tableViewHandler(app_table, "select p.nic, p.full_name,p.address,p.dob,p.email,p.phone,s.bank_name,"
                        + "s.account_no,s.share_amount , s.share_price,s.approval ,"
                        + " s.issue_date , s.expiry_date from person p inner join shareholder s on p.nic=s.nic  where approval != 'approved' and approval != 'rejected' or approval is null", nbconn);

                viewTable.writeToTable();

                int amt = Integer.parseInt(Entity.parseFromQuery("select shares from shares", nbconn).get(0).getAsString("shares"));

                amt = amt - share_amount;
                Entity update_shares = new Entity("shares", nbconn);
                update_shares.add("share_id", 1);
                update_shares.add("shares", amt);
                update_shares.update();

                stockss.setText(Entity.parseFromQuery("select shares from shares", nbconn).get(0).getAsString("shares"));
            }
        });

        reject_share.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                Entity shareholder_reject = viewTable.fetchExtendedSelection("shareholder", "nic");
                shareholder_reject.add("approval", "rejected");
                shareholder_reject.update();

                viewTable = new tableViewHandler(app_table, "select p.nic, p.full_name,p.address,p.dob,p.email,p.phone,s.bank_name,"
                        + "s.account_no,s.share_amount , s.share_price,s.approval ,"
                        + " s.issue_date , s.expiry_date from person p inner join shareholder s on p.nic=s.nic  where approval != 'approved' and approval != 'rejected' or approval is null", nbconn);

                viewTable.writeToTable();
            }


        });
    }

    public void tabPane()
    {
        jfxtabpane_shareholder.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
        {


            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {

                viewTable = new tableViewHandler(app_table, "select p.nic, p.full_name,p.address,p.dob,p.email,p.phone,s.bank_name,"
                        + "s.account_no,s.share_amount , s.share_price,s.approval ,"
                        + " s.issue_date , s.expiry_date from person p inner join shareholder s on p.nic=s.nic  where approval != 'approved' and approval != 'rejected' or approval is null", nbconn);

                viewTable.writeToTable();

                //                if((int)newValue == 1)
                //                {
                stockss.setText(Entity.parseFromQuery("select shares from shares", nbconn).get(0).getAsString("shares"));
                //                }


            }
        });
    }

    public void btndisable()
    {
        jfxtabpane_shareholder.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
        {


            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {

                if (newValue != null)
                {
                    int asd = Integer.parseInt(Entity.parseFromQuery("select shares from shares", nbconn).get(0).getAsString("shares"));


                    if (asd != 0)

                    {
                        share_btn.setDisable(true);

                    }
                    else
                    {
                        share_btn.setDisable(false);

                    }
                }
            }
        });

    }

    public void initializePersonInputs()
    {
        personCont = new EntityControls("person", nbconn);
        personCont.add("nic", a_nic, new NICDOBCrossValidator(a_dob));
        personCont.add("full_name", a_fullname);
        personCont.add("email", a_email, new EmailValidator());
        personCont.add("dob", a_dob, new birthdayValidator());
        personCont.add("phone", a_phone, new PhoneValidator());
        personCont.add("address", a_address);
    }

    public void initializeShareholderInputs()
    {
        shareholderCont = new EntityControls("shareholder", nbconn);
        shareholderCont.add("nic", a_nic, new NICValidator());
        shareholderCont.add("share_amount", a_shareamount, new IntegerValidator(maximum_share_amount));
        shareholderCont.add("share_price", a_shareprice, new DoubleValidator(maximum_share_price));
        shareholderCont.add("account_no", a_accountno);
        shareholderCont.add("bank_name", a_bankname);
        shareholderCont.add("issue_date", a_dateofissue, new pastDateValidator());
        shareholderCont.add("expiry_date", a_dateofexpire);
    }


    public void setPersonInput()
    {
        search_cont = new EntityControls("person", nbconn);

        search_cont.add("nic", u_nic, new NICDOBCrossValidator(u_dob));
        search_cont.add("full_name", u_fullname);
        search_cont.add("email", u_email, new EmailValidator());
        search_cont.add("dob", u_dob, new birthdayValidator());
        search_cont.add("phone", u_phone, new PhoneValidator());
        search_cont.add("address", u_address);
        //search_cont.add("nic", a_nic);

    }

    public void setshareholderInput()
    {
        searchShareCont = new EntityControls("shareholder", nbconn);

        searchShareCont.add("nic", u_nic, new NICValidator());
        searchShareCont.add("share_amount", u_shareamount, new IntegerValidator(maximum_share_amount));
        searchShareCont.add("share_price", u_shareprice, new DoubleValidator(maximum_share_price));
        searchShareCont.add("account_no", u_accountno);
        searchShareCont.add("bank_name", u_bankname);
        searchShareCont.add("issue_date", u_dateofissue, new pastDateValidator());
        searchShareCont.add("expiry_date", u_dateofexpire);
    }

    public void addShares()
    {
        addShareCont = new EntityControls("shares", nbconn);

        addShareCont.add("share_id", share_id);
        addShareCont.add("shares", add_shares);

    }

    public void IReport()
    {
        ireport.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                try
                {
                    String path = "/Volumes/Media/Home/isuru/Documents/NetBeansProjects/GIBMS/src/reports/kiriya.jrxml";
                    JasperReport jr = JasperCompileManager.compileReport(path);
                    JasperPrint jp = JasperFillManager.fillReport(jr, null, nbconn.get());
                    JasperViewer.viewReport(jp, false);
                }
                catch (Exception e)
                {
                    System.out.println("ireport error : \n" + e);
                }
            }
        });


    }
}
