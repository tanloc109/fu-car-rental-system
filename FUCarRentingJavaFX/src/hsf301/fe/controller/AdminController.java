package hsf301.fe.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.fucar.login.AccountLogin;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminController implements Initializable {
	
	@FXML
	private Button btnCustomer;
	
	@FXML
	private Button btnCar;
	
	@FXML
	private Button btnRental;
	
	@FXML
	private Button btnReport;
	
	@FXML
	private Button btnLogout;
	
	
	public AdminController() {
	}
	
	@FXML
	public void toCustomer() throws IOException {
	    Stage currentStage = (Stage) btnCustomer.getScene().getWindow();

	    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/CustomerManagement.fxml"));
	    Parent root = fxmlLoader.load();
	    Stage stage = new Stage();
	    stage.setScene(new Scene(root));
	    stage.setTitle("Customer Management");
	    stage.show();
	    
	    currentStage.close();
	}

	@FXML
	public void toCar() throws IOException {
		Stage currentStage = (Stage) btnCar.getScene().getWindow();

	    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/CarManagement.fxml"));
	    Parent root = fxmlLoader.load();
	    Stage stage = new Stage();
	    stage.setScene(new Scene(root));
	    stage.setTitle("Car Management");
	    stage.show();
	    
	    currentStage.close();
	}

	@FXML
	public void toRental() throws IOException {
		Stage currentStage = (Stage) btnCustomer.getScene().getWindow();

	    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/CarRentalManagement.fxml"));
	    Parent root = fxmlLoader.load();
	    Stage stage = new Stage();
	    stage.setScene(new Scene(root));
	    stage.setTitle("Car Rental Management");
	    stage.show();
	    
	    currentStage.close();
	    
	}

	@FXML
	public void toReport() throws IOException {
	    Stage currentStage = (Stage) btnReport.getScene().getWindow();

	    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/RentingReport.fxml"));
	    Parent root = fxmlLoader.load();
	    Stage stage = new Stage();
	    stage.setScene(new Scene(root));
	    stage.setTitle("Renting Dashboard Report");
	    stage.show();
	    
	    currentStage.close();
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
	    showAlert("Logout success", "You are loged out successfully");
	}
	
	@FXML
	public void cancel() {
		Platform.exit();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
    private void showAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
