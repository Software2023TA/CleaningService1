package testing.example;

import com.example.demo1.Main;
import com.example.demo1.Scene2Controller;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javafx.event.ActionEvent;
import java.io.IOException;
import static org.junit.Assert.assertEquals;

public class LogoutTest {

    Scene2Controller scene2Controller = new Scene2Controller();


    @When("I click on the logout button")
    public void i_click_on_the_logout_button() throws IOException {
        scene2Controller.switchScene1(new ActionEvent());
    }
    @Then("I should be logged out from the application")
    public void i_should_be_logged_out_from_the_application() throws IOException {
        assertEquals(Main.scene1, Main.getCurrentScene());
    }

}
