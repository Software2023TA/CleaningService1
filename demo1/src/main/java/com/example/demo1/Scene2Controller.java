package com.example.demo1;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.*;
import java.util.Objects;

public class Scene2Controller {

    public Alert alert ;
    @FXML
    Parent root;
    @FXML
    public TextField sizetxt;
    @FXML
    public TextField itemtxt;
    @FXML
    public TextField DelText;
    @FXML
    public TextField ShippingType;
    String CustomerId="null";
    private String OrderAccepted = "Order Accepted";
    private String MsgText ="null";
    @FXML
    Label Location;
    @FXML
    private TabPane tabPane;

    @FXML
    private Tab Maintab;

    @FXML
    private Tab Shippingtab;

    @FXML
    private Button NEXTbutton;

    String itemname ;
    String itemsize;
    String cleaningtype;
    int Price;

    @FXML
    void initialize(ActionEvent event) {
        if (tabPane != null) {
            tabPane.getSelectionModel().select(1);
        }
    }

    private ActionEvent event;


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

    public Parent getRoot() {
        return root;
    }



    public void LoggedOutMsg() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Logged Out");
        alert.setHeaderText("You are now Logged Out");
        alert.showAndWait();
    }

    boolean isCarpetSelected = false;

    public void carpet() {
        itemname = "carpet";
        if (itemtxt != null) {
            itemtxt.setText("Carpet");
        } else {
        }
    }
    public boolean iscover =false;
    public void cover() {
        iscover=true;
        itemname = "cover";
        if (itemtxt != null) {
            itemtxt.setText("Cover");
        } else {
        }
    }

    public void SizeHandle1(ActionEvent event) throws IOException {
        if (sizetxt != null) {
            sizetxt.setText("200x100");
            itemsize = "200x100";
        } else {
        }
        int i = 280;
        Scene2Controller.setprice(i);
    }
    static int PRC;
    private static void setprice(int i) {
        PRC = i;
    }

    public void SizeHandle2(ActionEvent event) {
        if (sizetxt != null) {
            sizetxt.setText("200x200");
            itemsize = "200x200";
        } else {
        }
        int i = 300;
        Scene2Controller.setprice(i);
    }

    public void SizeHandle3(ActionEvent event) {
        if (sizetxt != null) {
            sizetxt.setText("300x200");
            itemsize = "300x200";
        } else {
        }
        int i = 350;
        Scene2Controller.setprice(i);
    }
        int res;
    public void SpeedBH(ActionEvent event) {

        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Speed Cleaning");
            alert.setHeaderText(null);
            res = getprice() + 80;
            int dis = (int) (res * 0.1);
            if (res > 400){
                res -= dis;
                if (iscover){
                    res = res - 40;
                    alert.setContentText("You got 10% discount\nAnd the final Price is: " + res);
                    iscover=false;
                }
                else {
                    alert.setContentText("You got 10% discount\nAnd the final Price is: " + res);
                }
            }else{
                if (iscover) {
                    res = res - 40;
                    alert.setContentText("The Price: " + res);
                }else{
                    alert.setContentText("The Price: " + res);

                }
            }
            alert.showAndWait();
            this.alert = alert;
        });
        cleaningtype = "Speed Cleaning";
    }

    public int getprice() {
        return PRC;
    }

    public void DryBH(ActionEvent event) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Dry Cleaning");
            alert.setHeaderText(null);
            res = getprice() + 120;
            int dis = (int) (res * 0.1);
            if (res > 400){
                res -= dis;
                if (iscover){
                    res = res - 40;
                    alert.setContentText("You got 10% discount\nAnd the final Price is: " + res);
                    iscover=false;
                }
                else {
                    alert.setContentText("You got 10% discount\nAnd the final Price is: " + res);
                }
            }else{
                if (iscover) {
                    res = res - 40;
                    alert.setContentText("The Price: " + res);
                }else{
                    alert.setContentText("The Price: " + res);

                }
            }
            alert.showAndWait();
        });
        cleaningtype = "Dry Cleaning";
    }

    public void DeepBH(ActionEvent event) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Deep Cleaning");
            alert.setHeaderText(null);
            res = getprice() + 110;
            int dis = (int) (res * 0.1);
            if (res > 400){
                res -= dis;
                if (iscover){
                    res = res - 40;
                    alert.setContentText("You got 10% discount\nAnd the final Price is: " + res);
                    iscover=false;
                }
                else {
                    alert.setContentText("You got 10% discount\nAnd the final Price is: " + res);
                }
            }else{
                if (iscover) {
                    res = res - 40;
                    alert.setContentText("The Price: " + res);
                }else{
                    alert.setContentText("The Price: " + res);

                }
            }
            alert.showAndWait();

        });
        cleaningtype = "Deep Cleaning";
    }

    public void LocatioHandle(ActionEvent event) throws IOException{
        if (DelText != null) {
            DelText.setVisible(true);
        }
        if (Location != null) {
            Location.setVisible(true);
        }
    }



    public void PickupBH(ActionEvent event) throws  IOException {
        if (ShippingType != null) {
            ShippingType.setText("Pickup");
        }
        if (DelText != null) {
            DelText.setVisible(false);
        }
        if (Location != null) {
            Location.setVisible(false);
        }
        Readymessage();
    }
