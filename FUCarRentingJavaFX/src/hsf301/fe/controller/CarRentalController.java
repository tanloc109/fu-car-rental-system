package hsf301.fe.controller;

import com.fucar.pojo.Car;
import com.fucar.pojo.CarRental;
import com.fucar.pojo.Customer;
import com.fucar.service.CarRentalService;
import com.fucar.service.CarService;
import com.fucar.service.CustomerService;
import com.fucar.service.ICarRentalService;
import com.fucar.service.ICarService;
import com.fucar.service.ICustomerService;
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
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ResourceBundle;

public class CarRentalController implements Initializable {

    @FXML
    private TableView<CarRental> data;
    @FXML
    private TableColumn<CarRental, Integer> codeColumn;
    @FXML
    private TableColumn<CarRental, Car> carColumn;
    @FXML
    private TableColumn<CarRental, Customer> customerColumn;
    @FXML
    private TableColumn<CarRental, LocalDate> pickupColumn;
    @FXML
    private TableColumn<CarRental, LocalDate> returnColumn;
    @FXML
    private TableColumn<CarRental, Double> priceColumn;
    @FXML
    private TableColumn<CarRental, String> statusColumn;

    @FXML
    private ComboBox<Car> comboCar;
    @FXML
    private ComboBox<Customer> comboCustomer;
    @FXML
    private DatePicker txtPickup;
    @FXML
    private DatePicker txtReturn;
    @FXML
    private TextField txtRentPrice;
    @FXML
    private TextField txtStatus;
    @FXML
    private Button btnBack;
    private ICarRentalService carRentalService;
    private ICarService carService;
    private ICustomerService customerService;

    public CarRentalController() {
        this.carRentalService = new CarRentalService();
        this.carService = new CarService();
        this.customerService = new CustomerService();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("rentalID"));
        carColumn.setCellValueFactory(new PropertyValueFactory<>("car"));
        customerColumn.setCellValueFactory(new PropertyValueFactory<>("customer"));
        pickupColumn.setCellValueFactory(new PropertyValueFactory<>("pickupDate"));
        returnColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("rentPrice"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadComboBoxes();
        loadTableData();

        // Add listener to TableView selection
        data.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showCarRental(newValue);
            }
        });
    }

    private void loadComboBoxes() {
        List<Car> cars = carService.findAll();
        List<Customer> customers = customerService.findAll();

        ObservableList<Car> carList = FXCollections.observableArrayList(cars);
        ObservableList<Customer> customerList = FXCollections.observableArrayList(customers);

        comboCar.setItems(carList);
        comboCustomer.setItems(customerList);

        comboCar.setConverter(new StringConverter<Car>() {
            @Override
            public String toString(Car car) {
                return car != null ? car.getCarID() + " | " + car.getCarName() + " | " + car.getCarModelYear() + " | " + car.getColor() + " | " + car.getCapacity() + " seats | " + car.getRentPrice() + "$" : "";
            }

            @Override
            public Car fromString(String string) {
                return null;
            }
        });

        comboCustomer.setConverter(new StringConverter<Customer>() {
            @Override
            public String toString(Customer customer) {
                return customer != null ? customer.getCustomerID() + " | " + customer.getCustomerName() + " | " + customer.getBirthday() + " | " + customer.getMobile() : "";
            }

            @Override
            public Customer fromString(String string) {
                return null;
            }
        });
    }

    private void loadTableData() {
        List<CarRental> rentals = carRentalService.findAll();
        ObservableList<CarRental> rentalList = FXCollections.observableArrayList(rentals);
        data.setItems(rentalList);
    }

    private void showCarRental(CarRental rental) {
        comboCar.setValue(rental.getCar());
        comboCustomer.setValue(rental.getCustomer());
        txtPickup.setValue(rental.getPickupDate());
        txtReturn.setValue(rental.getReturnDate());
        txtRentPrice.setText(String.valueOf(rental.getRentPrice()));
        txtStatus.setText(rental.getStatus());
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
    private void add() {
        Car car = comboCar.getValue();
        Customer customer = comboCustomer.getValue();
        LocalDate pickupDate = txtPickup.getValue();
        LocalDate returnDate = txtReturn.getValue();
        
        if (!returnDate.isAfter(pickupDate)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Invalid Info");
            alert.setContentText("Pickup date must be before return date. Please try again.");
            alert.showAndWait();
            return;
        }
        
        if (carRentalService.checkCarIsRentedInThisDate(car.getCarID(), pickupDate, returnDate)) {
        	Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("This car was rented by another customer during the period from " + pickupDate.toString() + " to " + returnDate.toString() + ". Please choose another car.");
            alert.showAndWait();
            return;
        }
        
        double rentPrice;
        if (txtRentPrice.getText().isEmpty()) {
            long daysBetween = ChronoUnit.DAYS.between(pickupDate, returnDate);
            rentPrice = car.getRentPrice() * daysBetween;
        } else {
            rentPrice = Double.parseDouble(txtRentPrice.getText());
        }
        
        String status = txtStatus.getText().isEmpty() ? "Completed" : txtStatus.getText();
        
        CarRental rental = new CarRental();
        rental.setCar(car);
        rental.setCustomer(customer);
        rental.setPickupDate(pickupDate);
        rental.setReturnDate(returnDate);
        rental.setRentPrice(rentPrice);
        rental.setStatus(status);
        
        carRentalService.save(rental);
        loadTableData();
        showAlert("Add Successful", "Create rental successfully.");
    }


    @FXML
    private void update() {
        CarRental selectedRental = data.getSelectionModel().getSelectedItem();
        if (txtPickup.getValue().isAfter(txtReturn.getValue())) {
       	 Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setHeaderText("Invalid Info");
	        alert.setContentText("Pickup date must be before return date. Please try again.");
	        alert.showAndWait();
	        return;
       }
        if (selectedRental != null) {
            selectedRental.setCar(comboCar.getValue());
            selectedRental.setCustomer(comboCustomer.getValue());
            selectedRental.setPickupDate(txtPickup.getValue());
            selectedRental.setReturnDate(txtReturn.getValue());
            selectedRental.setRentPrice(Double.parseDouble(txtRentPrice.getText()));
            selectedRental.setStatus(txtStatus.getText());

            carRentalService.update(selectedRental);
            loadTableData();
        }
        showAlert("Update Successful", "Rental information updated successfully.");
    }

    @FXML
    private void delete() {
        CarRental selectedRental = data.getSelectionModel().getSelectedItem();
        if (selectedRental != null) {
            carRentalService.delete(selectedRental.getRentalID());
            loadTableData();
        }
        showAlert("Delete Successful", "Rental information deleted successfully.");
    }
    
    private void showAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
