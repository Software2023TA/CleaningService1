package testing.example;
import com.example.demo1.Main;
import com.example.demo1.Scene4Controller;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

public class NewCustomerTest {

    Scene4Controller controller = new Scene4Controller();
    @When("the user enters valid data")
    public void the_user_enters_valid_data() throws IOException {
        Platform.runLater(() -> {
            try {
                Main.scene4 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Scene4.fxml")));
                assertTrue(Scene4Controller.validInputUser);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @When("the user clicks on the Sign up button")
    public void the_user_clicks_on_the_sign_up_button() {
        Platform.runLater(() -> {
            try {
                Main.scene4 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Scene4.fxml")));
                Scene4Controller controller = new Scene4Controller();
                controller.signup.fire();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Then("the user data should be saved in the database")
    public void the_user_data_should_be_saved_in_the_database() throws IOException {
        Platform.runLater(() -> {
            try {
                Main.scene4 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Scene4.fxml")));
                String username = controller.getUsername();
                String password = controller.getPassword();
                String email =  controller.getEmail();
                String number = controller.getPhone();
                String expectedData = username + "," + password + "," + email + "," + number;

                List<String> lines = null;
                try {
                    lines = Files.readAllLines(Paths.get("Customers.txt"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String actualData = String.join(",", lines);
                assertEquals(expectedData, actualData);
                assertTrue(Scene4Controller.validInputUser);
                System.out.println("VALIDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @When("the user enters invalid data")
    public void the_user_enters_invalid_data() throws IOException {
        Platform.runLater(() -> {
            try {
                Main.scene4 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Scene4.fxml")));
                assertFalse(Scene4Controller.validInputUser);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Then("the user data should not be saved in the database")
    public void the_user_data_should_not_be_saved_in_the_database() throws IOException {
        Platform.runLater(() -> {
            try {
                Main.scene4 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Scene4.fxml")));
                String username = controller.getUsername();
                String password = controller.getPassword();
                String email = controller.getEmail();
                String number = controller.getPhone();
                String expectedData = username + "," + password + "," + email + "," + number;

                List<String> lines = null;
                try {
                    lines = Files.readAllLines(Paths.get("Customers.txt"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String actualData = String.join(",", lines);
                assertNotEquals(expectedData, actualData);
                System.out.println("INNNNNNNNNNNNNNNNNNNNNNNNNVALIDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
