package testing.example;

import com.example.demo1.Scene1Controller;
import com.example.demo1.WorkersceneCont;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.junit.Assert;
import org.junit.Before;

import java.io.IOException;

public class AvailabilityTest {
    private String orderId;

    @When("the worker enter the order ID")
    public void the_worker_enter_the_order_id() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        Platform.runLater(() -> {
        WorkersceneCont ob = new WorkersceneCont();
        orderId = ob.getTextID();
        });
    }

    @When("click on Waiting button")
    public void click_on_waiting_button() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
    }

    @Then("his status will be unavailable")
    public void his_status_will_be_unavailable() {
        // Write code here that turns the phrase above into concrete actions
        Platform.runLater(() -> {
            WorkersceneCont obj = new WorkersceneCont();
            Scene1Controller obj1 = new Scene1Controller();
            String workerStatus = null; // Get the worker's status from the text file
            try {
                workerStatus = obj.getWorkerStatus(obj1.getScene2Username());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Assert.assertEquals("Unavailable", workerStatus);

        });
            //throw new io.cucumber.java.PendingException();
    }

    @When("click on Complete button")
    public void click_on_complete_button() {
        // Write code here that turns the phrase above into concrete actions

        //throw new io.cucumber.java.PendingException();
    }

    @Then("his status will be available")
    public void his_status_will_be_available() {
        // Write code here that turns the phrase above into concrete actions
        Platform.runLater(() -> {
            WorkersceneCont obj = new WorkersceneCont();
            Scene1Controller obj1 = new Scene1Controller();

            String workerStatus = null; // Get the worker's status from the text file
            try {
                workerStatus = obj.getWorkerStatus(obj1.getScene2Username());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Assert.assertEquals("Available", workerStatus);

        });
        //throw new io.cucumber.java.PendingException();
    }
}
