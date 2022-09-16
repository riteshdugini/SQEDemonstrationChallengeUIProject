package com.sample.test.demo.utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenericMethodUtils {

    /**
     * isElementEnabled
     *
     * @param element - Web element to be checked
     * @author riteshdugini
     */
    public static boolean isElementEnabled(WebElement element) {
        return element.isEnabled();
    }

    /**
     * waitForElementVisible
     *
     * @param element - Web element to be checked
     * @author riteshdugini
     */
    public static void waitForElementVisible(WebDriverWait wait, WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * selectDropDownByText
     *
     * @param element - Web element to be checked
     * @param text    - text to be selected in dropdown
     * @author riteshdugini
     */
    public static void selectDropDownByText(WebElement element, String text) {
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(text);
    }
}
