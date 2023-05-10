package com.example.demo1;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scene4Controller {
    @FXML
    private PasswordField passwordff;
    @FXML
    private TextField usernamefield;
    @FXML
    private TextField emailfield;
    @FXML
    private TextField phonefield;
    @FXML
    public  boolean validInputUser;
    @FXML
    public  Button  signup;

    public  boolean getvalidInputUser() {
        return validInputUser;
    }


    @FXML
public void saveData(ActionEvent event) throws IOException {
    String username = getUsername();
    String password = getPassword();
    String email = getEmail();
    String phone = getPhone();

    boolean validUsername = username.matches("^(?!Worker\\d*$|Admin\\d*$)[A-Za-z]\\w*$");

        Pattern pattern = Pattern.compile("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher matcher = pattern.matcher(email);
    boolean validEmail = matcher.matches();

    boolean validPhone = phone.matches("^\\d{10}$");

    StringBuilder errorMessages = new StringBuilder();
    validInputUser = true;

    if (!validUsername) {
        errorMessages.append("Invalid username\n");
        validInputUser = false;
    }

    if (!validEmail) {
        errorMessages.append("Invalid email\n");
        validInputUser = false;
    }

    if (!validPhone) {
        errorMessages.append("Invalid phone number\n");
        validInputUser = false;
    }

    if (validInputUser) {
        try (FileWriter writer = new FileWriter("Customers.txt", true)) {
            writer.write(username + "," + password +  "," + email + "," + phone + "\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try (FileWriter writer = new FileWriter("Untitled.txt", true)) {
            writer.write(username + "," + password + "\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        switchScene1(event);
    } else {
        JOptionPane.showMessageDialog(null, errorMessages.toString());
    }
}





    public void switchScene1(ActionEvent event) throws IOException {
        Platform.runLater(() -> {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Scene1.fxml")));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(Main.scene1);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    public String getUsername() throws IOException{
        if (usernamefield != null) {
            return usernamefield.getText() ;
        } else {
            return null;
        }
    }
    public String getPassword(){
        if (passwordff != null) {
            return passwordff.getText();
        } else {
            return null;
        }
    }
    public String getEmail(){
        if(emailfield != null){
            return emailfield.getText();
        }else
            return null;

    }
    public String getPhone(){
      if(phonefield != null){
          return phonefield.getText();
        }else
            return null;
    }

    public void setEmail(String invalidEmail) {
        emailfield.setText(invalidEmail);
    }

}
