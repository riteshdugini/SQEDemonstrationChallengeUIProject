package com.sample.test.demo.tests;

import com.sample.test.demo.TestBase;
import com.sample.test.demo.constants.PizzaToppings;
import com.sample.test.demo.constants.PizzaTypes;
import com.sample.test.demo.pages.PizzaOrderForm;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PizzaFormTest extends TestBase {

    private PizzaOrderForm pizzaOrderForm;

    @BeforeClass
    public void initTest() {
        pizzaOrderForm = new PizzaOrderForm(driver);
    }

    @Test(groups = "Order-Positive", description = "Test case 6. Order Medium 2 topping with Cash and Verify Message", priority = 0)
    public void validateValidOrderSubmit() {
        pizzaOrderForm.verifyPageTitle();
        pizzaOrderForm.selectPizzaName(PizzaTypes.MEDIUM_TWOTOPPINGS.getDisplayName() + " $" + PizzaTypes.MEDIUM_TWOTOPPINGS.getCost());
        pizzaOrderForm.selectPizzaTopping1(PizzaToppings.OLIVES.getDisplayName());
        pizzaOrderForm.selectPizzaTopping2(PizzaToppings.MUSHROOMS.getDisplayName());
        pizzaOrderForm.enterPizzaQty("1");
        pizzaOrderForm.enterPickupDetails("Ritesh", "3031234567");
        pizzaOrderForm.selectPaymentType("CASH");
        pizzaOrderForm.placeOrder();
        pizzaOrderForm.verifyOrderMessage();
    }

    @Test(groups = "Order-Negative", description = "Test case 18. Order Small 1 topping without payment selected", priority = 1)
    public void validateInvalidOrderSubmit() {
        pizzaOrderForm.verifyPageTitle();
        pizzaOrderForm.selectPizzaName(PizzaTypes.SMALL_NOTOPPINGS.getDisplayName() + " $" + PizzaTypes.SMALL_NOTOPPINGS.getCost());
        pizzaOrderForm.selectPizzaTopping1(PizzaToppings.OLIVES.getDisplayName());
        pizzaOrderForm.selectPizzaTopping2(PizzaToppings.MUSHROOMS.getDisplayName());
        pizzaOrderForm.enterPizzaQty("1");
        pizzaOrderForm.enterPickupDetails("Ritesh", "12345678");
        Assert.assertFalse(pizzaOrderForm.isPlaceOrderEnabled(), "Place Order Button Should Not Be Enabled Without Payment Type Selection");
    }
}
