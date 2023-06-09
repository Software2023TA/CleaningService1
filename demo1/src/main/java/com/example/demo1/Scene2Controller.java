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
import java.util.logging.Logger;


public class Scene2Controller {

    private static final Alert ALERT = new Alert(Alert.AlertType.INFORMATION);

    public Alert getALERT() {
        return ALERT;
    }
    @FXML
    public Button nextButton;
    @FXML
    public Tab shippingTab;
    @FXML
    public Tab mainTab;
    @FXML
    public Button logoutButton;
    @FXML
    Parent root1;
    @FXML
    public TextField sizetxt;
    @FXML
    public TextField itemtxt;
    @FXML
    public TextField delText;
    @FXML
    public TextField shippingType;

    String customerId ="null";
    @FXML
    private String orderAccepted = "Order Accepted";

    private String msgText ="null";
    @FXML
    private Label location1;
    @FXML
    private TabPane tabPane;


    String itemname ;
    String itemsize;
    String cleaningtype;
    int price;

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

    public Parent getRoot1() {
        return root1;
    }


    boolean isCarpetSelected = false;

    public void carpet() {
        itemname = "carpet";
        if (itemtxt != null) {
            itemtxt.setText("Carpet");
        }
    }
    private static boolean iscover =false;

    public static void setIscover(boolean iscover) {
        Scene2Controller.iscover = iscover;
    }
    public boolean getIscover(){
        return iscover;
    }

    public void cover() {
        setIscover(true);
        itemname = "cover";
        if (itemtxt != null) {
            itemtxt.setText("Cover");
        }
    }

    public void sizeHandle1() throws IOException {
        if (sizetxt != null) {
            sizetxt.setText("200x100");
            itemsize = "200x100";
        }
        int i = 280;
        Scene2Controller.setprice(i);
    }
    static int prc;
    private static void setprice(int i) {
        prc = i;
    }

    public void sizeHandle2() {
        if (sizetxt != null) {
            sizetxt.setText("200x200");
            itemsize = "200x200";
        }
        int i = 300;
        Scene2Controller.setprice(i);
    }

    public void sizeHandle3() {
        if (sizetxt != null) {
            sizetxt.setText("300x200");
            itemsize = "300x200";
        }
        int i = 350;
        Scene2Controller.setprice(i);
    }
        int res;
        String con1="You got 10% discount\nAnd the final Price is: ";
        String con2="The Price: ";
    public void speedBH() {

        Platform.runLater(() -> {
            ALERT.setTitle("Speed Cleaning");
            ALERT.setHeaderText(null);
            res = getprice() + 80;
            int dis = (int) (res * 0.1);
            if (res > 400) {
                res -= dis;
                if (getIscover()) {
                    res = res - 40;
                    ALERT.setContentText(con1 + res);
                    setIscover(false);
                } else {
                    ALERT.setContentText(con1 + res);
                }
            } else {
                if (getIscover()) {
                    res = res - 40;
                    ALERT.setContentText(con2 + res);
                } else {
                    ALERT.setContentText(con2 + res);
                }
            }
            ALERT.showAndWait();
        });
        cleaningtype = "Speed Cleaning";
    }

    public int getprice() {
        return prc;
    }

    public void dryBH() {
        Platform.runLater(() -> {
            Alert alr = new Alert(Alert.AlertType.INFORMATION);
            alr.setTitle("Dry Cleaning");
            alr.setHeaderText(null);
            res = getprice() + 120;
            int dis = (int) (res * 0.1);
            if (res > 400){
                res -= dis;
                if (getIscover()){
                    res = res - 40;
                    alr.setContentText(con1 + res);
                    setIscover(false);
                }
                else {
                    alr.setContentText(con1 + res);
                }
            }else{
                if (getIscover()) {
                    res = res - 40;
                    alr.setContentText(con2 + res);
                }else{
                    alr.setContentText(con2 + res);

                }
            }
            alr.showAndWait();
        });
        cleaningtype = "Dry Cleaning";
    }

    public void deepBH() {
        Platform.runLater(() -> {
            Alert alr = new Alert(Alert.AlertType.INFORMATION);
            alr.setTitle("Deep Cleaning");
            alr.setHeaderText(null);
            res = getprice() + 110;
            int dis = (int) (res * 0.1);
            if (res > 400){
                res -= dis;
                if (getIscover()){
                    res = res - 40;
                    alr.setContentText(con1 + res);
                    setIscover(false);
                }
                else {
                    alr.setContentText(con1 + res);
                }
            }else{
                if (getIscover()) {
                    res = res - 40;
                    alr.setContentText(con2 + res);
                }else{
                    alr.setContentText(con2 + res);

                }
            }
            alr.showAndWait();

        });
        cleaningtype = "Deep Cleaning";
    }

    public void locatioHandle() throws IOException{
        if (delText != null) {
            delText.setVisible(true);
        }
        if (location1 != null) {
            location1.setVisible(true);
        }
    }



    public void pickupBH() throws  IOException {
        if (shippingType != null) {
            shippingType.setText("Pickup");
        }
        if (delText != null) {
            delText.setVisible(false);
        }
        if (location1 != null) {
            location1.setVisible(false);
        }
        readymessage();
    }
    private static boolean isclicked =false;
    public static void setIsclicked(boolean isclicked) {
        Scene2Controller.isclicked = isclicked;
    }
    public boolean getIsclicked(){
        return isclicked;
    }
    public void deliveryBH() throws IOException {
        setIsclicked(true);
        if (shippingType != null) {
            shippingType.setText("Delivery");
        }
        locatioHandle();
    }


    public void readymessage() {

        Platform.runLater(() -> {
            Alert alr = new Alert(Alert.AlertType.INFORMATION);
            alr.setTitle("Notification...");
            alr.setHeaderText(null);
            alr.setContentText("You will Receive A Message When It's Ready");
            alr.showAndWait();

        });

    }

    private int counter;
    Scene1Controller s1 = new Scene1Controller();
    public void saveToTextFile() {
        String filename = "Reports.txt";
        if (getIsclicked()) {
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
                bwr(filename);

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
                bwrr(filename);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void bwr(String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
        customerId = String.format("%03d", counter);
        writer.write(customerId + "\t" + s1.getScene2Username() + "\t" + delText.getText() + "\t" + itemname + "\t" + itemsize + "\t" + cleaningtype + "\t" + price + "\n");
        counter++;
        msgText = "Your order with IDnumber " + customerId + " has been accepted and will be processed shortly, it will be sent to this location when it's done " + delText.getText() + " Thank you for choosing us";

        writer.close();
        String orderSave = "Order saved to file: " + filename;
        LOGGER.info(orderSave);
    }
    private static final Logger LOGGER = Logger.getLogger(Scene2Controller.class.getName());
    private void bwrr(String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
        customerId = String.format("%03d", counter);
        writer.write(customerId + "\t" + s1.getScene2Username() + "\t" + "" +  "\t" + itemname + "\t" + itemsize + "\t" + cleaningtype + "\t" + res + "\n");
        counter++;
        msgText = "Your order with IDnumber " + customerId + " has been accepted and will be processed shortly, We will send you an email when it's ready to pickup, Thank you for choosing us";
        writer.close();
        String fileName = "Order saved to file: " + filename;
        LOGGER.info(fileName);
    }

    public void onProceedclick() throws MyException {
        saveToTextFile();
       String email12 = s1.getEmailAddress(s1.getScene2Username());
        String subject = orderAccepted;
        String messageBody = msgText;
        try {
            new EmailSender(email12, subject, messageBody);
        }catch (Exception e){
            throw new MyException("Email not sent");
        }


    }
}
