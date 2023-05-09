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
    public static boolean validInputUser;
    @FXML
    public  Button  signup;



    @FXML
public void saveData(ActionEvent event) throws IOException {
    String username = getUsername();
    String password = getPassword();
    String email = getEmail();
    String phone = getPhone();

    boolean validUsername = username.matches("^(?!Worker\\d*$|Admin\\d*$)[A-Za-z][A-Za-z0-9_]*$");

    boolean validEmail = email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

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
           String username1= usernamefield.getText();
            System.out.println(username1+"123");
            return username1 ;

        } else {
            return null;
        }
    }
    public String getPassword(){
        if (passwordff != null) {
            String password1 = passwordff.getText();
            return password1;
        } else {
            return null;
        }
    }
    public String getEmail(){
        if(emailfield != null){
            String email1 = emailfield.getText();
            return email1;
        }else
            return null;

    }
    public String getPhone(){
      if(phonefield != null){
          String phone1 = phonefield.getText();
          return phone1;
        }else
            return null;
    }

    public void setUsername(String worker123) {
        usernamefield.setText(worker123);
    }

    public void setPassword(String password) {
        passwordff.setText(password);
    }

    public void setEmail(String invalid_email) {
        emailfield.setText(invalid_email);
    }

    public void setPhone(String s) {
        phonefield.setText(s);
    }
}
