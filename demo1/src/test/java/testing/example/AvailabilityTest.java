package testing.example;

import com.example.demo1.Scene1Controller;
import com.example.demo1.WorkersceneCont;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javafx.application.Platform;
import javafx.scene.control.TextField;
import org.junit.Assert;

import java.io.IOException;

public class AvailabilityTest {
    private TextField orderId;

    @When("the worker enter the order ID")
    public void the_worker_enter_the_order_id() {
        WorkersceneCont ob = new WorkersceneCont();
        orderId = ob.getTextID();
    }

    @When("click on Waiting button")
    public void click_on_waiting_button() throws IOException {
        WorkersceneCont obj = new WorkersceneCont();
        Assert.assertTrue(obj.W);
    }

    @Then("his status will be unavailable")
    public void his_status_will_be_unavailable() {
        Platform.runLater(() -> {
            WorkersceneCont obj = new WorkersceneCont();
            Scene1Controller obj1 = new Scene1Controller();
            String workerStatus = null;
            try {
                workerStatus = obj.getWorkerStatus(obj1.getUsername());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Assert.assertEquals("Unavailable", workerStatus);

        });
    }

    @When("click on Complete button")
    public void click_on_complete_button() {
        WorkersceneCont obj = new WorkersceneCont();
        Assert.assertTrue(obj.C);
    }

    @Then("his status will be available")
    public void his_status_will_be_available() {
        Platform.runLater(() -> {
            WorkersceneCont obj = new WorkersceneCont();
            Scene1Controller obj1 = new Scene1Controller();

            String workerStatus = null;
            try {
                workerStatus = obj.getWorkerStatus(obj1.getUsername());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Assert.assertEquals("Available", workerStatus);

        });
    }
}
