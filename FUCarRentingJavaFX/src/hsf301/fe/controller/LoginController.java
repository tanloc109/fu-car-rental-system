package hsf301.fe.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.fucar.login.AccountLogin;
import com.fucar.login.LoginService;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable{
	
	@FXML
	private TextField txtUsername;
	
	@FXML
	private PasswordField txtPassword;
	
	private LoginService loginService;
	
	public LoginController() {
		loginService = new LoginService();
	}
	
	@FXML
	public void login() throws IOException {
	    if (loginService.checkLogin(txtUsername.getText(), txtPassword.getText())) {
	        Stage currentStage = (Stage) txtUsername.getScene().getWindow();

	        Parent root;
	        Stage stage = new Stage();
	        
	        if (AccountLogin.curUserLogin.getAccount().getRole().equals("Admin")) {
	            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/AdminScreen.fxml"));
	            root = fxmlLoader.load();
	            stage.setScene(new Scene(root));
	            stage.setTitle("Admin Dashboard");
	        } else if (AccountLogin.curUserLogin.getAccount().getRole().equals("Customer")) {
	            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Customer.fxml"));
	            root = fxmlLoader.load();
	            stage.setScene(new Scene(root));
	            stage.setTitle("Customer Screen");
	        } else {
	            showAlert("Login Failed", "User role is not recognized");
	            return;
	        }

	        stage.show();
	        currentStage.close();
	        
	    } else {
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setHeaderText("Login Fail");
	        alert.setContentText("You have entered the wrong username or password. Please try again.");
	        alert.showAndWait();
	    }
	}
	
	@FXML
	public void cancel() {
		Platform.exit();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
	
    private void showAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
