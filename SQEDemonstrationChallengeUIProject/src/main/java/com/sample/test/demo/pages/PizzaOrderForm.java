package com.sample.test.demo.pages;


import com.sample.test.demo.utils.GenericMethodUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class PizzaOrderForm {

    protected WebDriver driver;
    protected WebDriverWait wait;
    @FindBy(id = "pizza1Pizza")
    private WebElement pizza1;

    @FindBy(xpath = "//div[@id='pizza1']//select[@class='toppings1']")
    private WebElement pizza1Toppings1;

    @FindBy(xpath = "//div[@id='pizza1']//select[@class='toppings2']")
    private WebElement pizza1Toppings2;

    @FindBy(id = "pizza1Qty")
    private WebElement pizza1Quantity;

    @FindBy(id = "pizza1Cost")
    private WebElement pizza1Cost;

    @FindBy(id = "name")
    private WebElement name;

    @FindBy(id = "phone")
    private WebElement phone;

    @FindBy(id = "cashpayment")
    private WebElement radioCash;

    @FindBy(id = "ccpayment")
    private WebElement radioCreditCard;
    @FindBy(xpath = "//h1//center")
    private WebElement pizzaFormTitle;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "placeOrder")
    private WebElement placeOrderButton;

    @FindBy(id = "reset")
    private WebElement resetButton;

    @FindBy(id = "dialog")
    private WebElement dialog;

    @FindBy(xpath = "//div[@id='dialog']/p")
    private WebElement dialogText;

    static Logger log = LogManager.getLogger(GenericMethodUtils.class.getName());

    /**
     * PizzaOrderForm
     *
     * @param driver
     * @author riteshdugini
     */
    public PizzaOrderForm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 120);
    }

    /**
     * verifyPageTitle
     *
     * @author riteshdugini
     */
    public void verifyPageTitle() {
        log.info("Entered into verifyPageTitle");
        GenericMethodUtils.waitForElementVisible(wait, pizzaFormTitle);
        Assert.assertTrue(pizzaFormTitle.getText().contains("Pizza Order Form"), "Page Not Loaded Properly. Page Title Expected Pizza Order Form But Actual :" + pizzaFormTitle.getText());
    }

    /**
     * selectPizzaName
     *
     * @param pizzaName - name of pizza to be selected
     * @author riteshdugini
     */
    public void selectPizzaName(String pizzaName) {
        log.info("Entered into selectPizzaName :" + pizzaName);
        GenericMethodUtils.selectDropDownByText(pizza1, pizzaName);
    }

    /**
     * selectPizzaTopping1
     *
     * @param topping1 -  Topping1 to be selected
     * @author riteshdugini
     */
    public void selectPizzaTopping1(String topping1) {
        log.info("Entered into selectPizzaTopping1 :" + topping1);
        GenericMethodUtils.selectDropDownByText(pizza1Toppings1, topping1);
    }

    /**
     * selectPizzaTopping2
     *
     * @param topping2 - Topping2 to be selected
     * @author riteshdugini
     */
    public void selectPizzaTopping2(String topping2) {
        log.info("Entered into selectPizzaTopping2 :" + topping2);
        GenericMethodUtils.selectDropDownByText(pizza1Toppings2, topping2);
    }

    /**
     * enterPizzaQty
     *
     * @param qty - quantity of pizza
     * @author riteshdugini
     */
    public void enterPizzaQty(String qty) {
        log.info("Entered into enterPizzaQty :" + qty);
        GenericMethodUtils.waitForElementVisible(wait, pizza1Quantity);
        pizza1Quantity.clear();
        pizza1Quantity.sendKeys(qty);
    }

    /**
     * enterPickupDetails
     *
     * @param nameText  - name of person placing order
     * @param phoneText - phone of person placing order
     * @author riteshdugini
     */
    public void enterPickupDetails(String nameText, String phoneText) {
        log.info("Entered into enterPickupDetails Name :" + nameText + " Phone:" + phoneText);
        GenericMethodUtils.waitForElementVisible(wait, name);
        name.clear();
        name.sendKeys(nameText);
        GenericMethodUtils.waitForElementVisible(wait, phone);
        phone.clear();
        phone.sendKeys(phoneText);
    }

    /**
     * selectPaymentType
     *
     * @param type - type of payment to be sent
     * @author riteshdugini
     */
    public void selectPaymentType(String type) {
        log.info("Entered into selectPaymentType :" + type);
        switch (type) {
            case "CASH":
                GenericMethodUtils.waitForElementVisible(wait, radioCash);
                radioCash.click();
                break;
            case "CC":
                GenericMethodUtils.waitForElementVisible(wait, radioCreditCard);
                radioCreditCard.click();
                break;
            default:
                Assert.assertTrue(false, "Invalid Payment Type Entered");
                break;
        }
    }

    /**
     * isPlaceOrderEnabled
     *
     * @author riteshdugini
     */
    public boolean isPlaceOrderEnabled() {
        log.info("Entered into isPlaceOrderEnabled");
        GenericMethodUtils.waitForElementVisible(wait, placeOrderButton);
        return GenericMethodUtils.isElementEnabled(placeOrderButton);

    }

    /**
     * placeOrder
     *
     * @author riteshdugini
     */
    public void placeOrder() {
        log.info("Entered into placeOrder");
        Assert.assertTrue(isPlaceOrderEnabled(), "Place Order Button is Not Enabled");
        placeOrderButton.click();
        GenericMethodUtils.waitForElementVisible(wait, dialog);
    }

    /**
     * verifyOrderMessage
     *
     * @author riteshdugini
     */
    public void verifyOrderMessage() {
        log.info("Entered into verifyOrderMessage");
        GenericMethodUtils.waitForElementVisible(wait, dialogText);
        Assert.assertTrue(dialogText.getText().trim().contains("Thank you for your order!"), "Order Success Message Invalid Expected : Thank you for your order!");
    }
}
