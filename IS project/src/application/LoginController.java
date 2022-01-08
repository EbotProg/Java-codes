package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class LoginController {

	
	@FXML
	private Button cancelButton;
	
	@FXML
	private Label loginMessageLabel;
	
	@FXML
	private Button loginButton;
	
	@FXML
	private TextField usernameTextField;
	
	@FXML
	private PasswordField passwordPasswordField;


	
	public void loginButtonOnAction(ActionEvent e) {
		
		
		if(usernameTextField.getText().isBlank() == false && passwordPasswordField.getText().isBlank() == false) {
			//loginMessageLabel.setText("You tried to login");
			
			validateLogin();
		
			
		}else {
			loginMessageLabel.setText("Please enter username and password");
		}
	}
	
	public void cancelButtonOnAction(ActionEvent e) {
		Stage primaryStage = (Stage) cancelButton.getScene().getWindow();
		primaryStage.close();
	}
	
	private void loginClose() {
	// TODO Auto-generated method stub
		try {
			Stage primaryStage = (Stage) loginButton.getScene().getWindow();
			primaryStage.close();
			
			
		} catch(Exception e) {
			e.printStackTrace();
			e.getCause();
		  }
		
}
	
//	public void switchToWelcomePage() throws IOException{
//		Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
//		Stage primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
//		Scene scene = new Scene(root);
//		primaryStage.setScene(scene); 
//		primaryStage.show();
//		
//	}
	public void validateLogin() {
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		String verifyLogin = "SELECT count(1) FROM useraccounts WHERE username = '"+ usernameTextField.getText() +"' AND passwords = '"+ passwordPasswordField.getText() +"';";
	
	try {
		Statement statement = connectDB.createStatement();
		ResultSet queryResult = statement.executeQuery(verifyLogin);
				
		while(queryResult.next()) {
		  if(queryResult.getInt(1) == 1) {
			  loginClose();
			  createAddToDatabasePage();
			  
			  loginMessageLabel.setText("Welcome!!");
			  
		  }else {
			  loginMessageLabel.setText("Invalid login. Please try again");
		  }
		}
				
	}catch (Exception e) {
	     e.printStackTrace();       
	    }
	}
	
	private void createAddToDatabasePage() {
	// TODO Auto-generated method stub
		try {
			
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("registration.fxml"));
			Scene scene = new Scene(root,560,407);
			Stage primaryStage = new Stage();
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
			e.getCause();
		  }
		
}

//	public void createAddToDatabasePage(Stage primaryStage) {
//	
//	try {
//		BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("registration.fxml"));
//		Scene scene = new Scene(root,560,407);
//		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//		primaryStage.setScene(scene);
//		primaryStage.show();
//		
//	} catch(Exception e) {
//		e.printStackTrace();
//		e.getCause();
//	  }
//	}
	}

