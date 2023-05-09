package testing.example;

import com.example.demo1.Scene2Controller;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import java.io.IOException;
import static org.junit.Assert.assertEquals;

public class ShippingTest {

    Scene2Controller obj = new Scene2Controller();


    @When("i choose delivery method")
    public void i_choose_delivery_method() throws IOException {
        obj.deliveryBH();
    }

    @Then("i should be asked for my location")
    public void i_should_be_asked_for_my_location() throws IOException {
        obj.locatioHandle();
    }

    @When("i choose pickup method")
    public void i_choose_pickup_method() throws IOException {
        Platform.runLater(() -> {
            try {
                obj.pickupBH();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertEquals("Pickup",obj.shippingType.getText());

        });

    }

    @Then("i should receive a message when it's ready")
    public void i_should_receive_a_message_when_it_s_ready() {
        Platform.runLater(() -> {
            obj.readymessage();
            Alert alert = obj.alert;
            assertEquals("Notification...", alert.getTitle());
            assertEquals(null, alert.getHeaderText());
            assertEquals("You will Receive A Message When It's Ready", alert.getContentText());

        });
    }

}
