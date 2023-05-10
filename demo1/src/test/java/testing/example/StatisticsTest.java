package testing.example;

import com.example.demo1.Scene3Controller;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javafx.application.Platform;
import org.junit.Assert;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StatisticsTest {

    private int numOrders = 0;
    private int totalCash = 0;
    @When("i click on total sales button")
    public void i_click_on_total_sales_button() {
        Platform.runLater(() -> {
            Scene3Controller obj = new Scene3Controller();
            Assert.assertTrue(obj.isCo());
        });
    }

    @Then("it will print the result in sales textfield")
    public void it_will_print_the_result_in_sales_textfield() {
        Platform.runLater(() -> {
            Scene3Controller obj = new Scene3Controller();
            try (BufferedReader reader = new BufferedReader(new FileReader("Reports.txt"))) {
                while (reader.readLine() != null) {
                    numOrders++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            Assert.assertEquals(String.valueOf(numOrders),obj.getsalesField() );

        });
    }

    @When("i click on cash button")
    public void i_click_on_cash_button() {
        Platform.runLater(() -> {
            Scene3Controller obj = new Scene3Controller();
            Assert.assertTrue(obj.isCh());

        });
    }

    @Then("it will print the result in cash textfield")
    public void it_will_print_the_result_in_cash_textfield() {
        Platform.runLater(() -> {
            Scene3Controller obj = new Scene3Controller();
            try (BufferedReader reader = new BufferedReader(new FileReader("Reports.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] tokens = line.split("\t");
                    totalCash += Integer.parseInt(tokens[tokens.length - 1]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            Assert.assertEquals(String.valueOf(totalCash),obj.getcashField() );

        });
    }
}
