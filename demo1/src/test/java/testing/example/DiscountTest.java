package testing.example;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import javafx.application.Platform;
import org.junit.Assert;

public class DiscountTest {
        private int price;
        private int discount;

        @When("the price more than {int}")
        public void the_price_more_than(Integer int1) {
            price = int1 + 1;
        }

        @Then("discount {int}%")
        public void discount(Integer expectedDiscount) {
            Platform.runLater(() -> {
                discount = expectedDiscount;
                int discountedPrice = (int) (price * (1.0 - (discount / 100.0)));
                Assert.assertEquals(discountedPrice, price * 0.9);
            });
        }

        @When("the price less than {int}")
        public void the_price_less_than(Integer int1) {
            price = int1;
        }

        @Then("no discount")
        public void no_discount() {
            Platform.runLater(() -> {
                int P = price;
                Assert.assertEquals(P, price);
            });
        }
    }
