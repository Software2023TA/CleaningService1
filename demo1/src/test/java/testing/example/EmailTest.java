package testing.example;

import com.example.demo1.WorkersceneCont;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javafx.application.Platform;
import javafx.scene.control.TextField;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EmailTest {

    @When("i click on waiting button")
    public void i_click_on_waiting_button() {
        Platform.runLater(() -> {
        WorkersceneCont obj = new WorkersceneCont();
        String id = obj.getTextID();
        String ActMsg =obj.getMsg();
        String ExpMsg = "Your order has been added to the system and is waiting for a worker to accept it.\n Your order ID is: " + id + "\n Thank you for using our service.\n";
        assertEquals(ExpMsg.startsWith("Your order has been added"),ActMsg.startsWith("Your order has been added"));
        });
    }

    @Then("send email")
    public void send_email() {
        WorkersceneCont obj = new WorkersceneCont();
        assertEquals(true,obj.getsent());
    }

    @When("i click on in treatment button")
    public void i_click_on_in_treatment_button() {
        Platform.runLater(() -> {
            WorkersceneCont obj = new WorkersceneCont();
            String ActMsg = obj.getMsg();
            String ExpMsg = "Your order has been accepted by a worker and is being treated.\n Your order ID is: ";
            assertEquals(ExpMsg.startsWith("Your order has been accepted"), ActMsg.startsWith("Your order has been accepted"));
        });
    }

    @When("i click on complete button")
    public void i_click_on_complete_button() {
        Platform.runLater(() -> {
            WorkersceneCont obj = new WorkersceneCont();
        String ActMsg =obj.getMsg();
        String ExpMsg = "Your order has been completed.\n Your order ID is: " + obj.getTextID() + "\n Thank you for using our service.\n";
        assertEquals(ExpMsg.startsWith("Your order has been completed"),ActMsg.startsWith("Your order has been completed"));
        });
    }
}
