package com.example.demo1;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.*;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Logger;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;


public class Scene1Controller {

    boolean validCredential = false;
    @FXML
    Parent root;
    @FXML
    private PasswordField fieldPass=null;
    @FXML
    private static TextField fieldUser;
    @FXML
    public Parent getRoot() {
        return root;
    }
    @FXML
    public void setFieldPass(String passwordField) {
        this.fieldPass.setText(passwordField);
    }
    @FXML
    public PasswordField getFieldPass() {
        return fieldPass;
    }
    @FXML
    public void setFieldUser(String username) {
        if (fieldUser != null) {
            fieldUser.setText(username);
        }
    }
    @FXML
    public String getFieldUser() {
        return fieldUser.getText();
    }
    @FXML
    public Button newCustomerButton;
    @FXML
    public boolean isScene4Open = false;

    @FXML
    private static boolean isApplicationClosed = false;
    @FXML
    static String email = null;

    @FXML
    public  String username   = null;

    @FXML
    public void login(ActionEvent event) throws IOException {
        if (fieldUser != null && fieldPass != null) {
            username = fieldUser.getText();
            String password = fieldPass.getText();
            boolean validCredentials = CheckCredentials(username, password, "Untitled.txt");
            if (validCredentials) {
                setValidCredential(true);
                if (username.startsWith("Worker")) {
                    switchSceneWorker();
                } else if (username.equals("Admin")) {
                    switchScene3(event);
                } else {
                    switchScene2(event);
                }
            } else {
                setValidCredential(false);
                ShowErrorMessage();
            }
        } else {
            setValidCredential(false);
        }
    }


    public void switchSceneWorker() {
        Main.primaryStage.setScene(Main.scene5);
    }

    public void setValidCredential(boolean b) {
        validCredential = b;
    }
    public boolean getValidCredentials() {
        return validCredential;
    }

    @FXML
    public void switchScene2(ActionEvent event) throws IOException {
            Main.primaryStage.setScene(Main.scene2);
    }
    @FXML
    public void switchScene3(ActionEvent event) throws IOException {
        Main.primaryStage.setScene(Main.scene3);
    }
    @FXML
    public void switchScene4(ActionEvent event) throws IOException {
        Platform.runLater(() -> {
            Main.primaryStage.setScene(Main.scene4);
            isScene4Open = true;
        });
    }

    @FXML
    public boolean CheckCredentials(String username, String password, String filePath) throws IOException {
        String classFilePath = new File(filePath).getAbsolutePath();
        BufferedReader reader = new BufferedReader(new FileReader(classFilePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 2 && parts[0].equals(username) && parts[1].equals(password)) {
                reader.close();
                return true;
            }
        }
        reader.close();
        return false;
    }
    @FXML
    public void ShowErrorMessage() {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("The username and/or password you entered is incorrect.");
            alert.showAndWait();
        });
    }


    @FXML
    public void exit(ActionEvent event) throws IOException {
        closeApplicationIfConfirmed(Main.getCurrentScene().getWindow());
    }

    @FXML
    public static void closeApplicationIfConfirmed(Window window) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Exit");
            alert.setHeaderText("Are you sure you want to exit?");
            alert.setContentText("Click OK to exit, or Cancel to continue.");
            alert.initOwner(window);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                closeWindow(window);
                setApplicationClosed(true);
            }
        });
    }

    @FXML
    private static void closeWindow(Window window) {
        Stage stage = (Stage) window;
        stage.close();
    }
    @FXML
    public Node getRootNode() {
        return root;
    }

    public static boolean isApplicationClosed() {
        return isApplicationClosed;
    }

    public static void setApplicationClosed(boolean isClosed) {
        isApplicationClosed = isClosed;
    }

    public static Scene getScene() {
        return Main.scene1;
    }

    public static String getEmailAddress(String username) {
        try (Scanner scanner = new Scanner(new File("C:\\Users\\Msys\\Desktop\\CleaningSrv\\Customers.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",");
                if (fields.length >= 3 && fields[0].equals(username)) {
                    email = fields[2];
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return email;
    }
    private static final Logger LOGGER = Logger.getLogger(Scene1Controller.class.getName());

    public String getUsername() throws IOException{
        if (fieldUser != null) {
            username= fieldUser.getText();
            LOGGER.info(username+"123");
            return username ;

        } else {
            return null;
        }
    }
}



