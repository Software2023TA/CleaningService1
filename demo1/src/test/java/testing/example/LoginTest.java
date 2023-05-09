package testing.example;

import com.example.demo1.Main;
import com.example.demo1.Scene1Controller;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javafx.application.Application;
import javafx.event.ActionEvent;
import org.testfx.api.FxRobot;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

public class LoginTest extends FxRobot {

        String username;
    String password;
    String filePath = "C:\\Users\\MsI\\Desktop\\ProjectSoft\\demo1\\Untitled.txt";
    Boolean Check;
    Scene1Controller scene1Controller = new Scene1Controller();


  @BeforeAll
        public static void before_all() {
            Application.launch(Main.class);
        }

    @When("I login and the credentials are valid")
    public void i_login_and_the_credentials_are_valid() throws IOException {
        scene1Controller.login(new ActionEvent ());
        assertFalse(scene1Controller.getValidCredentials());
    }

    @Then("I should be logged in to the application")
    public void i_should_be_logged_in_to_the_application() throws IOException {
       assertEquals(Main.scene1, Main.getCurrentScene());
    }

    @When("I login  and the credentials are invalid")
    public void i_login_and_the_credentials_are_invalid() throws IOException {
        scene1Controller.login(new ActionEvent ());
        assertFalse(scene1Controller.getValidCredentials());

    }

    @Then("I should not be logged in to the application, I should see an error message")
    public void i_should_not_be_logged_in_to_the_application_i_should_see_an_error_message() {
        assertEquals(Main.scene1, Main.getCurrentScene());
    }

    @When("I login with blank credentials")
    public void i_login_with_blank_credentials() throws IOException {
        scene1Controller.login(new ActionEvent ());
    }
    }

