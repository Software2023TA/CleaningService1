package com.example.demo1;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Logger;

public class WorkersceneCont {
    Scene1Controller s1 = new Scene1Controller();
    String reportFile = "C:\\Users\\Msys\\Desktop\\Cleaning\\Reports.txt";



    private boolean sent = false;
    @FXML
    private TextArea textArea;

    @FXML
    private TextField textID;

    private String status;
    private String availableWorker;
    private static String  msgText;
    @FXML
    public void reloading(ActionEvent event) throws FileNotFoundException {


        File file = new File(reportFile);
        Scanner scanner = new Scanner(file);
        StringBuilder fileContentBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            fileContentBuilder.append(scanner.nextLine()).append("\n");
        }
        String fileContent = fileContentBuilder.toString();
        scanner.close();

        textArea.setText(fileContent);

    }

    public String getTextID() {
        return textID.getText();
    }
    private static final Logger LOGGER = Logger.getLogger(Scene2Controller.class.getName());
    public void orders() throws IOException {
        String filename = "Order.txt";
        String id = textID.getText();
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
        Scene1Controller s = new Scene1Controller();
        writer.write(s.getUsername() + "\t" + id + "\t" + status + "\n");        writer.close();
        LOGGER.info("Order saved to file: " + filename);
    }

    public void availableW() throws IOException {
        String filename = "AvailableW.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
        Scene1Controller s = new Scene1Controller();
        writer.write(s.getUsername() + "\t" + availableWorker + "\n");
        writer.close();
        LOGGER.info("Order saved to file: " + filename);
    }

    public String getWorkerStatus(String workerName) throws IOException {
        File file = new File("AvailableW.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split("\t");
            if (parts.length >= 2 && parts[0].equals(workerName)) {
                return parts[1];
            }
        }
        scanner.close();
        return null;
    }
    @FXML
    public void waiting(ActionEvent event) throws IOException {
        status = "Added";
        availableWorker = "Unavailable";
        msgText = "Your order has been added to the system and is waiting for a worker to accept it.\n Your order ID is: " + textID.getText() + "\n Thank you for using our service.\n";
        String number = getTextID();
        getName(String.valueOf(number));
        orders();
        availableW();
    }
    @FXML
    public void inTreatment(ActionEvent event) throws IOException {
        status = "InTreatment";
        msgText = "Your order has been accepted by a worker and is being treated.\n Your order ID is: " + textID.getText() + "\n Thank you for using our service.\n";
        getName(textID.getText());
        orders();
    }
    @FXML
    public void complete(ActionEvent event) throws IOException {
        status = "Complete";
        availableWorker = "Available";
        msgText = "Your order has been completed.\n Your order ID is: " + textID.getText() + "\n Thank you for using our service.\n";
        getName(textID.getText());
        orders();
        availableW();
    }

    public void switchScene1(ActionEvent event) throws IOException {
        Platform.runLater(() -> {
            try {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Scene1.fxml")));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                //stage.setScene(new Scene(root));
                stage.setScene(Main.scene1);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    private String messageBody;
    public String getName(String id) {
        String name = "";
        sent = false;
        try (Scanner scanner = new Scanner(new File(reportFile))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split("\t");
                if (fields.length >= 3 && fields[0].equals(id)) {
                    name = fields[1];
                    String email = s1.getEmailAddress(name);
                    String subject = "OrderUpdate";
                    messageBody = msgText;
                    new EmailSender(email, subject, messageBody);
                   sent = true;
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return name;
    }

    public boolean getsent() {
        return sent;
    }

    public String getMsg() {
        return messageBody;
    }


}
