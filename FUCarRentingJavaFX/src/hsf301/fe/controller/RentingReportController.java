package hsf301.fe.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.fucar.pojo.CarRental;
import com.fucar.service.CarRentalService;
import com.fucar.service.ICarRentalService;

public class RentingReportController {
    
    @FXML
    private DatePicker txtStart;
    
    @FXML
    private DatePicker txtEnd;
    
    @FXML
    private TableView<CarRental> data;
    
    @FXML
    private TableColumn<CarRental, String> codeColumn;
    
    @FXML
    private TableColumn<CarRental, String> carColumn;
    
    @FXML
    private TableColumn<CarRental, String> customerColumn;
    
    @FXML
    private TableColumn<CarRental, LocalDate> pickupColumn;
    
    @FXML
    private TableColumn<CarRental, LocalDate> returnColumn;
    
    @FXML
    private TableColumn<CarRental, Double> priceColumn;
    
    @FXML
    private TableColumn<CarRental, String> statusColumn;
    
    @FXML
    private Button btnBack;

    private ICarRentalService carRentalService;

    public RentingReportController() {
    	carRentalService = new CarRentalService();
	}

	@FXML
    public void initialize() {
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("rentalID"));
        carColumn.setCellValueFactory(new PropertyValueFactory<>("car"));
        customerColumn.setCellValueFactory(new PropertyValueFactory<>("customer"));
        pickupColumn.setCellValueFactory(new PropertyValueFactory<>("pickupDate"));
        returnColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("rentPrice"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
    }
    
	@FXML
	private void add() {
	    LocalDate startDate = txtStart.getValue();
	    LocalDate endDate = txtEnd.getValue();
	    
	    if (startDate == null || endDate == null) {
	        showErrorAlert("Error", "Please select both start date and end date.");
	        return;
	    }
	    if (startDate.isAfter(endDate)) {
	        showErrorAlert("Error", "Start date must be before end date.");
	        return;
	    }
	    
	    List<CarRental> rentals = carRentalService.findByPeriodDate(startDate, endDate);
	    
	    if (!rentals.isEmpty()) {
	        ObservableList<CarRental> reportList = FXCollections.observableArrayList(rentals);
	        data.setItems(reportList);
	    } else {
	        showErrorAlert("Error", "No rentals found for the selected period.");
	    }
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
    
    private void showErrorAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    public void setCarRentalService(ICarRentalService carRentalService) {
        this.carRentalService = carRentalService;
    }
}
