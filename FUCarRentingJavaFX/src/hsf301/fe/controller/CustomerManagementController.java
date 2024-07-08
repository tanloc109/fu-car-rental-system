package hsf301.fe.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import com.fucar.pojo.Account;
import com.fucar.pojo.Customer;
import com.fucar.service.AccountService;
import com.fucar.service.CustomerService;
import com.fucar.service.IAccountService;
import com.fucar.service.ICustomerService;

public class CustomerManagementController implements Initializable {

    @FXML
    private TableView<Customer> data;

    @FXML
    private TableColumn<Customer, Integer> idColumn;
    @FXML
    private TableColumn<Customer, String> nameColumn;
    @FXML
    private TableColumn<Customer, LocalDate> birthdayColumn;
    @FXML
    private TableColumn<Customer, String> mobileColumn;
    @FXML
    private TableColumn<Customer, String> emailColumn;
    @FXML
    private TableColumn<Customer, String> identityColumn;
    @FXML
    private TableColumn<Customer, String> licenceColumn;
    @FXML
    private TableColumn<Customer, LocalDate> licenceDateColumn;
    @FXML
    private TableColumn<Customer, String> roleColumn;
    @FXML
    private TableColumn<Customer, String> accountColumn;
    @FXML
    private Button btnBack;
    @FXML
    private TextField txtName;
    @FXML
    private DatePicker txtBirthday;
    @FXML
    private TextField txtMobile;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtIdentityCard;
    @FXML
    private TextField txtLicenceNumber;
    @FXML
    private DatePicker txtLicenceDate;
    @FXML
    private TextField txtRole;
    @FXML
    private TextField txtAccountName;
    @FXML
    private TextField txtPassword;

    private int customerID;

    private final ICustomerService customerService;
    private final IAccountService accountService;

    public CustomerManagementController() {
        this.customerService = new CustomerService();
        this.accountService = new AccountService();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        mobileColumn.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        identityColumn.setCellValueFactory(new PropertyValueFactory<>("identityCard"));
        licenceColumn.setCellValueFactory(new PropertyValueFactory<>("licenceNumber"));
        licenceDateColumn.setCellValueFactory(new PropertyValueFactory<>("licenceDate"));
        roleColumn.setCellValueFactory(cellData -> {
            if (cellData.getValue().getAccount() != null) {
                return new SimpleStringProperty(cellData.getValue().getAccount().getRole());
            } else {
                return new SimpleStringProperty("");
            }
        });
        accountColumn.setCellValueFactory(cellData -> {
            if (cellData.getValue().getAccount() != null) {
                return new SimpleStringProperty(cellData.getValue().getAccount().getAccountName());
            } else {
                return new SimpleStringProperty("");
            }
        });

        // Customize DatePicker to display only date
        StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };

        txtBirthday.setConverter(converter);
        txtLicenceDate.setConverter(converter);

