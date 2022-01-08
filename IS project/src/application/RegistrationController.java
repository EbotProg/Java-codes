package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import  java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class RegistrationController {
	
	@FXML
	private Button closeButton;
	
	@FXML
	private TextField carOwnerFirstNameTextField;
	
	@FXML
	private TextField carOwnerLastNameTextField;
	
//	@FXML
//	private TextField carIDTextField;
	
	public void closeButtonOnAction(ActionEvent e) {
		Stage primaryStage = (Stage) closeButton.getScene().getWindow();
		primaryStage.close();
	}
	
	public void addToDatabaseButtonOnAction(ActionEvent e) {
		
		if(carOwnerFirstNameTextField.getText().isBlank() == false && carOwnerLastNameTextField.getText().isBlank() == false) {
			addToDatabase();
		}
		
	}

	public void addToDatabase() {
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		String carOwnerfirstName = carOwnerFirstNameTextField.getText();
		String carOwnerLastName = carOwnerLastNameTextField.getText();
//		String carID = carIDTextField;
		
		String insertFields = "INSERT INTO car_owner(owner_FirstName, owner_LastName) VALUES ('";
		String insertValues = carOwnerfirstName + "','" + carOwnerLastName + "')";
		String insertToRegister = insertFields + insertValues;
		
	
	try {
		Statement statement = connectDB.createStatement();
		statement.executeUpdate(insertToRegister);
				
//		while(queryResult.next()) {
//		  if(queryResult.getInt(1) == 1) {
//			
//			 
//			  
//			  
//		  }else {
//			 
//		  }
//		}
	
}catch (Exception e)  {
	e.printStackTrace();
	e.getCause();
      }
	}
}
