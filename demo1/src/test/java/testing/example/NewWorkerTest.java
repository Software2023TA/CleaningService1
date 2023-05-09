package testing.example;

import com.example.demo1.Main;
import com.example.demo1.Scene3Controller;
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


public class NewWorkerTest {

    Scene3Controller controller = new Scene3Controller();

    @When("the Admin enters valid data")
    public void the_admin_enters_valid_data() {
        Platform.runLater(() -> {
            try {
                Main.scene3 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Scene3.fxml")));
                assertTrue(Scene3Controller.validWorkerInput);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    @When("the Admin clicks on the Create New worker button")
    public void the_admin_clicks_on_the_create_new_worker_button() {
        Platform.runLater(() -> {
            try {
                Main.scene3 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Scene3.fxml")));
                controller.neworker.fire();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    @Then("the Wroker data should be saved in the database")
    public void the_wroker_data_should_be_saved_in_the_database() {
        Platform.runLater(() -> {
            try {
                Main.scene3 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Scene3.fxml")));
                String firstname = controller.getfirstname();
                String lastname = controller.getlastname();
                String username = controller.getusername();
                String password = controller.getpassword();
                String email = controller.getemail();
                String number = controller.getnumber();
                String expectedData = firstname + " " + lastname + " " + username + " " + password + " " + email + " " + number;

                List<String> lines = null;
                try {
                    lines = Files.readAllLines(Paths.get("Workers.txt"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String actualData = lines.get(lines.size() - 1);
                assertEquals(expectedData, actualData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @When("the Admin enters invalid data")
    public void the_admin_enters_invalid_data() {
        Platform.runLater(() -> {
            try {
                Main.scene3 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Scene3.fxml")));
                assertFalse(Scene3Controller.validWorkerInput);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Then("the Wroker data should not be saved in the database")
    public void the_wroker_data_should_not_be_saved_in_the_database() {
        Platform.runLater(() -> {
            try {
                Main.scene3 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/Scene3.fxml")));
                String firstname = controller.getfirstname();
                String lastname = controller.getlastname();
                String username = controller.getusername();
                String password = controller.getpassword();
                String email = controller.getemail();
                String number = controller.getnumber();
                String expectedData = firstname + " " + lastname + " " + username + " " + password + " " + email + " " + number;

                List<String> lines = null;
                try {
                    lines = Files.readAllLines(Paths.get("Workers.txt"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String actualData = lines.get(lines.size() - 1);
                assertNotEquals(expectedData, actualData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