public boolean isclicked =false;
    public void DeliveryBH(ActionEvent event) throws IOException {
        isclicked=true;
        if (ShippingType != null) {
            ShippingType.setText("Delivery");
        }
        LocatioHandle(new ActionEvent());
    }


    public void Readymessage() {

        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notification...");
            alert.setHeaderText(null);
            alert.setContentText("You will Receive A Message When It's Ready");
            alert.showAndWait();

        });

    }

    private int counter;
    Scene1Controller s1 = new Scene1Controller();
    public void saveToTextFile() {
        String filename = "Reports.txt";
        if (isclicked) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(filename));
                String lastLine = "";
                String currentLine = reader.readLine();
                while (currentLine != null) {
                    lastLine = currentLine;
                    currentLine = reader.readLine();
                }
                reader.close();

                if (lastLine.equals("")) {
                    counter = 1;
                } else {
                    String[] parts = lastLine.split("\t");
                    String lastId = parts[0];
                    counter = Integer.parseInt(lastId) + 1;
                }
                BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
                CustomerId = String.format("%03d", counter);
                writer.write(CustomerId + "\t" + Scene1Controller.username + "\t" + DelText.getText() + "\t" + itemname + "\t" + itemsize + "\t" + cleaningtype + "\t" + Price + "\n");
                counter++;
                MsgText = "Your order with IDnumber " + CustomerId + " has been accepted and will be processed shortly, it will be sent to this location when it's done " + DelText.getText() + " Thank you for choosing us";
                writer.close();
                System.out.println("Order saved to file: " + filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(filename));
                String lastLine = "";
                String currentLine = reader.readLine();
                while (currentLine != null) {
                    lastLine = currentLine;
                    currentLine = reader.readLine();
                }
                reader.close();

                if (lastLine.equals("")) {
                    counter = 1;
                } else {
                    String[] parts = lastLine.split("\t");
                    String lastId = parts[0];
                    counter = Integer.parseInt(lastId) + 1;
                }
                BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
                CustomerId = String.format("%03d", counter);
                writer.write(CustomerId + "\t" + Scene1Controller.username + "\t" + "" +  "\t" + itemname + "\t" + itemsize + "\t" + cleaningtype + "\t" + res + "\n");
                counter++;
                MsgText = "Your order with IDnumber " + CustomerId + " has been accepted and will be processed shortly, We will send you an email when it's ready to pickup, Thank you for choosing us";
                writer.close();
                System.out.println("Order saved to file: " + filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onProceedclick() throws IOException {
        saveToTextFile();
        Scene1Controller s1 = new Scene1Controller();


       String email12 = s1.getEmailAddress(Scene1Controller.username);
        String subject = OrderAccepted;
        String messageBody = MsgText;
        sendEmail x = new sendEmail(email12, subject, messageBody);

    }
}
