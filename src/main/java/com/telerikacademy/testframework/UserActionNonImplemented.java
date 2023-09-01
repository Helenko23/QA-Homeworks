package com.telerikacademy.testframework;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static com.telerikacademy.testframework.Utils.LOGGER;

public class UserActionNonImplemented {
    public WebDriver driver;



    public void switchToIFrame(String iframe) {
        // TODO: Implement the method
        // 1. Get iframe locator value from properties by key
        // 2. Add Log entry for the action to be performed
        // 3. Switch to the frame
    }

    public boolean isElementPresent(String locator, Object... arguments) {
        // TODO: Implement the method
        // 1. Get default timeout from properties
        // 2. Initialize Wait utility
        // 3. Try to wait for element present
        // 4. return true/false if the element is/not present
        return true;
    }

    public boolean isElementVisible(String locator, Object... arguments) {
        // TODO: Implement the method
        // 1. Get default timeout from properties
        // 2. Initialize Wait utility
        // 3. Try to wait for element visible
        // 4. return true/false if the element is/not visible
        return true;
    }

    public void waitFor(long timeOutMilliseconds) {
        try {
            Thread.sleep(timeOutMilliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //############# ASSERTS #########
    public void assertNavigatedUrl(String urlKey) {
        // TODO: Implement the method
        // 1. Get Current URL
        String currentUrl = driver.getCurrentUrl();

//        // 2. Get expected url by urlKey from Properties
//        String expectedUrl = return properties.getProperty(urlKey);
//
//        // 3. Assertion
//        if (currentUrl.equals(expectedUrl)) {
//            System.out.println("Assertion passed: Navigated URL is as expected.");
//        } else {
//            System.out.println("Assertion failed: Navigated URL is not as expected.");
//        }

    }

    public void pressKey(Keys key) {
        // TODO: Implement the method
        // 1. Initialize Actions
        // 2. Perform key press
    }

    public String getElementAttribute(String locator, String attributeName) {
        // TODO: Implement the method
        // 1. Find Element using the locator value from Properties
        // 2. Get the element attribute
        // 3. Return the expected value
        return "";
    }
}