        data.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showCustomer(newValue);
            }
        });

        loadData();
    }



    private void loadData() {
        List<Customer> customers = customerService.findAll();
        ObservableList<Customer> customerList = FXCollections.observableArrayList(customers);
        data.setItems(customerList);
    }

    private void showCustomer(Customer customer) {
        this.customerID = customer.getCustomerID();
        this.txtName.setText(customer.getCustomerName());
        
        if (customer.getBirthday() != null) {
            this.txtBirthday.setValue(((Date) customer.getBirthday()).toLocalDate());
        } else {
            this.txtBirthday.setValue(null);
        }
        
        this.txtMobile.setText(customer.getMobile());
        this.txtEmail.setText(customer.getEmail());
        this.txtIdentityCard.setText(customer.getIdentityCard());
        this.txtLicenceNumber.setText(customer.getLicenceNumber());
        
        if (customer.getLicenceDate() != null) {
            this.txtLicenceDate.setValue(((Date) customer.getLicenceDate()).toLocalDate());
        } else {
            this.txtLicenceDate.setValue(null);
        }
        
        if (customer.getAccount() != null) {
            this.txtRole.setText(customer.getAccount().getRole());
            this.txtAccountName.setText(customer.getAccount().getAccountName());
        } else {
            this.txtRole.setText("");
            this.txtAccountName.setText("");
        }
        
        this.txtPassword.setText(customer.getPassword());
    }


    private void refreshDataTable() {
        this.customerID = 0;
        this.txtName.setText("");
        this.txtBirthday.setValue(null);
        this.txtMobile.setText("");
        this.txtEmail.setText("");
        this.txtIdentityCard.setText("");
        this.txtLicenceNumber.setText("");
        this.txtLicenceDate.setValue(null);
        this.txtRole.setText("");
        this.txtAccountName.setText("");
        this.txtPassword.setText("");

        loadData();
    }

    private void showAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    private void showErrorAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    private void showWarningAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    @FXML
	public void back() throws IOException {
	    Stage currentStage = (Stage) btnBack.getScene().getWindow();

	    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/AdminScreen.fxml"));
	    Parent root = fxmlLoader.load();
	    Stage stage = new Stage();
	    stage.setScene(new Scene(root));
	    stage.setTitle("Admin Screen");
	    stage.show();
	    
	    currentStage.close();
	}

    @FXML
    public void addCustomer() {
        if (!validateInput()) {
            return;
        }

        if (!validateDates()) {
            return;
        }
        if (!validateRole()) {
            return;
        }
        
        Account needCheck = accountService.findByAccountName(txtAccountName.getText());
        if (needCheck != null) {
        	showErrorAlert("Error", "Account name is duplicate");
        	return;
        }
        
        Customer newCustomer = new Customer();
        newCustomer.setCustomerName(txtName.getText());
        newCustomer.setMobile(txtMobile.getText());
        newCustomer.setIdentityCard(txtIdentityCard.getText());
        newCustomer.setLicenceNumber(txtLicenceNumber.getText());
        newCustomer.setEmail(txtEmail.getText());
        newCustomer.setPassword(txtPassword.getText());

        if (txtBirthday.getValue() != null) {
            newCustomer.setBirthday(Date.valueOf(txtBirthday.getValue()));
        }

        if (txtLicenceDate.getValue() != null) {
            newCustomer.setLicenceDate(Date.valueOf(txtLicenceDate.getValue()));
        }

        Account newAccount = new Account();
        newAccount.setAccountName(txtAccountName.getText());
        newAccount.setRole(txtRole.getText());
        newCustomer.setAccount(newAccount);
        accountService.save(newAccount);

        customerService.save(newCustomer);

        refreshDataTable();
        showAlert("Success", "Customer added successfully");
    }

    private boolean validateInput() {
        if (txtName.getText().isEmpty() || txtMobile.getText().isEmpty() ||
            txtIdentityCard.getText().isEmpty() || txtLicenceNumber.getText().isEmpty() ||
            txtEmail.getText().isEmpty() || txtRole.getText().isEmpty() ||
            txtAccountName.getText().isEmpty() || txtPassword.getText().isEmpty()) {
            showErrorAlert("Error", "All fields are required.");
            return false;
        }
        
        return true;
    }

    private boolean validateDates() {
        LocalDate birthday = txtBirthday.getValue();
        LocalDate licenceDate = txtLicenceDate.getValue();

        if (birthday != null && licenceDate != null) {
            if (licenceDate.isBefore(birthday)) {
                showErrorAlert("Error", "Licence date cannot be before birthday.");
                return false;
            }
        }

        return true;
    }

    private boolean validateRole() {
        String role = txtRole.getText();
        if (!role.equalsIgnoreCase("Admin") && !role.equalsIgnoreCase("Customer")) {
            showErrorAlert("Error", "Role must be either 'Admin' or 'Customer'.");
            return false;
        }

        return true;
    }

    @FXML
    public void updateCustomer() {
    	if (!validateInput()) {
            return;
        }

        if (!validateDates()) {
            return;
        }
        if (!validateRole()) {
            return;
        }
        Customer existingCustomer = data.getSelectionModel().getSelectedItem();
        if (existingCustomer == null) {
            showErrorAlert("Error", "Please select a customer to update.");
            return;
        }
        
        Account needCheck = accountService.findByAccountName(txtAccountName.getText());
        if (needCheck != null && needCheck.getAccountID() != existingCustomer.getAccount().getAccountID()) {
        	showErrorAlert("Error", "Account name is duplicate");
        	return;
        }

        existingCustomer.setCustomerName(txtName.getText());
        existingCustomer.setMobile(txtMobile.getText());
        existingCustomer.setIdentityCard(txtIdentityCard.getText());
        existingCustomer.setLicenceNumber(txtLicenceNumber.getText());
        existingCustomer.setEmail(txtEmail.getText());
        existingCustomer.setPassword(txtPassword.getText());

        if (txtBirthday.getValue() != null) {
            existingCustomer.setBirthday(Date.valueOf(txtBirthday.getValue()));
        } else {
            existingCustomer.setBirthday(null);
        }

        if (txtLicenceDate.getValue() != null) {
            existingCustomer.setLicenceDate(Date.valueOf(txtLicenceDate.getValue()));
        } else {
            existingCustomer.setLicenceDate(null);
        }
        
        Account account = existingCustomer.getAccount();
        account.setAccountName(txtAccountName.getText());
        account.setRole(txtRole.getText());
        accountService.update(account);
        customerService.update(existingCustomer);

        refreshDataTable();
        showAlert("Success", "Customer updated successfully");
    }


    @FXML
    public void deleteCustomer() {
        customerService.delete(customerID);
        refreshDataTable();
        showAlert("Success", "Customer deleted successfully");
    }
}
