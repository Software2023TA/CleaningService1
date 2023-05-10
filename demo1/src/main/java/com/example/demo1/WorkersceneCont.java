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
    String reportFile = "C:\\Users\\Msys\\Desktop\\Cleaning\\demo1\\Reports.txt";
    String orderFile = "C:\\Users\\Msys\\Desktop\\Cleaning\\demo1\\Order.txt";
    String availableWFile = "C:\\Users\\Msys\\Desktop\\Cleaning\\demo1\\AvailableW.txt";
    String thankYou = "Thank you for using our service. We hope to see you again soon!";
    String saveOrder = "Order saved to file: " + orderFile;
    String saveAva = "Order saved to file: " + availableWFile;
    private boolean sent = false;
    @FXML
    private TextArea textArea;

    @FXML
    private TextField textID;

    private String status;
    private String availableWorker;
    private String  msgText;
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
    private static final Logger LOGGER = Logger.getLogger(WorkersceneCont.class.getName());
    public void orders() throws IOException {
        String id = textID.getText();
        BufferedWriter writer = new BufferedWriter(new FileWriter(orderFile, true));
        Scene1Controller s = new Scene1Controller();
        writer.write(s.getScene2Username() + "\t" + id + "\t" + status + "\n");        writer.close();
        LOGGER.info(saveOrder);
    }

    public void availableW() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(availableWFile, true));
        Scene1Controller s = new Scene1Controller();
        writer.write(s.getScene2Username() + "\t" + availableWorker + "\n");
        writer.close();
        LOGGER.info(saveAva);
    }

    public String getWorkerStatus(String workerName) throws IOException {
        File file = new File(availableWFile);
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
    public void waiting(ActionEvent event) throws IOException, MyException {
        status = "Added";
        availableWorker = "Unavailable";
        msgText = "Your order has been added to the system and is waiting for a worker to accept it.\n Your order ID is: " + textID.getText() + "\n" +  thankYou +"\n";
        String number = getTextID();
        getName(String.valueOf(number));
        orders();
        availableW();
    }
    @FXML
    public void inTreatment(ActionEvent event) throws IOException, MyException {
        status = "InTreatment";
        msgText = "Your order has been accepted by a worker and is being treated.\n Your order ID is: " + textID.getText() + "\n" +  thankYou +"\n";
        getName(textID.getText());
        orders();
    }
    @FXML
    public void complete(ActionEvent event) throws IOException, MyException {
        status = "Complete";
        availableWorker = "Available";
        msgText = "Your order has been completed.\n Your order ID is: " + textID.getText() + "\n" +  thankYou +"\n";
        getName(textID.getText());
        orders();
        availableW();
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
    private String messageBody;
    public String getName(String id) throws MyException {
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
                    emailCall(email, subject, messageBody);

                   sent = true;
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return name;
    }

    private void emailCall(String email, String subject,String messageBody) throws MyException {
        try {
            new EmailSender(email, subject, messageBody);
        }
        catch (Exception e) {
            throw new MyException((IOException) e);
        }
    }

    public boolean getsent() {
        return sent;
    }

    public String getMsg() {
        return messageBody;
    }


}
