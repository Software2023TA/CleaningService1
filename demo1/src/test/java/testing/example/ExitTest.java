package testing.example;

import com.example.demo1.Scene1Controller;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javafx.event.ActionEvent;
import java.io.IOException;

import static org.junit.Assert.assertTrue;


public class ExitTest {

    Scene1Controller scene1Controller = new Scene1Controller();

    @When("I click the exit option")
    public void i_click_the_exit_option() throws IOException {
        scene1Controller.exit(new ActionEvent());
    }

    @When("I press ALTF4 option")
    public void i_press_alt_f4_option() throws IOException {
        scene1Controller.exit(new ActionEvent());
    }

    @When("I click the X")
    public void i_click_the_x()throws IOException {
        scene1Controller.exit(new ActionEvent());
    }
    @Then("the application should exit")
    public void the_application_should_exit() {
        assertTrue(Scene1Controller.isApplicationClosed());
    }

}
