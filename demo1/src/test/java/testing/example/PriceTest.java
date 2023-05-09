package testing.example;
import javafx.scene.control.Alert;
import com.example.demo1.Scene2Controller;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javafx.application.Platform;
import javafx.scene.control.TextField;
import java.io.IOException;
import static org.junit.Assert.*;

public class PriceTest {

    Scene2Controller obj = new Scene2Controller();

    @When("i choose 200x100 size")
    public void i_choose_200x100_size() throws IOException {
        obj.sizeHandle1();
        TextField sizetxt = new TextField();
        sizetxt.setText("you chose 200x100");

        obj.sizetxt = sizetxt;
        assertNotNull(obj.sizetxt);
        assertEquals("you chose 200x100",sizetxt.getText());
    }

    @When("i choose speed cleaning")
    public void i_choose_speed_cleaning() {
        Platform.runLater(() -> {
            obj.speedBH();
            Alert alert = obj.alert;
            assertEquals("Speed Cleaning", alert.getTitle());
            assertEquals(null, alert.getHeaderText());
        });
    }

    @Then("the price will be {int}")
    public void the_price_will_be(Integer int1) throws IOException {
        Platform.runLater(() -> {
            Alert alert = obj.alert;
            assertNotNull(alert);
            assertEquals("The Price: "+ obj.getprice(), alert.getContentText());
        });

    }

    @When("i choose 200x200 size")
    public void i_choose_200x200_size() {
        obj.sizeHandle2();
        TextField sizetxt = new TextField();
        sizetxt.setText("you chose 200x200");

        obj.sizetxt = sizetxt;
        assertNotNull(obj.sizetxt);
        assertEquals("you chose 200x200",sizetxt.getText());
    }

    @When("i choose deep cleaning")
    public void i_choose_deep_cleaning() {
        Platform.runLater(() -> {
            obj.speedBH();
            Alert alert = obj.alert;
            assertEquals("Deep Cleaning", alert.getTitle());
            assertEquals(null, alert.getHeaderText());
        });
    }

    @When("i choose 300x200 size")
    public void i_choose_300x200_size() {
        obj.sizeHandle3();
        TextField sizetxt = new TextField();
        sizetxt.setText("you chose 300x200");

        obj.sizetxt = sizetxt;
        assertNotNull(obj.sizetxt);
        assertEquals("you chose 300x200",sizetxt.getText());

    }

    @When("i choose dry cleaning")
    public void i_choose_dry_cleaning() throws InterruptedException {
        Platform.runLater(() -> {
            obj.speedBH();
            Alert alert = obj.alert;
            assertEquals("Dry Cleaning", alert.getTitle());
            assertEquals(null, alert.getHeaderText());
        });
    }
}
