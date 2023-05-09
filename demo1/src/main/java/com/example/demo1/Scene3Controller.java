package com.example.demo1;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Scene3Controller {

    String reportFile = "C:\\Users\\Msys\\Desktop\\Cleaning\\Reports.txt";
    @FXML
    private TextField cashfield;
    @FXML
    private TextField salesfield;
    @FXML
    private Tab workertab;
    @FXML
    private TextArea textarea1;
    private int totalCash = 0;
    private int numOrders = 0;
    public boolean co = false;
    public boolean ch = false;

    @FXML
    private TextField firstname;

    @FXML
     private TextField lastname;

    @FXML
     private TextField userfield;

    @FXML
     private TextField emailfield;

    @FXML
     private TextField phonefield;

    @FXML
     private DatePicker datepicker;
    @FXML
    private PasswordField passfield;
    @FXML
    public Button neworker;
    @FXML
    public boolean validWorkerInput;


  public void switchScene1(ActionEvent event) throws IOException ,  MyException{
   Platform.runLater(() -> {
    try {
     Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Scene1.fxml")));
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     stage.setScene(Main.scene1);

    } catch (IOException e) {
     e.printStackTrace();
    }
   });
      clearAllFields();
  }

    public void calculateCash()  {
        CH = true;
        cashfield.setEditable(false);
        try (BufferedReader reader = new BufferedReader(new FileReader(reportFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("\t");
                totalCash += Integer.parseInt(tokens[tokens.length - 1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        cashfield.setText(String.valueOf(totalCash));
    }
    public void countOrders()  {
        CO = true;
        salesfield.setEditable(false);
        try (BufferedReader reader = new BufferedReader(new FileReader(reportFile))) {
            String readStored = reader.readLine();
            while (readStored != null) {
                numOrders++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        salesfield.setText(String.valueOf(numOrders));
    }

    public void saveUserData()  {
        String fname = "";
        String lname = "";
        String username = "";
        String email = "";
        String phone = "";
        String birthday = "";
        String password = "";

        try {
            fname = firstname.getText();
            lname = lastname.getText();
            username = userfield.getText();
            email = emailfield.getText();
            phone = phonefield.getText();
            password = passfield.getText();
            if (datepicker.getValue() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                birthday = datepicker.getValue().format(formatter);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        boolean notEmptyFields = !fname.isEmpty() && !lname.isEmpty() && !username.isEmpty() && !email.isEmpty() && !phone.isEmpty() && !password.isEmpty();
        boolean isValidUsername = username.matches("^Worker([1-9]|[1-9]\\d|100)$");
        boolean isValidEmail = email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        boolean isValidPhone = phone.matches("^\\d{10}$");
        boolean isValidName = fname.matches("^[A-Za-z]*$") && lname.matches("^[A-Za-z]*$");

        if (notEmptyFields && isValidUsername && isValidEmail && isValidPhone && isValidName) {
            try (FileWriter writer = new FileWriter("Workers.txt", true)) {
                writer.write(fname + "," + lname + "," + username + "," + email + "," + phone + "," + birthday + "\n");
                validWorkerInput = true;
            } catch (IOException e) {
                e.printStackTrace();
            }

            try (FileWriter writer = new FileWriter("Untitled.txt", true)) {
                writer.write(username + "," + password + "\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            JOptionPane.showMessageDialog(null, "User added successfully.");
            firstname.setText("");
            lastname.setText("");
            userfield.setText("");
            emailfield.setText("");
            phonefield.setText("");
            passfield.setText("");
            datepicker.setValue(null);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid input detected. Please check that all fields are not empty and are valid.");
        }
    }






    public void reloading() throws FileNotFoundException {
        File file = new File(reportFile);
        Scanner scanner = new Scanner(file);
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine()).append("\n");
        }
        scanner.close();
        String fileContent = sb.toString();
        textarea1.setText(fileContent);

    }


    public void sendReports() throws MyException {
        new EmailSender();
    }
    public void clearAllFields() {
        cashfield.setText("");
        salesfield.setText("");
        textarea1.setText("");
        firstname.setText("");
        lastname.setText("");
        userfield.setText("");
        emailfield.setText("");
        phonefield.setText("");
        datepicker.setValue(null);
        passfield.setText("");
    }

    public String getsalesField() {
        return salesfield.getText();
    }

    public String getcashField() {
      return cashfield.getText();
    }

    public String getfirstname() {
      return firstname.getText();
    }

    public String getlastname() {
        return lastname.getText();
    }
    public String getusername() {
      return userfield.getText();
    }

    public String getpassword() {
      return passfield.getText();
    }

    public String getemail() {
      return emailfield.getText();
    }

    public String getnumber() {
      return phonefield.getText();
    }

    public boolean getvalidWorkerInput() {
        return validWorkerInput;
    }
}




