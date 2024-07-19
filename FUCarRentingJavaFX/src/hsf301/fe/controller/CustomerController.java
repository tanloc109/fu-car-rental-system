package hsf301.fe.controller;

import com.fucar.login.AccountLogin;
import com.fucar.pojo.Account;
import com.fucar.pojo.CarRental;
import com.fucar.pojo.Customer;
import com.fucar.service.AccountService;
import com.fucar.service.CarRentalService;
import com.fucar.service.CustomerService;
import com.fucar.service.IAccountService;
import com.fucar.service.ICarRentalService;
import com.fucar.service.ICustomerService;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    @FXML
    private TableView<CarRental> historyTable;

    @FXML
    private TableColumn<CarRental, Integer> rentalCodeColumn;

    @FXML
    private TableColumn<CarRental, Integer> carIDColumn;

    @FXML
    private TableColumn<CarRental, String> carNameColumn;

    @FXML
    private TableColumn<CarRental, String> colorColumn;

    @FXML
    private TableColumn<CarRental, String> producerNameColumn;

    @FXML
    private TableColumn<CarRental, LocalDate> pickupDateColumn;

    @FXML
    private TableColumn<CarRental, LocalDate> returnDateColumn;

    @FXML
    private TableColumn<CarRental, Double> rentPriceColumn;

    @FXML
    private Button btnUpdate;

    @FXML
    private TextField txtCustomerID;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtBirthday;

    @FXML
    private TextField txtMobile;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtIdentityCard;

    @FXML
    private TextField txtLicenceNumber;

    @FXML
    private TextField txtLicenceDate;

    @FXML
    private TextField txtAccountName;

    @FXML
    private TextField txtPassword;
    
    @FXML
    private Button btnBack;
    
    @FXML
    private Button btnLogout;

    private ICustomerService customerService;
    private ICarRentalService carRentalService;
    private IAccountService accountService;

    private Customer customer;

    public CustomerController() {
        customerService = new CustomerService();
        carRentalService = new CarRentalService();
        accountService = new AccountService();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rentalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("rentalID"));
        carIDColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCar().getCarID()));
        carNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCar().getCarName()));
        colorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCar().getColor()));
        producerNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCar().getProducer().getProducerName()));
        pickupDateColumn.setCellValueFactory(new PropertyValueFactory<>("pickupDate"));
        returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        rentPriceColumn.setCellValueFactory(new PropertyValueFactory<>("rentPrice"));

        loadCustomerData();
        loadBookingHistory();
    }

    private void loadCustomerData() {
        int customerId = AccountLogin.curUserLogin.getCustomerID();
        customer = customerService.findById(customerId);

        if (customer != null) {
            txtCustomerID.setText(String.valueOf(customer.getCustomerID()));
            txtName.setText(customer.getCustomerName());
            txtBirthday.setText(customer.getBirthday().toString());
            txtMobile.setText(customer.getMobile());
            txtEmail.setText(customer.getEmail());
            txtIdentityCard.setText(customer.getIdentityCard());
            txtLicenceNumber.setText(customer.getLicenceNumber());
            txtLicenceDate.setText(customer.getLicenceDate().toString());
            txtAccountName.setText(customer.getAccount().getAccountName());
            txtPassword.setText(customer.getPassword());
        }
    }

    private void loadBookingHistory() {
        int customerId = AccountLogin.curUserLogin.getCustomerID();
        List<CarRental> bookings = carRentalService.findByCustomerIdOrderByPickupDateDesc(customerId);

        ObservableList<CarRental> data = FXCollections.observableArrayList(bookings);
        historyTable.setItems(data);
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

    private boolean validateInput() {
        if (txtName.getText().isEmpty() || txtMobile.getText().isEmpty() ||
            txtIdentityCard.getText().isEmpty() || txtLicenceNumber.getText().isEmpty() ||
            txtEmail.getText().isEmpty() ||
            txtAccountName.getText().isEmpty() || txtPassword.getText().isEmpty()) {
            showErrorAlert("Error", "All fields are required.");
            return false;
        }
        
        return true;
    }

    @FXML
    void updateCustomer() {
        try {
            if (!validateInput()) {
                return;
            }
            Account needCheck = accountService.findByAccountName(txtAccountName.getText());
            if (needCheck != null && needCheck.getAccountID() != customer.getAccount().getAccountID()) {
                showErrorAlert("Error", "Account name is duplicate");
                return;
            }
            customer.setCustomerName(txtName.getText());
            customer.setBirthday(LocalDate.parse(txtBirthday.getText()));
            customer.setMobile(txtMobile.getText());
            customer.setEmail(txtEmail.getText());
            customer.setIdentityCard(txtIdentityCard.getText());
            customer.setLicenceNumber(txtLicenceNumber.getText());
            customer.setLicenceDate(LocalDate.parse(txtLicenceDate.getText()));

            customer.getAccount().setAccountName(txtAccountName.getText());
            customer.setPassword(txtPassword.getText());

            customerService.update(customer);

            showAlert("Update Successful", "Customer information updated successfully.");

            loadCustomerData();
            loadBookingHistory();
        } catch (Exception e) {
            showAlert("Error", "Failed to update customer information: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @FXML
    public void logout() throws IOException {
        Stage currentStage = (Stage) btnLogout.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/LoginGUI.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Login Page");
        stage.show();

        currentStage.close();
        AccountLogin.curUserLogin = null;
        showAlert("Logout success", "You are logged out successfully");
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
}
