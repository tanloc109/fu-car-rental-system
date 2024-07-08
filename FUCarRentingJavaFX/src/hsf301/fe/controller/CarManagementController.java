package hsf301.fe.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import com.fucar.pojo.Car;
import com.fucar.pojo.CarProducer;
import com.fucar.service.CarService;
import com.fucar.service.CarProducerService;
import com.fucar.service.ICarService;
import com.fucar.service.ICarProducerService;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CarManagementController implements Initializable {

    @FXML
    private TableView<Car> data;

    @FXML
    private TableColumn<Car, Integer> idColumn;
    @FXML
    private TableColumn<Car, String> nameColumn;
    @FXML
    private TableColumn<Car, Integer> modelYearColumn;
    @FXML
    private TableColumn<Car, String> colorColumn;
    @FXML
    private TableColumn<Car, Integer> capacityColumn;
    @FXML
    private TableColumn<Car, String> descriptionColumn;
    @FXML
    private TableColumn<Car, LocalDate> importDateColumn;
    @FXML
    private TableColumn<Car, CarProducer> producerColumn;
    @FXML
    private TableColumn<Car, Double> rentPriceColumn;
    @FXML
    private TableColumn<Car, String> statusColumn;

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtModelYear;
    @FXML
    private TextField txtColor;
    @FXML
    private TextField txtCapacity;
    @FXML
    private TextArea txtDescription;
    @FXML
    private DatePicker txtImportDate;
    @FXML
    private ComboBox<CarProducer> comboProducer;
    @FXML
    private TextField txtRentPrice;
    @FXML
    private TextField txtStatus;
    @FXML
    private Button btnBack;

    private int carID;

    private ICarService carService;

    private ICarProducerService carProducerService;

    public CarManagementController() {
        this.carService = new CarService();
        this.carProducerService = new CarProducerService();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("carID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("carName"));
        modelYearColumn.setCellValueFactory(new PropertyValueFactory<>("carModelYear"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        importDateColumn.setCellValueFactory(new PropertyValueFactory<>("importDate"));
        producerColumn.setCellValueFactory(new PropertyValueFactory<>("producer"));
        rentPriceColumn.setCellValueFactory(new PropertyValueFactory<>("rentPrice"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Enable editing for description column
        descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        // Load producers into ComboBox
        List<CarProducer> producers = carProducerService.findAll();
        ObservableList<CarProducer> producerList = FXCollections.observableArrayList(producers);
        comboProducer.setItems(producerList);

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

        txtImportDate.setConverter(converter);

        data.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showCar(newValue);
            }
        });

        loadData();
    }

    private void loadData() {
        List<Car> cars = carService.findAll();
        ObservableList<Car> carList = FXCollections.observableArrayList(cars);
        data.setItems(carList);
    }

    private void showCar(Car car) {
        this.carID = car.getCarID();
        this.txtName.setText(car.getCarName());
        this.txtModelYear.setText(String.valueOf(car.getCarModelYear()));
        this.txtColor.setText(car.getColor());
        this.txtCapacity.setText(String.valueOf(car.getCapacity()));
        this.txtDescription.setText(car.getDescription());

        if (car.getImportDate() != null) {
            this.txtImportDate.setValue(car.getImportDate().toLocalDate());
        } else {
            this.txtImportDate.setValue(null);
        }

        this.comboProducer.setValue(car.getProducer());
        this.txtRentPrice.setText(String.valueOf(car.getRentPrice()));
        this.txtStatus.setText(car.getStatus());
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
    public void add() {
        if (!validateInput()) {
            return;
        }

        Car newCar = new Car();
        newCar.setCarName(txtName.getText());
        newCar.setCarModelYear(Integer.parseInt(txtModelYear.getText()));
        newCar.setColor(txtColor.getText());
        newCar.setCapacity(Integer.parseInt(txtCapacity.getText()));
        newCar.setDescription(txtDescription.getText());

        if (txtImportDate.getValue() != null) {
            newCar.setImportDate(Date.valueOf(txtImportDate.getValue()));
        }

        newCar.setProducer(comboProducer.getValue());
        newCar.setRentPrice(Double.parseDouble(txtRentPrice.getText()));
        newCar.setStatus(txtStatus.getText());

        carService.save(newCar);

        refreshDataTable();
        showAlert("Success", "Car added successfully");
    }

    private void refreshDataTable() {
        this.carID = 0;
        this.txtName.setText("");
        this.txtModelYear.setText("");
        this.txtColor.setText("");
        this.txtCapacity.setText("");
        this.txtDescription.setText("");
        this.txtImportDate.setValue(null);
        this.comboProducer.setValue(null);
        this.txtRentPrice.setText("");
        this.txtStatus.setText("");

        loadData();
    }

    private boolean validateInput() {
        try {
            if (txtName.getText().isEmpty() || txtModelYear.getText().isEmpty() ||
                    txtColor.getText().isEmpty() || txtCapacity.getText().isEmpty() ||
                    txtDescription.getText().isEmpty() || txtRentPrice.getText().isEmpty() ||
                    txtStatus.getText().isEmpty() || comboProducer.getValue() == null) {
                showErrorAlert("Error", "All fields are required.");
                return false;
            }

            Integer.parseInt(txtModelYear.getText());
            Integer.parseInt(txtCapacity.getText());
            Double.parseDouble(txtRentPrice.getText());

            return true;
        } catch (NumberFormatException e) {
            showErrorAlert("Error", "Invalid input format. Model year, capacity, and rent price must be numeric.");
            return false;
        }
    }

    @FXML
    public void update() {
        Car existingCar = data.getSelectionModel().getSelectedItem();
        if (existingCar == null) {
            showErrorAlert("Error", "Please select a car to update.");
            return;
        }

        existingCar.setCarName(txtName.getText());
        existingCar.setCarModelYear(Integer.parseInt(txtModelYear.getText()));
        existingCar.setColor(txtColor.getText());
        existingCar.setCapacity(Integer.parseInt(txtCapacity.getText()));
        existingCar.setDescription(txtDescription.getText());

        if (txtImportDate.getValue() != null) {
            existingCar.setImportDate(Date.valueOf(txtImportDate.getValue()));
        } else {
            existingCar.setImportDate(null);
        }

        existingCar.setProducer(comboProducer.getValue());
        existingCar.setRentPrice(Double.parseDouble(txtRentPrice.getText()));
        existingCar.setStatus(txtStatus.getText());

        carService.update(existingCar);

        refreshDataTable();
        showAlert("Success", "Car updated successfully");
    }

    @FXML
    public void delete() {
        Car car = data.getSelectionModel().getSelectedItem();
        if (car == null) {
            showErrorAlert("Error", "Please select a car to delete.");
            return;
        }

        carService.delete(car.getCarID());

        refreshDataTable();
        showAlert("Success", "Car deleted successfully");
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
